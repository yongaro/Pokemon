package pokemon.launcher;

import java.util.Vector;

import pokemon.annotations.Tps;
import pokemon.controle.Cinematique;
import pokemon.controle.DeplacementNPC;
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
    private DeplacementNPC movingNPC = null;
    
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
				//... et si elle est finie, on l'enleve.
				cinematique = null;
				if(game.getScreen()==this){
					Gdx.input.setInputProcessor(controller);
				}
			}
		}
		
		//On verifie si le joueur est aggro par un PNJ
		updateAutoCutscene();
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
		//Si il n'y a pas deja de boite de dialogue...
		if(box == null) {
			//... on en cree une avec le texte fourni
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
			//... on l'enleve
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

	public NPCVue getNPCVueById(int id) {
		for(NPCVue npcv : npcs) {
			if(npcv.getNPCId() == id) {
				return npcv;
			}
		}
		return null;
	}

	
	//Fonctions privees
	private void updateNPCs() {
		for(NPC npc : j.getCurrentMap().getNpcs()) {
			NPCVue npcvue = new NPCVue(npc);
			npcs.add(npcvue);
		}
	}
	
	private void updateAutoCutscene() {
		if(movingNPC == null && cinematique == null) {			
			//Si le joueur n'est pas deja en train de parler a quelqu'un d'autre
			detectBattle();
		}
		else if(movingNPC != null && cinematique == null){
			//Sinon, on update le deplacement du personnage qui veut parler au joueur
			movingNPC.update();
			//Si le personnage a fini de se deplacer, on declenche la cinematique
			if(movingNPC.isDoneMoving()) {
				//On arrete l'animation du personnage, et on force l'interaction.
				movingNPC.npc.move(movingNPC.moveDirection, 0, 0);
				cinematique = new Cinematique(this, movingNPC.getNPC().getNPC());
				Gdx.input.setInputProcessor(cinematique.getController());
			}
		}
	}
	
	private void detectBattle() {
		//Pour chaque NPC de la map, on cherche si le joueur est dans la portee
		for(NPCVue npc : npcs) {
			Direction dir = npc.getOrientation();
			Vector2 pos = npc.getPos();
			Vector2 dim = npc.getDimensions();
			
			//On construit la zone d'interaction du personnage
			Rectangle interactRegion = getInteractRegion(pos, dim, dir);	
			
			//On verifie si le joueur est dans cette zone
			Rectangle playerHitbox = new Rectangle(j.getPos().x, j.getPos().y-16, j.getDimensions().x, j.getDimensions().y - 5);
			if(interactRegion.overlaps(playerHitbox))
			{
				//On fait se déplacer le NPC vers le joueur
				float moveDistance = 0;
				switch(npc.getOrientation()) {
				case East:
					moveDistance = j.getPos().x - npc.getPos().x - npc.getDimensions().x;
					j.setOrientation(Direction.West);
					break;
				case North:
					moveDistance = j.getPos().y-16 - npc.getPos().y - npc.getDimensions().y;
					j.setOrientation(Direction.South);
					break;
				case South:
					moveDistance = npc.getPos().y - j.getPos().y;
					j.setOrientation(Direction.North);
					break;
				case West:
					moveDistance = npc.getPos().x - j.getPos().x - npc.getDimensions().x;
					j.setOrientation(Direction.East);
					break;
				default:
					break;
				}
				System.out.println(moveDistance);
				//On commence le deplacement du personnage
				movingNPC = new DeplacementNPC(npc, dir, moveDistance);
				//On arrete le joueur
				j.stop();
			}
		}
	}
	
	private Rectangle getInteractRegion(Vector2 pos, Vector2 dim, Direction dir){
		float dist = 100; // valeur a modifier
		float thickness = 2;
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
