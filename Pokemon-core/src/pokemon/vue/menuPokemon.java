package pokemon.vue;

import java.util.Random;
import java.util.Vector;

import pokemon.annotations.Tps;
import pokemon.controle.PokemonMenuListenner;
import pokemon.launcher.MyGdxGame;
import pokemon.modele.Capacite;
import pokemon.modele.CapacitePassive;
import pokemon.modele.Joueur;
import pokemon.modele.Pkm;
import pokemon.modele.Stockage;
import pokemon.modele.Type;
import pokemon.modele.UniteStockage;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
@Tps(nbhours=5)
public class menuPokemon  implements Screen {

	MyGdxGame myGdxGame;
	Joueur joueur= MyGdxGame.Jtest;
	private Texture texture;
	//private Image splashImage = new Image(texture);
	int width=640;//Gdx.graphics.getWidth();
	int height=360;//Gdx.graphics.getHeight();
	/*TextureAtlas atlas=new TextureAtlas(Gdx.files.internal("pkm.pack"));
		TextureRegion sp=atlas.findRegion("1");*/
	public Stage stage = new Stage(new FitViewport(width,height));
	BitmapFont f=new BitmapFont(Gdx.files.internal("pkm1.fnt"), Gdx.files.internal("pkm1.png"), false);
	Random r=new Random();
	ShapeRenderer shapeRenderer=new ShapeRenderer();
	int state=1;
	public int pkselector=1;
	int atkselector=1;
	int offset=0;
	int posx=0;
	//String[] str; //bufferisation de la definition de l'attaque
	public Vector<String> nom=new Vector<String>();
	public Vector<String> attaque=new Vector<String>();


	public menuPokemon(MyGdxGame myGdxGame) {
		this.myGdxGame=myGdxGame;
		//str=textCut("Envoie un puissant jet d'eau\npour frapper l'ennemi.");
		//listener=new PokemonMenuListenner(this,myGdxGame);
		nom.add("Dracaufeu");nom.add("Pikaderp");nom.add("Bulbiboule");nom.add("Sorboul");nom.add("Saladerp");nom.add("Taupiqueur");
		attaque.add("Hydro-canon");attaque.add("Fatal Foudre");attaque.add("Griffe");attaque.add("Smashing");

	}


	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
		// stage.getBatch().begin();
		// f.draw(stage.getBatch(),message,(Gdx.graphics.getWidth()/2)-(f.getBounds(message).width)/2, 200);
		// stage.getBatch().end();
		shapeRenderer.setProjectionMatrix(stage.getViewport().getCamera().combined);
		shapeRenderer.begin(ShapeType.Filled);
		/*Drawing top tabs*/
		shapeRenderer.setColor(0.85f, 0.85f, 0.85f, 1);
		shapeRenderer.rect(0, height-30, 154, 30);	       
		shapeRenderer.setColor(0.58f, 0.59f, 0.57f, 1);
		shapeRenderer.rect(162,height-30, 154, 30);
		shapeRenderer.rect(324, height-30, 154, 30);
		shapeRenderer.rect(486, height-30, 154, 30);

		shapeRenderer.setColor(0.85f, 0.85f, 0.85f, 1);
		shapeRenderer.rect(0,0,width, height-30);
		/*Drawing left menu*/
		shapeRenderer.setColor(1, 1, 1, 1);
		shapeRenderer.rect(15,10, 185, 270);
		shapeRenderer.setColor(0.58f, 0.59f, 0.57f, 1);
		shapeRenderer.rect(15,280, 185, 41);

