package pokemon.modele;

import java.util.Random;
import java.util.Stack;

import pokemon.annotations.Tps;

@Tps(nbhours=3)
public class PokemonCombat implements Comparable<PokemonCombat> {
	protected Pkm pkm;
	protected boolean isIA;
	protected int listeIndice;
	//protected int equipe;
	protected PokemonCombat[] equipe;
	protected Object prop;
	protected Stack<Pkm> XpStack;
	protected PokemonCombat[] adv;
	protected PokemonCombat[] voisins;

	public PokemonCombat(Pkm p,boolean isIA,Object j){
		pkm=p;
		this.isIA=isIA;
		listeIndice=-1;
		prop=j;
		this.XpStack=new Stack<Pkm>();
		this.adv=new PokemonCombat[5];
		this.voisins=new PokemonCombat[2];
	}
	
	public Pkm getPkm() {
		return pkm;
	}

	public boolean isIA() {
		return isIA;
	}

	public int compareTo(PokemonCombat p) {
		if(p.pkm.stats[7][0]==this.pkm.stats[7][0])
				return 0;
		else if (p.pkm.stats[7][0]>this.pkm.stats[7][0])
			return 1;
		else
			return -1;
	} 
	
	public void action(PokemonCombat cible,Combat context){
		if(!isIA){
			context.action(this,cible);
		}
		else{
			context.ajoutBuffer("Tour de l'IA");
			context.setfreeze(true);
			int ind=0;
			//IA de meilleur choix
			
			switch(pkm.IAbh){
			case 1:
				ind=bestdmg(cible,context);
				break;
			case 2:
				ind=this.bestdot(cible, context);
				break;
			case 3:
				ind=this.bestControl(cible, context);
				break;
			default:
				ind=0;
				break;
			}
			pkm.cap.utiliser(ind,pkm,cible.pkm,context);
			if(pkm.stats[2][0]<=0){ XPreward(); context.pokeswap(this); }
			if(cible.pkm.stats[2][0]<=0){ cible.XPreward(); context.pokeswap(cible); }
			if(pkm.stats[2][0]<=(int)(pkm.stats[2][1]/2) && cible.pkm.statut!=Statut.KO){
				if(pkm.objTenu instanceof Medicament && cible.pkm.objTenu!=null){
					Medicament m=(Medicament)pkm.objTenu;
					context.ajoutBuffer(pkm.nom+" utilise sa baie");
					if(m.baie){ m.script(pkm); pkm.objTenu=null; }
				}
			}
			if(cible.pkm.stats[2][0]<=(int)(cible.pkm.stats[2][1]/2) && cible.pkm.statut!=Statut.KO){
				if(cible.pkm.objTenu instanceof Medicament && cible.pkm.objTenu!=null){
					Medicament m=(Medicament)cible.pkm.objTenu;
					context.ajoutBuffer(cible.pkm.nom+" utilise sa baie");
					if(m.baie){ m.script(cible.pkm); cible.pkm.objTenu=null; }
				}
			}
		}
	}
	
	//Choix de l'attaque la plus puissante
	public int bestdmg(PokemonCombat cible,Combat context){
		int maxdmg=0; int dmgtemp=0; int ind=0;
		for(int i=0;i<pkm.cap.contenu.size();i++){
			dmgtemp=pkm.cap.contenu.elementAt(i).cible.atkdamage(pkm, cible.pkm, context,true);
			//Choix de l'attaque la plus forte
			if(dmgtemp>maxdmg){ ind=i; maxdmg=dmgtemp; }
			//En cas d'egalité de degats
			if(dmgtemp==maxdmg){
				//Choix de l'attaque la plus precise
				if(pkm.cap.contenu.elementAt(i).cible.pre>pkm.cap.contenu.elementAt(ind).cible.pre){
					ind=i;
				}
				else{
					//Sinon choix de l'attaque la plus reutilisable
					if(pkm.cap.contenu.elementAt(i).quantite>pkm.cap.contenu.elementAt(ind).quantite){
						ind=i;
					}
				}
			}
		}
		return ind;
	}
	
