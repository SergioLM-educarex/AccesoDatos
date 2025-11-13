package tema1.ficheros6formatoIntercambio.dom.ejemplos;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LeerXML {

	public static void main(String[] args) {
		try {
	//Crear un nuevo objeto DOCUMENTBUILDERFACTORY
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	
	//Crear un DocumentBuilder
	
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		//Cargar el archivo XML
		File archivoXML = new File("Personas.xml");
		Document documento = builder.parse(archivoXML);
		
		//NOmralizar el documento para elminar espacios vacios
		documento.getDocumentElement().normalize();
		
		
		//Ontener el elemenot razi
		Element raiz = documento.getDocumentElement();
		//Mostrar los nodos recursivamente
		recorrerRamaDom(raiz,0);
		
		
		
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//Cargar el archivo
		
		
	}

	private static void recorrerRamaDom(Node raiz, int i) {
		
		if (raiz!=null && raiz.getNodeType()== Node.ELEMENT_NODE) { //solo los nodos tipo ELEMENT_MODE
			
			//Crear indentación en función del nivel del nodo
			String indentacion = " ".repeat(i*4); //Cada nivel añade 4 espacios
			
			//Mostrar el nombre del nodo con indentación 
			System.out.println(indentacion+" Nombre: "+raiz.getNodeName());
			
			//Obtener los hijos del nodo
			NodeList hijos = raiz.getChildNodes();
			
			for (int j = 0; j < hijos.getLength(); j++) {
				Node nodoNieto = hijos.item(j);
				recorrerRamaDom(nodoNieto, i+1); //llamada recursiva, y + nivel
			}
			
		}
		
	}
	
	

}
