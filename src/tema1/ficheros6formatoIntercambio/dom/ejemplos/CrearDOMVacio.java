package tema1.ficheros6formatoIntercambio.dom.ejemplos;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

public class CrearDOMVacio {

	public static void main(String[] args) {

		try {
			// Creamos la factoría
			DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
			// Creamos el DocumentBuilder
			DocumentBuilder db = null;

			db = factoria.newDocumentBuilder();

			// Creamos un DOM vacío
			Document documento = db.newDocument();

			// (Opcional) Establecer versión XML
			documento.setXmlVersion("1.0");
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
