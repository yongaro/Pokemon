package pokemon.vue;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Music.OnCompletionListener;

//Listener Factored for each music
public class EndOfTrackListener implements OnCompletionListener{
	protected float loop_begin;
	
	public EndOfTrackListener(float lb){ this.loop_begin=lb; }
	
	public void onCompletion(Music arg){
		arg.play();
		arg.setPosition(loop_begin);
	}

}