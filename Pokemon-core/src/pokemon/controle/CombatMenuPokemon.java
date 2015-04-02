package pokemon.controle;

import pokemon.launcher.MyGdxGame;
import pokemon.modele.Joueur;
import pokemon.vue.CombatV;
import pokemon.vue.menuPokemon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class CombatMenuPokemon implements InputProcessor{ //CONTROLLEUR DE L ECRAN POKEMON PENDANT LE COMBAT

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

	public boolean keyDown(int arg0) {
		switch(arg0)
		{
		case Keys.DOWN:
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
			break;
		
		case Keys.UP:
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
			break;
		case Keys.BACKSPACE:
			myGdxGame.setScreen(combatv);
		
		
		}
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
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
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
