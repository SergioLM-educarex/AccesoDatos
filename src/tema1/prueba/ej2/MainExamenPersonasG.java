package tema1.prueba.ej2;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MainExamenPersonasG {

	private static final int LONGITUD_DNI = 9;
	private static final String TELEFONO_BIN = "telefono.bin";
	private static final String SEPARADOR = ";";
	private static final File CONTACTOS_CSV = new File("contactos.csv");
	private static final File PERSONAS_JSON = new File("personas.json");
	private static final File PERSONAS1_OBJ = new File("personas1G.obj");
	private static final File PERSONAS2_OBJ = new File("personas2G.obj");
	private static final File PERSONAS_OBJ = new File("personas.obj");
	private static final File PERSONAS_XML = new File("personas.xml");

	private static final int OPCION_SALIR = 7;
	private static ListaPersonasG listaPersonasJson = new ListaPersonasG();
	private static ListaPersonasG listaPersonasXml = new ListaPersonasG();
	private static ListaPersonasG listaPersonasFinal = new ListaPersonasG();

	private static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {

		int opcion = 0;
		do {
			mostrarMenu();
			opcion = Integer.parseInt(entrada.nextLine());
			operarMenu(opcion);

		} while (opcion != OPCION_SALIR);

	}

	private static void operarMenu(int opcion) {
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
			ejercicio4();
			break;

		case 5:
			ejercicio5();
			break;

		case 6:
			ejercicio6();
			break;

		case 7:
			System.out.println("Saliendo.....");
			break;
		default:
			System.out.println("Opción no válida, repita la operación");
			break;
		}

	}

	private static void ejercicio6() {

	    System.out.println("Ingrese el dni:");
	    String dniBuscado = entrada.nextLine().toUpperCase().trim();

	    boolean encontrado = false;

	    try (RandomAccessFile raf = new RandomAccessFile(TELEFONO_BIN, "r")) {

	        while (true) {
	            try {
	                // Leer DNI
	                byte[] dniBytes = new byte[LONGITUD_DNI];
	                raf.readFully(dniBytes);
	                String dni = new String(dniBytes).trim();

	                // Leer teléfono
	                int telefono = raf.readInt();

	                // Comprobar si coincide con el buscado
	                if (dniBuscado.equalsIgnoreCase(dni)) {
	                    System.out.println("El DNI es: " + dni);
	                    System.out.println("El teléfono es: " + telefono);
	                    encontrado = true;
	                    // NO usamos break, seguimos leyendo hasta EOF
	                }

	            } catch (EOFException eof) {
	                // Al llegar al final del fichero, se termina el while
	                break;
	            }
	        }

	        if (!encontrado) {
	            System.out.println("DNI no encontrado en el fichero.");
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


	private static void ejercicio5() {

		ObjectInputStream ois1;
		try {
			ois1 = new ObjectInputStream(new FileInputStream(PERSONAS_OBJ));
			ListaPersonasG p1 = new ListaPersonasG();

			p1 = (ListaPersonasG) ois1.readObject();
			ois1.close();

			RandomAccessFile raf = new RandomAccessFile(TELEFONO_BIN, "rw");

			for (PersonaG p : p1.getPersonas()) {
				String dni = p.getDni();

				if (p.getDni().length() < LONGITUD_DNI) {
					p.setDni(dni += " ");
				}

				raf.writeBytes(p.getDni());
				raf.writeInt(p.getTelefono());

			}

			System.out.println("Random Access escrito con dni y numero");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void ejercicio4() {
		try {

			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(CONTACTOS_CSV, true));

			for (PersonaG p : listaPersonasFinal.getPersonas()) {

				bufferedWriter.write(p.getDni().trim() + SEPARADOR + p.getNombre().trim() + SEPARADOR + p.getEdad()
						+ SEPARADOR + p.getTelefono() + SEPARADOR + p.getEmail().trim());

				bufferedWriter.newLine();
			}
			bufferedWriter.close();
			System.out.println("Archivo CSV creado con exito");

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private static void ejercicio3() {

		try {
			ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream(PERSONAS1_OBJ));
			ListaPersonasG p1 = new ListaPersonasG();

			p1 = (ListaPersonasG) ois1.readObject();
			ois1.close();

			ObjectInputStream ois2 = new ObjectInputStream(new FileInputStream(PERSONAS2_OBJ));
			ListaPersonasG p2 = new ListaPersonasG();

			p2 = (ListaPersonasG) ois2.readObject();
			ois2.close();

			for (PersonaG p : p1.getPersonas()) {

				for (PersonaG pp : p2.getPersonas()) {

					if (p.getDni().equals(pp.getDni())) {

						PersonaG pg = new PersonaG(p.getDni(), p.getNombre(), p.getEdad(), pp.getTelefono(),
								pp.getEmail());
						listaPersonasFinal.aniadirPersona(pg);
					}
				}
			}

			listaPersonasFinal.mostrarPersonas();

			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PERSONAS_OBJ, true));
			oos.writeObject(listaPersonasFinal);
			oos.close();
			System.out.println("Archivo ObjectOutputStream escrito");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void ejercicio2() {

		try {
			JAXBContext contexto = JAXBContext.newInstance(ListaPersonasG.class);
			Unmarshaller unmarshaller = contexto.createUnmarshaller();
			listaPersonasXml = (ListaPersonasG) unmarshaller.unmarshal(PERSONAS_XML);

			listaPersonasXml.mostrarPersonas();

			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PERSONAS2_OBJ, true));
			oos.writeObject(listaPersonasXml);
			oos.close();
			System.out.println("Archivo ObjectOutputStream escrito con Ã©xito");

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void ejercicio1() {

		ObjectMapper mapper = new ObjectMapper();

		try {
			listaPersonasJson = mapper.readValue(PERSONAS_JSON, ListaPersonasG.class);

			listaPersonasJson.mostrarPersonas();
			System.out.println("Archivo Json leido correctamente");

			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PERSONAS1_OBJ, false));
			oos.writeObject(listaPersonasJson);
			oos.close();
			System.out.println("Archivo ObjectOutputStream escrito");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

	}

	private static void mostrarMenu() {
		System.out.println("-----------------");
		System.out.println("Menu: ");
		System.out.println("Ejercicio 1");
		System.out.println("Ejercicio 2");
		System.out.println("Ejercicio 3");
		System.out.println("Ejercicio 4");
		System.out.println("Ejercicio 5");
		System.out.println("Ejercicio 6");
		System.out.println("Elige opcion: ");

	}

}
