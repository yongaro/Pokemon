package pokemon.modele;

public class ObjetRare extends Objet {
	protected int cible;
	
	
	public ObjetRare(){ super(); }

	public ObjetRare(int id,String nm, String desc, int nbmax,int cible){
		super(id,nm,desc,nbmax);
		this.cible=cible;
	}
	
	public int script(Pkm cible){ return 0;}
	public int script(Joueur J){ return 0; }
}
