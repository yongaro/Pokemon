package pokemon.vue;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class SpriteStartMenu extends SpriteActor{
	static Random rand=new Random();
	
	public SpriteStartMenu() {
		super();
		reset();
		
	}
	
	public void reset(){
		this.setSprite(Gdx.files.internal("Sprites/"+(rand.nextInt(150)+1)+".png"));
		this.setPosition(GameScreen.width, 200);
		this.addAction(Actions.sequence(Actions.parallel(Actions.moveBy(-(GameScreen.width/2)-50,0f,1.8f),Actions.scaleTo(1.1f, 1.1f, 1.8f)),Actions.delay(1f),Actions.parallel(Actions.moveBy(-(GameScreen.width/2)-55,0f,1.8f),Actions.scaleTo(1.0f, 1.0f, 1.8f)),Actions.delay(1f)));
	}
	
	public void act(float delta){
		super.act(delta);
		if(this.getActions().size==0){
			reset();
		}
	}
	


}
