package tema3PostgreSQL.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionPostgreSQL {

	public static void main(String[] args) {
		
		conexion_DB();

	}

	private static void conexion_DB() {
		try {
			//Cargamos el driver
			Class.forName("org.postgresql.Driver");
			//Establecemoslaconexión
			Connection conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mascotasbd",
			"postgres", "toor");
			
			
			System.out.println("Conectado a MascotasBD");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
