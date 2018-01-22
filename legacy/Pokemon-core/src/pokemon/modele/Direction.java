package pokemon.modele;

public enum Direction {
	North, South, East, West,Standing;

	public static Direction toDirection(String str) {
		if(str == "north") {
			return Direction.North;
		}
		else if(str == "south") {
			return Direction.South;
		}
		else if(str == "east") {
			return Direction.East;
		}
		else {
			return Direction.West;
		}
	}
	
	public static String toString(Direction dir) {
		if(dir == Direction.North) {
			return "north";
		}
		else if(dir == Direction.South) {
			return "south";
		}
		else if(dir == Direction.East) {
			return "east";
		}
		else {
			return "west";
		}
	}
}
