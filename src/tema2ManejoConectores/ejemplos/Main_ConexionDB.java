package tema2ManejoConectores.ejemplos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import tema2ManejoConectores.ejercicios.ejercicio9.Conexion9;

public class Main_ConexionDB {

	

	public static void main(String[] args) {
		// Llamar al m�todo conectar para establecer la conexi�n
		Connection conexion = Conexion9.conectar();
		// Cerrar la conexi�n despu�s de usarla
		if (conexion != null) {
			try {
				conexion.close();
				System.out.println("Conexi�n cerrada.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	

}