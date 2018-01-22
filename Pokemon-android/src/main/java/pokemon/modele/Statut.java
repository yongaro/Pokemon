package pokemon.modele;

import java.util.Random;

public enum Statut {
	Attraction,Brule,Confus,Empoisonne,Endormi,Gele,KO,Maudit,Normal,Paralyse,Peur,Picots,Piege,Requiem,Toxic,Vampigraine;
	
	protected int nbtours;
	protected Capacite dummy;
	protected int dmg;
	protected boolean principal;
	protected boolean infligedegats;
	
	private Statut(){
		if(this.name().compareTo("Attraction")==0){
			this.dummy=new Atk(Type.Fee); principal=false; infligedegats=false;
		}
		if(this.name().compareTo("Brule")==0){
			this.dummy=new Atk(Type.Feu); principal=true; infligedegats=true;
		}
		else if(this.name().compareTo("Confus")==0){
			this.dummy=new Atk(Type.Psy); infligedegats=false;
		}
		else if(this.name().compareTo("Empoisonne")==0 || this.name().compareTo("Toxic")==0){
			this.dummy=new Atk(Type.Poison); principal=true; infligedegats=true;
		}
		else if(this.name().compareTo("Endormi")==0){
			this.dummy=new Atk(Type.Vol); principal=true; infligedegats=false;
		}
		else if(this.name().compareTo("Gele")==0){
			this.dummy=new Atk(Type.Glace); principal=true; infligedegats=false;
		}
		else if(this.name().compareTo("Maudit")==0 || this.name().compareTo("Requiem")==0){
			this.dummy=new Atk(Type.Spectre); infligedegats=true;
		}
		else if(this.name().compareTo("Paralyse")==0 || this.name().compareTo("Stuck")==0){
			this.dummy=new Atk(Type.Electrique); principal=true; infligedegats=false;
		}
		else if(this.name().compareTo("Peur")==0){
			this.dummy=new Atk(Type.Tenebre); infligedegats=false;
		}
		else if(this.name().compareTo("Vampigraine")==0){
			this.dummy=new Atk(Type.Plante); this.dummy.type=3; infligedegats=true;
		}
		else if(this.name().compareTo("Piege")==0){
			this.dummy=new Atk(Type.Plante); this.dummy.type=3; infligedegats=true;
		}
		else if(this.name().compareTo("Picots")==0){
			this.dummy=new Atk(Type.Normal); this.dummy.type=3; infligedegats=true;
		}
		else{
			this.dummy=new Atk(Type.Normal); infligedegats=false;
		}
		nbtours=0; dmg=0;
	}
	
