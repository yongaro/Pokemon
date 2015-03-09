package pokemon.modele;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public enum Pokedex {
	Bulbizarre,Herbizarre,Florizarre,Salameche,Reptincel,Dracaufeu,Carapuce,Carabaffe,
	Tortank,Rattata,Rattatac,Roucool,Roucoups,Roucarnage,Pikachu;
	
	
	Pokedex(){
		Element pk; Element subtemp;
		try{
			Element root = reader.parse(Gdx.files.internal("xml/Pokemon.xml"));
			pk=root.getChildByName(this.name());
			if(pk.get("type2").compareTo("")==0){
				pkm=new Pkm(pk.get("nom"),pk.get("description"),
						new int[]{pk.getInt("PV"),pk.getInt("ATT"),pk.getInt("DEF"),pk.getInt("ATTSP"),pk.getInt("DEFSP"),pk.getInt("VIT")},
						Type.valueOf(pk.get("type1")),null);
			}
			else{
				pkm=new Pkm(pk.get("nom"),pk.get("description"),
						new int[]{pk.getInt("PV"),pk.getInt("ATT"),pk.getInt("DEF"),pk.getInt("ATTSP"),pk.getInt("DEFSP"),pk.getInt("VIT")},
						Type.valueOf(pk.get("type1")),Type.valueOf(pk.get("type2")));
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public Pkm get(){ return this.pkm; }
	
	protected Pkm pkm;
	XmlReader reader = new XmlReader();
}
