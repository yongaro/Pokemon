package pokemon.modele;

import java.io.IOException;
import java.util.Vector;

import pokemon.annotations.Tps;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlReader.Element;

/* La classe Dresseur (herite de NPC) decrit le modele d'un dresseur
 * ainsi que son equipe de pokemon, lue depuis un fichier xml */

@Tps(nbhours=3)
public class Dresseur extends NPC implements CombatInfos {
	private boolean aggressive;
	private Vector<Pkm> team;
	
	//Constructeurs
	public Dresseur() {
		super();
		setAggressive(false);
	}
	public Dresseur(String path) {
		super(path);
		setAggressive(false);
	}
	public Dresseur(Vector2 pos) {
		super(pos);
		setAggressive(false);
	}
	public Dresseur(String path, Vector2 pos, boolean isAggressive) {
		super(path, pos);
		setAggressive(isAggressive);
	}
	public Dresseur(String path, Vector2 pos, int status, boolean isAggressive) {
		super(path, pos, status);
		setAggressive(isAggressive);
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
	
	public boolean asTeam() {
		return !team.isEmpty();
	}
	
	//Fonctions privees
	@Override
	protected void lireXML(String path) {
		
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
	
	public Pkm teamAt(int ind){ return this.team.elementAt(ind); }
	public Pkm[] Team(){ return (Pkm[])team.toArray(); }
	
	@Override
	public boolean isAggressive() {
		return aggressive && status == 0;
	}
	public void setAggressive(boolean aggressive) {
		this.aggressive = aggressive;
	}
}
