package pokemon.modele;

import java.util.Vector;

import com.badlogic.gdx.math.Vector2;

public class Marchand extends NPC {
	private Vector<Vente> ventes;
	
	//Constructeurs
	public Marchand() {
		super();
		ventes = new Vector<Vente>();
	}
	public Marchand(String path) {
		super(path);
		ventes = new Vector<Vente>();
	}
	public Marchand(Vector2 pos) {
		super(pos);
		ventes = new Vector<Vente>();
	}
	public Marchand(String path, Vector2 pos) {
		super(path, pos);
		ventes = new Vector<Vente>();
	}
	public Marchand(String path, Vector2 pos, int status) {
		super(path, pos, status);
		ventes = new Vector<Vente>();
	}
	
	//Fonctionnalitees principales
	public void addItem(Objet o, int price, boolean permanent) {
		ventes.addElement(new Vente(o, price, permanent));
	}
	public void removeItem(Objet o) {
		for(int i = 0;i<ventes.size();i++) {
			if(ventes.get(i).getItem() == o) {
				ventes.remove(i);
			}
		}
	}
	public Objet buy(Joueur j, Objet o) {
		for(int i = 0;i<ventes.size();i++) {
			if(ventes.get(i).getItem() == o && j.argent >= ventes.get(i).getPrice()) {
				Objet item = ventes.get(i).getItem();
				//On supprime la vente si celle ci n'est pas permanente
				if(!ventes.get(i).isPermanent()) {
					ventes.remove(i);
				}
				return item;
			}
		}
		return null;
	}
	public Objet buy(Joueur j, int index) {
		if(j.argent >= ventes.get(index).getPrice()) {
			Objet item = ventes.get(index).getItem();
			//On supprime la vente si celle ci n'est pas permanente
			if(!ventes.get(index).isPermanent()) {
				ventes.remove(index);
			}
			return item;
		}
		return null;
	}
	
	//Accesseurs
	public Vector<Vente> getVentes() {
		return ventes;
	}
	public void setVentes(Vector<Vente> ventes) {
		this.ventes = ventes;
	}
}
