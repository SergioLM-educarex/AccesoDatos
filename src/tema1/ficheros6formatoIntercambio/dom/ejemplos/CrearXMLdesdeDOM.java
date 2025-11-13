package tema1.ficheros6formatoIntercambio.dom.ejemplos;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;



public class CrearXMLdesdeDOM {

	public static void main(String[] args) {
		
		try {
		//Creamos la factoria
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		//Cremos el Builder
		
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			
			//Creamos el DOM vacío
			Document document = builder.newDocument();
			document.setXmlVersion("1.0");
			
			//Creamos elementos y añadimos al DOM
			Element elemento= document.createElement("Raiz");
			Element ele1 = document.createElement("hoja");
			Element ele2 = document.createElement("rama");
			
			//Añadimos el elemento raiz al documento
			document.appendChild(elemento);
			
			//Añadir un nodo elemento como hijo de otro elemento
			elemento.appendChild(ele1);
			elemento.appendChild(ele2);
			
			//Añadir atributos al elemento
			ele1.setAttribute("nombre", "valor");
			ele1.setTextContent("Texto del elemento - Tipo Texto");
			
			ele2.setAttribute("nombre", "sergio");
			ele2.setAttribute("apellidos", "Leon");
			
			//-----------------
			
			//1. Crear una fuente/origen con el arbol DOM
			DOMSource fuente = new DOMSource(document);
			
			//2. Crear el destino de la transformación
			StreamResult ficheroXML = new StreamResult(new File("FicheroXMLcreadoDeDOM.xml"));
			
			//2.1 Si quiero mostrar por consola
			StreamResult consola = new StreamResult (System.out);
			
			//3.Crear un Transformador
			Transformer t = TransformerFactory.newInstance().newTransformer();
			
			//4.Mejoramos el forma
			t.setOutputProperty(OutputKeys.INDENT, "yes");
		//	t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			//5.Transformar la  fuente en el resultado
			t.transform(fuente, ficheroXML);
			 t.transform(fuente, consola);
			
		} catch (ParserConfigurationException | TransformerConfigurationException | TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Hecho");

	}

}
