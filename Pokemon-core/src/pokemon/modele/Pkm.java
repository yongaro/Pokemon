package pokemon.modele;

import java.util.*;

import pokemon.annotations.Tps;

@Tps(nbhours=8)
public class Pkm implements Qmax,Comparator<Pkm>,Comparable<Pkm>,Infos{
	protected int ID;
	protected String nom;
	//0-niveau | 1-XP | 2-PV | 3-ATT | 4-DEF | 5-ATTSP | 6-DEFSP | 7-VIT | 8-Precision (100) | 9-Esquive (5% de base)
	protected int[][] stats;
	protected Statut statut;
	protected Vector<Statut> supTemp;
	protected int[] IV;
	protected int[] EV;
	protected int prevXpPal;
	protected Nature personnalite;
	//1 - Agressif(BestDmg) || 2 - Dot || 3 - Control A remplir que pour les pkm sous IA
	protected int IAbh;
	protected Vector <Type> type;
	protected Stockage<Capacite> cap;
	protected CapacitePassive capP;
	protected Objet objTenu;
	protected long attacks;
	
	public Pkm(Pkm base,int level){
		//creation d'un nouveau Pokemon par copie de la base bl
		this.ID=base.ID;
		this.nom=base.nom;
		int i=0;
		this.stats=new int[10][2];
		for(i=0;i<8;i++){this.stats[i][0]=base.stats[i][0]; this.stats[i][1]=base.stats[i][1];}
		this.stats[8][0]=100;this.stats[8][1]=100;
		this.stats[9][0]=5;this.stats[9][1]=5;
		this.stats[0][0]=level; this.stats[0][1]=level;
		this.statut=Statut.Normal;
		this.supTemp=new Vector<Statut>();
		this.personnalite=Nature.getRandom(this);
		this.type=new Vector <Type>();
		for(Type t: base.type){ this.type.addElement(t);}
		this.cap=new Stockage<Capacite>(); cap.max=4;
		i=0;
		//Gestion de l'aleatoire et de la mise a niveau
		Random rand=new Random();
		this.capP=Pokedex.values()[ID-1].randCapP(rand);
		int IV=(int)rand.nextInt(31)+1;
		this.IV=new int[6];
		this.EV=new int[6];
		this.IV[0]=IV;
		for(i=3;i<8;i++){
			IV=(int)rand.nextInt(31)+1;
		}
		//niveau
		this.stats[0][0]=level;
		this.AjustementStats();
		/*Fast - 0.8(Current Level)^3
		Medium Fast - (Current Level)^3
		Medium Slow - 1.2(Current Level)^3 - 15(Current Level)^2 + 100(Current Level) - 140
		Slow - 1.25(Current Level)^3*/
		//PV= (int)((2xBS+IV)*level/100+S) pour les pv S= level+10 pour le reste 5
	}
	
	//constructeur pour creer les pokemon de base du pokedex
	public Pkm(int ID,String nom,int[] stats,Type t1,Type t2){
		//copie des stats
		this.ID=ID;
		this.statut=Statut.Normal;
		this.nom=nom;
		int i=0;
		this.stats=new int[10][2];
		this.stats[0][0]=0; this.stats[1][0]=0;
		this.stats[0][1]=0; this.stats[1][1]=0;
		for(i=0;i<6;i++){this.stats[i+2][0]=stats[i]; this.stats[i+2][1]=stats[i];}
		this.stats[8][0]=100;this.stats[8][1]=100;
		this.stats[9][0]=5;this.stats[9][1]=5;
		//copie du/des type(s)
		this.type=new Vector <Type>();
		if(t1!=null){this.type.addElement(t1);} if(t2!=null){this.type.addElement(t2);}
		i=0;
		//copie des capacites
		this.cap=new Stockage<Capacite>(); cap.max=4;
		i=0;
	}
	
