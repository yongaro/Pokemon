package pokemon.modele;

import java.util.Vector;

import pokemon.annotations.Tps;

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
 * objets present sur la map */

@Tps(nbhours=10)
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
		MapLayer objectLayer = tiledMap.getLayers().get("NPCs");
		if(objectLayer != null) {			
			for(MapObject o : objectLayer.getObjects()) {
				NPC npc = null;
				float x = Float.parseFloat(o.getProperties().get("x").toString());
				float y = Float.parseFloat(o.getProperties().get("y").toString());
				if(o.getProperties().containsKey("name")) {
					if(o.getProperties().containsKey("trainer")) {
						npc = new Dresseur(o.getProperties().get("name").toString(), new Vector2(x, y));
					}
					else {						
						npc = new NPC(o.getProperties().get("name").toString(), new Vector2(x, y));
					}
					npcs.addElement(npc);
					npcList.addNPC(npc);
				}
				else {
					npc = new NPC(new Vector2(x, y));
					npcs.addElement(npc);
					npcList.addNPC(npc);
				}
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
		//Collision avec les tiles
		TiledMapTileLayer layerCollision = (TiledMapTileLayer) tiledMap.getLayers().get(1);
		if(layerCollision.getCell((int)(nextPos.x/16f),(int)(nextPos.y/16f))!=null ||
				layerCollision.getCell((int)((nextPos.x+spriteWidth-5)/16f),(int)(nextPos.y/16f))!=null ||
				layerCollision.getCell((int)((nextPos.x+spriteWidth-5)/16f),(int)((nextPos.y+spriteHeight-5)/16f))!=null ||
				layerCollision.getCell((int)((nextPos.x)/16f),(int)((nextPos.y+spriteHeight-5)/16f))!=null) {
			return true;
		}
		
		//Collision avec les NPC
		Rectangle playerHitbox = new Rectangle(nextPos.x, nextPos.y, spriteWidth, spriteHeight-5);
		for(NPC npc : npcs) {
			Vector2 npcPos = npc.getPos();
			Rectangle npcHitbox = new Rectangle(npcPos.x+1, npcPos.y+16, 13, 19);
			if(playerHitbox.overlaps(npcHitbox)) {
				return true;
			}
		}
		return false;
	}
	/* Interagit avec le NPC de la position donnee
	 * Renvoie null si aucun NPC n'est present sur place.*/
//	public String interact(Joueur j, Vector2 target, NPCList npcList) throws NoMoreInstructionException, NoNPCException, MovementException {
//		for(NPC npc : npcs) {
//			Rectangle npcHitbox = new Rectangle(npc.getPos().x, npc.getPos().y+16, 16, 16);
//			if(npcHitbox.contains(target)) {
//				switch(j.orientation) {
//				case East:
//					npc.setOrientation(Direction.West);
//					break;
//				case North:
//					npc.setOrientation(Direction.South);
//					break;
//				case South:
//					npc.setOrientation(Direction.North);
//					break;
//				case West:
//					npc.setOrientation(Direction.East);
//					break;
//				default:
//					break;			
//				}
//				String res = npc.executeDialog(j, npcList);
//				if(npc.getMoveDistance() > 0) {
//					throw new MovementException();
//				}
//				return res;
//			}
//		}
//		throw new NoNPCException();
//	}
	
	//Fait interagir le joueur j avec le NPC npc
	public String interact(Joueur j, NPC npc, NPCList list) throws NoMoreInstructionException {
		return npc.executeDialog(j, list);
	}
	//Renvoie le NPC devant le joueur. Si il y en a pas, renvoie null.
	public NPC getNPC(Joueur j) {
		//On determine le point cible
		Vector2 target = new Vector2();
		Vector2 center = new Vector2();
		center.x = j.getPos().x + (14/2);
		center.y = j.getPos().y + (14/2); //Car spriteHeight - 5 = spriteWidth
		int range = 12;
		switch(j.getOrientation()) {
		case East:
			target.x = center.x + range;
			target.y = center.y;
			break;
		case North:
			target.x = center.x;
			target.y = center.y + range;
			break;
		case South:
			target.x = center.x;
			target.y = center.y - range;
			break;
		case West:
			target.x = center.x - range;
			target.y = center.y;
			break;
		default:
			break;	
		}
		
		//Pour chaque NPC de la map...
		for(NPC npc : npcs) {
			//... on vérifie si un NPC se trouve au point cible ...
			Rectangle npcHitbox = new Rectangle(npc.getPos().x, npc.getPos().y+16, 16, 16);
			if(npcHitbox.contains(target)) {
				//... et on renvoie le NPC en question.
				return npc;
			}
		}
		//Sinon, on renvoie null.
		return null;
	}
	public void addNPC(NPC npc, NPCList npcList) {
		npcs.addElement(npc);
		npcList.addNPC(npc);
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
	public Vector<NPC> getNpcs() {
		return npcs;
	}
}
