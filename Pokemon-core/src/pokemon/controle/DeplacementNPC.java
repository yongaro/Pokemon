package pokemon.controle;

import pokemon.modele.Direction;
import pokemon.modele.NPC;

public class DeplacementNPC {
	public NPC npc;
	public Direction dir;
	public int distance;
	
	public DeplacementNPC(NPC npc, Direction dir, int distance) {
		this.npc = npc;
		this.dir = dir;
		this.distance = distance;
	}
}
