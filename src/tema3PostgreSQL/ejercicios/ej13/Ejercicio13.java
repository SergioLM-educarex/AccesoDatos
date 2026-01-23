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
	private static final int CASE_OPCION_SALIR = 5;

	private static final Connection conn = ConexionMascotasDB.getConexion();
	private static Scanner entrada = new Scanner(System.in);

	/* ===================== MAIN ===================== */

	public static void main(String[] args) throws SQLException {
		int opcion;

		do {
			mostrar_Menu_Principal();
			opcion = Integer.parseInt(entrada.nextLine());
			operarMenu(opcion);
		} while (opcion != CASE_OPCION_SALIR);
	}

	/* ===================== MENÚ PRINCIPAL ===================== */

	private static void mostrar_Menu_Principal() {
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
			int opcionModificar = 0;
			do {
				ver_Menu_Modificar();
				opcionModificar = Integer.parseInt(entrada.nextLine());
				operar_Modificar(opcionModificar);
			} while (opcionModificar < 1 || opcionModificar > 3);
			break;

		case CASE_BAJA:
			// implementar
			break;

		case CASE_APLICAR_VACUNA:
			poner_Vacuna();
			break;

		case CASE_OPCION_SALIR:
			System.out.println("Saliendo del programa");
			break;

		default:
			System.out.println("Opción no válida");
			break;
		}
	}

	/* ===================== ALTA ===================== */

	private static void menu_alta() {
		System.out.println("Seleccione un alta");
		System.out.println("1. Alta de Veterinario");
		System.out.println("2. Alta de Mascota");
		System.out.println("3. Alta de Propietario");
	}

	private static void operar_alta(int opcion) throws SQLException {
		switch (opcion) {
		case MENU_ALTA_VETERINARIO:
			dar_Alta_Veterinario();
			break;

		case MENU_ALTA_MASCOTA:
			dar_Alta_Mascota();
			break;

		case MENU_ALTA_PROPIETARIO:
			dar_Alta_Propietario();
			break;

		default:
			System.out.println("Opción no disponible");
			break;
		}
	}

	/* ========= ALTA VETERINARIO ============== */

	private static void dar_Alta_Veterinario() {
		String sql = "INSERT INTO veterinario (dni, nombre, apellidos, numcolegiado) VALUES (?, ?, ?, ?)";

		int numColegiado, dni;
		String nombre;
		String[] apellidos = new String[2];

		System.out.println("INSERTE UN VETERINARIO");

		System.out.println("Ingrese numero de colegiado");
		numColegiado = Integer.parseInt(entrada.nextLine());

		System.out.println("Ingrese un nombre");
		nombre = entrada.nextLine();

		System.out.println("Ingrese el primer apellido");
		apellidos[0] = entrada.nextLine();

		System.out.println("Ingrese el segundo apellido");
		apellidos[1] = entrada.nextLine();

		System.out.println("Ingrese el dni");
		dni = Integer.parseInt(entrada.nextLine());

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, dni);
			st.setString(2, nombre);
			st.setArray(3, conn.createArrayOf("text", apellidos));
			st.setInt(4, numColegiado);

			int filasInsertadas = st.executeUpdate();

			if (filasInsertadas > 0) {
				System.out.println("Veterinario insertado correctamente");
			}

			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* ====== ALTA DE MASCOTA ====================== */

	private static void dar_Alta_Mascota() throws SQLException {
		String sqlVacuna = "INSERT INTO public.mascota(id, nombre, especie, raza, cartillavacunacion) "
				+ "VALUES (?, ?, ?, ?, ARRAY [ROW(?,?,?)::vacuna]);";
		String sqlSINVacuna = "INSERT INTO public.mascota(id, nombre, especie, raza) " + "VALUES (?, ?, ?, ?);";

		int respuesta = 0;

		// Obtener próximo ID libre
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

		do {
			System.out.println("¿Quieres añadir alguna vacuna?");
			System.out.println("1. SI");
			System.out.println("2. NO");
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
					e.printStackTrace();
				}

			} else if (respuesta == 2) {
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
					e.printStackTrace();
				}
			}

		} while (respuesta != 1 && respuesta != 2);
	}

	/* ======== ALTA PROPIETARIO ==== */

	private static void dar_Alta_Propietario() {
		String sql = "INSERT INTO propietario (dni, nombre, apellidos, direccion) "
				+ "VALUES (?, ?, ?, ROW(?, ?, ?, ?))";

		int dni, numero, cp;
		String nombre, calle, ciudad;
		String[] apellidos = new String[2];

		System.out.println("INSERTE UN PROPIETARIO");

		System.out.print("Ingrese DNI: ");
		dni = Integer.parseInt(entrada.nextLine());

		System.out.print("Ingrese nombre: ");
		nombre = entrada.nextLine();

		System.out.print("Ingrese primer apellido: ");
		apellidos[0] = entrada.nextLine();

		System.out.print("Ingrese segundo apellido: ");
		apellidos[1] = entrada.nextLine();

		System.out.print("Ingrese calle: ");
		calle = entrada.nextLine();

		System.out.print("Ingrese número: ");
		numero = Integer.parseInt(entrada.nextLine());

		System.out.print("Ingrese ciudad: ");
		ciudad = entrada.nextLine();

		System.out.print("Ingrese código postal: ");
		cp = Integer.parseInt(entrada.nextLine());

		try (PreparedStatement st = conn.prepareStatement(sql)) {

			st.setInt(1, dni);
			st.setString(2, nombre);
			st.setArray(3, conn.createArrayOf("text", apellidos));
			st.setString(4, calle);
			st.setInt(5, numero);
			st.setString(6, ciudad);
			st.setInt(7, cp);

			int filasInsertadas = st.executeUpdate();

			if (filasInsertadas > 0) {
				System.out.println("Propietario insertado correctamente");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* ===================== MODIFICAR ===================== */

	private static void ver_Menu_Modificar() {
		System.out.println("¿Que quieres modificar?");
		System.out.println("1. Modificar Propietario");
		System.out.println("2. Modificar Mascota");
		System.out.println("3. Salir");
	}

	private static void operar_Modificar(int opcionModificar) {
		switch (opcionModificar) {
		case 1:
			modificar_propietario();
			break;

		case 2:
			modificar_Campo_mascota();
			break;

		case 3:
			mostrar_Menu_Principal();
			break;

		default:
			System.out.println("Opcion no válida");
			break;
		}
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
					modificar_direccion_propietario(dni);
					break;
				case 4:
					System.out.println("Volviendo al menú anterior...");
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

	private static void modificar_direccion_propietario(int dni) {
		String mod = "UPDATE propietario " + "SET direccion = ROW(?, ?, ?, ?)::direccion " + "WHERE dni = ?";

		String calle, poblacion;
		int numero, cod_postal;

		System.out.println("Inserte la calle");
		calle = entrada.nextLine();
		System.out.println("Inserte la poblacion");
		poblacion = entrada.nextLine();
		System.out.println("Inserte el numero de la calle");
		numero = Integer.parseInt(entrada.nextLine());
		System.out.println("Inserte el codigo postal");
		cod_postal = Integer.parseInt(entrada.nextLine());

		try {
			PreparedStatement ps = conn.prepareStatement(mod);

			ps.setString(1, calle);
			ps.setInt(2, numero);
			ps.setString(3, poblacion);
			ps.setInt(4, cod_postal);
			ps.setInt(5, dni);

			int filas = ps.executeUpdate();
			if (filas > 0) {
				System.out.println("Realizado con éxito.");
			}

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* ===================== MODIFICAR MASCOTA ===================== */

	private static void modificar_Campo_mascota() {
		String sqlBuscar = "SELECT id FROM mascota WHERE id = ?";

		System.out.println("Introduce el ID de la mascota:");
		int id = Integer.parseInt(entrada.nextLine());

		try {
			PreparedStatement ps = conn.prepareStatement(sqlBuscar);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (!rs.next()) {
				System.out.println("No existe una mascota con ese ID");
				return;
			}

			int opcion;
			do {
				menu_Modificar_mascota();
				opcion = Integer.parseInt(entrada.nextLine());
				operar_ModificarMascota(id, opcion);

			} while (opcion != 5);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void menu_Modificar_mascota() {
		System.out.println("¿Qué campo quieres modificar?");
		System.out.println("1. Nombre");
		System.out.println("2. Raza");
		System.out.println("3. Especie");
		System.out.println("4. DNI propietario");
		System.out.println("5. Salir");
	}

	private static void operar_ModificarMascota(int id, int opcion) {
		switch (opcion) {
		case 1:
			modificar_campo_texto_mascota("nombre", id);
			break;
		case 2:
			modificar_campo_texto_mascota("raza", id);
			break;
		case 3:
			modificar_campo_texto_mascota("especie", id);
			break;
		case 4:
			modificar_campoInt_mascota(id);
			break;
		case 5:
			System.out.println("Volviendo al menú anterior...");
			break;
		default:
			System.out.println("Opción no válida");
		}
	}

	private static void modificar_campo_texto_mascota(String campo, int id) {
		String sql = "UPDATE mascota SET " + campo + " = ? WHERE id = ?";

		System.out.println("Introduce el nuevo valor:");
		String nuevoValor = entrada.nextLine();

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nuevoValor);
			ps.setInt(2, id);

			int filas = ps.executeUpdate();

			if (filas > 0) {
				System.out.println("Dato actualizado correctamente");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void modificar_campoInt_mascota(int id) {
		String sql = "UPDATE mascota SET dnipropietario = ? WHERE id = ?";

		System.out.println("Inserte el nuevo DNI del propietario");
		int dni = Integer.parseInt(entrada.nextLine());

		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, dni);
			ps.setInt(2, id);

			int filas = ps.executeUpdate();

			if (filas > 0) {
				System.out.println("Dato actualizado correctamente");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* ===================== PONER VACUNA ===================== */

	private static void poner_Vacuna() {
		System.out.println("Inserte el id de la mascota: ");
		int id = Integer.parseInt(entrada.nextLine());

		// Verifica si el ID existe en la base de datos
		String consultaVerificar = "SELECT COUNT(*) FROM mascota WHERE id = ?";

		try (PreparedStatement psVerificar = conn.prepareStatement(consultaVerificar)) {
			psVerificar.setInt(1, id);
			ResultSet rs = psVerificar.executeQuery();

			if (rs.next() && rs.getInt(1) == 0) {
				System.out.println("No existe ninguna mascota con el ID " + id);
				return;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

		// Si el ID existe, continuar con el proceso de vacunación
		String consulta = "CALL poner_Vacuna(?, ?, ?, ?)";

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

			boolean resultado = ps.execute();

			if (resultado) {
				System.out.println("PINCHOTAZOOOOOO");
			} else {
				System.out.println("Al hueso!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}