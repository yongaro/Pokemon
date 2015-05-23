package pokemon.vue;


import pokemon.annotations.Tps;
import pokemon.launcher.MyGdxGame;
import pokemon.modele.Capacite;
import pokemon.modele.Joueur;
import pokemon.modele.Type;
import pokemon.modele.UniteStockage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

@Tps(nbhours=5)
public class menuPokemon  extends GameScreen {

	MyGdxGame myGdxGame;
	Joueur joueur= MyGdxGame.Jtest;
	private Texture texture;
	int state=1;
	public int pkselector=0;
	int atkselector=1;
	int offset=0;
	int posx=0;
	String types="";

	int[] healthbars;

	public menuPokemon(MyGdxGame myGdxGame) {
		super();
		System.out.println("Police d ecriture "+f);
		this.myGdxGame=myGdxGame;
		//str=textCut("Envoie un puissant jet d'eau\npour frapper l'ennemi.");
		//listener=new PokemonMenuListenner(this,myGdxGame);
		healthbars=new int[6];
	}


	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glEnable(GL20.GL_BLEND);
	    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
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
		shapeRenderer.setColor(0.58f, 0.59f, 0.57f, 0.3f);
		shapeRenderer.triangle(210+207-10, 237, 210+207+10, 237, 210+207, 227); //top triangle
		shapeRenderer.triangle(210+207-10, 205, 210+207+10, 205, 210+207, 215); //bottom triangle
	
		/*Drawing Healthbars*/
		shapeRenderer.setColor(0.5f, 0.8f, 0.5f, 1f);
		offset=0;
		for(int i=0;i<joueur.teamSize();i++) //liste des pokemons
		{
		shapeRenderer.rect(35, 235-offset, healthbars[i], 5);
		offset+=45;
		}
		/*Drawing pokemon selectors*/
		shapeRenderer.setColor(1, 0, 0, 1);
		offset=45;
		offset+=offset*pkselector;
		shapeRenderer.rect(15,280-offset, 5, 45);
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
			f.draw(stage.getBatch(),joueur.getTeam()[pkselector].getNom(),310, 315);
			f.draw(stage.getBatch(),"Lvl "+joueur.getTeam()[pkselector].get(0),(210+405)-f.getBounds("Lvl "+joueur.getTeam()[pkselector].get(0)).width, 315);
			f.setScale(1.0f);
			f.draw(stage.getBatch(),"Pv "+joueur.getTeam()[pkselector].get(2)+"/"+joueur.getTeam()[pkselector].getmax(2),310, 285);
			int xprate=0;//(joueur.getTeam()[pkselector].get(1)*100)/joueur.getTeam()[pkselector].getmax(1);
			f.draw(stage.getBatch(),"XP "+joueur.getTeam()[pkselector].getmax(0)+"%",(210+405)-f.getBounds("XP "+joueur.getTeam()[pkselector].getmax(0)+"%").width, 285);
			f.setScale(0.8f);
			f.draw(stage.getBatch(),joueur.getTeam()[pkselector].getStatut().name(),310, 257);

			f.draw(stage.getBatch(),types,(210+405)-f.getBounds(types).width, 257);
			f.setScale(0.6f);
			f.setColor(0.58f, 0.59f, 0.57f, 1);
			f.draw(stage.getBatch(),"Att: "+joueur.getTeam()[pkselector].get(3)+" / AttSpe: "+joueur.getTeam()[pkselector].get(5),220,235);
			f.draw(stage.getBatch(),"Def: "+joueur.getTeam()[pkselector].get(4)+" / DefSpe: "+joueur.getTeam()[pkselector].get(6),220,220);
			f.draw(stage.getBatch(),"Vitesse: "+joueur.getTeam()[pkselector].get(7),(210+405)-f.getBounds("Vitesse: "+joueur.getTeam()[pkselector].get(7)).width,235);
			f.draw(stage.getBatch(),joueur.getTeam()[pkselector].getNat().name(),(210+405)-f.getBounds(joueur.getTeam()[pkselector].getNat().name()).width,220);
			f.setColor(1, 1, 1, 1);
			f.draw(stage.getBatch(),joueur.getTeam()[pkselector].getCapP().getNom(),(210+((415/2)-(f.getBounds(joueur.getTeam()[pkselector].getCapP().getNom()).width)/2)),202);
			f.setScale(0.8f);

