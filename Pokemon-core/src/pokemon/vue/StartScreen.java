package pokemon.vue;

import pokemon.controle.MenuListener;
import pokemon.launcher.MapScreen;
import pokemon.launcher.MyGdxGame;
import pokemon.modele.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

public class StartScreen extends GameScreen implements InputProcessor{
	MyGdxGame game;
	SpriteActor s=new SpriteStartMenu();
	SpriteActor charm=new SpriteActor(Gdx.files.internal("charmander.png"), 0, 0);

	
	public StartScreen(MyGdxGame game){
		this.game=game;
	}

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
		Gdx.gl.glClearColor(1f, 1f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT| GL20.GL_DEPTH_BUFFER_BIT);
		stage.act();
		stage.draw();
		stage.getBatch().begin();
		f.setColor(Color.BLACK);
		f.draw(stage.getBatch(), "Nouvelle Partie", width/2-f.getBounds("Nouvelle Partie").width/2, height/2f);
		f.draw(stage.getBatch(), "Charger Partie", width/2-f.getBounds("Charger Partie").width/2, height/2.5f);

		stage.getBatch().end();
		if(Gdx.input.isKeyJustPressed(Keys.ENTER) || Gdx.input.isKeyJustPressed(Keys.VOLUME_UP)){
			MyGdxGame.Jtest.setCurrentMap(new Map("maps/bigmap.tmx", MyGdxGame.npcList));
			MapScreen mapS=new MapScreen(game);
			MenuListener menu=new MenuListener(game,mapS);
		}

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		charm.setBounds(0, -190, charm.getWidth()/5, charm.getHeight()/5);
	
		stage.addActor(s);
		stage.addActor(charm);
	}

	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		return false;
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