	public void applique(Pkm cible,Combat context){
		if(this==Brule || this==Empoisonne || this==Endormi || this==Gele || this==Paralyse || this==Toxic){
			if(cible.statut==Normal){
				if(this!=Toxic){
				context.ajoutBuffer(cible.nom+" est "+this.name());
				}
				else{
					context.ajoutBuffer(cible.nom+" est gravement empoisonne");
				}
				cible.statut=this;

				if(this==Endormi){
					Random rand=new Random();
					nbtours=rand.nextInt(5)+1;
				}
				if(cible.objTenu instanceof Medicament){
					Medicament m=(Medicament)cible.objTenu;
					if(m.baie && (m.flagSoin==2 || m.flagSoin==4)){
						context.ajoutBuffer(cible.nom+" utilise sa baie");
						m.script(cible, context);
						cible.objTenu=null;
					}
				}
			}
		}
		if(this==KO){cible.statut=this; cible.supTemp.clear();}
		if(this==Attraction || this==Confus || this==Peur || this==Piege || this==Vampigraine){
			if(this==Confus || this==Piege){
				Random rand=new Random();
				nbtours=rand.nextInt(5)+1;
				context.ajoutBuffer(cible.nom+" est "+this.name()+" pour "+nbtours+" tours");
				//git problem
			}
			if(this==Attraction){
				context.ajoutBuffer(cible.nom+" est sous le charme");
			}
			if(this==Peur){context.ajoutBuffer(cible.nom+" a peur");}
			if(this==Vampigraine){context.ajoutBuffer(cible.nom+" est infecte");}
			if(this==Requiem){nbtours=3;}
			if(!cible.supTemp.contains(this)){
				if(cible.objTenu instanceof Medicament){
					Medicament m=(Medicament)cible.objTenu;
					if(m.baie && (m.flagSoin==2 || m.flagSoin==4)){
						context.ajoutBuffer(cible.nom+" utilise sa baie");
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
	public int StatEffect(Pkm cible,Pkm user,int flag,Combat context){
		if(this==Statut.Attraction && flag==0){
	    	Random rand=new Random();
	    	if(rand.nextInt(100)<50){
	    		context.ajoutBuffer(cible.nom+" est sous le charme");
	    		return 0;
	    	}
	    	return 1;
	    }
		if(this==Statut.Brule && flag==1){
	    	dmg=(int)cible.stats[2][1]/16;
			cible.infliger(dmg);
			context.ajoutBuffer("La brulure inflige des degats.");
			context.ajoutBuffer(dmg+" pv");
				return 1;
		}
	    if(this==Statut.Endormi && flag==0){
	    	if(nbtours==0){
	    		System.out.println(cible.nom+" se reveille");
	    		context.ajoutBuffer(cible.nom+" se reveille");
	    		cible.statut=Statut.Normal;
	    		return 1;
	    	}
	    	else{
	    		context.ajoutBuffer(cible.nom+" dort profondemment");
	    		nbtours--;
	    		return 0;
	    	}
	    	
	    }
	    if(this==Statut.Gele && flag==0){
	    	Random rand=new Random();
	    	if(rand.nextInt(100)<=10){
	    		context.ajoutBuffer(cible.nom+" n'est plus gele");
	    		cible.statut=Statut.Normal;
	    		return 1;
	    	}
	    	context.ajoutBuffer(cible.nom+" est gele");
	    	return 0;
	    }
	    if(this==Statut.Paralyse && flag==0){
	    	Random rand=new Random();
	    	if(rand.nextInt(100)<33){
	    		context.ajoutBuffer(cible.nom+" est paralyse");
	    		return 0;
	    	}
	    	return 1;
	    }
	    if(this==Statut.Empoisonne && flag==1){
	    	dmg=(int)cible.stats[2][1]/16;
	    	context.ajoutBuffer("Le poison inflige des degats");
	    	context.ajoutBuffer(dmg+" pv");
	    	cible.infliger(dmg);
	    	return 1;
	    }
	    if(this==Statut.Toxic && flag==1){
	    	dmg=(int)(++nbtours*cible.stats[2][1])/16;
	    	context.ajoutBuffer("Le poison inflige des degats");
	    	context.ajoutBuffer(dmg+" pv");
	    	cible.infliger(dmg);
	    	return 1;
	    }
	    if(this==Statut.Confus && flag==0){
	    	if(nbtours==0){
	    		//cible.supTemp.remove(this);
	    		//context.ajoutBuffer(cible.nom+" sort de sa confusion");
	    		return 1;
	    	}
	    	else{
	    		Random rand=new Random();
		    	if(rand.nextInt(100)<=50){
		    		dmg=Atk.confDmg.atkdamage(user, cible, context,true);
		    		//dmg=(int)cible.stats[2][1]/16;
			    	context.ajoutBuffer(cible.nom+" est Confus");
			    	context.ajoutBuffer(cible.nom+" se blesse dans sa follie");
			    	cible.infliger(dmg);
			    	//infliger les degats d'une attaque a 40 de dégats sans type
			    	nbtours--;
			    	return 0;
		    	}
		    	return 1;
	    	}
	    }
	    if(this==Statut.Peur && flag==0){
	    	cible.supTemp.remove(this);
	    	context.ajoutBuffer(cible.nom+" a peur");
	    	return 0;
	    }
	    if(this==Statut.Maudit && flag==1){
	    	dmg=(int)(cible.stats[2][1]/4);
	    	context.ajoutBuffer(cible.nom+" est maudit");
	    	context.ajoutBuffer(dmg+" pv");
	    	cible.infliger(dmg);
	    	return 1;
	    }
	    if(this==Statut.Vampigraine && flag==1){
	    	dmg=(int)cible.stats[2][1]/8;
	    	context.ajoutBuffer("La santee de "+cible.nom+" est drainee");
	    	context.ajoutBuffer(dmg+" pv");
	    	cible.infliger(dmg);
	    	user.heal(dmg);
	    	return 1;
	    }
	    if(this==Statut.Piege && flag==1){
	    	dmg=(int)cible.stats[2][1]/16;
	    	context.ajoutBuffer("Le piege inflige des degats");
	    	context.ajoutBuffer(dmg+" pv");
	    	cible.infliger(dmg);
	    	return 1;
	    }
	    return 1;
	}
}
