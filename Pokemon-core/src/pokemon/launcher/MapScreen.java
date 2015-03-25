package pokemon.launcher;

import pokemon.controle.JoueurController;
import pokemon.modele.Joueur;
import pokemon.vue.DialogBox;
import pokemon.vue.JoueurVue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MapScreen implements Screen{
	//Attributs joueur
	private Joueur j=MyGdxGame.Jtest;
	private JoueurVue joueur= new JoueurVue(j);
	private JoueurController controller = new JoueurController(this, joueur);
	
	//Attributs affichage
	private int width=640;//Gdx.graphics.getWidth();
	private int height=360;//Gdx.graphics.getHeight();
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera cam;

    //Attributs cinematiques
	private Stage stage;
    private DialogBox box = null;
    
    @Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.position.set(j.getPos().x,j.getPos().y,0);
		cam.update();
		update(delta);
		
		renderer.setView(cam);
		renderer.render();
		renderer.getBatch().begin();
		joueur.render(delta, renderer.getBatch());

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
	public void show() {
		renderer=new OrthogonalTiledMapRenderer(j.getCurrentMap().getTiledMap());
		cam=new OrthographicCamera();
		cam.zoom-=0.5;
		
		Gdx.input.setInputProcessor(controller);
		
		stage = new Stage(new FitViewport(width,height,cam));
	}
	public void update(float delta)
	{
		joueur.updatePosition(renderer);
	}
	
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
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
	
	//Autres fonctions
	public void updateDialogBox(Joueur j) {
		if(box == null) {
			String text = j.interact(MyGdxGame.npcList);
			if(text != null) {
				j.setTalking(true);
				box = new DialogBox(text);
				stage.addActor(box);
			}
		}
		else
		{
			j.setTalking(false);
			stage.clear();
			box.remove();
			box = null;
		}
	}
}
