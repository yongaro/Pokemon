package pokemon.controle;

import java.util.Vector;

import pokemon.annotations.Tps;
import pokemon.launcher.MapScreen;
import pokemon.launcher.MyGdxGame;
import pokemon.modele.Direction;
import pokemon.modele.Joueur;
import pokemon.vue.JoueurVue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

/* La classe JoueurController gere les inputs sur la map*/

@Tps(nbhours=2)
public class JoueurController implements InputProcessor{
	private MyGdxGame game;
	private Vector<Direction> input=new Vector<Direction>();
	private JoueurVue jv;
	private Joueur j;
	private MapScreen screen;
	
	//Constructeur
	public JoueurController(MapScreen screen, JoueurVue jv, MyGdxGame game) {
		this.jv = jv;
		this.j = jv.getJoueur();
		this.screen = screen;
		this.game = game;
	}
	
	//Pour Desktop
	@Override
	public boolean keyDown(int arg0) {
		if(j.canMove()) {		
			if(Gdx.input.isKeyPressed(Keys.RIGHT))
			{
				if(input.size()<2 && !input.contains(Direction.East)) {
					input.add(0, Direction.East);
					j.move(input.firstElement());
				}
				
			}
			if(Gdx.input.isKeyPressed(Keys.LEFT) ){
				
				if(input.size()<2 && !input.contains(Direction.West)) {
					input.add(0, Direction.West);
					j.move(input.firstElement());
				}
			}
			if(Gdx.input.isKeyPressed(Keys.DOWN)){
				
				if(input.size()<2 && !input.contains(Direction.South)) {
					input.add(0, Direction.South);
					j.move(input.firstElement());
				}
				
			}
			if(Gdx.input.isKeyPressed(Keys.UP))
			{
				if(input.size()<2) {
					input.add(0, Direction.North);
					j.move(input.firstElement());				
				}
			}
			if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
				j.setSpeedX(0);
				j.setSpeedY(0);
				new MenuListener(game);
			}
		}
		if(Gdx.input.isKeyPressed(Keys.ENTER)) {
			screen.updateCutscene(j);
		}
		return false;
	}
	@Override
	public boolean keyUp(int arg0) {
		switch(arg0)
		{
		case Keys.RIGHT:
			input.remove(Direction.East);
			break;
		case Keys.LEFT:
			input.remove(Direction.West);
			break;
		case Keys.DPAD_UP:
			input.remove(Direction.North);
			break;
		case Keys.DPAD_DOWN:
			input.remove(Direction.South);
			break;

		}
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


	//Pour Android
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
	
	//A voir
	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		return false;
	}	
	@Override
	public boolean scrolled(int arg0) {
		return false;
	}

}
