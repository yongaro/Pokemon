package pokemon.launcher;


import java.io.FileInputStream;
import java.io.InputStream;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

import pokemon.modele.*;


public class TestCombat {
	
	public static void main(String args[]){
		MyGdxGame.initStatic();
		Combat ctest=new Combat();
		//ctest.combatsolo(MyGdxGame.Jtest,MyGdxGame.Ptest[5]);
		TestCombat.fromXML();
	} 
	
	
	public static void fromXML(){
		Capacite[] testcap=new Capacite[16]; int i=0; Element temp;
		XmlReader reader = new XmlReader();
		InputStream inputstream;
		FileHandle test=Gdx.files.internal("xml/Capacite.xml");

		try {
			inputstream = new FileInputStream("C:/Users/User/git/Pokemon/Pokemon-android/assets/xml/Capacite.xml");
			System.out.println("ICI"); 
			Element root = reader.parse(Gdx.files.local("assets/xml/Capacite.xml"));
			System.out.println("ICI "+root);
			Array<Element> elements= root.getChildrenByName("Atk");
			System.out.println("ICI");
			for(Element e:elements){
				System.out.println("ICI");
				temp=e.getChild(0);System.out.println(temp);
				testcap[i]=new Atk(e.getInt("power"),e.getInt("pre"),e.getInt("CC"),e.get("nom"),e.get("description"),
								   Type.valueOf(e.get("element")),e.getInt("type"),e.getInt("maxPP"),Statut.valueOf(e.get("effet"))
								  );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(Capacite c:testcap){
			System.out.println(c);
		}
	}
	

	
}
