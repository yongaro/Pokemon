package pokemon.vue;

import pokemon.modele.Pkm;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BattleHud extends Actor{

	ShapeRenderer shapeRenderer=new ShapeRenderer();
	SpriteBatch b=new SpriteBatch();
	Pkm p;
	BitmapFont f=new BitmapFont(Gdx.files.internal("pkm1.fnt"), Gdx.files.internal("pkm1.png"), false);
	int oldpv;
	float[] pvperc;
	int speed;
	boolean increase;
	public BattleHud(int x,int y,Pkm p){
		this.setX(x);
		this.setY(y);
		this.setHeight(55);
		this.setWidth(210);
		this.p=p;
		oldpv=p.get(2);
		pvperc=new float[2];
		pvperc[1]=(160*p.get(2))/p.getmax(2);
		pvperc[0]=pvperc[1];
		b.getProjectionMatrix().setToOrtho2D(0, 0, 640, 320);
		speed=30;
		increase=false;
	}

	public void act(float delta){

		if(p.get(2)!=oldpv)
		{
			if(p.get(2)>oldpv){
				increase=true;
			}
			else
				increase=false;
			pvperc[1]=(160*p.get(2))/p.getmax(2);

			oldpv=p.get(2);
		}
		if(pvperc[1]!=pvperc[0]){
			if(increase){
				if(pvperc[0]<pvperc[1])
					pvperc[0]+=delta*speed;
				else
					pvperc[0]=pvperc[1];
			}
			else{
			if(pvperc[0]>pvperc[1])
				pvperc[0]-=delta*speed;				
			else
				pvperc[0]=pvperc[1];
		}

	}
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
	f.draw(b, p.getNom()+"                 Lv"+p.get(0), this.getX()+30, this.getY()+this.getHeight()-f.getCapHeight());
	b.end();
}

}
