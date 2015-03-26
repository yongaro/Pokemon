package pokemon.modele;

public class InstructionStatus extends Instruction{
	private int newStatus;
	private int id;
	
	//Constructeurs
	public InstructionStatus() {
		newStatus = 0;
		id = 0;
	}
	public InstructionStatus(int s, int i) {
		newStatus = s;
		id = i;
	}
	
	//Surcharge
	@Override
	public String execute(NPCList list, Joueur j) {
		if(id != 0) {
			list.getNPC(id).setNewStatus(newStatus);
		}
		return null;
	}
}
