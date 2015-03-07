package pokemon.modele;

public class CT extends Objet {
	Capacite capRef;
	
	public CT(){ super(); }
	
	public CT(int id, Capacite cap){
		super(id,cap.nom,cap.description,1);
		capRef=cap;
	}
	
	public int script(Pkm cible){
		capRef.teach(cible);
		return 0;
	}
	
}
