package pokemon.launcher;

import java.util.Vector;

import pokemon.annotations.Tps;
import pokemon.controle.Cinematique;
import pokemon.controle.JoueurController;
import pokemon.controle.MenuListener;
import pokemon.modele.ChangeMapException;
import pokemon.modele.Combat;
import pokemon.modele.Direction;
import pokemon.modele.Dresseur;
import pokemon.modele.Joueur;
import pokemon.modele.NPC;
import pokemon.vue.CombatV;
import pokemon.vue.DialogBox;
import pokemon.vue.GameScreen;
import pokemon.vue.JoueurVue;
import pokemon.vue.NPCVue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

@Tps(nbhours=10)
public class MapScreen extends GameScreen{
	
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
	private Cinematique cinematique = null;
	private Stage stage;
    private DialogBox box = null;
    
    //Attribut sonore
    private Music music;

    //Constructeurs
    public MapScreen(MyGdxGame game) {
		this.game = game;
		this.controller = new JoueurController(this, joueur);
		updateMusic();
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
		super.drawUI(delta);
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
		cam.zoom-=0.32;
		
		//Affichage des NPC
		updateNPCs();
		
		//Generation du Stage
		stage = new Stage(new FitViewport(width,height,cam));
		
		//Definition de l'input
		if(cinematique == null) {
			Gdx.input.setInputProcessor(controller);
		}
		else {
			Gdx.input.setInputProcessor(cinematique.getController());
		}
	}
	public void update(float delta)
	{
		//On met a jour la position du joueur.
		try {
			joueur.updatePosition(renderer);
		} catch (ChangeMapException e) {
			npcs.clear();
			updateNPCs();
			updateMusic();
		}
		
		//On met a jour la position des NPC
		for(NPC npc : j.getCurrentMap().getNpcs()) {
			npc.updatePosition();
		}
		
		//On met a jour la cinematique en cas de mouvement de personnage.
		if(cinematique != null) {
			//On update la cinematique...
			if(!cinematique.update()) {
				//... et si elle est finie, on l'enlève.
				cinematique = null;
				Gdx.input.setInputProcessor(controller);
			}
		}
		
		//On vérifie si le joueur est aggro par un PNJ
		detectBattle();
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
//		//Si aucune cinematique est en train d'etre jouee ...
//		if(talkingNPC == null) {
//			//... on recupere le NPC cible.
//			talkingNPC = j.getCurrentMap().getNPC(j);
//			if(talkingNPC != null)
//			{				
//				switch(j.getOrientation()) {
//				case East:
//					talkingNPC.setOrientation(Direction.West);
//					break;
//				case North:
//					talkingNPC.setOrientation(Direction.South);
//					break;
//				case South:
//					talkingNPC.setOrientation(Direction.North);
//					break;
//				case West:
//					talkingNPC.setOrientation(Direction.East);
//					break;
//				default:
//					break;
//					
//				}
//			}
//		}
//		if(talkingNPC != null && talkingNPC.getMoveDistance() <= 0) {
//			String textToDisplay = null;
//			boolean isCutsceneEnded = false;
//			//Tant qu'on a pas de texte ...
//			while(textToDisplay == null) {
//				try {
//					//... on exectue le dialogue du NPC.
//					textToDisplay = j.interact(talkingNPC, MyGdxGame.npcList);
//				} catch (NoMoreInstructionException e) {
//					//Si le dialogue est fini, on quitte la boucle.
//					isCutsceneEnded = true;
//					break;
//				} catch (MovementException e) {
//					//Si un personnage doit bouger, on detruit la boite de dialogue (si il y en a une).
//					if(box != null) {
//						stage.clear();
//						box.remove();
//						box = null;
//					}
//					//Enfin, on sort de la boucle.
//					break;
//				} catch (CombatException e) {
//					//Si le dresseur a une equipe...
//					if(e.getDresseur()!= null) {
//						//... on lance un combat
//						music.stop();
//						Combat c = new Combat(j, e.getDresseur());
//						c.start();
//						game.setScreen(new CombatV(c,game));
//					}
//				}
//			}
//			//Si on a un texte a afficher ...
//			if(textToDisplay != null) {
//				//... alors si il n'y a pas de boite ...
//				if(box == null) {
//					//... alors on cree une nouvelle boite, et on met le joueur en mode cinematique.
//					j.setMove(false);
//					box = new DialogBox(textToDisplay);
//					stage.addActor(box);
//				}
//				//... sinon ...
//				else {
//					//... on met a jour la boite existante.
//					box.setMessage(textToDisplay);
//				}
//			}
//			//... sinon, si aucun texte n'est a afficher ...
//			else if(box != null && isCutsceneEnded) {
//				//... on detruit la boite.
//				j.setMove(true);
//				stage.clear();
//				box.remove();
//				box = null;
//				talkingNPC = null;
//			}
//		}
		NPC talkingNPC = j.getCurrentMap().getNPC(j);
		if(talkingNPC != null) {
			//On change l'orientation du NPC
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
			cinematique = new Cinematique(this, talkingNPC);
			Gdx.input.setInputProcessor(cinematique.getController());
		}
	}
	
