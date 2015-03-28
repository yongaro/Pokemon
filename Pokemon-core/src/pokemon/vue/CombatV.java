package pokemon.vue;

import pokemon.launcher.MyGdxGame;
import pokemon.modele.Pkm;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class CombatV extends GameScreen implements InputProcessor{
	MyGdxGame mygdxgame;
	PokemonSprite e1=new PokemonSprite(PokemonSprite.e1,"Sprites/10.png");
	PokemonSprite e2=new PokemonSprite(PokemonSprite.e2,"Sprites/99.png");
	PokemonSprite a=new PokemonSprite(PokemonSprite.a1,"trainerS.png");
	Pkm[] pkms=MyGdxGame.Jtest.getTeam();
	PokemonSprite p1;
	DialogBox dbox;
	Sound s;
	int state=0;
	public CombatV(){
		
		Gdx.input.setInputProcessor(this);
		dbox=new DialogBox();
		dbox.setMessage("Un pokemon sauvage apparait");
	}
	
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render(float arg0) {
		Gdx.gl.glClearColor(0.5f, 0.5f, 5.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
		stage.act(arg0);
		stage.draw();
		
	}
	@Override
	public void resize(int arg0, int arg1) {
		super.resize(arg0, arg1);
		
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void show() {
		stage.addActor(e1);
		stage.addActor(e2);
		e1.addSlideAction();
		e2.addSlideAction();
		stage.addActor(a);
		a.addSlideAction();
		stage.addActor(dbox);
		
		
	}///////


	@Override
	public boolean keyDown(int arg0) {
		switch(arg0){
		case Keys.ENTER:
		{
			if(state==0 && stage.getActors().get(2).getActions().size==0){
				a.hideTrainer();
				dbox.setMessage("En avant "+pkms[0].getNom());
				p1=new PokemonSprite(new Vector2(20,60),"Sprites/back/"+pkms[0].getID()+".png");
				p1.popPokemon();
				stage.addActor(p1);
				}
			break;
		}
		}
		return false;//
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
