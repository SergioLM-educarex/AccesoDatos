package tema1.ficheros4flujosostreams.ejercicios.stream1Caracteres.ej12;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio12 {

	private static final String TXT = ".txt";
	private static final String NO = "N";
	private static final String SEPARADOR = ";";

	public static void main(String[] args) {
		/*
		 * 12. Realiza un programa que añada información a un fichero como el que se
		 * muestra en la imagen. El programa deberá pedir el nombre de fichero y los
		 * datos de varias personas. Si el fichero no existe, se creará. Si existe se
		 * añadirán registros.
		 */

		Scanner scanner = new Scanner(System.in);
		String nombreFichero = "";
		boolean continuar = true;
		String nombre, edad, ciudad, respuesta;

		System.out.println("Introduce el nombre del fichero");
		nombreFichero = scanner.nextLine().formatted(TXT);

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreFichero, true))) { // true = añade al final - NO SOBREESCRIBE
			while (continuar) {
				// Pedir los datos de la persona
				System.out.println("Nombre: ");
				nombre = scanner.nextLine();

				System.out.println("Edad: ");
				edad = scanner.nextLine();

				System.out.println("Ciudad: ");
				ciudad = scanner.nextLine();

				// Escribir los datos en el fichero separados por comas
				bw.write(nombre + SEPARADOR + edad + SEPARADOR + ciudad);
				bw.newLine(); // salto de línea
				

				// Preguntar si quiere añadir otra persona
				System.out.print("¿Deseas añadir otra persona? (s/n): ");
				respuesta = scanner.nextLine();
				if (respuesta.equalsIgnoreCase(NO)) {
					continuar = false;
				}
			}

			System.out.println("Datos añadidos correctamente al fichero " + nombreFichero);
			bw.close();

		} catch (IOException e) {
			System.out.println("Error al escribir en el fichero: " + e.getMessage());
		}

		scanner.close();

	}
}
