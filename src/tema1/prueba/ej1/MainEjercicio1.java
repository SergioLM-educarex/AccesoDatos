package tema1.prueba.ej1;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.bind.v2.runtime.reflect.ListTransducedAccessorImpl;

import tema1.prueba.Persona;
import tema1.prueba.Personas;

public class MainEjercicio1 {

	private static ArrayList<Persona> lista_personas_1 = new ArrayList<Persona>();
	private static ArrayList<Persona> lista_personas_2 = new ArrayList<Persona>();

	private static Personas lista_personasfusion = new Personas();

	private static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException, Exception {

		int opcion = 0;

		do {

			mostrarmenu();

			opcion = Integer.parseInt(entrada.nextLine());
			operarMenu(opcion);

		} while (opcion != 4);

	}

	private static void mostrarmenu() {

		System.out.println("Ejercicio 1");
		System.out.println("Ejercicio 2");
		System.out.println("Ejercicio 3");
		System.out.println("Ejercicio 4");
		System.out.println("Ejercicio 5");
		System.out.println("Ejercicio 6");
		System.out.println("\tElige opciona");
	}

	private static void operarMenu(int opcion) throws FileNotFoundException, Exception {

		switch (opcion) {
		case 1:
			ejercicio1();
			break;

		case 2:
			ejercicio2();
			break;

		case 3:
			ejercicio3();
			break;

		case 4:

			break;
		case 5:

			break;

		case 6:

			break;

		default:
			System.out.println("opción no válida");
			break;
		}

	}

	private static void ejercicio3() throws FileNotFoundException, IOException {
		System.out.println(lista_personas_1.toString());
		System.out.println(lista_personas_2.toString());

		for (int i = 0; i < lista_personas_1.size(); i++) {
			if (lista_personas_1.get(i).getDni().equals(lista_personas_2.get(i).getDni())) {

				Persona persona = new Persona(lista_personas_1.get(i).getDni(), lista_personas_1.get(i).getNombre(),
						lista_personas_2.get(i).getTelefono(), lista_personas_2.get(i).getTelefono(),
						lista_personas_1.get(i).getEmail());

				lista_personasfusion.aniadirPersona(persona);

				File fichero = new File("personasFusionado.bin");
				ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(fichero, false));
				ois.writeObject(lista_personasfusion);
				ois.close();
			

			}

		}

	}

	private static void ejercicio1() {
		ObjectMapper mapper = new ObjectMapper();

		try {
			Personas personas = mapper.readValue(new File("personas.json"), Personas.class);

			lista_personas_1 = personas.getListaPersonas();

			System.out.println("Json Leido correctamente");

			//mapper.writeValue(System.out, personas);
			
			mostrarmenu();

			File fichero = new File("personas1.bin");
			ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(fichero, false));
			ois.writeObject(lista_personasfusion);
			ois.close();

		} catch (EOFException e) {
			e.printStackTrace();

		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void ejercicio2() {
	
			
			try {
				JAXBContext context = JAXBContext.newInstance(Personas.class);
				Unmarshaller unmarshaller = context.createUnmarshaller();
				File xmlFile = new File("personas.xml");
				Personas personas;
				personas = (Personas) unmarshaller.unmarshal(xmlFile);
				lista_personas_2 = personas.getListaPersonas();
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			

			for (Persona p : lista_personas_2) {
				System.out.println("DNI: " + p.getDni());
				System.out.println("Teléfono: " + p.getTelefono());
				System.out.println("Email: " + p.getEmail());
				System.out.println("---------");

			}
			
			File fichero = new File("personas2.bin");
			ObjectOutputStream ois;
			try {
				ois = new ObjectOutputStream(new FileOutputStream(fichero, false));
				ois.writeObject(lista_personasfusion);
				ois.close();
			
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}

}
