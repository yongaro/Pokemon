package pokemon.vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;

public class Pokeball extends SpriteActor{

	//TextureRegion g=new T
	boolean alive;
	int i;
	
	Pokeball(boolean alive,int i){
		super();
		setSprite(Gdx.files.internal("Sprites/ballIcon.png"));
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
