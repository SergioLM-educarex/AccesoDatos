package tema1.ficheros6formatoIntercambio.jaxb.ejercicios.ejercicio42;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Main42 {

	private static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {

		Libreria libreria = null;

		libreria = crearLibreria();

		int opcion = 0;
		
		do {
			aniadirLibro(libreria);
			
			System.out.println("Continuar: 1-SI 2-NO");
			opcion = Integer.parseInt(entrada.nextLine());
			
			if (opcion!=1 && opcion!=2) {
				System.out.println("Opcion no válida. Repita la operación");
			}
			
		} while (opcion!=2);
		
		
		
		
		try {
			
			//Crear el contexto 
			JAXBContext context = JAXBContext.newInstance(Libreria.class);
			Marshaller marshaller = context.createMarshaller();
			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			marshaller.marshal(libreria, System.out);
			marshaller.marshal(libreria, new File("Libreria.xml"));
			
			
			System.out.println("Xml creado con éxito");
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	private static void aniadirLibro(Libreria libreria) {

		String titulo, autor, editorial, isbn;
		Libro libro = null;
		
		

		System.out.println("Ingrese el titulo del libro");
		titulo = entrada.nextLine();

		System.out.println("Ingrese el autor del libro");
		autor = entrada.nextLine();
		System.out.println("Ingrese la editoria del libro");
		editorial = entrada.nextLine();

		System.out.println("Ingrese el ISBN del libro");
		isbn = entrada.nextLine();
		
		
		
		
		
		libro = new Libro(titulo, autor, editorial, isbn);
		
		libreria.aniadirLibro(libro);
		

	}

	private static Libreria crearLibreria() {

		String nombreLibreria, lugar;
		int cod_postal = 0;
		ArrayList<Libro> listaLibros = new ArrayList<Libro>();

		System.out.println("------ CREAR LA LIBRERIA--------");
		System.out.println("Ingrese el nombre de la libreria");
		nombreLibreria = entrada.nextLine();

		System.out.println("Ingrese el lugar de la libreria");
		lugar = entrada.nextLine();

		try {
			System.out.println("Codigo postal:");
			cod_postal = Integer.parseInt(entrada.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Ingrese un numero");
		}

		Libreria libreria = new Libreria(nombreLibreria, lugar, cod_postal, listaLibros);

		System.out.println("Libreria creada con éxito");

		return libreria;

	}

}
