package tema1.ficheros4flujosostreams.ejercicios.stream1Caracteres.ej11;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio11 {

	private static final String TXT = ".txt";

	public static void main(String[] args) {
		/*
		 * 11. Realiza un programa que cree un fichero y añada un texto. El programa pedirá el nombre
			del fichero y el texto. 
		 */
		
		Scanner entrada = new Scanner(System.in);
		String nombreFichero = "", texto = "";
	
		
		//Lugar para pedir el nombre del fichero
		System.out.println("Ingrese el nombre del fichero");
		nombreFichero= entrada.nextLine().formatted(TXT);
		
		//Pedir el texto del nombreFichero
		
		System.out.println("Que texto quieres añadir: ");
		texto=entrada.nextLine();
		
		//Creamos fichero y añadimos texto

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(nombreFichero,true)); // el true, añade no borra
			
			bw.write(texto); 
			bw.newLine();
			bw.close();
			
			System.out.println("Texto añadido correctamente");
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}
