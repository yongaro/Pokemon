package pokemon.modele;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;

/* La classe Musique permet de stocker le nom du fichier de la musique que doit jouer la Map, 
 * notemment en lisant les propri�t�s de la TiledMap */

public class Music {
	private String path;
	
	public Music() {
		setPath("musics/music.mp3");
	}
	
	public Music(TiledMap tiledMap) {
		MapProperties prop = tiledMap.getProperties();
		path = prop.get("music", String.class);
		if(path == null)
			setPath("musics/music.mp3");
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
