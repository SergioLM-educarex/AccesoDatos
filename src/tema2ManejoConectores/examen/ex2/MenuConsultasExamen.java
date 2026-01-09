package tema2ManejoConectores.examen.ex2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MenuConsultasExamen {

	private static final int OPCION_SALIR = 7;
	private static final String SEL_ENVERGADURA = "consultaEnvergadura.sql";
	private static Connection conn = ConexionAve.conectar();

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
			mostrar_aves_Habitat();
			break;

		case 3:
			consultar_Envergadura();
			break;

		case 4:
			mostrar_Max_Envergadura();
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

	private static void mostrar_Max_Envergadura() {
		try {
			CallableStatement stmt = conn.prepareCall("{?=CALL maxEnvergadura()}");
			
			stmt.registerOutParameter(1, java.sql.Types.INTEGER);
			
			stmt.executeUpdate();
			
			int envergadura = stmt.getInt(1);
			
			System.out.println("El maximo de envergadura es de "+ envergadura);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void consultar_Envergadura() {
		System.out.println("Ingrese una envergadura: ");
		int envergadura = Integer.parseInt(entrada.nextLine());

		String sql;
		try {
			sql = Files.readString(Paths.get(SEL_ENVERGADURA));

			PreparedStatement ps;

			ps = conn.prepareStatement(sql);
			ps.setInt(1, envergadura);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("ID: " + rs.getInt("id_ave") + "\tNombre ave: " + rs.getString("nombre_comun"));

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void mostrar_aves_Habitat() {

		int op = 0;
		String habitat = "";

		String sql = "Select id_ave, nombre_comun from ave where habitat=?";

		do {
			System.out.println("Seleccione el habitat");
			System.out.println("1. Dehesas");
			System.out.println("2. Montaña");
			System.out.println("3. Humedales");
			System.out.println("4. Bosques");
			System.out.println("5. Pirineos");
			System.out.println("6. Acantilados");
			System.out.println("7. Zonas abiertas");
			op = Integer.parseInt(entrada.nextLine());

			switch (op) {
			case 1:
				habitat = "Dehesas";
				break;
			case 2:
				habitat = "Zonas Montañosas";
				break;
			case 3:
				habitat = "Humedales";
				break;
			case 4:
				habitat = "Bosques atlánticos";
				break;
			case 5:
				habitat = "Pirineos";
				break;
			case 6:
				habitat = "Acantilados";
				break;
			case 7:
				habitat = "Zonas abiertas";
				break;

			default:
				System.out.println("Opcion no valida, respita la operacion");
				break;
			}

		} while (op < 1 || op > 7);

		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, habitat);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("ID: " + rs.getInt("id_ave") + "Nombre ave: " + rs.getString("nombre_comun"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void mostrar_Total_Aves() {

		String sql = "SELECT * FROM ave";

		try {
			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {

				System.out.println("ID: " + rs.getInt("id_ave") + "\nNombre Comun: " + rs.getString("nombre_comun")
						+ "\nNombre Cientifico: " + rs.getString("nombre_cientifico") + "\nHabitat:"
						+ rs.getNString("Habitat") + "\nEnvergadura: " + rs.getInt("envergadura_cm")
						+ "\nEstado de conservacion: " + rs.getString("estado_conservacion"));

				System.out.println("-----------------");

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void mostrar_Menu() {
		System.out.println("-------------");
		System.out.println("====== Menu operaciones ======");
		System.out.println("1.Mostrar todas las aves" + "\n" + "2.Mostrar aves por hábitat" + "\n"
				+ "3.Mostrar aves con una envergadura mayor a un valor indicado" + "\n"
				+ "4.Mostrar el ave con mayor envergadura" + "\n"
				+ "5.Mostrar la envergadura media por estado de conservación" + "\n" + "6.Exportar datos" + "\n"
				+ "7.Salir");
	}
}
