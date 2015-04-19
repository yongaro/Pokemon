package pokemon.controle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public abstract class GameInput implements InputProcessor{

	GameInput() {
	}
	
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

		if(v.x>35 && v.x<65 && v.y>10 && v.y<40)
			handleDown();
		if(v.x>35 && v.x<65 && v.y>65 && v.y<90)
			handleUp();
		if(v.x>10 && v.x<35 && v.y>35 && v.y<70)
			{handleLeft();System.out.print("LEFT");}
		if(v.x>60 && v.x<90 && v.y>30 && v.y<70)
			handleRight();
		if(v.x>460 && v.x<540 && v.y>10 && v.y<80)
			handleB();
		if(v.x>550 && v.x<630 && v.y>10 && v.y<80)
			handleA();
		return true;
		
	}
}
