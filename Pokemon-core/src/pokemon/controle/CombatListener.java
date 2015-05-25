package pokemon.controle;

import java.util.Arrays;

import pokemon.launcher.MapScreen;
import pokemon.launcher.MyGdxGame;
import pokemon.modele.AtkMeteo;
import pokemon.modele.Combat;
import pokemon.vue.BattleSoundManager;
import pokemon.vue.CombatV;

import com.badlogic.gdx.Gdx;


public class CombatListener extends GameInput{

	CombatV combatV;
	Combat c;
	String[] retval;
	int width=640;
	CombatMenuPokemon mpokemon;
	MyGdxGame myGdxGame;
	int flag;
	int textinc;
	MapScreen ms;
	
	public CombatListener(MyGdxGame myGdxGame,CombatV combatV,Combat c,MapScreen ms) {
		this.combatV=combatV;
		this.c=c;
		this.myGdxGame=myGdxGame;
		this.ms=ms;
		Gdx.input.setInputProcessor(this);

	}

	public void getBuffer(String s){
		retval=s.split("\n");
		System.out.println("RETVAL"+Arrays.toString(retval));
		combatV.getDbox().setWidth(width);
		combatV.getDbox().setMessage(retval[0]);
		textinc=1;
		if(c.getFini()!=0){
			BattleSoundManager.end();
			myGdxGame.setScreen(ms);
		}
		if(c.isMeteo()){
			System.out.println("CAY LA METEO");
			combatV.setState(10);
		}
		else
			combatV.setState(5);
	}
	
	@Override
	public boolean keyDown(int arg0) {
		//if(c.getPCourant()!=null)
		System.out.println("Called");
		
		if(!combatV.healthbarLocked()){
			if(this.myGdxGame.getScreen()==combatV){
				return super.keyDown(arg0);
			}

	
		}
		return false;
	}
	public boolean touchDown(int x, int y, int pointer, int button) {			
		if(combatV==myGdxGame.getScreen()){
			combatV.setTouched(true);
			return true;
			}
		else
			return false;
	}



	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(combatV==myGdxGame.getScreen()){
			combatV.setTouched(false);
			v.set(screenX, screenY);
			combatV.getStage().screenToStageCoordinates(v);
			System.out.println(v);
			return super.touchUp(v);}
		else
			return false;
	}
	
	
	

	public int getTextinc() {
		// TODO Auto-generated method stub
		return textinc;
	}

	@Override
	void handleA() {
		// TODO Auto-generated method stub
		if(combatV.getState()==0){
			combatV.battleBegin();//set state to 1
			return;
		}
		if(combatV.getState()==1){
			System.out.println("UNLOCKING THREAD STATE 1");
			c.setfreeze(false);
			combatV.getDbox().setWidth(width/2);
			combatV.getDbox().setMessage("Que faire ?");
			//state++;
			combatV.setState(combatV.getState()+1);
			combatV.setSelector(0);
			return;

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
			return;

		}
		if(combatV.getState()==3){ //selection atq
		//	attackanimation=true;
			c.setAct(flag, combatV.getSelector());
			//System.out.println(" SETACT "+flag+","+selector);
			//selector=0;
			combatV.setSelector(0);
		}
		if(combatV.getState()==5)//lecture du buffer
		{//si qqchose a lire
			if(textinc==1){
				System.out.println("LAUNCHING ANIMATIONS");
				if(c.getCapCur() instanceof AtkMeteo){
					combatV.weather();
					}
				else
				combatV.playAttackAnimations();

			}
			//state++;
			combatV.setState(combatV.getState()+1);
			return;

		}

		if(combatV.getState()==6){

			if(textinc<retval.length)
			{
				//if(!combatV.healthbarLocked())
					combatV.getDbox().setMessage(retval[textinc++]);
					System.out.println(retval[textinc-1]);
				if(textinc==2){
					combatV.animateHealthBars();
				}
			}
			else{
				System.out.println("ICI");
				//textinc=1;

				if(combatV.hideDeadIA())
				{
					return;
				}
				
				if(combatV.getPkm().get(2)==0){
					mpokemon=new CombatMenuPokemon(myGdxGame,combatV);
				}

				if(c.getPCourant().getPkm()==combatV.getPkm())
				{
					combatV.setState(2);
					combatV.getDbox().setWidth(width/2);
				
				combatV.getDbox().setMessage("Que faire ?");
				}
				//System.out.println("UNLOCKING THREAD STATE6");

				c.setfreeze(false);
			}
			return;

		}

		if(combatV.getState()==7){ //pokemon change par le joueur
			c.setfreeze(false);
			
			combatV.setState(2);
			return;

		}
		if(combatV.getState()==8){ //swapIA
			combatV.swapIA();
			return;

		}
		if(combatV.getState()==10){
			combatV.weather();
			combatV.setState(11);
			combatV.animateHealthBars();
			return;

		}
		if(combatV.getState()==11){
			c.setfreeze(false);
			combatV.getDbox().setWidth(width/2);
			combatV.getDbox().setMessage("Que faire ?");
			combatV.setState(2);
			return;

		}
	}

	@Override
	void handleB() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void handleLeft() {
		// TODO Auto-generated method stub
		if(combatV.getSelector()>1)
			combatV.setSelector(combatV.getSelector()-2);
	}

	@Override
	void handleRight() {
		// TODO Auto-generated method stub
		if(combatV.getSelector()<2)
			combatV.setSelector(combatV.getSelector()+2);
	}

	@Override
	void handleUp() {
		// TODO Auto-generated method stub
		if(combatV.getSelector()!=0 && combatV.getSelector()!=2)
			combatV.setSelector(combatV.getSelector()-1);
	}

	@Override
	void handleDown() {
		// TODO Auto-generated method stub
		if(combatV.getSelector()==0 || combatV.getSelector()==2)
			combatV.setSelector(combatV.getSelector()+1);
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
