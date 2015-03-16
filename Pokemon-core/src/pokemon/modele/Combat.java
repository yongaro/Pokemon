package pokemon.modele;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

import pokemon.annotations.Tps;

@Tps(nbhours=12)
public class Combat {
	protected Terrain terrain;
	protected Climat climat;
	protected Vector<Stack<Pkm>> XPStackTeam1;
	protected Vector<Stack<Pkm>> XPStackTeam2;
	protected Scanner sc = new Scanner(System.in); //BERK
	
	//0 niveau 1 XP 2 PV 3 ATT 4 DEF 5 ATTSP 6 DEFSP 7 VIT 8 Precision (100) 9 Esquive (5% de base)
	
	public Combat(){
		terrain=Terrain.Plaine; climat=Climat.Normal;
	}
	
	public int gagnant(Joueur j1,Joueur j2){
		int nbko1=0; int nbko2=0;
		
		for(int i=0;i<j1.teamsize;i++){
			if(j1.team[i].stats[2][0]<=0){ nbko1++; }
		}
		
		for(int i=0;i<j2.teamsize;i++){
			if(j2.team[i].stats[2][0]<=0){ nbko2++; }
		}
		if(nbko2==j2.teamsize){ return 1; }
		if(nbko1==j1.teamsize){ return 2; }
		return 0;
	}
	
	
	public int combatsolo(Joueur j1,Joueur j2){
		PokemonCombat[] pkmListe=new PokemonCombat[2];
		XPStackTeam1 = new Vector<Stack<Pkm>>(j1.teamsize,0);
		XPStackTeam2 = new Vector<Stack<Pkm>>(j2.teamsize,0);
		
		pkmListe[0]=new PokemonCombat(j1.team[0],false);
		pkmListe[1]=new PokemonCombat(j2.team[0],false);
		pkmListe[0].adv[0]=pkmListe[1]; pkmListe[0].prop=j1;
		pkmListe[1].adv[0]=pkmListe[0]; pkmListe[1].prop=j2;
		while(this.gagnant(j1,j2)==0){
			Arrays.sort(pkmListe);
			for(PokemonCombat p: pkmListe){
				p.action(p.adv[0],this);
			}
			//Application des dégats sur la durée
			for(PokemonCombat p:pkmListe){
				if(p.pkm.statut==Statut.Empoisonne || p.pkm.statut==Statut.Brule ){ 
					p.pkm.statut.StatEffect(p.pkm,1);
					p.pkm.supTemp.StatEffect(p.pkm,1);
					if(p.pkm.stats[2][0]<=0){pokeswap(p);}
				}
			}
		}
		
		return this.gagnant(j1,j2);
	}
	
	
	public void action(PokemonCombat user,PokemonCombat cible){
		int isdone=0;  int act=0; int i=0; int ch1=0; int ch2=0;
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
				//Application des statuts pouvant empecher l'action
				ch1=user.pkm.statut.StatEffect(user.pkm,1);
				ch2=user.pkm.supTemp.StatEffect(user.pkm,1);
				if(ch1==1 && ch2==1){
					user.pkm.cap.at(act).script(user.pkm,cible.pkm,this);
				}
				//Conséquences de l'action
				if(user.pkm.stats[2][0]<=0){ pokeswap(user); }
				if(cible.pkm.stats[2][0]<=0){ pokeswap(cible); }
				if(user.pkm.stats[2][0]<=(int)(user.pkm.stats[2][1]/2)){
					if(user.pkm.objTenu instanceof Medicament && cible.pkm.objTenu!=null){
						Medicament m=(Medicament)user.pkm.objTenu;
						System.out.println(user.pkm.nom+" utilise sa baie");
						if(m.baie){ m.script(user.pkm); user.pkm.objTenu=null; }
					}
				}
				if(cible.pkm.stats[2][0]<=(int)(cible.pkm.stats[2][1]/2)){
					if(cible.pkm.objTenu instanceof Medicament && cible.pkm.objTenu!=null){
						Medicament m=(Medicament)cible.pkm.objTenu;
						System.out.println(cible.pkm.nom+" utilise sa baie");
						if(m.baie){ m.script(cible.pkm); cible.pkm.objTenu=null; }
					}
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
		if(!user.isIA){
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
		else{
			for(Pkm p:user.prop.team){
				if(p.statut!=Statut.KO){
					user.pkm=p;
					System.out.println(user.prop+" envoie "+p+" au combat");
				}
			}
		}
		
	}
	
	
}
