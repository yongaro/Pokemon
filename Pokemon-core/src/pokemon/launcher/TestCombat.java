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
	
	
	
	public static void fromXML() throws Exception{

		Capacite[] testcap=new Capacite[16]; 
		XmlReader reader = new XmlReader();
		Element root = reader.parse(Gdx.files.internal("xml/Capacite.xml"));
		Element e,subroot; int cpt=0;
		
		for(int j=0;j<root.getChildCount();j++){
		subroot=root.getChild(j); 
			for(int k=0;k<subroot.getChildCount();k++){
				e=subroot.getChild(k);
				//La capacite courante est une Atk
				if(j==0){
					testcap[cpt]=new Atk(e.getInt("power"),e.getInt("pre"),e.getInt("CC"),e.get("nom"),e.get("description"),
							Type.valueOf(e.get("element")),e.getInt("type"),e.getInt("maxPP"),Statut.valueOf(e.get("effet")));
					cpt++;
				}
				//La capacite courante est une AtkChangeStats		
				if(j==1){
					testcap[cpt]=new AtkChangeStats(e.getInt("power"),e.getInt("pre"),e.getInt("CC"),e.get("nom"),e.get("description"),
							Type.valueOf(e.get("element")),e.getInt("type"),e.getInt("maxPP"),Statut.valueOf(e.get("effet")),e.get("Tstats"),
							e.getInt("ChangeProc"),e.getInt("fof"));
					cpt++;
				}
				//La capacite courante est une AtkRepet
				if(j==2){
					testcap[cpt]=new AtkRepet(e.getInt("power"),e.getInt("pre"),e.getInt("CC"),e.get("nom"),e.get("description"),
							Type.valueOf(e.get("element")),e.getInt("type"),e.getInt("maxPP"),Statut.valueOf(e.get("effet")));
					cpt++;
				}
				//La capacite courante est un Heal
				if(j==3){
					testcap[cpt]=new Heal(e.getInt("power"),e.getInt("pre"),e.getInt("CC"),e.get("nom"),e.get("description"),
							Type.valueOf(e.get("element")),e.getInt("type"),e.getInt("maxPP"),e.getInt("code"));
				cpt++;
				}
			}
		}
		
		for(Capacite c:testcap){
			System.out.println(c);
		}
	}

	
	public static void main(String args[]) throws Exception{
		MyGdxGame.initStatic();
		Combat ctest=new Combat();
		//ctest.combatsolo(MyGdxGame.Jtest,MyGdxGame.Ptest[5]);
		TestCombat.fromXML();
	} 

	
}
