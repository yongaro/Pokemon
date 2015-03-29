package pokemon.modele;

public class InstructionTexte extends Instruction{
	private String text;
	
	//Constructeurs
	public InstructionTexte() {
		text = "Bonjour";
	}
	public InstructionTexte(String t) {
		text = t;
	}
	
	//Surcharge
	@Override
	public String execute(NPCList list, Joueur j) {
		System.out.println("TEXTE");
		return text;
	}

}
