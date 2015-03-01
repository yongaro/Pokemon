package pokemon.launcher;

 import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
 import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
 import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
 import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.*;


 public class MyActor extends Actor {
	    public Texture t;
	    SpriteBatch b=new SpriteBatch();
	    public int posx=0;
	    public int posy=0;
	    public Move move=Move.wait;
	    public MyActor () {
	        t = new Texture(Gdx.files.internal("crosshair.png"));
	        this.setBounds(0,0,30,30);
	       /* RepeatAction action = new RepeatAction();
	        action.setCount(RepeatAction.FOREVER);
	        action.setAction(Actions.fadeOut(2f));*/
	        this.addAction(Actions.repeat(RepeatAction.FOREVER,Actions.sequence(Actions.fadeOut(1f),Actions.fadeIn(1f))));
			

			//this.addAction(action);
			//this.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(2f)));
;
			
	    }

	    @Override
	    public void draw (Batch batch, float parentAlpha) {
	      b.begin();
	    	Color color = getColor();
	    	switch(move){
	    	case left:
	    		posx-=70*Gdx.graphics.getDeltaTime();
	    		break;
	    	case right:
	    		posx+=70*Gdx.graphics.getDeltaTime();
	    		break;

	    	}
	    			    	
	    	
		   b.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		   
	       b.draw(t,posx,this.getY(),30,30);
	       b.setColor(color);
	       b.end();
	        //System.out.println("Called");
	    }
	}