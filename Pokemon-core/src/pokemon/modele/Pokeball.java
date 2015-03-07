package pokemon.modele;

public class Pokeball extends Objet {
	protected int proc; // chances de capture
	
	public Pokeball(){ super(); }

	public Pokeball(int id,String nm, String desc, int nbmax,int proc){
		super(id,nm,desc,nbmax);
		this.proc=proc;
	}
	
	public int script(Pkm cible){
		
		
		return 0;
	}

}
