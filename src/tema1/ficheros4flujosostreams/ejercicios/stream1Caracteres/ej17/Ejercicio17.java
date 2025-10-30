package tema1.ficheros4flujosostreams.ejercicios.stream1Caracteres.ej17;

import java.io.*;
import java.util.Scanner;

public class Ejercicio17 {

	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);
		String nombreFichero, nombre, apellidos, telefono, lineaNueva;

		// Pregunto el nombre del fichero
		System.out.print("Indique cual es el fichero: ");
		nombreFichero = entrada.nextLine();

		// Crear el fichero, ¡¡¡IMPORTANTE ESTE PASO!!!
		File fichero = new File(nombreFichero);

		// Preguntar si es una linea
		if (fichero.isFile()) {

			// mostrarFichero() devuelve un int al leer el archivo se crea un contador
			// incrementado por linea leida
			int totalLineas = mostrarFichero(fichero);
			int numModificar = 0;

			do {

				System.out.print("Introduce el numero de linea que deseas modificar (1 - " + totalLineas + "): ");
				try {

					numModificar = Integer.parseInt(entrada.nextLine());

				} catch (NumberFormatException e) {
					System.out.println("Por favor, introduce un número válido.");
				}
			} while (numModificar < 1 || numModificar > totalLineas);

			System.out.println("Introduce el nuevo contenido para la línea " + numModificar + ": ");

			// Pedir datos
			System.out.println("Nombre");
			nombre = entrada.nextLine();

			System.out.println("Apellidos");
			apellidos = entrada.nextLine();

			System.out.println("Telefono");
			telefono = entrada.nextLine();

			lineaNueva = nombre + ";" + apellidos + ";"+ telefono;

			modificarFichero(fichero, numModificar, lineaNueva);

			System.out.println("\nFichero modificado:");
			mostrarFichero(fichero);

		} else {
			System.out.println("No es un fichero válido.");
		}

		entrada.close();
	}

	/**
	 * Modifica la línea pedida por Scanner en el fichero, creando un temporal y
	 * reemplazando la línea.
	 */
	private static void modificarFichero(File fichero, int numModificar, String nuevaLinea) {

		int lineaActual = 1;
		String linea;
		File temporal = new File("temporal.txt");

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fichero));
				BufferedWriter bfTemporal = new BufferedWriter(new FileWriter(temporal))) {

			while ((linea = bufferedReader.readLine()) != null) {
				// Si es la línea a modificar, escribe la nueva
				if (lineaActual == numModificar) {
					bfTemporal.write(nuevaLinea);
				} else {
					bfTemporal.write(linea);
				}
				bfTemporal.newLine();
				lineaActual++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Sustituir el fichero original por el temporal
		if (fichero.delete()) {
			if (!temporal.renameTo(fichero)) {
				System.out.println("Error al renombrar el fichero temporal.");
			}
		} else {
			System.out.println("Error al eliminar el fichero original.");
		}
	}

	/**
	 * Muestra el contenido del fichero con numeración de líneas.
	 */
	private static int mostrarFichero(File fichero) {
		String linea;
		int numLinea = 1;

		try (BufferedReader breader = new BufferedReader(new FileReader(fichero))) {
			while ((linea = breader.readLine()) != null) {
				System.out.println(numLinea + ": " + linea);
				numLinea++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return numLinea - 1;
	}
}
