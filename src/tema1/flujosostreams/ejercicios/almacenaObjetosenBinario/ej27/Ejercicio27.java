package tema1.flujosostreams.ejercicios.almacenaObjetosenBinario.ej27;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Ejercicio27 {

	private static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {
		int opcion = 0;

		do {
			mostrar_Menu();
			opcion = Integer.parseInt(entrada.nextLine());

			operarMenu(opcion);

		} while (opcion != 5);

	}

	private static void operarMenu(int opcion) {

		switch (opcion) {
		case 1:
			log_Empleados();
			break;

		case 2:
			buscar_Empleados();
			break;

		case 3:
			// Lista empleados
			break;

		case 4:
			// Borrar empleados (por el dni)
			break;

		case 5:

			System.out.println("Saliendo...");
			break;

		default:
			System.out.println("Opci�n de valida");
			break;
		}

	}

	private static void buscar_Empleados() {
		
		String dniPedido;
		
		File archivo = new File("empleado.bin");
		
		Empleado empleado = null;
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
		
		System.out.println("Inserte el dni");
		dniPedido = entrada.nextLine();
		
		while (ois.readObject()!=null) {
			if (dniPedido.equals()) {
				
			}
		}
		
		
		
		
		
		
		
	}

	private static void log_Empleados() {
		
		Empleado empleado = pedirDatosEmpleados();

		File archivo = new File("empleado.bin");
		ObjectOutputStream oos = null;

		try {
			oos = new ObjectOutputStream(new FileOutputStream(archivo));
			oos.writeObject(empleado);
			
			
			
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (oos!=null) {
				try {
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	private static Empleado pedirDatosEmpleados() {

		String nombre, dni;
		double sueldo;

		System.out.println("Inserte el nombre: ");
		nombre = entrada.nextLine().toUpperCase().trim();

		System.out.println("Inserte el dni: ");
		dni = entrada.nextLine().toUpperCase().trim();

		System.out.println("Inserte el sueldo");
		sueldo = Double.parseDouble(entrada.nextLine());

		Empleado empleado = new Empleado(nombre, dni, sueldo);

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
