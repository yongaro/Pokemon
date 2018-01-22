package pokemon.vue;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class menuOptions extends GameScreen {

	public menuOptions() {
		// TODO Auto-generated constructor stub
		state=1;
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		shapeRenderer.setProjectionMatrix(stage.getViewport().getCamera().combined);
		shapeRenderer.begin(ShapeType.Filled);
		/*Drawing top tabs*/
		shapeRenderer.setColor(0.58f, 0.59f, 0.57f, 1);
		shapeRenderer.rect(0, height-30, 154, 30);	       
		shapeRenderer.rect(162,height-30, 154, 30);
		shapeRenderer.rect(324, height-30, 154, 30);
		shapeRenderer.setColor(0.85f, 0.85f, 0.85f, 1);
		shapeRenderer.rect(486, height-30, 154, 30);
		/*Drawing backGround*/
		shapeRenderer.rect(0,0,width, height-30);
		shapeRenderer.setColor(0.58f, 0.59f, 0.57f, 1);
		shapeRenderer.rect(width/4,100,(width/2), 150);

		shapeRenderer.end();
		
	
		stage.getBatch().begin();
				f.setColor(1, 1, 1, 1);

		f.setScale(1.2f);
		f.draw(stage.getBatch(),"Pokemons",18, height-3);

		f.draw(stage.getBatch(),"Inventaire",160+13, height-3);
		f.draw(stage.getBatch(),"PokeMatos",324+13, height-3);
		f.setColor(0.58f, 0.59f, 0.57f, 1);
		f.draw(stage.getBatch(),"Options",488+30, height-3);
		f.setColor(0.85f, 0.85f, 0.85f, 1);
		if(state==1)
		f.setColor(1, 1, 1, 1);
		f.draw(stage.getBatch(),"Sauvegarder", width/2-(f.getBounds("Sauvegarder").width)/2, 220);
		if(state==2)
			f.setColor(1, 1, 1, 1);
		else
			f.setColor(0.85f, 0.85f, 0.85f, 1);
		f.draw(stage.getBatch(),"Sauvegarder et Quitter", width/2-(f.getBounds("Sauvegarder et Quitter").width)/2, 160);

		stage.getBatch().end();
		this.drawUI(delta);
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

}
