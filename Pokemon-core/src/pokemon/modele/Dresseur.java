package pokemon.modele;

import java.util.Vector;

public class Dresseur extends NPC {
	private Vector<Pkm> team;
	
	public Dresseur() {
		super();
		team = new Vector<Pkm>();
	}
	public Dresseur(String path) {
		super(path);
		team = new Vector<Pkm>();
	}
	public Dresseur(String path, int status) {
		super(path, status);
		team = new Vector<Pkm>();
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
}
