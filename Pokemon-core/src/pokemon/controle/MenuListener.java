package pokemon.controle;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import pokemon.launcher.MapScreen;
import pokemon.launcher.MyGdxGame;
import pokemon.vue.menuInventaire;
import pokemon.vue.menuPokematos;
import pokemon.vue.menuPokematosMap;
import pokemon.vue.menuPokemon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;

public class MenuListener extends InputMultiplexer{
	MyGdxGame mygdxgame;
	menuPokemon menupokemon;
	public int state=0; //1- objet dans pokemon 2-Utilisation objet pokemon
	public int slotInventaire[];
	public int slotPokemon;
	menuInventaire menuinventaire;
	menuPokematos  menupokematos;
	menuPokematosMap  menupokematosmap;
	MapScreen screen;
	
	public MenuListener(MyGdxGame mygdxgame, MapScreen screen)
	{
		this.mygdxgame=mygdxgame;
		this.screen = screen;
		menupokemon=new menuPokemon(mygdxgame);
		menuinventaire=new menuInventaire(mygdxgame,this);
		menupokematos=new menuPokematos(mygdxgame);
		menupokematosmap=new menuPokematosMap(mygdxgame);
		mygdxgame.setScreen(menupokemon);
		this.addProcessor(new ReturnToMapListener(mygdxgame, screen));
		this.addProcessor(new PokemonMenuListenner(menupokemon,mygdxgame,this));
		this.addProcessor(new InventaireMenuListener(mygdxgame, menupokemon, menuinventaire,this));
		this.addProcessor(new PokematosMenuListener(menupokematos,mygdxgame,this));
		this.addProcessor(new PokematosMapMenuListener(menupokematosmap,mygdxgame,this));
		Gdx.input.setInputProcessor(this);
		slotInventaire=new int[2];
	}

	public void switchtoInventory()
	{
		if(menuinventaire==null){
			menuinventaire=new menuInventaire(mygdxgame,this);
			this.addProcessor(new InventaireMenuListener(mygdxgame, menupokemon, menuinventaire,this));}
		mygdxgame.setScreen(menuinventaire);
	}
	
	public void switchto(Class c)
	{
		Field[] fields=this.getClass().getDeclaredFields();
		Object o = null;
		try {
			
			System.out.println("Looking for"+c.toString());
			for(Field f:fields){
				System.out.println(f.getName()+"  "+f.get(this));
	
				if(f.getType()==c){
					/*Class[] types = new Class[]{MyGdxGame.class,MenuListener.class};
				    Constructor ct = f.getType().getConstructor(types); 
				    f.setAccessible(true);
				    o=ct.newInstance(mygdxgame,this);
				    f.set(this,o);*/
					mygdxgame.setScreen((Screen)f.get(this));
					break;
				}
			}

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setState(int i){
		state=i;
	}

	public MapScreen getScreen() {
		return screen;
	}

}
