package pokemon.launcher;

import java.util.Vector;

import pokemon.controle.JoueurController;
import pokemon.modele.Joueur;
import pokemon.modele.NPC;
import pokemon.modele.NoMoreInstructionException;
import pokemon.modele.NoNPCException;
import pokemon.vue.DialogBox;
import pokemon.vue.JoueurVue;
import pokemon.vue.NPCVue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MapScreen implements Screen{
	
	@SuppressWarnings("unused")
	private MyGdxGame game;
	
	//Attributs joueur
	private Joueur j=MyGdxGame.Jtest;
	private JoueurVue joueur= new JoueurVue(j);
	private JoueurController controller;
	
	//Attributs NPC
	private Vector<NPCVue> npcs = new Vector<NPCVue>();
	
	//Attributs affichage
	private int width=640;//Gdx.graphics.getWidth();
	private int height=360;//Gdx.graphics.getHeight();
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera cam;

    //Attributs cinematiques
	private Stage stage;
    private DialogBox box = null;

    //Constructeur
    public MapScreen(MyGdxGame game) {
		this.game = game;
		controller = new JoueurController(this, joueur, game);
	}
    
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
		for(NPCVue npc : npcs) {
			npc.render(renderer.getBatch(), delta);
		}
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
		//Affichage de la TiledMap
		renderer=new OrthogonalTiledMapRenderer(j.getCurrentMap().getTiledMap());
		cam=new OrthographicCamera();
		cam.zoom-=0.5;
		
		//Affichage des NPC
		for(NPC npc : j.getCurrentMap().getNpcs()) {
			NPCVue npcvue = new NPCVue(npc);
			npcs.add(npcvue);
		}
		
		//Generation du Stage
		stage = new Stage(new FitViewport(width,height,cam));
		
		//Definition de l'input
		Gdx.input.setInputProcessor(controller);
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
		String text = null;
		while(text == null) {
			try {
				text = j.interact(MyGdxGame.npcList);
			} catch (NoNPCException e) {
				//Si il n'y a pas de NPC, on quitte la boucle
				break;
			} catch (NoMoreInstructionException e) {
				//Si le dialogue est fini, on quitte la boucle
				break;
			}
		}
		if(text != null) {
			//Si on vient de commencer le dialogue, il faut creer la boite
			if(box == null) {				
				j.setMove(false);
				box = new DialogBox(text);
				stage.addActor(box);
			}
			//Si on poursuit la conversation, on modifie la boite deja existante
			else {
				box.setMessage(text);
			}
		}
		else if(box != null){
			//Sinon, on finit la conversation
			j.setMove(true);
			stage.clear();
			box.remove();
			box = null;
		}
	}
}
