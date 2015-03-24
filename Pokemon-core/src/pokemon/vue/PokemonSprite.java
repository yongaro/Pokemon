package pokemon.vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class PokemonSprite extends Actor{
    public Texture s=new Texture(Gdx.files.internal("Sprites/6.png"));
    SpriteBatch b=new SpriteBatch();
    
    public PokemonSprite(){
   
    		super();
    	   	this.setBounds(-60, 250, s.getWidth()*1.2f, s.getHeight()*1.2f);
  
    	   	this.addAction(Actions.moveTo(500, 250, 3f));
    	   	this.addAction(Actions.scaleBy(100, 100,3f));
	        b.setColor(0.2f, 0.2f, 0.2f,1); 
	        b.getProjectionMatrix().setToOrtho2D(0, 0,640,360);
    }
    
    
    
    public void draw (Batch batch, float parentAlpha) {
	     b.begin();
	     	if(this.getX()==500)
	     		b.setColor(Color.WHITE);
		   System.out.print("Scale:"+this.getScaleX());
		 
	       b.draw(s,this.getX(),this.getY(),this.getScaleX(), this.getScaleY());
	      // b.setColor(color);
	       b.end();
	        System.out.println(this.getX()+"+"+this.getY());
	    	//batch.draw(t,this.getX(),this.getY(),t.getWidth(),t.getHeight());
	    	//batch.draw(s,this.getX()+Minimap.BourgPalette.getX(),this.getY()+Minimap.BourgPalette.getY(),30,30);
	    }
}
