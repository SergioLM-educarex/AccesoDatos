package tema3PostgreSQL.ejercicios.ej13;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import tema3PostgreSQL.ConexionMascotasDB;

public class Ejercicio13 {

	/* ================== CONSTANTES MENU ALTA =============== */
	private static final int MENU_ALTA_VETERINARIO = 1;
	private static final int MENU_ALTA_MASCOTA = 2;
	private static final int MENU_ALTA_PROPIETARIO = 3;

	/* ================== CONSTANTES MENU PRINCIPAL =============== */
	private static final int CASE_ALTA = 1;
	private static final int CASE_MODIFICAR = 2;
	private static final int CASE_BAJA = 3;
	private static final int CASE_APLICAR_VACUNA = 4;
	private static final int OPCION_SALIR = 5;

	private static final Connection conn = ConexionMascotasDB.getConexion();
	private static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {

		int opcion;

		do {
			mostrar_Menu();
			opcion = Integer.parseInt(entrada.nextLine());
			operarMenu(opcion);
		} while (opcion != OPCION_SALIR);
	}

	private static void operarMenu(int op) throws SQLException {

		switch (op) {

		case CASE_ALTA:
			int opcionAlta;
			do {
				menu_alta();
				opcionAlta = Integer.parseInt(entrada.nextLine());
				operar_alta(opcionAlta);
			} while (opcionAlta < 1 || opcionAlta > 3);
			break;

		case CASE_MODIFICAR:
			int opcionModificar= 0;
			do {
				ver_Menu_Modificar();
				opcionModificar = Integer.parseInt(entrada.nextLine());
				operar_alta(opcionModificar);
			} while (opcionModificar < 1 || opcionModificar > 3);
			break;

		case CASE_BAJA:

			break;

		case CASE_APLICAR_VACUNA:
			poner_Vacuna();
			break;

		case OPCION_SALIR:
			System.out.println("Saliendo del programa");
			break;

		default:
			System.out.println("Opción no válida");
			break;
		}
	}

	

	/* ===================== PONER VACUNA ===================== */

