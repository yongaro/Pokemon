package pokemon.launcher;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;

import pokemon.modele.*;



public class MyGdxGame extends Game {
	SpriteBatch batch;
	Texture img;
	TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;

    public static Joueur Jtest=new Joueur();
    public static Pkm[] Ptest=new Pkm[6];
    public static void initStatic(){
    	Ptest[0]=new Pkm(Pokedex.Bulbizarre.get(),50);
    	Ptest[1]=new Pkm(Pokedex.Herbizarre.get(),50);
    	Ptest[2]=new Pkm(Pokedex.Florizarre.get(),50);
    	Ptest[3]=new Pkm(Pokedex.Rattata.get(),50);
    	Ptest[4]=new Pkm(Pokedex.Rattatac.get(),50);
    	Ptest[5]=new Pkm(Pokedex.Salameche.get(),50);
    	for(Pkm p:Ptest){
    		Jtest.add(p);
    		p.add(bddCapacite.Charge.get());
    		p.add(bddCapacite.JetdeSable.get());
    		p.add(bddCapacite.Repos.get());
    		p.add(bddCapacite.Tonnerre.get());
    	}
    	
    }
 
	@Override
	public void create () {
		MyGdxGame.initStatic();
		MenuListener menu=new MenuListener(this);
		System.out.println("Level pk1 :"+MyGdxGame.Ptest[0].get(0));
	}

}
