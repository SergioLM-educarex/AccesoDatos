package tema2ManejoConectores.ejercicios.ejde1a5alumnos.ej2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionEJ3 {

	public static void main(String[] args) {
		
		Connection con = ConexionEJ2.conectar();
		
 		
		Statement sentencia;
		try {
			sentencia = con.createStatement();
			ResultSet resultado = sentencia.executeQuery ("select * from alumno");
			System.out.println ("id \t \t Nombre \tCurso");
			
			while (resultado.next()) {
				System.out.println(resultado.getInt(1)+"\t\t"+
			resultado.getString(2)+"\t\t"+resultado.getString(3));
			}
			resultado.close();
			sentencia.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
