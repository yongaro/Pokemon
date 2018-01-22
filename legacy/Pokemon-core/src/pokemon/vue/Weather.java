package pokemon.vue;

import com.badlogic.gdx.Gdx;

public enum Weather {
	Grele(new WeatherParticleEffect(Gdx.files.internal("effect/IceEffect"),Gdx.files.internal("Sound/hail.ogg"),0,360,1f)),Pluie(new WeatherParticleEffect(Gdx.files.internal("effect/RainEffect"),Gdx.files.internal("Sound/rain.ogg"),-150,360,2f)),TempeteSable(new WeatherParticleEffect(Gdx.files.internal("effect/SandEffect"),Gdx.files.internal("Sound/wind.ogg"),0,0,1.5f)),Zenith(new WeatherAnimation(Gdx.files.internal("effect/sunshine.pack"), 0, -30,640,400));
	
	WeatherEffect w;
	
	Weather(WeatherEffect w){
		this.w=w;
	}
	WeatherEffect get(){
		return w;
	}
}
