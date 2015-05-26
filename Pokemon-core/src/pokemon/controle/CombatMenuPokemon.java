package pokemon.controle;

import pokemon.launcher.MyGdxGame;
import pokemon.modele.Joueur;
import pokemon.vue.CombatV;
import pokemon.vue.menuPokemon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class CombatMenuPokemon extends GameInput{ //CONTROLLEUR DE L ECRAN POKEMON PENDANT LE COMBAT

	menuPokemon menu;
	MyGdxGame myGdxGame;
	Joueur joueur= MyGdxGame.Jtest;
	int state;
	int pkselector;
	int atkselector;
	CombatV combatv;



	public CombatMenuPokemon(MyGdxGame myGdxGame, CombatV combatV) {
		super();
		menu=new menuPokemon(myGdxGame);
		this.myGdxGame = myGdxGame;
		state=1;
		pkselector=0;
		atkselector=1;
		myGdxGame.setScreen(menu);
		combatv=combatV;
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {			
		
			menu.setTouched(true);
			return true;
	}



	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			menu.setTouched(false);
			v.set(screenX, screenY);
			menu.getStage().screenToStageCoordinates(v);
			
			return super.touchUp(v);
	}


	@Override
	void handleA() {
		// TODO Auto-generated method stub
		if(joueur.getTeam()[pkselector].get(2)>0){
			combatv.swapPokemon(joueur.getTeam()[pkselector],pkselector);
			myGdxGame.setScreen(combatv);
		}
	}

	@Override
	void handleB() {
		// TODO Auto-generated method stub
		myGdxGame.setScreen(combatv);
	}

	@Override
	void handleLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void handleRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void handleUp() {
		// TODO Auto-generated method stub
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

	@Override
	void handleDown() {
		// TODO Auto-generated method stub
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

	@Override
	void handleSelect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void handleStart() {
		// TODO Auto-generated method stub
		
	}


}
