package tema3PostgreSQL.ejercicios.ej16;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import tema3PostgreSQL.ConexionMascotasDB;

public class Ejercicio16FuncionesRutinas {

	private static Scanner entrada = new Scanner(System.in);
	private static Connection conn = ConexionMascotasDB.getConexion();

	public static void main(String[] args) {

		int opcion = 0;

		System.out.println("---- PROGRAMA DE FUNCIONES/PRODCEDIMIENTOS ALMACENADOS ----- ");

		System.out.println("1. Mostrar el numero total de las mascotas registradas");
		System.out.println("2. Poner una vacuna a una mascota");
		opcion = Integer.parseInt(entrada.nextLine());

		// Opcion 1

		switch (opcion) {
		case 1:
			contar_mascotas();
			break;

		case 2:
			poner_Vacuna();
			break;

		case 3:
			System.out.println("Saliendo del programa");
			break;

		default:
			System.out.println("Opcion no válida");
			break;
		}

	}

	private static void poner_Vacuna() {
		String consulta = "{call poner_Vacuna(?,?,?,?)}";

		System.out.println("Inserte el id de la mascota: ");
		int id = Integer.parseInt(entrada.nextLine());

		System.out.println("Inserte nombre vacuna ");
		String nombreVacuna = entrada.nextLine();

		System.out.println("Inserte el numero de colegiado");
		int colegiado = Integer.parseInt(entrada.nextLine());

		// Fecha de vacunacion
		System.out.println("Indique año vacunacion: ");
		int anyoInt = Integer.parseInt(entrada.nextLine());
		System.out.println("Indique mes de vacunacion: ");
		int mesInt = Integer.parseInt(entrada.nextLine());
		System.out.println("Indique dia: ");
		int diaInt = Integer.parseInt(entrada.nextLine());

		LocalDate fecha = LocalDate.of(anyoInt, mesInt, diaInt);

		try (CallableStatement cs = conn.prepareCall(consulta)) {
			
			cs.setInt(1, id);           // primer ?
			cs.setString(2, nombreVacuna);    // segundo ?
			cs.setInt(3, colegiado);    // tercer ?
			cs.setDate(4,java.sql.Date.valueOf(fecha));
			
			cs.execute();
			
			System.out.println("PINCHOTAZOOOOOO");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void contar_mascotas() {

		String consulta = "{? = call contar_mascotas()}";

		try (CallableStatement stmt = conn.prepareCall(consulta)) {

			stmt.registerOutParameter(1, Types.INTEGER);

			stmt.executeUpdate();

			int resultado = stmt.getInt(1);
			System.out.println("El numero de mascotas es " + resultado);

		} catch (Exception e) {
			System.out.println("No entra ");
			e.printStackTrace();
		} finally {
			entrada.close();
		}

	}
}