	public void AjustementStats(){
		Pkm base=Pokedex.values()[ID-1].get();
		//XP
		prevXpPal=this.stats[1][0]=(int)Math.pow(this.stats[0][0],3);
		this.stats[1][1]=(int)Math.pow(this.stats[0][0]+1,3);
		//PV
		this.stats[2][0]=this.stats[2][1]=(int)(((2*base.stats[2][1])+(EV[0]/4)+IV[0])*(double)(stats[0][0]/100.0)+(stats[0][0]+10));
		for(int i=3;i<8;i++){
			this.stats[i][0]=this.stats[i][1]=(int)((2*base.stats[i][1]+IV[i-2])*(double)(stats[0][0]/100.0)+5);
		}
		this.personnalite.Applique(this);
		stats[0][1]=(int)((stats[1][0]-prevXpPal)/(stats[1][1]-prevXpPal));
	}
	
	
	public Event eventAt(int level){
		Pokedex base=Pokedex.values()[ID-1];
		
		for(int i=0;i<base.events.length;i++){
			if(base.events[i].niveau==level){
				return base.events[i];
			}
		}
		return null;
	}
	
	public void evolution(Pkm cible){
		this.ID=cible.ID;
		this.AjustementStats();
		System.out.println("Felicitations ! Votre "+nom+" a evolue en "+cible.nom);
		this.nom=cible.nom;
	}
	
	public void addXP(int xp){
		Event temp;
		
		this.stats[1][0]+=xp;
		if(stats[1][0]>= stats[1][1] && stats[0][0]<100){
			stats[0][0]++;
			temp=eventAt(stats[0][0]);
			if(temp!=null){
				if(temp.evolution!=null){ this.evolution(temp.evolution); }
				if(temp.cap!=null){ temp.cap.teach(this); }
			}
			this.AjustementStats();
		}
		else{
			stats[0][1]=(int)((stats[1][0]-prevXpPal)/(stats[1][1]-prevXpPal));
		}
	}
	
	public int get(int indice){
		if(indice>=0 && indice<10){
			return stats[indice][0];
		}
		return -1;
	}
	
	public int getmax(int indice){
		if(indice>=0 && indice<10){
			return stats[indice][1];
		}
		return -1;
	}
	
	
	public Nature getNat(){ return personnalite;}
	
	
	
	public Vector<Type> getType() {
		return type;
	}

	public CapacitePassive getCapP() {
		return capP;
	}

	public void setIAbh(int iabh){ IAbh=iabh; }
	
	public void buff(String code){
		for(char c:code.toCharArray()){
		if(c=='3'){
			System.out.println("l'attaque de "+this.nom+" augmente");
			this.stats[3][0]=(this.stats[3][0]+(int)(this.stats[3][0]*0.15));
		}
		if(c=='4'){
			System.out.println("la defense de "+this.nom+" augmente");
			this.stats[4][0]=(this.stats[4][0]+(int)(this.stats[4][0]*0.15));
		}
		if(c=='5'){
			System.out.println("l'attaque speciale de "+this.nom+" augmente");
			this.stats[5][0]=(this.stats[5][0]+(int)(this.stats[5][0]*0.15));
		}
		if(c=='6'){
			System.out.println("la defense speciale de "+this.nom+" augmente");
			this.stats[6][0]=(this.stats[6][0]+(int)(this.stats[6][0]*0.15));
		}
		if(c=='7'){
			System.out.println("la vitesse de "+this.nom+" augmente");
			this.stats[7][0]=(this.stats[7][0]+(int)(this.stats[7][0]*0.15));
		}
		if(c=='8'){
			System.out.println("la precision de "+this.nom+" augmente");
			this.stats[8][0]=(this.stats[8][0]+(int)(this.stats[8][0]*0.15));
		}
		if(c=='9'){
			if(this.stats[9][0]<=90){
			System.out.println("l'esquive de "+this.nom+" augmente");
			this.stats[9][0]+=10;
			}
			else{
				System.out.println("l'esquive de "+this.nom+" ne peut plus augmenter");
			}
		}
		}
	}

