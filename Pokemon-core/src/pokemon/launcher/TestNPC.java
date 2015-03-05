package pokemon.launcher;

import pokemon.modele.NPC;
import pokemon.modele.NPCList;

/* Test des classes NPC et NPCList*/

public class TestNPC {

	public static void main(String[] args) {
		NPCList liste = new NPCList();
		NPC npc = new NPC("assets/npcs/test.xml");
		liste.addNPC(npc);
		System.out.println(npc.executeDialog(liste));
		System.out.println(npc.executeDialog(liste));
	}
}
