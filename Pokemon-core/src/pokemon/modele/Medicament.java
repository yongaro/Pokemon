package pokemon.modele;

public class Medicament extends Objet {
	protected int effet; // proportion de pv rendus //0 - 0% 1- 15% 2- 25 % 3- 33 % 4- 100 %
	protected int flagSoin; // 1 pv | 2 statut | 3 K.O | 4 pv+statut
	protected boolean baie;
	
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
				return (cible.stats[2][0]<cible.stats[2][1]) && (cible.statut!=Statut.Normal && cible.statut!=Statut.KO);
			default:
				return false;
		}
	}
	
	public int script(Pkm cible){
		if(this.utilisable(cible)){
			int soin=0;
			switch(effet){
				case 1: 
					cible.Heal(soin=(int)(15*cible.stats[2][1]/100)); System.out.println(cible.nom+" regagne "+soin+" PV");
					break;
				case 2:
					cible.Heal(soin=(int)(25*cible.stats[2][1]/100)); System.out.println(cible.nom+" regagne "+soin+" PV");
					break;
				case 3:
					cible.Heal(soin=(int)(33*cible.stats[2][1]/100)); System.out.println(cible.nom+" regagne "+soin+" PV");
					break;
				case 4:
					cible.Heal(soin=cible.stats[2][1]); System.out.println(cible.nom+" regagne "+soin+" PV");
					break;
				default: System.out.println("Medicament utilisé avec effet invalide:"+effet);
			}
			switch(flagSoin){
				case 2:
					cible.statut=Statut.Normal; System.out.println(cible.nom+" est soigné de son affliction");
					break;
				case 3:
					cible.statut=Statut.Normal; System.out.println(cible.nom+" est soigné de son affliction");
					break;
				case 4:
					cible.statut=Statut.Normal; System.out.println(cible.nom+" est soigné de son affliction");
					break;
				default: System.out.println("Medicament utilisé avec flagSoin invalide:"+flagSoin);
			}
			return 0;
		}
		System.out.println("Ca n'aura aucun effet"); 
		return -1;
		
		
	}

	
	
}
