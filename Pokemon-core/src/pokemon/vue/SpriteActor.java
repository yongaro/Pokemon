package pokemon.vue;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class SpriteActor extends Actor{

	Sprite s;
	public SpriteActor() {
		this.setBounds(0, 0,0,0);

	}
		
	SpriteActor(FileHandle f)
	{
		s=new Sprite(new Texture(f));
		
		this.setBounds(0, 0,s.getWidth(),s.getHeight());

	}
	
	public SpriteActor(FileHandle f,int x,int y) {
		this(f);
		this.setBounds(x, y,s.getWidth(),s.getHeight());
		
	}

	public void setSprite(FileHandle f){
			s=new Sprite(new Texture(f));
			this.setWidth(s.getWidth());
			this.setHeight(s.getHeight());

	}
	
	@Override
	public void draw(Batch batch,float parentAlpha){
		s.setScale(this.getScaleX(), this.getScaleY());
		s.setBounds(this.getX(),this.getY(),this.getWidth(),this.getHeight());
		s.draw(batch);
	}
}
