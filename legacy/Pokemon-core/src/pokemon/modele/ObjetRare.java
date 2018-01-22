package pokemon.modele;

import java.io.Serializable;

public class ObjetRare extends Objet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -487748501040989678L;


	protected int cible;
	
	
	public static ObjetRare Bicyclette=new ObjetRare (3,"Bicyclette","Augmente la vitesse de deplacement",0);
	public static ObjetRare Machette=new ObjetRare (4,"Machette","Permet de couper les arbustes et les hautes herbes",1);
	public static ObjetRare Lampe=new ObjetRare(5,"Lampe","Permet de voir dans les endroits sombres",0);
	
	public ObjetRare(){ super(); }

	public ObjetRare(int id,String nm, String desc,int cible){
		super(id,nm,desc,1);
		this.cible=cible;
	}
	
	public int script(Pkm cible){ return 0; }
	public int script(Joueur J){ return 0; }
}
