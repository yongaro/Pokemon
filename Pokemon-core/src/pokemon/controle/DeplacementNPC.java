package pokemon.controle;

import com.badlogic.gdx.Gdx;

import pokemon.modele.Direction;
import pokemon.vue.NPCVue;

public class DeplacementNPC {
	public NPCVue npc;
	public Direction moveDirection;
	public int moveDistance;
	
	public DeplacementNPC(NPCVue npc, Direction dir, int distance) {
		this.npc = npc;
		this.moveDirection = dir;
		this.moveDistance = distance;
	}
	
	public boolean isDoneMoving() {
		return moveDistance <= 0;
	}
	
	public void update() {
		float speed = 60;
		npc.move(moveDirection, moveDistance, speed);
		moveDistance -= Gdx.graphics.getDeltaTime()*speed;
	}
}
