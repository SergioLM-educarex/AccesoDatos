package tema1.ficheros6formatoIntercambio.ejercicios;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Ejercicio37 {

	private static final int NO = 2;
	private static final int SI = 1;
	private static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {

		boolean continuar = true;
		String nombre = "";
		double nota;
		int numExpe, num = 1;
		Set<Alumno> conjuntoAlumnos = new TreeSet<Alumno>();

		while (num == SI) {

			System.out.println("----- PROGRAMA PARA AÑADIR NOTAS EXPEDIENTE ----");
			try {
				System.out.println("Inserte el numero de Expediente");
				numExpe = Integer.parseInt(entrada.nextLine());

				System.out.println("Inserte el nombre");
				nombre = entrada.nextLine();

				System.out.println("Inserte la nota");
				nota = Double.parseDouble(entrada.nextLine());

				System.out.println("¿Quieres continuar? 1-SI - 2.NO");
				num = Integer.parseInt(entrada.nextLine());

				Alumno alumno = new Alumno(numExpe, nombre, nota);

				conjuntoAlumnos.add(alumno);

			} catch (NumberFormatException e) {
				System.out.println("Error!! Introduce un numero");
			}

			// Creamos la factoría
			DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
			// Creamos el DocumentBuilder
			DocumentBuilder db;
			try {
				db = factoria.newDocumentBuilder();
				// Creamos un DOM vacío
				Document documento = db.newDocument();
				// Establecemos la versión
				documento.setXmlVersion("1.0");
				
				
				// Creamos elementos y añadimos al DOM
				Element elementoRaiz = documento.createElement("Alumnos");	
				
				
				
				for (Alumno alumno : conjuntoAlumnos) {
				
				Element El_alumno = documento.createElement("Alumno");
				elementoRaiz.appendChild(El_alumno);
				
				Element num_Expediente = documento.createElement();
				
				}
				
				
				
				
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		}

	}

}
