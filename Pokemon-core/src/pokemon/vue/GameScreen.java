package pokemon.vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public abstract class GameScreen implements Screen{
	public static final int width=640;//Gdx.graphics.getWidth();
	public static final int height=360;
	protected Stage stage;
	protected BitmapFont f;
	protected ShapeRenderer shapeRenderer;
	float elapsedTime;
	boolean touched;

	public GameScreen(){
		stage=new Stage(new FitViewport(GameScreen.width,GameScreen.height));
		f=new BitmapFont(Gdx.files.internal("pkm1.fnt"), Gdx.files.internal("pkm1.png"), false);
		shapeRenderer=new ShapeRenderer();
		elapsedTime=-1;
		touched=false;
	}
	
	public void resize(int arg0, int arg1) {
	 	stage.getViewport().update(arg0, arg1, true);
        stage.getBatch().getProjectionMatrix().setToOrtho2D(0, 0, width, height);
		
	}

	public Stage getStage() {
		return stage;
	}
	
	public void drawUI(float delta){
		if(elapsedTime!=-1 && elapsedTime<300){
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.rect(35, 10, 30, 80);
			shapeRenderer.rect(10, 35, 80, 30);
			shapeRenderer.ellipse(550, 10, 80, 80);
			shapeRenderer.ellipse(460,10, 80, 80);
			shapeRenderer.end();
			if(touched==false)
					elapsedTime+=delta*120;
		}

	}
	public void setTouched(boolean touched) {
		System.out.println("TOUCHED");
		this.touched=touched;
		if(touched){
			elapsedTime=0;
		}
		

	}

}
