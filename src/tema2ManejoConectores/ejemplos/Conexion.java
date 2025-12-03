package tema2ManejoConectores.ejemplos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	
	private static final String URL = "jdbc:mysql://localhost:3306/ejemplo";
	private static final String USER = "root"; // Usuario
	private static final String PASSWORD = ""; // Contrasenia
	
	public static Connection conectar() {
		Connection conexion = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Establecer la conexion
			conexion = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Conexi�n exitosa a la base de datos 'test'");
		} catch (ClassNotFoundException e) {
			System.out.println("Error: No se encontr� el driver de MySQL.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error: No se pudo conectar a la base de datos.");
			e.printStackTrace();
		}
		return conexion;
	}
}
