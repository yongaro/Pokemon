package pokemon.modele;

public class Infirmiere extends NPC {
	public Infirmiere() {
		super();
	}
	public Infirmiere(String path) {
		super(path);
	}
	public Infirmiere(String path, int status) {
		super(path, status);
	}
	
	//Fonctionnalites principales
	public void soignerEquipe(Pkm[] equipe) {
		for(Pkm p : equipe) {
			//Normalement, c'est cette formule
			p.stats[2][0] = p.stats[2][1];
		}
	}
}
