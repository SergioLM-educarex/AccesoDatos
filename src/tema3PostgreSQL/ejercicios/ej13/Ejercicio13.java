package tema3PostgreSQL.ejercicios.ej13;

import java.util.Scanner;

public class Ejercicio13 {

	private static final int OPCION_SALIR = 5;
	private static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {
		
		
		int opcion = 0;
		
		
		do {
			mostrar_Menu();
			opcion = Integer.parseInt(entrada.nextLine());
			
			operarMenu(opcion);
				
		} while (opcion!=OPCION_SALIR);
		
	}

	/**
	 * Ejecuta el menu de operaciones con un switch
	 * @param opcion
	 */
	private static void operarMenu(int op) {
		switch (op) {
		case 1:
			int opcion = 0;
			do {
				System.out.println("Seleccione un alta");
				System.out.println("1. Alta de Veterinario");
				System.out.println("2. Alta de Mascota");
				System.out.println("3. Alta de propietario");
				opcion = Integer.parseInt(entrada.nextLine());
				
			} while (opcion!=1||opcion!=2||opcion!=3);
			
			
			
			break;
		case 2:
			
			break;
		case 3:
			
			break;
			
		case 4:
			
			break;
			
		case 5: 
			System.out.println("Saliendo del programa");
			break;

		default:
			System.out.println("Opción no válida, repita la operacion");
			break;
		}
		
	}

	/**
	 * Muestra el menu de operaciones
	 */
	private static void mostrar_Menu() {
		System.out.println("-----------------------------------");
		System.out.println("------  APLICACION MASCOTAS   -----");
		System.out.println("-----------------------------------");
		
		System.out.println("\t 1. Alta");
		System.out.println("\t 2. Modificar");
		System.out.println("\t 3. Baja");
		System.out.println("\t 4. Aplicar vacuna");
		System.out.println("\t 5. Salir");
		System.out.println("Elige una opcion:");
	}
}
