package pokemon.controle;

import java.util.Vector;

import pokemon.launcher.MapScreen;
import pokemon.modele.Dialog;
import pokemon.modele.Dresseur;
import pokemon.modele.Instruction;
import pokemon.modele.InstructionCombat;
import pokemon.modele.InstructionMouvement;
import pokemon.modele.InstructionStatus;
import pokemon.modele.InstructionTexte;
import pokemon.modele.NPC;
import pokemon.modele.NoMoreInstructionException;

public class Cinematique {
	
	//Attributs �trangers
	private MapScreen screen;
	private Dialog dialog;
	
	//Attributs internes
	private CinematiqueController controller;
	private Vector<NPC> changingStatus;
	@SuppressWarnings("unused")
	private Vector<DeplacementNPC> isMoving;
	private Instruction currentInstruction;
	
	public Cinematique(MapScreen screen, NPC npc)
	{
		this.screen = screen;
		this.dialog = npc.getCurrentDialog();
		controller = new CinematiqueController();
		isMoving = new Vector<DeplacementNPC>();
		changingStatus = new Vector<NPC>();
		try {
			currentInstruction = getCurrentInstruction();
		} catch (NoMoreInstructionException e) {
			currentInstruction = null;
		}
	}
	
	public Instruction getCurrentInstruction() throws NoMoreInstructionException {
		return dialog.getCurrent();
	}
	
	
	//Renvoie vrai si la cin�matique continu, faux si elle est finie.
	public boolean update() {
		//Si la cin�matique n'a pas d'instruction, on la fini.
		if(currentInstruction == null) {
			return false;
		}
		
		//Si l'instruction courrante est un texte � afficher...
		if(currentInstruction instanceof InstructionTexte) {
			InstructionTexte ins = (InstructionTexte) currentInstruction;
			
			//... alors on affiche le texte � l'�cran
			screen.addBox(ins.getText());
			
			//Si le joueur a appuy� sur Enter...
			if(controller.isSkipped()) {
				//... on reset le controller, et on passe � l'instruction suivante
				return skip();
			}
		}
		else if(currentInstruction instanceof InstructionStatus) {
			InstructionStatus ins = (InstructionStatus) currentInstruction;
			NPC npcToUpdate = screen.getNPCById(ins.getId());
			npcToUpdate.setNewStatus(ins.getNewStatus());
			changingStatus.add(npcToUpdate);
			
			return skip();
		}
		else if(currentInstruction instanceof InstructionCombat) {
			//On v�rifie si la cible du combat est un dresseur
			InstructionCombat ins = (InstructionCombat) currentInstruction;
			NPC npc = screen.getNPCById(ins.getId());
			
			if(npc instanceof Dresseur) {
				Dresseur dress = (Dresseur) npc;
				//Si le dresseur a une �quipe...
				if(dress.asTeam()) {
					//... on lance un combat
					screen.startBattle(dress);
					return skip();
				}
			}
		}
		else if (currentInstruction instanceof InstructionMouvement) {
			return skip();
		}
		else {
			return skip();
		}
		
		return true;
	}
	
	private boolean skip() {
		controller.setSkipped(false);
		dialog.next();
		try {
			currentInstruction = getCurrentInstruction();
		} catch (NoMoreInstructionException e) {
			//Si il n'y a plus d'instructions, alors on finit la cin�matique en ...
			//... remettant le dialogue � zero, ...
			dialog.reset();
			//... en enlevant une �ventuelle boite de dialogue, ...
			screen.removeBox();
			//... en mettant � jour les status des personnages.
			for(NPC npc : changingStatus) {
				npc.updateStatus();
			}
			return false;
		}
		
		return true;
	}

	public CinematiqueController getController() {
		return controller;
	}
}
