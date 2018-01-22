package pokemon.modele;

import java.util.Vector;

public class Minimap {
	Vector<MinimapCity> cities;
	


public Minimap(){
	cities=new Vector<MinimapCity>();
	MinimapCity c1=new MinimapCity("Bourg Palette",385,33);
	MinimapCity c2=new MinimapCity("Jadielle",385,68);
	c1.addNeighbour("North", c2);
	c2.addNeighbour("South", c1);
	
	cities.add(c1);
	cities.add(c2);

}



public Vector<MinimapCity> getCities() {
	return cities;
}

}