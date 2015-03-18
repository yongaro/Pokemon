package pokemon.modele;

import java.util.Vector;
import java.lang.Iterable;
import java.util.Iterator;

public class Stockage<A extends Qmax & Infos> implements Iterable<UniteStockage<A>> {
	protected Vector<UniteStockage<A>> contenu;
	protected int max;
	
	public Stockage(){contenu=new Vector<UniteStockage<A>>();}
	public Stockage(int max){contenu=new Vector<UniteStockage<A>>(); this.max=max;}
	public Stockage(Vector<UniteStockage<A>> cpy,int capmax){
		contenu=new Vector<UniteStockage<A>>();
		for(UniteStockage<A> a:cpy){
			contenu.add(a);
		}
		max=capmax;
	}
	
	
	
	
	public int indiceOf(UniteStockage<A> u){
		int i=0;
		for(i=0;i<max;i++){
			if(this.contenu.elementAt(i).equals(u)){ return i; }
		}
		return -1;
	}
	
	public int indiceOf(A a){
		int i=0;
		for(i=0;i<max;i++){
			if(this.contenu.elementAt(i).cible.equals(a)){
				return i;
			}
		}
		return -1;
	}
	
	public boolean contains(A a){
		return contenu.contains(a);
	}
	
	public void swap(A a1,A a2){
		UniteStockage<A> temp1=new UniteStockage<A>(a1);
		UniteStockage<A> temp2=new UniteStockage<A>(a2);
		int ind1=this.indiceOf(temp1);
		int ind2=this.indiceOf(temp2);
		//facultatif ?
		temp1.quantite=contenu.elementAt(ind1).quantite;
		temp1.quantitemax=contenu.elementAt(ind1).quantitemax;
		temp2.quantite=contenu.elementAt(ind2).quantite;
		temp2.quantitemax=contenu.elementAt(ind2).quantitemax;
		if(ind1>ind2){
			this.contenu.remove(ind1);
			this.contenu.remove(ind2);
		}
		else{
			this.contenu.remove(ind2);
			this.contenu.remove(ind1);
		}
		this.contenu.insertElementAt(temp2,ind1);
		this.contenu.insertElementAt(temp1,ind2);	
	}
	
	public void swap(UniteStockage<A> u1,UniteStockage<A> u2){
		int ind1=this.indiceOf(u1); int ind2=this.indiceOf(u2);
		UniteStockage<A> temp1=u1; UniteStockage<A> temp2=u2;
		this.contenu.setElementAt(temp2,ind1);
		this.contenu.setElementAt(temp1,ind2);
	}
	
	public void add(UniteStockage<A> u){
		if(contenu.size()<max){
			contenu.add(u);	
		}
	}
	public void add(A a){
		if(contenu.size()<max){
			contenu.add(new UniteStockage<A>(a));
			
		}
	}
	
	public void utiliser(int index,Pkm user,Pkm cible,Combat context){
		contenu.elementAt(index).utiliser(user,cible,context);
		if(contenu.elementAt(index).cible instanceof Objet && contenu.elementAt(index).quantite==0){
			contenu.remove(contenu.elementAt(index));
		}
	}
	
	
	public void remove(A a){
		if(contenu.size()>0){
			contenu.remove(a);
		}
	}
	public void remove(UniteStockage<A> u){
		if(contenu.size()>0){
			contenu.remove(u);
		}
	}
	
	public void clear(){
		for(UniteStockage<A> u:this.contenu){
			contenu.remove(u);
		}
	}
	
	public UniteStockage<A> elementAt(int index){return this.contenu.elementAt(index);}
	public A at(int index){return this.contenu.elementAt(index).cible;}
	
public class IteratorStck implements Iterator<UniteStockage<A>>{
		
		protected int ind;
			
		public IteratorStck(){ ind=0; }
		
		@Override
		public boolean hasNext(){
			if(ind<contenu.size()){return true;}
			else{return false;}
		}
		@Override
		public UniteStockage<A> next(){
			return contenu.elementAt(ind++);
		}
		@Override
		public void remove(){
			//TODO Auto-generated method stub
		}
	}
	
	public IteratorStck iterator(){ return new IteratorStck();}
	
	public int size(){ return contenu.size(); }
	
	public String toString(){
		String res="";
		for(UniteStockage<A> a:contenu){res+=a.toString()+"\n";}
		return res;
	}
}
