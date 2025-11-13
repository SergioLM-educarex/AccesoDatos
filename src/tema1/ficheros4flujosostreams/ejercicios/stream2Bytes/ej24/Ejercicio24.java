package tema1.ficheros4flujosostreams.ejercicios.stream2Bytes.ej24;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio24 {

	private static final char SEPARADOR = '\n';

	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);
		String nomFichero;
		int expediente;
		String nombre_alumno;
		double nota;

		System.out.println("Inserte la ruta del fichero:");
		nomFichero = entrada.nextLine();

		File fichero = new File(nomFichero);

		try {
			DataInputStream dataInputStream = new DataInputStream(new FileInputStream(fichero));

			// Este bucle recorre el archivo hasta que salte la exception EOFException que
			// manejara el expeption no permitiendo el error
			while (true) {

				// Conversión del archivo
				// Saber como vienen los datos, no pueden venir en otro orden porque no lo lee.
				expediente = dataInputStream.readInt();
				nota = dataInputStream.readDouble();
				nombre_alumno = "";
				char c;

				// Este siguiente bucle concatena un char a otro hasta que llega '\n'

				while ((c = dataInputStream.readChar()) != SEPARADOR) {
					nombre_alumno += c;
				}

				System.out.println("Expediente:" + expediente + "\tNota:" + nota + "\tNombre:" + nombre_alumno);
			}

		} catch (EOFException e) {
			// Salto la excepcion para ver y comprobar que entra en la excepcion
			System.out.println("Salta la excepción\n Terminado");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
