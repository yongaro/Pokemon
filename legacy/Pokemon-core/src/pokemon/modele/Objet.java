package pokemon.modele;

import java.io.Serializable;

public class Objet implements Qmax,Infos, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6811087298280325661L;
	protected int ID;
	protected String nom;
	protected String description;
	protected int qmax;
	protected Type buffedType;
	
	public static Objet graineMiracle= new Objet(10,"Graine Miracle","Augmente la puissance des attaques Plante de 50%",99,Type.Plante);
	public static Objet aimant= new Objet(10,"Aimant","Augmente la puissance des attaques Electrique de 50%",99,Type.Electrique);
	
	
	
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
