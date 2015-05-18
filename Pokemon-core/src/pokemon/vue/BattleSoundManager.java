package pokemon.vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class BattleSoundManager {
	private static Music main=Gdx.audio.newMusic(Gdx.files.internal("musics/battlemain.ogg"));
	private static Music mainloop=Gdx.audio.newMusic(Gdx.files.internal("musics/battlemainloop.ogg"));
	private static Music start=Gdx.audio.newMusic(Gdx.files.internal("musics/battlestart.ogg"));
	private static Music intro=Gdx.audio.newMusic(Gdx.files.internal("musics/battleintro.ogg"));
	private static float volume=0.3f;

	public static void begin(){
		start.setVolume(volume);
		start.play();
		start.setOnCompletionListener(new Music.OnCompletionListener() {

			@Override
			public void onCompletion(Music arg0) {
				start.stop();
				intro.setVolume(volume);
				intro.play();
				intro.setLooping(true);
			}
		});
	}

	public static void next(){
		if(intro.isPlaying()){
			intro.setLooping(false);
			intro.setOnCompletionListener(new Music.OnCompletionListener() {

				@Override
				public void onCompletion(Music arg0) {
					intro.stop();
					main.setVolume(volume);
					main.play();
					main.setOnCompletionListener(new Music.OnCompletionListener() {

						@Override
						public void onCompletion(Music arg0) {
							main.stop();
							mainloop.setVolume(volume);
							mainloop.play();
							mainloop.setLooping(true);

						}
					});
				}
			});
		}
	}
	public static void end(){
		if(mainloop.isPlaying()){
			mainloop.stop();	
		}
	}

}
