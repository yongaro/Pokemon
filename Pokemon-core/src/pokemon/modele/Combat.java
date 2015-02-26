package pokemon.modele;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;


public class Combat {
	protected Terrain terrain;
	protected Climat climat;
	protected Scanner sc = new Scanner(System.in); //HAAAAAN CA PUE HAAAAAN CAY DEGUEULASS
	
	//0 niveau 1 XP 2 PV 3 ATT 4 DEF 5 ATTSP 6 DEFSP 7 VIT 8 Precision (100) 9 Esquive (5% de base)
	
	public Combat(){
		terrain=Terrain.Plaine; climat=Climat.Normal;
	}
	
	public int gagnant(Joueur j1, Pkm pk){
		int nbko=0;
		
		if(pk.stats[2][0]<=0){ return 1; }
		
		for(int i=0;i<j1.teamsize;i++){
			if(j1.team[i].stats[2][0]<=0){ nbko++; }
		}
		if(nbko==j1.teamsize){
			return 2;
		}
		return 0;
	}
	
	
	public int combatsolo(Joueur j,Pkm pk){
		PokemonCombat[] pkmListe=new PokemonCombat[2];
		
		pkmListe[0]=new PokemonCombat(j.team[0],false);
		pkmListe[1]=new PokemonCombat(pk,false);
		pkmListe[0].adv[0]=pkmListe[1]; pkmListe[0].prop=j;
		pkmListe[1].adv[0]=pkmListe[0]; pkmListe[1].prop=j;
		
		while(this.gagnant(j,pk)==0){
			Arrays.sort(pkmListe);
			for(PokemonCombat p: pkmListe){
				p.action(p.adv[0],this);
			}
			//Application des dégats sur la durée
		}
		
		return this.gagnant(j,pk);
	}
	
	
	public void action(PokemonCombat user,PokemonCombat cible){
		int isdone=0;  int act=0; int i=0;
		while(isdone==0){
			System.out.println("Que doit faire "+user.pkm.nom+" ? "+user.pkm.stats[2][0]+"/"+user.pkm.stats[2][1]+" "+user.pkm.statut);
			System.out.println("1-Attaque   2-Pkm");
			System.out.println("3-Objet     4-Fuite ");
			act=sc.nextInt();
			
			switch(act){
			case 1:
				for(UniteStockage<Capacite> u:user.pkm.cap){
						System.out.println(i+" "+u); i++;				
				}
				//while((act=sc.nextInt())<user.cap.max){System.out.println(act); }
				act=sc.nextInt();
				user.pkm.cap.at(act).script(user.pkm,cible.pkm,this);
				if(user.pkm.stats[2][0]<=0 && !cible.isIA){
					System.out.println(user.pkm.nom+" est K.O");
					user.pkm.stats[2][0]=0; user.pkm.statut=Statut.KO;
					pokeswap(user);
				}
				if(cible.pkm.stats[2][0]<=0 && !cible.isIA){
					System.out.println(cible.pkm.nom+" est K.O");
					cible.pkm.stats[2][0]=0; cible.pkm.statut=Statut.KO;
					pokeswap(cible);
				} 
				isdone=1;
				break;
			case 2:
				pokeswap(user);
				isdone=1;
				//traitement capacité passive ici
				break;
			case 3:
				System.out.println("TODO");
				break;
			case 4:
				System.out.println("TODO");
				break;
			default :
				System.out.println("ENCORE");
				break;
			}
		}
		//sc.close();
	}
	
	public void pokeswap(PokemonCombat user){
		int i=0; int act=0; int done=0;
		
		while(done==0){
			System.out.println("Qui voulez vous envoyer ?");
			for(Pkm p: user.prop.team){
				System.out.println(i+" "+p.nom+" LV."+p.stats[0][1]+" "+p.stats[2][0]+"/"+p.stats[2][1]+" "+p.statut);
				i++;
			}
			act=sc.nextInt();
			if(user.prop.team[act].statut!=Statut.KO){
				System.out.println(user.prop.team[act].nom+" remplace "+user.pkm.nom);
				user.pkm=user.prop.team[act];
				done=1;
			}
			else{
				System.out.println("Vous ne pouvez pas envoyer un Pokemon K.O au combat !");
			}
		}
		
	}
	
	
}
