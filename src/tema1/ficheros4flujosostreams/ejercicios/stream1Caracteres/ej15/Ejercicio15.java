package tema1.ficheros4flujosostreams.ejercicios.stream1Caracteres.ej15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio15 {

	private static final String TXT = ".txt";
	private static final String SEPARADOR = ";";

	public static void main(String[] args) {
		
		
		
		/*
		 * Realiza un programa que lea y muestre un fichero de texto que contiene el nombre, los
		apellidos y número de teléfono de personas, separados por el carácter ;.También, deberá
		mostrar el número de personas que hay en el fichero. 
		El nombre del fichero se pedirá por teclado.
		 */
		Scanner entrada = new Scanner(System.in);
		String nombre_fichero="";
		
		
		System.out.println("Introduce el nombre del fichero");
		nombre_fichero = entrada.nextLine()+TXT;
		
		
		
		leerAgenda(nombre_fichero);
		
		
		
		
		
	}

	private static void leerAgenda(String nombre_fichero) {
		int contador_personas=0;
		String linea, nombre,apellidos,telefono;
		String [] datosTroc;
		
		
		
		try (BufferedReader lector = new BufferedReader(new FileReader(nombre_fichero))) {
			
			System.out.println("=== CONTACTOS EN LA AGENDA ===");
			System.out.println("------------------------------");
			
		while ((linea=lector.readLine())!=null) {
			contador_personas++;
			datosTroc= linea.split(SEPARADOR);
			
			if (datosTroc.length==3) {
				
				nombre=datosTroc[0];
				apellidos=datosTroc[1];
				telefono=datosTroc[2];
				
				System.out.println("Persona Numero: "+contador_personas);
				System.out.println("Nombre: "+nombre);
				System.out.println("Apellidos: "+apellidos);
				System.out.println("Num Telefono: "+telefono);
				
				System.out.println("-------------------------");
				
			}else {
				System.err.println("Error en el número de campos"+ linea);
			}
			
			
		}
			System.out.println("TOTAL DE PERSONAS = "+ contador_personas);
			
			
		} catch (IOException e) {
			System.out.println("Error: No se pudo encontrar o leer el fichero '" + nombre_fichero + "'");
	        System.out.println("Asegúrate de que:");
	        System.out.println("- El fichero existe en la ruta correcta");
	        System.out.println("- El nombre del fichero es correcto (incluye .txt)");
	        System.out.println("- El fichero está en el mismo directorio que el programa");
		}
		
	}
}
