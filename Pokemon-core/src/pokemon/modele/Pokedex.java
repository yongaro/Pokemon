package pokemon.modele;

public enum Pokedex {
	Bulbizarre(new Pkm("Bulbizarre",new int[]{45,49,49,65,65,45},new Type[]{Type.Plante,Type.Poison})),
	Herbizarre(new Pkm("Herbizarre",new int[]{62,62,63,80,80,60},new Type[]{Type.Plante,Type.Poison})),
	Florizarre(new Pkm("Florizarre",new int[]{80,82,83,100,100,80},new Type[]{Type.Plante,Type.Poison})),
	Salameche(new Pkm("Salameche",new int[]{39,52,43,60,50,65},new Type[]{Type.Feu})),
	Reptincel(new Pkm("Reptincel",new int[]{58,64,58,80,65,80},new Type[]{Type.Feu})),
	Dracaufeu(new Pkm("Dracaufeu",new int[]{78,84,78,109,85,100},new Type[]{Type.Feu})),
	Rattata(new Pkm("Rattata",new int[]{30,56,35,25,35,72},new Type[]{Type.Normal,null})),
	Rattatac(new Pkm("Rattatac",new int[]{55,81,60,50,70,97},new Type[]{Type.Normal,null}))
	;
	
	
	Pokedex(Pkm p){
		pk=p;
	}
	
	public Pkm get(){
		return this.pk;
	}
	
	protected Pkm pk;
}
