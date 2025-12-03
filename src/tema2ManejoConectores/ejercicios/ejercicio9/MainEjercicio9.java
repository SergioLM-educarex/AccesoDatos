package tema2ManejoConectores.ejercicios.ejercicio9;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MainEjercicio9 {

	private static final int OPCION_SALIR = 6;

	private static Scanner entrada = new Scanner(System.in);
	private static Connection con = Conexion9.conectar();
	

	public static void main(String[] args) {

		
		int op = 0;
		
		do {

			mostrarMenu();
			op = Integer.parseInt(entrada.nextLine());

			operarmenu(op);

		} while (op != OPCION_SALIR);


		if (con != null) {
			try {
				con.close();
				System.out.println("Conexion cerrada.");
				System.out.println("--");
			} catch (SQLException e) {
				e.printStackTrace();
			}

			
		}
	}

	private static void operarmenu(int op) {

		switch (op) {
		case 1:
			try {
				mostrarAlumnosAsignaturas();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
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

		default:
			break;
		}

	}

		
	public static void mostrarAlumnosAsignaturas() throws SQLException {
	    String sql = "SELECT a.nombre AS alumno, m.nombre AS asignatura "
	               + "FROM alumno a JOIN modulo m ON a.id = m.idalumno";

	    System.out.println(sql); // DEBUG

	    try (Statement st = con.createStatement();
	         ResultSet rs = st.executeQuery(sql)) {
	       
	    	while (rs.next()) {
	            System.out.println(rs.getString("alumno") + " - " + rs.getString("asignatura"));
	        }
	    }
	}

	

	private static void mostrarMenu() {
		System.out.println("******* MENU EJERCICIO 9 *******");
		System.out.println("1. Mostrar nombre de Alumno de sus asignaturas");
		System.out.println("2. Muestra el nombre de las asignaturas y la nota de un alumno");
		System.out.println("3. Añadir a la tabla alumno el campo telefono de tipo entero");
		System.out.println("4. Añadir alumno");
		System.out.println("5. Rellenar telefono indicando el id de alumno");
		System.out.println("6. Salir");
	}
}
