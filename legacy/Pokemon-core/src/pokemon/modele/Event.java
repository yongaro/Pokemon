package pokemon.modele;

public class Event {
	protected int niveau;
	protected Pkm evolution;
	protected Capacite cap;
	
	public Event(){niveau= -1; evolution=null; cap=null;}
	public Event(int lv,Pkm pk,Capacite cap){niveau=lv; evolution=pk; this.cap=cap;}
}
