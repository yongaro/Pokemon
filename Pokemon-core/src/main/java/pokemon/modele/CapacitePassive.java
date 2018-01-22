package pokemon.modele;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public enum CapacitePassive {
	Absenteisme,AbsorbEau,AbsorbVolt,Agitation,AirLock,AntiBruit,ArmuMagma,Armurbaston,Attention,
	Benet,Brasier,Calque,Chlorophylle,CielGris,CorpsArdent,CorpsSain,Crachin,Cran,Cuvette,Deguisement,
	EcailleSpeciale,Echauffement,EcranPoudre,Engrais,Essaim,Fermete,ForcePure,Fuite,GardeMystique,Glissade,
	Glue,HyperCutter,IgnifuVoile,Insomnia,Intimidation,Isograisse,JoliSourire,Levitation,MarqueOmbre,
	MedicNature,Meteo,Minus,Moiteur,Mue,OeilCompose,Paratonnerre,PeauDure,Plus,PointPoison,PoseSpore,
	Pression,Puanteur,RegardVif,SableVolant,Secheresse,Serenite,Statik,Suintement,TempoPerso,TeteDeRoc,
	Torche,Torrent,Turbo,Vaccin,VoileSable;
	

	protected String nom;
	protected String description;
	//Conditions d'activation de la capacite
	protected int flag;
	//0-Le pkm arrive en combat
	//1-Le pkm est touche par une attaque physique | 2- touche par attaque speciale | 3- attaque quelconque
	//4-le statut du pokemon change | 5-Les statistiques changent
	//6-Activation en debut de tour | 7-Activation en fin de tour | 8-debut ET fin de tour
	//9 Le pokemon attaque(la capacitePassive est un bonus) | 10-la meteo change
	//11-le pokemon est en jeu.( 0 + 1 + 8 )
	//12-action de l'adversaire
	//13-Le pokemon quitte le combat
	protected Type buffedType;
	XmlReader reader = new XmlReader();
	
	CapacitePassive(){
		Element temp;
		try{
			Element root = reader.parse(Gdx.files.internal("xml/CapacitePassive.xml"));
			temp=root.getChildByName(this.name());
			nom=temp.get("nom"); description=temp.get("description"); flag=temp.getInt("flag");
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public String getDesc(){
		return description;
	}
	public String getNom(){
		return nom;
	}
	
	
	//prototype manquant la verification d'echauffement
	public static void Statik(Pkm user,Pkm cible){ 
		user.statut=Statut.Paralyse;
		System.out.println("Le Statik de "+cible.nom+" paralyse "+user.nom);
	}
	
	public static void AirLock(Combat c){ 
		c.climat=Climat.Normal;
		System.out.println("Le climat redevient normal");
	}
	
	public static void Crachin(Combat c){ 
		c.climat=Climat.Pluie;
		System.out.println("Crachin fait tomber la pluie");
	}

}
