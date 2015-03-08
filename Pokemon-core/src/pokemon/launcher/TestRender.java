package pokemon.launcher;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;

public class TestRender implements Screen,InputProcessor{
	TiledMap map = new TmxMapLoader().load("sans-titre.tmx");
	TextureAtlas atlaswest=new TextureAtlas(Gdx.files.internal("player/w_right.pack"));
	TextureAtlas atlaseast=new TextureAtlas(Gdx.files.internal("player/w_right.pack"));
	TextureAtlas atlassouth=new TextureAtlas(Gdx.files.internal("player/w_south.pack"));
	TextureAtlas atlasnorth=new TextureAtlas(Gdx.files.internal("player/w_north.pack"));
	Animation rightwalk=new Animation(1f/5f,atlaswest.getRegions());
	Animation leftwalk=new Animation(1f/5f,atlaseast.getRegions());
	Animation southwalk=new Animation(1f/5f,atlassouth.getRegions());
	Animation northwalk=new Animation(1f/5f,atlasnorth.getRegions());

	Animation a=rightwalk;

	OrthogonalTiledMapRenderer renderer;
	OrthographicCamera cam;
	Texture t=new Texture(Gdx.files.internal("sprite.png"));
	TiledMapTileLayer layerCollision;
	float posx=0;
	float posy=17;
	Vector2 speed;
	Vector2 nextPos=new Vector2(posx,posy);
	float animationtime;
	boolean move=false;
	
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
		cam.position.set(80,80,0);
		cam.update();
		update(delta);
		renderer.setView(cam);
		renderer.render();
		renderer.getBatch().begin();
		if(speed.x!=0 || speed.y!=0){
				animationtime+=delta;
				move=true;
		}
		else{
			System.out.println(a.getKeyFrameIndex(animationtime));
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
		renderer.getBatch().draw(a.getKeyFrame(animationtime,true), posx, posy);
		renderer.getBatch().end();
		


	}
	public boolean noCollision(){
		return (layerCollision.getCell((int) ((posx+t.getWidth())/16f),1))==null;}
	@Override
	public void resize(int arg0, int arg1) {
		cam.viewportWidth=arg0/3.5f;
		cam.viewportHeight=arg1/3.5f;

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		renderer=new OrthogonalTiledMapRenderer(map);
		layerCollision =  (TiledMapTileLayer) map.getLayers().get(1);
		cam=new OrthographicCamera();
		Gdx.input.setInputProcessor(this);
		speed=new Vector2(0,0);
		for(TextureRegion r:atlaseast.getRegions())
		{
			r.flip(true, false);
		}
	}
	
	public void update(float delta)
	{
		/*si le sprite bouge*/
		if(speed.x!=0 || speed.y!=0){
			nextPos.x=posx;
			nextPos.y=posy;
			nextPos.x+=Gdx.graphics.getDeltaTime()*speed.x;
			nextPos.y+=Gdx.graphics.getDeltaTime()*speed.y;
			System.out.println("Nextpos: "+nextPos);
			/*Verif si non debordement de map*/

			if(nextPos.x>160-t.getWidth() || nextPos.x<0 	) 
			{speed.x=0;return;}
			if( nextPos.y>160-t.getHeight() || nextPos.y<0)
			{speed.y=0;return;}
			/*verif si collision avec decors*/
			if(layerCollision.getCell((int)(nextPos.x/16f),(int)(nextPos.y/16f))!=null ||
					layerCollision.getCell((int)((nextPos.x+t.getWidth()-5)/16f),(int)(nextPos.y/16f))!=null ||
					layerCollision.getCell((int)((nextPos.x+t.getWidth()-5)/16f),(int)((nextPos.y+t.getHeight()-5)/16f))!=null ||
					layerCollision.getCell((int)((nextPos.x)/16f),(int)((nextPos.y+t.getHeight()-5)/16f))!=null)
			{
				if(speed.x!=0)
					speed.x=0;
				if(speed.y!=0)
					speed.y=0;
				return;
			}
			System.out.println("Speed: "+speed);
			//speed.x+=10*delta;
			posx=nextPos.x;
			posy=nextPos.y;
		}


	}

	@Override
	public boolean keyDown(int keycode) {
		if(Gdx.input.isKeyPressed(Keys.RIGHT))
		{
			if((layerCollision.getCell((int) ((posx+t.getWidth())/16f),(int) (posy/16f)))==null){
				
				System.out.println((posx+t.getWidth())/16f);
			}
			speed.x=60;
			a=this.rightwalk;
			speed.y=0;
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT))
			if((layerCollision.getCell((int)(posx/16f),(int) (posy/16f)))==null){
				{//posx-=Gdx.graphics.getDeltaTime()*100;
				System.out.print("moving");

				}
				speed.x=-60;
				a=this.leftwalk;

				speed.y=0;
			}
		if(Gdx.input.isKeyPressed(Keys.DOWN)){
			//if((layerCollision.getCell((int)((posx+t.getWidth()/2f)/16f),(int) (posy/16f)))==null){

				//{posy-=Gdx.graphics.getDeltaTime()*100;
				//System.out.print("moving");
				//}
				speed.y=-60;
				a=this.southwalk;
				speed.x=0;
			}
		if(Gdx.input.isKeyPressed(Keys.UP))
		{
			//if((layerCollision.getCell((int)((posx+t.getWidth()/2f)/16f),(int) ((posy+t.getHeight())/16f)))==null){
			//posy+=Gdx.graphics.getDeltaTime()*100;
			speed.y=60;
			speed.x=0;
			a=this.northwalk;
		//}
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
		case Keys.LEFT:
			speed.x=0;
			break;
		case Keys.DPAD_UP:
		case Keys.DPAD_DOWN:
			speed.y=0;
			break;
		}
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
