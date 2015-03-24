package pokemon.modele;

import java.util.Random;
import java.util.Stack;

public class PokemonCombat implements Comparable<PokemonCombat> {
	protected Pkm pkm;
	protected boolean isIA;
	protected int listeIndice;
	//protected int equipe;
	protected PokemonCombat[] equipe;
	protected Joueur prop;
	protected Stack<Pkm> XpStack;
	protected PokemonCombat[] adv;
	protected PokemonCombat[] voisins;

	public PokemonCombat(Pkm p,boolean isIA,Joueur j){
		pkm=p;
		this.isIA=isIA;
		listeIndice=-1;
		prop=j;
		this.XpStack=new Stack<Pkm>();
		this.adv=new PokemonCombat[5];
		this.voisins=new PokemonCombat[2];
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
			//IA de meilleur choix
			
			
			
			
		}
	}
	
	//Choix de l'attaque la plus puissante
	public int bestdmg(PokemonCombat cible,Combat context){
		int maxdmg=0; int dmgtemp=0; int ind=0;
		for(int i=0;i<pkm.cap.contenu.size();i++){
			dmgtemp=pkm.cap.contenu.elementAt(i).cible.atkdamage(pkm, cible.pkm, context.climat,true);
			//Choix de l'attaque la plus forte
			if(dmgtemp>maxdmg){ ind=i; maxdmg=dmgtemp; }
			//En cas d'égalité de dégats
			if(dmgtemp==maxdmg){
				//Choix de l'attaque la plus précise
				if(pkm.cap.contenu.elementAt(i).cible.pre>pkm.cap.contenu.elementAt(ind).cible.pre){
					ind=i;
				}
				else{
					//Sinon choix de l'attaque la plus réutilisable
					if(pkm.cap.contenu.elementAt(i).quantite>pkm.cap.contenu.elementAt(ind).quantite){
						ind=i;
					}
				}
			}
		}
		return ind;
	}
	
	//
	public int bestdot(PokemonCombat cible,Combat context){
		int ind=0; int maxdmg=0; int tempdmg=0;
		for(int i=0;i<pkm.cap.contenu.size();i++){
			//Dans le cas ou l'adversaire n'pas de changement de statut on cherche d'abord a lui en infliger un
				//On choisit l'attaque qui a le plus de chance de changer le statut
				if(pkm.cap.contenu.elementAt(i).cible instanceof Atk){
					if(((Atk)pkm.cap.contenu.elementAt(i).cible).effetProc>((Atk)pkm.cap.contenu.elementAt(ind).cible).effetProc){
						ind=i;
					}
					//En cas de probabilites egales
					if(((Atk)pkm.cap.contenu.elementAt(i).cible).effetProc==((Atk)pkm.cap.contenu.elementAt(ind).cible).effetProc){
						
						//On choisit l'attaque la plus precise
						if(pkm.cap.contenu.elementAt(i).cible.pre>pkm.cap.contenu.elementAt(ind).cible.pre){
							ind=i;
						}
						else{
							//sinon on choisit la  plus forte
							tempdmg=pkm.cap.contenu.elementAt(i).cible.atkdamage(pkm, cible.pkm,context.climat,true);
							if(tempdmg>maxdmg){
								maxdmg=tempdmg; ind =i;
							}
							else{
								//sinon on choisit la plus réutilisable
								if(pkm.cap.contenu.elementAt(i).quantite>pkm.cap.contenu.elementAt(ind).quantite){
									ind=i;
								}
							}
						}
					}
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
