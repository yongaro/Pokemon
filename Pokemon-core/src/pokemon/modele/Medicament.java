package pokemon.modele;

public class Medicament extends Objet {
	protected int effet; // proportion de pv rendus 
	protected int flagSoin; // 1 pv | 2 statut | 3 K.O | 4 pv+statut | 5 pv+statut+K.0  
	protected boolean baie;
	
	public Medicament(){ super(); }

	public Medicament(int id,String nm, String desc, int nbmax,int effet,int flag,boolean baie){
		super(id,nm,desc,nbmax);
		this.effet=effet; flagSoin=flag; this.baie=baie;
	}
	
	public void script(Pkm cible){}

}