	public void popMenu() {
		new MenuListener(game, this);
	}
	
	public void updateMusic() {
		if(music != null) {			
			music.stop();
		}
		music = Gdx.audio.newMusic(Gdx.files.internal(j.getCurrentMap().getMusique().getPath()));
		music.setVolume(0.3f);
		music.setLooping(true);
		music.play();
	}
	
	public void addBox(String text) {
		//Si il n'y a pas déja de boite de dialogue...
		if(box == null) {
			//... on en crée une avec le texte fourni
			box = new DialogBox(text);
			stage.addActor(box);
		}
		else {
			box.setMessage(text);
		}
	}
	
	public void removeBox() {
		//Si une boite de dialogue existe ...
		if(box != null) {			
			//... on l'enlève
			System.out.println("Remove()");
			stage.clear();
			box.remove();
			box = null;
		}
	}
	
	public void startBattle(Dresseur dress) {
		music.stop();
		Combat c = new Combat(j, dress);
		c.start();
		game.setScreen(new CombatV(c,game));
	}
	
	public NPC getNPCById(int i) {
		return j.getCurrentMap().getNPCById(i);
	}
	
	//Fonctions privees
	private void updateNPCs() {
		for(NPC npc : j.getCurrentMap().getNpcs()) {
			NPCVue npcvue = new NPCVue(npc);
			npcs.add(npcvue);
		}
	}
	
	private void detectBattle() {
		//Pour chaque NPC de la map, on cherche si le joueur est dans la portée
		for(NPC npc : j.getCurrentMap().getNpcs()) {
			Direction dir = npc.getOrientation();
			Vector2 pos = npc.getPos();
			Vector2 dim = npc.getDimensions();
			
			//On construit la zone d'intéraction du personnage
			Rectangle interactRegion = getInteractRegion(pos, dim, dir);	
			
			//On vérifie si le joueur est dans cette zone
			Rectangle playerHitbox = new Rectangle(j.getPos().x, j.getPos().y-16, j.getDimensions().x, j.getDimensions().y - 5);
			if(interactRegion.overlaps(playerHitbox))
			{
//				System.out.println("NPC : " + pos);
//				System.out.println("Region : " + interactRegion);
				
				//On déclenche la cinématique de combat
				
			}
		}
	}
	
	private Rectangle getInteractRegion(Vector2 pos, Vector2 dim, Direction dir){
		float dist = 50; // valeur a modifier
		float thickness = 6;
		Rectangle interactRegion = new Rectangle();
		switch(dir)
		{
		case East:
			interactRegion.x = pos.x + dim.y;
			interactRegion.y = pos.y + (dim.y / 2) - (thickness / 2);
			interactRegion.width = dist;
			interactRegion.height = thickness;
			break;
		case North:
			interactRegion.x = pos.x + (dim.x / 2) - (thickness / 2);
			interactRegion.y = pos.y + dim.x;
			interactRegion.width = thickness;
			interactRegion.height = dist;
			break;
		case South:
			interactRegion.x = pos.x + (dim.x / 2) - (thickness / 2);
			interactRegion.y = pos.y - dist;
			interactRegion.width = thickness;
			interactRegion.height = dist;
			break;
		case West:
			interactRegion.x = pos.x - dist;
			interactRegion.y = pos.y + (dim.x / 2) - (thickness / 2);
			interactRegion.width = dist;
			interactRegion.height = thickness;
			break;
		default:
			interactRegion.x = pos.x + (dim.x / 2) - (thickness / 2);
			interactRegion.y = pos.y - dist;
			interactRegion.width = thickness;
			interactRegion.height = dist;
			break;
		}
		return interactRegion;
	}

}
