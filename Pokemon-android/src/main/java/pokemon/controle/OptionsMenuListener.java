package pokemon.controle;

import com.badlogic.gdx.Gdx;

import pokemon.launcher.MyGdxGame;
import pokemon.vue.menuOptions;
import pokemon.vue.menuPokematos;

public class OptionsMenuListener extends GameInput {
	
	MenuListener menuListener;
	menuOptions menu;
	MyGdxGame myGdxGame;
	public OptionsMenuListener(MenuListener menuListener,menuOptions menu,MyGdxGame mygdxgame){
		this.menuListener=menuListener;
		this.menu=menu;
		this.myGdxGame=mygdxgame;
	}
	@Override
	void handleA() {
		// TODO Auto-generated method stub
		if(menu.getState()==1)
			MyGdxGame.Jtest.Sauvegarder();
		if(menu.getState()==2)
			Gdx.app.exit();
	}

	@Override
	void handleB() {
		// TODO Auto-generated method stub

	}

	@Override
	void handleRight() {
		// TODO Auto-generated method stub

	}

	@Override
	void handleLeft() {
		// TODO Auto-generated method stub
		menuListener.switchto(menuPokematos.class);
	}

	@Override
	void handleUp() {
		// TODO Auto-generated method stub
		if(menu.getState()==2)
			menu.setState(1);
	}

	@Override
	void handleDown() {
		// TODO Auto-generated method stub
		if(menu.getState()==1)
			menu.setState(2);
	}

	@Override
	void handleSelect() {
		// TODO Auto-generated method stub

	}

	@Override
	void handleStart() {
		// TODO Auto-generated method stub

	}
	
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		if(menu==myGdxGame.getScreen()){
			menu.setTouched(true);
			return true;
			}
		else
			return false;

	}

}
