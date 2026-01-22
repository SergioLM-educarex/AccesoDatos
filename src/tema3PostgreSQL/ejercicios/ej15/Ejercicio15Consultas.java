package tema3PostgreSQL.ejercicios.ej15;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import tema3PostgreSQL.ConexionMascotasDB;

public class Ejercicio15Consultas {

	private static final int CASE_3_MOSTRAR_PERROS_MOQUILLO = 3;
	private static final int CASE_2_MOSTRAR_NOMBRE_MASCOTAS_DNI = 2;
	private static final int CASE_LISTAR_MASCOTAS_VACUNA = 1;
	private static final Connection conn = ConexionMascotasDB.getConexion();

	private static final int OP_SALIR = 4;

	private static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {

		int opcion = 0;

		do {
			mostrar_Menu();
			opcion = Integer.parseInt(entrada.nextLine());
			operar_Menu(opcion);

		} while (opcion != OP_SALIR);

	}

	private static void operar_Menu(int opcion) {

		switch (opcion) {
		case CASE_LISTAR_MASCOTAS_VACUNA:

			listar_mascotas_vacunas();
			break;

		case CASE_2_MOSTRAR_NOMBRE_MASCOTAS_DNI:
			mostrar_nombreYespecie_pasandoDNI();
			break;

		case CASE_3_MOSTRAR_PERROS_MOQUILLO:
			mostrar_Perros_moquillo();
			break;

		case OP_SALIR:
			System.out.println("Saliendo del programa");
			break;
		default:
			System.out.println("Opcion no disponible");
			break;
		}

	}

	private static void mostrar_Perros_moquillo() {
		
		String sql = "SELECT * FROM public.mascota WHERE dnipropietario = ?";
		
	}

	private static void mostrar_nombreYespecie_pasandoDNI() {

		String sql = "SELECT nombre, especie FROM public.mascota WHERE dnipropietario = ?";
		
		try {
		
			System.out.println("Inserte el DNI: ");
			int dni = Integer.parseInt(entrada.nextLine());
			
			
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setInt(1, dni);
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			
			System.out.println("NOMBRE: "+rs.getString("nombre"));
			System.out.println("ESPECIE: "+rs.getString("especie"));
			
		}
			
			
			
			
			
		} catch (NumberFormatException e) {
			System.out.println("Inserte un dni sin letra");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

	private static void listar_mascotas_vacunas() {

		String sql = "SELECT * FROM public.mascota";

		try {
			PreparedStatement ps = conn.prepareCall(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				System.out.println("ID: " + rs.getInt("id"));
				System.out.println("NOMBRE MASCOTA: " + rs.getString("nombre"));
				System.out.println("ESPECIE DE LA MASCOTA: " + rs.getString("especie"));
				System.out.println("RAZA: " + rs.getString("raza"));
				System.out.println("DNI PROPIETARIO: " + rs.getInt("dnipropietario"));
				System.out.println("CARTILLA DE VACUNACION: " + rs.getArray("cartillavacunacion"));

				System.out.println("·· ·· ·· ·· ·· ·· ··");
			}
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Muestra el menu de operaciones
	 */
	private static void mostrar_Menu() {
		System.out.println("-----------------------------------");
		System.out.println("------  APLICACION MASCOTAS   -----");
		System.out.println("-----------------------------------");

		System.out.println("\t 1. Listas todas las mascotas con sus vacunas");
		System.out.println("\t 2. Mostrar nombre y especie de las mascotas de un propietario pasando el dni");
		System.out.println("\t 3. Mostrar todos los perros que se han vacunado de la moquillo");
		System.out.println("\t 4. Aplicar vacuna");
		System.out.println("\t 5. Salir");
		System.out.println("Elige una opcion:");
	}

}
