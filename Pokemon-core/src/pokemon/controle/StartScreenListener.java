package pokemon.controle;

import java.io.IOException;

import pokemon.launcher.MapScreen;
import pokemon.launcher.MyGdxGame;
import pokemon.modele.Map;
import pokemon.vue.StartScreen;

public class StartScreenListener extends GameInput{

	StartScreen screen;
	MyGdxGame game;
	
	public StartScreenListener(StartScreen startScreen, MyGdxGame game) {
		screen=startScreen;
		this.game=game;
	}

	@Override
	void handleA() {
		if(screen.getSelector()==0){
			MyGdxGame.Jtest.setCurrentMap(new Map("maps/bigmap.tmx", MyGdxGame.npcList));
			MapScreen mapS=new MapScreen(game);
		//	MenuListener menu=new MenuListener(game,mapS);
			game.setScreen(mapS);
			screen.dispose();

		}
		if(screen.getSelector()==1){
			try{
			MyGdxGame.Jtest.Charger();
			MyGdxGame.Jtest.setCurrentMap(new Map("maps/bigmap.tmx", MyGdxGame.npcList));
			MapScreen mapS=new MapScreen(game);
		//	MenuListener menu=new MenuListener(game,mapS);
			game.setScreen(mapS);
			}
			catch(IOException e){
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

}
