package tema2ManejoConectores.ejemplos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main_ConexionDB {

	

	public static void main(String[] args) {
		// Llamar al método conectar para establecer la conexión
		Connection conexion = Conexion.conectar();
		// Cerrar la conexión después de usarla
		if (conexion != null) {
			try {
				conexion.close();
				System.out.println("Conexión cerrada.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	

}