package tema3PostgreSQL.examenV2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionV2 {

    private static final String URL = "jdbc:postgresql://localhost:5432/bd_sergio_leon_mateos_gimnasio";
    private static final String USER = "postgres";
    private static final String PASSWORD = "toor";

    public static Connection getConexion() {
       
    	Connection conexion = null;

        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conectado a bd_sergio_leon_mateos_gimnasio");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Driver no encontrado");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error de conexión a la BD");
            e.printStackTrace();
        }

        return conexion;
    }
}
