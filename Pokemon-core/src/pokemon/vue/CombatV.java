package pokemon.vue;

import pokemon.launcher.MyGdxGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class CombatV implements Screen{
	int width=640;//Gdx.graphics.getWidth();
	int height=360;//Gdx.graphics.getHeight();
	public Stage stage = new Stage(new FitViewport(width,height));
	MyGdxGame mygdxgame;
	
	public CombatV(){
		
		
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
	 	stage.getViewport().update(arg0, arg1, true);
        stage.getBatch().getProjectionMatrix().setToOrtho2D(0, 0, width, height);
		
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void show() {
		stage.addActor(new PokemonSprite(PokemonSprite.e1,"Sprites/80.png"));
		stage.addActor(new PokemonSprite(PokemonSprite.e2,"Sprites/99.png"));
		stage.addActor(new PokemonSprite(PokemonSprite.a1,"Sprites/pikachu-f.png"));
		
	}//////

}
