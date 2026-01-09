package tema3PostgreSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMascotasDB {

    private static final String URL = "jdbc:postgresql://localhost:5432/mascotasbd";
    private static final String USER = "postgres";
    private static final String PASSWORD = "toor";

    public static Connection getConexion() {
       
    	Connection conexion = null;

        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conectado a MascotasBD");
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
