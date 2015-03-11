package pokemon.modele;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

//import pokemon.launcher.MyGdxGame;

/* La classe Joueur regroupe toute les informations concernant le joueur, son �quipe
 * de Pokemon ainsi que son etat (argent, badges...). 
 * Contient aussi la base de donnees afin de stocker les pokemons */

public class Joueur {
	//Attributs du dresseur
	protected int ID;
	protected String nom;
	protected int argent;
	protected int[] badges;
	protected Vector<Stockage<Objet>> inventaire; //  0 Medicaments | 1 Objets Rares | 2 CT/CM | 3 Pokeball | 4 Objets
	
	//Attributs de l'équipe de Pokémon
	protected Pkm[] team;
	protected int teamsize;
	protected Vector<Stockage<Pkm>> boites;
	
	//Attributs concernant la position du joueur
	protected Vector2 pos;
	protected Direction orientation;
	protected Map currentMap;
	protected Vector2 speed;

	public Joueur(){
		ID=0; nom="TODO"; argent=2000; badges=new int[8];
		team=new Pkm[6]; teamsize=0;
		boites=new Vector<Stockage<Pkm>>();
		inventaire=new Vector<Stockage<Objet>>();
		
		pos = new Vector2(400f, 400f);
		speed=new Vector2(0,0);
		orientation = Direction.South;
		currentMap = null;
	}
	public Joueur(int id,String nom){
		ID=id; this.nom=nom; argent=2000; badges=new int[8];
		team=new Pkm[6]; teamsize=0;
		boites=new Vector<Stockage<Pkm>>();
		inventaire=new Vector<Stockage<Objet>>();
		
		pos = new Vector2(0f, 0f);
		orientation = Direction.South;
		currentMap = null;
	}
	
	//Fonction privees
	private void changeMap() {
		
	}
	
	//Accesseurs
	public Vector2 getPos() {
		return pos;
	}
	public void setPos(Vector2 pos) {
		this.pos.x=pos.x;
		this.pos.y=pos.y;
	}

	public Map getCurrentMap() {
		return currentMap;
	}
	public void setCurrentMap(Map currentMap) {
		this.currentMap = currentMap;
	}
	public Pkm[] getTeam(){ return team; }
	public int teamSize(){ return teamsize;}
	
	//Ajoute le pokemon cible a l'equipe du dresseur
	public void add(Pkm cible){
		if(teamsize<6){
			team[teamsize]=cible;
			teamsize++;
		}
	}
	
	//enleve le pokemon cible a l'equipe du dresseur
	public void remove(Pkm cible){
		int ind=-1;
		for(int i=0;i<teamsize;i++){
			if(team[i]==cible){
				ind=i; teamsize--;
				break;
			}
		}
		if(ind!=-1){
			for(int i=ind;i<teamsize;i++){
				team[i]=team[i++];
			}
			team[teamsize+1]=null;
		}
	}
	
	public void teamSet(Pkm cible,int ind){ 
		team[ind]=cible;
	}
	
	/*public boolean move(Direction dir) {
		Cell targetPosition = null;
		orientation = dir;
		if(currentMap == null) {
			return false;
		}
		if(dir == Direction.North) {
			targetPosition = ( (TiledMapTileLayer) currentMap.getTiledMap().getLayers().get("Collide") ).getCell( (int) pos.x, (int) pos.y + 1);
			if(targetPosition == null && !currentMap.isNPCPresent( (int) pos.x, (int) pos.y + 1)) {
				pos.y++;
				return true;
			}
		}
		if(dir == Direction.South) {
			targetPosition = ( (TiledMapTileLayer) currentMap.getTiledMap().getLayers().get("Collide") ).getCell( (int) pos.x, (int) pos.y - 1);
			if(targetPosition == null && !currentMap.isNPCPresent( (int) pos.x, (int) pos.y - 1)) {
				pos.y--;
				return true;
			}
		}
		if(dir == Direction.West) {
			targetPosition = ( (TiledMapTileLayer) currentMap.getTiledMap().getLayers().get("Collide") ).getCell( (int) pos.x - 1, (int) pos.y);
			if(targetPosition == null && !currentMap.isNPCPresent( (int) pos.x - 1, (int) pos.y)) {
				pos.x--;
				return true;
			}
		}
		if(dir == Direction.East) {
			targetPosition = ( (TiledMapTileLayer) currentMap.getTiledMap().getLayers().get("Collide") ).getCell( (int) pos.x + 1, (int) pos.y );
			if(targetPosition == null && !currentMap.isNPCPresent( (int) pos.x + 1, (int) pos.y)) {
				pos.x++;
				return true;
			}
		}
		return false;
	}*/
	
