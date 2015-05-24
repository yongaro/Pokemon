package pokemon.vue;

import java.util.Vector;

import pokemon.controle.CombatListener;
import pokemon.controle.CombatMenuPokemon;
import pokemon.launcher.MapScreen;
import pokemon.launcher.MyGdxGame;
import pokemon.modele.Capacite;
import pokemon.modele.Combat;
import pokemon.modele.Pkm;
import pokemon.modele.UniteStockage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;


public class CombatV extends GameScreen{
	MyGdxGame mygdxgame;
	Vector<BattleGroup> ennemies=new Vector<BattleGroup>();
	Vector<BattleGroup> friends=new Vector<BattleGroup>();
	PokeballGroup ballGroup=new PokeballGroup();
	BattleGroup a=new BattleGroup(new PokemonSprite(PokemonSprite.a1,"trainerS.png"));
	Texture fond=new Texture(Gdx.files.internal("battlebackground.png"));
	Pkm pkm=MyGdxGame.Jtest.getTeam()[0];
	boolean attackanimation;
	int atknumber=0;
	DialogBox dbox;
	float timer;
	int offset;
	int selector=0;
	int flag;
	Combat c;
	String[] actions = {"Attaque","Objets","Pkm","Fuite"};
	CombatMenuPokemon mpokemon;
	Music music;
	ParticleEffect e;
	ParticleEffect boom;
	int swap;
	CombatListener listener;
	WeatherEffect weather;
	
	public float cout=-1;
	public CombatV(Combat c,MyGdxGame mygdxgame,MapScreen ms){
		state=0;
		this.getStage().clear();
		this.mygdxgame=mygdxgame;
		dbox=new DialogBox(new Vector2(640,100),true);
		dbox.setMessage("Un pokemon sauvage apparait");
		for(int i=0;i<MyGdxGame.Jtest.getTeam().length;i++){
			if(MyGdxGame.Jtest.getTeam()[i].get(2)!=0){
				pkm=MyGdxGame.Jtest.getTeam()[i];
				System.out.println("Envoi "+pkm.getNom());
				break;
			}
		}
		this.c=c;
		for( int i=0;i<c.getPkmListe().length;i++){
			System.out.println(c.getPkmListe()[i].isIA());
			if(this.c.getPkmListe()[i].isIA())
			{
				ennemies.add(new BattleGroup(PokemonSprite.e1,c,this,c.getPkmListe()[i]));
			}
			else{
				friends.add(new BattleGroup(PokemonSprite.a1,c,this,c.getPkmListe()[i]));
				System.out.println("FRIENDS "+c.getPkmListe()[i].getPkm().getNom());
			}
		}
		stage.addActor(a);
		stage.addActor(ballGroup);
		for(BattleGroup g:ennemies)
		{
			stage.addActor(g);
			g.getpSprite().addSlideAction();
		}
		a.getpSprite().addSlideAction();
		stage.addActor(dbox);


		//Demarrage de la musique
		BattleSoundManager.begin();
		attackanimation=true;
		e=new ParticleEffect();
		boom=new ParticleEffect();
		listener=new CombatListener(mygdxgame, this, c,ms);
		
		Gdx.input.setInputProcessor(listener);
	}


