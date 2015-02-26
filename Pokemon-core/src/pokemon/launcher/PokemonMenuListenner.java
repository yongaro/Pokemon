package pokemon.launcher;

import pokemon.modele.Joueur;
import pokemon.modele.Pkm;
import pokemon.modele.Stockage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class PokemonMenuListenner implements InputProcessor{

	menuPokemon menu;
	MyGdxGame myGdxGame;
	Screen nextScreen=null;
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
		pkselector=1;
		atkselector=1;
		this.myGdxGame=myGdxGame;
		this.menuListener=menuListener;
	}
	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		
		if(this.myGdxGame.getScreen()==menu){
			switch(myInput.detectInput()){
			
			case DOWN:
				if(state==1 || state==3){
					if(pkselector<joueur.teamSize()){
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
			
			case UP:
				if(state==1 || state==3){
					if(pkselector!=1){
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
			case A:
				if(state==1)
					this.state++;
				break;
			case B:
				if(state==2){
					this.state--;
					atkselector=1;
				}
				break;
			case SELECT:
				switch(state)
				{
				case 1:
					state=3;
					change=pkselector-1;
					System.out.println("State to 3");
					break;
				case 2:
					state=4;
					change=atkselector-1;
					break;
				case 3://switch tes pokemons
					Pkm swap=joueur.getTeam()[pkselector-1];
					joueur.getTeam()[pkselector-1]= joueur.getTeam()[change];
					joueur.getTeam()[change]=swap;
					//team.swap(team.elementAt(change), team.elementAt(pkselector-1));
					
					state=1;
					menu.update(state,pkselector,atkselector);
					break;
				case 4://switch des attaques
					String attaque=menu.attaque.get(atkselector-1);
					//menu.attaque.set(atkselector-1, menu.attaque.get(change));
					//menu.attaque.set(change, attaque);
					joueur.getTeam()[pkselector-1].getCap().swap(joueur.getTeam()[pkselector-1].getCap().elementAt(atkselector-1), joueur.getTeam()[pkselector-1].getCap().elementAt(change));
					state=2;
					menu.update(state,pkselector,atkselector);
					break;
				}
				break;
			case RIGHT:
			
				if(state==1){				
						menuListener.switchtoInventory();
				}
				break;
			case START:
				if(state==2)
				{
					System.out.print("OK");
					menuListener.switchtoInventory();
					menuListener.setState(1);
				}
				break;
			
			
			}
			return true;
		}
		
		else
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
	public boolean touchDown(int x, int y, int pointer, int button) {			
			int i=280;
			int count=1;
			Vector2 v=new Vector2(x,y);
			menu.stage.screenToStageCoordinates(v);
			System.out.println(v.x+" "+v.y);
			if(v.x>15 && v.x<200 && v.y>10 && v.y<275){
				
				while(i>v.y)
				{
					i-=45;
					count++;
					System.out.print(i+"  ");
				}
				System.out.println(count-1);
				pkselector=count-1;
				menu.update(state,pkselector,atkselector);
			}
			
			return true;
		
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

