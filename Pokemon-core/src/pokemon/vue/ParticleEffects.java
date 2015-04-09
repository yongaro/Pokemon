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
		hit= new ParticleEffect(); hit.load(Gdx.files.internal("effect/"+this.name()+"hit"), Gdx.files.internal("effect"));
		specialLeft = new ParticleEffect(); specialLeft.load(Gdx.files.internal("effect/"+this.name()+"SpecialLeft"), Gdx.files.internal("effect"));
		specialLeft.setPosition(170,150);
//		specialRight = new ParticleEffect(); specialRight.load(Gdx.files.internal("effect/"+this.name()+"SpecialRight"), Gdx.files.internal("effect"));
	}
	
	
}
