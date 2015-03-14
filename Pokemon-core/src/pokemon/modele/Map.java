package pokemon.modele;

import java.util.Vector;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
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
	
	//Attributs des changements de Map
	private Vector<MapChange> mapChanges;
	
	//Constructeurs
	public Map() {
		tiledMap = new TmxMapLoader().load("map.tmx");
		npcs = new Vector<NPC>();
		mapChanges = new Vector<MapChange>();
		
		setMusique(new Music(tiledMap));
	}	
	public Map(String path) {
		TmxMapLoader loader = new TmxMapLoader();
		tiledMap = loader.load(path);
		npcs = new Vector<NPC>();
		mapChanges = new Vector<MapChange>();
		
		getTransitions();
		setMusique(new Music(tiledMap));
	}
	public Map(String path, NPCList npcList) {
		TmxMapLoader loader = new TmxMapLoader();
		tiledMap = loader.load(path);
		npcs = new Vector<NPC>();
		mapChanges = new Vector<MapChange>();
		
		getTransitions();
		getNPCs(npcList);
		setMusique(new Music(tiledMap));
	}
	
	//Fonction privees
	private void getNPCs(NPCList npcList) {
		for(MapObject o : tiledMap.getLayers().get("NPCs").getObjects()) {
			float x = Float.parseFloat(o.getProperties().get("x").toString());
			float y = Float.parseFloat(o.getProperties().get("y").toString());
			if(o.getProperties().containsKey("dialogs")) {
				NPC npc = new NPC(o.getProperties().get("dialogs").toString(), new Vector2(x, y));
				npcs.addElement(npc);
				npcList.addNPC(npc);
			}
			else {
				NPC npc = new NPC(new Vector2(x, y));
				npcs.addElement(npc);
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
				Vector2 dim = new Vector2();
				Vector2 targetPos = new Vector2();
				targetPos.x = Float.parseFloat(o.getProperties().get("dest_x", String.class));
				targetPos.y = Float.parseFloat(o.getProperties().get("dest_y", String.class));

				RectangleMapObject r = (RectangleMapObject) o;
				pos.x = r.getRectangle().x;
				pos.y = r.getRectangle().y;
				dim.x = r.getRectangle().width;
				dim.y = r.getRectangle().height;
				
				mapChanges.add(new MapChange(destMap, orientation, pos, dim, targetPos));
			}
		}
	}
	
	//Fonctionnalites principales
	public boolean collide(Vector2 nextPos, int spriteWidth, int spriteHeight) {
		TiledMapTileLayer layerCollision = (TiledMapTileLayer) tiledMap.getLayers().get(1);
		if(layerCollision.getCell((int)(nextPos.x/16f),(int)(nextPos.y/16f))!=null ||
				layerCollision.getCell((int)((nextPos.x+spriteWidth-5)/16f),(int)(nextPos.y/16f))!=null ||
				layerCollision.getCell((int)((nextPos.x+spriteWidth-5)/16f),(int)((nextPos.y+spriteHeight-5)/16f))!=null ||
				layerCollision.getCell((int)((nextPos.x)/16f),(int)((nextPos.y+spriteHeight-5)/16f))!=null) {
			return true;
		}
		Rectangle playerHitbox = new Rectangle(nextPos.x, nextPos.y, spriteWidth, spriteHeight-5);
		for(NPC npc : npcs) {
			Vector2 npcPos = npc.getPos();
			Rectangle npcHitbox = new Rectangle(npcPos.x, npcPos.y+16, 16, 16);
			if(playerHitbox.overlaps(npcHitbox)) {
				return true;
			}
		}
		return false;
	}
	/* Interagit avec le NPC de la position donnee
	 * Renvoie null si aucun NPC n'est present sur place.*/
	public String interact(float x, float y, NPCList npcList) {
		/*Vector2 targetPosition = new Vector2((float) x, (float) y);
		//On verifie si un NPC se trouve a la position cible
		//TODO
		for(int i = 0;i<npcsPosition.size();i++) {
			if(targetPosition.x == npcsPosition.get(i).x && targetPosition.y == npcsPosition.get(i).y) {			
				return npcs.get(i).executeDialog(npcList);
			}
		}*/
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
	public Vector<MapChange> getMapChanges() {
		return mapChanges;
	}
	public void setMapChanges(Vector<MapChange> mapChanges) {
		this.mapChanges = mapChanges;
	}
}
