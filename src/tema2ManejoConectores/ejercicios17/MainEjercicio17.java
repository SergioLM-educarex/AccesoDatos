package tema2ManejoConectores.ejercicios17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.ServerPreparedStatement;

public class MainEjercicio17 {

	private static final String SEPARADOR = ";";
	private static final String NAVAVINTED_CSV = "navavinted.csv";
	public static Connection con = ConexionEJ17.conectar();

	
	public static void main(String[] args) {
		
	
		try {
			BufferedReader br = new BufferedReader(new FileReader(NAVAVINTED_CSV));
			String [] datosTroc = null;
			String linea = "";
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO producto values (?,?,?,?,?,?,?,?,?,?,?) ");
			
			while ((linea=br.readLine())!=null) {
				
				datosTroc=linea.split(SEPARADOR);
				int id_Producto = Integer.parseInt(datosTroc[0]);
				String nombre_producto = datosTroc[1];
				int id_categoria = Integer.parseInt(datosTroc[2]);
				int id_Talla = Integer.parseInt(datosTroc[3]);
				int id_color = Integer.parseInt(datosTroc[4]);
				int id_material = Integer.parseInt(datosTroc[5]);
				int stock = Integer.parseInt(datosTroc[6]);
				double precio = Double.parseDouble(datosTroc[7]);
				double costo = Double.parseDouble(datosTroc[8]);
				String estado= datosTroc[9];
				int descuento = Integer.parseInt(datosTroc[10]);
				
				ps.setInt(1, id_Producto);
				ps.setString(2, nombre_producto);
				ps.setInt(3, id_categoria);
				ps.setInt(4, id_Talla);
				ps.setInt(5, id_color);
				ps.setInt(6, id_material);
				ps.setInt(7, stock);
				ps.setDouble(8, id_Producto);
				ps.setDouble(9, id_Producto);
				ps.setString(10, estado);
				ps.setInt(11, descuento);
				
				
				ps.execute();
		
				
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
