package pokemon.controle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

public class CinematiqueController implements InputProcessor {
	private boolean isSkipped;
	
	public CinematiqueController() {
		setSkipped(false);
	}
	
	@Override
	public boolean keyDown(int arg0) {
		return false;
	}

	public boolean keyTyped(char arg0) {
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
			setSkipped(true);
		}
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		return false;
	}

	public boolean isSkipped() {
		return isSkipped;
	}

	public void setSkipped(boolean isSkipped) {
		this.isSkipped = isSkipped;
	}

}
