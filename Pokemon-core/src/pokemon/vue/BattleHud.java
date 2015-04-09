package pokemon.vue;

import pokemon.modele.Pkm;
import pokemon.modele.PokemonCombat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

public class BattleHud extends Actor{

	ShapeRenderer shapeRenderer=new ShapeRenderer();
	SpriteBatch b=new SpriteBatch();
	PokemonCombat p;
	BitmapFont f=new BitmapFont(Gdx.files.internal("pkm1.fnt"), Gdx.files.internal("pkm1.png"), false);
	int oldpv;
	float[] pvperc;
	int speed;
	boolean increase,locked,refresh;
	CombatV combatv;

	public BattleHud(CombatV vuecombat,PokemonCombat pokemonCombat){
		p=pokemonCombat;
		combatv=vuecombat;	
		this.setHeight(55);
		this.setWidth(210);
		if(p.isIA()){
			this.setX(-210);
			this.setY(300);
			this.addAction(Actions.sequence(Actions.delay(0.5f),Actions.moveBy(220, 0,0.5f)));
		}
		else{
			this.setX(640);
			this.setY(110);
			this.addAction(Actions.sequence(Actions.delay(0.5f),Actions.moveBy(-220, 0,0.5f)));
		}

		oldpv=pokemonCombat.getPkm().get(2);
		pvperc=new float[2];
		pvperc[1]=(160*pokemonCombat.getPkm().get(2))/pokemonCombat.getPkm().getmax(2);
		pvperc[0]=pvperc[1];
		b.getProjectionMatrix().setToOrtho2D(0, 0, 640, 360);
		speed=50;
		increase=false;
		locked=false;
		refresh=false;
	}

	public void hideRight(){
		this.addAction(Actions.sequence(Actions.delay(0.5f),Actions.moveBy(210, 0, 0.5f),Actions.visible(false),Actions.moveBy(-210, 0)));

	}

	public void act(float delta){
		super.act(delta);
		if(combatv.getTextinc()==2){
			if(p.getPkm().get(2)!=oldpv)
			{

				locked=true;
				System.out.println("LOCKED");
				if(p.getPkm().get(2)>oldpv){
					increase=true;
				}
				else{
					increase=false;
				}
				pvperc[1]=(160*p.getPkm().get(2))/p.getPkm().getmax(2);
				oldpv=p.getPkm().get(2);
			}
			if(pvperc[1]!=pvperc[0]){
				System.out.println(pvperc[0]+"/"+pvperc[1]);
				if(increase){
					if(pvperc[0]<pvperc[1])
						pvperc[0]+=delta*speed;
					else
					{
						pvperc[0]=pvperc[1];
						locked=false;
						System.out.println("UNLOCKED");
					}
				}
				else{
					if(pvperc[0]>pvperc[1])
						pvperc[0]-=delta*speed;				
					else
					{pvperc[0]=pvperc[1];
					locked=false;
					System.out.println("UNLOCKED");}
				}

			}
		}
	}



	public void setP(PokemonCombat p) {
		this.p = p;
		pvperc[1]=(160*p.getPkm().get(2))/p.getPkm().getmax(2);
		pvperc[0]=pvperc[1];
		oldpv=p.getPkm().get(2);
		this.setVisible(true);
	}

	public void draw (Batch batch, float parentAlpha) {
		shapeRenderer.setProjectionMatrix(this.getStage().getViewport().getCamera().combined);
		shapeRenderer.begin(ShapeType.Filled);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		shapeRenderer.setColor(0f, 0f, 0f, 0.8f);
		shapeRenderer.rect(getX(), getY(), this.getWidth()-20,20);
		shapeRenderer.rect(getX()+20, getY()+getHeight()-35, this.getWidth()-20,20);
		shapeRenderer.triangle(getX(), getY()+20, getX()+20, getY()+20, getX()+20, getY()+40);//left triangle
		shapeRenderer.triangle(getX()+this.getWidth()-20,this.getY(), getX()+this.getWidth()-20, getY()+20, getX()+this.getWidth(), getY()+20);
		shapeRenderer.setColor(1f, 1f, 1f, 0.6f);
		shapeRenderer.rect(getX()+35,getY()+getHeight()-27,160,8);
		shapeRenderer.setColor(0f, 0.95f, 0f, 1f);
		shapeRenderer.rect(getX()+35,getY()+getHeight()-27,pvperc[0],8);
		shapeRenderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
		b.begin();
		f.setScale(0.7f);
		f.setColor(1,1, 1, 1);
		f.draw(b, p.getPkm().getNom()+"                 Lv"+p.getPkm().get(0), this.getX()+30, this.getY()+this.getHeight()+2);
		f.setScale(0.5f);
		f.draw(b,"HP",getX()+20,getY()+38);
		f.draw(b,oldpv+"/"+p.getPkm().getmax(2), this.getX()+140, this.getY()+25);
		b.end();

	}
	public boolean isLocked(){
		return locked;
	}




}
