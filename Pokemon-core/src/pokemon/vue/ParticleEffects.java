package pokemon.vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;

public enum ParticleEffects {
	Eau,Feu,Plante,Poison,Vol,Electrique,Sol,Roche,Psy,Spectre,Insecte,Tenebre,Acier,Combat,Glace,Dragon,Normal,Fee;
	
	ParticleEffect hit;
	ParticleEffect specialLeft;
	ParticleEffect specialRight;
	
	ParticleEffects(){
		System.out.println(this.name());
		hit= new ParticleEffect(); hit.load(Gdx.files.internal("effect/"+this.name()+"Hit"), Gdx.files.internal("effect"));
		
		
		specialLeft = new ParticleEffect(); specialLeft.load(Gdx.files.internal("effect/"+this.name()+"SpecialLeft"), Gdx.files.internal("effect"));
		specialLeft.setPosition(170,150); 
		specialLeft.scaleEffect(0.9f);
		
		specialRight = new ParticleEffect(); specialRight.load(Gdx.files.internal("effect/"+this.name()+"SpecialRight"), Gdx.files.internal("effect"));
		specialRight.setPosition(500,270);
		specialRight.scaleEffect(0.9f);
	}
	
	public void AdvEffect(CombatV cbv){
		
		this.hit.setPosition(170, 150);
		cbv.e=this.specialRight;
		cbv.boom=this.hit;
	}
	
	public void JoueurEffect(CombatV cbv){
		
		this.hit.setPosition(500,270);
		cbv.e=this.specialLeft;
		cbv.boom=this.hit;
	}
	public void AdvSelf(CombatV cbv){
		this.hit.setPosition(500,270);
		cbv.boom=this.hit;

	}
	
	public void JoueurSelf(CombatV cbv){
		this.hit.setPosition(170, 150);
		cbv.boom=this.hit;

	}
	
}
