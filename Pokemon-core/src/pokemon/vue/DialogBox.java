package pokemon.vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class DialogBox extends Actor {
	int width=640;//Gdx.graphics.getWidth();
	int height=360;//Gdx.graphics.getHeight();
	private BitmapFont f=new BitmapFont(Gdx.files.internal("pkm1.fnt"), Gdx.files.internal("pkm1.png"), false);
	private SpriteBatch sBatch = new SpriteBatch();
	private String message;
	
	//Constructeurs
	public DialogBox() {
		message = "Bonjour !";
	}
	public DialogBox(String msg) {
		message = msg;
	}
	
	//Fonctionnalites principales
	public void draw(Batch batch, float parentAlpha) {
		//Init
		super.draw(batch, parentAlpha);
		sBatch.begin();
		
		//Reglage de la police
		f.setColor(1, 1, 1, 1);
		f.setScale(1.2f);
		
		//On affiche le texte
		f.drawWrapped(sBatch, message, 10, 100, 600);
		sBatch.end();
	}
}
