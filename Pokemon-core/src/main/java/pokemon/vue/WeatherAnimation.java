package pokemon.vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class WeatherAnimation extends WeatherEffect{
	
	TextureAtlas atlas;
	Animation a;
	float time,speed,scale;
	int width,height;
	
	WeatherAnimation(FileHandle file,int x,int y,int width,int height){
		time=-1;
		atlas=new TextureAtlas(file);
		a=new Animation(1f/8f,atlas.getRegions());
		this.setPos(x, y);
		this.width=width;
		this.height=height;
	}
	
	public void start() {
		time=0;
	}

	public void draw(Batch batch, float delta) {
		if(time>=0){
			time+=delta;
			//System.out.println(a.getKeyFrameIndex(time));
			batch.draw(a.getKeyFrame(time), x, y,width,height);
			}
			if (a.getKeyFrameIndex(time)==9 && time>=a.getFrameDuration()*10)
				time=-1;
		
	}

}
