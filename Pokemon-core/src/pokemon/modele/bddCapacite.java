package pokemon.modele;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.*;

public enum bddCapacite {
	
	//Type Plante
	
	//Type Sol
	JetdeSable(0,100,0,"Jet de Sable","Lance du sable au visage de l'ennemi pour baisser sa precision.",Type.Sol,5,35,Statut.Normal,"08",100,0),
	//Type Electrique
	Tonnerre(90,100,10,"Tonnerre","Une grosse decharge electrique tombe sur l'ennemi. Peut aussi le paralyser.",Type.Electrique,5,10,Statut.Paralyse),
	//Type Normal
	Boularmure(0,100,0,"Boul'armure","",Type.Normal,5,40,Statut.Normal,"04",100,1),
	Charge(35,95,10,"Charge","",Type.Normal,3,35,Statut.Normal),
	//Type Psy
	ChocMental(50,100,10,"Choc Mental","",Type.Psy,5,25,Statut.Confus),
	Plenitude(0,100,0,"Plenitude","",Type.Psy,5,20,Statut.Normal,"056",100,1),
	Psyko(90,100,10,"Choc Mental","",Type.Psy,5,10,Statut.Normal,"06",10,0),
	PsychoBoost(140,90,10,"Psycho Boost","",Type.Psy,5,5,Statut.Normal,"05",75,1),
	RafalePsy(65,100,10,"Choc Mental","",Type.Psy,5,20,Statut.Confus),
	Repos(100,100,0,"Repos","",Type.Psy,5,10,'3'),
	Telekinesie(0,80,0,"Telekinesie","",Type.Psy,5,35,Statut.Normal,"08",100,0),
	Yoga(0,100,0,"Yoga","",Type.Psy,5,40,Statut.Normal,"03",100,1)
	//Type Tenebre
	;
	
	bddCapacite(int pw,int pre,int cc,String nom,String d,Type el,int type,int pp,Statut effet,String code,int proc,int fof){
			cap=new AtkChangeStats(pw,pre,cc,nom,d,el,type,pp,effet,code,proc,fof);
		
	}
	bddCapacite(int pw,int pre,int cc,String nom,String d,Type el,int type,int pp,Statut effet){
		cap=new Atk(pw,pre,cc,nom,d,el,type,pp,effet);
	}
	bddCapacite(int pw,int pre,int cc, String nom,String d,Type el,int type,int pp,char code){
		cap=new Heal(pw,pre,cc,nom,d,el,type,pp,code);
	}
	protected Capacite cap;
	
	public Capacite get(){
		return cap;
	}
	
	
	
	
}
