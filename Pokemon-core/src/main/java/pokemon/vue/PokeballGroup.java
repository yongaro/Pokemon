package pokemon.vue;

import pokemon.launcher.PokemonCore;

import com.badlogic.gdx.scenes.scene2d.Group;

public class PokeballGroup extends Group{
	
	PokeballGroup(){
		int i=0;
		while(i<6 && PokemonCore.Jtest.getTeam()[i]!=null)
		{
				this.addActor(new Pokeball( PokemonCore.Jtest.getTeam()[i].get(2)!=0,i));
				i++;
		}
		this.setTransform(true);
		//this.addAction(Actions.sequence(Actions.delay(5f),Actions.rotateBy(360,2)));
	}
}