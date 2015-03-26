package pokemon.modele;

public class NoMoreInstructionException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public NoMoreInstructionException() {
		super();
	}
	public NoMoreInstructionException(String s) {
		super(s);
	}
}
