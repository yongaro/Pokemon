package pokemon.controle;

import java.util.Arrays;

import pokemon.launcher.MyGdxGame;
import pokemon.modele.Combat;
import pokemon.vue.CombatV;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

public class CombatListener implements InputProcessor{

	CombatV combatV;
	Combat c;
	String[] retval;
	int width=640;
	CombatMenuPokemon mpokemon;
	MyGdxGame myGdxGame;
	int flag;
	int textinc;
	
	public CombatListener(MyGdxGame myGdxGame,CombatV combatV,Combat c) {
		this.combatV=combatV;
		this.c=c;
		this.myGdxGame=myGdxGame;
		Gdx.input.setInputProcessor(this);

	}

	public void getBuffer(String s){
		retval=s.split("\n");
		System.out.println("RETVAL"+Arrays.toString(retval));
		combatV.getDbox().setWidth(width);
		combatV.getDbox().setMessage(retval[0]);
		textinc=1;
	}
	
	@Override
	public boolean keyDown(int arg0) {
		if(c.getPCourant()!=null)
		//System.out.println(c.getPCourant().getNom());
		if(combatV.healthbarLocked()){
			
		
			return false;}
		switch(arg0){
		
		case Keys.ENTER:
		{
			if(combatV.getState()==0){
				combatV.battleBegin();//set state to 1
				break;
			}
			if(combatV.getState()==1){
				System.out.println("UNLOCKING THREAD STATE 1");
				c.setfreeze(false);
				combatV.getDbox().setWidth(width/2);
				combatV.getDbox().setMessage("Que faire ?");
				//state++;
				combatV.setState(combatV.getState()+1);
				combatV.setSelector(0);
				break;
			}
			if(combatV.getState()==2){ //selection action
				if(combatV.getSelector()==3) {
					System.exit(0);
				}

				if(combatV.getSelector()==2)
				{
					//state=2;
					//combatV.setState(2);
					mpokemon=new CombatMenuPokemon(myGdxGame,combatV);
				}
				else{
					textinc=0;
					//state++;
					combatV.setState(combatV.getState()+1);
				}
				flag=combatV.getSelector();
				combatV.setSelector(0);				
				break;
			}
			if(combatV.getState()==3){ //selection atq
			//	attackanimation=true;
				c.setAct(flag, combatV.getSelector());
				//System.out.println(" SETACT "+flag+","+selector);
				//selector=0;
				combatV.setSelector(0);
				break;
			}
			if(combatV.getState()==5)//lecture du buffer
			{//si qqchose a lire
				if(textinc==1){
					System.out.println("LAUNCHING ANIMATIONS");
					combatV.playAttackAnimations();

				}
				//state++;
				combatV.setState(combatV.getState()+1);

				break;
			}

			if(combatV.getState()==6){

				if(textinc<retval.length)
				{
					//if(!combatV.healthbarLocked())
						combatV.getDbox().setMessage(retval[textinc++]);
					if(textinc==2){
						combatV.animateHealthBars();
					}
				}
				else{
					//retval=null;
					textinc=1;
					if(combatV.hideDeadIA())
					{
						return true;
					}
					
					if(combatV.getPkm().get(2)==0){
						mpokemon=new CombatMenuPokemon(myGdxGame,combatV);
						break;
					}

					if(c.getPCourant().getPkm()==combatV.getPkm())
					{
						combatV.setState(2);
						combatV.getDbox().setWidth(width/2);
					
					combatV.getDbox().setMessage("Que faire ?");
					}
					System.out.println("UNLOCKING THREAD STATE6");
					c.setfreeze(false);
				}
				break;
			}

			if(combatV.getState()==7){ //pokemon change par le joueur
				c.setfreeze(false);
				combatV.getDbox().setWidth(width/2);
				combatV.getDbox().setMessage("Que faire ?");
				combatV.setState(2);
				break;
			}
			if(combatV.getState()==8){ //swapIA
				combatV.swapIA();
				break;
			}
		}
		case Keys.DOWN:
		{	
			if(combatV.getSelector()==0 || combatV.getSelector()==2)
				combatV.setSelector(combatV.getSelector()+1);
			break;
		}
		case Keys.UP:
		{	
			if(combatV.getSelector()!=0 && combatV.getSelector()!=2)
				combatV.setSelector(combatV.getSelector()-1);			break;
		}
		case Keys.LEFT:
		{	
			if(combatV.getSelector()>1)
				combatV.setSelector(combatV.getSelector()-2);			break;
		}
		case Keys.RIGHT:
		{	
			if(combatV.getSelector()<2)
				combatV.setSelector(combatV.getSelector()+2);			break;
		}
		case Keys.W:
		{
			combatV.weather();
		}


		}


		return false;//
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

	public int getTextinc() {
		// TODO Auto-generated method stub
		return textinc;
	}
}
