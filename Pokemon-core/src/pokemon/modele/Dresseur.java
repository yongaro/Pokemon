package pokemon.modele;

import java.io.IOException;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

/* La classe Dresseur (herite de NPC) decrit le modele d'un dresseur
 * ainsi que son equipe de pokemon, lue depuis un fichier xml */

public class Dresseur extends NPC {
	private Vector<Pkm> team;
	
	//Constructeurs
	public Dresseur() {
		super();
	}
	public Dresseur(String path) {
		super(path);
	}
	public Dresseur(Vector2 pos) {
		super(pos);
	}
	public Dresseur(String path, Vector2 pos) {
		super(path, pos);
	}
	public Dresseur(String path, Vector2 pos, int status) {
		super(path, pos, status);
	}
	
	//Fonctionnalitees principales
	public void addPkm(Pkm p) {
		if(team.size()<6){
			team.addElement(p);
		}
	}
	public void removePkm(Pkm p) {
		for(int i = 0;i<team.size();i++) {
			if(team.get(i) == p) {
				team.remove(i);
			}
		}
	}
	public void removePkm(String s) {
		for(int i = 0;i<team.size();i++) {
			if(team.get(i).nom.compareTo(s) == 0) {
				team.remove(i);
			}
		}
	}
	public void removePkm(int i) {
		team.remove(i);
	}
	
	public Vector<Pkm> getTeam() {
		return team;
	}
	
	//Fonctions privees
	@Override
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
			
			//On recupere l'equipe
			Element equipe = root.getChildByName("equipe");
			if(equipe != null) {
				team = new Vector<Pkm>();
				for(int j = 0;j<equipe.getChildCount();j++) {
					Element pokemonElt = equipe.getChild(j);
					int id = pokemonElt.getInt("id");
					int lvl = pokemonElt.getInt("niveau");
					
					Pkm pokemon = new Pkm(Pokedex.values()[id-1].get(), lvl);
//					System.out.println(pokemon.nom);
					for(int k = 0;k<pokemonElt.getChildCount();k++) {
						Element cap = pokemonElt.getChild(k);
						pokemon.add(bddCapacite.valueOf(cap.getAttribute("nom")).get());
//						System.out.println("Ajout de " + cap.getAttribute("nom"));
					}
					addPkm(pokemon);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
