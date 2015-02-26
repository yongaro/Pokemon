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
}
