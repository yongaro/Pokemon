package pokemon.controle;

import pokemon.annotations.Tps;
import pokemon.launcher.MyGdxGame;
import pokemon.modele.Joueur;
import pokemon.modele.Pkm;
import pokemon.vue.menuInventaire;
import pokemon.vue.menuPokemon;


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

	public PokemonMenuListenner(menuPokemon menu,MyGdxGame myGdxGame) {
		state=1;
		pkselector=0;
		atkselector=1;
		
	}
	
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
	public boolean touchDown(int x, int y, int pointer, int button) {			
		if(menu==myGdxGame.getScreen()){
			menu.setTouched(true);
			return true;
			}
		else
			return false;
	}



	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(menu==myGdxGame.getScreen()){
			menu.setTouched(false);
			v.set(screenX, screenY);
			menu.getStage().screenToStageCoordinates(v);
			System.out.println(v);
			return super.touchUp(v);}
		else
			return false;
	}

	public void handleLeft(){

	}

	public void handleRight(){
		if(state==1){		
			menuListener.switchto(menuInventaire.class);
			stateRAZ();
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
		System.out.println("B pressed");
		if(state==1){
			myGdxGame.setScreen(menuListener.getScreen());
		}
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
			menuListener.slotPokemon=pkselector;
		}
	}

	public void stateRAZ(){
		state=1;
		pkselector=0;
		atkselector=1;
		menu.update(state, pkselector, atkselector);
	}

}

