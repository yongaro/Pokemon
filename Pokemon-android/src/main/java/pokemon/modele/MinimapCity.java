package pokemon.modele;

import com.badlogic.gdx.utils.ArrayMap;



public class MinimapCity {
	String nom;
	int x;
	int y;
	ArrayMap<String,MinimapCity> neighbours;
	
	
	public MinimapCity(String nom, int x, int y) {
		super();
		this.nom = nom;
		this.x = x;
		this.y = y;
		neighbours=new ArrayMap<String,MinimapCity>();
	}
	
	public void addNeighbour(String pos,MinimapCity c)
	{
		neighbours.put(pos, c);
	}
	
	public boolean hasNeighbourAt(String pos){
		return neighbours.containsKey(pos);
	}
	
	public MinimapCity getNeighbour(String pos){
		return neighbours.get(pos);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	
}
