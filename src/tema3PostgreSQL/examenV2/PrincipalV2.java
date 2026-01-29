package tema3PostgreSQL.examenV2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalV2 {

	private static Scanner entrada = new Scanner(System.in);
	private static Connection con = ConexionV2.getConexion();

	public static void main(String[] args) {

		int op = 0;
		do {
			mostrar_Menu_Principal();
			op = Integer.parseInt(entrada.nextLine());
			operar_Menu_Principal(op);

		} while (op != 5);

	}

	/* =================== MOSTRAR MENU PRINCIPAL ============= */

	private static void mostrar_Menu_Principal() {

		System.out.println("================");
		System.out.println("Examen GIMNASIO");
		System.out.println("===============");
		System.out.println("Elije una opcion:");
		System.out.println("1. Alta");
		System.out.println("2. Cargar datos");
		System.out.println("3. Gestion de rutinas clientes");
		System.out.println("4. Gestion salario");
		System.out.println("5. Salir");

	}

	/* =================== OPERAR MENU PRINCIPAL ============= */
	private static void operar_Menu_Principal(int op) {
		switch (op) {
		case 1:
			altas();
			break;

		case 2:

			break;
		case 3:
			gestion_Rutinas_Clientes();
			break;
		case 4:
			gestion_Salarios();
			break;
		case 5:
			System.out.println("Saliendo....");
			break;

		default:
			System.out.println("Opcion no valida");
			break;
		}
	}

	/* =================== ***** 4. GESTION SALARIO ***** ============= */
	private static void gestion_Salarios() {

		int op = 0;
		do {

			try {
				mostrar_Menu_Gestion_Salarios();
				op = Integer.parseInt(entrada.nextLine());
				operar_Menu_Gestion_Salarios(op);
			} catch (NumberFormatException e) {
				System.out.println("Ingrese un numero");
			}

		} while (op < 1 || op > 3);
	}

	/* =================== MENU GESTION SALARIO ============= */
	private static void mostrar_Menu_Gestion_Salarios() {
		;
		System.out.println("/*/*/*/Gestion de salario/*/*/*//*/");

		System.out.println("Elije una opcion:");
		System.out.println("1. Calcular el salario de un entrenador");
		System.out.println("2. Aumentar en 2% ");
		System.out.println("3. Volver al menu Principal");

	}

	/* =================== OPERAR GESTION SALARIO ============= */
	private static void operar_Menu_Gestion_Salarios(int op) {
		switch (op) {
		case 1:
			calcular_salario_Entrenador();
			break;

		case 2:
			subir_Salario_Entrenadores();
			break;

		case 3:
			mostrar_Menu_Principal();
			break;

		default:
			System.out.println("Opcion no valida");
			break;
		}

	}
	/* =================== SUBIR SALARIO ENTRENADORES ============= */
	private static void subir_Salario_Entrenadores() {
		/*
		 * UPDATE entrenador
		SET salario = ROW((salario).salariobase*1.02, (salario).comision)::pago;
		 */
		
		String llamada = "CALL subir_salarios";
		
		try {
			CallableStatement cs = con.prepareCall(llamada);
			
			int resultado = cs.executeUpdate();
			
			if (resultado>=0) {
				System.out.println("subidos ");
			}else {
				System.out.println("no subidos");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/* =================== OPERAR GESTION SALARIO ============= */
	private static void calcular_salario_Entrenador() {
		String sql = "select nombre, (salario).cuota_base+(salario).comision as salariototal \r\n"
				+ "from entrenador where dni = ? ;";

		String dni = pedirDni();

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, dni);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String nombre = rs.getString("nombre");
				int salarioTotal = rs.getInt("salariototal");

				System.out.println("Entrenador: " + nombre);
				System.out.println("Salario total: " + salarioTotal);
			} else {
				System.out.println("No existe ningún entrenador con ese DNI");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* =================== ***** 3. GESTION RUTINAS CLIENTES ***** ============= */
	private static void gestion_Rutinas_Clientes() {
		int op = 0;
		do {

			try {
				mostrar_Menu_Gestion();
				op = Integer.parseInt(entrada.nextLine());
				operar_Menu_Gestion(op);
			} catch (NumberFormatException e) {
				System.out.println("Ingrese un numero");
			}

		} while (op < 1 || op > 3);

	}

	/* =================== OPERAR GESTION RUTINAS CLIENTES ============= */
	private static void operar_Menu_Gestion(int op) {

		switch (op) {
		case 1:
			registrar_Rutina_Cliente();
			break;

		case 2:
			total_Rutinas_Cliente();
			break;

		case 3:
			mostrar_Menu_Principal();
			break;

		default:
			System.out.println("Opcion no valida");
			break;
		}

	}

	private static void total_Rutinas_Cliente() {
		
		/* OBTENER DE UN ARRAY UN CONTEO ------------> IMPORTANTE
		 * SELECT array_length(rutinas, 1)
				FROM cliente
				WHERE dni = p_dni;
		 */

		String sql = "{? = CALL contar_rutinas(?)}";

		String dni = pedirDni();

		try {
			CallableStatement cs = con.prepareCall(sql);

			cs.registerOutParameter(1, Types.INTEGER);

			cs.setString(2, dni);

			int resultado = cs.executeUpdate();

			if (resultado >= 0) {
				System.out.println("el resultado de las rutians con dni " + dni + " es " + cs.getInt(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String pedirDni() {
		System.out.println("Dni");
		String dni = entrada.nextLine();
		return dni;
	}

	private static void registrar_Rutina_Cliente() {

		System.out.println("Ingrese el dni para meter la nueva rutina");
		String dni = entrada.nextLine();
		List<String> rutinasAdd = new ArrayList<String>();
		String buscarSql = "SELECT dni FROM cliente WHERE dni = ?";
		int op = 0;

		String sql = "UPDATE cliente " + "SET rutinas = array_append(rutinas, ?) " + "WHERE dni = ?;";

		try {
			PreparedStatement ps = con.prepareStatement(buscarSql);

			ps.setString(1, dni);

			ResultSet rs = ps.executeQuery();

			if (!rs.next()) {
				System.out.println("No existe el cliente con ese numero de cliente");
				rs.close();
				ps.close();
				return;
			}

			// Añadir rutinas
			do {
				System.out.println("Introduce el nombre de la rutina:");
				String rutina = entrada.nextLine();
				rutinasAdd.add(rutina);

				System.out.println("¿Quieres añadir más rutinas?");
				System.out.println("1. SI");
				System.out.println("2. NO");
				op = Integer.parseInt(entrada.nextLine());

			} while (op == 1);

			PreparedStatement psUpdate = con.prepareStatement(sql);

			for (String rutina : rutinasAdd) {
				psUpdate.setString(1, rutina); // setString, NO setArray
				psUpdate.setString(2, dni);
				psUpdate.executeUpdate();
			}

			psUpdate.close();

			System.out.println("Se han añadido " + rutinasAdd.size() + " rutinas correctamente");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* =================== MOSTRAR MENU GESTION ============= */
	private static void mostrar_Menu_Gestion() {
		System.out.println("/*/*/*/*/* MENU GESTION */*/*/*/");
		System.out.println("Elije una opcion:");
		System.out.println("1. Registrar rutina para un cliente");
		System.out.println("2. Total rutinas de un cliente (servidor BD)");
		System.out.println("3. Volver al menu principal");

	}

	/* =================== ***** ALTAS***** ==================== */
	private static void altas() {
		int op = 0;
		do {

			try {
				mostrar_Menu_Altas();
				op = Integer.parseInt(entrada.nextLine());
				operar_Menu_Altas(op);
			} catch (NumberFormatException e) {
				System.out.println("Ingrese un numero");
			}

		} while (op < 1 || op > 3);

	}

	/* =================== MENU ALTAS ============= */
	private static void mostrar_Menu_Altas() {

		System.out.println("/*/*/*/*/* MENU ALTAS */*/*/*/");
		System.out.println("Elije una opcion:");
		System.out.println("1. Agregar cliente");
		System.out.println("2. Agregar entrenador");
		System.out.println("3. Volver al menu principal");

	}

	/* =================== OPERAR ALTAS ============= */
	private static void operar_Menu_Altas(int op) {
		switch (op) {
		case 1:
			agregar_Cliente();
			break;

		case 2:
			agregar_Entrenador();
			break;

		case 3:
			mostrar_Menu_Principal();
			break;

		default:
			System.out.println("Opcion no valida");
			break;
		}

	}

	/* =================== 2. ALTA ENTRENADOR ============= */
	private static void agregar_Entrenador() {

		String sqlEntrenador = "INSERT INTO public.entrenador(" + "	dni, nombre, apellidos, especialidad, salario)"
				+ "	VALUES (?, ?, ?, ?, ROW(?,?));";
		String dni, nombre, apellidos, especialidad;

		System.out.println("Nombre");
		nombre = entrada.nextLine();

		System.out.println("Apellidos");
		apellidos = entrada.nextLine();

		System.out.println("DNI:");
		dni = entrada.nextLine();

		System.out.println("Especialidad:");
		especialidad = entrada.nextLine();

		System.out.println("Salario base");
		int salario = Integer.parseInt(entrada.nextLine());

		System.out.println("Comision");
		int comision = Integer.parseInt(entrada.nextLine());

		try {
			PreparedStatement ps = con.prepareStatement(sqlEntrenador);

			ps.setString(1, dni);
			ps.setString(2, nombre);
			ps.setString(3, apellidos);
			ps.setString(4, especialidad);
			ps.setInt(5, salario);
			ps.setInt(6, comision);

			int result = ps.executeUpdate();

			if (result > 0) {
				System.out.println("Insertado correctamente" + nombre + " " + apellidos);
			} else {
				System.out.println("Error al insertar en base de datos");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* =================== 1. AGREGAR CLIENTE ============= */
	private static void agregar_Cliente() {

		String sqlRutinas = "INSERT INTO public.cliente(dni, nombre, apellidos, numerocliente, rutinas)"
				+ "	VALUES (?, ?, ?, ?, ?);";

		String sqlsinRutinas = "INSERT INTO public.cliente(dni, nombre, apellidos, numerocliente)VALUES (?, ?, ?, ?);";

		List<String> listaRutinas = new ArrayList<String>();
		String dni, nombre, apellidos;
		String[] rutinas;
		int num_cliente, op;

		System.out.println("Nombre");
		nombre = entrada.nextLine();

		System.out.println("Apellidos");
		apellidos = entrada.nextLine();

		System.out.println("Numero cliente");
		num_cliente = Integer.parseInt(entrada.nextLine());

		System.out.println("DNI:");
		dni = entrada.nextLine();

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

		// Insertar sin rutinas
		if (listaRutinas.isEmpty()) {

			try {
				PreparedStatement ps = con.prepareStatement(sqlsinRutinas);

				ps.setString(1, dni);
				ps.setString(2, nombre);
				ps.setString(3, apellidos);
				ps.setInt(4, num_cliente);

				int result = ps.executeUpdate();

				if (result > 0) {
					System.out.println("Insertado correctamente" + nombre + " " + apellidos);
				} else {
					System.out.println("Error al insertar en base de datos");
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			PreparedStatement ps;
			try {
				ps = con.prepareStatement(sqlsinRutinas);
				rutinas = listaRutinas.toArray(new String[0]);
				ps = con.prepareStatement(sqlRutinas);
				ps.setString(1, dni);
				ps.setString(2, nombre);
				ps.setString(3, apellidos);
				ps.setInt(4, num_cliente);
				ps.setArray(5, con.createArrayOf("text", rutinas));
				int result = ps.executeUpdate();

				if (result > 0) {
					System.out.println("Insertado correctamente" + nombre + " " + apellidos);
				} else {
					System.out.println("Error al insertar en base de datos");
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