		/*Drawing right bottom menu*/
		shapeRenderer.rect(210,10, 175, 110);
		shapeRenderer.setColor(1, 1, 1, 1);
		shapeRenderer.rect(385,10, 240, 110);
		/*object banner*/
		shapeRenderer.rect(210,125, 150,60); 
		shapeRenderer.setColor(0.58f, 0.59f, 0.57f, 1);
		/*capacity definition banner*/
		shapeRenderer.rect(360,125, 265, 60);
		/*capacity banner*/
		shapeRenderer.rect(210,185, 415, 20);
		shapeRenderer.setColor(1, 1, 1, 1);
		shapeRenderer.rect(210,205, 415, 32);
		/*top banner*/
		shapeRenderer.setColor(0.58f, 0.59f, 0.57f, 1);
		shapeRenderer.rect(210,237, 415, 84);
		shapeRenderer.setColor(0.58f, 0.59f, 0.57f, 1);
		shapeRenderer.triangle(210+207-10, 237, 210+207+10, 237, 210+207, 227); //top triangle
		shapeRenderer.triangle(210+207-10, 205, 210+207+10, 205, 210+207, 215); //bottom triangle
		/*Drawing pokemon selectors*/
		shapeRenderer.setColor(1, 0, 0, 1);
		shapeRenderer.rect(15,280-(45*this.pkselector), 5, 45);
		shapeRenderer.setColor(0, 0, 1, 1);
		shapeRenderer.rect(210,120-(27.5f*this.atkselector), 5, 27);

		shapeRenderer.end();
		
		stage.getBatch().begin();
		if(joueur.teamSize()>0)
		{
			
			f.setColor(0.58f, 0.59f, 0.57f, 1);
			f.setScale(1.2f);
			f.draw(stage.getBatch(),"Pokemons",18, height-5);
			f.setColor(1, 1, 1, 1);

			f.draw(stage.getBatch(),"Inventaire",160+13, height-5);
			f.draw(stage.getBatch(),"PokeMatos",324+13, height-5);

			f.setScale(1.5f);
			f.draw(stage.getBatch(),"Team",105*0.7f, 315);
			//stage.getBatch().draw(sp, 50, 50,500,500);
			f.setScale(1.2f);
			f.draw(stage.getBatch(),joueur.getTeam()[pkselector-1].getNom(),310, 315);
			f.draw(stage.getBatch(),"Lvl "+joueur.getTeam()[pkselector-1].get(0),(210+405)-f.getBounds("Lvl "+joueur.getTeam()[pkselector-1].get(0)).width, 315);
			f.setScale(1.0f);
			f.draw(stage.getBatch(),"Pv "+joueur.getTeam()[pkselector-1].get(2)+"/"+joueur.getTeam()[pkselector-1].getmax(2),310, 285);
			int xprate=0;//(joueur.getTeam()[pkselector-1].get(1)*100)/joueur.getTeam()[pkselector-1].getmax(1);
			f.draw(stage.getBatch(),"XP "+joueur.getTeam()[pkselector-1].getmax(0)+"%",(210+405)-f.getBounds("XP "+joueur.getTeam()[pkselector-1].getmax(0)+"%").width, 285);
			f.setScale(0.8f);
			f.draw(stage.getBatch(),"Empoisonne",310, 257);
			String types="";
			for(Type t:joueur.getTeam()[pkselector-1].getType())
			{
				if(t!=null)
				types+=" "+t.name();
			}
			f.draw(stage.getBatch(),types,(210+405)-f.getBounds(types).width, 257);
			f.setScale(0.6f);
			f.setColor(0.58f, 0.59f, 0.57f, 1);
			f.draw(stage.getBatch(),"Att: "+joueur.getTeam()[pkselector-1].get(3)+" / AttSpe: "+joueur.getTeam()[pkselector-1].get(5),220,235);
			f.draw(stage.getBatch(),"Def: "+joueur.getTeam()[pkselector-1].get(4)+" / DefSpe: "+joueur.getTeam()[pkselector-1].get(6),220,220);
			f.draw(stage.getBatch(),"Vitesse: "+joueur.getTeam()[pkselector-1].get(7),(210+405)-f.getBounds("Vitesse: "+joueur.getTeam()[pkselector-1].get(7)).width,235);
			f.draw(stage.getBatch(),joueur.getTeam()[pkselector-1].getNat().name(),(210+405)-f.getBounds(joueur.getTeam()[pkselector-1].getNat().name()).width,220);
			f.setColor(1, 1, 1, 1);
			f.draw(stage.getBatch(),joueur.getTeam()[pkselector-1].getCapP().getNom(),(210+((415/2)-(f.getBounds(joueur.getTeam()[pkselector-1].getCapP().getNom()).width)/2)),202);
			f.setScale(0.8f);

			f.drawWrapped(stage.getBatch(),joueur.getTeam()[pkselector-1].getCapP().getDesc(),365,185,260);
			f.setColor(0.58f, 0.59f, 0.57f, 1);
				   
			f.draw(stage.getBatch(),joueur.getTeam()[pkselector-1].getCap().at(atkselector-1).getNom(),400,115);
			f.draw(stage.getBatch(),"90",(210+405)-f.getBounds("90").width,115);
			offset=0;	   
			f.setScale(0.7f);	       


			f.drawWrapped(stage.getBatch(),joueur.getTeam()[pkselector-1].getCap().at(atkselector-1).getDesc(),395,95,230);
			offset+=17;

			offset=0;
			//f.draw(stage.getBatch(),attaque.get(1),250,95-offset);


			f.setScale(0.9f);
			offset=0;
			for(int i=0;i<joueur.teamSize();i++) //liste des pokemons
			{
				f.draw(stage.getBatch(),joueur.getTeam()[i].getNom(),35,268-offset);
				offset+=46;
			}
			f.setScale(0.7f);
			offset=0;
			f.setColor(1,1,1,1);
			for(UniteStockage<Capacite> cap:joueur.getTeam()[pkselector-1].getCap())//affichage des attaques
			{
				f.draw(stage.getBatch(),cap.get().getNom(),230,113-offset);
				offset+=27.5;
			}
			/*for(int i=0;i<joueur.getTeam()[pkselector-1].getCap().size();i++){ //liste des attaques du pokemon survolé
				f.draw(stage.getBatch(),attaque.get(i),230,113-offset);
				offset+=27.5;
			}*/
		}
		stage.getBatch().draw(texture,(320-10)*0.7f,(410-25)*0.6f,(int)(texture.getWidth())*0.95f,(int)(texture.getHeight()*0.95f));
		stage.getBatch().end();

