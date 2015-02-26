package pokemon.launcher;

import pokemon.modele.*;

public class TestCombat {
	
	public static void main(String args[]){
		MyGdxGame.initStatic();
		Combat ctest=new Combat();
		ctest.combatsolo(MyGdxGame.Jtest,MyGdxGame.Ptest[5]);
	} 
	
}
