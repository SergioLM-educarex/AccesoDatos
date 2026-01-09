package tema3PostgreSQL.ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tema3PostgreSQL.ConexionMascotasDB;



public class Ejercicio4_Select {

	public static void main(String[] args) {
		
		Connection conn = ConexionMascotasDB.getConexion();
		
		
		String sql = "SELECT * FROM mascota";

		try {
		    Statement st = conn.createStatement();
		    System.out.println("----");
		    ResultSet rs = st.executeQuery(sql);

		    while (rs.next()) {
		        // Ejemplo de columnas
		        System.out.println(
		            rs.getInt("id") + " - " +
		            rs.getString("nombre")+" - "+
		            rs.getString("especie")+" - "+
		            rs.getString("raza")
		        );
		    }
		    
		    System.out.println("Listo");

		    rs.close();
		    st.close();

		} catch (SQLException e) {
		    e.printStackTrace();
		}


	}

	private static Connection conexion_mascotasDB() {

		Connection conexion = null;

		try {
			// Cargamos el driver
			Class.forName("org.postgresql.Driver");
			// Establecemoslaconexión
			conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mascotasbd", "postgres", "toor");

			System.out.println("Conectado a MascotasBD");
			return conexion;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}