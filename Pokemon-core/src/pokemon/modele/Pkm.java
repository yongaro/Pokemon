package pokemon.modele;

import java.util.*;

public class Pkm implements Qmax,Comparator<Pkm>,Comparable<Pkm>,Infos{
	private static int cpt=1;
	protected int ID;
	protected String nom;
	//0-niveau | 1-XP | 2-PV | 3-ATT | 4-DEF | 5-ATTSP | 6-DEFSP | 7-VIT | 8-Precision (100) | 9-Esquive (5% de base)
	protected int[][] stats;
	protected Statut statut;
	protected int[] IV;
	protected Nature personnalite;
	protected Vector <Type> type;
	protected Stockage<Capacite> cap;
	//PP[0][1] contient les PP max de l'attaque 1 PP[0][0] contient les PP courants
	protected CapacitePassive capP;
	
	protected static final Capacite[] bases={bddCapacite.Charge.cap};
	
	
	public Pkm(Pkm base,int level){
		//creation d'un nouveau Pokemon par copie de la base
		this.ID=base.ID;
		this.nom=base.nom;
		int i=0;
		this.stats=new int[10][2];
		for(i=0;i<8;i++){this.stats[i][0]=base.stats[i][0]; this.stats[i][1]=base.stats[i][1];}
		this.stats[8][0]=100;this.stats[8][1]=100;
		this.stats[9][0]=5;this.stats[9][1]=5;
		this.stats[0][0]=level; this.stats[0][1]=level;
		this.statut=Statut.Normal;
		this.personnalite=Nature.getRandom(this);
		this.capP=CapacitePassive.Statik;
		this.type=new Vector <Type>();
		for(Type t: base.type){ this.type.addElement(t);}
		this.cap=new Stockage<Capacite>(); cap.max=4;
		i=0;
		//this.levelinglist=new Hashtable<Integer,Capacite>();
		//for(Integer key: base.levelinglist.keySet()){
			//this.levelinglist.put(key,base.levelinglist.get(key));	
		//}
		//Gestion de l'aleatoire et de la mise a niveau
		Random rand=new Random();
		int IV=(int)rand.nextInt(32);
		this.IV=new int[6];
		this.IV[0]=IV;
		//niveau
		this.stats[0][0]=level;
		//XP
		this.stats[1][0]=(int)Math.pow(level-1,3);
		this.stats[1][1]=(int)Math.pow(level,3);
		//PV
		this.stats[2][0]=this.stats[2][1]=(int)((2*base.stats[3][1]+IV)*(double)(level/100.0)+(level+10));
		//autres stats
		for(i=3;i<8;i++){
			IV=(int)rand.nextInt(32);
			this.stats[i][0]=this.stats[i][1]=(int)((2*base.stats[i][1]+IV)*(double)(level/100.0)+5);}
		/*Fast - 0.8(Current Level)^3
		Medium Fast - (Current Level)^3
		Medium Slow - 1.2(Current Level)^3 - 15(Current Level)^2 + 100(Current Level) - 140
		Slow - 1.25(Current Level)^3*/
		//PV= (int)((2xBS+IV)*level/100+S) pour les pv S= level+10 pour le reste 5
	}
	
	//constructeur pour creer les pokemon de base du pokedex
	public Pkm(String nom,int[] stats,Type[] type){//,Hashtable <Integer,Capacite> levelinglist){
		//copie des stats
		this.ID=cpt++;
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
		for(Type t: type){this.type.addElement(t);}
		i=0;
		//copie des capacites
		this.cap=new Stockage<Capacite>(); cap.max=4;
		i=0;
		
		//Copie des evolutions

		
		//copie de la liste de capacités a apprendre
		//this.levelinglist=new Hashtable<Integer,Capacite>();
		
		//for(Integer key:levelinglist.keySet()){
			//this.levelinglist.put(key,levelinglist.get(key));	
		//}
		
	
		//copie des conditions d'evolution
		//this.evolution=new int[2];
		//this.evolution[0]=evolution[0];
		//this.evolution[1]=evolution[1];	
		
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
	
	public Stockage<Capacite> getCap(){
		return cap;
	}
	
	public void add(Capacite Cap){
		cap.add(Cap);
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
				"\n ATT:"+stats[3][1]+" DEF:"+stats[4][1]+
				"\n ASP:"+stats[5][1]+" DSP:"+stats[6][1]+
				"\n VIT:"+stats[7][1]+" "+personnalite);
		
	}
	
	
}
