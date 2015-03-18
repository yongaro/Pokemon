package pokemon.modele;

import java.util.Random;

public class AtkSoin extends Atk {

	public AtkSoin(){
		super(); this.effet=Statut.Normal;
	}
	public AtkSoin(int pw,int pre,int cc,String nom,String d,Type el,int type,int pp,Statut effet){
		super(pw,pre,cc,nom,d,el,type,pp,effet);
	}
	
	
	public void script(Pkm user,Pkm cible,Combat context){
		System.out.println(user.nom+" utilise "+nom); 
		Random random=new Random();
		//test de precision
		int touche=0;
		if(random.nextInt(100)<=this.pre){touche=1;}
		//test d'esquive de l'adversaire
		int esquive=0;
		if(random.nextInt(100)<=cible.stats[9][0]){esquive=1;}
		if(touche==0 || esquive==1){
			System.out.println(user.nom+" rate son attaque...");
		}
		if(touche==1 && esquive==0 && power>0){
			int soin=0;
			if(power>0){soin=(int)(this.atkdamage(user,cible,context.climat)/2);}
			if(random.nextInt(100)<=10 && this.effet!=Statut.Normal){cible.statut=effet; System.out.println(cible.nom+" est "+effet);}
			
			//Test de capacite Passive Suintement
			if(cible.capP==CapacitePassive.Suintement){
				System.out.println("Suitement de "+cible.nom+" fait perdre des PV a "+user.nom);
				//user.stats[2][0]-=soin;
				user.infliger(soin);
			}
			else{
				System.out.println("L'attaque soigne "+user.nom);
				user.heal(soin);
			}
			
			//Traitement capacite passive
			if(user.capP.flag==3 || (user.capP.flag==1 && type==3) || (user.capP.flag==2 && type==5) ){
				try{
					CapacitePassive.class.getMethod(cible.capP.name(),Pkm.class,Pkm.class).invoke(null,user,cible);
				}
				catch(Exception ex){System.out.println("DAMNIT JAVA\n"+ex);}
			}
		}
	}
	
}
