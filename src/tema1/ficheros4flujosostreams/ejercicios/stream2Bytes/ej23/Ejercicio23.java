package tema1.ficheros4flujosostreams.ejercicios.stream2Bytes.ej23;

import java.io.DataOutputStream;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Scanner;

public class Ejercicio23 {

	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);
		String nomFichero;

		System.out.println("Escriba el nombre del fichero .bin");
		nomFichero = entrada.nextLine();

		File archivo = new File(nomFichero);

		if (!archivo.exists()) {
			try {
				archivo.createNewFile();
				System.out.println("Fichero creado correctamente");
				introducir_Datos(entrada, archivo);

			} catch (IOException e) {
				System.out.println("No se pudo crear el archivo");
				e.printStackTrace();
			}

		} else {
			// se crea

			introducir_Datos(entrada, archivo);

		}

	}

	private static void introducir_Datos(Scanner entrada, File archivo) {
		int expediente;
		double nota;
		String nombre_alumno;
		System.out.println("Indique el numero de expediente:");
		expediente = Integer.parseInt(entrada.nextLine());

		System.out.println("Indique la nota del alumno");
		nota = Double.parseDouble(entrada.nextLine());

		System.out.println("Indique el nombre del alumno");
		nombre_alumno = entrada.nextLine();

		try {
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(archivo, true));

			dos.writeInt(expediente);
			dos.writeDouble(nota);
			dos.writeChars(nombre_alumno + "\n");

			System.out.println("Archivo escrito correctamente");

			dos.close();
		} catch (IOException e) {
			System.out.println("No se ha podido escribir");
			e.printStackTrace();
		}

	}

}
