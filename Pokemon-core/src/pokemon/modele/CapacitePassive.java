package pokemon.modele;

public enum CapacitePassive {
	Absenteisme("Absenteisme","Le pokemon attaque 1 fois sur 2",9),
	AbsorbEau("Absorb Eau","Les attaques Eau regenerent 1/4 des PV",3),
	AbsorbVolt("Absorb Volt","Les attaques Electriques regenerent 1/4 des PV",3),
	Agitation("Agitation","Augmente ATT de 50% mais baisse PRE de 20%",9),
	AirLock("Air Lock","Annule les effets du climat",8),
	AntiBruit("Anti-Bruit","Insensible aux capacites sonores",3),
	ArmuMagma("ArmuMagma","Immunise contre le Gel",4),
	Armurbaston("ArmurBaston","Ne peut subir de coup critique",3),
	Attention("Attention","Immunise contre la peur",3),
	Benet("Benet","Immunise contre charme et provocation",4),
	Brasier("Brasier","Augmente la puissance des attaques Feu de 50% si le pkm a moins d'1/3 de ses PV",9),
	Calque("Calque","Copie la Capacite Speciale de l'adversaire",8),
	Chlorophylle("Chlorophylle","Double la vitesse si le soleil est au Zenith",11),
	CielGris("Ciel Gris","Insensible aux effets climatiques",7),
	CorpsArdent("Corps ardent","30% de chance de bruler l'adversaire qui attaque au CaC",1),
	CorpsSain("Corps Sain","Empeche les stats de diminuer",5),
	Crachin("Crachin","Il pleut tant que le pokemon est en jeu",11),
	Cran("Cran","Augmente l'attaque de 50% si le statut du pkm change",9),
	Cuvette("Cuvette","S'il pleut, regenre 1/16 des pv a chaque fin de tour",7),
	Deguisement("Deguisement","Change le type du pkm en celui de la derniere attaque subie",3),
	EcailleSpeciale("Ecaille Speciale","Augmente DEF de 50% si le pkm change de statut",3),
	Echauffement("Echauffement","Immunise contre la paralysie",3),
	EcranPoudre("Ecran Poudre","Immunise contre tous les changements de statut",4),
	Engrais("Engrais","Augmente la puissance des attaques Plante de 50% si le pkm a moins d'1/3 de ses PV",9),
	Essaim("Essaim","Augmente la puissance des attaques Insect de 50% si le pkm a moins d'1/3 de ses PV",9),
	Fermete("Fermete","Le pokemon ne peut etre mis K.O en un tour",3),
	ForcePure("Force Pure","Double l'attaque du pokemon",9),
	Fuite("Fuite","Peut toujours fuire face aux pokemon sauvages",9),
	GardeMystique("Garde Mystique","Ne subit que les attaques super efficaces",3),
	Glissade("Glissade","Double la vitesse par temps de pluie",11),
	Glue("Glue","Empeche la perte de l'objet tenu",3),
	HyperCutter("Hyper Cutter","L'attaque ne peut pas diminuer",3),
	IgnifuVoile("Ignifu-Voile","Immunise contre la brulure",4),
	Insomnia("Insomnia","Empeche le pkm de s'endormir",4),
	Intimidation("Intimidation","Baisse l'attaque de l'adversaire quand le pkm entre en combat",0),
	Isograisse("IsoGraisse","Divise par 2 les degats de type Feu ou Glace subit",3),
	JoliSourire("Joli Sourire","30% de chance de provoquer l'attirance du pokemon qui attaque",1),
	Levitation("Levitation","Immunise contre le type Sol",3),
	MarqueOmbre("Marque Ombre","Empeche changement ou fuite du pokemon adverse",12),
	MedicNature("MedicNature","Enleve les changements de statut si le pokemon sort du combat",1),
	Meteo("Meteo","Change de type avec le climat",10),
	Minus("Minus","Augmente ATTSP de 50% si un partenaire possede 'Plus'",8),
	Moiteur("Moiteur","Ne peut pas etre touche par des capacite auto-destructrices",3),
	Mue("Mue","A chaque fin de tour, 33% de chance d'etre guerit d'un changement de statut",7),
	OeilCompose("Oeil Compose","Double la precision",9),
	Paratonnerre("Paratonnerre","Attire les attaques de types electriques et augmente l'attaque speciale",3),
	PeauDure("Peau Dure","Fait perdre 1/16 des pv de chaque attaquant au CaC",3),
	Plus("Plus","Augmente ATTSP de 50% si un partenaire possede 'Minus'",8),
	PointPoison("PointPoison","30% de chance d'empoisonner le pokemon qui attaque au Cac",3),
	PoseSpore("Pose Spore","30% d'infliger empoisonnement ou paralysie ou sommeil au pokemon qui attaque au Cac",3),
	Pression("Pression","L'adversaire double son utilisation de PP",3),
	Puanteur("Puanteur","Les attaques physiques ont 10% de chance d'effrayer la cible",9),
	RegardVif("Regard vif","Empeche la perte de precision",5),
	SableVolant("Sable Volant","Tempete de sable tant que le pokemon est en jeu",11),
	Secheresse("Secheresse","Zenith tant que le pokemon est en jeu",11),
	Serenite("Serenite","2 fois plus de chance de declencher l'effet secondaire d'une attaque",9),
	Statik("Statik","30% de chance de paralyser le pokemon qui attaque au Cac",1),
	Suintement("Suintement","Les attaques absorbantes de l'adversaire lui font perdre des PV",3),
	TempoPerso("Tempo Perso","Immunise contre la confusion",4),
	TeteDeRoc("Tete de Roc","Immunise contre les degats du recul",9),
	Torche("Torche","Annule les degats de type Feu subits et augmente la puissance du type Feu de 50%",3),
	Torrent("Torrent","Augmente la puissance des attaques Eau de 50% si le pkm a moins d'1/3 de ses PV",9),
	Turbo("Turbo","Augmente la vitesse a chaque fin de tour",7),
	Vaccin("Vaccin","Immunise contre le poison",4),
	VoileSable("Voile Sable","Augmente l'esquive de 20% pendant les tempetes de sable",11);
	

	protected String nom;
	protected String description;
	//Conditions d'activation de la capacite
	protected int flag;
	//0-Le pkm arrive en combat |
	//1-Le pkm est touche par une attaque physique | 2- touche par attaque speciale | 3- attaque quelconque
	//4-le statut du pokemon change | 5-Les statistiques changent
	//6-Activation en debut de tour | 7-Activation en fin de tour | 8-debut ET fin de tour
	//9 Le pokemon attaque | 10-la meteo change
	//11-le pokemon est en jeu.( 0 + 1 + 8 )
	//12-action de l'adversaire
	//13-Le pokemon quitte le combat
	
	
	CapacitePassive(String nm,String desc,int f){
		nom=nm; description=desc; flag=f;
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
		System.out.println("Statk de "+cible.nom+" paralyse "+user.nom);
	}
	
	public static void AirLock(Combat c){ 
		c.climat=Climat.Normal;
		System.out.println("Le climat redevient normal");
	}
	
	public static void Crachin(Combat c){ 
		c.climat=Climat.Pluie;
		System.out.println("Le climat devient pluvieux");
	}

}