	public void debuff(String code){
		for(char c:code.toCharArray()){
		if(c=='3'){
			System.out.println("l'attaque de "+this.nom+" diminue");
			this.stats[3][0]=(this.stats[3][0]-(int)(this.stats[3][0]*0.15));
		}
		if(c=='4'){
			System.out.println("la defense de "+this.nom+" diminue");
			this.stats[4][0]=(this.stats[4][0]-(int)(this.stats[4][0]*0.15));
		}
		if(c=='5'){
			System.out.println("l'attaque speciale de "+this.nom+" diminue");
			this.stats[5][0]=(this.stats[5][0]-(int)(this.stats[5][0]*0.15));
		}
		if(c=='6'){
			System.out.println("la defense speciale de "+this.nom+" diminue");
			this.stats[6][0]=(this.stats[6][0]-(int)(this.stats[6][0]*0.15));
		}
		if(c=='7'){
			System.out.println("la vitesse de "+this.nom+" diminue");
			this.stats[7][0]=(this.stats[7][0]-(int)(this.stats[7][0]*0.15));
		}
		if(c=='8'){
			System.out.println("la precision de "+this.nom+" diminue");
			this.stats[8][0]=(this.stats[8][0]-(int)(this.stats[8][0]*0.15));
		}
		if(c=='9'){
			if(this.stats[9][0]>=5){
			System.out.println("l'esquive de "+this.nom+" diminue");
			 this.stats[9][0]-=5; }
			else{
				System.out.println("l'esquive de "+this.nom+" ne peut plus diminuer");
			}
		}
		}
	}
	

	
	public void heal(int pv){
		stats[2][0]+=pv;
		if(stats[2][0]>stats[2][1]){stats[2][0]=stats[2][1];}
	}
	
	public void infliger(int dmg){
		if(dmg>=this.stats[2][0]){ this.stats[2][0]=0; this.statut=Statut.KO; this.supTemp.clear(); }
		else{ stats[2][0]-=dmg; }
	}
	
	public void reset(){
		int i=0;
		for(i=2;i<10;i++){
			this.stats[i][0]=this.stats[i][1];
		}
	}
	
	public int qmax(){return 1;}
	public int getID(){return ID;}
	public String getNom(){return nom;}
	public String getDesc(){return "";}
	public String getInfos(){return "";}
	public long getAttacks(){return attacks;}
	public void setAttacks(long l){attacks=l;}
	
	public Stockage<Capacite> getCap(){
		return cap;
	}
	
	public Statut getStatut() {
		return statut;
	}

	public void add(Capacite Cap){
		cap.add(Cap);
	}
	
	public void give(Objet o){
		if(this.objTenu==null){
			this.objTenu=o;
		}
	}
	
	public int compare(Pkm p1,Pkm p2){
		if(p1.stats[7][0]<p2.stats[7][0]){return -1;}
		if(p1.stats[7][0]>p2.stats[7][0]){return 1;}
		
		return 0;
	}
	public int compareTo(Pkm p){
		if(this.stats[7][0]<p.stats[7][0]){return -1;}
		if(this.stats[7][0]>p.stats[7][0]){return 1;}
		
		return 0;
	}
	public String toString(){
		
		return (ID+" "+nom+" LV."+stats[0][0]+
				"\n XP:"+stats[1][0]+"/"+stats[1][1]+
				"\n PV:"+stats[2][0]+"/"+stats[2][1]+" "+statut+
				"\n"+objTenu.nom+
				"\n ATT:"+stats[3][1]+" DEF:"+stats[4][1]+
				"\n ASP:"+stats[5][1]+" DSP:"+stats[6][1]+
				"\n VIT:"+stats[7][1]+" "+personnalite);
	}
	
	
}
