package pokemon.modele;

public class NoNPCException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public NoNPCException() {
		super();
	}
	public NoNPCException(String s) {
		super(s);
	}
}
