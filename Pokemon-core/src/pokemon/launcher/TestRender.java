package pokemon.launcher;



import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import pokemon.modele.Direction;
import pokemon.modele.Joueur;
import pokemon.vue.DialogBox;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class TestRender implements Screen,InputProcessor{
	TiledMap map = new TmxMapLoader().load("maps/bigmap.tmx");
	TextureAtlas atlaswest=new TextureAtlas(Gdx.files.internal("player/w_right.pack"));
	TextureAtlas atlaseast=new TextureAtlas(Gdx.files.internal("player/w_right.pack"));
	TextureAtlas atlassouth=new TextureAtlas(Gdx.files.internal("player/w_south.pack"));
	TextureAtlas atlasnorth=new TextureAtlas(Gdx.files.internal("player/w_north.pack"));
	Animation rightwalk=new Animation(1f/5f,atlaswest.getRegions());
	Animation leftwalk=new Animation(1f/5f,atlaseast.getRegions());
	Animation southwalk=new Animation(1f/5f,atlassouth.getRegions());
	Animation northwalk=new Animation(1f/5f,atlasnorth.getRegions());
	Joueur j=MyGdxGame.Jtest;
	Animation a=rightwalk;
	OrthogonalTiledMapRenderer renderer;
	OrthographicCamera cam;
	int width=640;//Gdx.graphics.getWidth();
	int height=360;//Gdx.graphics.getHeight();
	private BitmapFont f=new BitmapFont(Gdx.files.internal("pkm1.fnt"), Gdx.files.internal("pkm1.png"), false);
	//private Stage stage = new Stage(new FitViewport(width,height));
    private Stage stage;
	Texture t=new Texture(Gdx.files.internal("sprite.png"));
	TiledMapTileLayer layerCollision;
	float posx=0;
	float posy=17;
	Vector2 speed;
	Vector2 nextPos=new Vector2(posx,posy);
	float animationtime;
	boolean move=false;
	Vector<Direction> input=new Vector<Direction>();
	DialogBox box = null;
	boolean inDialog = false;
	
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.position.set(j.getPos().x,j.getPos().y,0);
		cam.update();
		update(delta);
		renderer.setView(cam);
		renderer.render();
		renderer.getBatch().begin();
		if(j.isMoving()){
				animationtime+=delta;
				move=true;
		}
		else{
			if(a.getKeyFrameIndex(animationtime)<2 && move) //on va jusqua la derniere frame
				animationtime+=delta;
			else{
				move=false;
				animationtime=0;
			}
				
		}
		
		//renderer.getBatch().draw(atlassouth.getRegions().get(1),0,0);
		//renderer.getBatch().draw(atlassouth.getRegions().get(2),20,0);
		//else
		//animationtime=0;
		renderer.getBatch().draw(a.getKeyFrame(animationtime,true), j.getPos().x, j.getPos().y);
		//renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get(2));

		renderer.getBatch().end();
		stage.draw();
		


	}

	@Override
	public void resize(int arg0, int arg1) {
		//cam.viewportWidth=arg0/2f;
		//cam.viewportHeight=arg1/2f;
		stage.getViewport().update(arg0, arg1, true);
		stage.getBatch().getProjectionMatrix().setToOrtho2D(0, 0, this.width,this.height);
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		renderer=new OrthogonalTiledMapRenderer(j.getCurrentMap().getTiledMap());
		//layerCollision =  (TiledMapTileLayer) map.getLayers().get(1);
		cam=new OrthographicCamera();
		Gdx.input.setInputProcessor(this);
		//speed=new Vector2(0,0);
		for(TextureRegion r:atlaseast.getRegions())
		{
			r.flip(true, false);
		}

		stage = new Stage(new FitViewport(width,height,cam));
	   //cam.zoom-=0.5;
	}
	
	public void update(float delta)
	{
		/*si le sprite bouge*/

		if(j.updatePosition()){
			map=j.getCurrentMap().getTiledMap();
			renderer.setMap(map);
		}


	}

	@Override
	public boolean keyDown(int keycode) {
		if(Gdx.input.isKeyPressed(Keys.RIGHT))
		{
			if(input.size()<2 && !input.contains(Direction.East))
				{input.add(0, Direction.East);
			j.move(input.firstElement());
			a=this.rightwalk;}

		}
		if(Gdx.input.isKeyPressed(Keys.LEFT) ){
			
			if(input.size()<2 && !input.contains(Direction.West))
				{input.add(0, Direction.West);
				j.move(input.firstElement());
				a=this.leftwalk;}
			}
		if(Gdx.input.isKeyPressed(Keys.DOWN)){
		
			if(input.size()<2 && !input.contains(Direction.South))
				{input.add(0, Direction.South);
			j.move(input.firstElement());
			a=this.southwalk;}

		}
		if(Gdx.input.isKeyPressed(Keys.UP))
		{
			if(input.size()<2){
				input.add(0, Direction.North);
			j.move(input.firstElement());

			a=this.northwalk;}

		}
		if(Gdx.input.isKeyPressed(Keys.A)) {
			String text = j.interact(MyGdxGame.npcList);
			if(text != null) {
				if(!inDialog){
					DialogBox box = new DialogBox(text);
					stage.addActor(box);
					inDialog = true;
				}
			}
		}

		for(Direction c:input)
		{
			System.out.println(c.name());
		}
		return false;
	}


	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		switch(arg0)
		{
		case Keys.RIGHT:
			input.remove(Direction.East);
			break;
		case Keys.LEFT:
			input.remove(Direction.West);
			break;
		case Keys.DPAD_UP:
			input.remove(Direction.North);
			break;
		case Keys.DPAD_DOWN:
			input.remove(Direction.South);
			break;

		}
		if(input.size()!=0){
			j.move(input.firstElement());
			switch(input.firstElement()){
			case North:
				a=this.northwalk;
				break;

			case South:
				a=this.southwalk;
				break;

			case East:
				a=this.rightwalk;
				break;

			case West:
				a=this.leftwalk;
				break;
			}
		}
		else 
			j.move(Direction.Standing);
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

}
