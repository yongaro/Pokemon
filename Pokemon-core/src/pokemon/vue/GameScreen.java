package pokemon.vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public abstract class GameScreen implements Screen{
	public static final int width=640;//Gdx.graphics.getWidth();
	public static final int height=360;
	protected Stage stage;
	protected BitmapFont f;
	protected ShapeRenderer shapeRenderer;
	
	public GameScreen(){
		stage=new Stage(new FitViewport(GameScreen.width,GameScreen.height));
		f=new BitmapFont(Gdx.files.internal("pkm1.fnt"), Gdx.files.internal("pkm1.png"), false);
		shapeRenderer=new ShapeRenderer();
		System.out.println("Police d ecriture "+f);
	}
	
	public void resize(int arg0, int arg1) {
	 	stage.getViewport().update(arg0, arg1, true);
        stage.getBatch().getProjectionMatrix().setToOrtho2D(0, 0, width, height);
		
	}

	public Stage getStage() {
		return stage;
	}

}
