package pokemon.vue;

import pokemon.modele.Combat;
import pokemon.modele.PokemonCombat;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BattleGroup extends Group{
	
	BattleHud hud;
	PokemonSprite pSprite;
	PokemonCombat pCombat;
	CombatV combatV;
	Combat combat;
	

	BattleGroup(PokemonSprite p){
		pSprite=p;
		pSprite.setGroup(this);
		this.addActor(pSprite);
		
	}
	
	
	BattleGroup(Vector2 v,Combat c,CombatV cV,PokemonCombat p){
		
		combatV=cV;
		combat=c;
		pCombat=p;
		hud=new BattleHud(this);
		pSprite=new PokemonSprite(v,this);
		this.addActor(pSprite);
		this.addActor(hud);
	}
	
	public PokemonCombat getpCombat() {
		return pCombat;
	}

	public void setpCombat(PokemonCombat pCombat) {
		//boolean alive=(this.pCombat.getPkm().get(2)>0);
		System.out.println("BEFORE "+this.pCombat.getNom());
		this.pCombat.setPokemon(pCombat.getPkm());
		pSprite.setP();
		hud.setP();
		System.out.println("AFTER "+this.pCombat.getNom());

	}

	public CombatV getCombatV() {
		return combatV;
	}

	public Combat getCombat() {
		return combat;
	}

	public PokemonSprite getpSprite() {
		return pSprite;
	}

	public BattleHud getHud() {
		return hud;
	}
	public Stage getStage(){
		if(combatV!=null)
		return combatV.getStage();
		else return null;
	}
	
	public boolean isLocked(){
		if(super.getStage()==null)
			return false;
		else{
		//System.out.println(" ISLOCKED :"+(hud.isLocked())+" || "+(pSprite.getActions().size!=0) +"  nbact:"+pSprite.getActions().size);
		return hud.isLocked() || pSprite.getActions().size!=0 || hud.getActions().size!=0;
			}
			}

}
