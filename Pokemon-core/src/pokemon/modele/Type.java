package pokemon.modele;

public enum Type {
	Eau,Feu,Plante,Poison,Vol,Electrique,Sol,Roche,Psy,Spectre,Insecte,Tenebre,Acier,Combat,Glace,Dragon,Normal,Fee;
	
	public double isweak(Type test){
		
		if(this==Type.Eau){
			if(test==Type.Feu || test==Type.Eau || test==Type.Glace || test==Type.Acier){return 1/2.0; }
			if(test==Type.Electrique || test==Type.Plante){return 2.0;}
			else{return 1.0;}
		}
		if(this==Type.Feu){
			if(test==Type.Feu || test==Type.Plante || test==Type.Glace || test==Type.Acier || test==Type.Insecte || test==Type.Fee){
				return 1/2.0;
			}
			if(test==Type.Eau || test==Type.Sol || test==Type.Roche){ return 2.0;}
			else{return 1.0;}
		}
		if(this==Type.Plante){
			if(test==Type.Eau || test==Type.Plante || test==Type.Sol || test==Type.Electrique){return 1/2.0;}
			if(test==Type.Feu || test==Type.Vol || test==Type.Glace || test==Type.Insecte || test==Type.Poison){
				return 2.0;
			}
			else{return 1.0;}
		}
		if(this==Type.Poison){
			if(test==Type.Plante || test==Type.Combat || test==Type.Poison || test==Type.Insecte || test==Type.Fee){return 1/2.0;}
			if(test==Type.Sol || test==Type.Psy){return 2.0;}
			else{return 1.0;}
		}
		if(this==Type.Vol){
			if(test==Type.Plante || test==Type.Insecte || test==Type.Combat){return 1/2.0;}
			if(test==Type.Sol){return 0.0;}
			if(test==Type.Electrique || test==Type.Roche || test==Type.Glace){return 2.0;}
			else{return 1.0;}
		}
		if(this==Type.Electrique){
			if(test==Type.Electrique || test==Type.Vol){return 1/2.0;}
			if(test==Type.Sol){return 2.0;}
			else{return 1.0;}
		}
		if(this==Type.Sol){
			if(test==Type.Poison || test==Type.Roche){return 1/2.0;}
			if(test==Type.Electrique){return 0.0;}
			if(test==Type.Eau || test==Type.Plante || test==Type.Glace){return 2.0;}
			else{return 0.0;}
		}
		if(this==Type.Roche){
			if(test==Type.Normal || test==Type.Feu || test==Type.Poison || test==Type.Vol){return 1/2.0;}
			if(test==Type.Eau || test==Type.Plante || test==Type.Combat || test==Type.Acier || test==Type.Sol){return 2.0;}
			else{return 1.0;}
		}
		if(this==Type.Psy){
			if(test==Type.Psy || test==Type.Combat){return 1/2.0;}
			if(test==Type.Insecte || test==Type.Tenebre || test==Type.Spectre){return 2.0;}
			else{return 1.0;}
		}
		if(this==Type.Spectre){
			if(test==Type.Poison || test==Type.Insecte){return 1/2.0;}
			if(test==Type.Normal || test==Type.Combat){return 0.0;}
			if(test==Type.Spectre || test==Type.Tenebre){return 2.0;}
			else{return 1.0;}
		}
		if(this==Type.Insecte){
			if(test==Type.Combat || test==Type.Sol || test==Type.Plante){return 1/2.0;}
			if(test==Type.Feu || test==Type.Roche || test==Type.Vol){return 2.0;}
			else{return 1.0;}
		}
		if(this==Type.Tenebre){
			if(test==Type.Tenebre || test==Type.Spectre){return 1/2.0;}
			if(test==Type.Psy){return 0.0;}
			if(test==Type.Combat || test==Type.Insecte || test==Type.Fee){return 2.0;}
			else{return 1.0;}
		}
		if(this==Type.Acier){
			if(test==Type.Eau || test==Type.Electrique){return 1.0;}
			if(test==Type.Poison){return 0.0;}
			if(test==Type.Feu || test==Type.Combat || test==Type.Sol){return 2.0;}
			else{return 1/2.0;}
		}
		if(this==Type.Combat){
			if(test==Type.Insecte || test==Type.Roche || test==Type.Tenebre){return 1/2.0;}
			if(test==Type.Vol || test==Type.Psy || test==Type.Fee){return 2.0;}
			else{return 1.0;}
		}
		if(this==Type.Glace){
			if(test==Type.Glace){return 1/2.0;}
			if(test==Type.Combat || test==Type.Feu || test==Type.Roche || test==Type.Acier){return 2.0;}
			else{return 1.0;}
		}
		if(this==Type.Dragon){
			if(test==Type.Eau || test==Type.Plante || test==Type.Electrique || test==Type.Feu){return 1/2.0;}
			if(test==Type.Dragon || test==Type.Glace || test==Type.Fee){return 2.0;}
			else{return 1.0;}
		}
		if(this==Type.Normal){
			if(test==Type.Spectre){return 0.0;}
			if(test==Type.Combat){return 2.0;}
			else{return 1.0;}
		}
		
		if(this==Type.Fee){
			if(test==Type.Dragon){return 0.0;}
			if(test==Type.Acier || test==Type.Poison){return 2.0;}
			if(test==Type.Combat || test==Type.Insecte || test==Type.Tenebre){return 1/2.0;}
			else{return 1.0;}
		}
		
		return 0;
	}
}
