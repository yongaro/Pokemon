package pokemon.controle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

public class CinematiqueController extends GameInput {
	private boolean isSkipped;
	
	public CinematiqueController() {
		setSkipped(false);
	}
	
	

	public boolean isSkipped() {
		return isSkipped;
	}

	public void setSkipped(boolean isSkipped) {
		this.isSkipped = isSkipped;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {			
			.setTouched(true);
			return true;

	}



	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(menu==myGdxGame.getScreen()){
			menu.setTouched(false);
			v.set(screenX, screenY);
			menu.getStage().screenToStageCoordinates(v);
			System.out.println(v);
			return super.touchUp(v);}
		else
			return false;
	}


	@Override
	void handleA() {
		// TODO Auto-generated method stub
		setSkipped(true);
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
		// TODO Auto-generated method stub
		
	}



	@Override
	void handleDown() {
		// TODO Auto-generated method stub
		
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
