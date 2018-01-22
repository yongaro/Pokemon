package pokemon.vue;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class WeatherEffect {
	

	
	
	protected int x,y;
	Sound s;
	
	public WeatherEffect(){
		x=y=0;
		s=null;
	}
	

	public abstract void start();
	public abstract void draw(Batch batch,float delta);
	
	public void setPos(int x,int y){
		this.x=x;
		this.y=y;
	}
}