		// System.out.println(pkselector);
		Gdx.graphics.setContinuousRendering(false); //coupe la boucle de render inutile dans les menus
	}


	public void update(int state,int pkselector, int atkselector)
	{
		this.state=state;
		this.pkselector=pkselector;
		this.atkselector=atkselector;
		if(joueur.teamSize()>0)
			texture = new Texture(Gdx.files.internal("Sprites/"+joueur.getTeam()[pkselector-1].getID()+".png")); /*pick random pkm*/
		else
			texture = new Texture(Gdx.files.internal("Sprites/0.png"));
	}

	@Override
	public void resize(int width, int height) {     //https://github.com/libgdx/libgdx/wiki/Scene2d
		stage.getViewport().update(width, height, true);
		stage.getBatch().getProjectionMatrix().setToOrtho2D(0, 0, this.width,this.height);
		Gdx.graphics.requestRendering();
	}


	@Override
	public void show() {
		//Gdx.input.setInputProcessor(listener);
		update(state,pkselector,atkselector);
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {       
	}

	@Override
	public void resume() {      
	}

	@Override
	public void dispose() {

	}
	/*  private void init() {
			camera = new OrthographicCamera(Gdx.graphics.getWidth(),
					Gdx.graphics.getHeight());
			camera.update();
			//batch = new SpriteBatch();

			// create stage
			stage = new Stage();
			// set the stage to handle input
	        Gdx.input.setInputProcessor(stage);

	        // create actor
	       myActor = new MyActor(stage.getBatch());

	        // add myActor to stage
	        stage.addActor(myActor);
		}*/

}