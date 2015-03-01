package pokemon.launcher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class menuPokematos implements Screen{


	int width=640;//Gdx.graphics.getWidth();
	int height=360;//Gdx.graphics.getHeight();
	public Stage stage = new Stage(new FitViewport(width,height));
	MyGdxGame mygdxgame;
	int state;
	int pkselector;
	int tab=0;
	ShapeRenderer shapeRenderer;
	BitmapFont f=new BitmapFont(Gdx.files.internal("pkm1.fnt"), Gdx.files.internal("pkm1.png"), false);
	Texture t;
	MyActor acteur;
	boolean moveleft=false;
	public menuPokematos(MyGdxGame myGdxGame) {
		
		super();
		shapeRenderer=new ShapeRenderer();
		pkselector=1;
		this.mygdxgame = myGdxGame;
		this.state = 1;
	}

	
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	public void pause() {
		// TODO Auto-generated method stub
		
	}

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
		shapeRenderer.end();
		if(tab==0)
			drawPokedex();
		else
			drawMap();

		//System.out.print(tab);

		stage.getBatch().begin();
		f.setScale(1.2f);
		f.setColor(0.58f, 0.59f, 0.57f, 1);	
		f.draw(stage.getBatch(),"PokeMatos",324+13, height-5);

		f.setColor(1, 1, 1, 1);
		f.draw(stage.getBatch(),"Pokemons",18, height-5);
		

		f.draw(stage.getBatch(),"Inventaire",160+13, height-5);
		stage.getBatch().end();
		if(acteur.isVisible()){
		stage.draw();
		stage.act(delta);
		}
		
	}


	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		stage.getBatch().getProjectionMatrix().setToOrtho2D(0, 0, width, height);

	}


	public void resume() {
		// TODO Auto-generated method stub
		
	}


	public void show() {
		acteur= new MyActor();
		acteur.setVisible(false);
		
		t=new Texture(Gdx.files.internal("testminimap.png"));
	
	}
	
	public void drawPokedex()
	{
		shapeRenderer.begin(ShapeType.Filled);
		
		shapeRenderer.setColor(0.85f, 0.85f, 0.85f, 1);
		shapeRenderer.rect(0, 165, 30, 165);
		shapeRenderer.setColor(0.58f, 0.59f, 0.57f, 1);
		shapeRenderer.rect(0,0, 30, 165);
		
		/*Drawing left menu*/
		shapeRenderer.setColor(1, 1, 1, 1);
		shapeRenderer.rect(45,10, 185, 270);
		shapeRenderer.setColor(0.58f, 0.59f, 0.57f, 1);
		shapeRenderer.rect(45,280, 185, 41);

		/*Drawing right bottom menu*/
		shapeRenderer.rect(240,10, 175, 110);
		shapeRenderer.setColor(1, 1, 1, 1);
		shapeRenderer.rect(415,10, 240, 110);
		/*object banner*/
		shapeRenderer.rect(240,125, 150,60); 
		shapeRenderer.setColor(0.58f, 0.59f, 0.57f, 1);
		/*capacity definition banner*/
		shapeRenderer.rect(400,125, 265, 60);
		/*capacity banner*/
		shapeRenderer.rect(240,185, 415, 20);
		shapeRenderer.setColor(1, 1, 1, 1);
		shapeRenderer.rect(240,205, 415, 32);
		/*top banner*/
		shapeRenderer.setColor(0.58f, 0.59f, 0.57f, 1);
		shapeRenderer.rect(240,237, 415, 84);
		shapeRenderer.setColor(0.58f, 0.59f, 0.57f, 1);
		shapeRenderer.triangle(240+207-10, 237, 240+207+10, 237, 240+207, 227); //top triangle
		shapeRenderer.triangle(240+207-10, 205, 240+207+10, 205, 240+207, 215); //bottom triangle
		/*Drawing pokemon selectors*/
		shapeRenderer.setColor(1, 0, 0, 1);
		shapeRenderer.rect(45,280-(45*this.pkselector), 5, 45);
		/*shapeRenderer.setColor(0, 0, 1, 1);
		shapeRenderer.rect(210,120-(27.5f*this.atkselector), 5, 27);*/
		shapeRenderer.end();
		}
	
	public void drawMap(){
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(0.58f, 0.59f, 0.57f, 1);
		shapeRenderer.rect(0, 165, 30, 165);
		shapeRenderer.setColor(0.85f, 0.85f, 0.85f, 1);
		shapeRenderer.rect(0,0, 30, 165);
		shapeRenderer.end();

		stage.getBatch().begin();
		stage.getBatch().draw(t,150,30,216*1.5f,168*1.5f);
	
		stage.getBatch().end();
		if(!acteur.isVisible()){
			acteur.setVisible(true);
			stage.addActor(acteur);
		}
		
	}
		
	

	public void update(int state, int tab) {
		this.state=state;
		this.tab=tab;
		
	}

}
