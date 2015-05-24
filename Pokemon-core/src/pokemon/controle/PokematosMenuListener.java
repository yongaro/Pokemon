package pokemon.controle;

import java.util.Vector;

import pokemon.launcher.MyGdxGame;
import pokemon.modele.Pokedex;
import pokemon.vue.menuInventaire;
import pokemon.vue.menuOptions;
import pokemon.vue.menuPokematos;
import pokemon.vue.menuPokematosMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PokematosMenuListener extends GameInput{
	menuPokematos menu;
	MyGdxGame myGdxGame;
	MenuListener menuListener;
	int state; //2-Pokemon slider 1 debloque /3-Pokemon valide slider 2 debloque /4-Affichage location
	Sound s;
	int pkselector=1,optselector=1;
	int page=0;
	Vector<String> nom=new Vector<String>();

	PokematosMenuListener(menuPokematos menu,MyGdxGame myGdxGame, MenuListener menuListener)
	{
		this.menu=menu;
		state=1;
		this.myGdxGame=myGdxGame;
		this.menuListener=menuListener;
		for(int i=0;i<20;i++)
			nom.add("Pokemon "+i);
	}

	@Override
	public boolean keyDown(int arg0) {

		if(menu==myGdxGame.getScreen()){
			super.keyDown(arg0);
			menu.update(state,pkselector,page,optselector);
			return true;
		}
		return false;
	}


	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		if(menu==myGdxGame.getScreen()){
			menu.setTouched(true);
			return true;
			}
		else
			return false;

	}


	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(menu==myGdxGame.getScreen()){
			menu.setTouched(false);
			Vector2 v=new Vector2(screenX,screenY);
			menu.getStage().screenToStageCoordinates(v);
			super.touchUp(v);
			menu.update(state,pkselector,page,optselector);
			return true;
}
		else
			return false;
	}

	

	@Override
	void handleA() {
		// TODO Auto-generated method stub
		if(state==3 && optselector==1){
			s = Gdx.audio.newSound(Gdx.files.internal("Sound/"+(page+pkselector)+".ogg"));
			s.play();
		}
		if(state==3 && optselector==2){
			state=4;
			menu.drawMap();

		}
		if(state==2)
		{
			state=3;
		}
		if(state==1)
			state=2;
	}

	@Override
	void handleB() {
		// TODO Auto-generated method stub
		if(state==1){
			myGdxGame.setScreen(menuListener.getScreen());
		}
		if(state<=3 && state>1)
			state--;
		if(state==4){
			for(Actor a:menu.getStage().getActors())
				a.remove();
			state--;
		}
	}

	@Override
	void handleLeft() {
		// TODO Auto-generated method stub
		if(state==1){
			menuListener.switchto(menuInventaire.class);

		}
	}

	@Override
	void handleRight() {
		// TODO Auto-generated method stub
		if(state==1){
			menuListener.switchto(menuOptions.class);

		}
	}

	@Override
	void handleUp() {
		// TODO Auto-generated method stub
		if(state==2){
			if(pkselector==1 && page>0){
				page-=6;pkselector=6;}
			else
				if(pkselector>1)
					pkselector--;
		}	
		if(state==3){
			if(optselector==2)
				optselector--;
		}
	}

	@Override
	void handleDown() {
		// TODO Auto-generated method stub
		System.out.println("Pokedex lenght: "+ Pokedex.values().length);
		if(state==2){
			if(pkselector<6 && page+pkselector<Pokedex.values().length)
				pkselector++;
			else
				if(pkselector==6 && page+7<Pokedex.values().length){
					page+=6;pkselector=1;}
		}
		if(state==3){
			if(optselector==1)
				optselector++;
		}
		if(state==1)
			menuListener.switchto(menuPokematosMap.class);

	}

	@Override
	void handleSelect() {
		// TODO Auto-generated method stub

	}

	@Override
	void handleStart() {
		// TODO Auto-generated method stub

	}

}
