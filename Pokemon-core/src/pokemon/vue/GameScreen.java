package pokemon.vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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
	protected int state;

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
	
	public int getState(){
		return state;
	}
	
	public void drawUI(float delta){
		if(elapsedTime!=-1 && elapsedTime<300){
			shapeRenderer.setProjectionMatrix(stage.getViewport().getCamera().combined);
			shapeRenderer.setColor(0.2f, 0.2f, 0.2f, 0.7f);
			shapeRenderer.begin(ShapeType.Filled);
			Gdx.gl.glEnable(GL20.GL_BLEND);
			shapeRenderer.rect(35, 10, 30, 80);
			shapeRenderer.rect(10, 35, 80, 30);
			shapeRenderer.ellipse(565, 40, 70, 70);
			shapeRenderer.ellipse(490,5, 70, 70);
			shapeRenderer.rect(340, 0,60,25);
			shapeRenderer.rect(250, 0,60,25);

			shapeRenderer.end();
			Gdx.gl.glDisable(GL20.GL_BLEND);
			if(touched==false)
					elapsedTime+=delta*150;
		}

	}
	
	public void setTouched(boolean touched) {
		this.touched=touched;
		if(touched){
			elapsedTime=0;
		}
	}

}
