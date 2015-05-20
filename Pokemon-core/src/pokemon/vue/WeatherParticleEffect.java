package pokemon.vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

public class WeatherParticleEffect extends WeatherEffect{
	ParticleEffect pEffect;
	float scale;
	
	WeatherParticleEffect(FileHandle effect,int x,int y,float scale){
		pEffect=new ParticleEffect();
		this.setPos(x, y);
		pEffect.load(effect, Gdx.files.internal("effect"));
		pEffect.setPosition(x, y);
		pEffect.scaleEffect(scale);
	}
	
	WeatherParticleEffect(FileHandle effect,FileHandle sound,int x,int y,float scale){
		this(effect,x,y, scale);
		s=Gdx.audio.newSound(sound);
	}
	
	public void start(){
		pEffect.start();
		if(s!=null)
		s.setVolume(s.play(),1f);
	}
	
	public void draw(Batch batch,float delta){
		if(!pEffect.isComplete())
		batch.setColor(Color.LIGHT_GRAY);
		pEffect.draw(batch,delta);
	}
}
