package pokemon.modele;

import java.io.IOException;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

/*La classe NPC permet de stocker toutes les informatiosn relatives au comportement d'un PNJ, 
 * c'est a� dire les lignes de texte que le personnage doit communiquer au joueur*/

public class NPC {
	//status determine quelle ligne de dialogue le PNJ doit reciter
	protected int id;
	protected int status;
	protected Direction orientation;
	protected Vector<Dialog> dialogs;
	protected Vector2 pos;
	
	//Constructeurs
	public NPC() {
		setStatus(0);
		setId(0);
		setOrientation(Direction.South);
		dialogs = new Vector<Dialog>();
		dialogs.add(new Dialog());
		pos = new Vector2(0, 0);
	}
	public NPC(String path) {
		setStatus(0);
		setOrientation(Direction.South);
		dialogs = new Vector<Dialog>();
		pos = new Vector2(0, 0);
		lireXML(path);
	}
	public NPC(Vector2 pos) {
		setStatus(0);
		setOrientation(Direction.South);
		dialogs = new Vector<Dialog>();
		dialogs.add(new Dialog());
		this.pos = new Vector2(pos);
	}
	public NPC(String path, Vector2 pos) {
		setStatus(0);
		setOrientation(Direction.South);
		dialogs = new Vector<Dialog>();
		this.pos = new Vector2(pos);
		lireXML(path);
	}
	public NPC(String path, Vector2 pos, int status) {
		setStatus(status);
		setOrientation(Direction.South);
		dialogs = new Vector<Dialog>();
		this.pos = new Vector2(pos);
		lireXML(path);
	}

	//Accesseurs
	public String executeDialog(Joueur j, NPCList npcList) {
		return dialogs.get(status).execute(npcList);
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Direction getOrientation() {
		return orientation;
	}
	public void setOrientation(Direction orientation) {
		this.orientation = orientation;
	}
	public Vector2 getPos() {
		return pos;
	}
	public void setPos(Vector2 pos) {
		this.pos = pos;
	}
	//Fonctions privees
	protected void lireXML(String path) {
		XmlReader reader = new XmlReader();
		Element temp = null;
		try {
			//On recupere la racine, et l'id du NPC
			Element root = reader.parse(Gdx.files.internal(path));
			id = root.getInt("id");
			
			//On parcourt chaque dialogues
			for(int i = 0;i<root.getChildrenByName("dialogue").size;i++) {
				temp = root.getChildrenByName("dialogue").get(i);
				
//				System.out.println("Nouveau dialogue");
				Element text = temp.getChildByName("text");
				Element status = temp.getChildByName("status");
				int newStatus = 0, target = 0;
				if(status != null) {
					newStatus = status.getInt("value");
					target = status.getInt("npc");
				}
				dialogs.addElement(new Dialog(text.getText(), target, newStatus));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
