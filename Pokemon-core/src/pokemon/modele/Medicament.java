package pokemon.modele;

public class Medicament extends Objet {
	protected int effet; // proportion de pv rendus // 1- 10% 2- 20 % 3- 33 % 4- 100 %
	protected int flagSoin; // 1 pv | 2 statut | 3 K.O | 4 pv+statut | 5 pv+statut+K.0  
	protected boolean baie;
	
	public Medicament(){ super(); }

	public Medicament(int id,String nm, String desc, int nbmax,int effet,int flag,boolean baie){
		super(id,nm,desc,nbmax);
		this.effet=effet; flagSoin=flag; this.baie=baie;
	}
	
	public int script(Pkm cible){
		if((flagSoin==1 || flagSoin==4 || flagSoin==5) && (cible.stats[2][0]<cible.stats[2][1]) ){
			switch(effet){
				case 1: 
					cible.Heal((int)(10*cible.stats[2][1]/100));
					break;
				case 2:
					cible.Heal((int)(20*cible.stats[2][1]/100));
					break;
				case 3:
					cible.Heal((int)(33*cible.stats[2][1]/100));
					break;
				case 4:
					cible.Heal((int)(10*cible.stats[2][1]/100));
					break;
				default: System.out.println("Medicament utilisé avec effet invalide:"+effet);
			}
		}
		switch(flagSoin){
			case 2:
				if(cible.statut!=Statut.Normal && cible.statut!=Statut.KO){ 
					cible.statut=Statut.Normal; System.out.println(cible.nom+" est soigné de son affliction");
				}
				else{ System.out.println("Ca n'aura aucun effet"); return -1;}
				break;
			case 3:
				cible.stats[2][0]+=(int)(33*cible.stats[2][1]/100);
				break;
			case 4:
				cible.stats[2][0]+=(int)(10*cible.stats[2][1]/100);
				break;
			default: System.out.println("Medicament utilisé avec flagSoin invalide:"+flagSoin);
		}
		return 0;
	}

}
