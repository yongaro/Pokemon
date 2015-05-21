package pokemon.modele;

import java.io.Serializable;

public class AtkMeteo extends Capacite implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2986588452219474010L;
	protected Climat meteo;
	
	@Override
	public void script(Pkm user, Pkm cible, Combat context) {
		context.climat=meteo;
	}

}
