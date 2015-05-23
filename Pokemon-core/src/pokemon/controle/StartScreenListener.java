package pokemon.controle;

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
		}
		if(screen.getSelector()==1){
			MyGdxGame.Jtest.Charger();
			MyGdxGame.Jtest.setCurrentMap(new Map("maps/bigmap.tmx", MyGdxGame.npcList));
			MapScreen mapS=new MapScreen(game);
		//	MenuListener menu=new MenuListener(game,mapS);
			game.setScreen(mapS);
		}
	}

	@Override
	void handleB() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void handleLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void handleRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void handleUp() {
		if(screen.getSelector()==1)
			screen.setSelector(0);
		
	}

	@Override
	void handleDown() {
		if(screen.getSelector()==0)
			screen.setSelector(1);
		
		
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
