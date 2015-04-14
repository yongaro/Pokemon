package pokemon.controle;

import pokemon.annotations.Tps;
import pokemon.launcher.MapScreen;
import pokemon.launcher.MyGdxGame;
import pokemon.modele.Joueur;
import pokemon.modele.Pkm;
import pokemon.modele.Stockage;
import pokemon.vue.menuInventaire;
import pokemon.vue.menuPokemon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

@Tps(nbhours=2)
public class PokemonMenuListenner extends GameInput{

	menuPokemon menu;
	MyGdxGame myGdxGame;
	MenuListener menuListener;
	Joueur joueur= MyGdxGame.Jtest;
	int state;
	int pkselector;
	int atkselector;
	int change;

	PokemonMenuListenner(menuPokemon menu,MyGdxGame myGdxGame, MenuListener menuListener)
	{
		this.menu=menu;
		state=1;
		pkselector=0;
		atkselector=1;
		this.myGdxGame=myGdxGame;
		this.menuListener=menuListener;
	}
	@Override
	public boolean keyDown(int arg0) {

		if(this.myGdxGame.getScreen()==menu){
			return super.keyDown(arg0);
		}
		else{
			return false;
		}
	}


	@Override
	public boolean keyTyped(char arg0) {

		return true;

	}

	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {			
		if(menu==myGdxGame.getScreen()){
			menu.setTouched(true);
			return true;
			}
		else
			return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(menu==myGdxGame.getScreen()){
			menu.setTouched(false);
			Vector2 v=new Vector2(screenX,screenY);
			menu.getStage().screenToStageCoordinates(v);
			return super.touchUp(v);}
		else
			return false;
	}

	public void handleLeft(){

	}

	public void handleRight(){
		if(state==1){		
			menuListener.switchto(menuInventaire.class);
			//menuListener.switchtoInventory();
		}

	}

	public void handleUp(){
		if(state==1 || state==3){
			if(pkselector!=0){
				this.pkselector--;
				menu.update(state,pkselector,atkselector);
			}
		}
		if(state==2 || state==4){
			if(atkselector!=1){
				this.atkselector--;
				menu.update(state,pkselector,atkselector);
			}
		}		
	}

	public void handleDown(){

		if(state==1 || state==3){
			if(pkselector+1<joueur.teamSize()){
				this.pkselector++;
				menu.update(state,pkselector,atkselector);
			}
		}
		if(state==2 || state==4){
			if(atkselector<4){
				this.atkselector++;
				menu.update(state,pkselector,atkselector);
			}
		}

	}

	public void handleA(){
		if(state==1)
			if(menuListener.state==2)
			{
				//joueur.getPoche(menuListener.slotInventaire[0]).at(menuListener.slotInventaire[0]).script(joueur.getTeam()[pkselector]);
				System.out.println("Je donne"+joueur.getPoche(menuListener.slotInventaire[0]).at(menuListener.slotInventaire[0]).getNom());
				joueur.getPoche(menuListener.slotInventaire[0]).utiliser(menuListener.slotInventaire[1], null,joueur.getTeam()[pkselector] ,null);
				menuListener.state=0;
			}
			else
				this.state++;
	}

	public void handleB(){
		if(state==2){
			this.state--;
			atkselector=1;
		}
	}
	@Override
	void handleSelect() {
		switch(state)
		{
		case 1:
			state=3;
			change=pkselector;
			System.out.println("State to 3");
			break;
		case 2:
			state=4;
			change=atkselector-1;
			System.out.println("State to 4 Selectec atk "+change);

			break;
		case 3://switch des pokemons
			Pkm swap=joueur.getTeam()[pkselector];
			joueur.getTeam()[pkselector]= joueur.getTeam()[change];
			joueur.getTeam()[change]=swap;
			//team.swap(team.elementAt(change), team.elementAt(pkselector-1));
			menu.healthbarswap(change, pkselector);
			state=1;
			menu.update(state,pkselector,atkselector);
			break;
		case 4://switch des attaques
			//String attaque=menu.attaque.get(atkselector-1);
			//menu.attaque.set(atkselector-1, menu.attaque.get(change));
			//menu.attaque.set(change, attaque);
			joueur.getTeam()[pkselector].getCap().swap(joueur.getTeam()[pkselector].getCap().elementAt(atkselector-1), joueur.getTeam()[pkselector].getCap().elementAt(change));
			state=2;
			menu.update(state,pkselector,atkselector);
			break;//
		}

	}
	@Override
	void handleStart() {
		if(state==2)
		{
			System.out.print("OK");
			menuListener.switchto(menuInventaire.class);
			menuListener.setState(1);
		}
	}



}

