package pokemon.controle;

import java.util.Vector;

import pokemon.annotations.Tps;
import pokemon.launcher.MapScreen;
import pokemon.modele.Direction;
import pokemon.modele.Joueur;
import pokemon.vue.JoueurVue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

/* La classe JoueurController gere les inputs sur la map*/

@Tps(nbhours=2)
public class JoueurController extends GameInput{
	private Vector<Direction> input=new Vector<Direction>();
	private JoueurVue jv;
	private Joueur j;
	private MapScreen screen;
	boolean freeze;
	//Constructeur
	public JoueurController(MapScreen screen, JoueurVue jv) {
		this.jv = jv;
		this.j = jv.getJoueur();
		this.screen = screen;
	}

	//Pour Desktop
	@Override
	public boolean keyDown(int key) {
		if(j.canMove()) {		
			super.keyDown(key);
		}


		return false;
	}
	@Override

	public boolean keyUp(int arg0) {

		if(arg0==myInput.LEFT.getID())
			input.remove(Direction.West);
		if(arg0==myInput.RIGHT.getID())
			input.remove(Direction.East);
		if(arg0==myInput.UP.getID())
			input.remove(Direction.North);
		if(arg0==myInput.DOWN.getID())
			input.remove(Direction.South);


		if(input.size()!=0){
			j.move(input.firstElement());
			jv.setAnimation(input.firstElement());
		}
		else 
			j.move(Direction.Standing);
		return false;
	}
	@Override
	public boolean keyTyped(char arg0) {
		return false;
	}


	//controles
	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		return false;
	}
	@Override
	public boolean touchUp(int x, int y, int arg2, int arg3) {
		screen.setTouched(false);
		//v.set(x,y);
		//screen.getStage().screenToStageCoordinates(v);
		if(v.x>35 && v.x<65 && v.y>10 && v.y<40)
			input.remove(Direction.South);
		if(v.x>35 && v.x<65 && v.y>65 && v.y<90)
			input.remove(Direction.North);
		if(v.x>10 && v.x<35 && v.y>35 && v.y<70)
			input.remove(Direction.West);
		if(v.x>60 && v.x<90 && v.y>30 && v.y<70)
			input.remove(Direction.East);
		/*	if(x>460 && x<540 && y>10 && y<80)
			handleB();
		if(x>550 && x<630 && y>10 && y<80)
			handleA();*/
		if(input.size()!=0){
			j.move(input.firstElement());
			jv.setAnimation(input.firstElement());
		}
		else 
			j.move(Direction.Standing);
		return false;
	}
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		screen.setTouched(true);
		v.set(x, y);
		screen.getStage().screenToStageCoordinates(v);
		if(!freeze)
			super.touchUp(v);
		return false;
	}

	//A voir
	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		return false;
	}	
	@Override
	public boolean scrolled(int arg0) {
		return false;
	}

	public void freeze(){
		freeze=true;
	}
	public void unfreeze(){
		freeze=false;
	}
	@Override
	void handleA() {
		screen.updateCutscene(j);
	}

	@Override
	void handleB() {
		// TODO Auto-generated method stub

	}

	@Override
	void handleLeft() {
		// TODO Auto-generated method stub
		if(input.size()<2 && !input.contains(Direction.West) && !freeze) {
			input.add(0, Direction.West);
			j.move(input.firstElement());
		}
	}

	@Override
	void handleRight() {
		// TODO Auto-generated method stub
		if(input.size()<2 && !input.contains(Direction.East) && !freeze) {
			input.add(0, Direction.East);
			j.move(input.firstElement());
		}
	}

	@Override
	void handleUp() {
		// TODO Auto-generated method stub
		if(input.size()<2 && !freeze && !freeze) {
			input.add(0, Direction.North);
			j.move(input.firstElement());				
		}
	}

	@Override
	void handleDown() {
		// TODO Auto-generated method stub
		if(input.size()<2 && !input.contains(Direction.South) && !freeze) {
			input.add(0, Direction.South);
			j.move(input.firstElement());
		}
	}

	@Override
	void handleSelect() {
		// TODO Auto-generated method stub

	}

	@Override
	void handleStart() {
		j.setSpeedX(0);
		j.setSpeedY(0);
		screen.popMenu();
	}



}
