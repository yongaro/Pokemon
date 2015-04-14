package pokemon.controle;

import java.util.Vector;

import pokemon.annotations.Tps;
import pokemon.launcher.MyGdxGame;
import pokemon.modele.Joueur;
import pokemon.vue.menuInventaire;
import pokemon.vue.menuPokematos;
import pokemon.vue.menuPokemon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.intellij.openapi.ui.ComponentWithBrowseButton.MyDoClickAction;
@Tps(nbhours=3)
public class InventaireMenuListener extends GameInput{

	MyGdxGame mygdxgame;
	Screen previous;
	menuInventaire inventaire; //this is wat i'm listening
	int state=1;
	int pktselector=1;
	int atkselector=1;
	int displayedAtk=0;
	int actionselector=1;
	int[] objselector={0,0};
	public static Vector<String> objets = new Vector<String>();
	MenuListener menuListener;
	Joueur joueur= MyGdxGame.Jtest;


	public InventaireMenuListener(MyGdxGame myGdxGame, Screen previous, menuInventaire menu, MenuListener menuListener) {

		this.mygdxgame = myGdxGame;
		this.previous = previous;
		this.inventaire=menu;
		for(int i=0; i<45;i++)
		{
			objets.add("AtpL"+i+"   x1");
		}
		System.out.print("ObjetsCrees");
		this.menuListener=menuListener;
	}

	public boolean keyDown(int arg0) {
		if(mygdxgame.getScreen()==inventaire){
			super.keyDown(arg0);
			inventaire.update(state, pktselector, objselector[0], objselector[1],actionselector, displayedAtk);
			return true;
		}
		return false;
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
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		if(inventaire==mygdxgame.getScreen()){
			inventaire.setTouched(true);
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
		if(inventaire==mygdxgame.getScreen()){
			inventaire.setTouched(false);
			Vector2 v=new Vector2(screenX,screenY);
			inventaire.getStage().screenToStageCoordinates(v);
			return super.touchUp(v);}
		else
			return false;
	}

	@Override
	void handleA() {
		// TODO Auto-generated method stub
		if(state==2 && this.menuListener.state==1){
			System.out.println("Je donne"+objets.get(objselector[0]+displayedAtk+7*objselector[1]));
		}
		if(state==3){
			menuListener.slotInventaire[0]=pktselector-1;
			menuListener.slotInventaire[1]=objselector[0]+displayedAtk+7*objselector[1];
			menuListener.state=2;
			menuListener.switchto(menuPokemon.class);
		}
		if(state<3)
			state++;
	}

	@Override
	void handleB() {
		if(state!=1)
		{
			state--;
		}
		
	}

	@Override
	void handleLeft() {
		// TODO Auto-generated method stub
		if(state==1){

			System.out.println("Switch a partir de l'inventaire");
			menuListener.switchto(menuPokemon.class);}
		if(state==2 && objselector[1]==1)
			objselector[1]--;
	}

	@Override
	void handleRight() {
		// TODO Auto-generated method stub
		if(state==2 && objselector[1]==0 && objselector[0]+7+displayedAtk<joueur.getPoche(pktselector-1).size())
		{
			objselector[1]++;
			System.out.println(objets.get(objselector[0]+displayedAtk+7*objselector[1]));

		}
		if(state==1){
			System.out.println("Switch a partir de l'inventaire");
			menuListener.switchto(menuPokematos.class);}
	}

	@Override
	void handleUp() {
		// TODO Auto-generated method stub
		if(state==1){
			if(pktselector!=1)
				pktselector--;
		}

		if(state==2){

			if(objselector[0]==0 && displayedAtk!=0){
				objselector[0]=0;
				objselector[1]=0;
				displayedAtk-=14;
			}
			if(objselector[0]!=0)
				objselector[0]--;
			System.out.println(objselector[0]);
		}
		if(state==3)
			if(actionselector!=1)
				actionselector--;
	}

	@Override
	void handleDown() {
		// TODO Auto-generated method stub
		if(state==1 ){
			if(pktselector<5){
				this.pktselector++;
			}
		}
		if(state==2)
		{
			if(objselector[0]<6 && objselector[1]==0){
				if(objselector[0]+displayedAtk+1<joueur.getPoche(pktselector-1).size()){
					//System.out.println(objselector+displayedAtk);
					this.objselector[0]++;}
			}
			else{
				if(objselector[0]==6 && displayedAtk+14<joueur.getPoche(pktselector-1).size()){ //NOUVELLE LISTE
					displayedAtk+=14;
					System.out.print("Bouh");
					objselector[0]=0;
					objselector[1]=0;
				}

			}
			if(objselector[0]<6 && objselector[1]==1){
				if(objselector[0]+1+displayedAtk+7*objselector[1]<joueur.getPoche(pktselector-1).size()){
					this.objselector[0]++;


				}
			}
			/*System.out.println(objets.get(objselector[0]+displayedAtk+7*objselector[1]));*/
		}
		if(state==3){
			if(actionselector<2)
				actionselector++;
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
