package pokemon.modele;

import java.util.Random;


public class AtkRepet extends Atk {
	
	public AtkRepet(){super();}
	public AtkRepet(int pw,int pre,int cc,String nom,String d,Type el,int type,int pp,Statut effet){
		super(pw,pre,cc,nom,d,el,type,pp,effet);
	}
	public void script(Pkm user,Pkm cible,Combat context){
		System.out.println(user.nom+" utilise "+description);
		 Random random=new Random();
		//test de precision
		int touche=0;
		if(random.nextInt(100)<=this.pre){touche=1;}
		//test d'esquive de l'adversaire
		int esquive=0;
		if(random.nextInt(100)<=cible.stats[9][0]){esquive=1;}
		if(touche==1 && esquive==0){
			//Nombre de coups portes par l'attaque
			int  nbcoups=random.nextInt();
			for(int i=0;i<=nbcoups;i++){
				this.atkdamage(user,cible,context.climat);
			}
			System.out.println("Touche "+nbcoups+" fois");
			
			//Traitement capacité passive
			if(user.capP.flag==3 || (user.capP.flag==1 && type==3) || (user.capP.flag==2 && type==5) ){
				try{
					CapacitePassive.class.getMethod(cible.capP.name(),Pkm.class,Pkm.class).invoke(null,user,cible);
					//cible.capP.getClass().getMethod(cible.capP.name(),Pkm.class,Pkm.class).invoke(user,cible);
				}
				//catch(NoSuchMethodException ex){System.out.println(ex);}
				catch(Exception ex){System.out.println("DAMNIT JAVA");}
			}
		}
		else{System.out.println(user.nom+" rate son attaque...");}
	}
}
