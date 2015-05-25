package pokemon.vue;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class PokemonSprite extends Actor{
	
	public static Vector2 e1=new Vector2(60,220);
	public static Vector2 e2=new Vector2(-100,220);
	public static Vector2 a1=new Vector2(440,60);
    public Texture s;
    SpriteBatch b=new SpriteBatch();
    boolean finished,attackplayer=false;
    ShapeRenderer shapeRenderer=new ShapeRenderer();
	Sound son;
	Music sonatq = Gdx.audio.newMusic(Gdx.files.internal("Sound/normaldamage.WAV"));
	Music soneatq;
	BattleGroup myGroup;

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
    public void setGroup(BattleGroup g)
    {
    	myGroup=g;
    }
    public PokemonSprite(Vector2 v, BattleGroup group){
    	super();
    	myGroup=group;
    	if(myGroup.getpCombat().isIA()){
    		s=new Texture(Gdx.files.internal("Sprites/"+myGroup.getpCombat().getPkm().getID()+".png"));
    		this.setBounds(60, 220, s.getWidth()*1.2f, s.getHeight()*1.2f);
    	}
    		
    	else{
 
    		s=new Texture(Gdx.files.internal("Sprites/back/"+myGroup.getpCombat().getPkm().getID()+".png"));
       	   	this.setBounds(10, 60, s.getWidth()*1.2f, s.getHeight()*1.2f);

    	}
    	son = Gdx.audio.newSound(Gdx.files.internal("Sound/"+myGroup.getpCombat().getPkm().getID()+".ogg"));
	
    	pos=v;
    	b.getProjectionMatrix().setToOrtho2D(0, 0,640,360);
    }
    
    public void addSlideAction(){
    	if(myGroup.getpCombat()!=null && myGroup.getpCombat().isIA())
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
    	if(myGroup.getpCombat().isIA())
    		this.addAction(Actions.sequence(Actions.moveBy(150,0, 0.3f),Actions.visible(false),Actions.moveBy(-150, 0,0.3f)));
    	else
    		this.addAction(Actions.sequence(Actions.moveBy(-150,0, 0.3f),Actions.visible(false),Actions.moveBy(150, 0,0.3f)));

    	
    }
    public void attack(){
    	if(myGroup.getpCombat().getPkm()==myGroup.getCombat().getPCourant().getPkm()){

    		System.out.println("CALLING ATTACK "+myGroup.getpCombat().getNom());
    		if (myGroup.getpCombat().isIA()){
    			this.addAction(Actions.sequence(Actions.moveBy(-50, -40,0.2f
    					),Actions.moveBy(50, 40,0.2f)));
    			if(myGroup.getCombat().getCapCur()!=null){
    				System.out.println(myGroup.getCombat().getCapCur().getElement().name());
    				if(myGroup.getCombat().getendOfTurn()){
    					ParticleEffects.valueOf(myGroup.getCombat().getCapCur().getElement().name()).AdvSelf(myGroup.getCombatV());}
    				else
    					ParticleEffects.valueOf(myGroup.getCombat().getCapCur().getElement().name()).AdvEffect(myGroup.getCombatV());
    			}
    		}
    		else{
    			this.addAction(Actions.sequence(Actions.moveBy(50, 40,0.2f),Actions.moveBy(-50, -40,0.2f)));
    			if(myGroup.getCombat().getCapCur()!=null ){
    				System.out.println(myGroup.getCombat().getCapCur().getElement().name());
    				if(myGroup.getCombat().getendOfTurn()){
    					ParticleEffects.valueOf(myGroup.getCombat().getCapCur().getElement().name()).JoueurSelf(myGroup.getCombatV());}
    				else
    					ParticleEffects.valueOf(myGroup.getCombat().getCapCur().getElement().name()).JoueurEffect(myGroup.getCombatV());
    			}
    			
    		}
    		if(myGroup.getCombat().getCapCur().getType()!=3)
    			myGroup.getCombatV().playEffect();
    		else{
    			myGroup.getCombatV().playBoom();
    		}
    		
    		switch(myGroup.getCombat().getCapCur().getElement()){
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
    	if(myGroup.getpCombat().getPkm()==myGroup.getCombat().getCibleCourante().getPkm()){
    		//System.out.println("CALLING HURT "+p.getNom());

    		if (myGroup.getpCombat().isIA()){
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
    	if(myGroup.getpCombat()!=null)
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
    		if(son!=null)
    		son.play(0.5f);	
    	}
    }
    public void setP() {
		//this.p.setPokemon(p.getPkm());
		//System.out.print("SWAPPED "+p);
		finished=false;
		this.popPokemon();
		if(myGroup.getpCombat().isIA())
			s=new Texture(Gdx.files.internal("Sprites/"+myGroup.getpCombat().getPkm().getID()+".png"));
		else
			s=new Texture(Gdx.files.internal("Sprites/back/"+myGroup.getpCombat().getPkm().getID()+".png"));
		son=Gdx.audio.newSound(Gdx.files.internal("Sound/"+myGroup.getpCombat().getPkm().getID()+".ogg"));
	}


  
}

