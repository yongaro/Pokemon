package pokemon.modele;

import com.badlogic.gdx.math.Vector2;

public class InstructionCreer extends Instruction {
	private String path;
	private Vector2 pos;
	
	//Constructeurs
	public InstructionCreer() {
		this.path = "npc/npc";
		this.pos = new Vector2(0, 0);
	}
	public InstructionCreer(String path, Vector2 pos) {
		this.path = path;
		this.pos = pos;
	}
	
	@Override
	public String execute(NPCList list, Joueur j) {
//		j.getCurrentMap().addNPC(npc, list);
		return null;
	}

}
