package pokemon.modele;

public class Vente {
	private Objet item;
	private int price;
	private boolean isPermanent;
	
	public Vente() {
		setItem(new Objet());
		setPrice(1);
		setPermanent(true);
	}
	public Vente(Objet obj, int p, boolean permanent) {
		setItem(obj);
		setPrice(p);
		setPermanent(permanent);
	}
	
	//Accesseurs
	public Objet getItem() {
		return item;
	}
	public void setItem(Objet item) {
		this.item = item;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isPermanent() {
		return isPermanent;
	}
	public void setPermanent(boolean isPermanent) {
		this.isPermanent = isPermanent;
	}
}
