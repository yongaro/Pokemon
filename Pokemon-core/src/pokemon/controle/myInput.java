package pokemon.controle;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public enum myInput {
	LEFT,RIGHT,UP,DOWN,START,SELECT,A,B;

	int id;

	myInput() throws UnknownKeyException{
		XmlReader reader = new XmlReader();
		try {
			Element root = reader.parse(Gdx.files.internal("xml/KeyMapping.xml"));
			Element b=root.getChildByName(this.name());
			this.id=Keys.valueOf(b.getAttribute("button"));
			if(this.id==-1)
				throw new UnknownKeyException("Wrong keycode for : "+this.name());
			System.out.println(this.name()+" : "+this.id);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	myInput(int id){
		System.out.println(this.name());
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

		return A;
	}

	public int getID() {
		return id;
	}


}
