package tema1.ficheros4flujosostreams.ejercicios.stream1Caracteres.ej13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio13 {

	public static void main(String[] args) {

		/*
		 * 13. Muestra en pantalla el contenido de un fichero de texto cuya ruta se pasa
		 * por consola. Leeremos por carácter.
		 */
		Scanner entrada = new Scanner(System.in);

		String ruta = "C:\\Users\\sergi\\Desktop\\DAW\\Acceso a Datos\\MostrarFichero.txt";
		int caracter; // Guardamos cada carácter leído

		// Abrimos y leemos el fichero

		try (BufferedReader lector = new BufferedReader(new FileReader(ruta))) {

			// Leer carácter a carácter hasta el final
			while ((caracter = lector.read()) != -1) {
				System.out.print((char) caracter);

			}

		} catch (IOException e) {
			// Si hay error al abrir o leer
			System.out.print("Error al leer el fichero: " + e.getMessage());
		}
	}

}
