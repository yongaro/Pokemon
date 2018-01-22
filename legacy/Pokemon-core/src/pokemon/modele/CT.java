package pokemon.modele;

public class CT extends Objet {
	protected Capacite capRef;
	
	
	public static CT tonnerre=new CT(24,bddCapacite.Tonnerre.get());
	public static CT surf=new CT(29,bddCapacite.Surf.get());
	
	
	
	public CT(){ super(); }
	
	public CT(int id, Capacite cap){
		super(id,cap.nom,cap.description,1);
		capRef=cap;
	}
	
	public int script(Pkm cible){
		capRef.teach(cible);
		return 0;
	}
	
	public String toString(){ return capRef.toString(); }
	
}
