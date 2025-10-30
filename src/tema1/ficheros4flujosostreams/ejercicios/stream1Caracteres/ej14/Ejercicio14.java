package tema1.ficheros4flujosostreams.ejercicios.stream1Caracteres.ej14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio14 {

	public static void main(String[] args) {

		// 14. Modifica el ejercicio 13 para leer por línea.

		Scanner entrada = new Scanner(System.in);

		String ruta = "C:\\Users\\sergi\\Desktop\\DAW\\Acceso a Datos\\MostrarFichero.txt";

		String linea = "";

		// Abrimos y leemos el fichero

		try (BufferedReader lector = new BufferedReader(new FileReader(ruta))) {

			/*
			 * PARENTESIS 
			 * // Razón: Los paréntesis fuerzan el orden de evaluación: 
			 *
			 * Primero: (linea = lector.readLine()) //
			 * Después: comparar el resultado con  != null
			 */

			while ((linea = lector.readLine()) != null) {

				System.out.println(linea);

			}

		} catch (IOException e) {
			// Si hay error al abrir o leer
			System.out.print("Error al leer el fichero: " + e.getMessage());
		}

	}

}
