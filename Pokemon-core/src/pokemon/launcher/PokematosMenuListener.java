package pokemon.launcher;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PokematosMenuListener implements   InputProcessor{
	menuPokematos menu;
	MyGdxGame myGdxGame;
	MenuListener menuListener;
	int state;
	int tab=0;
	
	PokematosMenuListener(menuPokematos menu,MyGdxGame myGdxGame, MenuListener menuListener)
	{
		this.menu=menu;
		state=1;
		this.myGdxGame=myGdxGame;
		this.menuListener=menuListener;
	}

	@Override
	public boolean keyDown(int arg0) {

		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {

		if(menu==myGdxGame.getScreen()){
			if(Gdx.input.isKeyJustPressed(Keys.DPAD_DOWN)){
				if(state==1)
				{tab++;
				}
				if(state==2){
					menu.acteur.move=Move.down;
					System.out.print("set");
				}
			}
			if(Gdx.input.isKeyJustPressed(Keys.DPAD_UP)){
				if(state==1)
					tab--;
				if(state==2){
					menu.acteur.move=Move.up;
					//System.out.print("set");
				}
			}
			if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
				if(tab==1){
					state++;System.out.print("State="+state);}
				Gdx.graphics.setContinuousRendering(true);
				System.out.print("set");;System.out.print("State="+state+" Tab="+tab);
			}
			if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)){
				if(state==2){
					menu.acteur.move=Move.right;
					//System.out.print("set");
				}
			}
			if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)){
				if(state==2){
					menu.acteur.move=Move.left;
					//System.out.print("set");
				}
			}
			menu.update(state,tab);
			return true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		if(myGdxGame.getScreen()==menu)
		{
			//menu.acteur.move=Move.wait;
			switch(arg0)
			{
			case Keys.DPAD_DOWN:
				if(state==2){
					menu.acteur.move=Move.wait;
					//System.out.print("set");
				}
				break;
			case Keys.DPAD_UP:
				if(state==2){
					menu.acteur.move=Move.wait;
					//System.out.print("set");
					}
			break;
			case Keys.DPAD_RIGHT:
					if(state==2){
					menu.acteur.move=Move.wait;
					//System.out.print("set");
					}
			break;
			case Keys.DPAD_LEFT:
						if(state==2){
						menu.acteur.move=Move.wait;
						//System.out.print("set");
						}
			break;
			}
			return true;
		}
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
