package tema1.ficheros4flujosostreams.ejercicios.stream1Caracteres.ej19;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio19 {

	private static final String SEPARADOR = ";";

	public static void main(String[] args) {
		
		Scanner entrada = new Scanner(System.in);
		String nombreFichero = "ej19_cotizacion.txt";
		String linea = "";
		int contador_linea=1;
		
		String nombreEmpresa="";
		String precioAccion="";
		
		File fichero = new File(nombreFichero);
		
		if (fichero.isFile() && fichero.exists()) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(fichero));
				BufferedWriter writer = new BufferedWriter(new FileWriter("fichero_nuevo.txt",true));
				String lineaCompleta="";
				
				while ((linea=reader.readLine())!=null) {
					
					// Si el número de línea módulo 5 es igual a 2, es la línea del nombre de empresa
					if (contador_linea%5==2) {
						nombreEmpresa=linea;
						writer.write(nombreEmpresa);
					}
					
					
					// Si el número de línea módulo 5 es igual a 3, es la línea del precio de acción
					if (contador_linea%5==3) {
						precioAccion=linea;
						writer.write(SEPARADOR+precioAccion);
						writer.newLine();
					}
		
					
					
					contador_linea++;
					
				}
				writer.close();
				System.out.println("Fichero "+writer.toString()+" escrito exitosamente");
				
				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("El archivo no es un fichero o no existe.");
		}
		
		
		
		
	}
}