	//Scores : Requiem 100 | Picots,Stuck 75 | Poison,Brule = 70 | Confus,Maudit =60 | Paralysie,Sommeil,Gel = 40
	public int dotScore(Capacite c,Pkm user,Pkm cible){
		int score=0;
		if(c instanceof Heal){ return 0; }
		if(c instanceof Atk){
			if(cible.statut==Statut.Normal){
				if(((Atk)c).effet==Statut.Empoisonne || ((Atk)c).effet==Statut.Brule){
					score+=70;
				}
				if(((Atk)c).effet==Statut.Paralyse || ((Atk)c).effet==Statut.Endormi || ((Atk)c).effet==Statut.Gele){
					score+=40;
				}
			}
			else if(cible.supTemp.contains(((Atk) c).effet)){
				if(((Atk) c).effet==Statut.Requiem){ score+=100; }
				if(((Atk) c).effet==Statut.Picots || ((Atk) c).effet==Statut.Stuck){ score+=75; }
				if(((Atk) c).effet==Statut.Confus || (((Atk) c).effet==Statut.Maudit && user.type.contains(Type.Spectre))){
					score+=60;
				}
			}
			//Ajout d'une base au score
			score+=c.pre;
			score+=(int)(c.maxPP/5);
			if(c instanceof AtkRecul && (user.stats[2][0]<(int)(user.stats[2][1]/2))){
				score-=15;
			}
			if(c instanceof AtkSoin ){
				if(user.stats[2][0]<(int)(user.stats[2][1]/2)){
					score+=20;
				}
				else if(cible.capP==CapacitePassive.Suintement){
					score-=20;
				}
			}
		}
		return score;
	}
	
	
	
	public int bestdot(PokemonCombat cible,Combat context){
		int ind=0; int maxScore=0; int scoreTemp=0;
		for(int i=0;i<pkm.cap.contenu.size();i++){
			//Dans le cas ou l'adversaire n'pas statut on cherche d'abord a lui en infliger un
			//Strat�gie qui vise a infliger Poison ou Brulure + Maudit + Requiem + Stuck
			//On cherche d'abord a infliger Requiem
			scoreTemp=dotScore(this.pkm.cap.elementAt(i).get(),pkm,cible.pkm);
			if(maxScore<scoreTemp){ maxScore=scoreTemp; ind=i; }
			if(maxScore==scoreTemp){
				ind=bestdmg(cible,context);
			}
		}
		return ind;
	}
	
	
	//Permet de choisir la meilleur attaque de controle pour empecher son adversaire d'attaquer et lui infliger des dégats
	public int controlScore(Capacite c,Pkm user,Pkm cible){
		int score=0;
		//On cherche a infliger Sommeil ou Paralysie ou Gel + confusion + Attraction + Peur
		if(c instanceof Atk){
			if(cible.statut==Statut.Normal){
				if(((Atk)c).effet==Statut.Empoisonne || ((Atk)c).effet==Statut.Brule){
					score+=20;
				}
				if(((Atk)c).effet==Statut.Paralyse || ((Atk)c).effet==Statut.Endormi || ((Atk)c).effet==Statut.Gele){
					score+=70;
				}
			}
			else if(cible.supTemp.contains(((Atk) c).effet)){
				if(((Atk) c).effet==Statut.Requiem){ score+=10; }
				if(((Atk) c).effet==Statut.Picots || ((Atk) c).effet==Statut.Stuck){ score+=30; }
				if(((Atk) c).effet==Statut.Confus || ((Atk) c).effet==Statut.Attraction || ((Atk) c).effet==Statut.Peur ){
					score+=50;
				}
				//Ajout d'une base au score
				score+=c.pre;
				score+=(int)(c.maxPP/5);
				if(c instanceof AtkRecul && (user.stats[2][0]<(int)(user.stats[2][1]/2))){
					score-=15;
				}
				if(c instanceof AtkSoin ){
					if(user.stats[2][0]<(int)(user.stats[2][1]/2)){
						score+=20;
					}
					else if(cible.capP==CapacitePassive.Suintement){
						score-=20;
					}
				}
			}
		}
		return score;
	}
	
	public int bestControl(PokemonCombat cible,Combat context){
		int ind=0; int maxScore=0; int scoreTemp=0;
		for(int i=0;i<pkm.cap.contenu.size();i++){
			scoreTemp=controlScore(this.pkm.cap.elementAt(i).get(),pkm,cible.pkm);
			if(maxScore<scoreTemp){ maxScore=scoreTemp; ind=i; }
			if(maxScore==scoreTemp){
				ind=bestdmg(cible,context);
			}
		}
		return ind;
	}
	
	public void setPokemon(Pkm p){ pkm=p; }
	
	//A modifier pour prendre en compte les differents bonus
	public void XPreward(){
		Pkm temp;
		int XP=(int)( (Pokedex.values()[pkm.ID-1].baseXP*pkm.stats[0][0]*1.5)/7 );
		
		while(!XpStack.empty()){
			temp=XpStack.pop();
			temp.addXP(XP);
			System.out.println(temp.nom+" gagne "+XP+" pts d'experience: "+temp.stats[1][0]+"/"+temp.stats[1][1]);
		}
		
	}
	
}