	@Override
	public void dispose() {


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
	public void render(float arg0) {
		//if(c.getPCourant()!=null && c.getCibleCourante()!=null)
	//	System.out.println("PCOURRANT: "+c.getPCourant().getNom()+ "PCIBLE"+c.getCibleCourante().getNom());
		//else{
			//System.out.println("pcur iz null");
		//}
	//	System.out.println("STATE: "+state+"TEXT INC: "+listener.getTextinc());
//		System.out.println(Gdx.input.getInputProcessor());
		
		if(!c.bufferIsEmpty() && state>1){
			listener.getBuffer(c.readBuffer());
			c.resetBuffer();
			//retval=text.split("\n");
			//System.out.println("---"+text+"---");
			//System.out.println("RETVAL"+Arrays.toString(retval));
			//System.out.println("RETVALEND");
			//dbox.setWidth(width);
		//	dbox.setMessage(retval[0]);
			//textinc=1;
			

			attackanimation=true;
		}
		Gdx.gl.glClearColor(0f, 0f, 0f, 0.0f);
		// Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT| GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		shapeRenderer.setProjectionMatrix(this.getStage().getViewport().getCamera().combined);
		stage.getBatch().begin();

		stage.getBatch().draw(fond,0,0);

		stage.getBatch().end();

		switch(state){
		case 2:
			drawPanel();
			offset=0;
			stage.getBatch().begin();

			f.setColor(0.58f, 0.59f, 0.57f, 1);
			for(int i=0;i<actions.length;i++)//affichage des attaques
			{
				//f.draw(stage.getBatch(),cap.get().getNom(),220,113-offset);
				if(i<2)
					f.draw(stage.getBatch(),actions[i],330,85-offset);
				if(i==2)
					offset=0;
				if(i>=2)
					f.draw(stage.getBatch(),actions[i],490,85-offset);
				offset+=50;
			}
			stage.getBatch().end();
			break;
		case 3:
			drawPanel();
			stage.getBatch().begin();
			offset=0;
			f.setColor(0.58f, 0.59f, 0.57f, 1);
			for(int i=0;i<pkm.getCap().size();i++)//affichage des attaques
			{
				//f.draw(stage.getBatch(),cap.get().getNom(),220,113-offset);
				if(i<2)
					f.draw(stage.getBatch(),pkm.getCap().at(i).getNom(),330,85-offset);
				if(i==2)
					offset=0;
				if(i>=2)
					f.draw(stage.getBatch(),pkm.getCap().at(i).getNom(),490,85-offset);
				offset+=50;
			}
			stage.getBatch().end();
			dbox.setMessage(descGen(pkm.getCap().elementAt(selector)));
			break;

		}
		stage.act(arg0);
		stage.draw();
		
		stage.getBatch().begin();

		e.draw(stage.getBatch(), arg0);
		boom.draw(stage.getBatch(), arg0);
		if(weather!=null)
		weather.draw(stage.getBatch(), arg0);
		stage.getBatch().end();		
		super.drawUI(arg0);
		Gdx.gl.glDisable(GL20.GL_BLEND);
		
	}
	@Override
	public void resize(int arg0, int arg1) {
		super.resize(arg0, arg1);

	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}
	@Override
	public void show() {

		Gdx.input.setInputProcessor(listener);
		System.out.println(Gdx.input.getInputProcessor());

	}

	
	private String descGen(UniteStockage<Capacite> element) {
		String str="Type: "+element.get().getElement().name();
		str+="\n\n          PP: ";
		str+=element.getQte()+"/"+element.getQteMax();
		//str+="Puissance: "+element.get().getPower();

		return str;
	}

	private void drawPanel(){
		shapeRenderer.begin(ShapeType.Filled);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		shapeRenderer.setColor(1f, 1f, 1f, 0.8f);
		shapeRenderer.rect(310, 0,320,100);
		offset=50;
		if(selector<2)
			offset+=offset*selector;
		else
			offset+=offset*(selector-2);
		shapeRenderer.setColor(1f, 0, 0f, 0.2f);
		if(selector<2)
			shapeRenderer.rect(310,100-offset, 160, 50);
		else
			shapeRenderer.rect(470,100-(offset), 160, 50);
		shapeRenderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}

	void playEffect(){
		e.start();
		//e.scaleEffect(1.2f);
		boom.start();		
		//boom.scaleEffect(1.2f);
	}
	
	void playBoom(){
		boom.start();
	}
	
	public void weather(){
		weather=Weather.valueOf(c.getClimat().name()).get();
		weather.start();
	}
	
	public void animateHealthBars(){
		for(int i=0;i<ennemies.size();i++)
			ennemies.get(i).getHud().animate();
		for(int i=0;i<friends.size();i++)
			friends.get(i).getHud().animate();
	}

	public int getSelector() {
		return selector;
	}


	public void setSelector(int selector) {
		this.selector = selector;
	}


	public DialogBox getDbox() {
		return dbox;
	}


	public void setDbox(DialogBox dbox) {
		this.dbox = dbox;
	}


	public Pkm getPkm() {
		return pkm;
	}


	public void setPkm(Pkm pkm) {
		this.pkm = pkm;
	}


	public boolean healthbarLocked(){
		for(int i=0;i<friends.size();i++){
			if(friends.get(i).isLocked())
				return true;
		}
		for(int i=0;i<ennemies.size();i++){
			if(ennemies.get(i).isLocked())
				return true;
		}
		return false;
	}

	public void battleBegin(){
		if(a.getpSprite().getActions().size==0 && BattleSoundManager.next()){
			ballGroup.remove();
			a.getpSprite().hideTrainer();
			dbox.setMessage("En avant "+pkm.getNom());
			for(BattleGroup g:friends){
				stage.getActors().insert(0, g);
				g.getpSprite().popPokemon();					
			}
			state++;
		}
	}
	
	public boolean hideDeadIA(){ //retourne vrai si ia dead
		boolean ret=false;
		for(int i=0;i<c.getPkmListe().length;i++){//acteurs des ennemis
			//System.out.println(ennemies.get(i).getP().getNom()+ "=/SWAP/=" + ennemies.get(i).getP().getSwap());
			//System.out.println("COMBAT-"+c.getPkmListe()[i].getNom()+ "=/SWAP/=" + c.getPkmListe()[i].getSwap());
		//	System.out.println("COMBAT-"+c.getPkmListe()[i+1].getNom()+ "=/SWAP/=" + c.getPkmListe()[i+1].getSwap());
			if(ennemies.get(i).getpCombat().getSwap()!=-1){
				System.out.println("DEAD IA FOUND");
				ret=true;
				state=8; //swapping ennemies
				dbox.setMessage("Le pokemon ennemi est KO");
				ennemies.get(i).getpSprite().die();
				ennemies.get(i).getHud().hide();
				swap=ennemies.get(i).getpCombat().getSwap();
				//ennemies.get(i).setP(c.getEquipe2()[swap]);
				//ennemies.get(i).getHud().hideRight();
			}
			i++;
		}
		return ret;
	}
	public void swapIA()
	{
	ennemies.get(0).getpCombat().setSwap(-1);
	ennemies.get(0).setpCombat(c.getEquipe2()[swap]);
	//ennemiesHUD.get(0).setP(c.getEquipe2()[swap]);
	dbox.setWidth(width/2);
	dbox.setMessage("Que faire ?");
	state=2;
	c.setfreeze(false);
	}

	public void playAttackAnimations(){
		for(BattleGroup g:ennemies)
		{
			g.getpSprite().attack();
			g.getpSprite().hurt();
		}
		for(BattleGroup g:friends)
		{
			g.getpSprite().attack();
			g.getpSprite().hurt();
		}
	}
	
	public void swapPokemon(Pkm p,int indice){
		int i=0;
		state=7;
		dbox.setWidth(width);
		System.out.println(p.getNom()+"va remplacer"+pkm.getNom());
		dbox.setMessage("En avant "+p.getNom()+" !");
		for(i=0;i<c.getEquipe1().length;i++) //recherche du pokemon dnas l'equipe
		{
			if(p==c.getEquipe1()[i].getPkm())
			{
				System.out.println("Found"+c.getEquipe1()[i].getNom());
				break;
			}
		}

		if(pkm.get(2)!=0){
			//c.setfreeze(true);

			//	friends.get(0).getP().setSwap(indice);

			//System.out.println(c.getEquipe1()[i].getPkm());
			c.setAct(2,indice);
		}
		else{
			int j=0;
			System.out.println("DEAD SWAP");
			for(j=0;j<c.getPkmListe().length;j++) //recherche du pokemon a changer dans la liste des pokemons
			{
				if(pkm==c.getPkmListe()[j].getPkm()){ //quand j'ai trouve
					System.out.println("set act "+c.getPkmListe()[j].getNom()+" a "+indice);
					c.getPkmListe()[j].setSwap(indice);
				}
			}
		}
		pkm=p;
		friends.get(0).setpCombat(c.getEquipe1()[i]);
		//friendHUD.get(0).setP(c.getEquipe1()[i]);
	}

}
