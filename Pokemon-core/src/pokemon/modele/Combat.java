package pokemon.modele;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

import pokemon.annotations.Tps;
import pokemon.launcher.MyGdxGame;

@Tps(nbhours=17)
public class Combat extends Thread {
	protected Terrain terrain;
	protected Climat climat;
	protected PokemonCombat[] equipe1;
	protected PokemonCombat[] equipe2;
	protected PokemonCombat[] pkmListe;

	protected Scanner sc = new Scanner(System.in); //BERK
	protected  String buffer="";
	protected boolean bufferReady;
	protected PokemonCombat pCourant;
	protected int actflag=-1;
	protected  int act=-1;
	protected  int ind=-1;
	protected  int swapInd=-1;
	protected boolean freeze=false;
	
	
	//0 niveau 1 XP 2 PV 3 ATT 4 DEF 5 ATTSP 6 DEFSP 7 VIT 8 Precision (100) 9 Esquive (5% de base)
	
	public Combat(){
		terrain=Terrain.Plaine; climat=Climat.Normal;
		bufferReady=false;
		
	}
	public Combat(Joueur j1,Joueur j2){ this(); this.initSolo(j1,j2); } 
	public Combat(Joueur j,Dresseur d){ this(); this.initSolo(j,d); }
	
	public int gagnant(){
		int nbko1=0; int nbko2=0;
		
		for(int i=0;i<equipe1.length;i++){
			if(equipe1[i].pkm.stats[2][0]<=0){ nbko1++; }
		}
		
		for(int i=0;i<equipe2.length;i++){
			if(equipe2[i].pkm.stats[2][0]<=0){ nbko2++; }
		}
		if(nbko2==equipe2.length){ return 1; }
		if(nbko1==equipe1.length){ return 2; }
		return 0;
	}
		public PokemonCombat[] getPkmListe() {
		return pkmListe;
	}
	public void run(){ this.combatsolo();
	}
	
	
	public void initSolo(Joueur j1,Joueur j2){
		equipe1=new PokemonCombat[j1.teamsize];
		equipe2=new PokemonCombat[j2.teamsize];
		pkmListe=new PokemonCombat[2];
		
		for(int i=0;i<j1.teamsize;i++){
			equipe1[i]=new PokemonCombat(j1.team[i],false,j1); equipe1[i].equipe=equipe1;
		}
		for(int i=0;i<j2.teamsize;i++){
			equipe2[i]=new PokemonCombat(j2.team[i],true,j2); equipe2[i].equipe=equipe2;
		}
		pkmListe[0]=equipe1[0];
		pkmListe[1]=equipe2[0];
		pkmListe[0].adv[0]=pkmListe[1]; pkmListe[0].XpStack.add(pkmListe[0].adv[0].pkm);
		pkmListe[1].adv[0]=pkmListe[0]; pkmListe[1].XpStack.add(pkmListe[1].adv[0].pkm);
		pkmListe[0].listeIndice=0; pkmListe[1].listeIndice=1;
	}
	
