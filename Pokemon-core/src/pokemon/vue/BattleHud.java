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
	//PokemonCombat p;
	BitmapFont f=new BitmapFont(Gdx.files.internal("pkm1.fnt"), Gdx.files.internal("pkm1.png"), false);
	int oldpv;
	float[] pvperc; //pourcentage des pv pvperc[1] pourcentage actuel pvperc[1] pourcentage a atteindre
	int speed;
	boolean increase,locked,refresh;
	BattleGroup myGroup;
	
	public BattleHud(BattleGroup group){
		myGroup=group;
		this.setHeight(55);
		this.setWidth(210);
		if(myGroup.getpCombat().isIA()){
			this.setX(-210);
			this.setY(300);
			this.addAction(Actions.sequence(Actions.delay(0.5f),Actions.moveBy(220, 0,0.5f)));
		}
		else{
			this.setX(640);
			this.setY(110);
			this.addAction(Actions.sequence(Actions.delay(0.5f),Actions.moveBy(-220, 0,0.5f)));
		}
		oldpv=myGroup.getpCombat().getPkm().get(2);
		pvperc=new float[2];
		pvperc[1]=(160*myGroup.getpCombat().getPkm().get(2))/myGroup.getpCombat().getPkm().getmax(2);
		pvperc[0]=pvperc[1];
		b.getProjectionMatrix().setToOrtho2D(0, 0, 640, 360);
		speed=50;
		increase=false;
		locked=false;
		refresh=false;
	}

	public void hide(){
		if(myGroup.getpCombat().isIA())
			this.addAction(Actions.sequence(Actions.delay(0.3f),Actions.moveBy(-210, 0, 0.2f),Actions.visible(false)));
		else
			this.addAction(Actions.sequence(Actions.delay(0.3f),Actions.moveBy(210, 0, 0.2f),Actions.visible(false)));
	}

	public void act(float delta){
		super.act(delta);
		if(myGroup.getCombatV().getTextinc()==2 && myGroup.getpCombat().getPkm().get(2)!=oldpv){		
				locked=true; //verouillage
		}
		if(locked){
				//System.out.println("LOCKED");
				if(myGroup.getpCombat().getPkm().get(2)>oldpv){ //Monter ou descendre les pdvs ?
					increase=true;
				}
				else{
					increase=false;
				}
				//calcul du nouveau pourcentage
				pvperc[1]=(160*myGroup.getpCombat().getPkm().get(2))/myGroup.getpCombat().getPkm().getmax(2); 
				oldpv=myGroup.getpCombat().getPkm().get(2);	
				//System.out.println(pvperc[0]+"/"+pvperc[1]);

			}
			if(pvperc[1]!=pvperc[0]){
				if(increase){
					if(pvperc[0]<pvperc[1]) //pourcentage non max non atteint augmentation du pourcentage
						pvperc[0]+=delta*speed; 
					else //le pourcentage est atteint
					{
						pvperc[0]=pvperc[1]; //pour les erreur d'approx
						locked=false; //deverouillage
						//System.out.println("UNLOCKED");
					}
				}
				else{
					if(pvperc[0]>pvperc[1])//pourcentage non max non atteint diminution du pourcentage
						pvperc[0]-=delta*speed;				
					else
					{pvperc[0]=pvperc[1];//pour les erreur d'approx
					locked=false;//deverouillage
					//System.out.println("UNLOCKED");
					}
				}
				oldpv=(int) ((pvperc[0]*myGroup.getpCombat().getPkm().getmax(2))/160.0);
			}
			if(locked && pvperc[1]==pvperc[0])
				locked=false;
			
		}
	



	public void setP() {
		pvperc[1]=(160*myGroup.getpCombat().getPkm().get(2))/myGroup.getpCombat().getPkm().getmax(2);
		pvperc[0]=pvperc[1];
		oldpv=myGroup.getpCombat().getPkm().get(2);
		locked=false;
		if(myGroup.pCombat.isIA())
		addAction(Actions.parallel(Actions.moveBy(210, 0,0.2f),Actions.visible(true)));
		else
			addAction(Actions.parallel(Actions.moveBy(-210, 0,0.2f),Actions.visible(true)));

		System.out.println("HUD NOW VISIBLE"+this.isVisible());
	}

	public void draw (Batch batch, float parentAlpha) {

		shapeRenderer.setProjectionMatrix(myGroup.getStage().getCamera().combined);
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
		f.draw(b, myGroup.getpCombat().getPkm().getNom()+"                 Lv"+myGroup.getpCombat().getPkm().get(0), this.getX()+30, this.getY()+this.getHeight()+2);
		f.setScale(0.5f);
		f.draw(b,"HP",getX()+20,getY()+38);
		f.draw(b,oldpv+"/"+myGroup.getpCombat().getPkm().getmax(2), this.getX()+140, this.getY()+25);
		b.end();

	}
	public boolean isLocked(){
		return locked;
	}




}
