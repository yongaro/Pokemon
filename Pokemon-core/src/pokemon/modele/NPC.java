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
	protected int newStatus;
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

	//Fonctionnalitees principales
	public String executeDialog(Joueur j, NPCList npcList) throws NoMoreInstructionException {
		try {
			return dialogs.get(status).execute(npcList, j);
		} catch (NoMoreInstructionException e) {
			updateStatus();
			throw new NoMoreInstructionException();
		}
	}
	public void setNewStatus(int s) {
		newStatus = s;
	}
	
	//Fonction privee
	private void updateStatus() {
		status = newStatus;
	}
	
	//Accesseurs	
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
		Element dialog = null;
		Element instruction = null;
		try {
			//On recupere la racine, et l'id du NPC
			Element root = reader.parse(Gdx.files.internal("npcs/" + path + "/dialogs.xml"));
			id = root.getInt("id");
			
			//On parcourt chaque dialogues
			for(int i = 0;i<root.getChildrenByName("dialogue").size;i++) {
				dialog = root.getChildrenByName("dialogue").get(i);
				Dialog newDialog = new Dialog();
				
				//On parcourt chaque instruction du dialogue
				for(int j = 0;j<dialog.getChildCount();j++) {
					instruction = dialog.getChild(j);
					
					//Si l'instruction est un texte a afficher
					if(instruction.getName().compareTo("text") == 0) {
						InstructionTexte newInst = new InstructionTexte(instruction.getText());
						newDialog.addInstruction(newInst);
					}
					else if (instruction.getName().compareTo("status") == 0) {
						int id = instruction.getInt("npc");
						int value = instruction.getInt("value");
						InstructionStatus newInst = new InstructionStatus(value, id);
						newDialog.addInstruction(newInst);
					}
				}
				dialogs.addElement(newDialog);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
