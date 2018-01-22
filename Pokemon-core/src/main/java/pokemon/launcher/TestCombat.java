package pokemon.launcher;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

import pokemon.modele.*;
import pokemon.annotations.Cpx;

public class TestCombat {
	
	
	
	

	
	public static void main(String args[]) throws Exception{
		PokemonCore.initStatic();
		Combat ctest=new Combat();
		//ctest.combatsolo(PokemonCore.Jtest,PokemonCore.Ptest[5]);
		//TestCombat.fromXML();
	} 

	
}
