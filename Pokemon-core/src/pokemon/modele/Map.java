package pokemon.modele;

import java.util.Vector;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;

/* La classe Map permet de regrouper toutes les informations
 * concernant une map, notemment sa TiledMap, ainsi que tout les
 * objets présent sur la map */

public class Map {
	//Proprietes de la Map
	private TiledMap tiledMap;
	private Music music;
	
	//Attributs des NPC
	private Vector<NPC> npcs;
	private Vector<Vector2> npcsPosition;
	
	//Attributs des changements de Map
	private Vector<MapChange> mapChanges;
	
	//Constructeurs
	public Map() {
		tiledMap = new TmxMapLoader().load("map.tmx");
		npcs = new Vector<NPC>();
		npcsPosition = new Vector<Vector2>();
		mapChanges = new Vector<MapChange>();
		
		setMusique(new Music(tiledMap));
	}	
	public Map(String path) {
		TmxMapLoader loader = new TmxMapLoader();
		tiledMap = loader.load(path);
		npcs = new Vector<NPC>();
		npcsPosition = new Vector<Vector2>();
		mapChanges = new Vector<MapChange>();
		
		getTransitions();
		setMusique(new Music(tiledMap));
	}
	public Map(String path, NPCList npcList) {
		TmxMapLoader loader = new TmxMapLoader();
		tiledMap = loader.load(path);
		npcs = new Vector<NPC>();
		npcsPosition = new Vector<Vector2>();
		mapChanges = new Vector<MapChange>();
		
		getTransitions();
		getNPCs(npcList);
		setMusique(new Music(tiledMap));
	}
	
	//Fonction privees
	private void getNPCs(NPCList npcList) {
		for(MapObject o : tiledMap.getLayers().get("NPCs").getObjects()) {
			float x = Float.parseFloat(o.getProperties().get("x").toString())/16;
			float y = Float.parseFloat(o.getProperties().get("y").toString())/16;
			if(o.getProperties().containsKey("dialogs")) {
				NPC npc = new NPC(o.getProperties().get("dialogs").toString());
				addNPC((int) x, (int) y , npc);
				npcList.addNPC(npc);
			}
			else {
				NPC npc = new NPC();
				addNPC((int) x, (int) y , npc);
				npcList.addNPC(npc);
			}
		}
	}
	private void getTransitions() {
		MapLayer objectLayer = tiledMap.getLayers().get("Regions");
		if(objectLayer != null)
		{
			for(MapObject o : objectLayer.getObjects()) {
				String destMap = o.getProperties().get("map", String.class);
				String orientationStr = o.getProperties().get("orientation", String.class);
				Direction orientation = Direction.toDirection(orientationStr);
				Vector2 pos = new Vector2();
				Vector2 targetPos = new Vector2();
				
				pos.x = o.getProperties().get("x", Float.class);
				pos.y = o.getProperties().get("y", Float.class);
				targetPos.x = Float.parseFloat(o.getProperties().get("dest_x", String.class));
				targetPos.y = Float.parseFloat(o.getProperties().get("dest_y", String.class));
				
				mapChanges.add(new MapChange(destMap, orientation, pos, targetPos));
			}
		}
	}
	
	//Fonctionnalites principales
	//Ajoute un NPC sur la map
	public void addNPC(int x, int y, NPC npc) {
		npcsPosition.add(new Vector2((int) x, (int) y));
		npcs.add(npc);
	}
	//V�rifie si un NPC est pr�sent sur la position donn�e.
	public boolean isNPCPresent(int x, int y) {
		Vector2 targetPosition = new Vector2((float) x, (float) y);
		//On v�rifie si un NPC se trouve � la position cible
		for(int i = 0;i<npcsPosition.size();i++) {
			if(targetPosition.x == npcsPosition.get(i).x && targetPosition.y == npcsPosition.get(i).y) {			
				return true;
			}
		}
		return false;
	}
	/* Int�ragit avec le NPC de la position donn�e
	 * Renvoie null si aucun NPC n'est pr�sent sur place.*/
	public String interact(float x, float y, NPCList npcList) {
		Vector2 targetPosition = new Vector2((float) x, (float) y);
		//On v�rifie si un NPC se trouve � la position cible
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
