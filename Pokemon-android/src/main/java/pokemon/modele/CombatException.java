package pokemon.modele;

public class CombatException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private Dresseur dresseur;
	
	//Constructeurs
	public CombatException() {
		super();
		dresseur = null;
	}
	public CombatException(String s) {
		super(s);
		dresseur = null;
	}

	//Accesseurs
	public Dresseur getDresseur() {
		return dresseur;
	}
	public void setDresseur(Dresseur dresseur) {
		this.dresseur = dresseur;
	}
}
