package tema1.ficheros4flujosostreams.ejercicios.stream1Caracteres.ej20;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ejercicio20_Main {

	private static final String SEPARADOR = ";";
	// Constantes
	private static final int OPCION_SALIR = 5;
	private static final String NOMBRE_FICHERO = "Telefonos.dat";

	// Variables
	private static Scanner entrada = new Scanner(System.in);
	private static Map<String, Telefono> mapaAgendaTelefono = new HashMap<String, Telefono>();

	public static void main(String[] args) {

		int opcion = 0;

		do {
			mostrar_Menu();
			opcion = Integer.parseInt(entrada.nextLine());

			operarMenu(opcion);

		} while (opcion != OPCION_SALIR);

	}

	private static void operarMenu(int opcion) {

		switch (opcion) {
		case 1:
			add_Telefono();
			break;
		case 2:
			show_ListaContactos();
			break;
		case 3:
			modificar_Lista();
			break;
		case 4:
			// delete_Telefono
			break;
		case 5:
			System.out.println("Saliendo del programa... ");
			break;

		default:

			System.out.println("Opcion no valida, repite la operacion");
			break;
		}

	}

	private static void modificar_Lista() {

		long numTelefono = 0;
		String telefono = "";
		int opcion = 0;
		String nombreNuevo="";

		telefono = pedirTelefono(numTelefono, false);

		if (mapaAgendaTelefono.containsKey(telefono)) {

			while (opcion != 1 && opcion != 2) {
				System.out.println("¿Que deseas modificar?");
				System.out.println("1. Nombre");
				System.out.println("2. Telefono");
				opcion = Integer.parseInt(entrada.nextLine());
				
				if (opcion==1) {
					System.out.println("Escriba el nombre de nuevo");
					nombreNuevo= entrada.nextLine();
					
					Telefono t= mapaAgendaTelefono.get(telefono);
					
					t.setNombrepersona(nombreNuevo);
					
					guardarAgenda();
				}else {
					
					
					telefono= pedirTelefono(numTelefono, false);
					
					Telefono t = mapaAgendaTelefono.get(telefono);
					mapaAgendaTelefono.remove(t);
					t.setNumTelefono(telefono);
					mapaAgendaTelefono.put(telefono, t);
					
					guardarAgenda();
				}

				if (opcion != 1 && opcion != 2) {
					System.out.println("Repite la operación");
				}
			}

		} else {
			System.out.println("Telefono incorrecto");
		}

	}

	private static void show_ListaContactos() {
		for (Map.Entry<String, Telefono> entry : mapaAgendaTelefono.entrySet()) {
			String key = entry.getKey();
			Telefono val = entry.getValue();

			System.out.println(val);

		}

	}

	private static void add_Telefono() {

		String nombre, telefono;
		long telefonoNumeros = 0;
		boolean num_valido = false;

		System.out.println("Inserte el nombre;");
		nombre = entrada.nextLine();

		telefono = pedirTelefono(telefonoNumeros, num_valido); // validado ya todo, lo convertimos en texto para
																// trabajar con el.

		Telefono telefonoPersonal = new Telefono(nombre, telefono);

		mapaAgendaTelefono.put(telefono, telefonoPersonal);

		guardarAgenda();

	}

	private static String pedirTelefono(long telefonoNumeros, boolean num_valido) {
		String telefono;
		num_valido = false;

		while (!num_valido) {
			System.out.println("Escriba el número de teléfono (solo digitos):");
			try {

				telefonoNumeros = Long.parseLong(entrada.nextLine());
				num_valido = true; // Si no hay excepcion, el número es valido
			} catch (NumberFormatException e) {
				System.err.println("Error: debe escribir solo numeros. Intentelo de nuevo.");
			}

		}

		telefono = String.valueOf(telefonoNumeros);
		return telefono;
	}

	private static void guardarAgenda() {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(NOMBRE_FICHERO, false));

			for (Map.Entry<String, Telefono> entry : mapaAgendaTelefono.entrySet()) {
				String key = entry.getKey();
				Telefono val = entry.getValue();

				writer.write(val.getNombrepersona() + SEPARADOR + val.getNumTelefono());
				writer.newLine();

			}

			writer.close();
			System.out.println("Agenda guardada correctamente");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void mostrar_Menu() {

		System.out.println("");
		System.out.println("======== AGENDA DE TELEFONOS ========");
		System.out.println("1. A�adir telefono");
		System.out.println("2. Mostrar lista de telefonos");
		System.out.println("3. Modificar datos de la agenda");
		System.out.println("4. Borrar un numero de telefono");
		System.out.println("5. Salir");
		System.out.println("------------- Elige una opcion:");

	}
}
