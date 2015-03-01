package pokemon.launcher;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;







import pokemon.modele.*;


public class TestCombat {
	
	//Test de parse XML
	public static void fromXML() throws Exception{

		Capacite[] testcap=new Capacite[16]; Element e; int cpt=0;
		XmlReader reader = new XmlReader();
		Element root = reader.parse(Gdx.files.internal("xml/Capacite.xml"));
		Array<Element> elements= root.getChildrenByName("Atk");
		for(Element temp:elements){
			for(int i=0;i<temp.getChildCount();i++){
			e=temp.getChild(i);System.out.println("temp"+temp);
			testcap[cpt]=new Atk(e.getInt("power"),e.getInt("pre"),e.getInt("CC"),e.get("nom"),e.get("description"),
							   Type.valueOf(e.get("element")),e.getInt("type"),e.getInt("maxPP"),Statut.valueOf(e.get("effet"))
							  );
			cpt++;
			}
		}
	}
	public static void main(String args[]) throws Exception{
		MyGdxGame.initStatic();
		Combat ctest=new Combat();
		//ctest.combatsolo(MyGdxGame.Jtest,MyGdxGame.Ptest[5]);
		TestCombat.fromXML();
	} 

	
}
