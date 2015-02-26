package pokemon.modele;

public enum Statut {
	Normal,Brule,Endormi,Gele,Paralyse,Empoisonne,Confus,KO;
	
	void StatEffect(Pkm cible){
	    if(this==Statut.Brule){
		System.out.println("La brulure inflige des dégats");
	    }
	    if(this==Statut.Endormi){
		System.out.println(cible.nom+" dort");
	    }
	    if(this==Statut.Gele){
		System.out.println(cible.nom+" est gelé");
	    }
	    if(this==Statut.Paralyse){
		System.out.println(cible.nom+" est paralysé");
	    }
	    if(this==Statut.Empoisonne){
		System.out.println("Le poison inflige des dégats");
	    }
	    if(this==Statut.Confus){
		System.out.println(cible.nom+" est Confus");
		System.out.println(cible.nom+" se blesse dans sa follie");
	    }
	}
}
