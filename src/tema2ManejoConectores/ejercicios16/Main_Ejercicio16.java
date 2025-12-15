package tema2ManejoConectores.ejercicios16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main_Ejercicio16 {

	private static final String CREAR_TABLA_PROFESOR_SQL = "crearTablaProfesor.sql";
	private static final String CREAR_TABLA_IMPARTE = "crearTablaImparte.sql";

	public static void main(String[] args) {
		
		Connection conexion = ConexionAlumnos16.conectar();
		
		
		insertar_Tabla(conexion, CREAR_TABLA_PROFESOR_SQL);
		insertar_Tabla(conexion, CREAR_TABLA_IMPARTE);
		
		
	}

	private static void insertar_Tabla(
			Connection conexion, String sql) {
	
		try {
			Statement st = conexion.createStatement();
			String script = null;
			
			script = Files.readString(Paths.get(sql));
			System.out.println(script);
			
			st.execute(script);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void insertar_Tabla_Profesor(Connection conexion) {
		try {
			Statement st = conexion.createStatement();
			String script = null;
			
			script = Files.readString(Paths.get(CREAR_TABLA_PROFESOR_SQL));
			System.out.println(script);
			
			st.execute(script);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
