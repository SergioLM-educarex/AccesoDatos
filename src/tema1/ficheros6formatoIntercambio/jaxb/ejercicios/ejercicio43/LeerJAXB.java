package tema1.ficheros6formatoIntercambio.jaxb.ejercicios.ejercicio43;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import tema1.ficheros6formatoIntercambio.jaxb.ejercicios.ejercicio42.Libreria;

public class LeerJAXB {

	public static void main(String[] args) {

		try {
			// Instanciar un contexto de la clase JAXBContext con la clase RootElement
			JAXBContext contexto = JAXBContext.newInstance(Libreria.class);
			// Crear un unmarshaller que convierte el XML en JavaBeans.
			Unmarshaller um = contexto.createUnmarshaller();
			// Realiza la deserializaci�n llamando al m�todo unmarshal del marshaller.
			Libreria laLibreria = (Libreria) um.unmarshal(new File("Libreria.xml"));
			
		laLibreria.mostrarDatosLibreria();
		
		
		} catch (JAXBException e) {
			e.printStackTrace();

		}
	}
}
