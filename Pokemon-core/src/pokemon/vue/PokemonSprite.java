package pokemon.vue;

import pokemon.modele.PokemonCombat;
import pokemon.modele.Statut;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class PokemonSprite extends Actor{
	public static Vector2 e1=new Vector2(60,220);
	public static Vector2 e2=new Vector2(-100,220);
	public static Vector2 a1=new Vector2(440,60);
    public Texture s=new Texture(Gdx.files.internal("Sprites/6.png"));
    SpriteBatch b=new SpriteBatch();
    boolean finished=false;
    ShapeRenderer shapeRenderer=new ShapeRenderer();
	Sound son = Gdx.audio.newSound(Gdx.files.internal("Sound/4.ogg"));
	PokemonCombat p;
	
    private Vector2 pos;
    public PokemonSprite(Vector2 v,String nom){
    	super();	
    	if(nom!="")
    		s=new Texture(Gdx.files.internal(nom));
    	pos=new Vector2(v);
   	   	this.setBounds(pos.x, pos.y, s.getWidth()*1.2f, s.getHeight()*1.2f);
   	   		
	        b.getProjectionMatrix().setToOrtho2D(0, 0,640,360);

    }
    ////
    
    public PokemonSprite(Vector2 v, PokemonCombat pc){
    	super();
    	p=pc;
    	if(pc.isIA()){
    		s=new Texture(Gdx.files.internal("Sprites/"+pc.getPkm().getID()+".png"));
    		this.setBounds(60, 220, s.getWidth()*1.2f, s.getHeight()*1.2f);
    	}
    		
    	else{
 
    		s=new Texture(Gdx.files.internal("Sprites/back/"+p.getPkm().getID()+".png"));
       	   	this.setBounds(10, 60, s.getWidth()*1.2f, s.getHeight()*1.2f);

    	}
    	pos=v;
    	b.getProjectionMatrix().setToOrtho2D(0, 0,640,360);
    }
    
    public void addSlideAction(){
    	if(p!=null && p.isIA())
    		this.addAction(Actions.moveBy(400, 0, 1.6f));
    	if(pos.x>320)
    		this.addAction(Actions.moveTo(pos.x-420, pos.y, 1.6f));
    	this.addAction(Actions.scaleTo(1.5f, 1.5f,1.6f));
    	b.setColor(0.2f, 0.2f, 0.2f,1); 
    }

    public void hideTrainer(){
    	if(pos.x>320)
   			this.addAction(Actions.sequence(Actions.moveTo(this.getX(), -70,0.5f),Actions.visible(false)));
    	
   	}
    
    public void die(){
    	if(p.isIA())
    		this.addAction(Actions.moveBy(0,150, 0.3f));
    	else
    		this.addAction(Actions.moveBy(0,-150, 0.3f));
    	
    }
    
    public void popPokemon(){
    	this.setVisible(false);
    	if(!p.isIA())
    	this.addAction(Actions.delay(0.5f,Actions.parallel(Actions.visible(true),Actions.scaleTo(1.5f, 1.5f,0.5f))));
    	
    }
    
    public void draw (Batch batch, float parentAlpha) {



    	b.begin();
    	if(p!=null)
    	//System.out.println(p.getPkm().getNom()+"  "+getX());
    	if( (this.getX()<=461 && this.getX()>=459) || this.getX()==pos.x-420)
    		b.setColor(Color.WHITE);
    	b.draw(s,this.getX(),this.getY(),s.getWidth()*this.getScaleX(),s.getHeight()*this.getScaleY());
    	// b.setColor(color);
    	b.end();

    }
    public void act(float delta){
    
    	super.act(delta);
    	if(p!=null && p.getPkm().get(2)==0)
    		{
    		die();
    		p=null;
    		}
    	if( (getScaleX()==1.5) && !finished)
    		{
    		finished=true;
    		b.setColor(Color.WHITE);
    		son.play(0.1f);	
    		} 
    	if(p!=null && p.getPkm().getStatut()!=Statut.Normal)
    	{
    		System.out.println("NEW STATUT "+p.getPkm().getNom()+" "+p.getPkm().getStatut().name());
    	}
    }
  
}

