package pokemon.modele;

import java.util.Random;

public enum Statut {
	Attraction,Brule,Confus,Empoisonne,Endormi,Gel,KO,Maudit,Normal,Paralyse,Peur;
	
	//useless ?
	public void applique(Pkm cible){
		if(this==Brule || this==Empoisonne || this==Endormi || this==Gel || this==Paralyse){
			if(cible.statut==Normal){
				cible.statut=this;
				if(cible.objTenu instanceof Medicament){
					Medicament m=(Medicament)cible.objTenu;
					if(m.baie && (m.flagSoin==2 || m.flagSoin==4)){
						m.script(cible);
						cible.objTenu=null;
					}
				}
			}
		}
		if(this==KO){cible.statut=this;}
		if(this==Attraction || this==Confus || this==Peur){
			if(cible.supTemp==Normal){
				cible.supTemp=this;
				if(cible.objTenu instanceof Medicament){
					Medicament m=(Medicament)cible.objTenu;
					if(m.baie && (m.flagSoin==2 || m.flagSoin==4)){
						m.script(cible);
						cible.objTenu=null;
					}
				}
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
	    	Random rand=new Random();
	    	if(rand.nextInt()<=25){
	    		System.out.println(cible.nom+" se réveille");
	    		cible.statut=Statut.Normal;
	    		return 1;
	    	}
	    	System.out.println(cible.nom+" dort");
	    	return 0;
	    }
	    if(this==Statut.Gel && flag==0){
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
	    	if(rand.nextInt()<=25){
	    		System.out.println(cible.nom+" sort de sa confusion");
	    		cible.supTemp=Statut.Normal;
	    		return 1;
	    	}
	    	if(rand.nextInt()<=50){
	    	System.out.println(cible.nom+" est Confus");
	    	System.out.println(cible.nom+" se blesse dans sa follie");
	    	}
	    }
	    if(this==Statut.Peur && flag==0){
	    	cible.supTemp=Statut.Normal;
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
