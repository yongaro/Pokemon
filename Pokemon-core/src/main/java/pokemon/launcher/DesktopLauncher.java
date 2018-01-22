package pokemon.launcher;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class DesktopLauncher {
	public static void main (String[] arg) throws Exception {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=(int) (640*1.2f);
		config.height=(int) (360*1.2f);
		LwjglApplication application = new LwjglApplication(new PokemonCore(), config);
	}
}