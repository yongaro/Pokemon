package pokemon.modele;

public class AtkMeteo extends Capacite {
	protected Climat meteo;
	
	@Override
	public void script(Pkm user, Pkm cible, Combat context) {
		context.climat=meteo;
	}

}
