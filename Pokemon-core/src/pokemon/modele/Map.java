package pokemon.modele;

import java.util.Vector;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;

/* La classe Map permet de regrouper toutes les informations
 * concernant une map, notemment sa TiledMap, ainsi que tout les
 * objets prÃ©sent sur la map */

public class Map {
	//Propriétés de la Map
	private TiledMap tiledMap;
	private Music music;
	
	//Attributs des NPC
	private Vector<NPC> npcs;
	private Vector<Vector2> npcsPosition;
	
	//Constructeurs
	public Map() {
		tiledMap = new TmxMapLoader().load("map.tmx");
		npcs = new Vector<NPC>();
		npcsPosition = new Vector<Vector2>();
		
		setMusique(new Music(tiledMap));
	}	
	public Map(String path) {
		TmxMapLoader loader = new TmxMapLoader();
		tiledMap = loader.load(path);
		npcs = new Vector<NPC>();
		npcsPosition = new Vector<Vector2>();
		
		setMusique(new Music(tiledMap));
	}

	//Fonctionnalités principales
	//Ajoute un NPC sur la map
	public void addNPC(int x, int y, NPC npc) {
		npcsPosition.add(new Vector2((int) x, (int) y));
		npcs.add(npc);
	}
	//Vérifie si un NPC est présent sur la position donnée.
	public boolean isNPCPresent(int x, int y) {
		Vector2 targetPosition = new Vector2((float) x, (float) y);
		//On vérifie si un NPC se trouve à la position cible
		for(int i = 0;i<npcsPosition.size();i++) {
			if(targetPosition.x == npcsPosition.get(i).x && targetPosition.y == npcsPosition.get(i).y) {			
				return true;
			}
		}
		return false;
	}
	/* Intéragit avec le NPC de la position donnée
	 * Renvoie null si aucun NPC n'est présent sur place.*/
	public String interact(int x, int y, NPCList npcList) {
		Vector2 targetPosition = new Vector2((float) x, (float) y);
		//On vérifie si un NPC se trouve à la position cible
		for(int i = 0;i<npcsPosition.size();i++) {
			if(targetPosition.x == npcsPosition.get(i).x && targetPosition.y == npcsPosition.get(i).y) {			
				return npcs.get(i).executeDialog(npcList);
			}
		}
		return null;
	}
	
	//Accesseurs
	public Music getMusique() {
		return music;
	}
	public void setMusique(Music music) {
		this.music = music;
	}
	public TiledMap getTiledMap() {
		return tiledMap;
	}
	public void setTiledMap(TiledMap tiledMap) {
		this.tiledMap = tiledMap;
	}
}
