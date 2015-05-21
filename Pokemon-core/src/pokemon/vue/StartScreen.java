package pokemon.vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class StartScreen extends GameScreen{

	SpriteActor s=new SpriteStartMenu();
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
		Gdx.gl.glClearColor(0.44f, 0.44f, 0.44f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT| GL20.GL_DEPTH_BUFFER_BIT);
		stage.getBatch().begin();
		f.draw(stage.getBatch(), "Nouvelle Partie", width/2-f.getBounds("Nouvelle Partie").width/2, height/2f);
		f.draw(stage.getBatch(), "Charger Partie", width/2-f.getBounds("Charger Partie").width/2, height/2.5f);

		stage.getBatch().end();

		stage.act();
		stage.draw();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		//s.setBounds(0, 0,width/2,height/2);
		stage.addActor(s);
		
	}

}
