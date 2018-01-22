package pokemon.modele;

public class Personnage {
	protected static int cptID;
	protected final int ID;
	protected String nom;
	protected Stockage<Pkm> Equipe;
	
	
	public Personnage(){ID=cptID++;}
}
