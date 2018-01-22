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
			//PV
			p.stats[2][0] = p.stats[2][1];
			
			//PP
			for(UniteStockage<Capacite> cap : p.cap) {
				cap.quantite = cap.quantitemax;
			}
			
			//Status
			p.statut = Statut.Normal;
		}
	}
}
