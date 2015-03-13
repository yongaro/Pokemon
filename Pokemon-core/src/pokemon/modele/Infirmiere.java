package pokemon.modele;

import com.badlogic.gdx.math.Vector2;

public class Infirmiere extends NPC {
	public Infirmiere() {
		super();
	}
	public Infirmiere(String path) {
		super(path);
	}
	public Infirmiere(Vector2 pos) {
		super(pos);
	}
	public Infirmiere(String path, Vector2 pos) {
		super(path, pos);
	}
	public Infirmiere(String path, Vector2 pos, int status) {
		super(path, pos, status);
	}
	
	//Fonctionnalites principales
	public void soignerEquipe(Pkm[] equipe) {
		for(Pkm p : equipe) {
			//Normalement, c'est cette formule
			p.stats[2][0] = p.stats[2][1];
		}
	}
}
