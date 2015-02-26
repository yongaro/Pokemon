package pokemon.launcher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

public class MenuListener extends InputMultiplexer{
	MyGdxGame mygdxgame;
	menuPokemon menupokemon;
	public int state=0; //1- objet dans pokemon
	menuInventaire menuinventaire;


	MenuListener(MyGdxGame mygdxgame)
	{
		this.mygdxgame=mygdxgame;
		menupokemon=new menuPokemon(mygdxgame);
		mygdxgame.setScreen(menupokemon);
		this.addProcessor(new PokemonMenuListenner(menupokemon,mygdxgame,this));
		Gdx.input.setInputProcessor(this);
	}

	public void switchtoInventory()
	{
		if(menuinventaire==null){
			menuinventaire=new menuInventaire(mygdxgame, menuinventaire,this);
			this.addProcessor(new InventaireMenuListener(mygdxgame, menupokemon, menuinventaire,this));}
		mygdxgame.setScreen(menuinventaire);
	}
	public void setState(int i){
		state=i;
	}

}
