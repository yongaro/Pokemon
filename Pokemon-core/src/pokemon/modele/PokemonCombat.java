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
