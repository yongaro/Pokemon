package pokemon.modele;

import java.io.IOException;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public class Dresseur extends NPC {
	private Vector<Pkm> team = new Vector<Pkm>();
	
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
			if(team.get(i).nom.equals(s)) {
				team.remove(i);
			}
		}
	}
	public void removePkm(int i) {
		team.remove(i);
	}
	
	//Fonctions privees
	@Override
	protected void lireXML(String path) {
		XmlReader reader = new XmlReader();
		Element temp = null;
		try {
			//On recupere la racine, et l'id du NPC
			Element root = reader.parse(Gdx.files.internal(path));
			id = root.getInt("id");
			
			//On parcourt chaque dialogues
			for(int i = 0;i<root.getChildCount();i++) {
				temp = root.getChild(i);
				
				if(temp.getName() == "dialogue") {					
					Element text = temp.getChildByName("text");
					Element status = temp.getChildByName("status");
					int newStatus = 0, target = 0;
					if(status != null) {
						newStatus = status.getInt("value");
						target = status.getInt("npc");
					}
					dialogs.addElement(new Dialog(text.getText(), target, newStatus));
				}
				//On recupere l'equipe
				else if(temp.getName() == "equipe") {
					for(int j = 0;j<temp.getChildCount();j++) {
						Element pokemonElt = temp.getChild(j);
						int id = pokemonElt.getInt("id");
						int lvl = pokemonElt.getInt("niveau");
						Pkm pokemon = new Pkm(Pokedex.values()[id-1].get(), lvl);
						System.out.println(pokemon.nom);
						for(int k = 0;k<pokemonElt.getChildCount();k++) {
							Element cap = pokemonElt.getChild(k);
							pokemon.add(bddCapacite.valueOf(cap.getAttribute("nom")).get());
							System.out.println("Ajout de " + cap.getAttribute("nom"));
						}
						team.add(pokemon);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
