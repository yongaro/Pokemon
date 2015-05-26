package pokemon.modele;

import pokemon.annotations.*;

@Tps(nbhours=5)
public class Medicament extends Objet {
	protected int effet; // proportion de pv rendus //0 - 0% 1- 15% 2- 25 % 3- 33 % 4- 100 %
	protected int flagSoin; // 1 pv | 2 statut | 3 K.O | 4 pv+statut
	protected boolean baie;
	
	public static Medicament baieTest= new Medicament(1,"BaieTest","Une baie qui soigne 33% des PV et les problemes de Statut",255,3,4,true);
	public static Medicament rappel= new Medicament(2,"Rappel","Un medicament qui reanime un pokemon K.O",3,4,3,false);
	
	public Medicament(){ super(); }

	public Medicament(int id,String nm, String desc, int nbmax,int effet,int flag,boolean baie){
		super(id,nm,desc,nbmax);
		this.effet=effet; flagSoin=flag; this.baie=baie;
	}
	
	public boolean utilisable(Pkm cible){
		switch(flagSoin){
			case 1:
				return cible.stats[2][0]<cible.stats[2][1];
			case 2:
				return cible.statut!=Statut.Normal && cible.statut!=Statut.KO;
			case 3:
				return cible.statut==Statut.KO;
			case 4:
				return (cible.stats[2][0]<cible.stats[2][1]) || (cible.statut!=Statut.Normal && cible.statut!=Statut.KO);
			default:
				return false;
		}
	}
	
	public int script(Pkm cible,Combat context){
		if(this.utilisable(cible)){
			int soin=0;
			switch(effet){
				case 1: 
					cible.heal(soin=(int)(15*cible.stats[2][1]/100)); if(context!=null){context.ajoutBuffer(cible.nom+" regagne "+soin+" PV");}
					break;
				case 2:
					cible.heal(soin=(int)(25*cible.stats[2][1]/100)); if(context!=null){context.ajoutBuffer(cible.nom+" regagne "+soin+" PV");}
					break;
				case 3:
					cible.heal(soin=(int)(33*cible.stats[2][1]/100)); if(context!=null){context.ajoutBuffer(cible.nom+" regagne "+soin+" PV");}
					break;
				case 4:
					cible.heal(soin=cible.stats[2][1]); if(context!=null){context.ajoutBuffer(cible.nom+" regagne "+soin+" PV");}
					break;
				default: System.out.println("Medicament utilise avec effet invalide:"+effet);
			}
			switch(flagSoin){
				case 2:
					cible.statut=Statut.Normal; if(context!=null){context.ajoutBuffer(cible.nom+" est soigne de son affliction");}
					break;
				case 3:
					cible.statut=Statut.Normal; if(context!=null){context.ajoutBuffer(cible.nom+" est soigne de son affliction");}
					break;
				case 4:
					cible.statut=Statut.Normal; if(context!=null){context.ajoutBuffer(cible.nom+" est soigne de son affliction");}
					break;
				default: System.out.println("Medicament utilise avec flagSoin invalide:"+flagSoin);
			}
			return 0;
		}
		System.out.println("Ca n'aura aucun effet"); 
		return -1;
		
		
	}

	
	
}
