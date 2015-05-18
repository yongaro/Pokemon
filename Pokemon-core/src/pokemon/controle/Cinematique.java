package pokemon.controle;

import java.util.Vector;

import pokemon.launcher.MapScreen;
import pokemon.modele.Dresseur;
import pokemon.modele.Instruction;
import pokemon.modele.InstructionCombat;
import pokemon.modele.InstructionStatus;
import pokemon.modele.InstructionTexte;
import pokemon.modele.NPC;
import pokemon.modele.NoMoreInstructionException;

public class Cinematique {
	
	//Attributs étrangers
	private MapScreen screen;
	private NPC npc;
	
	//Attributs internes
	private CinematiqueController controller;
	@SuppressWarnings("unused")
	private Vector<DeplacementNPC> isMoving;
	private Instruction currentInstruction;
	
	public Cinematique(MapScreen screen, NPC npc)
	{
		this.screen = screen;
		this.npc = npc;
		controller = new CinematiqueController();
		isMoving = new Vector<DeplacementNPC>();
		try {
			currentInstruction = getNextInstruction();
		} catch (NoMoreInstructionException e) {
			currentInstruction = null;
		}
	}
	
	public Instruction getNextInstruction() throws NoMoreInstructionException {
		return npc.getCurrentInstruction();
	}
	
	public boolean update() {
		//Si la cinématique n'a pas d'instruction, on la fini.
		if(currentInstruction == null) {
			return false;
		}
		
		//Si l'instruction courrante est un texte à afficher...
		if(currentInstruction instanceof InstructionTexte) {
			InstructionTexte ins = (InstructionTexte) currentInstruction;
			
			//... alors on affiche le texte à l'écran
			screen.addBox(ins.getText());
			
			//Si le joueur a appuyé sur Enter...
			if(controller.isSkipped()) {
				//... on reset le controller, et on passe à l'instruction suivante
				return skip();
			}
		}
		else if(currentInstruction instanceof InstructionStatus) {
			InstructionStatus ins = (InstructionStatus) currentInstruction;
			NPC npcToUpdate = screen.getNPCById(ins.getId());
			npcToUpdate.setNewStatus(ins.getNewStatus());
			
			return skip();
		}
		else if(currentInstruction instanceof InstructionCombat) {
			//On vérifie si l'interlocuteur est un dresseur (et qu'il a une équipe)
			if(npc instanceof Dresseur) {
				Dresseur dress = (Dresseur) npc;
				if(dress.asTeam()) {
					//... on lance un combat
					screen.startBattle(dress);
					return skip();
				}
			}
		}
		else {
			return skip();
		}
		
		return true;
	}
	
	private boolean skip() {
		controller.setSkipped(false);
		npc.next();
		try {
			currentInstruction = getNextInstruction();
		} catch (NoMoreInstructionException e) {
			npc.reset();
			screen.removeBox();
			return false;
		}
		
		return true;
	}

	public CinematiqueController getController() {
		return controller;
	}
}
