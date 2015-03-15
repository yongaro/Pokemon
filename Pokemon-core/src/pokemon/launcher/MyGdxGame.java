package pokemon.launcher;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import pokemon.annotations.Tps;
import pokemon.controle.MenuListener;
import pokemon.modele.*;


@Tps(nbhours=2)
public class MyGdxGame extends Game {
	SpriteBatch batch;
	Texture img;
	TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    
    public static NPCList npcList = new NPCList();
    public static Minimap m=new Minimap();
    public static Joueur Jtest=new Joueur();
    public static Joueur Jtest2=new Joueur();
    public static Pkm[] Ptest=new Pkm[6];
    public static Pkm[] Ptest2=new Pkm[6];
   
 
	@Override
	public void create () {
		MyGdxGame.initStatic();
		Jtest.setCurrentMap(new Map("maps/bigmap.tmx", npcList));
		MenuListener menu=new MenuListener(this);
		
		//TestMap2 test = new TestMap2();
		//this.setScreen(new TestRender());
//		Combat test=new Combat();
//		System.out.println(test.combatsolo(MyGdxGame.Jtest,MyGdxGame.Jtest2));
	}
	
	 public static void initStatic(){
	    	Ptest[0]=new Pkm(Pokedex.Reptincel.get(),25); Ptest2[0]=new Pkm(Pokedex.Reptincel.get(),25);
	    	Ptest[1]=new Pkm(Pokedex.Herbizarre.get(),25); Ptest2[1]=new Pkm(Pokedex.Herbizarre.get(),25);
	    	Ptest[2]=new Pkm(Pokedex.Carabaffe.get(),25); Ptest2[2]=new Pkm(Pokedex.Carabaffe.get(),25);
	    	Ptest[3]=new Pkm(Pokedex.Roucoups.get(),25); Ptest2[3]=new Pkm(Pokedex.Roucoups.get(),25);
	    	Ptest[4]=new Pkm(Pokedex.Rattatac.get(),25); Ptest2[4]=new Pkm(Pokedex.Rattatac.get(),25);
	    	Ptest[5]=new Pkm(Pokedex.Pikachu.get(),25); Ptest2[5]=new Pkm(Pokedex.Pikachu.get(),25);
	    	
	    	for(int i=0;i<6;i++){
	    		Ptest[i].give(Medicament.baieTest); Ptest2[i].give(Medicament.baieTest);
	    		if(i==0){
	    			Ptest[i].add(bddCapacite.PoingEclair.get()); Ptest2[i].add(bddCapacite.PoingEclair.get());
	    			Ptest[i].add(bddCapacite.JetDeSable.get()); Ptest2[i].add(bddCapacite.JetDeSable.get());
	    			Ptest[i].add(bddCapacite.PoingDeFeu.get()); Ptest2[i].add(bddCapacite.PoingDeFeu.get());
	    			Ptest[i].add(bddCapacite.Repos.get()); Ptest2[i].add(bddCapacite.Repos.get());
	    		}
	    		if(i==1){
	    			Ptest[i].add(bddCapacite.Charge.get()); Ptest2[i].add(bddCapacite.Charge.get());
	    			Ptest[i].add(bddCapacite.JetDeSable.get()); Ptest2[i].add(bddCapacite.JetDeSable.get());
	    			Ptest[i].add(bddCapacite.Seisme.get()); Ptest2[i].add(bddCapacite.Seisme.get());
	    			Ptest[i].add(bddCapacite.MegaSangsue.get()); Ptest2[i].add(bddCapacite.MegaSangsue.get());
	    		}
	    		if(i==2){
	    			Ptest[i].add(bddCapacite.Surf.get()); Ptest2[i].add(bddCapacite.Surf.get());
	    			Ptest[i].add(bddCapacite.JetDeSable.get()); Ptest2[i].add(bddCapacite.JetDeSable.get());
	    			Ptest[i].add(bddCapacite.PoingGlace.get()); Ptest2[i].add(bddCapacite.PoingGlace.get());
	    			Ptest[i].add(bddCapacite.Repos.get()); Ptest2[i].add(bddCapacite.Repos.get());
	    		}
	    		if(i==3){
	    			Ptest[i].add(bddCapacite.CruAile.get()); Ptest2[i].add(bddCapacite.CruAile.get());
	    			Ptest[i].add(bddCapacite.JetDeSable.get()); Ptest2[i].add(bddCapacite.JetDeSable.get());
	    			Ptest[i].add(bddCapacite.JetPierres.get()); Ptest2[i].add(bddCapacite.JetPierres.get());
	    			Ptest[i].add(bddCapacite.Repos.get()); Ptest2[i].add(bddCapacite.Repos.get());
	    		}
	    		if(i==4){
	    			Ptest[i].add(bddCapacite.CrocDeMort.get()); Ptest2[i].add(bddCapacite.CrocDeMort.get());
	    			Ptest[i].add(bddCapacite.JetDeSable.get()); Ptest2[i].add(bddCapacite.JetDeSable.get());
	    			Ptest[i].add(bddCapacite.CrochetVenin.get()); Ptest2[i].add(bddCapacite.CrochetVenin.get());
	    			Ptest[i].add(bddCapacite.Repos.get()); Ptest2[i].add(bddCapacite.Repos.get());
	    		}
	    		if(i==5){
	    			Ptest[i].add(bddCapacite.Tonnerre.get()); Ptest2[i].add(bddCapacite.Tonnerre.get());
	    			Ptest[i].add(bddCapacite.JetDeSable.get()); Ptest2[i].add(bddCapacite.JetDeSable.get());
	    			Ptest[i].add(bddCapacite.PoingGlace.get()); Ptest2[i].add(bddCapacite.PoingGlace.get());
	    			Ptest[i].add(bddCapacite.Repos.get()); Ptest2[i].add(bddCapacite.Repos.get());
	    		}
	    		Jtest.add(Ptest[i]); Jtest2.add(Ptest2[i]);
	    	}
	    	Pkm[] temp=Jtest2.getTeam();
	    	temp[0]=Ptest2[2]; temp[2]=Ptest2[0];
	    }
}
