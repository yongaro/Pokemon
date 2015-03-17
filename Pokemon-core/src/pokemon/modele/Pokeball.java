package pokemon.modele;

public class Pokeball extends Objet {
	protected float bonusBall ; // chances de capture
	
	
	public static Pokeball pokeball=new Pokeball(6,"Pokeball","Permet d'attraper des pokemons",1.0f,99);
	public static Pokeball superball=new Pokeball(7,"SuperBall","Permet d'attraper pokemon, fonctionne mieux qu'une Pokeball",1.5f,99);
	public static Pokeball hyperball=new Pokeball(8,"Hyperball","Permet d'attraper un pokemon, fonctionne mieux qu'une Hyperball",2.0f,99);
	public static Pokeball masterball=new Pokeball(9,"Masterball","Permet d'attraper un pokemon a coup sur",255.0f,99);
	
	
	
	
	public Pokeball(){ super(); }
	public Pokeball(int id,String nm, String desc,float bBall,int nbmax){
		super(id,nm,desc,nbmax);
		bonusBall=bBall;
	}
	
	public int script(Pkm cible){
		
		
		return 0;
	}

}
