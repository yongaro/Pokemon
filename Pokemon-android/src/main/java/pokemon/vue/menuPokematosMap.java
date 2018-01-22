package pokemon.vue;

import pokemon.launcher.MyGdxGame;
import pokemon.modele.Minimap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class menuPokematosMap extends GameScreen{

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
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.setProjectionMatrix(stage.getViewport().getCamera().combined);
		
		shapeRenderer.begin(ShapeType.Filled);
		/*Drawing top tabs*/
		shapeRenderer.setColor(0.58f, 0.59f, 0.57f, 1);

		shapeRenderer.rect(0, height-30, 154, 30);	       
	
		shapeRenderer.rect(162,height-30, 154, 30);
		shapeRenderer.setColor(0.85f, 0.85f, 0.85f, 1);
		shapeRenderer.rect(324, height-30, 154, 30);

		shapeRenderer.setColor(0.58f, 0.59f, 0.57f, 1);

		shapeRenderer.rect(486, height-30, 154, 30);
		/*Drawing backGround*/
		shapeRenderer.setColor(0.85f, 0.85f, 0.85f, 1);
		shapeRenderer.rect(30,0,width, height-30);
		shapeRenderer.setColor(0.58f, 0.59f, 0.57f, 1);
		shapeRenderer.rect(0, 165, 30, 165);
		shapeRenderer.setColor(0.85f, 0.85f, 0.85f, 1);
		shapeRenderer.rect(0,0, 30, 165);
		
		shapeRenderer.end();
		stage.getBatch().begin();
		f.setScale(1.2f);
	
		f.setColor(1, 1, 1, 1);
		f.drawMultiLine(stage.getBatch(),"P\nO\nK\nE\nD\nE\nX", 5, 327);
		f.setColor(0.58f, 0.59f, 0.57f, 1);	

		f.drawMultiLine(stage.getBatch(),"C\nA\nR\nT\nE", 5, 140);
		f.setColor(1, 1, 1, 1);
		f.draw(stage.getBatch(),"Pokemons",18, height-3);
		f.draw(stage.getBatch(),"Options",488+30, height-3);
		f.draw(stage.getBatch(),"Inventaire",160+13, height-3);
		//f.setScale(1.5f);
		//f.draw(stage.getBatch(),"Pokedex",75, 315);			
		//f.draw(stage.getBatch(),"Pokedex",80, 315);
		
		f.setColor(0.58f, 0.59f, 0.57f, 1);	
		f.draw(stage.getBatch(),"PokeMatos",324+13, height-3);
		stage.getBatch().draw(map,340-map.getWidth()/2,165-map.getHeight()/2,map.getWidth(),map.getHeight());
		stage.getBatch().end();
		stage.act(delta);
		stage.draw();
		super.drawUI(delta);

	}

	@Override
	public void resize(int arg0, int arg1) {
    	stage.getViewport().update(arg0, arg1, true);
        stage.getBatch().getProjectionMatrix().setToOrtho2D(0, 0, width, height);
		Gdx.graphics.requestRendering();
		
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
