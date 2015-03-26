package pokemon.vue;

import pokemon.modele.NPC;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/* La classe NPCVue permet de stocker et afficher l'apparence d'un NPC */

public class NPCVue {
	private TextureAtlas atlaswest;
	private TextureAtlas atlaseast;
	private TextureAtlas atlassouth;
	private TextureAtlas atlasnorth;
	private Animation eastwalk;
	private Animation westwalk;
	private Animation southwalk;
	private Animation northwalk;
	private Animation a;
	
	private NPC npc;
	
	//Constructeurs
	public NPCVue() {
		initAnimation("npcs/npc");
		for(TextureRegion r:atlaseast.getRegions())
		{
			r.flip(true, false);
		}
		npc = new NPC();
	}
	public NPCVue(NPC npc) {
		initAnimation("npcs/npc");
		for(TextureRegion r:atlaseast.getRegions())
		{
			r.flip(true, false);
		}
		this.npc = npc;
	}
	public NPCVue(NPC npc, String path) {
		initAnimation(path);
		for(TextureRegion r:atlaseast.getRegions())
		{
			r.flip(true, false);
		}
		this.npc = npc;
	}
	
	//Fonctions privees
	private void initAnimation(String path) {
		atlaswest=new TextureAtlas(Gdx.files.internal(path + "/right.pack"));
		atlaseast=new TextureAtlas(Gdx.files.internal(path + "/right.pack"));
		atlassouth=new TextureAtlas(Gdx.files.internal(path + "/south.pack"));
		atlasnorth=new TextureAtlas(Gdx.files.internal(path + "/north.pack"));
		eastwalk=new Animation(1f/5f,atlaswest.getRegions());
		westwalk=new Animation(1f/5f,atlaseast.getRegions());
		southwalk=new Animation(1f/5f,atlassouth.getRegions());
		northwalk=new Animation(1f/5f,atlasnorth.getRegions());
		a=southwalk;
	}
	
	//Fonctionnalitees principales
	public void render(Batch batch, float deltatime) {
		switch(npc.getOrientation()) {
		case East:
			a = eastwalk;
			break;
		case North:
			a = northwalk;
			break;
		case South:
			a = southwalk;
			break;
		case West:
			a = westwalk;
			break;
		default:
			break;
		}
		batch.draw(a.getKeyFrame(0), npc.getPos().x, npc.getPos().y+16);
	}
}
