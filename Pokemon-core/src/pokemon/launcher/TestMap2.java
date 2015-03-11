package pokemon.launcher;

import java.util.Scanner;

import com.badlogic.gdx.math.Vector2;

import pokemon.modele.Direction;
import pokemon.modele.Joueur;
import pokemon.modele.Map;

public class TestMap2 {
	public TestMap2() {
		test();
	}

	private void test() {
		System.out.println("TEST DE LA MAP");
		
		Joueur j = new Joueur();
		Map map = new Map("maps/test.tmx");
		j.setCurrentMap(map);
		j.setPos(new Vector2(304f, 544f));
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		sc.nextLine();
		
		while(j.getCurrentMap() == map) {
			j.move(Direction.North);
			System.out.println("Test " + j.getPos());
		}
		System.out.println("Changement de map");
			
		System.out.println("FIN DU TEST DE LA MAP");
	}
}
