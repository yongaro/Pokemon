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
	private boolean move;
	private float animationTime;
	
	private NPC npc;
	
	//Constructeurs
	public NPCVue() {
		initAnimation("npcs/npc");
		animationTime = 0;
		for(TextureRegion r:atlaseast.getRegions())
		{
			r.flip(true, false);
		}
		npc = new NPC();
	}
	public NPCVue(NPC npc) {
		initAnimation("npcs/npc");
		animationTime = 0;
		for(TextureRegion r:atlaseast.getRegions())
		{
			r.flip(true, false);
		}
		this.npc = npc;
	}
	public NPCVue(NPC npc, String path) {
		initAnimation(path);
		animationTime = 0;
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
		if(npc.getMoveDistance() > 0) {
			animationTime += deltatime;
			move = true;
		}
		else {
			if(a.getKeyFrameIndex(animationTime)<2 && move) //on va jusqua la derniere frame
				animationTime+=deltatime;
			else {
				move=false;
				animationTime=0;
			}	
		}
		batch.draw(a.getKeyFrame(animationTime, true), npc.getPos().x, npc.getPos().y+16);
	}
	
	public void updatePosition() {
		//TODO
	}
}
