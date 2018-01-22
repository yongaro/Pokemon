package pokemon.modele;

import java.util.Vector;

/* La classe Dialog permet de stocker toutes les instructions relatives a un dialogue d'un PNJ*/

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
	public String execute(NPCList list, Joueur j) throws NoMoreInstructionException, CombatException {
		if(currentInstruction < getCount()) {
			if(instructions.get(currentInstruction) instanceof InstructionCombat) {
				currentInstruction++;
				System.out.println("COMBAT");
				throw new CombatException();
			}
			return instructions.get(currentInstruction++).execute(list, j);
		}
		else{
			currentInstruction = 0;
			throw new NoMoreInstructionException();
		}
	}
	
	//Accesseurs
	public Instruction getCurrent() throws NoMoreInstructionException {
		if(currentInstruction < getCount()) {			
			return instructions.get(currentInstruction);
		}
		else {
			throw new NoMoreInstructionException();
		}
	}
	public int getCurrentIndex() {
		return currentInstruction;
	}
	public void next() {
		currentInstruction++;
	}
	public void reset() {
		currentInstruction = 0;
	}
	public int getCount() {
		return instructions.size();
	}
}
