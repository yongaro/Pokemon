package pokemon.modele;

public class Objet implements Qmax,Infos {
	protected int ID;
	protected String nom;
	protected String description;
	protected int qmax;
	
	public Objet(){ ID=0; nom="TODO"; description="TODO"; qmax=1; }
	
	public Objet(int id,String nm, String desc, int nbmax){
		ID=id; nom=nm; description=desc; qmax=nbmax;
	}
	
	
	
	public int script(Pkm cible){
		
		return 0;
	}
	
	public int qmax(){ return qmax; }
	public int getID(){return ID;}
	public String getNom(){return nom;}
	public String getDesc(){return description;}
	public String getInfos(){return "";}
	public String toString(){return nom+"\n"+description;}
}
