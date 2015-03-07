package pokemon.modele;

public enum Statut {
	Amoureux,Brule,Confus,Empoisonne,Endormi,Gele,KO,Normal,Paralyse,Peur;
	
	void StatEffect(Pkm cible){
	    if(this==Statut.Brule){
		System.out.println("La brulure inflige des degats");
	    }
	    if(this==Statut.Endormi){
		System.out.println(cible.nom+" dort");
	    }
	    if(this==Statut.Gele){
		System.out.println(cible.nom+" est gele");
	    }
	    if(this==Statut.Paralyse){
		System.out.println(cible.nom+" est paralyse");
	    }
	    if(this==Statut.Empoisonne){
		System.out.println("Le poison inflige des degats");
	    }
	    if(this==Statut.Confus){
		System.out.println(cible.nom+" est Confus");
		System.out.println(cible.nom+" se blesse dans sa follie");
	    }
	}
}
