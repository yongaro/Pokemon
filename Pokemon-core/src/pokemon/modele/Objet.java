package pokemon.modele;

public class Objet implements Qmax,Infos {
	protected int ID;
	protected String nom;
	protected String description;
	protected int qmax;
	protected Type buffedType;
	
	public Objet(){ ID=0; nom="TODO"; description="TODO"; qmax=1; }
	
	public Objet(int id,String nm, String desc, int nbmax){ ID=id; nom=nm; description=desc; qmax=nbmax; buffedType=null; }
	public Objet(int id,String nm, String desc, int nbmax,Type bT){this(id,nm,desc,nbmax); buffedType=bT;}
	
	
	
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
