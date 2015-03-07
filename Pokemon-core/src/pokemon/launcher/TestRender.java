package pokemon.launcher;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

public class TestRender implements Screen,InputProcessor{
	TiledMap map = new TmxMapLoader().load("sans-titre.tmx");
	OrthogonalTiledMapRenderer renderer;
	OrthographicCamera cam;
	Texture t=new Texture(Gdx.files.internal("sprite.png"));
	TiledMapTileLayer layerCollision;
	float posx=0;
	float posy=17;
	Vector2 speed;
	Vector2 nextPos=new Vector2(posx,posy);
	
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
		update();
		renderer.setView(cam);
		renderer.render();
		renderer.getBatch().begin();
		renderer.getBatch().draw(t, posx, posy,16,16);
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
	}
	
	public void update()
	{
		nextPos.x=posx;
		nextPos.y=posy;
		nextPos.x+=Gdx.graphics.getDeltaTime()*speed.x;
		nextPos.y+=Gdx.graphics.getDeltaTime()*speed.y;
		System.out.println("Nextpos: "+nextPos);
		/*Verif si non debordement de map*/
		if(nextPos.x>160-t.getWidth() || nextPos.x<0 ||
				layerCollision.getCell((int)(nextPos.x/16f),(int)(nextPos.y/16f))!=null ||
				layerCollision.getCell((int)((nextPos.x+t.getWidth())/16f),(int)(nextPos.y/16f))!=null ||
				layerCollision.getCell((int)((nextPos.x+t.getWidth()-5)/16f),(int)((nextPos.y+t.getHeight()-5)/16f))!=null ||
				layerCollision.getCell((int)((nextPos.x)/16f),(int)((nextPos.y+t.getHeight()-5)/16f))!=null

				) 
			{speed.x=0;return;}
		if( nextPos.y>160-t.getHeight() || nextPos.y<0)
			{speed.y=0;return;}
		
		System.out.println("Speed: "+speed);
		posx=nextPos.x;
		//posy=nextPos.y;
	}

	@Override
	public boolean keyDown(int keycode) {
		if(Gdx.input.isKeyPressed(Keys.RIGHT))
		{
			if((layerCollision.getCell((int) ((posx+t.getWidth())/16f),(int) (posy/16f)))==null){
				
				System.out.println((posx+t.getWidth())/16f);
			}
			speed.x=100;
			//speed.y=0;
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT))
			if((layerCollision.getCell((int)(posx/16f),(int) (posy/16f)))==null){
				{//posx-=Gdx.graphics.getDeltaTime()*100;
				System.out.print("moving");
				
				}
				speed.x=-100;
				//speed.y=0;
			}
		if(Gdx.input.isKeyPressed(Keys.DOWN)){
			//if((layerCollision.getCell((int)((posx+t.getWidth()/2f)/16f),(int) (posy/16f)))==null){

				//{posy-=Gdx.graphics.getDeltaTime()*100;
				//System.out.print("moving");
				//}
				speed.y=-100;
				//speed.x=0;
			}
		if(Gdx.input.isKeyPressed(Keys.UP))
		{
			//if((layerCollision.getCell((int)((posx+t.getWidth()/2f)/16f),(int) ((posy+t.getHeight())/16f)))==null){
			//posy+=Gdx.graphics.getDeltaTime()*100;
			speed.y=100;
			//speed.x=0;

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
