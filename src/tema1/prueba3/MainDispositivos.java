package tema1.prueba3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MainDispositivos {

	private static final String SEPARADOR = ",";
	private static final File ARCHIVO_DISPOSITIVO = new File("Dispositivos.csv");
	private static final File JSON_PRODUCTO = new File("dispositivos.json");
	private static Scanner entrada = new Scanner(System.in);
	private static ListaDispositivos listaDispositivos = new ListaDispositivos();

	public static void main(String[] args) {

		int opcion = 0;
		do {
			System.out.println("--- Programa simulacro de examen ----");
			mostrarmenu();

			opcion = Integer.parseInt(entrada.nextLine());
			operarMenu(opcion);

		} while (opcion != 4);

	}

	private static void operarMenu(int opcion) {

		switch (opcion) {
		case 1:
			ejercicio1();
			break;

		case 2:
			// ejercicio2();
			break;

		case 3:
			// ejercicio3();
			break;

		case 4:
			// ejercicio4();
			break;
		case 5:
			// ejercicio5();
			break;

		default:
			System.out.println("opción no válida");
			break;
		}

	}

	private static void ejercicio1() {

		try {
			// Crear el bufferedReader para leer el archivo
			BufferedReader bufferedReader = new BufferedReader(new FileReader(ARCHIVO_DISPOSITIVO));
			String linea, marca = null, modelo = null, tipo = null, alcacena = null, ram = null, color = null;
			int id = 0, stock = 0, garanMes = 0;
			double precio = 0;
			String[] datostroc;

			// Obvia la primera linea
			String lineaDescartada = bufferedReader.readLine();

			while ((linea = bufferedReader.readLine()) != null) {

				datostroc = linea.split(SEPARADOR);

				id = Integer.parseInt(datostroc[0]);
				marca = datostroc[1];
				modelo = datostroc[2];
				tipo = datostroc[3];
				alcacena = datostroc[4];
				ram = datostroc[5];
				color = datostroc[6];
				stock = Integer.parseInt(datostroc[7]);
				precio = Double.parseDouble(datostroc[8]);
				garanMes = Integer.parseInt(datostroc[9]);

				Dispositivo d = new Dispositivo(id, marca, modelo, tipo, alcacena, ram, color, stock, precio, garanMes);

				listaDispositivos.getListaDispositivos().add(d);

			}
			System.out.println("Archivo Buffer leido correctamente");

			listaDispositivos.mostrarListaDispositivos();

			generarJson();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void generarJson() {

		ObjectMapper mapper = new ObjectMapper();

		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(JSON_PRODUCTO, listaDispositivos);

			// TENER CUIDADO SI SE IGNORA ALGUN CAMPO - SI SE IGNORA HAY QUE PONER LAS
			// ANOTACIONES

			System.out.println("Json generado correctamente");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void mostrarmenu() {

		System.out.println("Ejercicio 1 - Generar un fichero JSON a partir de un CSV");
		System.out.println("Ejercicio 2 - ");
		System.out.println("Ejercicio 3");
		System.out.println("Ejercicio 4");
		System.out.println("Ejercicio 5");

		System.out.println("\tElige opcion");
	}

}
