package pokemon.modele;

import java.util.Vector;

import com.badlogic.gdx.math.Vector2;

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
	public Dresseur(Vector2 pos) {
		super(pos);
		team = new Vector<Pkm>();
	}
	public Dresseur(String path, Vector2 pos) {
		super(path, pos);
		team = new Vector<Pkm>();
	}
	public Dresseur(String path, Vector2 pos, int status) {
		super(path, pos, status);
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