			f.drawWrapped(stage.getBatch(),joueur.getTeam()[pkselector].getCapP().getDesc(),365,185,260);
			f.setColor(0.58f, 0.59f, 0.57f, 1);
			if( joueur.getTeam()[pkselector].getObjTenu()!=null)
			f.draw(stage.getBatch(), joueur.getTeam()[pkselector].getObjTenu().getNom(), 220, 150);
			f.draw(stage.getBatch(),joueur.getTeam()[pkselector].getCap().at(atkselector-1).getNom(),400,115);
			f.draw(stage.getBatch(),""+joueur.getTeam()[pkselector].getCap().at(atkselector-1).getPower(),(210+405)-f.getBounds(""+joueur.getTeam()[pkselector].getCap().at(atkselector-1).getPower()).width,115);
			offset=0;	   
			f.setScale(0.7f);	       


			f.drawWrapped(stage.getBatch(),joueur.getTeam()[pkselector].getCap().at(atkselector-1).getDesc(),395,95,230);
			//f.draw(stage.getBatch(),joueur.getTeam()[pkselector].getCap().elementAt(atkselector-1).,395,20);


			f.setScale(0.9f);
			offset=0;
			for(int i=0;i<joueur.teamSize();i++) //liste des pokemons
			{
				f.draw(stage.getBatch(),joueur.getTeam()[i].getNom(),35,268-offset);
				if(healthbars[i]+delta*100f<(joueur.getTeam()[i].get(2)*140)/joueur.getTeam()[i].getmax(2))
					healthbars[i]+=delta*100f;
				offset+=45;
			}
			f.setScale(0.7f);
			offset=0;
			f.setColor(1,1,1,1);
			for(UniteStockage<Capacite> cap:joueur.getTeam()[pkselector].getCap())//affichage des attaques
			{
				//f.draw(stage.getBatch(),cap.get().getNom(),220,113-offset);
				f.draw(stage.getBatch(),cap.get().getNom(),220,113-offset);
				f.draw(stage.getBatch(),cap.getQte()+"/"+cap.getQteMax(),385-f.getBounds(cap.getQte()+" / "+cap.getQteMax()).width,113-offset);
				offset+=27.5;
			}
			/*for(int i=0;i<joueur.getTeam()[pkselector].getCap().size();i++){ //liste des attaques du pokemon survolé
				f.draw(stage.getBatch(),attaque.get(i),230,113-offset);
				offset+=27.5;
			}*/
		}
		stage.getBatch().draw(texture,(320-10)*0.7f,(410-25)*0.6f,(int)(texture.getWidth())*0.95f,(int)(texture.getHeight()*0.95f));
		stage.getBatch().end();
		super.drawUI(delta);
		// System.out.println(pkselector);
		//Gdx.graphics.setContinuousRendering(false); //coupe la boucle de render inutile dans les menus
	}


	public void update(int state,int pkselector, int atkselector)
	{
		this.state=state;
		this.pkselector=pkselector;
		this.atkselector=atkselector;
		if(joueur.teamSize()>0)
			texture = new Texture(Gdx.files.internal("Sprites/"+joueur.getTeam()[pkselector].getID()+".png")); /*pick random pkm*/
		else
			texture = new Texture(Gdx.files.internal("Sprites/0.png"));
		types="";
		for(Type t:joueur.getTeam()[pkselector].getType())
		{
			if(t!=null)
			types+=" "+t.name();
		}
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
	public void healthbarswap(int i,int j){
		int temp=healthbars[i];
		healthbars[i]=healthbars[j];
		healthbars[j]=temp;
	}
}