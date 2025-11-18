package tema1.pruebadura;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.Scanner;

public class GestionBiblioteca {

	private static final File LIBROS_DAT = new File("libros.dat");
	private static final int OPCION_SALIR = 4;

	private static final int TAMANIOTITULO = 40;
	private static final int TAMANIOAUTOR = 30;
	private static Scanner entrada = new Scanner(System.in);
	private static Libros libros = new Libros();

	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);
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
			anadirLibro();
			break;
		case 2:
			listarLibros();
			break;

		case 3:
			buscarPorId();
			break;

		case 4:

			break;

		case 5:

			break;

		case 6:

			break;

		default:
			break;
		}

	}

	private static void buscarPorId() {

		int idBuscado = 0;
		boolean valido = false;

		while (!valido) {
			try {
				System.out.println("Inserte el id:");
				idBuscado = Integer.parseInt(entrada.nextLine().trim());
				valido = true; // si parsea correctamente, salimos del bucle
			} catch (NumberFormatException e) {
				System.err.println("Formato no válido. Inténtelo de nuevo.");
			}
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(LIBROS_DAT))) {
			Libros librosArchivo = (Libros) ois.readObject();

			boolean encontrado = false;

			for (Libro l : librosArchivo.getLibros()) {
				if (l.getIdLibro() == idBuscado) {
					System.out.println("Titulo: " + l.getTitulo());
					System.out.println("Autor: " + l.getAutor());
					System.out.println("Año: " + l.getAnio());
					System.out.println("Precio: " + l.getPrecioLibro());
					encontrado = true;

				}
			}

			if (!encontrado) {
				System.out.println("Libro con ID " + idBuscado + " no encontrado.");
			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void listarLibros() {

		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(LIBROS_DAT));

			Libros libros = new Libros();

			libros = (Libros) ois.readObject();

			libros.mostrarLibros();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void anadirLibro() {
		int id = 1;
		int anio = 0;
		double precio = 0;
		int op = 0;

		System.out.println("Ingrese el titulo del libro");
		String titulo = entrada.nextLine().trim();

		if (titulo.length() < TAMANIOTITULO) {

			for (int i = titulo.length(); i < TAMANIOTITULO; i++) {
				titulo += " ";
			}

		}

		System.out.println("Ingrese el autor:");
		String autor = entrada.nextLine().trim();
		if (autor.length() < TAMANIOAUTOR) {

			for (int i = autor.length(); i < TAMANIOAUTOR; i++) {
				autor += " ";
			}

		}

		try {

			System.out.println("Ingrese el año");
			anio = Integer.parseInt(entrada.nextLine().trim());
			System.out.println("Ingrese el precio");
			precio = Double.parseDouble(entrada.nextLine().trim());

		} catch (NumberFormatException e) {
			System.out.println("Ingrese un numero");
		}

		id = libros.obtenerSiguienteId();

		Libro l = new Libro(id, titulo, autor, anio, precio);

		libros.aniadirLibro(l);

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(LIBROS_DAT));

			oos.writeObject(libros);

			oos.close();
			System.out.println("Fichero " + LIBROS_DAT.toString() + " escrito correctamente");
			System.out.println("---------------------");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void mostrarMenu() {

		System.out.println("Menu: ");
		System.out.println("1. Añadir libro nuevo al fichero");
		System.out.println("2. Listar todos los libros leidos secuencialmente");
		System.out.println("3. Buscar un libro por ID recorriendo el fichero completo");
		System.out.println("Ejercicio 1");
		System.out.println("Ejercicio 1");
		System.out.println("Ejercicio 1");
		System.out.println("Elige opcion: ");

	}

}
