package pokemon.modele;

import java.util.*;

import pokemon.annotations.Tps;


@Tps(nbhours=4)
public abstract class Capacite implements Qmax,Infos {
	protected int ID;
	protected String nom;
	protected String description;
	//puissance de la capacite marche aussi bien dans l'offensif que dans la restauration des pv
	protected int power;
	//designe l'attribut utilise par la capacite ATT pour cap physique ATTSP pour le reste
	protected int type;
	//conditions requises pour apprendre l'attaque
	//protected int[] auth; //tableaux de 400 int en moyenne pour chaque attaque ... MEME PAS EN REVE
	//precision
	protected int pre;
	//Chances de Coups critiques
	protected int CC;
	//feu eau etc ...
	protected Type element;
	//nombre d'utilisations de la capacite directement apres apprentissage
	protected int maxPP;
	//blblbl
	public  Capacite(){}
	public Capacite(Type elt){element=elt;}
	public Capacite(int pw,int pre,int cc,String nom,String d,Type el,int type,int pp){
		power=pw; this.type=type; this.pre=pre; this.CC=cc;this.nom=nom; description=d; element=el; maxPP=pp;
	}
	public boolean  peutApprendre(Pkm user){
		boolean ok=false;
		boolean fini = false;
		long atks = user.attacks;
		int i=2;
		
		while (!fini){
			if (atks % i ==0){
				if(this.ID==i){ return true; }
				atks=atks/i;}
			if (atks==1){
				fini=true;}
			i++;
			while(!isPremier(i)){
				i++;}
			//System.out.println(i);
			if(i==1223){
				fini=true;
			}
		}
		return ok;
	}
	public int getId(){return ID;}
	public boolean isPremier(int n) {
		boolean isPremier = true;
		if (n < 0) {
			isPremier = false;
		} else if (n != 0 && n != 1) {
			for (int i = 2; i <= n/2; i++) {
				if (n != i && n % i == 0) {
					isPremier = false;
					break;
				}
			}
		}
		return isPremier;
	}
	
	protected int atkdamage(Pkm user,Pkm cible,Combat context,boolean quiet){
		double STAB=1;double weakness=1.0; Random random=new Random(); double climatmod;
		//recherche de l'affinite de l'utilisateur avec l'element de l'attaque
		if(user.type.contains(this.element)){STAB=1.5;}
		//Calcul des degats
		double damage;
		//recherche de la resistance de l'adversaire a�l'attaque
		for(Type t:cible.type){weakness*=t.isweak(element);} 
		//test de chance pour le critique
		double ccmult=1.0;
		if(random.nextInt(100)<=this.CC){
			ccmult=1.5;
			if(!quiet){context.ajoutBuffer("Coup critique");}
		}
		//formule de calcul des degats
		double mod=(double)STAB*(double)weakness*(double)ccmult;
		double A=(2*user.stats[0][0]+10)/250.0;
		double B=(user.stats[type][0]/(double)cible.stats[type+1][0]);
		damage=A*B*((double)power+2.0)*mod;
		climatmod=context.climat.mod(this.element);
		damage=(int)((double)damage*climatmod);
		//Traitement de l'objet du pokemon (buff passifs des objets)
		if(user.objTenu!=null){
			if(user.objTenu.buffedType!=null){
				if(user.objTenu.buffedType==this.element){damage+=(int)(damage/2);}
			}
		}
		if(weakness==0){ if(!quiet){context.ajoutBuffer("Aucun effet");} return 0;}
		else{
			if(!quiet){
				if(weakness>=2){context.ajoutBuffer("C'est super efficace");}
				if(weakness<1 && weakness>0){context.ajoutBuffer("Ce n'est pas tres efficace");}
				context.ajoutBuffer("-"+(int)damage+" PV");
			}
		//cible.infliger((int)damage);
		//cible.stats[2][0]-=(int)damage;
		return (int)damage;
		}
	}
	public abstract void script(Pkm user,Pkm cible,Combat context);
	public void teach(Pkm cible){
		//Verification des conditions requises
		//Verification de la liste des capacites
		//Si 4 capacites connues demande du numero de la technique a effacer ou annulation
	}
	public boolean equals(Capacite c){return this.description==c.description;}
	
	//Implementation des interfaces
	public int qmax(){return maxPP;}
	public int getID(){return ID;}
	public String getNom(){return nom;}
	public String getDesc(){return description;}
	public String getInfos(){ return power+" "+pre+" "+type+" "+element;}
	
	public int getPower() { return power; }
	public int getPre(){ return pre; }
	public int getType(){ return type; }
	public Type getElement(){ return element; }
	public String toString(){
		if(type==3){
		return (nom+": "+"pw:"+power+" pre:"+pre+" "+element+" ATT");
		}
		else{
			return (nom+": "+"pw:"+power+" pre:"+pre+" "+element+" ATTSP");
		}
	}
}
