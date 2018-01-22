package pokemon.controle;

import pokemon.launcher.PokemonCore;
import pokemon.modele.Joueur;
import pokemon.vue.CombatV;
import pokemon.vue.menuPokemon;
import com.badlogic.gdx.Gdx;


public class CombatMenuPokemon extends GameInput{ //CONTROLLEUR DE L ECRAN POKEMON PENDANT LE COMBAT

	menuPokemon menu;
	PokemonCore PokemonCore;
	Joueur joueur= PokemonCore.Jtest;
	int state;
	int pkselector;
	int atkselector;
	CombatV combatv;



	public CombatMenuPokemon(PokemonCore PokemonCore, CombatV combatV) {
		super();
		menu=new menuPokemon(PokemonCore);
		this.PokemonCore = PokemonCore;
		state=1;
		pkselector=0;
		atkselector=1;
		PokemonCore.setScreen(menu);
		combatv=combatV;
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {			
		
			menu.setTouched(true);
			v.set(x, y);
			menu.getStage().screenToStageCoordinates(v);
			return super.touchUp(v);
	}



	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			menu.setTouched(false);
			return true;
	}


	@Override
	void handleA() {
		// TODO Auto-generated method stub
		if(joueur.getTeam()[pkselector].get(2)>0){
			combatv.swapPokemon(joueur.getTeam()[pkselector],pkselector);
			PokemonCore.setScreen(combatv);
		}
	}

	@Override
	void handleB() {
		// TODO Auto-generated method stub
		PokemonCore.setScreen(combatv);
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
