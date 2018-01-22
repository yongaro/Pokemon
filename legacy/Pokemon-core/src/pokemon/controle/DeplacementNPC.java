package pokemon.controle;

import com.badlogic.gdx.Gdx;

import pokemon.modele.Direction;
import pokemon.vue.NPCVue;

public class DeplacementNPC {
	public NPCVue npc;
	public Direction moveDirection;
	public float moveDistance;
	
	public DeplacementNPC(NPCVue npc, Direction dir, float distance) {
		this.npc = npc;
		this.moveDirection = dir;
		this.moveDistance = distance;
	}
	
	public boolean isDoneMoving() {
		return moveDistance <= 0f;
	}
	
	public void update() {
		float speed = 60;
		npc.move(moveDirection, moveDistance, speed);
		moveDistance -= Gdx.graphics.getDeltaTime()*speed;
	}
	
	public NPCVue getNPC() {
		return npc;
	}
}
