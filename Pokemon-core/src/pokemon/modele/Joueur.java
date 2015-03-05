package pokemon.modele;

import java.util.Vector;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;

//import pokemon.launcher.MyGdxGame;

/* La classe Joueur regroupe toute les informations concernant le joueur, son équipe
 * de Pokémon ainsi que son état (argent, badges...). 
 * Contient aussi la base de données afin de stocker les pokémons */

public class Joueur {
	//Attributs du dresseur
	protected int ID;
	protected String nom;
	protected int argent;
	protected int[] badges;
	protected Vector<Stockage<Objet>> inventaire; //  0 Medicaments | 1 Objets Rares | 2 CT/CM | 3 Pokeball | 4 Objets
	
	//Attributs de l'Ã©quipe de PokÃ©mon
	protected Pkm[] team;
	protected int teamsize;
	protected Vector<Stockage<Pkm>> boites;
	
	//Attributs concernant la position du joueur
	protected Vector2 pos;
	protected Direction orientation;
	protected Map currentMap;
	
	public Joueur(){
		ID=0; nom="TODO"; argent=2000; badges=new int[8];
		team=new Pkm[6]; teamsize=0;
		boites=new Vector<Stockage<Pkm>>();
		inventaire=new Vector<Stockage<Objet>>();
		
		pos = new Vector2(0f, 0f);
		orientation = Direction.South;
		currentMap = null;
	}
	public Joueur(int id,String nom){
		ID=id; this.nom=nom; argent=2000; badges=new int[8];
		team=new Pkm[6]; teamsize=0;
		boites=new Vector<Stockage<Pkm>>();
		inventaire=new Vector<Stockage<Objet>>();
		
		pos = new Vector2(0f, 0f);
		orientation = Direction.South;
		currentMap = null;
	}
	
	//Accesseurs
	public Vector2 getPos() {
		return pos;
	}
	public void setPos(Vector2 pos) {
		this.pos = pos;
	}
	public Map getCurrentMap() {
		return currentMap;
	}
	public void setCurrentMap(Map currentMap) {
		this.currentMap = currentMap;
	}
	public Pkm[] getTeam(){ return team; }
	public int teamSize(){ return teamsize;}
	
	//Ajoute le pokemon cible a l'equipe du dresseur
	public void add(Pkm cible){
		if(teamsize<6){
			team[teamsize]=cible;
			teamsize++;
		}
	}
	
	//enleve le pokemon cible a l'equipe du dresseur
	public void remove(Pkm cible){
		int ind=-1;
		for(int i=0;i<teamsize;i++){
			if(team[i]==cible){
				ind=i; teamsize--;
				break;
			}
		}
		if(ind!=-1){
			for(int i=ind;i<teamsize;i++){
				team[i]=team[i++];
			}
			team[teamsize+1]=null;
		}
	}
	
	public void teamSet(Pkm cible,int ind){ 
		team[ind]=cible;
	}
	
	public boolean move(Direction dir) {
		Cell targetPosition = null;
		orientation = dir;
		if(currentMap == null) {
			return false;
		}
		if(dir == Direction.North) {
			targetPosition = ( (TiledMapTileLayer) currentMap.getTiledMap().getLayers().get("Collide") ).getCell( (int) pos.x, (int) pos.y + 1);
			if(targetPosition == null && !currentMap.isNPCPresent( (int) pos.x, (int) pos.y + 1)) {
				pos.y++;
				return true;
			}
		}
		if(dir == Direction.South) {
			targetPosition = ( (TiledMapTileLayer) currentMap.getTiledMap().getLayers().get("Collide") ).getCell( (int) pos.x, (int) pos.y - 1);
			if(targetPosition == null && !currentMap.isNPCPresent( (int) pos.x, (int) pos.y - 1)) {
				pos.y--;
				return true;
			}
		}
		if(dir == Direction.West) {
			targetPosition = ( (TiledMapTileLayer) currentMap.getTiledMap().getLayers().get("Collide") ).getCell( (int) pos.x - 1, (int) pos.y);
			if(targetPosition == null && !currentMap.isNPCPresent( (int) pos.x - 1, (int) pos.y)) {
				pos.x--;
				return true;
			}
		}
		if(dir == Direction.East) {
			targetPosition = ( (TiledMapTileLayer) currentMap.getTiledMap().getLayers().get("Collide") ).getCell( (int) pos.x + 1, (int) pos.y );
			if(targetPosition == null && !currentMap.isNPCPresent( (int) pos.x + 1, (int) pos.y)) {
				pos.x++;
				return true;
			}
		}
		return false;
	}
	
	public String interact(NPCList npcList) {
		if(orientation == Direction.North) {
			return currentMap.interact((int) pos.x, (int) pos.y + 1, npcList);
		}
		else if(orientation == Direction.South) {
			return currentMap.interact((int) pos.x, (int) pos.y - 1, npcList);
		}
		else if(orientation == Direction.East) {
			return currentMap.interact((int) pos.x + 1, (int) pos.y, npcList);
		}
		else if(orientation == Direction.West) {
			return currentMap.interact((int) pos.x - 1, (int) pos.y, npcList);
		}
		return null;
	}
}
