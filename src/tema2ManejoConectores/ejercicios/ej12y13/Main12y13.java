package tema2ManejoConectores.ejercicios.ej12y13;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main12y13 {

	private static final String SCRIPT_INGREDIENTE = "ingrediente.sql";
	private static final String SCRIPT_ORIGEN = "origen.sql";
	private static final String SCRIPT_RECETA = "receta.sql";
	private static final String SCRIPT1 = "consulta1.sql";
	private static final String SCRIPT2 = "consulta2.sql";

	private static Connection con = Conexion1213.conectar();

	public static void main(String[] args) throws FileNotFoundException {

		int opcion = 0;
		Scanner entrada = new Scanner(System.in);

	//	insertarDatos(SCRIPT_ORIGEN);
	//	insertarDatos(SCRIPT_RECETA);
	//	insertarDatos(SCRIPT_INGREDIENTE);

		do {
			mostrarMenu();
			opcion = Integer.parseInt(entrada.nextLine());
			operarMenu(opcion);
		} while (opcion != 11);

	}

	private static void operarMenu(int opcion) {

		switch (opcion) {
		case 1:
			listar_Recetas(SCRIPT1);
			break;
		case 2:
			recetas_Espania(SCRIPT2);
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

			break;
		case 8:

			break;
		case 9:

			break;
		case 10:

			break;

		default:
			break;
		}

	}

	private static void recetas_Espania(String scripT) {
		
		//esto esta mal
		ResultSet rs;
		Statement stmt;
		try {
			stmt = con.createStatement();
			BufferedReader br = new BufferedReader(new FileReader(scripT));

			String linea;
			String script = new String();
			while ((linea = br.readLine()) != null) {
				script += linea;

			}

			System.out.println(script);

			rs = stmt.executeQuery(script);

			while (rs.next()) {
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
				System.out.println(rs.getInt(4));
				System.out.println("------");
			}

			stmt.close();
			br.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void listar_Recetas(String archivo) {
		ResultSet rs;
		Statement stmt;
		try {
			stmt = con.createStatement();
			BufferedReader br = new BufferedReader(new FileReader(archivo));

			String linea;
			String script = new String();
			while ((linea = br.readLine()) != null) {
				script += linea;

			}

			System.out.println(script);

			rs = stmt.executeQuery(script);

			while (rs.next()) {
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
				System.out.println(rs.getInt(4));
				System.out.println("------");
			}

			stmt.close();
			br.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void mostrarMenu() {
		System.out.println("1: Consulta básica para listar todas las recetas y su tipo.\r\n"
				+ "2: Selecciona todas las recetas cuyo origen sea España.\r\n"
				+ "3: Obtén las recetas que tienen como ingrediente Tomate.\r\n"
				+ "4: Cuenta el número de recetas de cada tipo.\r\n"
				+ "5: Ordena las recetas alfabéticamente por nombre.\r\n"
				+ "6: Encuentra recetas que contienen “sopa” en su nombre.\r\n"
				+ "7: Cambia la región de origen de la receta Sopa de ave a “Cataluña”.\r\n"
				+ "8: Elimina la receta Vichyssoise de la base de datos.\r\n"
				+ "9: Lista todas las recetas y sus ingredientes en una sola consulta.\r\n"
				+ "10: Calcula el número total de ingredientes únicos utilizados en todas las recetas.\r\n"
				+ "Ejercicio");
		System.out.println("------------------");
	}

	private static void insertarDatos(String archivo) {
		try {
			Statement stmt = con.createStatement();

			BufferedReader br = new BufferedReader(new FileReader(archivo));

			String linea;
			String script = new String();
			while ((linea = br.readLine()) != null) {
				script += linea;
			}

			stmt.execute(script);

			System.out.println("Datos " + archivo + " insertados correctamente");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
