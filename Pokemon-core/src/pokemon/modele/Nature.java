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
		if(Res==Nature.Assure){//-10% ATT +10% DEF 
			cible.stats[3][0]-=(int)cible.stats[3][0]*0.1;
			cible.stats[3][1]-=(int)cible.stats[3][1]*0.1;
			cible.stats[4][0]+=(int)cible.stats[4][0]*0.1;
			cible.stats[4][1]+=(int)cible.stats[4][1]*0.1;
		}
		if(Res==Nature.Brave){//ATT +10% VIT -10%
			cible.stats[7][0]-=(int)cible.stats[7][0]*0.1;
			cible.stats[7][1]-=(int)cible.stats[7][1]*0.1;
			cible.stats[3][0]+=(int)cible.stats[3][0]*0.1;
			cible.stats[3][1]+=(int)cible.stats[3][1]*0.1;
		}
		if(Res==Nature.Calme){//ATT-10% DEFSP+10%
			cible.stats[3][0]-=(int)cible.stats[3][0]*0.1;
			cible.stats[3][1]-=(int)cible.stats[3][1]*0.1;
			cible.stats[6][0]+=(int)cible.stats[6][0]*0.1;
			cible.stats[6][1]+=(int)cible.stats[6][1]*0.1;
		}
		if(Res==Nature.Discret){//ASP+10% VIT-10%
			cible.stats[7][0]-=(int)cible.stats[7][0]*0.1;
			cible.stats[7][1]-=(int)cible.stats[7][1]*0.1;
			cible.stats[5][0]+=(int)cible.stats[5][0]*0.1;
			cible.stats[5][1]+=(int)cible.stats[5][1]*0.1;
		}
		if(Res==Nature.Doux){//DEF-10% ATTSP +10%
			cible.stats[4][0]-=(int)cible.stats[4][0]*0.1;
			cible.stats[4][1]-=(int)cible.stats[4][1]*0.1;
			cible.stats[5][0]+=(int)cible.stats[5][0]*0.1;
			cible.stats[5][1]+=(int)cible.stats[5][1]*0.1;
		}
		if(Res==Nature.Foufou){//ASP+ DSP-
			cible.stats[6][0]-=(int)cible.stats[6][0]*0.1;
			cible.stats[6][1]-=(int)cible.stats[6][1]*0.1;
			cible.stats[5][0]+=(int)cible.stats[5][0]*0.1;
			cible.stats[5][1]+=(int)cible.stats[5][1]*0.1;
		}
		if(Res==Nature.Gentil){//def- defsp+
			cible.stats[4][0]-=(int)cible.stats[4][0]*0.1;
			cible.stats[4][1]-=(int)cible.stats[4][1]*0.1;
			cible.stats[6][0]+=(int)cible.stats[6][0]*0.1;
			cible.stats[6][1]+=(int)cible.stats[6][1]*0.1;
		}
		if(Res==Nature.Jovial){//asp- vit+
			cible.stats[5][0]-=(int)cible.stats[5][0]*0.1;
			cible.stats[5][1]-=(int)cible.stats[5][1]*0.1;
			cible.stats[7][0]+=(int)cible.stats[7][0]*0.1;
			cible.stats[7][1]+=(int)cible.stats[7][1]*0.1;
		}
		if(Res==Nature.Lache){//def+ defsp-
			cible.stats[6][0]-=(int)cible.stats[6][0]*0.1;
			cible.stats[6][1]-=(int)cible.stats[6][1]*0.1;
			cible.stats[4][0]+=(int)cible.stats[4][0]*0.1;
			cible.stats[4][1]+=(int)cible.stats[4][1]*0.1;
		}
		if(Res==Nature.Malin){//def+ attsp-
			cible.stats[5][0]-=(int)cible.stats[5][0]*0.1;
			cible.stats[5][1]-=(int)cible.stats[5][1]*0.1;
			cible.stats[4][0]+=(int)cible.stats[4][0]*0.1;
			cible.stats[4][1]+=(int)cible.stats[4][1]*0.1;
		}
		if(Res==Nature.Malpoli){//defsp+ vit-
			cible.stats[7][0]-=(int)cible.stats[7][0]*0.1;
			cible.stats[7][1]-=(int)cible.stats[7][1]*0.1;
			cible.stats[6][0]+=(int)cible.stats[6][0]*0.1;
			cible.stats[6][1]+=(int)cible.stats[6][1]*0.1;
		}
		if(Res==Nature.Mauvais){//att+ defsp-
			cible.stats[6][0]-=(int)cible.stats[6][0]*0.1;
			cible.stats[6][1]-=(int)cible.stats[6][1]*0.1;
			cible.stats[3][0]+=(int)cible.stats[3][0]*0.1;
			cible.stats[3][1]+=(int)cible.stats[3][1]*0.1;
		}
		if(Res==Nature.Modeste){//att- attsp+
			cible.stats[3][0]-=(int)cible.stats[3][0]*0.1;
			cible.stats[3][1]-=(int)cible.stats[3][1]*0.1;
			cible.stats[5][0]+=(int)cible.stats[5][0]*0.1;
			cible.stats[5][1]+=(int)cible.stats[5][1]*0.1;
		}
		if(Res==Nature.Naif){//defsp- vit+
			cible.stats[6][0]-=(int)cible.stats[6][0]*0.1;
			cible.stats[6][1]-=(int)cible.stats[6][1]*0.1;
			cible.stats[7][0]+=(int)cible.stats[7][0]*0.1;
			cible.stats[7][1]+=(int)cible.stats[7][1]*0.1;
		}
		if(Res==Nature.Presse){//def- vit+
			cible.stats[4][0]-=(int)cible.stats[4][0]*0.1;
			cible.stats[4][1]-=(int)cible.stats[4][1]*0.1;
			cible.stats[7][0]+=(int)cible.stats[7][0]*0.1;
			cible.stats[7][1]+=(int)cible.stats[7][1]*0.1;
		}
		if(Res==Nature.Prudent){//attsp- defsp+
			cible.stats[5][0]-=(int)cible.stats[5][0]*0.1;
			cible.stats[5][1]-=(int)cible.stats[5][1]*0.1;
			cible.stats[6][0]+=(int)cible.stats[6][0]*0.1;
			cible.stats[6][1]+=(int)cible.stats[6][1]*0.1;
		}
		if(Res==Nature.Relax){//def+ vit-
			cible.stats[7][0]-=(int)cible.stats[7][0]*0.1;
			cible.stats[7][1]-=(int)cible.stats[7][1]*0.1;
			cible.stats[4][0]+=(int)cible.stats[4][0]*0.1;
			cible.stats[4][1]+=(int)cible.stats[4][1]*0.1;
		}
		if(Res==Nature.Rigide){//att+ attsp-
			cible.stats[5][0]-=(int)cible.stats[5][0]*0.1;
			cible.stats[5][1]-=(int)cible.stats[5][1]*0.1;
			cible.stats[3][0]+=(int)cible.stats[3][0]*0.1;
			cible.stats[3][1]+=(int)cible.stats[3][1]*0.1;
		}
		if(Res==Nature.Solo){//att+ -def
			cible.stats[4][0]-=(int)cible.stats[4][0]*0.1;
			cible.stats[4][1]-=(int)cible.stats[4][1]*0.1;
			cible.stats[3][0]+=(int)cible.stats[3][0]*0.1;
			cible.stats[3][1]+=(int)cible.stats[3][1]*0.1;
		}
		if(Res==Nature.Timide){//att- vit+
			cible.stats[3][0]-=(int)cible.stats[3][0]*0.1;
			cible.stats[3][1]-=(int)cible.stats[3][1]*0.1;
			cible.stats[7][0]+=(int)cible.stats[7][0]*0.1;
			cible.stats[7][1]+=(int)cible.stats[7][1]*0.1;
		}
		return Res;
	}
}
