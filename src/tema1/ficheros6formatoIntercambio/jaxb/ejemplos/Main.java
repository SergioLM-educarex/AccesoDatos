package tema1.ficheros6formatoIntercambio.jaxb.ejemplos;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Main {

	public static void main(String[] args) {

		Personas ListaPersonas = new Personas();// ArrayList de persona
		ListaPersonas.aniadirPersona(new Persona("123123", "Paco", 45));
		ListaPersonas.aniadirPersona(new Persona("666666", "Luis", 33));
		ListaPersonas.mostrarPersonas();
		System.out.println("...................");
		try {
			// Crear contexto JAXB
			JAXBContext jaxbContext = JAXBContext.newInstance(Personas.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			// Configuración opcional para formato legible
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			// Convertir objeto a XML y mostrar en consola
			marshaller.marshal(ListaPersonas, System.out);
			marshaller.marshal(ListaPersonas, new File("salidaJaxb.xml"));
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

}
