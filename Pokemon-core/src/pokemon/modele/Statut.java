package pokemon.modele;

import java.util.Random;

public enum Statut {
	Attraction,Brule,Confus,Empoisonne,Endormi,Gele,KO,Maudit,Normal,Paralyse,Peur,Picots,Requiem,Stuck;
	
	protected int nbtours;
	protected Capacite dummy;
	protected int dmg;
	
	private Statut(){
		if(this.name().compareTo("Brule")==0){
			this.dummy=new Atk(Type.Feu);
		}
		else if(this.name().compareTo("Confus")==0){
			this.dummy=new Atk(Type.Psy);
		}
		else if(this.name().compareTo("Empoisonne")==0){
			this.dummy=new Atk(Type.Poison);
		}
		else if(this.name().compareTo("Endormi")==0){
			this.dummy=new Atk(Type.Vol);
		}
		else if(this.name().compareTo("Gele")==0){
			this.dummy=new Atk(Type.Glace);
		}
		else if(this.name().compareTo("Maudit")==0 || this.name().compareTo("Requiem")==0){
			this.dummy=new Atk(Type.Spectre);
		}
		else if(this.name().compareTo("Paralyse")==0 || this.name().compareTo("Stuck")==0){
			this.dummy=new Atk(Type.Electrique);
		}
		else if(this.name().compareTo("Peur")==0){
			this.dummy=new Atk(Type.Tenebre);
		}
		else{
			this.dummy=new Atk(Type.Normal);
		}
		nbtours=0; dmg=0;
	}
	
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
	public int StatEffect(Pkm cible,int flag,Combat context){
	    if(this==Statut.Brule && flag==1){
	    	dmg=(int)cible.stats[2][1]/16;
			System.out.println("La brulure inflige des degats: "+dmg);
			cible.infliger(dmg);
			context.ajoutBuffer("La brulure inflige des degats.");
			context.ajoutBuffer(dmg+" pv");
				return 1;
		}
	    if(this==Statut.Endormi && flag==0){
	    	if(nbtours==0){
	    		System.out.println(cible.nom+" se reveille");
	    		context.ajoutBuffer(cible.nom+" se reveille");
	    		return 1;
	    	}
	    	else{
	    		System.out.println(cible.nom+" dort profondement");
	    		context.ajoutBuffer(cible.nom+" dort profondemment");
	    		nbtours--;
	    		return 0;
	    	}
	    	
	    }
	    if(this==Statut.Gele && flag==0){
	    	Random rand=new Random();
	    	if(rand.nextInt()<=10){
	    		System.out.println(cible.nom+" n'est plus gele");
	    		context.ajoutBuffer(cible.nom+" n'est plus gele");
	    		cible.statut=Statut.Normal;
	    		return 1;
	    	}
	    	System.out.println(cible.nom+" est gele");
	    	context.ajoutBuffer(cible.nom+" est gele");
	    	return 0;
	    }
	    if(this==Statut.Paralyse && flag==0){
	    	Random rand=new Random();
	    	if(rand.nextInt()<33){
	    		System.out.println(cible.nom+" est paralyse");
	    		context.ajoutBuffer(cible.nom+" est paralyse");
	    		return 0;
	    	}
	    	return 1;
	    }
	    if(this==Statut.Empoisonne && flag==1){
	    	dmg=(int)cible.stats[2][1]/16;
	    	System.out.println("Le poison inflige des degats: "+dmg);
	    	context.ajoutBuffer("Le poison inflige des degats.");
	    	context.ajoutBuffer(dmg+" pv");
	    	cible.infliger((int)cible.stats[2][1]/16);
	    	return 1;
	    }
	    if(this==Statut.Confus && flag==0){
	    	Random rand=new Random();
	    	if(nbtours==0){
	    		cible.supTemp.remove(this);
	    		System.out.println(cible.nom+" sort de sa confusion");
	    		context.ajoutBuffer(cible.nom+" sort de sa confusion");
	    		return 1;
	    	}
	    	else{
		    	if(rand.nextInt()<=50){
			    	System.out.println(cible.nom+" est Confus");
			    	context.ajoutBuffer(cible.nom+" est Confus");
			    	System.out.println(cible.nom+" se blesse dans sa follie");
			    	context.ajoutBuffer(cible.nom+" se blesse dans sa follie");
			    	//infliger les degats d'une attaque a 40 de dégats sans type
		    	}
		    	nbtours--;
	    	}
	    }
	    if(this==Statut.Peur && flag==0){
	    	cible.supTemp.remove(this);
	    	System.out.println(cible.nom+" a peur");
	    	context.ajoutBuffer(cible.nom+" a peur");
	    	return 0;
	    }
	    if(this==Statut.Maudit && flag==1){
	    	context.ajoutBuffer(cible.nom+" est maudit");
	    	context.ajoutBuffer(dmg+" pv");
	    	cible.infliger((int)(cible.stats[2][1]/4));
	    	return 1;
	    }
	    return 1;
	}
}