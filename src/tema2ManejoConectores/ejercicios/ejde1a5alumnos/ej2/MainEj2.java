package tema2ManejoConectores.ejercicios.ejde1a5alumnos.ej2;

import java.sql.Connection;
import java.sql.SQLException;

public class MainEj2 {

	public static void main(String[] args) {

		Connection conexion = ConexionEJ2.conectar();
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
