package pokemon.vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

public class WeatherEffect {
	ParticleEffect pEffect;
	Sound s;
	
	WeatherEffect(FileHandle effect){
		pEffect=new ParticleEffect();
		pEffect.load(effect, Gdx.files.internal("effect"));
		pEffect.setPosition(-150, GameScreen.height);
		pEffect.scaleEffect(2f);
	}
	
	WeatherEffect(FileHandle effect,FileHandle sound){
		this(effect);
		s=Gdx.audio.newSound(sound);
	}
	
	public void start(){
		pEffect.start();
		if(s!=null);
		s.setVolume(s.play(),1f);
		
		
	}
	
	public void draw(Batch batch,float delta){
		if(!pEffect.isComplete())
		batch.setColor(Color.LIGHT_GRAY);
		pEffect.draw(batch,delta);
	}
}
