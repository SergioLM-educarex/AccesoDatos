package tema2ManejoConectores.examen.ex2;

import java.sql.Connection;
import java.util.Scanner;

public class MenuConsultasExamen {

	private static Connection conn = ConexionAve.conectar();
	private static final int OPCION_SALIR = 7;
	public static Scanner entrada = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		
		
		int opcion = 0;
		
		do {
			mostrar_Menu();
			opcion = Integer.parseInt(entrada.nextLine());
			operar_Menu(opcion);
		} while (opcion != OPCION_SALIR);
		
	}
	
	private static void operar_Menu(int opcion) {

		switch (opcion) {
		case 1:
		mostrar_Total_Aves();
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

		case 7:
			System.out.println("Saliendo....");
			break;

		default:
			System.out.println("Opcion no válida");
			break;
		}

	}
	
	private static void mostrar_Total_Aves() {
	
		String sql = "SELECT * FROM ave";
		
		
		
	}

	private static void mostrar_Menu() {
		System.out.println("-------------");
		System.out.println("====== Menu operaciones ======");
		System.out.println("1.Mostrar todas las aves" + "\n" + "2.Mostrar aves por hábitat" + "\n"
				+ "3.Mostrar aves con una envergadura mayor a un valor indicado" + "\n"
				+ "4.Mostrar el ave con mayor envergadura" + "\n"
				+ "5.Mostrar la envergadura media por estado de conservación" + "\n" + "6.Exportar datos"
				+ "\n" + "7.Salir");
	}
}
