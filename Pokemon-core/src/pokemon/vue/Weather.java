package pokemon.vue;

import com.badlogic.gdx.Gdx;

public enum Weather {
	Rain(new WeatherParticleEffect(Gdx.files.internal("effect/RainEffect"),Gdx.files.internal("Sound/rain.ogg"),-150,360,2f)),Dust(new WeatherParticleEffect(Gdx.files.internal("effect/SandEffect"),Gdx.files.internal("Sound/wind.ogg"),0,0,1.5f)),Sunny(new WeatherAnimation(Gdx.files.internal("effect/sunshine.pack"), 0, -30,640,400));
	
	WeatherEffect w;
	
	Weather(WeatherEffect w){
		this.w=w;
	}
	WeatherEffect get(){
		return w;
	}
}
