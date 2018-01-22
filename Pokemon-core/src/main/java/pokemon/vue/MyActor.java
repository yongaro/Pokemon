package pokemon.vue;

 import pokemon.launcher.PokemonCore;

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
	    public Texture s=new Texture(Gdx.files.internal("crosshair.png"));
	    SpriteBatch b=new SpriteBatch();
	    float x,y;
	    
	    public MyActor (float x, float y) {
	    	this.x=x;
	    	this.y=y;
	    	System.out.print(PokemonCore.m.getCities().get(0).getX());
	        this.setBounds(x+PokemonCore.m.getCities().get(0).getX(),y+PokemonCore.m.getCities().get(0).getY(),s.getWidth(),s.getHeight());
	        
	       /* RepeatAction action = new RepeatAction();
	        action.setCount(RepeatAction.FOREVER);
	        action.setAction(Actions.fadeOut(2f));*/
	      this.addAction(Actions.repeat(RepeatAction.FOREVER,Actions.sequence(Actions.fadeOut(1f),Actions.fadeIn(1f))));
			//posx=this.getX();
			//posy=this.getY();
			//this.addAction(action);
			//this.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(2f)));
	        System.out.println("Actor constructed");
	        b.getProjectionMatrix().setToOrtho2D(0, 0,640,360);
			
	    }
	    

	    @Override
	    public void draw (Batch batch, float parentAlpha) {
	     b.begin();
	    	Color color = getColor();
	    	
	    			    	
	    	
		   b.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		   
	       b.draw(s,this.getX()-15,this.getY()-15,30,30);
	       b.setColor(color);
	       b.end();
	        //System.out.println("Called");
	    	//batch.draw(t,this.getX(),this.getY(),t.getWidth(),t.getHeight());
	    	//batch.draw(s,this.getX()+Minimap.BourgPalette.getX(),this.getY()+Minimap.BourgPalette.getY(),30,30);
	    }
	    public void setPosition(float x, float y){
	    	this.setX(x+this.x);
	    	this.setY(y+this.y);
	    }
	}
 
