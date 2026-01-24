package tema3PostgreSQL.examen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

	private static final String SEPARADOR = ",";

	private static final int CASE_CARGAR_DATOS = 2;

	private static final int CASE_ALTA_CLIENTE = 1;

	private static final int CASE_ALTA = 1;

	private static final int OPCION_SALIR = 5;

	private static Connection con = ConexionGimnasioDB.getConexion();
	private static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {
		int opcion = 0;

		do {

			try {
				mostrar_Menu_Principal();
				opcion = Integer.parseInt(entrada.nextLine());
				operar_Menu_principal(opcion);
			} catch (NumberFormatException e) {
				System.out.println("Introduzca un numero, repita la operacion");
			}
		} while (opcion != OPCION_SALIR);

	}

	/* ======================= MENU PRINCIPAL ========================= */
	private static void operar_Menu_principal(int opcion) {

		switch (opcion) {
		case CASE_ALTA:
			int op = 0;

			do {
				try {
					mostrar_menu_alta();
					op = Integer.parseInt(entrada.nextLine());
					operar_menu_alta(op);
				} catch (NumberFormatException e) {
					System.out.println("Introduce un numero, respita la operacion");
				}
			} while (op < 1 || op > 3);

			break;
		case CASE_CARGAR_DATOS:
			cargar_Datos();
		
			break;
		case 3:

			break;
		case 4:

			break;
		case 5:

			break;

		default:
			break;
		}

	}

	private static void cargar_Datos() {
		int inse = 0;

		do {
			System.out.println("¿Que quieres insertar?");
			System.out.println("1. Entrenadores");
			System.out.println("2. Clientes");
			inse = Integer.parseInt(entrada.nextLine());
		} while (inse != 1 || inse!= 2);

		
		if (inse==1) {
			cargar_Entrenadores_CSV();
		}else {
			// cargar_Clientes_JSOn();
		}
	}

	private static void cargar_Entrenadores_CSV() {

		String rutaArchivo = "entrenadores.csv";

		int insertados = 0;
		int duplicados = 0;
		int errores = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
			String linea;
			boolean primeraLinea = true;

			while ((linea = br.readLine()) != null) {
				// Saltar la cabecera
				if (primeraLinea) {
					primeraLinea = false;
					continue;
				}

				String[] datosTroc = linea.split(SEPARADOR);

				if (datosTroc.length != 7) {
					System.out.println("Línea con formato incorrecto: " + linea);
					errores++;
					continue;
				}

				String dni = datosTroc[0].trim();
				String nombre = datosTroc[1].trim();
				String apellido1 = datosTroc[2].trim();
				String apellido2 = datosTroc[3].trim();
				String especialidad = datosTroc[4].trim();
				int salarioBase = Integer.parseInt(datosTroc[5].trim());
				int complemento = Integer.parseInt(datosTroc[6].trim());

				String apellidos = apellido1 + " " + apellido2;

				// Verificar si el DNI ya existe
				if (existeDNI_Entrenador(dni)) {
					System.out.println("El entrenador con DNI " + dni + " ya existe. Saltando...");
					duplicados++;
					continue;
				}

				// Insertar el entrenador
				String sql = "INSERT INTO public.entrenador(dni, nombre, apellidos, especialidad, salario) "
						+ "VALUES (?, ?, ?, ?, ROW(?, ?)::pago)";

				try (PreparedStatement ps = con.prepareStatement(sql)) {
					ps.setString(1, dni);
					ps.setString(2, nombre);
					ps.setString(3, apellidos);
					ps.setString(4, especialidad);
					ps.setDouble(5, salarioBase);
					ps.setDouble(6, complemento);

					int resultado = ps.executeUpdate();

					if (resultado > 0) {
						System.out.println("Entrenador insertado: " + nombre + " " + apellidos);
						insertados++;
					}
				} catch (SQLException e) {
					System.out.println("Error al insertar entrenador con DNI " + dni + ": " + e.getMessage());
					errores++;
				}
			}

			// Resumen
			System.out.println("\n===== RESUMEN DE CARGA =====");
			System.out.println("Entrenadores insertados: " + insertados);
			System.out.println("Duplicados omitidos: " + duplicados);
			System.out.println("Errores: " + errores);

		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static boolean existeDNI_Entrenador(String dni) {
		String sql = "SELECT COUNT(*) FROM entrenador WHERE dni = ?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	/* ============= ALTAS =============================== */

	private static void operar_menu_alta(int op) {
		switch (op) {
		case CASE_ALTA_CLIENTE:
			try {
				alta_Cliente();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case 2:
			alta_Entrenador();
			break;
		case 3:
			mostrar_Menu_Principal();
			break;

		default:
			System.out.println("Opcion no válida");
			break;
		}

	}

	/* ============= ALTA DE ENTRENADOR ============== */
	private static void alta_Entrenador() {

		String dni, nombre, apellidos, apellido1, apellido2;

		String sql = "INSERT INTO public.entrenador(dni, nombre, apellidos, especialidad, salario)"
				+ "	VALUES (?, ?, ?, ?, ROW(?,?)::pago)";

		System.out.println("Ingrese el dni");
		dni = entrada.nextLine();

		System.out.println("Ingresa el nombre");
		nombre = entrada.nextLine();

		System.out.println("Ingresa el primer apellido");
		apellido1 = entrada.nextLine();

		System.out.println("Ingresa el segundo apellido");
		apellido2 = entrada.nextLine();

		apellidos = apellido1 + " " + apellido2;

		System.out.println("Especialidad");
		String especialidad = entrada.nextLine();

		System.out.println("Cual es el salario base");
		int salarioBase = Integer.parseInt(entrada.nextLine());

		System.out.println("Inserte la comision");
		int comision = Integer.parseInt(entrada.nextLine());

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, dni);
			ps.setString(2, nombre);
			ps.setString(3, apellidos);
			ps.setString(4, especialidad);
			ps.setInt(5, salarioBase);
			ps.setInt(6, comision);

			int resultado = ps.executeUpdate();

			if (resultado > 0) {
				System.out.println("Bienvenido " + nombre + " " + apellido1);
			} else {
				System.out.println("No se ha podido insertar el entrenador");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* ============= ALTA DE CLIENTE ============== */

	private static void alta_Cliente() throws SQLException {
		String sql = "INSERT INTO public.cliente(\r\n" + "	dni, nombre, apellidos, numerocliente, rutinas)\r\n"
				+ "	VALUES (?, ?, ?, ?, ?);";

		String sqlSinrutina = "INSERT INTO public.cliente(\r\n" + "	dni, nombre, apellidos, numerocliente)\r\n"
				+ "	VALUES (?, ?, ?, ?);";

		String dni, nombre, apellidos, apellido1, apellido2;
		ArrayList<String> listaRutinas = new ArrayList<>();
		int numClienteGenerado = 0, op = 0;

		System.out.println("Ingrese el dni");
		dni = entrada.nextLine();

		System.out.println("Ingresa el nombre");
		nombre = entrada.nextLine();

		System.out.println("Ingresa el primer apellido");
		apellido1 = entrada.nextLine();

		System.out.println("Ingresa el segundo apellido");
		apellido2 = entrada.nextLine();

		apellidos = apellido1 + " " + apellido2;

		// Obtiene próximo ID libre
		PreparedStatement psId = con
				.prepareStatement("SELECT COALESCE(MAX(numerocliente),0) + 1 AS next_id FROM cliente");
		ResultSet rs = psId.executeQuery();
		rs.next();
		numClienteGenerado = rs.getInt("next_id");
		System.out.println("Id generado = " + numClienteGenerado);

		// Preguntar por rutinas
		do {
			System.out.println("¿Quieres añadir una rutina?");
			System.out.println("1. SI");
			System.out.println("2. NO");
			op = Integer.parseInt(entrada.nextLine());

			if (op == 1) {
				System.out.println("Inserte el nombre de la rutina: ");
				String rutina = entrada.nextLine();
				listaRutinas.add(rutina);
				System.out.println("Rutina añadida correctamente.");
			}

		} while (op != 2);

		// Insertar en base de datos
		PreparedStatement ps;
		int resultado;

		if (listaRutinas.isEmpty()) {
			// Sin rutinas
			ps = con.prepareStatement(sqlSinrutina);
			ps.setString(1, dni);
			ps.setString(2, nombre);
			ps.setString(3, apellidos);
			ps.setInt(4, numClienteGenerado);
			resultado = ps.executeUpdate();
		} else {
			// Con rutinas
			String[] rutinas = listaRutinas.toArray(new String[0]);
			ps = con.prepareStatement(sql);
			ps.setString(1, dni);
			ps.setString(2, nombre);
			ps.setString(3, apellidos);
			ps.setInt(4, numClienteGenerado);
			ps.setArray(5, con.createArrayOf("text", rutinas));
			resultado = ps.executeUpdate();
		}

		if (resultado > 0) {
			System.out.println("Bienvenido " + nombre + " " + apellidos);
			if (!listaRutinas.isEmpty()) {
				System.out.println("Rutinas asignadas: " + listaRutinas);
			}
		} else {
			System.out.println("No se ha insertado en la base de datos");
		}

		ps.close();
		rs.close();
		psId.close();
	}

	private static void mostrar_menu_alta() {
		System.out.println("====== MENU ALTA =====");
		System.out.println("1. Agregar un cliente");
		System.out.println("2. Agregar un entrenador");
		System.out.println("3. Salir");
	}

	private static void mostrar_Menu_Principal() {
		System.out.println("====== MENU GIMNASIO ======");
		System.out.println("1. Alta");
		System.out.println("2. Cargar Datos");
		System.out.println("3. Gestion de rutinas clientes");
		System.out.println("4. Gestion de salarios");
		System.out.println("5. Salir");
	}
}
