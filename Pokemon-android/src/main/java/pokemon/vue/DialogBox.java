package pokemon.vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/* La classe DialogBox permet d'afficher le message d'un PNJ a l'ecran*/

public class DialogBox extends Actor {
	
	//Attributs graphiques
	private int width;
	private int height;
	private BitmapFont f=new BitmapFont(Gdx.files.internal("pkm1.fnt"), Gdx.files.internal("pkm1.png"), false);
	private SpriteBatch sBatch = new SpriteBatch();
	private ShapeRenderer shapeRenderer = new ShapeRenderer();
	boolean usestagematrix;	//Attributs modele
	private String message;
	//

	//Constructeurs
	public DialogBox() {
		width = 640;
		height = 100;
		sBatch.getProjectionMatrix().setToOrtho2D(0, 0,640,360);
		usestagematrix=false;
	}
	public DialogBox(String msg) {
		this();
		message = msg;
	}
	public DialogBox(Vector2 size, boolean usestagematrix) {
	
		width = (int) size.x;
		height = (int) size.y;
		sBatch.getProjectionMatrix().setToOrtho2D(0, 0,640,360);
		this.usestagematrix=usestagematrix;
	}
	
	public void setWidth(int width)
	{
		this.width=width;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	//
	//Fonctionnalites principales
	public void draw(Batch batch, float parentAlpha) {
		//Init
		super.draw(batch, parentAlpha);
		batch.end();
		
		//Dessin du rectangle
		
		if(usestagematrix)
		shapeRenderer.setProjectionMatrix(this.getStage().getCamera().combined);
		else
		shapeRenderer.setProjectionMatrix(sBatch.getProjectionMatrix());
		
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(0.1f, 0.1f, 0.1f, 0.1f);
	    shapeRenderer.rect(10, 0, width-20, height);
	    shapeRenderer.end();
		
		sBatch.begin();
		
		//Reglage de la police
		f.setColor(1, 1, 1, 1);
		f.setScale(1.2f);
		
		//On affiche le texte + rectangle
		f.drawWrapped(sBatch, message, 20, 98, width-5);
		
		//Quit
		sBatch.end();
		batch.begin();
	}
	
}
