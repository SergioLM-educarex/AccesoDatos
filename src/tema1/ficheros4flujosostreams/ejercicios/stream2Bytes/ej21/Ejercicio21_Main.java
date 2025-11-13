package tema1.ficheros4flujosostreams.ejercicios.stream2Bytes.ej21;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio21_Main {

	private static final int MAXIMO = 126;
	private static final int MINIMO = 32;

	public static void main(String[] args) {

		/*
		 * Crea un programa que genere un fichero binario que almacene un número entero
		 * entre el 32 y el 126. Se pedirá al usuario la ruta del fichero y el número
		 * que desea almacenar. Si el fichero existe se sobrescribirá. Visualiza el
		 * fichero creado. Compara el resultado con la tabla ASCII.
		 */

		Scanner entrada = new Scanner(System.in);
		String nomFichero;

		int numero = 0;

		System.out.println("Escriba el nombre del fichero .bin");
		nomFichero = entrada.nextLine();

		File archivo = new File(nomFichero);

		if (!archivo.exists()) {
			try {

				numero = pedirNumero(entrada);

				FileOutputStream stream = new FileOutputStream(archivo);
				stream.write(numero);

				stream.close();

				FileInputStream inputStream = new FileInputStream(archivo);
				int valor = inputStream.read();
				inputStream.close();

				System.out.println(valor);
				System.out.println((char) valor);

			} catch (IOException e) {
				System.out.println("No se pudo crear el archivo");
				e.printStackTrace();
			}

		} else {

			numero = pedirNumero(entrada);
			FileOutputStream stream;
			try {
				stream = new FileOutputStream(archivo);

				stream.write(numero);

				stream.close();

				FileInputStream inputStream = new FileInputStream(archivo);
				int valor = inputStream.read();
				inputStream.close();

				System.out.println("Numero escrito: "+valor);
				System.out.println("Numero en valor ASCII: "+(char) valor);

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private static int pedirNumero(Scanner entrada) {
		int numero;
		do {
			System.out.println("Escriba un numero");
			numero = Integer.parseInt(entrada.nextLine());

			if (numero < MINIMO || numero > MAXIMO) {
				System.out.println("Numero no valido");
			}

		} while (numero < MINIMO || numero > MAXIMO);
		return numero;
	}

}
