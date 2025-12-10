package tema2ManejoConectores.ejercicios.ej12y13;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main12y13 {

	public static void main(String[] args) throws FileNotFoundException {


		Connection con = Conexion1213.conectar();
		
		try {
			Statement stmt = con.createStatement();
			
			BufferedReader br = new BufferedReader(new FileReader("ingrediente.sql"));
			
			String linea;
			String script = new String();
			while ((linea=br.readLine())!=null) {
				script+=linea;
			}
			
			stmt.execute(script);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		

	}

}
