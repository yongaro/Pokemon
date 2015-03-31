package pokemon.modele;

import java.io.IOException;
import java.util.Vector;

import pokemon.annotations.Tps;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlReader.Element;

/* La classe Dresseur (herite de NPC) decrit le modele d'un dresseur
 * ainsi que son equipe de pokemon, lue depuis un fichier xml */

@Tps(nbhours=3)
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
	
	@Override
	public String executeDialog(Joueur j, NPCList npcList) throws NoMoreInstructionException, CombatException {
		try {
			return dialogs.get(status).execute(npcList, j);
		} catch (NoMoreInstructionException e) {
			updateStatus();
			throw e;
		} catch (CombatException e) {
			e.setDresseur(this);
			throw e;
		}
	}
	
	//Fonctions privees
	@Override
	protected void lireXML(String path) {
		System.out.println("Dresseur");
		try {
			//On recupere les dialogues
			Element root = getDialogs(path);
			
			//On recupere l'equipe
			Element equipe = root.getChildByName("equipe");
			if(equipe != null) {
				team = new Vector<Pkm>();
				for(int j = 0;j<equipe.getChildCount();j++) {
					Element pokemonElt = equipe.getChild(j);
					int id = pokemonElt.getInt("id");
					int lvl = pokemonElt.getInt("niveau");
					
					Pkm pokemon = new Pkm(Pokedex.values()[id-1].get(), lvl);
					System.out.println(pokemon.nom);
					for(int k = 0;k<pokemonElt.getChildCount();k++) {
						Element cap = pokemonElt.getChild(k);
						pokemon.add(bddCapacite.valueOf(cap.getAttribute("nom")).get());
						System.out.println("Ajout de " + cap.getAttribute("nom"));
					}
					addPkm(pokemon);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			dialogs.add(new Dialog());
		}
	}
}
