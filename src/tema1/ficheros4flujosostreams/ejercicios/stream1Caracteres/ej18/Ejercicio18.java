package tema1.ficheros4flujosostreams.ejercicios.stream1Caracteres.ej18;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio18 {

	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);
		String nombreFichero = "";
		String linea = "";

		System.out.println("Nombre del archivo");
		nombreFichero = entrada.nextLine();

		File fichero = new File(nombreFichero);

		try {

			BufferedReader reader = new BufferedReader(new FileReader(fichero));
			BufferedWriter writer = new BufferedWriter(new FileWriter("agenda_contacto.txt", true));

			if (fichero.isFile() && fichero.exists()) {

				while ((linea = reader.readLine()) != null) {

					writer.write(linea.toUpperCase());
					writer.newLine();

				}
				writer.close();
				System.out.println("Terminado!!!");

			} else {
				System.out.println("El archivo no es un fichero!!");
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
