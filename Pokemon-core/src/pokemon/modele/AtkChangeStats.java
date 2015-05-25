package pokemon.modele;

import java.io.Serializable;
import java.util.Random;


public class AtkChangeStats extends Atk implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2836585856246905964L;
	protected String Tstats;
	protected int ChangeProc;
	protected int fof;
	
	public AtkChangeStats(){
		super();
	}
	public AtkChangeStats(int pw,int pre,int cc,String nom,String d,Type el,int type,int pp,Statut effet,int efprc,String code,int proc,int fof){
		super(pw,pre,cc,nom,d,el,type,pp,effet,efprc); 
		Tstats=code; ChangeProc=proc; this.fof=fof;
	}
	public void script(Pkm user,Pkm cible,Combat context){
		context.ajoutBuffer(user.nom+" utilise "+nom);
		 Random random=new Random();
		//test de precision
		int touche=0;
		if(random.nextInt(100)<=this.pre){touche=1;}
		//test d'esquive de l'adversaire
		int esquive=0;
		if(random.nextInt(100)<=cible.stats[9][0]){esquive=1;}
		if(touche==0 || esquive==1){
			context.ajoutBuffer(user.nom+" rate son attaque...");
		}
		if(touche==1 && esquive==0){
			if(power>0){cible.infliger(this.atkdamage(user,cible,context,false));}
			if(random.nextInt(100)<=ChangeProc){
				if(fof==1){user.buff(Tstats,context);}
				if(fof==0){cible.debuff(Tstats,context);}
			}
			if(random.nextInt(100)<=effetProc && this.effet!=Statut.Normal){effet.applique(cible,context);}
		
			//Traitement capacit� passive
			if(user.capP.flag==3 || (user.capP.flag==1 && type==3) || (user.capP.flag==2 && type==5) ){
				try{
					CapacitePassive.class.getMethod(cible.capP.name(),Pkm.class,Pkm.class).invoke(null,user,cible);
					//cible.capP.getClass().getMethod(cible.capP.name(),Pkm.class,Pkm.class).invoke(user,cible);
				}
				catch(Exception ex){System.out.println("DAMNIT JAVA");}
			}
		}
	}

	
}