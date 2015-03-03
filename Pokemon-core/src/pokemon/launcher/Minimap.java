package pokemon.launcher;

public enum Minimap {
	BourgPalette("Bourg Palette",10,10);
	
	
	String str;
	int x;
	int y;
	
	Minimap(String s,int x,int y){
		str=s;
		this.x=x;
		this.y=y;
	}

	public String getStr() {
		return str;
	}



	public int getX() {
		return x;
	}



	public int getY() {
		return y;
	}

	

}
