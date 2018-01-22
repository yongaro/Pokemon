package pokemon.modele;

import java.util.Iterator;

public class IteratorStck<A extends Qmax & Infos> implements Iterator<UniteStockage<A>>{
		
		protected Stockage<A> S;
		protected int ind;
		protected final int size;
		
		
		public IteratorStck(Stockage<A> sa){S=sa; size=sa.contenu.size();ind=0; 
		}
		
		@Override
		public boolean hasNext(){
			if(ind<size){return true;}
			else{return false;}
		}
		@Override
		public UniteStockage<A> next(){
			return S.elementAt(ind++);
		}
		@Override
		public void remove(){
			//TODO Auto-generated method stub
		}
	}