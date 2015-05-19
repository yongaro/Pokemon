package pokemon.modele;

public class InstructionCombat extends Instruction {
	private int id;
	
	public InstructionCombat() {
		id = 1;
	}
	public InstructionCombat(int i) {
		id = i;
	}
	
	@Override
	public String execute(NPCList list, Joueur j) {
		return null;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
