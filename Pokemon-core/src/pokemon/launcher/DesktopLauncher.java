package pokemon.launcher;

import pokemon.modele.Atk;
import pokemon.modele.Capacite;
import pokemon.modele.Statut;
import pokemon.modele.Type;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public class DesktopLauncher {
	public static void main (String[] arg) throws Exception {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MyGdxGame(), config);
		config.width=900;
		config.height=600;
		TestCombat.fromXML();
	}
	
	

	
	
	
	
}
