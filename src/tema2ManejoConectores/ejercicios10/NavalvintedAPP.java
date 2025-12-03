package tema2ManejoConectores.ejercicios10;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class NavalvintedAPP {

	private static final int OPCION_SALIR = 6;
	private static Connection con = ConexionNava.conectar();
	private static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {

		int op = 0;

		do {

			mostrarMenu();
			op = Integer.parseInt(entrada.nextLine());

			operarmenu(op);

		} while (op != OPCION_SALIR);

		if (con != null) {
			try {
				con.close();
				System.out.println("Conexion cerrada.");
				System.out.println("--");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	private static void operarmenu(int op) {
		switch (op) {
		case 1:
			productoPorcategorias();
			break;
		case 2:
			procutosPorTallas();
			break;
		case 3:

			break;
		case 4:

			break;
		case 5:

			break;
		case 6:

			break;

		default:
			break;
		}

	}

	private static void procutosPorTallas() {
		String talla = "";
		System.out.println("Introduce la talla");
		talla = entrada.nextLine().toUpperCase();
		
		String consulta = "SELECT nombre_Producto, estado, precio, t.talla "
                + "FROM producto p "
                + "JOIN talla t ON p.id_Talla = t.id_Talla "
                + "WHERE t.talla = ?;";

		
		try {
			PreparedStatement ps = con.prepareStatement(consulta);
			
			ps.setString(1, talla);

			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				System.out.println("Talla: "+rs.getString("talla"));
				System.out.println("Nombre: "+rs.getString("nombre_Producto"));
				System.out.println("Estado "+rs.getString("estado"));
				System.out.println("Precio "+rs.getDouble("precio"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	private static void productoPorcategorias() {
		String categoria = "";
		System.out.println("Introduce la categoria");
		categoria = entrada.nextLine();

		String consulta = "select * from producto p join categoria c "
				+ "on c.id_categoria = p.id_categoria  where categoria = ?";

		try {
			PreparedStatement ps = con.prepareStatement(consulta);

			ps.setString(1, categoria);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("Categoria: " + rs.getString("categoria"));
				System.out.println("ID Producto: " + rs.getInt("id_Producto"));
				System.out.println("Nombre: " + rs.getString("nombre_Producto"));
				System.out.println("Estado: " + rs.getString("estado"));
				System.out.println("Precio: " + rs.getString("precio"));
				System.out.println("-----------------------");
			}
			
			rs.close();
			ps.close();
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void mostrarMenu() {
		System.out.println("------ PROGRAMA NAVAVINTED -------");
		System.out.println("1. Producto por categorias");
		System.out.println("2. Productos por tallas");
		System.out.println("3. Nuevo producto");
		System.out.println("4. Calcular precio final");
		System.out.println("5. Vender un producto");
		System.out.println("6. Salir");
		System.out.println("Elige una opción:");

	}

}
