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
	    public Texture s=new Texture(Gdx.files.internal("crosshair.png"));
	    SpriteBatch b=new SpriteBatch();
	    public float posx;
	    public float posy;
	    public Move move=Move.wait;
	    public MyActor () {
	    	
	        t = new Texture(Gdx.files.internal("testminimap.png"));
	        this.setBounds(340-t.getWidth()/2,165-t.getHeight()/2,370,370);
	        
	       /* RepeatAction action = new RepeatAction();
	        action.setCount(RepeatAction.FOREVER);
	        action.setAction(Actions.fadeOut(2f));*/
	      //  this.addAction(Actions.repeat(RepeatAction.FOREVER,Actions.sequence(Actions.fadeOut(1f),Actions.fadeIn(1f))));
			//posx=this.getX();
			//posy=this.getY();
			//this.addAction(action);
			//this.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(2f)));
;
			
	    }
	    
	   /*public void act(float delta){
		   super.act(delta);
	    	switch(move){
	    	case left:
	    		if(posx>this.getX())
	    		posx-=2;
	    		break;
	    	case right:
	    		if(posx<this.getX()+this.getWidth())
	    		posx+=2;
	    		break;
	    	case up:
	    		if(posy<this.getY()+this.getHeight())
	    		posy+=2;
	    		break;
	    	case down:
	    		if(posy>this.getY())
	    		posy-=2;
	    		break;
	    		
	    	}
	    	System.out.println(posx+";"+posy);
	    }*/

	    @Override
	    public void draw (Batch batch, float parentAlpha) {
	    /*  b.begin();
	    	Color color = getColor();
	    	
	    			    	
	    	
		   b.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		   
	       b.draw(t,posx,posy,30,30);
	       b.setColor(color);
	       b.end();*/
	        //System.out.println("Called");
	    	batch.draw(t,this.getX(),this.getY(),t.getWidth(),t.getHeight());
	    	batch.draw(s,this.getX()+Minimap.BourgPalette.getX(),this.getY()+Minimap.BourgPalette.getY(),30,30);
	    }
	}