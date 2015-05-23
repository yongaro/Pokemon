package pokemon.controle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public abstract class GameInput implements InputProcessor{

	GameInput() {
	}
	Vector2 v=new Vector2();
	abstract void handleA();
	abstract void handleB();
	abstract void handleLeft();
	abstract void handleRight();
	abstract void handleUp();
	abstract void handleDown();
	abstract void handleSelect();
	abstract void handleStart();
	
	public boolean keyDown(int key){
		
		if(Gdx.input.isKeyJustPressed(myInput.A.getID()))
			handleA();
		if(Gdx.input.isKeyJustPressed(myInput.B.getID()))
			handleB();
		if(Gdx.input.isKeyJustPressed(myInput.LEFT.getID()))
			handleLeft();
		if(Gdx.input.isKeyJustPressed(myInput.RIGHT.getID()))
			handleRight();
		if(Gdx.input.isKeyJustPressed(myInput.UP.getID()))
			handleUp();
		if(Gdx.input.isKeyJustPressed(myInput.DOWN.getID()))
			handleDown();
		if(Gdx.input.isKeyJustPressed(myInput.START.getID()))
			handleStart();
		if(Gdx.input.isKeyJustPressed(myInput.SELECT.getID()))
			handleSelect();
		return true;
		
	}
	
	public boolean touchUp(Vector2 v){
		System.out.println(v);
		if(v.x>35 && v.x<65 && v.y>10 && v.y<40)
			handleDown();
		if(v.x>35 && v.x<65 && v.y>65 && v.y<90)
			handleUp();
		if(v.x>10 && v.x<35 && v.y>35 && v.y<70)
			handleLeft();
		if(v.x>60 && v.x<90 && v.y>30 && v.y<70)
			handleRight();
		if(v.x>490 && v.x<560 && v.y>5 && v.y<75)
			handleB();
		if(v.x>565 && v.x<635 && v.y>40 && v.y<110)
			handleA();
		if(v.x>340 && v.x<400 && v.y>0 && v.y<25)
			handleStart();
		if(v.x>250 && v.x<310 && v.y>0 && v.y<25)
			handleSelect();
		return true;
		
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
