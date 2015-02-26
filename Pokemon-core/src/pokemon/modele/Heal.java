package pokemon.modele;

//import java.util.Vector;
//import java.util.Random;

public class Heal extends Capacite{
    //Indique si la capacite soigne les statuts et les debuff de stats
    //1 si soigne les problemes de statut 2 si soigne les stats 3 les deux
    protected char code;
    
    public Heal(){
	super(); code='0';
    }
    
    public Heal(int pw,int pre,int cc,String nom,String d,Type el,int type,int  pp,char code){
	super(pw,pre,cc,nom,d,el,type,pp); this.code=code;
    }

    public void script(Pkm user,Pkm cible,Combat context){
	int soin=(int)((double)(cible.stats[2][1]*power)/100.0);

	if(soin+cible.stats[2][0]>=cible.stats[2][1]){cible.stats[2][0]=cible.stats[2][1];}
	else{cible.stats[2][0]+=soin;}
	
	if(code=='1'){
	    System.out.println(cible.nom+"n'est plus "+cible.statut);
	    cible.statut=Statut.Normal;
	}
	if(code=='2'){
	    int i=0;
	    System.out.println("Les malus de"+cible.nom+" disparaissent");
	    for(i=3;i<10;i++){
		if(cible.stats[i][0]<cible.stats[i][1]){cible.stats[i][0]=cible.stats[i][1];}
	    }
	}
	if(code=='3'){
	    System.out.println(cible.nom+"n'est plus "+cible.statut);
	    cible.statut=Statut.Normal;
	    int i=0;
	    System.out.println("Les malus de"+cible.nom+" disparaissent");
	    for(i=3;i<10;i++){
		if(cible.stats[i][0]<cible.stats[i][1]){cible.stats[i][0]=cible.stats[i][1];}
	    }
	}
 
	
}
}
