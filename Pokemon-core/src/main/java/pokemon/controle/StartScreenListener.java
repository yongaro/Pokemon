package pokemon.controle;

import java.io.IOException;

import com.badlogic.gdx.math.Vector2;

import pokemon.launcher.MapScreen;
import pokemon.launcher.PokemonCore;
import pokemon.modele.Map;
import pokemon.vue.StartScreen;

public class StartScreenListener extends GameInput{

	StartScreen screen;
	PokemonCore game;
	
	public StartScreenListener(StartScreen startScreen, PokemonCore game) {
		screen=startScreen;
		this.game=game;
	}

	@Override
	void handleA() {
		if(screen.getSelector()==0){
			PokemonCore.Jtest.setCurrentMap(new Map("maps/bigmap.tmx", PokemonCore.npcList));
			MapScreen mapS=new MapScreen(game);
		//	screenListener screen=new screenListener(game,mapS);
			game.setScreen(mapS);
			screen.dispose();

		}
		if(screen.getSelector()==1){
			try{
			PokemonCore.Jtest.Charger();
			PokemonCore.Jtest.setCurrentMap(new Map("maps/bigmap.tmx", PokemonCore.npcList));
			MapScreen mapS=new MapScreen(game);
		//	screenListener screen=new screenListener(game,mapS);
			game.setScreen(mapS);
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
	}

	@Override
	void handleB() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void handleLeft() {
		// TODO Auto-generated method stub
		if(screen.getSelector()==1)
			screen.setSelector(0);
	}

	@Override
	void handleRight() {
		// TODO Auto-generated method stub
		if(screen.getSelector()==0)
			screen.setSelector(1);
	}

	@Override
	void handleUp() {
		
		
	}

	@Override
	void handleDown() {

		
		
	}

	@Override
	void handleSelect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void handleStart() {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		
			screen.setTouched(true);
			return true;

	}


	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			screen.setTouched(false);
			Vector2 v=new Vector2(screenX,screenY);
			screen.getStage().screenToStageCoordinates(v);
			return super.touchUp(v);


	}
}