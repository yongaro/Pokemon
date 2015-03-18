package pokemon.modele;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

import pokemon.annotations.Tps;
import pokemon.launcher.MyGdxGame;

@Tps(nbhours=14)
public class Combat {
	protected Terrain terrain;
	protected Climat climat;
	protected PokemonCombat[] equipe1;
	protected PokemonCombat[] equipe2;
	protected PokemonCombat[] pkmListe;
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
		System.out.println(j1.teamsize+" "+j2.teamsize);
		equipe1=new PokemonCombat[j1.teamsize]; System.out.println(j1.team[0]);
		equipe2=new PokemonCombat[j2.teamsize];
		pkmListe=new PokemonCombat[2];
		
		for(int i=0;i<j1.teamsize;i++){
			equipe1[i]=new PokemonCombat(j1.team[i],false,j1); equipe1[i].equipe=equipe1;
		}
		for(int i=0;i<j2.teamsize;i++){
			equipe2[i]=new PokemonCombat(j2.team[i],false,j2); equipe2[i].equipe=equipe2;
		}
		pkmListe[0]=equipe1[0];
		pkmListe[1]=equipe2[0];
		pkmListe[0].adv[0]=pkmListe[1]; pkmListe[0].XpStack.add(pkmListe[0].adv[0].pkm);
		pkmListe[1].adv[0]=pkmListe[0]; pkmListe[1].XpStack.add(pkmListe[1].adv[0].pkm);
		pkmListe[0].listeIndice=0; pkmListe[1].listeIndice=1;
		
		while(this.gagnant(j1,j2)==0){
			Arrays.sort(pkmListe);
			for(int i=0;i<pkmListe.length;i++){
				pkmListe[i].action(pkmListe[i].adv[0],this);
			}
			//Application des dégats sur la durée
			for(PokemonCombat p:pkmListe){
				if(p.pkm.statut==Statut.Empoisonne || p.pkm.statut==Statut.Brule ){ 
					p.pkm.statut.StatEffect(p.pkm,1);
					p.pkm.supTemp.StatEffect(p.pkm,1);
					if(p.pkm.stats[2][0]<=0){ p.XPreward(); pokeswap(p); }
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
				if(user.pkm.stats[2][0]<=0){ user.XPreward(); pokeswap(user); }
				if(cible.pkm.stats[2][0]<=0){ cible.XPreward(); pokeswap(cible); }
				if(user.pkm.stats[2][0]<=(int)(user.pkm.stats[2][1]/2) && cible.pkm.statut!=Statut.KO){
					if(user.pkm.objTenu instanceof Medicament && cible.pkm.objTenu!=null){
						Medicament m=(Medicament)user.pkm.objTenu;
						System.out.println(user.pkm.nom+" utilise sa baie");
						if(m.baie){ m.script(user.pkm); user.pkm.objTenu=null; }
					}
				}
				if(cible.pkm.stats[2][0]<=(int)(cible.pkm.stats[2][1]/2) && cible.pkm.statut!=Statut.KO){
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
	
	public void ajoutXpStack(PokemonCombat pkmc){
		for(PokemonCombat p:pkmListe){
			if(p.equipe!=pkmc.equipe && !p.XpStack.contains(pkmc.pkm)){
				p.XpStack.push(pkmc.pkm);
			}
		}
	}
	
	
	public void pokeswap(PokemonCombat user){
		int i=0; int act=0; int done=0; Pkm pkmRef; Stack<Pkm> stackRef; 
		if(!user.isIA){
			while(done==0){
				System.out.println("Qui voulez vous envoyer ?");
				for(PokemonCombat p: user.equipe){
					System.out.println(i+" "+p.pkm.nom+" LV."+p.pkm.stats[0][0]+" "+p.pkm.stats[2][0]+"/"+p.pkm.stats[2][1]+" "+p.pkm.statut);
					i++;
				}
				act=sc.nextInt();
				if(user.equipe[act].pkm.statut!=Statut.KO){
					System.out.println(user.equipe[act].pkm.nom+" remplace "+user.pkm.nom);
					pkmRef=user.pkm; stackRef=user.XpStack;
					user.pkm=user.equipe[act].pkm; user.XpStack=user.equipe[act].XpStack;
					user.equipe[act].pkm=pkmRef; user.equipe[act].XpStack=stackRef;
					ajoutXpStack(user);
					done=1;
				}
				else{
					System.out.println("Vous ne pouvez pas envoyer un Pokemon K.O au combat !");
					i=0;
				}
			}
		}
		else{
			for(PokemonCombat p:user.equipe){
				if(p.pkm.statut!=Statut.KO){
					System.out.println(user.prop+" envoie "+p.pkm.nom+" au combat");
					pkmRef=user.pkm; stackRef=user.XpStack;
					user.pkm=p.pkm; user.XpStack=stackRef;
					p.pkm=pkmRef; p.XpStack=stackRef;
					ajoutXpStack(user);
				}
			}
		}
	}
	
	
}
