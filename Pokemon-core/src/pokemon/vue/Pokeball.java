package pokemon.vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class Pokeball extends Actor{

	Sprite s=new Sprite(new Texture(Gdx.files.internal("Sprites/ballIcon.png")));
	//TextureRegion g=new T
	boolean alive;
	RotateByAction b=new RotateByAction();
	int i;
	Pokeball(boolean alive,int i){
		this.alive=alive;
		this.setBounds(650+20*i,150,15,15);
		this.i=i;
		this.addAction(Actions.sequence(Actions.delay((float) (0.2+i*0.2f)),Actions.parallel(Actions.moveBy(-160, 0,0.5f))));
		
	}
	


	@Override
	public void draw (Batch batch, float parentAlpha){
		batch.end();
		batch.getProjectionMatrix().setToOrtho2D(0, 0,640,360);
		batch.begin();
		if(alive)
			batch.setColor(Color.WHITE);
		else{
			getStage().getBatch().setColor(Color.DARK_GRAY);
		}
		//batch.draw(t,this.getX(),this.getY(),0,0,15,15,1,1,this.getRotation());
		s.setPosition(this.getX(), this.getY());
		if(this.getX()<640+10*i && s.getRotation()<720f)
		s.rotate(30);
		s.draw(batch);
		batch.setColor(Color.WHITE);
		//b.end();
	}
}