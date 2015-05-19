package pokemon.modele;

import java.io.IOException;
import java.util.Vector;

import pokemon.annotations.Tps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

/*La classe NPC permet de stocker toutes les informatiosn relatives au comportement d'un PNJ, 
 * c'est a dire les lignes de texte que le personnage doit communiquer au joueur*/

@Tps(nbhours=6)
public class NPC {
	//Attributs d'interaction
	protected int id;
	protected int status; // determine quelle dialogue le PNJ doit reciter
	protected int newStatus;
	protected Vector<Dialog> dialogs;
	
	//Attributs de mouvement
	protected Direction orientation;
	protected Vector2 pos;
	protected float moveDistance;
	protected Direction moveDirection;
	
	//Constructeurs
	public NPC() {
		setStatus(0);
		setId(0);
		dialogs = new Vector<Dialog>();
		dialogs.add(new Dialog());
		
		setOrientation(Direction.South);
		pos = new Vector2(0, 0);
		setMoveDistance(0);
		setMoveDirection(Direction.Standing);
	}
	public NPC(String path) {
		setStatus(0);
		dialogs = new Vector<Dialog>();
		lireXML(path);
		
		setOrientation(Direction.South);
		pos = new Vector2(0, 0);
		setMoveDistance(0);
		setMoveDirection(Direction.Standing);
	}
	public NPC(Vector2 pos) {
		setStatus(0);
		dialogs = new Vector<Dialog>();
		dialogs.add(new Dialog());
		
		setOrientation(Direction.South);
		this.pos = new Vector2(pos);
		setMoveDistance(0);
		setMoveDirection(Direction.Standing);
	}
	public NPC(String path, Vector2 pos) {
		setStatus(0);
		dialogs = new Vector<Dialog>();
		lireXML(path);
		
		setOrientation(Direction.South);
		this.pos = new Vector2(pos);
		setMoveDistance(0);
		setMoveDirection(Direction.Standing);
	}
	public NPC(String path, Vector2 pos, int status) {
		setStatus(status);
		dialogs = new Vector<Dialog>();
		lireXML(path);
		
		setOrientation(Direction.South);
		this.pos = new Vector2(pos);
		setMoveDistance(0);
		setMoveDirection(Direction.Standing);
	}

	//Fonctionnalitees principales
	public void next() {
		dialogs.get(status).next();
	}
	
	public Dialog getCurrentDialog(){
		return dialogs.get(status);
	}
	
	public void setNewStatus(int s) {
		newStatus = s;
	}
	public void updatePosition() {
		if(moveDistance > 0) {
			float speed = 60;
			Vector2 targetPosition = new Vector2(getPos());
			
			switch(moveDirection) {
			case East:
				targetPosition.x += Gdx.graphics.getDeltaTime()*speed;
				break;
			case North:
				targetPosition.y += Gdx.graphics.getDeltaTime()*speed;
				break;
			case South:
				targetPosition.y -= Gdx.graphics.getDeltaTime()*speed;
				break;
			case West:
				targetPosition.x -= Gdx.graphics.getDeltaTime()*speed;
				break;
			default:
				break;
			}
			moveDistance -= Gdx.graphics.getDeltaTime()*speed;
			setPos(targetPosition);
			setOrientation(moveDirection);
		}
		else {
			setMoveDirection(Direction.Standing);
		}
	}
	//Fonction privee
	public void updateStatus() {
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
	
	public float getMoveDistance() {
		return moveDistance;
	}
	public void setMoveDistance(float moveDistance) {
		this.moveDistance = moveDistance;
	}
	public Direction getMoveDirection() {
		return moveDirection;
	}
	public void setMoveDirection(Direction moveDirection) {
		this.moveDirection = moveDirection;
	}
	
	//Fonctions privees
	protected Element getDialogs(String path) throws IOException {
		XmlReader reader = new XmlReader();
		Element dialog = null;
		Element instruction = null;
		
		//On recupere la racine, et l'id du NPC
		Element root = null;
		root = reader.parse(Gdx.files.internal("npcs/" + path + "/dialogs.xml"));
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
				
				//Si l'instruction est un changement de status
				else if (instruction.getName().compareTo("status") == 0) {
					int id = instruction.getInt("npc");
					int value = instruction.getInt("value");
					
					InstructionStatus newInst = new InstructionStatus(value, id);
					newDialog.addInstruction(newInst);
				}
				
				//Si l'instruction est un mouvement
				else if(instruction.getName().compareTo("move") == 0) {
					int id = instruction.getInt("id");
					Direction dir = Direction.valueOf(instruction.get("dir"));
					int dist = instruction.getInt("distance");
					
					InstructionMouvement newInst = new InstructionMouvement(id, dir, dist);
					newDialog.addInstruction(newInst);
				}
				else if(instruction.getName().compareTo("battle") == 0) {
					int id = instruction.getInt("with");
					InstructionCombat newInst = new InstructionCombat(id);
					newDialog.addInstruction(newInst);
				}
			}
			dialogs.addElement(newDialog);
		}
		
		return root;
	}
	protected void lireXML(String path) {
		try {
			getDialogs(path);
		} catch (IOException e) {
			e.printStackTrace();
			dialogs.add(new Dialog());
		}
	}
	public Vector2 getDimensions() {
		return new Vector2(16, 16);
	}
}
