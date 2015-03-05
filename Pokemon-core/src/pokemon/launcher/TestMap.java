package pokemon.launcher;

import com.badlogic.gdx.math.Vector2;

import pokemon.modele.Direction;
import pokemon.modele.Joueur;
import pokemon.modele.Map;
import pokemon.modele.NPC;
import pokemon.modele.NPCList;

public class TestMap {

	public TestMap() {
		test();
	}
	
	public void test() {
		System.out.println("TEST DE LA MAP");
		
		Map map = new Map("maps/test.tmx");
		NPCList npcList = new NPCList();
		NPC npc = new NPC("assets/npcs/test.xml");
		npcList.addNPC(npc);
		map.addNPC(22, 33, npc);
		Joueur joueur = new Joueur();
		joueur.setPos(new Vector2(20f, 34f));
		joueur.setCurrentMap(map);
		
		if(joueur.move(Direction.North)) {
			System.out.println("Mouvement vers le haut");
		}
		if(joueur.move(Direction.North)) {
			System.out.println("Mouvement vers le haut");
		}
		if(joueur.move(Direction.East)) {
			System.out.println("Mouvement vers la droite");
		}
		if(joueur.move(Direction.East)) {
			System.out.println("Mouvement vers la droite");
		}
		if(joueur.move(Direction.East)) {
			System.out.println("Mouvement vers la droite");
		}
		if(joueur.move(Direction.South)) {
			System.out.println("Mouvement vers le bas");
		}
		if(joueur.move(Direction.South)) {
			System.out.println("Mouvement vers le bas");
		}
		System.out.println(joueur.interact(npcList));
		System.out.println(joueur.interact(npcList));
		
		System.out.println("FIN DU TEST DE LA MAP");
	}

}
