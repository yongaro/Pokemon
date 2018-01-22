package pokemon.modele;

import com.badlogic.gdx.math.Vector2;

/* La classe MapChange permet de stocker toutes les informations relatifs a
 * un changement de map.*/

public class MapChange {
	//La region en elle meme
	private Vector2 pos;
	private Vector2 dim;
	
	//La destination
	private String destMap;
	private Direction orientation;
	private Vector2 targetPos;
	
	public MapChange() {
		setDestMap("maps/test.tmx");
		setOrientation(Direction.North);
		setPos(new Vector2(0f, 0f));
		setDim(new Vector2(16f, 16f));
		setTargetPos(new Vector2(0f, 0f));
	}
	public MapChange(String dest, Direction dir, Vector2 p, Vector2 d, Vector2 t) {
		setDestMap(dest);
		setOrientation(dir);
		pos = new Vector2(p);
		dim = new Vector2(d);
		targetPos = new Vector2(t);
		
//		System.out.println("Ajout de : " + dest + " a " + pos + " : " + dim);
	}

	//Accesseurs
	public String getDestMap() {
		return destMap;
	}
	public void setDestMap(String destMap) {
		this.destMap = destMap;
	}
	public Direction getOrientation() {
		return orientation;
	}
	public void setOrientation(Direction orientation) {
		this.orientation = orientation;
	}
	public Vector2 getPos() {
		return pos;
	}
	public void setPos(Vector2 pos) {
		this.pos = pos;
	}
	public Vector2 getTargetPos() {
		return targetPos;
	}
	public void setTargetPos(Vector2 targetPos) {
		this.targetPos = targetPos;
	}
	public Vector2 getDim() {
		return dim;
	}
	public void setDim(Vector2 dim) {
		this.dim = dim;
	}
}
