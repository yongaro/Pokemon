package pokemon.modele;

import java.io.Serializable;

public class AtkMeteo extends Capacite implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2986588452219474010L;
	protected Climat meteo;
	
	public AtkMeteo(String nom, String desc,int pre,int type,Type elt,int maxpp,Climat clt){
		this.nom=nom;
		this.description=desc;
		this.pre=pre;
		this.type=type;
		this.element=elt;
		this.maxPP=maxpp;
		this.meteo=clt;
	}
	
	
	@Override
	public void script(Pkm user, Pkm cible, Combat context) {
		context.climat=meteo;
		context.ajoutBuffer(meteo.text());
	}

}
