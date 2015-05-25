package pokemon.vue;

import pokemon.launcher.MyGdxGame;

import com.badlogic.gdx.scenes.scene2d.Group;

public class PokeballGroup extends Group{
	
	PokeballGroup(){
		int i=0;
		while(i<6 && MyGdxGame.Jtest.getTeam()[i]!=null)
		{
				this.addActor(new Pokeball( MyGdxGame.Jtest.getTeam()[i].get(2)!=0,i));
				i++;
		}
		this.setTransform(true);
		//this.addAction(Actions.sequence(Actions.delay(5f),Actions.rotateBy(360,2)));
	}
}