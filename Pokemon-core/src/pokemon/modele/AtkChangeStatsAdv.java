package pokemon.modele;
import java.util.Random;


public class AtkChangeStatsAdv extends Atk {
	
	protected String Tstats;
	protected int ChangeProc;
	
	public AtkChangeStatsAdv(){
		super();
	}
	public AtkChangeStatsAdv(int pw,int pre,int cc,String nom,String d,Type el,int type,int pp,Statut effet,String code,int proc){
		super(pw,pre,cc,nom,d,el,type,pp,effet); 
		Tstats=code; ChangeProc=proc;
	}
	public void script(Pkm user,Pkm cible,Combat context){
		System.out.println(user.nom+" utilise "+description);
		 Random random=new Random();
		//test de prcision
		int touche=0;
		if(random.nextInt(100)<=this.pre){touche=1;}
		//test d'esquive de l'adversaire
		int esquive=0;
		if(random.nextInt(100)<=cible.stats[9][0]){esquive=1;}
		if(touche==0 || esquive==1){
			System.out.println(user.nom+" rate son attaque...");
		}
		if(touche==1 && esquive==0){
			if(power>0){this.atkdamage(user,cible,context.climat);}
			if(random.nextInt(100)<=ChangeProc){cible.debuff(Tstats);}
			if(random.nextInt(100)<=10 && this.effet!=Statut.Normal){cible.statut=effet; System.out.println(cible.nom+" est "+effet);}
		
			//Traitement capacité passive flags 1-2-3
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
