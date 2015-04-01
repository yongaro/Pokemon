package pokemon.launcher;

import java.util.Vector;

import pokemon.annotations.Tps;
import pokemon.controle.JoueurController;
import pokemon.modele.ChangeMapException;
import pokemon.modele.Combat;
import pokemon.modele.CombatException;
import pokemon.modele.Direction;
import pokemon.modele.Joueur;
import pokemon.modele.MovementException;
import pokemon.modele.NPC;
import pokemon.modele.NoMoreInstructionException;
import pokemon.vue.CombatV;
import pokemon.vue.DialogBox;
import pokemon.vue.JoueurVue;
import pokemon.vue.NPCVue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

@Tps(nbhours=10)
public class MapScreen implements Screen{
	
	private MyGdxGame game;
	
	//Attributs joueur
	private Joueur j=MyGdxGame.Jtest;
	private JoueurVue joueur= new JoueurVue(j);
	private JoueurController controller;
	
	//Attributs NPCs
	private Vector<NPCVue> npcs = new Vector<NPCVue>();
	
	//Attributs affichage
	private int width=640;//Gdx.graphics.getWidth();
	private int height=360;//Gdx.graphics.getHeight();
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera cam;

    //Attributs cinematiques
	private NPC talkingNPC;
	private Stage stage;
    private DialogBox box = null;
    
    //Attribut sonore
    private Music music = Gdx.audio.newMusic(Gdx.files.internal(j.getCurrentMap().getMusique().getPath()));

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
		
		//Rendu des NPCs
		for(NPCVue npc : npcs) {
			npc.render(renderer.getBatch(), delta);
		}
		
		//Rendu du joueur
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
		updateNPCs();
		
		//Generation du Stage
		stage = new Stage(new FitViewport(width,height,cam));
		
		//Definition de l'input
		Gdx.input.setInputProcessor(controller);
		
		//Musique
		music.setVolume(0.3f);
		music.setLooping(true);
		playMusic(0);
	}
	public void update(float delta)
	{
		//On met a jour la position du joueur.
		try {
			joueur.updatePosition(renderer);
		} catch (ChangeMapException e) {
			npcs.clear();
			updateNPCs();
		}
		
		//On met a jour la position des NPC
		for(NPC npc : j.getCurrentMap().getNpcs()) {
			npc.updatePosition();
		}
		
		//On met a jour la cinematique en cas de mouvement de personnage.
		if(talkingNPC != null && box == null) {
			updateCutscene(j);
		}
	}
	
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	public void dispose() {
		music.dispose();
		
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
	public void updateCutscene(Joueur j) {
		//Si aucune cinematique est en train d'etre jouee ...
		if(talkingNPC == null) {
			//... on recupere le NPC cible.
			talkingNPC = j.getCurrentMap().getNPC(j);
			switch(j.getOrientation()) {
			case East:
				talkingNPC.setOrientation(Direction.West);
				break;
			case North:
				talkingNPC.setOrientation(Direction.South);
				break;
			case South:
				talkingNPC.setOrientation(Direction.North);
				break;
			case West:
				talkingNPC.setOrientation(Direction.East);
				break;
			default:
				break;
			
			}
		}
		if(talkingNPC != null && talkingNPC.getMoveDistance() <= 0) {
			String textToDisplay = null;
			boolean isCutsceneEnded = false;
			//Tant qu'on a pas de texte ...
			while(textToDisplay == null) {
				try {
					//... on exectue le dialogue du NPC.
					textToDisplay = j.interact(talkingNPC, MyGdxGame.npcList);
				} catch (NoMoreInstructionException e) {
					//Si le dialogue est fini, on quitte la boucle.
					isCutsceneEnded = true;
					break;
				} catch (MovementException e) {
					//Si un personnage doit bouger, on detruit la boite de dialogue (si il y en a une).
					if(box != null) {
						stage.clear();
						box.remove();
						box = null;
					}
					//Enfin, on sort de la boucle.
					break;
				} catch (CombatException e) {
					//Si le dresseur a une equipe...
					if(e.getDresseur()!= null) {
						//... on lance un combat
						music.stop();
						Combat c = new Combat(j, e.getDresseur());
						c.start();
						game.setScreen(new CombatV(c));
					}
				}
			}
			//Si on a un texte a afficher ...
			if(textToDisplay != null) {
				//... alors si il n'y a pas de boite ...
				if(box == null) {
					//... alors on cree une nouvelle boite, et on met le joueur en mode cinematique.
					j.setMove(false);
					box = new DialogBox(textToDisplay);
					stage.addActor(box);
				}
				//... sinon ...
				else {
					//... on met a jour la boite existante.
					box.setMessage(textToDisplay);
				}
			}
			//... sinon, si aucun texte n'est a afficher ...
			else if(box != null && isCutsceneEnded) {
				//... on detruit la boite.
				j.setMove(true);
				stage.clear();
				box.remove();
				box = null;
				talkingNPC = null;
			}
		}
	}
	
	//Fonctions privees
	private void updateNPCs() {
		for(NPC npc : j.getCurrentMap().getNpcs()) {
			NPCVue npcvue = new NPCVue(npc);
			npcs.add(npcvue);
		}
	}
	
	//Fonctions de la musique
	public float getMusicPosition() {
		return music.getPosition();
	}
	public void playMusic(float pos) {
		music.setPosition(pos);
		music.play();
	}
	public void stopMusic() {
		music.stop();
	}
}
