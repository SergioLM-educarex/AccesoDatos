package tema1.prueba;

import java.util.Scanner;

public class Menu {

	private static final int OPCION_SALIR = 4;

	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);
		int opcion=0;
		do {
			mostrarMenu();
			opcion = Integer.parseInt(entrada.nextLine());
			operarMenu(opcion);

		} while (opcion!=OPCION_SALIR);
		
		
	}

	private static void operarMenu(int opcion) {
		switch (opcion) {
		case 1:

			break;
		case 2:

			break;

		case 3:

			break;

		case 4:

			break;

		case 5:

			break;

		case 6:

			break;

		default:
			break;
		}

	}

	private static void mostrarMenu() {

		System.out.println("Menu: ");
		System.out.println("Ejercicio 1");
		System.out.println("Ejercicio 1");
		System.out.println("Ejercicio 1");
		System.out.println("Ejercicio 1");
		System.out.println("Ejercicio 1");
		System.out.println("Ejercicio 1");
		System.out.println("Elige opcion: ");

	}
}
