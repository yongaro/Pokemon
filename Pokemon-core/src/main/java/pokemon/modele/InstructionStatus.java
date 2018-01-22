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
		System.out.println("STATUS");
		if(id != 0) {
			list.getNPC(id).setNewStatus(newStatus);
		}
		return null;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNewStatus() {
		return newStatus;
	}
	public void setNewStatus(int newStatus) {
		this.newStatus = newStatus;
	}
}
