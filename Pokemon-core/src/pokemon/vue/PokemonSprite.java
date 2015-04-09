package pokemon.vue;

import pokemon.modele.Combat;
import pokemon.modele.PokemonCombat;
import pokemon.modele.Statut;
import pokemon.modele.Type;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class PokemonSprite extends Actor{
	
	public static Vector2 e1=new Vector2(60,220);
	public static Vector2 e2=new Vector2(-100,220);
	public static Vector2 a1=new Vector2(440,60);
    public Texture s=new Texture(Gdx.files.internal("Sprites/6.png"));
    SpriteBatch b=new SpriteBatch();
    boolean finished,attackplayer=false;
    ShapeRenderer shapeRenderer=new ShapeRenderer();
	Sound son = Gdx.audio.newSound(Gdx.files.internal("Sound/4.ogg"));
	Music sonatq = Gdx.audio.newMusic(Gdx.files.internal("Sound/normaldamage.WAV"));
	Music soneatq;
	PokemonCombat p;
	Combat c;
	CombatV combatv;

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
    
    public PokemonSprite(Vector2 v, PokemonCombat pc, Combat c,CombatV combatv){
    	super();
    	this.c=c;
    	this.combatv=combatv;
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
    public void attack(){
    	if(p.getPkm()==c.getPCourant().getPkm()){
    		//System.out.println("CALLING ATTACK "+p.getNom());
    		if (p.isIA()){
    			this.addAction(Actions.sequence(Actions.moveBy(-50, -40,0.2f
    					),Actions.moveBy(50, 40,0.2f)));
    			if(c.getCapCur()!=null){
    				System.out.println(c.getCapCur().getElement().name());
    				ParticleEffects.valueOf(c.getCapCur().getElement().name()).AdvEffect(combatv);
    			}
    		}
    		else{
    			this.addAction(Actions.sequence(Actions.moveBy(50, 40,0.2f),Actions.moveBy(-50, -40,0.2f)));
    			if(c.getCapCur()!=null){
    				System.out.println(c.getCapCur().getElement().name());
    				ParticleEffects.valueOf(c.getCapCur().getElement().name()).JoueurEffect(combatv);
    			}
    			combatv.playEffect();
    		}
    		switch(c.getCapCur().getElement()){
    		case Eau:
    			soneatq=Gdx.audio.newMusic(Gdx.files.internal("Sound/127-Water02.ogg"));
    			break;
    		case Feu:
    			soneatq=Gdx.audio.newMusic(Gdx.files.internal("Sound/117-Fire01.ogg"));break;
    		case Glace:
    			soneatq=Gdx.audio.newMusic(Gdx.files.internal("Sound/121-Ice02.ogg"));break;
    		case Electrique:
    			soneatq=Gdx.audio.newMusic(Gdx.files.internal("Sound/123-Thunder01.ogg"));break;
    		case Plante:
    			soneatq=Gdx.audio.newMusic(Gdx.files.internal("Sound/102-Attack14.ogg"));break;
    		default:
    			soneatq=Gdx.audio.newMusic(Gdx.files.internal("Sound/090-Attack02.ogg"));break;
    		}
    		soneatq.play();
    		
    	}    
    		
    	}

    public void hurt(){
    	if(p.getPkm()==c.getCibleCourante().getPkm()){
    		//System.out.println("CALLING HURT "+p.getNom());

    		if (p.isIA()){
    			this.addAction(Actions.sequence(Actions.delay(0.6f),Actions.moveBy(-30,0,0.2f
    					),Actions.moveBy(60,0,0.2f),Actions.moveBy(-30,0,0.2f)));
    			
    		}
    			
    		else
    			this.addAction(Actions.sequence(Actions.delay(0.6f),Actions.moveBy(30, 0,0.2f),Actions.moveBy(-60, 0,0.2f),Actions.moveBy(30, 0,0.2f)));
    		this.addAction(Actions.sequence(Actions.visible(false),Actions.delay(0.2f),Actions.visible(true)));
    		Timer.schedule(new Task() {
                
                @Override
                public void run() {
              	sonatq.play();
              //	Timer.instance().clear();
                }
            
             },0.3f);
    	
    	}

    	}

    public void popPokemon(){
    	this.setScale(1, 1);
    	this.setVisible(false);
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
    /*	if(p!=null && p.getPkm().get(2)==0)
    	{
    		die();
    		p=null;
    	}*/
    	if( (getScaleX()==1.5) && !finished)
    	{
    		finished=true;
    		b.setColor(Color.WHITE);
    		son.play(0.5f);	
    	}
    }
    public void setP(PokemonCombat p) {
		this.p = p;
		System.out.print("SWAPPED "+p);
		finished=false;
		this.popPokemon();
		if(p.isIA())
			s=new Texture(Gdx.files.internal("Sprites/"+p.getPkm().getID()+".png"));
		else
			s=new Texture(Gdx.files.internal("Sprites/back/"+p.getPkm().getID()+".png"));
	}

	public PokemonCombat getP() {
		return p;
	}
  
}

