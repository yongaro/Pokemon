package pokemon.modele;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*La classe NPC permet de stocker toutes les informatiosn relatives au comportement d'un PNJ, 
 * c'est à dire les lignes de texte que le personnage doit communiquer au joueur*/

public class NPC {
	//status détermine quelle ligne de dialogue le PNJ doit réciter
	private int id;
	private int status;
	private Direction orientation;
	private Vector<Dialog> dialogs;
	
	//Constructeurs
	public NPC() {
		setStatus(0);
		setId(0);
		dialogs = new Vector<Dialog>();
	}
	public NPC(String path) {
		setStatus(0);
		dialogs = new Vector<Dialog>();
		lireXML(path);
	}
	public NPC(String path, int status) {
		setStatus(status);
		dialogs = new Vector<Dialog>();
		lireXML(path);
	}

	//Accesseurs
	public String executeDialog(NPCList npcList) {
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
	
	//Fonctions priv�es
	private void lireXML(String path) {
		//On met en place le parseur
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document = builder.parse(new File(path));
			
			//On r�cup�re la racine et l'id du PNJ
			final Element root = document.getDocumentElement();
			setId(Integer.parseInt(root.getAttribute("id")));
			
			//On r�cup�re les enfants de la racine, pour les mettre dans le Vector.
			final NodeList children = root.getChildNodes();
			final int nbChildren = children.getLength();
			for(int i = 0;i<nbChildren;i++) {
				if(children.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element dialog = (Element) children.item(i);
					
					//On r�cup�re le texte du dialogue
					String text = dialog.getElementsByTagName("text").item(0).getTextContent();
					
					//On r�cup�re les informations du changement de status
					Element statusChange = (Element) dialog.getElementsByTagName("status").item(0);
					//On v�rifie si le dialogue qu'on a contient un changement de status
					if(statusChange == null) {
						dialogs.add(new Dialog(text, 0, 0));
					}
					else {
						int newStatus = Integer.parseInt(statusChange.getAttribute("value"));
						int changingNPC = Integer.parseInt(statusChange.getAttribute("npc"));
						dialogs.add(new Dialog(text, changingNPC, newStatus));
					}
				}
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
