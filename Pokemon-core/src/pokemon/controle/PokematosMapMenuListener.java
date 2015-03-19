package pokemon.controle;

import pokemon.launcher.MyGdxGame;
import pokemon.launcher.TestRender;
import pokemon.modele.Joueur;
import pokemon.modele.Minimap;
import pokemon.vue.menuPokematosMap;
import pokemon.modele.Map;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class PokematosMapMenuListener implements InputProcessor{

	MyGdxGame myGdxGame;
	MenuListener menuListener;
	menuPokematosMap menu;
	Minimap minimap=MyGdxGame.m;
	int actualcity=1;
	Joueur joueur= MyGdxGame.Jtest;

	PokematosMapMenuListener(menuPokematosMap menu,MyGdxGame myGdxGame, MenuListener menuListener)
	{
		this.menu=menu;
		this.myGdxGame=myGdxGame;
		this.menuListener=menuListener;

	}
	
	public boolean keyDown(int arg0) {
		if(myGdxGame.getScreen()==menu)
		{
			System.out.println("Input detected");
		switch(arg0){
		case Keys.DOWN:
			if(minimap.getCities().get(actualcity).hasNeighbourAt("South"))
				actualcity=minimap.getCities().indexOf(minimap.getCities().get(actualcity).getNeighbour("South"));
		menu.update(actualcity);
		case Keys.ENTER:
			joueur.setCurrentMap(new Map("maps/test.tmx"));
			myGdxGame.setScreen(new TestRender());
			return true;
		}
		}
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

}
