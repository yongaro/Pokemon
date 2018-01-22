package pokemon.modele;

public enum Climat {
	Pluie,Zenith,Grele,TempeteSable,Normal;
	
	
	
	public double mod(Type type){
		if(this==Pluie){
			if(type==Type.Feu){return 0.5;}
			if (type==Type.Eau){return 1.5;}
		}
		if(this==Zenith){
			if(type==Type.Feu){return 1.5;}
			if (type==Type.Eau){return 0.5;}
		}
		return 1.0;
	}
	
	protected void effet(Pkm cible){
		if(this==Grele && !cible.type.contains(Type.Glace)){cible.infliger((int)cible.stats[2][1]/16);}
		if(this==TempeteSable && (!cible.type.contains(Type.Acier) && !cible.type.contains(Type.Roche) && !cible.type.contains(Type.Sol) )){
			cible.infliger((int)cible.stats[2][1]/16);
		}
	}
	
	protected String text(){
		if(this==Pluie){ return "Il pleut";}
		if(this==Zenith){return "Les rayons du soleil brillent !";}
		if(this==Grele){return "La grele continue";}
		if(this==TempeteSable){ return "La tempete de sable fait rage";}
		
		return null;
	}
	
}
