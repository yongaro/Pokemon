package pokemon.modele;

public class ObjetRare extends Objet {
	protected int cible;
	
	
	public ObjetRare(){ super(); }

	public ObjetRare(int id,String nm, String desc, int nbmax,int cible){
		super(id,nm,desc,nbmax);
		this.cible=cible;
	}
	
	public void script(Pkm cible){}
	public void script(Joueur J){}
}
