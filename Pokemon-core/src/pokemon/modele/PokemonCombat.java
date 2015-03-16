package pokemon.modele;

import java.util.Random;
import java.util.Stack;

public class PokemonCombat implements Comparable<PokemonCombat> {
	protected Pkm pkm;
	protected boolean isIA;
	protected int equipe;
	protected Joueur prop;
	protected PokemonCombat[] adv;
	protected PokemonCombat[] voisins;

	public PokemonCombat(Pkm p,boolean isIA){
		pkm=p;
		this.isIA=isIA;
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
}
