package tema2ManejoConectores.examen.ex2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionAve {
	private static final String URL = "jdbc:mysql://localhost:3306/avesbd?allowMultiQueries=true";
	private static final String USER = "root"; // Usuario
	private static final String PASSWORD = ""; // Contrasenia
	
	public static Connection conectar() {
		Connection conexion = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Establecer la conexion
			conexion = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Conexion exitosa a la base de datos 'avesBD'");
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
