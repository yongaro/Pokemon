package pokemon.modele;

import java.util.Random;

public enum Nature {
	Assure,Brave,Calme,Discret,Doux,Foufou,Gentil,Jovial,Lache,Malin,
	Malpoli,Mauvais,Modeste,Naif,Presse,Prudent,Relax,Rigide,Solo,
	Timide;
	
	public static Nature getRandom(Pkm cible){
		int pick=new Random().nextInt(Nature.values().length);
		Nature Res=values()[pick];
		//2 pv 3 att 4 def 5 attsp 6 defsp 7 vit
		
		return Res;
	}
	
	public void Applique(Pkm cible){
		if(this==Nature.Assure){//-10% ATT +10% DEF 
			cible.stats[3][0]-=(int)cible.stats[3][0]*0.1;
			cible.stats[3][1]-=(int)cible.stats[3][1]*0.1;
			cible.stats[4][0]+=(int)cible.stats[4][0]*0.1;
			cible.stats[4][1]+=(int)cible.stats[4][1]*0.1;
		}
		if(this==Nature.Brave){//ATT +10% VIT -10%
			cible.stats[7][0]-=(int)cible.stats[7][0]*0.1;
			cible.stats[7][1]-=(int)cible.stats[7][1]*0.1;
			cible.stats[3][0]+=(int)cible.stats[3][0]*0.1;
			cible.stats[3][1]+=(int)cible.stats[3][1]*0.1;
		}
		if(this==Nature.Calme){//ATT-10% DEFSP+10%
			cible.stats[3][0]-=(int)cible.stats[3][0]*0.1;
			cible.stats[3][1]-=(int)cible.stats[3][1]*0.1;
			cible.stats[6][0]+=(int)cible.stats[6][0]*0.1;
			cible.stats[6][1]+=(int)cible.stats[6][1]*0.1;
		}
		if(this==Nature.Discret){//ASP+10% VIT-10%
			cible.stats[7][0]-=(int)cible.stats[7][0]*0.1;
			cible.stats[7][1]-=(int)cible.stats[7][1]*0.1;
			cible.stats[5][0]+=(int)cible.stats[5][0]*0.1;
			cible.stats[5][1]+=(int)cible.stats[5][1]*0.1;
		}
		if(this==Nature.Doux){//DEF-10% ATTSP +10%
			cible.stats[4][0]-=(int)cible.stats[4][0]*0.1;
			cible.stats[4][1]-=(int)cible.stats[4][1]*0.1;
			cible.stats[5][0]+=(int)cible.stats[5][0]*0.1;
			cible.stats[5][1]+=(int)cible.stats[5][1]*0.1;
		}
		if(this==Nature.Foufou){//ASP+ DSP-
			cible.stats[6][0]-=(int)cible.stats[6][0]*0.1;
			cible.stats[6][1]-=(int)cible.stats[6][1]*0.1;
			cible.stats[5][0]+=(int)cible.stats[5][0]*0.1;
			cible.stats[5][1]+=(int)cible.stats[5][1]*0.1;
		}
		if(this==Nature.Gentil){//def- defsp+
			cible.stats[4][0]-=(int)cible.stats[4][0]*0.1;
			cible.stats[4][1]-=(int)cible.stats[4][1]*0.1;
			cible.stats[6][0]+=(int)cible.stats[6][0]*0.1;
			cible.stats[6][1]+=(int)cible.stats[6][1]*0.1;
		}
		if(this==Nature.Jovial){//asp- vit+
			cible.stats[5][0]-=(int)cible.stats[5][0]*0.1;
			cible.stats[5][1]-=(int)cible.stats[5][1]*0.1;
			cible.stats[7][0]+=(int)cible.stats[7][0]*0.1;
			cible.stats[7][1]+=(int)cible.stats[7][1]*0.1;
		}
		if(this==Nature.Lache){//def+ defsp-
			cible.stats[6][0]-=(int)cible.stats[6][0]*0.1;
			cible.stats[6][1]-=(int)cible.stats[6][1]*0.1;
			cible.stats[4][0]+=(int)cible.stats[4][0]*0.1;
			cible.stats[4][1]+=(int)cible.stats[4][1]*0.1;
		}
		if(this==Nature.Malin){//def+ attsp-
			cible.stats[5][0]-=(int)cible.stats[5][0]*0.1;
			cible.stats[5][1]-=(int)cible.stats[5][1]*0.1;
			cible.stats[4][0]+=(int)cible.stats[4][0]*0.1;
			cible.stats[4][1]+=(int)cible.stats[4][1]*0.1;
		}
		if(this==Nature.Malpoli){//defsp+ vit-
			cible.stats[7][0]-=(int)cible.stats[7][0]*0.1;
			cible.stats[7][1]-=(int)cible.stats[7][1]*0.1;
			cible.stats[6][0]+=(int)cible.stats[6][0]*0.1;
			cible.stats[6][1]+=(int)cible.stats[6][1]*0.1;
		}
		if(this==Nature.Mauvais){//att+ defsp-
			cible.stats[6][0]-=(int)cible.stats[6][0]*0.1;
			cible.stats[6][1]-=(int)cible.stats[6][1]*0.1;
			cible.stats[3][0]+=(int)cible.stats[3][0]*0.1;
			cible.stats[3][1]+=(int)cible.stats[3][1]*0.1;
		}
		if(this==Nature.Modeste){//att- attsp+
			cible.stats[3][0]-=(int)cible.stats[3][0]*0.1;
			cible.stats[3][1]-=(int)cible.stats[3][1]*0.1;
			cible.stats[5][0]+=(int)cible.stats[5][0]*0.1;
			cible.stats[5][1]+=(int)cible.stats[5][1]*0.1;
		}
		if(this==Nature.Naif){//defsp- vit+
			cible.stats[6][0]-=(int)cible.stats[6][0]*0.1;
			cible.stats[6][1]-=(int)cible.stats[6][1]*0.1;
			cible.stats[7][0]+=(int)cible.stats[7][0]*0.1;
			cible.stats[7][1]+=(int)cible.stats[7][1]*0.1;
		}
		if(this==Nature.Presse){//def- vit+
			cible.stats[4][0]-=(int)cible.stats[4][0]*0.1;
			cible.stats[4][1]-=(int)cible.stats[4][1]*0.1;
			cible.stats[7][0]+=(int)cible.stats[7][0]*0.1;
			cible.stats[7][1]+=(int)cible.stats[7][1]*0.1;
		}
		if(this==Nature.Prudent){//attsp- defsp+
			cible.stats[5][0]-=(int)cible.stats[5][0]*0.1;
			cible.stats[5][1]-=(int)cible.stats[5][1]*0.1;
			cible.stats[6][0]+=(int)cible.stats[6][0]*0.1;
			cible.stats[6][1]+=(int)cible.stats[6][1]*0.1;
		}
		if(this==Nature.Relax){//def+ vit-
			cible.stats[7][0]-=(int)cible.stats[7][0]*0.1;
			cible.stats[7][1]-=(int)cible.stats[7][1]*0.1;
			cible.stats[4][0]+=(int)cible.stats[4][0]*0.1;
			cible.stats[4][1]+=(int)cible.stats[4][1]*0.1;
		}
		if(this==Nature.Rigide){//att+ attsp-
			cible.stats[5][0]-=(int)cible.stats[5][0]*0.1;
			cible.stats[5][1]-=(int)cible.stats[5][1]*0.1;
			cible.stats[3][0]+=(int)cible.stats[3][0]*0.1;
			cible.stats[3][1]+=(int)cible.stats[3][1]*0.1;
		}
		if(this==Nature.Solo){//att+ -def
			cible.stats[4][0]-=(int)cible.stats[4][0]*0.1;
			cible.stats[4][1]-=(int)cible.stats[4][1]*0.1;
			cible.stats[3][0]+=(int)cible.stats[3][0]*0.1;
			cible.stats[3][1]+=(int)cible.stats[3][1]*0.1;
		}
		if(this==Nature.Timide){//att- vit+
			cible.stats[3][0]-=(int)cible.stats[3][0]*0.1;
			cible.stats[3][1]-=(int)cible.stats[3][1]*0.1;
			cible.stats[7][0]+=(int)cible.stats[7][0]*0.1;
			cible.stats[7][1]+=(int)cible.stats[7][1]*0.1;
		}
	}
}
