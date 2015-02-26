package pokemon.modele;

import java.util.Vector;

import pokemon.launcher.MyGdxGame;


public class Joueur {
	protected int ID;
	protected String nom;
	protected int argent;
	protected int[] badges;
	protected Pkm[] team;
	protected int teamsize;
	protected Vector<Stockage<Pkm>> boites;
	protected Vector<Stockage<Objet>> inventaire; //  0 Medicaments | 1 Objets Rares | 2 CT/CM | 3 Pokeball | 4 Objets 
	
	
	public Joueur(){
		ID=0; nom="TODO"; argent=2000; badges=new int[8];
		team=new Pkm[6]; teamsize=0;
		boites=new Vector<Stockage<Pkm>>();
		inventaire=new Vector<Stockage<Objet>>();
	}
	public Joueur(int id,String nom){
		ID=id; this.nom=nom; argent=2000; badges=new int[8];
		team=new Pkm[6]; teamsize=0;
		boites=new Vector<Stockage<Pkm>>();
		inventaire=new Vector<Stockage<Objet>>();
	}
	
	public Pkm[] getTeam(){ return team; }
	public int teamSize(){ return teamsize;}
	
	//Ajoute le pokemon cible a l'equipe du dresseur
	public void add(Pkm cible){
		if(teamsize<6){
			team[teamsize]=cible;
			teamsize++;
		}
	}
	
	//enleve le pokemon cible a l'equipe du dresseur
	public void remove(Pkm cible){
		int ind=-1;
		for(int i=0;i<teamsize;i++){
			if(team[i]==cible){
				ind=i; teamsize--;
				break;
			}
		}
		if(ind!=-1){
			for(int i=ind;i<teamsize;i++){
				team[i]=team[i++];
			}
			team[teamsize+1]=null;
		}
	}
	
	public void teamSet(Pkm cible,int ind){ team[ind]=cible; }
	

	
	
}
