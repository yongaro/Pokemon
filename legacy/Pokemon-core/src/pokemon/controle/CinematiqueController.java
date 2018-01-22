package pokemon.controle;

import pokemon.launcher.MapScreen;

public class CinematiqueController extends GameInput {
	private boolean isSkipped;
	private MapScreen screen;
	
	public CinematiqueController(MapScreen screen) {
		setSkipped(false);
		this.screen= screen;
	}
	
	

	public boolean isSkipped() {
		return isSkipped;
	}

	public void setSkipped(boolean isSkipped) {
		this.isSkipped = isSkipped;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {			
			screen.setTouched(true);
			return super.touchUp(v);


	}



	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			screen.setTouched(false);
			v.set(screenX, screenY);
			screen.getStage().screenToStageCoordinates(v);
			System.out.println(v);
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
