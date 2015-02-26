package pokemon.launcher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public enum myInput {
	LEFT,RIGHT,UP,DOWN,START,SELECT,A,B,NOT_HANDLED;
	
	
	public static myInput detectInput()
	{
		
		if(Gdx.input.isKeyJustPressed(Keys.DPAD_DOWN) )
			return myInput.DOWN;
		if(Gdx.input.isKeyJustPressed(Keys.DPAD_UP) )
			return myInput.UP;
		if(Gdx.input.isKeyJustPressed(Keys.DPAD_LEFT) )
			return myInput.LEFT;
		if(Gdx.input.isKeyJustPressed(Keys.DPAD_RIGHT) )
			return myInput.RIGHT;
		if(Gdx.input.isKeyJustPressed(Keys.CONTROL_RIGHT) )
			return myInput.START;
		if(Gdx.input.isKeyJustPressed(Keys.SPACE) )
			return myInput.SELECT;
		if(Gdx.input.isKeyJustPressed(Keys.ENTER) )
			return myInput.A;
		if(Gdx.input.isKeyJustPressed(Keys.BACKSPACE) )
			return myInput.B;
		
		return myInput.NOT_HANDLED;
	}
	

}