	public boolean move(Direction dir) {
		Vector2 nextPos = new Vector2(getPos());
		TiledMapTileLayer layerCollision = (TiledMapTileLayer) getCurrentMap().getTiledMap().getLayers().get("Collide");
		int spriteWidth = 14;
		int spriteHeight = 19;
		
		nextPos.x+=Gdx.graphics.getDeltaTime()*getSpeed().x;
		nextPos.y+=Gdx.graphics.getDeltaTime()*getSpeed().y;
		//System.out.println("Nextpos: "+nextPos);
		/*Verif si non debordement de map*/

		if(nextPos.x>800-spriteWidth || nextPos.x<0 ) 
		{setSpeedX(0);System.out.println("REACH BORDER");return false;}
		if( nextPos.y>800-spriteHeight || nextPos.y<0)
		{setSpeedY(0);System.out.println("REACH BORDER");return false;}
		/*verif si collision avec decors*/
		if(layerCollision.getCell((int)(nextPos.x/16f),(int)(nextPos.y/16f))!=null ||
				layerCollision.getCell((int)((nextPos.x+spriteWidth-5)/16f),(int)(nextPos.y/16f))!=null ||
				layerCollision.getCell((int)((nextPos.x+spriteWidth-5)/16f),(int)((nextPos.y+spriteHeight-5)/16f))!=null ||
				layerCollision.getCell((int)((nextPos.x)/16f),(int)((nextPos.y+spriteHeight-5)/16f))!=null)
		{
			if(getSpeed().x!=0)
				setSpeedX(0);
			if(getSpeed().y!=0)
				setSpeedY(0);
			System.out.println("Collision DETECTE");
			System.out.println("Speed:" +getSpeed());
			return false;
		}
		//System.out.println("GOING TO NEXT POS");
		//speed.x+=10*delta;
		setPos(nextPos);//);=nextPos.x;
		//posy=nextPos.y;
		
		changeMap();
		
		return true;
	}
	
	public String interact(NPCList npcList) {
		Vector2 cellPos = new Vector2(Math.round(pos.x/16f), Math.round(pos.y/16f));
		if(orientation == Direction.North) {
			return currentMap.interact((int) cellPos.x, (int) cellPos.y + 1, npcList);
		}
		else if(orientation == Direction.South) {
			return currentMap.interact((int) cellPos.x, (int) cellPos.y - 1, npcList);
		}
		else if(orientation == Direction.East) {
			return currentMap.interact((int) cellPos.x + 1, (int) cellPos.y, npcList);
		}
		else if(orientation == Direction.West) {
			return currentMap.interact((int) cellPos.x - 1, (int) cellPos.y, npcList);
		}
		return null;
	}
	
	public Vector2 getSpeed() {
		return speed;
	}
	public void setSpeedX(float sx)
	{
		speed.x=sx;
	}
	public void setSpeedY(float sy)
	{
		speed.y=sy;
	}
	
	public void setOrientation(Direction orientation) {
		this.orientation = orientation;
		switch(this.orientation){
		case South:
			speed.y=-60;
			speed.x=0;
			break;
		case North:
			speed.y=60;
			speed.x=0;
			break;
		case East:
			speed.y=0;
			speed.x=60;
			break;
		case West:
			speed.y=0;
			speed.x=-60;
			break;
		case Standing:
			speed.y=0;
			speed.x=0;
		}
	}
	public boolean isMoving(){
		return !speed.isZero();}
}
