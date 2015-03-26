package pokemon.modele;

import java.util.Vector;

/* La classe Dialog permet de stocker toutes les informations relatives � un dialogue d'un PNJ :
 * -Le texte du dialogue
 * -Le changement de status d'un autre PNJ*/

public class Dialog {
	private int currentInstruction;
	private Vector<Instruction> instructions;
	
	//Constructeurs
	public Dialog(){
		currentInstruction = 0;
		instructions = new Vector<Instruction>();
	}
	
	//Fonctionnalitees principales
	public void addInstruction(Instruction inst) {
		instructions.addElement(inst);
	}
	public String execute(NPCList list, Joueur j) throws NoMoreInstructionException {
		if(currentInstruction < getCount()) {			
			return instructions.get(currentInstruction++).execute(list, j);
		}
		else{
			currentInstruction = 0;
			throw new NoMoreInstructionException();
		}
	}
	
	//Accesseurs
	public int getCurrent() {
		return currentInstruction;
	}
	public int getCount() {
		return instructions.size();
	}
}
