package pokemon.vue;

import java.util.Hashtable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Music.OnCompletionListener;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

/**
 * Jukebox class handling all musics of the game
 * @author ResodAfk
 */
public enum Musics {
	Default_LowHP,
	Default_Trainer_Battle,
	Default_Wild_Battle;
	
	
	
	
	protected Music content;
	//Each non enumerated track parsed goes there stored <title,music>
	protected static Hashtable<String,Music> non_enumerated_content = new Hashtable<String,Music>();
	//Tell if songs where loaded or not
	protected static boolean enum_loaded=false;
	protected static Music current_music;
	protected static Music prec_music;
	protected static float volume=0.5f;
	
	private Musics(){
		XmlReader reader= new XmlReader();
		Element root;
		try{
			root = reader.parse(Gdx.files.internal("xml/Musics.xml"));
			Element e; 
			e=root.getChildByName(this.name());
			this.content=Gdx.audio.newMusic(Gdx.files.internal("musics/"+e.get("title")));
			this.content.setOnCompletionListener(new EndOfTrackListener(e.getFloat("intro")));
		}
		catch(Exception ex){
			System.out.println("Une erreur est survenue lors du chargement des musiques enumerees \n"+ex);
		}
	}
	
	/**
	 * Method to call before launching the game.
	 * Use it only one time to use every music listed in xml/Musics.xml at will.
	 */
	public static void loadContent(){
		if(!enum_loaded){
			enum_loaded=true;
			XmlReader reader= new XmlReader();
			Element root;
			try{
				root = reader.parse(Gdx.files.internal("xml/Musics.xml"));
				Element e; int i=0; int maxchild=root.getChildCount(); Music refTemp;
				while(i<maxchild){
					e=root.getChild(i);
					System.out.println("Loading "+e.getName());
					
					if(e.getInt("default")==0){
						refTemp=Gdx.audio.newMusic(Gdx.files.internal("musics/"+e.get("title")));
						refTemp.setOnCompletionListener(new EndOfTrackListener(e.getFloat("intro")));
						non_enumerated_content.put(e.get("title"),refTemp);
						System.out.println(e.get("title")+" ajoute");
						System.out.println("CHECK"+refTemp);
					}
					i++;
				}
			}
			catch(Exception ex){
				System.out.println("Une erreur est survenue lors du chargement de la musique \n"+ex);
			}
		}
	}
	
	/**
	 * Use this to play any song stored in non_enumerated_content
	 * @param title
	 * path from musics to the file to be played
	 */
	public static void play(String title){ 
		if(current_music!=null){current_music.stop();}
		current_music=non_enumerated_content.get(title);
		current_music.setVolume(volume);
		current_music.play();
	}
	/**
	 * Pause the current_music
	 */
	public static void pause(){}
	/**
	 * Stop the current_music
	 */
	public static void stop(){
		if(current_music!=null){
			current_music.stop();
		}
	}
	
	/**
	 * Play the default_wild_battle or default_trainer_battle
	 * @param wild Vs Wild or Trainer
	 */
	public static void playDefaultBattle(boolean wild){
		if(current_music!=null){current_music.stop();}
		if(wild){
			current_music=Default_Wild_Battle.content;
		}
		else{
			current_music=Default_Trainer_Battle.content;
		}
		current_music.setVolume(volume);
		current_music.play();
	}
	
	public static void playDefaultLowHP(){
		if(current_music!=null){current_music.pause();}
		prec_music=current_music;
		current_music=Default_LowHP.content;
		current_music.setVolume(volume);
		current_music.play();
	}
	public static void resumeAfterLowHP(){
		if(current_music!=null){current_music.stop();}
		current_music=prec_music;
		current_music.setVolume(volume);
		current_music.play();
	}
	
}