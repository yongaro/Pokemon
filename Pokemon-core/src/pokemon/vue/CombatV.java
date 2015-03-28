package pokemon.vue;



import pokemon.launcher.MyGdxGame;
import pokemon.modele.Capacite;
import pokemon.modele.Pkm;
import pokemon.modele.UniteStockage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class CombatV extends GameScreen implements InputProcessor{
	MyGdxGame mygdxgame;
	PokemonSprite e1=new PokemonSprite(PokemonSprite.e1,"Sprites/10.png");
	PokemonSprite e2=new PokemonSprite(PokemonSprite.e2,"Sprites/99.png");
	PokemonSprite a=new PokemonSprite(PokemonSprite.a1,"trainerS.png");
	Texture fond=new Texture(Gdx.files.internal("battlebackground.png"));
	Pkm[] pkms=MyGdxGame.Jtest.getTeam();
	PokemonSprite p1;
	DialogBox dbox;
	float timer;
	int state=0;
	int offset;
	int selector=0;
	 String[] actions = {"Attaque","Objets","Pkm","Fuite"};
	public CombatV(){
		
		Gdx.input.setInputProcessor(this);
		dbox=new DialogBox();
		dbox.setMessage("Un pokemon sauvage apparait");
	}
	
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	
	}
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render(float arg0) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shapeRenderer.setProjectionMatrix(this.getStage().getViewport().getCamera().combined);
				stage.getBatch().begin();
			stage.getBatch().draw(fond,0,0);
			stage.getBatch().end();
		switch(state){
		
		case 2:
	
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(1f, 1f, 1f, 1f);
			shapeRenderer.rect(310, 0,310,100);
			offset=50;
			if(selector<2)
				offset+=offset*selector;
			else
				offset+=offset*(selector-2);
			shapeRenderer.setColor(1f, 0, 0f, 1f);
			if(selector<2)
			shapeRenderer.rect(310,100-offset, 5, 50);
			else
			shapeRenderer.rect(450,100-(offset), 5, 50);
			shapeRenderer.end();
			offset=0;
			stage.getBatch().begin();
			
			f.setColor(0.58f, 0.59f, 0.57f, 1);
			for(int i=0;i<actions.length;i++)//affichage des attaques
			{
				//f.draw(stage.getBatch(),cap.get().getNom(),220,113-offset);
				if(i<2)
				f.draw(stage.getBatch(),actions[i],330,85-offset);
				if(i==2)
					offset=0;
				if(i>=2)
					f.draw(stage.getBatch(),actions[i],480,85-offset);
				//f.draw(stage.getBatch(),cap.getQte()+"/"+cap.getQteMax(),385-f.getBounds(cap.getQte()+" / "+cap.getQteMax()).width,113-offset);
				offset+=50;
			}
			stage.getBatch().end();

		}
		stage.act(arg0);
		stage.draw();
		
	}
	@Override
	public void resize(int arg0, int arg1) {
		super.resize(arg0, arg1);
		
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void show() {
		
		stage.addActor(e1);
		stage.addActor(e2);
		e1.addSlideAction();
		e2.addSlideAction();
		stage.addActor(a);
		a.addSlideAction();
		stage.addActor(dbox);
		
		
	}///////


	@Override
	public boolean keyDown(int arg0) {
		switch(arg0){
		case Keys.ENTER:
		{
			if(state==0 && stage.getActors().get(2).getActions().size==0){
				a.hideTrainer();
				dbox.setMessage("En avant "+pkms[0].getNom());
				p1=new PokemonSprite(new Vector2(20,60),"Sprites/back/"+pkms[0].getID()+".png");
				p1.popPokemon();
				stage.getActors().insert(0, p1);
				state++;
				break;
				}
			if(state==1){
				dbox.setWidth(width/2);
				dbox.setMessage("Que faire ?");
				state++;
			}
			if(state==2){
				if(selector==0){
					dbox.setMessage(descGen(pkms[0].getCap().elementAt(0)));
				}
			}
			break;
		}
		case Keys.DOWN:
		{	System.out.println("Gne");
			if(selector==0 || selector==2)
				selector++;
			break;
		}
		case Keys.UP:
		{	System.out.println("Gne");
			if(selector!=0 && selector!=2)
				selector--;
			break;
		}
		case Keys.LEFT:
		{	
			if(selector>1)
				selector-=2;
			break;
		}
		case Keys.RIGHT:
		{	
			if(selector<2)
				selector+=2;
			break;
		}

		}
		return false;//
	}


	private String descGen(UniteStockage<Capacite> element) {
		String str="Type :"+element.get().getElement().name();
		str+="\n\n          PP: ";
		str+=element.getQte()+"/"+element.getQteMax();
		//str+="Puissance: "+element.get().getPower();
		
		return str;
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
