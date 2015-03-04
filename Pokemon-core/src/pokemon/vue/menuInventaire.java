package pokemon.vue;

import java.util.Vector;

import pokemon.annotations.Tps;
import pokemon.controle.InventaireMenuListener;
import pokemon.controle.MenuListener;
import pokemon.launcher.MyGdxGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

@Tps(nbhours=3)
public class menuInventaire implements Screen{

	MyGdxGame myGdxGame;
	//Screen previous;
	MenuListener menuListener;
	//InventaireMenuListener listener;
	private Texture texture = new Texture(Gdx.files.internal("Dresseur.png"));
	BitmapFont f=new BitmapFont(Gdx.files.internal("pkm1.fnt"), Gdx.files.internal("pkm1.png"), false);
	ShapeRenderer shapeRenderer=new ShapeRenderer();
	int width=640;
	int height=360;
    private Stage stage = new Stage(new FitViewport(width,height));
    int pktselector=1;
    int state=1;
    int actionselector=1;
    int[] objselector={0,0};
    int displayedAtk=0;
    int offset;
    String[] poches = {"Objets","Baies","Objets Combat","Objets Soin","Balls","Poche CT/CS"};
    Vector<String> objets ;
    public menuInventaire( MyGdxGame myGdxGame,  MenuListener menuListener)
    {
    	//this.previous=previous;
    	this.myGdxGame=myGdxGame;
    	//stage = new Stage(viewport);
    	this.menuListener=menuListener;
    	//listener=new InventaireMenuListener(myGdxGame,previous,this);
    	objets=InventaireMenuListener.objets;
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

		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shapeRenderer.setProjectionMatrix(stage.getViewport().getCamera().combined);
		shapeRenderer.begin(ShapeType.Filled);
		/*Drawing top tabs*/
		shapeRenderer.setColor(0.58f, 0.59f, 0.57f, 1);
		shapeRenderer.rect(0, height-30, 154, 30);	       
		shapeRenderer.setColor(0.85f, 0.85f, 0.85f, 1);
		shapeRenderer.rect(162,height-30, 154, 30);
		shapeRenderer.setColor(0.58f, 0.59f, 0.57f, 1);
		shapeRenderer.rect(324, height-30, 154, 30);
		shapeRenderer.rect(486, height-30, 154, 30);
        shapeRenderer.setColor(0.85f, 0.85f, 0.85f, 1);
        shapeRenderer.rect(0,0,width, height-30);
        
        /*Drawing left menu*/
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.rect(15,10, 185, 270);
        shapeRenderer.setColor(0.58f, 0.59f, 0.57f, 1);
        shapeRenderer.rect(15,280, 185, 40);
        
        /*Drawing top banner*/
        shapeRenderer.setColor(0.58f, 0.59f, 0.57f, 1);
        shapeRenderer.rect(210,235, 415, 85);
        
        /*Drawing middle left menu*/
        if(state>=2){
        shapeRenderer.rect(210,90, 240, 140);
       // shapeRenderer.setColor(1f, 1f, 1f, 1);
        shapeRenderer.rect(450,160, 175, 70); //middle right
        shapeRenderer.setColor(1f, 1f, 1f, 1);
        shapeRenderer.rect(450,90, 175, 70);
        shapeRenderer.rect(210,10, 415, 75); //bottom
        }
        /*Drawing selectors*/
        shapeRenderer.setColor(1, 0, 0, 1);
        shapeRenderer.rect(15,280-(45*this.pktselector), 5, 45);

        if(state>=2){
        	   shapeRenderer.setColor(0, 0, 1, 1);
        	   shapeRenderer.rect(210+(150*this.objselector[1]),230-(20f*(this.objselector[0]+1)), 5, 20);
               shapeRenderer.setColor(0, 1, 0, 1);
               shapeRenderer.rect(450,160-(35*this.actionselector), 5, 35);
        	   }
		shapeRenderer.end();
		
		stage.getBatch().begin();

		f.setColor(1, 1, 1, 1);
		f.setScale(1.2f);
		f.draw(stage.getBatch(),"Pokemons",18, height-5);
		f.draw(stage.getBatch(),"PokeMatos",324+13, height-5);
		f.setColor(0.58f, 0.59f, 0.57f, 1);
		f.draw(stage.getBatch(),"Inventaire",160+13, height-5);
		f.setScale(1.5f);
		f.setColor(1, 1, 1, 1);
		f.draw(stage.getBatch(),"Poches",55, 315);
        f.setScale(1.2f);
        f.draw(stage.getBatch(),"1348$",(210+405)-f.getBounds("1348$").width, 315);
	    f.draw(stage.getBatch(),"Sasha",310, 315);
	    f.setScale(1.0f);
	    f.draw(stage.getBatch(),"Badges",310, 285);
		
		offset=0;
        f.setScale(0.9f);
		f.setColor(0.58f, 0.59f, 0.57f, 1);

        for(int i=0;i<poches.length;i++)
        {
        	f.draw(stage.getBatch(),poches[i],35,268-offset);
        	offset+=46;
        }
        if(state>=2){
        	offset=0;
        	f.setScale(0.7f);
        	f.setColor(1,1,1,1);
        	for(int i=displayedAtk;i<Math.min(this.displayedAtk+7,objets.size());i++){
        		
        		f.draw(stage.getBatch(),objets.get(i),220,227-offset);
        		offset+=20;
   
        	}
        	offset=0;
        	for(int i=displayedAtk+7;i<Math.min(this.displayedAtk+14,objets.size());i++){
        		
        		f.draw(stage.getBatch(),objets.get(i),370,227-offset);
        		offset+=20;
   
        	}
    		f.setColor(0.58f, 0.59f, 0.57f, 1);


        	f.draw(stage.getBatch(),"Utiliser",475,150);
        }
        
        stage.getBatch().draw(texture,200,220,(int)(texture.getWidth())*1.2f,(int)(texture.getHeight())*1.2f);
		stage.getBatch().end();

	}

	@Override
	public void resize(int arg0, int arg1) {
    	stage.getViewport().update(arg0, arg1, true);
        stage.getBatch().getProjectionMatrix().setToOrtho2D(0, 0, width, height);
		Gdx.graphics.requestRendering();

		
	}
	 public void update(int state,int pktselector, int objectselector0,int objectselector1,int actionselector,int displayedAtk)
	    {
	    	this.state=state;
	    	this.pktselector=pktselector;
	    	this.objselector[0]=objectselector0;
	    	this.objselector[1]=objectselector1;
	    	this.displayedAtk=displayedAtk;
	    	this.actionselector=actionselector;
	    	Gdx.graphics.requestRendering();
	    }
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.graphics.setContinuousRendering(false);
	}

}
