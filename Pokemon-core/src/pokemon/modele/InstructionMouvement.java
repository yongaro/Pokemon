package pokemon.modele;

public class InstructionMouvement extends Instruction{
	private int id;
	private Direction dir;
	private int dist;
	
	//Constructeurs
	public InstructionMouvement() {
		id = 0;
		dir = Direction.South;
		dist = 0;
	}
	public InstructionMouvement(int i, Direction d, int dst) {
		id = i;
		dir = d;
		dist = dst;
	}
	
	//Surcharge
	@Override
	public String execute(NPCList list, Joueur j) {
		if(id != 0) {
			list.getNPC(id).setOrientation(dir);
		}
		else
		{
			j.setOrientation(dir);
		}
		return null;
	}
}
