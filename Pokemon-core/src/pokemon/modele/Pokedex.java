package pokemon.modele;

import java.io.IOException;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public enum Pokedex {
	Bulbizarre,Herbizarre,Florizarre,Salameche,Reptincel,Dracaufeu,Carapuce,Carabaffe,
	Tortank,Chenipan,Chrysacier,Papilusion,Aspicot,Coconfort,Dardargnan,Roucool,Roucoups,Roucarnage,
	Rattata,Rattatac,Piafabec,Rapasdepic,Abo,Arbok,Pikachu;
	
	protected Pkm pkm;
	
	protected String description;

	//liste des capacites apprises et evolutions lors de la progression
	protected Event[] events;
	//Base de la recompense d'experience donnee a l'adversaire en cas de défaite du pokemon
	protected int baseXP;
	//liste de capacites passives - chaque pokemon dispose d'une capP obtenue au hasard dans cette liste
	protected CapacitePassive[] capP;
	XmlReader reader = new XmlReader();

		public String getDescription() {
		return description;
	}
		
	public CapacitePassive[] getCapP() {
			return capP;
		}

	Pokedex(){
		Element pk; Element subtemp;
		try{
			Element root = reader.parse(Gdx.files.internal("xml/Pokemon.xml"));
			pk=root.getChildByName(this.name());
			if(pk.get("type2").compareTo("")==0){
				pkm=new Pkm(pk.getInt("ID"),pk.get("nom"),
						new int[]{pk.getInt("PV"),pk.getInt("ATT"),pk.getInt("DEF"),pk.getInt("ATTSP"),pk.getInt("DEFSP"),pk.getInt("VIT")},
						Type.valueOf(pk.get("type1")),null,pk.getInt("IAbh"));
			}
			else{
				pkm=new Pkm(pk.getInt("ID"),pk.get("nom"),
						new int[]{pk.getInt("PV"),pk.getInt("ATT"),pk.getInt("DEF"),pk.getInt("ATTSP"),pk.getInt("DEFSP"),pk.getInt("VIT")},
						Type.valueOf(pk.get("type1")),Type.valueOf(pk.get("type2")),pk.getInt("IAbh"));
			}
			//initialisation du tableau events
			subtemp=pk.getChildByName("CapacitePassive");
			capP=new CapacitePassive[subtemp.getChildCount()];
			for(int i=0;i<subtemp.getChildCount();i++){
				capP[i]=CapacitePassive.valueOf(subtemp.getChild(i).get("nom"));
			}
			description=pk.get("description");
			baseXP=pk.getInt("XP");
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public Pkm get(){ return this.pkm; }
	public CapacitePassive randCapP(Random rand){
		int res=(int)rand.nextInt(capP.length);
		return capP[res];
	}
	
}
	
