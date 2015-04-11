package pokemon.controle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public enum myInput {
	LEFT(Keys.DPAD_LEFT),RIGHT(Keys.DPAD_RIGHT),UP(Keys.DPAD_UP),DOWN(Keys.DPAD_DOWN),START(Keys.SPACE),SELECT(Keys.ENTER),A(Keys.ENTER),B(Keys.BACKSPACE),NOT_HANDLED(Keys
			.A);

	int id;

	myInput(int id){
		this.id=id;
	}

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

	public int getID() {
		return id;
	}


}
