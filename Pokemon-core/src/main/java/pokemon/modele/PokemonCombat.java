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
	protected CombatInfos prop;
	protected Stack<Pkm> XpStack;
	protected PokemonCombat[] adv;
	protected PokemonCombat[] voisins;
	protected int swap;

	public PokemonCombat(Pkm p,boolean isIA,CombatInfos j){
		pkm=p;
		this.isIA=isIA;
		listeIndice=-1;
		prop=j;
		this.XpStack=new Stack<Pkm>();
		this.adv=new PokemonCombat[5];
		this.voisins=new PokemonCombat[2];
		swap=-1;
	}
	
	public PokemonCombat(PokemonCombat cp){
		pkm=cp.pkm;
		isIA=cp.isIA;
		listeIndice=cp.listeIndice;
		equipe=cp.equipe;
		prop=cp.prop;
		XpStack=cp.XpStack;
		adv=cp.adv;
		voisins=cp.voisins;
		swap=cp.swap;
	}
	
	public void swapImport(PokemonCombat cp){
		pkm=cp.pkm;
		isIA=cp.isIA;
		listeIndice=cp.listeIndice;
		equipe=cp.equipe;
		prop=cp.prop;
		XpStack=cp.XpStack;
		swap=cp.swap;
	}
	
	
	public Pkm getPkm() { return pkm; }
	public boolean isIA() { return isIA; }
	public String getNom(){ return pkm.nom;}
	public int getId(){ return pkm.ID; }
	public Statut getStatut(){ return pkm.statut;}
	public PokemonCombat[] getAdv(){ return adv;}
	public PokemonCombat[] getEquipe(){ return equipe; }

	public int getSwap() {
		return swap;
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
		if(this.pkm.statut!=Statut.KO){
			if(!isIA){
				context.action(this,cible);
			}
			else{
				//context.ajoutBuffer("Tour de l'IA");
				int ind=0;
				//IA de meilleur choix
				
				switch(pkm.IAbh){
				case 1:
					System.out.println("IA BESTDMG");
					ind=bestdmg(cible,context);
					break;
				case 2:
					System.out.println("IA BESTDOT");
					ind=this.bestdot(cible, context);
					break;
				case 3:
					System.out.println("IA BESTCTRL");
					ind=this.bestControl(cible, context);
					break;
				default:
					ind=bestdmg(cible,context);
					break;
				}
				context.cibleCourante=cible;
				context.capCur=pkm.cap.elementAt(ind).cible;
				//System.out.println("FREEZE DE CHOIX DE CIBLE IA");
				//context.setfreeze(true);
				
				pkm.cap.utiliser(ind,pkm,cible.pkm,context);
				
				context.chercherKO();
				
				if(pkm.stats[2][0]<=(int)(pkm.stats[2][1]/2) && cible.pkm.statut!=Statut.KO){
					if(pkm.objTenu instanceof Medicament && cible.pkm.objTenu!=null){
						Medicament m=(Medicament)pkm.objTenu;
						context.ajoutBuffer(pkm.nom+" utilise sa baie");
						if(m.baie){ m.script(pkm,context); pkm.objTenu=null; }
					}
				}
				if(cible.pkm.stats[2][0]<=(int)(cible.pkm.stats[2][1]/2) && cible.pkm.statut!=Statut.KO){
					if(cible.pkm.objTenu instanceof Medicament && cible.pkm.objTenu!=null){
						Medicament m=(Medicament)cible.pkm.objTenu;
						context.ajoutBuffer(cible.pkm.nom+" utilise sa baie");
						if(m.baie){ m.script(cible.pkm,context); cible.pkm.objTenu=null; }
					}
				}
			}
			System.out.println("FREEZE DE FIN DE TOUR");
			context.setBufferState(true);
			context.setfreeze(true);
		}
		
	}
	
	//Choix de l'attaque la plus puissante
	public int bestdmg(PokemonCombat cible,Combat context){
		int maxdmg=0; int dmgtemp=0; int ind=0;
		for(int i=0;i<pkm.cap.contenu.size();i++){
			if(pkm.cap.contenu.elementAt(i).cible instanceof Heal){ dmgtemp=0;}
			else{
				dmgtemp=pkm.cap.contenu.elementAt(i).cible.atkdamage(pkm, cible.pkm, context,true);
			}
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
	
	//Scores : Requiem 100 | Picots,Stuck 70 | Poison,Brule = 70 | Confus,Maudit =60 | Paralysie,Sommeil,Gel = 40
	public int dotScore(Capacite c,Pkm user,Pkm cible){
		int score=0;
		if(c instanceof Heal){ return 0; }
		if(c instanceof Atk){
			if(((Atk)c).effet.principal){
				if(cible.statut==Statut.Normal){
					if(((Atk)c).effet==Statut.Empoisonne || ((Atk)c).effet==Statut.Brule || ((Atk)c).effet==Statut.Toxic ){
						score+=70;
					}
					if(((Atk)c).effet==Statut.Paralyse || ((Atk)c).effet==Statut.Endormi || ((Atk)c).effet==Statut.Gele){
						score+=30;
					}
					//Ajout d'une base au score
					score+=c.pre;
					score+=((Atk)c).effetProc;
				}
			}
			else if(!cible.supTemp.contains(((Atk) c).effet)){
				//if(((Atk) c).effet==Statut.Requiem){ score+=100; }
				if(((Atk) c).effet==Statut.Vampigraine){ score+=80; }
				if(((Atk) c).effet==Statut.Picots || ((Atk) c).effet==Statut.Piege){ score+=60; }
				if(((Atk) c).effet==Statut.Confus || (((Atk) c).effet==Statut.Maudit && user.type.contains(Type.Spectre))){
					score+=50;
				}
				//Ajout d'une base au score
				score+=c.pre;
				score+=((Atk)c).effetProc;
			}
			
			if(c instanceof AtkRecul && (user.stats[2][0]<(int)(user.stats[2][1]/2))){
				score-=15;
			}
			if(c instanceof AtkSoin ){
				if(user.stats[2][0]<(int)(user.stats[2][1]/2)){
					score+=20;
				}
				if(cible.capP==CapacitePassive.Suintement){
					score-=20;
				}
			}
		}
		return score;
	}
	
	
	
	public int bestdot(PokemonCombat cible,Combat context){
		int ind=0; int maxScore=0; int scoreTemp=0; int egalite=0;
		for(int i=0;i<pkm.cap.contenu.size();i++){
			//Dans le cas ou l'adversaire n'pas statut on cherche d'abord a lui en infliger un
			//Strat�gie qui vise a infliger Poison ou Brulure + Maudit + Requiem + Stuck
			//On cherche d'abord a infliger Requiem
			scoreTemp=dotScore(this.pkm.cap.elementAt(i).get(),pkm,cible.pkm);
			System.out.println(pkm.cap.at(i)+" "+scoreTemp);
			if(maxScore<scoreTemp){ maxScore=scoreTemp; scoreTemp=0; ind=i; }
			if(maxScore==scoreTemp){ egalite=maxScore;}
		}
		if(egalite==maxScore){ return bestdmg(cible,context); }
		return ind;
	}
	
	
	//Permet de choisir la meilleur attaque de controle pour empecher son adversaire d'attaquer et lui infliger des dégats
	public int controlScore(Capacite c,Pkm user,Pkm cible){
		int score=0;
		//On cherche a infliger Sommeil ou Paralysie ou Gel + confusion + Attraction + Peur
		if(c instanceof Atk){
			if(((Atk)c).effet.principal){
				if(cible.statut==Statut.Normal){
					if(((Atk)c).effet==Statut.Empoisonne || ((Atk)c).effet==Statut.Brule || ((Atk)c).effet==Statut.Toxic){
						score+=20;
					}
					if(((Atk)c).effet==Statut.Paralyse || ((Atk)c).effet==Statut.Endormi || ((Atk)c).effet==Statut.Gele){
						score+=70;
					}
					score+=c.pre;
					score+=((Atk)c).effetProc;
				}
			}
			else if(!cible.supTemp.contains(((Atk) c).effet)){
				if(((Atk) c).effet==Statut.Requiem){ score+=10; }
				if(((Atk) c).effet==Statut.Vampigraine){ score+=40; }
				if(((Atk) c).effet==Statut.Picots || ((Atk) c).effet==Statut.Piege){ score+=30; }
				if(((Atk) c).effet==Statut.Confus || ((Atk) c).effet==Statut.Attraction || ((Atk) c).effet==Statut.Peur ){
					score+=70;
				}
				//Ajout d'une base au score
				
				if(c instanceof AtkRecul && (user.stats[2][0]<(int)(user.stats[2][1]/2))){
					score-=15;
				}
				if(c instanceof AtkSoin ){
					if(user.stats[2][0]<(int)(user.stats[2][1]/2)){
						score+=20;
					}
					 if(cible.capP==CapacitePassive.Suintement){
						score-=20;
					}
				}
				score+=c.pre;
				score+=((Atk)c).effetProc;
			}
		}
		return score;
	}
	
	public int bestControl(PokemonCombat cible,Combat context){
		int ind=0; int maxScore=0; int scoreTemp=0; int egalite=0;
		for(int i=0;i<pkm.cap.contenu.size();i++){
			scoreTemp=controlScore(this.pkm.cap.elementAt(i).get(),pkm,cible.pkm);
			System.out.println(pkm.cap.at(i).nom+" "+scoreTemp);
			if(maxScore<scoreTemp){ maxScore=scoreTemp; scoreTemp=0; ind=i; }
			if(maxScore==scoreTemp){ egalite=maxScore; }
		}
		if(egalite==maxScore){ return bestdmg(cible,context); }
		return ind;
	}
	
	public void setPokemon(Pkm p){ pkm=p; }
	
	//A modifier pour prendre en compte les differents bonus
	public void XPreward(Combat context){
			Pkm temp;
			int XP=(int)( (Pokedex.values()[pkm.ID-1].baseXP*pkm.stats[0][0]*1.5)/7 );
			
			while(!XpStack.empty()){
				temp=XpStack.pop();
				temp.addXP(XP);
				if(this.isIA){
					context.ajoutBuffer(temp.nom+" gagne "+XP+" pts d'experience: "+temp.stats[1][0]+"/"+temp.stats[1][1]);
				}
			}
	}
	
	public synchronized void setSwap(int sw){ swap=sw; notify();	 }
	
	public synchronized void waitIAswap(){
		while(swap!=-1){
			try { wait(); } 
			catch(InterruptedException ie) { ie.printStackTrace(); }
		}
	}
	public synchronized void waitPlswap(){
		while(swap==-1){
			try { wait(); } 
			catch(InterruptedException ie) { ie.printStackTrace(); }
		}
	}
	
}
