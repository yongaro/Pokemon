package pokemon.vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import pokemon.controle.JoueurController;
import pokemon.modele.Joueur;

public class JoueurVue {
	private JoueurController controller;
	private TextureAtlas atlaswest=new TextureAtlas(Gdx.files.internal("player/w_right.pack"));
	private TextureAtlas atlaseast=new TextureAtlas(Gdx.files.internal("player/w_right.pack"));
	private TextureAtlas atlassouth=new TextureAtlas(Gdx.files.internal("player/w_south.pack"));
	private TextureAtlas atlasnorth=new TextureAtlas(Gdx.files.internal("player/w_north.pack"));
	private Animation rightwalk=new Animation(1f/5f,atlaswest.getRegions());
	private Animation leftwalk=new Animation(1f/5f,atlaseast.getRegions());
	private Animation southwalk=new Animation(1f/5f,atlassouth.getRegions());
	private Animation northwalk=new Animation(1f/5f,atlasnorth.getRegions());
	private Animation a=rightwalk;
	float animationtime;
	boolean move=false;
	private Joueur j;
	
	//Constructeurs
	public JoueurVue() {
		j = new Joueur();
		for(TextureRegion r:atlaseast.getRegions())
		{
			r.flip(true, false);
		}
	}
	public JoueurVue(Joueur j) {
		this.j = j;
		for(TextureRegion r:atlaseast.getRegions())
		{
			r.flip(true, false);
		}
	}
	
	//Fonctionalites principales
	public void render(float delta, Batch batch) {
		if(j.isMoving()){
			animationtime+=delta;
			move=true;
		}
		else{
			if(a.getKeyFrameIndex(animationtime)<2 && move) //on va jusqua la derniere frame
				animationtime+=delta;
			else{
				move=false;
				animationtime=0;
			}				
		}
		batch.draw(a.getKeyFrame(animationtime,true), j.getPos().x, j.getPos().y);
	}
	
	//Accesseurs
	public Joueur getJ() {
		return j;
	}
	
	public void setJ(Joueur j) {
		this.j = j;
	}
}
