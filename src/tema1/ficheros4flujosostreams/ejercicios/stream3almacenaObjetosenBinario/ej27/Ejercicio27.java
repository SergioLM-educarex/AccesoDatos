package tema1.ficheros4flujosostreams.ejercicios.stream3almacenaObjetosenBinario.ej27;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Ejercicio27 {

	private static final String NUEVO_FICHERO_BIN = "nuevo_fichero.bin";
	private static final String ARCHIVO = "empleado.bin";
	private static final int SALIR = 5;
	private static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {
		int opcion = 0;

		do {
			mostrar_Menu();
			opcion = Integer.parseInt(entrada.nextLine());

			operarMenu(opcion);

		} while (opcion != SALIR);

	}

	private static void operarMenu(int opcion) {

		switch (opcion) {
		case 1:
			crear_Empleado();
			break;

		case 2:
			buscar_Empleados();
			break;

		case 3:
			listar_empleados();
			break;

		case 4:
			borrar_Empleado();
			break;

		case 5:

			System.out.println("Saliendo...");
			break;

		default:
			System.out.println("Opcion no valida");
			break;
		}

	}

	private static void borrar_Empleado() {

		ObjectInputStream inputStream = null;
		ObjectOutputStream objectTemporal = null;
		String dniAborrar;

		File archivo = new File(ARCHIVO);
		File temporal = new File(NUEVO_FICHERO_BIN);

		System.out.println("Que DNI desea borrar");
		dniAborrar = entrada.nextLine();

		if (archivo.exists() && archivo.isFile()) {

			try {
				inputStream = new ObjectInputStream(new FileInputStream(archivo));
				objectTemporal = new ObjectOutputStream(new FileOutputStream(temporal));

				while (true) {

					Empleado empleado = (Empleado) inputStream.readObject();

					if (!dniAborrar.equals(empleado.getDni())) {
						objectTemporal.writeObject(empleado);
					}

				}

			} catch (EOFException eof) {

			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (archivo.delete()) {
				if (!temporal.renameTo(archivo)) {
					System.out.println("Error al renombrar el fichero temporal.");
				}
			} else {
				System.out.println("Error al eliminar el fichero original.");
			}

		}

	}

	private static void listar_empleados() {

		File archivo = new File(ARCHIVO);

		ObjectInputStream ois;

		if (archivo.exists() && archivo.isFile()) {

			try {
				ois = new ObjectInputStream(new FileInputStream(archivo));
				while (true) { // Unico sitio donde lo vamos a ver

					Empleado empleado = (Empleado) ois.readObject();

					System.out.println(empleado.toString());
				}

			} catch (EOFException e) {
				System.out.println("Terminado");

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private static void buscar_Empleados() {

		String dniPedido;

		File archivo = new File(ARCHIVO);

		ObjectInputStream ois;

		if (archivo.exists() && archivo.isFile()) {
			try {
				ois = new ObjectInputStream(new FileInputStream(archivo));

				dniPedido = pedir_Dni();

				while (true) { // Unico sitio donde lo vamos a ver

					Empleado empleado = (Empleado) ois.readObject();

					if (dniPedido.equals(empleado.getDni())) {
						System.out.println(empleado.toString());

					}

				}

			} catch (EOFException e) {
				System.out.println("Terminado");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private static String pedir_Dni() {
		String dniPedido;
		System.out.println("Inserte el dni");
		dniPedido = entrada.nextLine();

		return dniPedido;
	}

	private static void crear_Empleado() {
		Empleado empleado = pedirDatosEmpleados();
		File archivo = new File(ARCHIVO);
		ObjectOutputStream oos = null;

		try {
			// Si el archivo ya existe y tiene contenido, usamos MyObjectOutputStream para
			// no escribir la cabecera de nuevo
			if (archivo.exists() && archivo.length() > 0) {
				oos = new MyObjectOutputStream(new FileOutputStream(archivo, true));
			} else {
				// Primera vez: usar ObjectOutputStream normal para escribir la cabecera
				oos = new ObjectOutputStream(new FileOutputStream(archivo));
			}

			oos.writeObject(empleado);
			System.out.println("Empleado creado correctamente.\n");

		} catch (IOException e) {
			System.out.println("Error al escribir el empleado: " + e.getMessage());
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
			} catch (IOException e) {
				System.out.println("Error al cerrar el archivo: " + e.getMessage());
			}
		}
	}

	private static Empleado pedirDatosEmpleados() {

		String nombre, dni;
		double sueldo;


		System.out.println("Inserte el dni: ");
		dni = entrada.nextLine().toUpperCase().trim();
		
		System.out.println("Inserte el nombre: ");
		nombre = entrada.nextLine().toUpperCase().trim();

		System.out.println("Inserte el sueldo");
		sueldo = Double.parseDouble(entrada.nextLine());

		Empleado empleado = new Empleado(dni, nombre, sueldo);

		return empleado;

	}

	private static void mostrar_Menu() {
		System.out.println("======== MENU ========");
		System.out.println("-----------------------");
		System.out.println("1. Dar de alta empleado");
		System.out.println("2. Buscar empleados por DNI");
		System.out.println("3. Listar empleados");
		System.out.println("4. Borrar empleados");
		System.out.println("5. Salir");
	}

}
