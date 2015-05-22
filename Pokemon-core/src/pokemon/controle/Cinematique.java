package pokemon.controle;

import java.util.Vector;

import pokemon.launcher.MapScreen;
import pokemon.launcher.MyGdxGame;
import pokemon.modele.Dialog;
import pokemon.modele.Dresseur;
import pokemon.modele.Instruction;
import pokemon.modele.InstructionCombat;
import pokemon.modele.InstructionMouvement;
import pokemon.modele.InstructionStatus;
import pokemon.modele.InstructionTexte;
import pokemon.modele.NPC;
import pokemon.modele.NoMoreInstructionException;
import pokemon.vue.NPCVue;

public class Cinematique {
	
	//Attributs �trangers
	private MapScreen screen;
	private Dialog dialog;
	
	//Attributs internes
	private CinematiqueController controller;
	private Vector<NPC> changingStatus;
	private Vector<DeplacementNPC> isMoving;
	private Instruction currentInstruction;
	
	public Cinematique(MapScreen screen, NPC npc, MyGdxGame game)
	{
		this.screen = screen;
		this.dialog = npc.getCurrentDialog();
		controller = new CinematiqueController(game);
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
	
	
	//Renvoie vrai si la cinematique continu, faux si elle est finie.
	public boolean update() {
		//Si la cinematique n'a pas d'instruction, on la fini.
		if(currentInstruction == null) {
			return false;
		}
		
		//Si l'instruction courrante est un texte a afficher...
		if(currentInstruction instanceof InstructionTexte) {
			InstructionTexte ins = (InstructionTexte) currentInstruction;
			
			//... alors on affiche le texte a l'ecran
			screen.addBox(ins.getText());
			
			//Si le joueur a appuye sur Enter...
			if(controller.isSkipped()) {
				//... on reset le controller, et on passe a l'instruction suivante
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
			//On verifie si la cible du combat est un dresseur
			InstructionCombat ins = (InstructionCombat) currentInstruction;
			NPC npc = screen.getNPCById(ins.getId());
			
			if(npc instanceof Dresseur) {
				Dresseur dress = (Dresseur) npc;
				//Si le dresseur a une equipe...
				if(dress.asTeam()) {
					//... on lance un combat
					screen.startBattle(dress);
					return skip();
				}
			}
		}
		else if (currentInstruction instanceof InstructionMouvement) {
			screen.removeBox();
			InstructionMouvement ins = (InstructionMouvement) currentInstruction;
			
			boolean block = true;
			//Si l'appel est bloquant, alors :
			if(block) {
				//On ajoute un deplacement de NPC si on ne l'a pas d�ja fait :
				if(isMoving.isEmpty()) {					
					NPCVue npc = screen.getNPCVueById(ins.getId());
					if(npc == null) {
						return skip();
					}
					isMoving.addElement(new DeplacementNPC(npc, ins.getDir(), ins.getDist()));
				}
				//On met � jour la position du (ou des) NPC :
				for(DeplacementNPC depl : isMoving) {
					depl.update();
					//Si le NPC a fini de se deplacer, on l'enleve du tableau, et on passe a l'instruction suivante
					if(depl.isDoneMoving()) {
						isMoving.removeElement(depl);
						System.out.println("TUTUT");
						return skip();
					}
				}
			}
			
			return true;
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
			//Si il n'y a plus d'instructions, alors on finit la cinematique en ...
			//... remettant le dialogue a zero, ...
			dialog.reset();
			//... en enlevant une eventuelle boite de dialogue, ...
			screen.removeBox();
			//... en mettant a jour les status des personnages.
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
