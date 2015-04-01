package pokemon.modele;

import java.util.Random;

public enum Statut {
	Attraction,Brule,Confus,Empoisonne,Endormi,Gele,KO,Maudit,Normal,Paralyse,Peur,Picots,Requiem,Stuck;
	
	protected int nbtours;
	
	
	
	public void applique(Pkm cible){
		if(this==Brule || this==Empoisonne || this==Endormi || this==Gele || this==Paralyse){
			if(cible.statut==Normal){
				cible.statut=this;
				if(this==Endormi){
					Random rand=new Random();
					nbtours=rand.nextInt(5)+1;
				}
				System.out.println(cible.nom+" ");
				if(cible.objTenu instanceof Medicament){
					Medicament m=(Medicament)cible.objTenu;
					if(m.baie && (m.flagSoin==2 || m.flagSoin==4)){
						System.out.println(cible.nom+" utilise sa baie");
						m.script(cible);
						cible.objTenu=null;
					}
				}
			}
		}
		if(this==KO){cible.statut=this; cible.supTemp.clear();}
		if(this==Attraction || this==Confus || this==Peur){
			if(this==Confus){
				Random rand=new Random();
				nbtours=rand.nextInt(4)+1;
			}
			if(this==Requiem){nbtours=3;}
			if(!cible.supTemp.contains(this)){
				if(cible.objTenu instanceof Medicament){
					Medicament m=(Medicament)cible.objTenu;
					if(m.baie && (m.flagSoin==2 || m.flagSoin==4)){
						System.out.println(cible.nom+" utilise sa baie");
						m.script(cible);
						cible.objTenu=null;
					}
					else{ cible.supTemp.add(this);}
				}
				else{ cible.supTemp.add(this); }
			}
		}
	}
	
	//renvoie 1 si le pokemon peut attaquer 0 sinon | flag 0 -> avant action 1 -> apres action
	public int StatEffect(Pkm cible,int flag){
	    if(this==Statut.Brule && flag==1){
			System.out.println("La brulure inflige des degats");
			cible.infliger((int)cible.stats[2][1]/16);
				return 1;
		}
	    if(this==Statut.Endormi && flag==0){
	    	if(nbtours==0){
	    		System.out.println(cible.nom+" se reveille");
	    		return 1;
	    	}
	    	else{
	    		System.out.println(cible.nom+" dort profondement");
	    		nbtours--;
	    		return 0;
	    	}
	    	
	    }
	    if(this==Statut.Gele && flag==0){
	    	Random rand=new Random();
	    	if(rand.nextInt()<=10){
	    		System.out.println(cible.nom+" n'est plus gele");
	    		cible.statut=Statut.Normal;
	    		return 1;
	    	}
	    	System.out.println(cible.nom+" est gele");
	    	return 0;
	    }
	    if(this==Statut.Paralyse && flag==0){
	    	Random rand=new Random();
	    	if(rand.nextInt()<33){
	    		System.out.println(cible.nom+" est paralyse");
	    		return 0;
	    	}
	    	return 1;
	    }
	    if(this==Statut.Empoisonne && flag==1){
	    	System.out.println("Le poison inflige des degats");
	    	cible.infliger((int)cible.stats[2][1]/16);
	    	return 1;
	    }
	    if(this==Statut.Confus && flag==0){
	    	Random rand=new Random();
	    	if(nbtours==0){
	    		cible.supTemp.remove(this);
	    		System.out.println(cible.nom+" sort de sa confusion");
	    		return 1;
	    	}
	    	else{
		    	if(rand.nextInt()<=50){
			    	System.out.println(cible.nom+" est Confus");
			    	System.out.println(cible.nom+" se blesse dans sa follie");
			    	//infliger les degats d'une attaque a 40 de dégats sans type
		    	}
		    	nbtours--;
	    	}
	    }
	    if(this==Statut.Peur && flag==0){
	    	cible.supTemp.remove(this);
	    	System.out.println(cible.nom+" a peur");
	    	return 0;
	    }
	    if(this==Statut.Maudit && flag==1){
	    	cible.infliger((int)(cible.stats[2][1]/4));
	    	return 1;
	    }
	    return 1;
	}
}