	public void initSolo(Joueur j,Dresseur d){
		equipe1=new PokemonCombat[j.teamsize];
		equipe2=new PokemonCombat[d.getTeam().size()];
		pkmListe=new PokemonCombat[2];
		for(int i=0;i<j.teamsize;i++){
			equipe1[i]=new PokemonCombat(j.team[i],false,j); equipe1[i].equipe=equipe1;
		}
		for(int i=0;i<d.getTeam().size();i++){
			equipe2[i]=new PokemonCombat(d.getTeam().elementAt(i),false,d); equipe2[i].equipe=equipe2;
		}
		pkmListe[0]=equipe1[0];
		pkmListe[1]=equipe2[0];
		pkmListe[0].adv[0]=pkmListe[1]; pkmListe[0].XpStack.add(pkmListe[0].adv[0].pkm);
		pkmListe[1].adv[0]=pkmListe[0]; pkmListe[1].XpStack.add(pkmListe[1].adv[0].pkm);
		pkmListe[0].listeIndice=0; pkmListe[1].listeIndice=1;
	}
	
	
	public int combatsolo(){
		while(this.gagnant()==0){
			Arrays.sort(pkmListe);
			for(int i=0;i<pkmListe.length;i++){
				this.resetAct();
				this.setBufferState(false);
				pCourant=pkmListe[i];
				pkmListe[i].action(pkmListe[i].adv[0],this);
			}
			//Application des d�gats sur la dur�e
			for(PokemonCombat p:pkmListe){
				if(p.pkm.statut==Statut.Empoisonne || p.pkm.statut==Statut.Brule ){ 
					p.pkm.statut.StatEffect(p.pkm,1);
					for(Statut s: p.pkm.supTemp){
						s.StatEffect(p.pkm,1);
					}
					if(p.pkm.stats[2][0]<=0){ p.XPreward(); pokeswap(p); }
				}
			}
		}
		return this.gagnant();
	}
	
	
	public void action(PokemonCombat user,PokemonCombat cible){
		int isdone=0; int i=0; int ch1=0; int ch2=1;
		while(isdone==0){
			System.out.println("Debut du tour du joueur");
			this.getAct();
			
			switch(actflag){
			case 0:
				System.out.println("Execution d'une Capacite");
				//while((act=sc.nextInt())<user.cap.max){System.out.println(act); }
				//act=sc.nextInt();
				//Application des statuts pouvant empecher l'action
				ch1=user.pkm.statut.StatEffect(user.pkm,0);
				for(Statut s: user.pkm.supTemp){
					if(s.StatEffect(user.pkm,0)==0){
						ch2=0;
					}
				}
				if(ch1==1 && ch2==1){
					user.pkm.cap.utiliser(act,user.pkm,cible.pkm,this);
				}
				//Consequences de l'action
				if(user.pkm.stats[2][0]<=0){ user.XPreward(); pokeswap(user); }
				if(cible.pkm.stats[2][0]<=0){ cible.XPreward(); pokeswap(cible); }
				if(user.pkm.stats[2][0]<=(int)(user.pkm.stats[2][1]/2) && cible.pkm.statut!=Statut.KO){
					if(user.pkm.objTenu instanceof Medicament && cible.pkm.objTenu!=null){
						Medicament m=(Medicament)user.pkm.objTenu;
						this.ajoutBuffer(user.pkm.nom+" utilise sa baie");
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
				this.setBufferState(true);
				break;
			case 1:
				System.out.println("SWAP DE POKEMON A REMETTRE");
				break;
			case 2:
				pokeswap(user);
				isdone=1;
				//traitement capacit� passive ici
				break;
			case 3:
				System.out.println("FUITE");
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
					System.out.println(i+" "+p.pkm.nom+" LV."+p.pkm.stats[0][0]+" "+p.pkm.stats[0][1]+"% "+p.pkm.stats[2][0]+"/"+p.pkm.stats[2][1]+" "+p.pkm.statut);
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
	public Terrain getTerrain(){ return terrain; }
	public Climat getClimat(){ return climat; }
	public PokemonCombat[] getEquipe1(){ return equipe1;}
	public PokemonCombat[] getEquipe2(){ return equipe2;}
	
	
	
	// Fonctions de manipulation des objets synchronis�s entre modele et vue
	public synchronized void ajoutBuffer(String s){ 
		this.buffer+=s+"\n";
		notify();
	}
	
	public synchronized boolean bufferIsEmpty(){ return this.buffer.compareTo("")==0; }
	public synchronized boolean bufferIsReady(){ return bufferReady; }
	
	public synchronized String readBuffer(){
		while(this.buffer.compareTo("")==0){
			try { wait(); } 
			catch(InterruptedException ie) { ie.printStackTrace(); }
		}
		System.out.println(buffer);
		return this.buffer;
	}
	public synchronized void resetBuffer(){ this.buffer=""; }
	public synchronized void setBufferState(boolean st){
		bufferReady=st;
		if(bufferReady){ notify(); }
	}
	
	public synchronized void getAct(){
		while(actflag==-1 && act==-1){
			try { wait(); } 
			catch(InterruptedException ie) { ie.printStackTrace(); }
		}
	}
	
	
	public synchronized void setAct(int aflag,int act){
		actflag=aflag; this.act=act; notify();
	}
	
	
	public synchronized void resetAct(){ this.actflag=-1; this.act=-1; notify(); }

	public synchronized void setfreeze(boolean f){ 
		freeze=f;
		if(!freeze){ System.out.println("SETFREEZE "+f); notify();}
		else{
			while(freeze){
				try { System.out.println("SETFREEZE "+f);  wait(); } 
				catch(InterruptedException ie) { ie.printStackTrace(); }
			}
		}
		System.out.println("sortie de sommeil");
	}
	
	public synchronized PokemonCombat getPCourant(){ return pCourant;}
	
}


