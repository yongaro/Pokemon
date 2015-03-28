package pokemon.vue;

import java.util.Vector;

import pokemon.launcher.MyGdxGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class menuPokematos extends  GameScreen{




	MyGdxGame mygdxgame;
	int state;
	int pkselector,optselector;
	int page;
	int offset;
	Texture spritepokemon,map;
	Vector<String> nom=new Vector<String>();

	public menuPokematos(MyGdxGame myGdxGame) {
		
		super();
		shapeRenderer=new ShapeRenderer();
		pkselector=optselector=1;
		page=0;
		this.mygdxgame = myGdxGame;
		this.state = 1;
		for(int i=0;i<20;i++)
			nom.add("Pokemon "+i);
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
		shapeRenderer.setColor(1, 1, 1, 1);
		shapeRenderer.rect(240,10, 175, 110);
		//shapeRenderer.setColor(1, 1, 1, 1);
		//shapeRenderer.rect(415,10, 240, 110);
		/*object banner*/
		//shapeRenderer.rect(240,125, 150,60); 
		shapeRenderer.setColor(0.58f, 0.59f, 0.57f, 1);
		/*capacity definition banner*/
		//shapeRenderer.rect(400,125, 265, 60);
		/*capacity banner*/
		shapeRenderer.rect(240,125, 385, 80);
		shapeRenderer.setColor(1, 1, 1, 1);
		shapeRenderer.rect(240,205, 385, 32);
		/*top banner*/
		shapeRenderer.setColor(0.58f, 0.59f, 0.57f, 1);
		shapeRenderer.rect(240,237, 385, 84);
		shapeRenderer.setColor(0.58f, 0.59f, 0.57f, 1);
		shapeRenderer.triangle(407+10, 237, 427+10, 237, 417+10, 227); //top triangle
		shapeRenderer.triangle(407+10, 205, 427+10, 205,  417+10, 215); //bottom triangle
		/*Drawing pokemon selectors*/
		shapeRenderer.setColor(1, 0, 0, 1);
		shapeRenderer.rect(45,280-(45*this.pkselector), 5, 45);
		shapeRenderer.setColor(0, 0, 1, 1);
		shapeRenderer.rect(240,120-(55f*this.optselector), 5, 55);
		/*shapeRenderer.setColor(0, 0, 1, 1);
		shapeRenderer.rect(210,120-(27.5f*this.atkselector), 5, 27);*/
		shapeRenderer.end();



		//System.out.print(tab);

		stage.getBatch().begin();
	
		f.setScale(1.2f);
		f.setColor(0.58f, 0.59f, 0.57f, 1);	
		f.draw(stage.getBatch(),"PokeMatos",324+13, height-5);
		f.drawMultiLine(stage.getBatch(),"P\nO\nK\nE\nD\nE\nX", 5, 327);
		f.setColor(1, 1, 1, 1);
		f.drawMultiLine(stage.getBatch(),"C\nA\nR\nT\nE", 5, 140);
		f.setColor(1, 1, 1, 1);
		f.draw(stage.getBatch(),"Pokemons",18, height-5);

		f.draw(stage.getBatch(),"Inventaire",160+13, height-5);
		f.setScale(1.5f);
		f.draw(stage.getBatch(),"Pokedex",75, 315);			
		//f.draw(stage.getBatch(),"Pokedex",80, 315);
		
		f.setColor(0.58f, 0.59f, 0.57f, 1);	
		f.setScale(0.9f);
		offset=0;
		for(int i=page;i<Math.min(page+6,nom.size());i++) //liste des pokemons
		{
			f.draw(stage.getBatch(),nom.get(i),65,268-offset);
			offset+=46;
		}
		f.setScale(1.2f);
		f.draw(stage.getBatch(),"Cri",260,110);
		f.draw(stage.getBatch(),"Location",260,55);
		//offset+=27.5;
		f.setColor(1, 1, 1, 1);

		f.setScale(1.2f);
		f.draw(stage.getBatch(),nom.get(pkselector-1),340, 315);
		f.setScale(0.9f);

		f.drawWrapped(stage.getBatch(), "Ses ailes peuvent le faire voler � plus de 1400 m d'altitude. Ce Pokemon crache du feu a des temperatures tres �levees. ",245, 200, 380);

		
		stage.getBatch().draw(spritepokemon,247,(410-25)*0.6f,(int)(spritepokemon.getWidth())*0.95f,(int)(spritepokemon.getHeight()*0.95f));
		if(state==4)
		stage.getBatch().draw(map,340-map.getWidth()/2,165-map.getHeight()/2,map.getWidth(),map.getHeight());
		stage.getBatch().end();
		
		stage.draw();
		stage.act(delta);
		}
		



	public void resize(int w, int h) {
		// TODO Auto-generated method stub
		stage.getViewport().update(w, h, true);
		stage.getBatch().getProjectionMatrix().setToOrtho2D(0, 0, width,height);
		Gdx.graphics.requestRendering();

	}


	public void resume() {
		// TODO Auto-generated method stub
		
	}


	public void show() {
		
		spritepokemon=new Texture(Gdx.files.internal("Sprites/"+pkselector+".png"));
	
	}
		
	public void drawMap(){
		map = new Texture(Gdx.files.internal("testminimap.png"));
		stage.addActor(new MyActor(340-map.getWidth()/2,165-map.getHeight()/2));	
	}
		
	public void update(int state, int pkselector,int page, int optselector) {
		this.state=state;
		this.pkselector=pkselector;
		this.page=page;
		this.optselector=optselector;
		System.out.println(state+" "+pkselector+" "+page);
		spritepokemon=new Texture(Gdx.files.internal("Sprites/"+(pkselector+page)+".png"));
		Gdx.graphics.requestRendering();
		
	}

}
