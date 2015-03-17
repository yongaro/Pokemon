package pokemon.modele;

public class UniteStockage<A extends Qmax & Infos> /*implements Comparable*/ {
	protected A cible;
	protected int quantite;//ou PP
	protected int quantitemax;
	
	
	public UniteStockage(){ quantite=1; quantitemax=1; }
	public UniteStockage(A a){cible=a; quantitemax=a.qmax(); quantite=1;}
	public UniteStockage(A a,int i){cible=a; quantite=i;}
	public UniteStockage(UniteStockage<A> u){cible=u.cible; quantite=u.quantite; quantitemax=u.quantitemax;}
	
	public String toString(){return cible.toString();}
	public boolean equals(UniteStockage<A> u){
		return (this.cible.equals(u.cible) && this.quantite==u.quantite);
	}
	public void script(Pkm user,Pkm cible,Combat context){
		System.out.println("TODO");
	}
	
	public int ajoutQte(int qte){
		if(qte+quantite<quantitemax){
			quantite+=qte;
			return 0;
		}
		return -1;
	}
	
	public A get(){ return cible;}
	public String getInfos(){return cible.getInfos();}
	 public String getDesc(){return cible.getDesc();}
	 public String getNom(){return cible.getNom();}
	 public int getQte(){ return quantite;}
	 public int getQteMax(){ return quantitemax;}
}