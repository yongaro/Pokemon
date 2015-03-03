package pokemon.launcher;
import pokemon.modele.Minimap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class menuPokematosMap implements Screen{
	int width=640;//Gdx.graphics.getWidth();
	int height=360;//Gdx.graphics.getHeight();
	public Stage stage = new Stage(new FitViewport(width,height));
	MyGdxGame mygdxgame;
	Texture map;
	MyActor a;
	Minimap minimap=MyGdxGame.m;
	int actualcity=1;

public menuPokematosMap(MyGdxGame myGdxGame) {
		
		
			this.mygdxgame = myGdxGame;
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
		stage.getBatch().begin();
		stage.getBatch().draw(map,340-map.getWidth()/2,165-map.getHeight()/2,map.getWidth(),map.getHeight());
		stage.getBatch().end();
		stage.act(arg0);
		stage.draw();
	}

	@Override
	public void resize(int arg0, int arg1) {
		stage.getBatch().getProjectionMatrix().setToOrtho2D(0, 0, width,height);

		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
	public void update(int actualcity){
		this.actualcity=actualcity;
		a.setPosition(minimap.getCities().get(actualcity).getX(), minimap.getCities().get(actualcity).getY());
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		map = new Texture(Gdx.files.internal("testminimap.png"));
		a=new MyActor(340-map.getWidth()/2,165-map.getHeight()/2);
		stage.addActor(a);
		a.setPosition(minimap.getCities().get(actualcity).getX(), minimap.getCities().get(actualcity).getY());
	}

}
