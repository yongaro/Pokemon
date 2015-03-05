package pokemon.modele;

import java.util.HashMap;

/* La classe NPCList permet de lister tout les PNJ du jeu*/

public class NPCList {
	private HashMap<Integer, NPC> list;
	
	//Constructeur
	public NPCList() {
		list = new HashMap<Integer, NPC>();
	}
	
	//Fonctionnalités principales
	public NPC getNPC(int id) {
		return list.get(id);
	}
	public void addNPC(NPC npc) {
		list.put(npc.getId(), npc);
	}
}
