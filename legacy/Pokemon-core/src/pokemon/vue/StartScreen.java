package pokemon.vue;

import pokemon.controle.MenuListener;
import pokemon.controle.StartScreenListener;
import pokemon.launcher.MapScreen;
import pokemon.launcher.MyGdxGame;
import pokemon.modele.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class StartScreen extends GameScreen{
	MyGdxGame game;
	SpriteActor s=new SpriteStartMenu();
	SpriteActor charm=new SpriteActor(Gdx.files.internal("startbackg.png"));
	StartScreenListener listener;
	int selector;
	


	public StartScreen(MyGdxGame game){
		this.game=game;
		listener=new StartScreenListener(this,game);
		selector=0;
	}

	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
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
	public void render(float delta) {
		Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT| GL20.GL_DEPTH_BUFFER_BIT);
		stage.act();
		stage.draw();
		/*shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.circle(60,60,50);
		shapeRenderer.end();*/
		stage.getBatch().begin();
		f.setColor(0.58f, 0.59f, 0.57f, 1);
		if(selector==0)
			f.setColor(0f, 0f, 0f, 1);
		f.draw(stage.getBatch(), "Nouvelle Partie",20, 50);
		if(selector==1)
			f.setColor(0f, 0f, 0f, 1);
		else
			f.setColor(0.58f, 0.59f, 0.57f, 1);
		f.draw(stage.getBatch(), "Charger Partie", 470,50);
		stage.getBatch().end();
		super.drawUI(delta);

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(listener);
		stage.addActor(charm);
		stage.addActor(s);

	}


	public int getSelector() {
		return selector;
	}

	public void setSelector(int state) {
		this.selector = state;
	}
}
	
	