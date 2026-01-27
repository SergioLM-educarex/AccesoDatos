package tema3PostgreSQL.examen.gimnasio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Principal {

	private static final int CASE_GESTION_SALARIOS = 4;

	private static final int CASE_GESTION_RUTINAS_CLIENTES = 3;

	private static final String CLIENTES_JSON = "clientes.json";

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
		case CASE_GESTION_RUTINAS_CLIENTES:
			gestion_Rutinas_Clientes();
			break;
		case CASE_GESTION_SALARIOS:
			try {
				gestion_Salarios();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 5:
			System.out.println("Saliendo...");
			break;

		default:
			System.out.println("Opción no disponible");
			break;
		}

	}

	/* ================= GESTION DE SALARIO ======================== */

	private static void gestion_Salarios() throws SQLException {

		System.out.println("GESTION DE SALARIOS");
		System.out.println("1. Calcular salario entrenador");
		System.out.println("2. Aumentar 2% salario aa todos los entrenadores");
		int op = Integer.parseInt(entrada.nextLine());

		if (op == 1) {
			calcular_salario_entrenador();
		} else {
			subir_Salarios_Entrenadores();
		}

	}

	/* ================= SUBIR SALARIO DE ENTRENADORES ======================== */
	private static void subir_Salarios_Entrenadores() throws SQLException {
	    String sql = "CALL subir_salarios_entrenadores()";
	    
	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.execute();
	        System.out.println("Salarios de todos los entrenadores actualizados (+2%)");
	    } catch (SQLException e) {
	        System.out.println("Error al actualizar salarios: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

	/* ================= CALCULAR SALARIO DE ENTRENADOR ======================== */

	private static void calcular_salario_entrenador() {

		System.out.println("Introduce el DNI del entrenador:");
		String dni = entrada.nextLine();

		// Sintaxis para llamar a una función: {? = call nombre_funcion(?)}
		String sql = "{? = call calcular_salario_entrenador(?)}";

		CallableStatement cs;
		try {
			cs = con.prepareCall(sql);
			// Registrar el parámetro de salida (primer ?)
			cs.registerOutParameter(1, Types.INTEGER);

			// Establecer el parámetro de entrada (segundo ?)
			cs.setString(2, dni);

			// Ejecutar la función
			cs.execute();

			// Obtener el resultado
			int salarioTotal = cs.getInt(1);

			if (salarioTotal > 0) {
				System.out.println("Salario total del entrenador: " + salarioTotal + "€");
			} else {
				System.out.println("No existe el entrenador " + dni);
			}

			cs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* ============ GESTION DE RUTINAS CLIENTES ================== */

	private static void gestion_Rutinas_Clientes() {

		int op = 0;
		do {

			try {
				System.out.println("1. Registrar una nueva rutina para un cliente");
				System.out.println("2. Total de rutinas de un cliente");
				op = Integer.parseInt(entrada.nextLine());

				switch (op) {
				case 1:
					registrar_Rutina();
					break;
				case 2:
					calcular_Rutinas();
					break;
				default:
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Inserte un numero");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} while (op != 1 || op != 2);

	}

	/* ============ CALCULAR RUTINAS CLIENTES ================== */

	private static void calcular_Rutinas() throws SQLException {
		String sql = "SELECT array_length(rutinas, 1) AS total_rutinas " + "FROM cliente " + "WHERE numerocliente = ?";

		System.out.println("Introduce el numero de cliente:");
		int numeroCliente = Integer.parseInt(entrada.nextLine());

		// Verificar que el cliente existe
		String sqlBuscar = "SELECT numerocliente FROM cliente WHERE numerocliente = ?";

		PreparedStatement psBuscar = con.prepareStatement(sqlBuscar);
		psBuscar.setInt(1, numeroCliente);

		ResultSet rsBuscar = psBuscar.executeQuery();

		if (!rsBuscar.next()) {
			System.out.println("No existe el cliente con ese numero de cliente");
			rsBuscar.close();
			psBuscar.close();
			return;
		}

		rsBuscar.close();
		psBuscar.close();

		// Consultar el total de rutinas
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, numeroCliente);

		ResultSet rs = ps.executeQuery(); // executeQuery(), NO executeUpdate()

		if (rs.next()) {
			int cantidad = rs.getInt("total_rutinas"); // Obtener del ResultSet

			if (cantidad > 0) {
				System.out.println(
						"El cliente con numero de cliente " + numeroCliente + " tiene " + cantidad + " rutinas");
			} else {
				System.out.println("El cliente con numero de cliente " + numeroCliente + " no tiene rutinas");
			}
		}

		mostrar_Menu_Principal();

		rs.close();
		ps.close();
	}

	private static void registrar_Rutina() throws SQLException {
		String sql = "UPDATE cliente " + "SET rutinas = array_append(rutinas, ?) " + "WHERE numerocliente = ?";

		List<String> listaRutinas = new ArrayList<String>();
		int op = 0;

		// Verificar que el numero de cliente existe
		String sqlBuscar = "SELECT numerocliente FROM cliente WHERE numerocliente = ?";

		System.out.println("Introduce el numero de cliente:");
		int numeroCliente = Integer.parseInt(entrada.nextLine());

		PreparedStatement psBuscar = con.prepareStatement(sqlBuscar);
		psBuscar.setInt(1, numeroCliente);

		ResultSet rs = psBuscar.executeQuery();

		if (!rs.next()) {
			System.out.println("No existe el cliente con ese numero de cliente");
			rs.close();
			psBuscar.close();
			return;
		}

		rs.close();
		psBuscar.close();

		// Añadir rutinas
		do {
			System.out.println("Introduce el nombre de la rutina:");
			String rutina = entrada.nextLine();
			listaRutinas.add(rutina);

			System.out.println("¿Quieres añadir más rutinas?");
			System.out.println("1. SI");
			System.out.println("2. NO");
			op = Integer.parseInt(entrada.nextLine());

		} while (op == 1);

		// Inserta cada rutina una por una con array_append
		PreparedStatement psUpdate = con.prepareStatement(sql);

		for (String rutina : listaRutinas) {
			psUpdate.setString(1, rutina); // setString, NO setArray
			psUpdate.setInt(2, numeroCliente);
			psUpdate.executeUpdate();
		}

		psUpdate.close();

		System.out.println("Se han añadido " + listaRutinas.size() + " rutinas correctamente");
		mostrar_Menu_Principal();
	}

	/* =============== MENU CARGAR DATOS ===================== */

	private static void cargar_Datos() {

		int inse = 0;

		do {
			try {
				System.out.println("¿Que quieres insertar?");
				System.out.println("1. Entrenadores");
				System.out.println("2. Clientes");
				inse = Integer.parseInt(entrada.nextLine());

				if (inse == 1) {
					cargar_Entrenadores_CSV();
				} else {
					cargar_Clientes_JSON();
				}
			} catch (NumberFormatException e) {
				System.out.println("Inserte un numero");
			}

		} while (inse != 1 || inse != 2);

	}

	/* ======== ======= CARGAR CLIENTES JSON ================= */

	private static void cargar_Clientes_JSON() {
		System.out.println("Ingrese la ruta del archivo JSON:");

		int insertados = 0;
		int duplicados = 0;
		int errores = 0;

		try {
			// Leer el archivo JSON completo
			String contenido = new String(Files.readAllBytes(Paths.get(CLIENTES_JSON)));
			JSONArray clientes = new JSONArray(contenido);

			for (int i = 0; i < clientes.length(); i++) {
				JSONObject cliente = clientes.getJSONObject(i);

				String dni = cliente.getString("dni");
				String nombre = cliente.getString("nombre");
				String apellidos = cliente.getString("apellidos");
				int numeroCliente = cliente.getInt("numerocliente");

				// Verificar si el DNI ya existe
				if (existeDNI_Cliente(dni)) {
					System.out.println("El cliente con DNI " + dni + " ya existe. Saltando...");
					duplicados++;
					continue;
				}

				// Verificar si el número de cliente ya existe
				if (existeNumeroCliente(numeroCliente)) {
					System.out.println("El número de cliente " + numeroCliente + " ya existe. Saltando...");
					duplicados++;
					continue;
				}

				// Obtener rutinas si existen
				JSONArray rutinasArray = cliente.optJSONArray("rutinas");

				try {
					if (rutinasArray != null && rutinasArray.length() > 0) {
						// Cliente con rutinas
						String[] rutinas = new String[rutinasArray.length()];
						for (int j = 0; j < rutinasArray.length(); j++) {
							rutinas[j] = rutinasArray.getString(j);
						}

						String sql = "INSERT INTO public.cliente(dni, nombre, apellidos, numerocliente, rutinas) "
								+ "VALUES (?, ?, ?, ?, ?)";

						PreparedStatement ps = con.prepareStatement(sql);
						ps.setString(1, dni);
						ps.setString(2, nombre);
						ps.setString(3, apellidos);
						ps.setInt(4, numeroCliente);
						ps.setArray(5, con.createArrayOf("text", rutinas));

						int resultado = ps.executeUpdate();

						if (resultado > 0) {
							System.out.println("✓ Cliente insertado: " + nombre + " " + apellidos + " (con "
									+ rutinas.length + " rutinas)");
							insertados++;
						}
						ps.close();

					} else {
						// Cliente sin rutinas
						String sql = "INSERT INTO public.cliente(dni, nombre, apellidos, numerocliente) "
								+ "VALUES (?, ?, ?, ?)";

						PreparedStatement ps = con.prepareStatement(sql);
						ps.setString(1, dni);
						ps.setString(2, nombre);
						ps.setString(3, apellidos);
						ps.setInt(4, numeroCliente);

						int resultado = ps.executeUpdate();

						if (resultado > 0) {
							System.out.println("Cliente insertado: " + nombre + " " + apellidos + " (sin rutinas)");
							insertados++;
						}
						ps.close();
					}

				} catch (SQLException e) {
					System.out.println("Error al insertar cliente con DNI " + dni + ": " + e.getMessage());
					errores++;
				}
			}

			// Resumen
			System.out.println("\n===== RESUMEN DE CARGA =====");
			System.out.println("Clientes insertados: " + insertados);
			System.out.println("Duplicados omitidos: " + duplicados);
			System.out.println("Errores: " + errores);

		} catch (Exception e) {
			System.out.println("Error al procesar el archivo JSON: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static boolean existeDNI_Cliente(String dni) throws SQLException {
		String sql = "SELECT COUNT(*) FROM cliente WHERE dni = ?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		}

		return false;
	}

	private static boolean existeNumeroCliente(int numeroCliente) throws SQLException {
		String sql = "SELECT COUNT(*) FROM cliente WHERE numerocliente = ?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, numeroCliente);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		}

		return false;
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
