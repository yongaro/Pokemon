package pokemon.modele;

/* La classe Dialog permet de stocker toutes les informations relatives � un dialogue d'un PNJ :
 * -Le texte du dialogue
 * -Le changement de status d'un autre PNJ*/

public class Dialog {
	private String text;
	private int npcId;
	private int newStatus;
	
	//Constructeurs
	public Dialog() {
		this.setText("Bonjour !");
		this.setNPCId(0);
		this.setNewStatus(0);
	}
	public Dialog(String texte, int changeId, int changeStatus) {
		this.setText(texte);
		this.setNPCId(changeId);
		this.setNewStatus(changeStatus);
	}

	//Fonctionnalités principales
	public String execute(NPCList npcList) {
		if(npcId != 0) {
			npcList.getNPC(npcId).setStatus(newStatus);
		}
		return text;
	}
	
	//Accesseurs
	public String getText() {
		return text;
	}
	public void setText(String texte) {
		this.text = texte;
	}
	public int getNPCId() {
		return npcId;
	}
	public void setNPCId(int changeId) {
		this.npcId = changeId;
	}
	public int getNewStatus() {
		return newStatus;
	}
	public void setNewStatus(int changeStatus) {
		this.newStatus = changeStatus;
	}
}
