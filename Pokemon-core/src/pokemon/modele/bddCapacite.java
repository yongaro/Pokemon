package pokemon.modele;

import java.io.IOException;

import pokemon.annotations.Cpx;
import pokemon.annotations.Tps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.*;

@Tps(nbhours=5)
public enum bddCapacite {
	Balayage,Blizzard,Charge,CrochetVenin,CrocDeMort,CruAile,Eclair,Griffe,JetPierres,MegaSangsue,
	PoingDeFeu,PoingEclair,PoingGlace,RafalePsy,Repos,Seisme,Surf,Tonnerre,JetDeSable;

	
	protected  XmlReader reader = new XmlReader();
	protected Capacite cap;
	
	
	
	 bddCapacite(){
		 try {
			Element root,e;
			root = reader.parse(Gdx.files.internal("xml/Capacite.xml"));
			e=root.getChildByName(this.name());
			
			//La capacite courante est une Atk
			if(e.get("class").compareTo("Atk")==0){ 
				this.cap=new Atk(e.getInt("power"),e.getInt("pre"),e.getInt("CC"),e.get("nom"),e.get("description"),
						Type.valueOf(e.get("element")),e.getInt("type"),e.getInt("maxPP"),Statut.valueOf(e.get("effet")),e.getInt("effetProc"));
			}
			//La capacite courante est une AtkChangeStats		
			if(e.get("class").compareTo("AtkCS")==0){
				this.cap=new AtkChangeStats(e.getInt("power"),e.getInt("pre"),e.getInt("CC"),e.get("nom"),e.get("description"),
						Type.valueOf(e.get("element")),e.getInt("type"),e.getInt("maxPP"),Statut.valueOf(e.get("effet")),e.getInt("effetProc"),e.get("Tstats"),
						e.getInt("ChangeProc"),e.getInt("fof"));
			}
			//La capacite courante est une AtkRepet
			if(e.get("class").compareTo("AtkRep")==0){
				this.cap=new AtkRepet(e.getInt("power"),e.getInt("pre"),e.getInt("CC"),e.get("nom"),e.get("description"),
						Type.valueOf(e.get("element")),e.getInt("type"),e.getInt("maxPP"),Statut.valueOf(e.get("effet")),e.getInt("effetProc"));
				System.out.println(cap);
			}
			//La capacite courante est un Heal
			if(e.get("class").compareTo("Heal")==0){
				this.cap=new Heal(e.getInt("power"),e.getInt("pre"),e.getInt("CC"),e.get("nom"),e.get("description"),
						Type.valueOf(e.get("element")),e.getInt("type"),e.getInt("maxPP"),e.getInt("code"),Statut.valueOf(e.get("effet")));
			}
			if(e.get("class").compareTo("AtkSoin")==0){
				this.cap=new AtkSoin(e.getInt("power"),e.getInt("pre"),e.getInt("CC"),e.get("nom"),e.get("description"),
						Type.valueOf(e.get("element")),e.getInt("type"),e.getInt("maxPP"),Statut.valueOf(e.get("effet")),e.getInt("effetProc"));
			}
			if(e.get("class").compareTo("AtkRecul")==0){
				this.cap=new AtkRecul(e.getInt("power"),e.getInt("pre"),e.getInt("CC"),e.get("nom"),e.get("description"),
						Type.valueOf(e.get("element")),e.getInt("type"),e.getInt("maxPP"),Statut.valueOf(e.get("effet")),e.getInt("effetProc"),e.getInt("recul"));
			}
			cap.ID=e.getInt("ID");
		}
		catch (IOException e1) { e1.printStackTrace(); }
	}
	public Capacite get(){ return cap; }
	
}