	private static void poner_Vacuna() {

		String consulta = "CALL poner_Vacuna(?, ?, ?, ?)";

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

		try (PreparedStatement ps = conn.prepareStatement(consulta)) {

			ps.setInt(1, id);
			ps.setString(2, nombreVacuna);
			ps.setInt(3, colegiado);
			ps.setDate(4, java.sql.Date.valueOf(fecha));

			ps.execute();

			System.out.println("PINCHOTAZOOOOOO");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* ===================== ALTA ===================== */

	private static void operar_alta(int opcion) throws SQLException {

		switch (opcion) {

		case MENU_ALTA_VETERINARIO:
			dar_Alta_Veterinario();
			break;

		case MENU_ALTA_MASCOTA:
			dar_Alta_Mascota();
			break;

		case MENU_ALTA_PROPIETARIO:
			System.out.println("Alta Propietario (pendiente de BD)");
			break;

		default:
			System.out.println("Opción no disponible");
			break;
		}
	}

	private static void dar_Alta_Mascota() throws SQLException {
		String sqlVacuna = "INSERT INTO public.mascota(id, nombre, especie, raza, cartillavacunacion) "
				+ "VALUES (?, ?, ?, ?, ARRAY [ROW(?,?,?)::vacuna]);";
		String sqlSINVacuna = "INSERT INTO public.mascota(id, nombre, especie, raza) " + "VALUES (?, ?, ?, ?);";

		Scanner entrada = new Scanner(System.in);
		int opcion = 0;
		int respuesta = 0;

		// 1️⃣ Obtener próximo ID libre
		PreparedStatement psId = conn.prepareStatement("SELECT COALESCE(MAX(id),0) + 1 AS next_id FROM mascota");
		ResultSet rs = psId.executeQuery();
		rs.next();
		int id = rs.getInt("next_id");
		System.out.println("Id generado = " + id);

		System.out.println("Inserte el nombre");
		String nombre = entrada.nextLine();

		System.out.println("Inserte la especie");
		String especie = entrada.nextLine();

		System.out.println("Inserte la raza");
		String raza = entrada.nextLine();

		// ARRAY
		// Preguntar si quiere añadir otra vacunacion

		do {
			System.out.println("¿Quieres añadir alguna vacuna?");
			System.out.println("1. SI");
			System.out.println("2.NO");
			respuesta = Integer.parseInt(entrada.nextLine());

			if (respuesta == 1) {

				System.out.println("Inserte nombre vacuna ");
				String nombreVacuna = entrada.nextLine();

				System.out.println("Inserte el numero de colegiado");
				int colegiado = Integer.parseInt(entrada.nextLine());

				// Fecha de vacunacion
				System.out.println("Indique año vacunacion");
				String anyo = entrada.nextLine();
				System.out.println("Indique mes de vacunacion");
				String mes = entrada.nextLine();
				System.out.println("Indique dia");
				String dia = entrada.nextLine();

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate fecha = LocalDate.parse(anyo + "-" + mes + "-" + dia, formatter);

				try {
					PreparedStatement ps = conn.prepareStatement(sqlVacuna);

					ps.setInt(1, id);
					ps.setString(2, nombre);
					ps.setString(3, especie);
					ps.setString(4, raza);

					ps.setString(5, nombreVacuna);
					ps.setInt(6, colegiado);
					ps.setDate(7, java.sql.Date.valueOf(fecha));

					int filasInsertadas = ps.executeUpdate();

					if (filasInsertadas > 0) {
						System.out.println("Mascota insertada correctamente");
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				try {
					PreparedStatement ps = conn.prepareStatement(sqlSINVacuna);

					ps.setInt(1, id);
					ps.setString(2, nombre);
					ps.setString(3, especie);
					ps.setString(4, raza);

					int filasInsertadas = ps.executeUpdate();

					if (filasInsertadas > 0) {
						System.out.println("Mascota insertada correctamente");
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} while (respuesta != 1 && respuesta != 2);

	}

	private static void dar_Alta_Veterinario() {

	}

	private static void menu_alta() {
		System.out.println("Seleccione un alta");
		System.out.println("1. Alta de Veterinario");
		System.out.println("2. Alta de Mascota");
		System.out.println("3. Alta de Propietario");
	}
	
	/* ====== ======== ======= MODIFICAR ========== ============ =============*/
	
	private static void ver_Menu_Modificar() {
		System.out.println("¿Que quieres modificar?");
		System.out.println("1. Modificar Propietario");
		System.out.println("2. Modificar Mascota");
		System.out.println("3. Salir");
		
	}
	

	/* ===================== MODIFICAR PROPIETARIO ===================== */

	private static void modificar_propietario() {

		String sqlBuscar = "SELECT dni FROM propietario WHERE dni = ?";

		System.out.println("Introduce el DNI del propietario:");
		int dni = Integer.parseInt(entrada.nextLine());

		try {
			PreparedStatement ps = conn.prepareStatement(sqlBuscar);
			ps.setInt(1, dni);

			ResultSet rs = ps.executeQuery();

			if (!rs.next()) {
				System.out.println("No existe un propietario con ese DNI");
				return;
			}

			int opcion;
			do {
				System.out.println("¿Qué desea modificar?");
				System.out.println("1. Nombre");
				System.out.println("2. Apellidos");
				System.out.println("3. Dirección");
				System.out.println("4. Salir");

				opcion = Integer.parseInt(entrada.nextLine());

				switch (opcion) {
				case 1:
					modificar_campo_propietario("nombre", dni);
					break;
				case 2:
					modificar_campo_propietario("apellidos", dni);
					break;
				case 3:
					modificar_campo_propietario("direccion", dni);
					break;
				case 4:
					mostrar_Menu();
					break;
				default:
					System.out.println("Opción no válida");
				}

			} while (opcion != 4);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void modificar_campo_propietario(String campo, int dni) {

		String sql = "UPDATE propietario SET " + campo + " = ? WHERE dni = ?";

		System.out.println("Introduce el nuevo valor:");
		String nuevoValor = entrada.nextLine();

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nuevoValor);
			ps.setInt(2, dni);

			int filas = ps.executeUpdate();

			if (filas > 0) {
				System.out.println("Dato actualizado correctamente");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* ===================== MENÚ ===================== */

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
