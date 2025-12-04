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
			crearProducto();
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

	private static void crearProducto() {

		String nombreProducto, estado;
		int categoria = 0, talla = 0, id_color = 0, id_Material=0, stock=0, descuento=0;
		double precio, costo;
		

		System.out.println("Introduce el nombre del producto");
		nombreProducto = entrada.nextLine();

		categoria = pedirCategoria();

		talla = elegir_Talla();

		id_color = pedir_Color();

		id_Material = pedir_Material();
		
		System.out.println("Inserte stock:");
		stock= Integer.parseInt(entrada.nextLine());
		
		
		System.out.println("Inserte el precio del producto");
		precio= Double.parseDouble(entrada.nextLine());
		
		System.out.println("Costo del producto");
		costo= Double.parseDouble(entrada.nextLine());
	
		System.out.println("Estado del producto:");
		estado= entrada.nextLine();
		
		System.out.println("Escribe el descuento que tiene");
		descuento = Integer.parseInt(entrada.nextLine());
		
		String insercion = "INSERT INTO producto (nombre_Producto, id_categoria, id_Talla, id_Color,"
				+ "id_Material, stock, precio, costo, estado, descuento)VALUES(?,?,?,?,?,?,?,?,?,?);";
		
		try {
			PreparedStatement ps = con.prepareStatement(insercion);
			ps.setString(1, nombreProducto);
			ps.setInt(2, categoria);
			ps.setInt(3, talla);
			ps.setInt(4, id_color);
			ps.setInt(5, id_Material);
			ps.setInt(6, stock);
			ps.setDouble(7, precio);
			ps.setDouble(8, costo);
			ps.setString(9, estado);
			ps.setInt(10, descuento);
			
		
			
			// Ejecutamos la inserción
			int filasInsertadas = ps.executeUpdate();
			if (filasInsertadas > 0) {
			System.out.println("Insertado "+
					ps.getUpdateCount()+" registros");
			}
			
			
			
			
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

	private static int pedir_Material() {
		int id_Material = 0;
		do {
			System.out.println("1. Algodon");
			System.out.println("2. Mezclilla");
			System.out.println("3. Poliester");
			System.out.println("4. Lino");
			System.out.println("5. Sintetico");
			System.out.println("6. Cuero");
		
			id_Material = Integer.parseInt(entrada.nextLine());
			if (id_Material < 1 || id_Material > 6) {
				System.out.println("Opcion no válida, repita la operacion");
			}

		} while (id_Material < 1 || id_Material > 6);
		
		return id_Material;
	}

	private static int pedir_Color() {
		int id_color = 0;
		do {
			System.out.println("1. Azul");
			System.out.println("2. Negro");
			System.out.println("3. Rojo");
			System.out.println("4. Gris");
			System.out.println("5. Verde");
			System.out.println("6. Blanco");
			System.out.println("7. Beige");
			System.out.println("8. Marron");
			id_color = Integer.parseInt(entrada.nextLine());
			if (id_color < 1 || id_color > 8) {
				System.out.println("Opcion no válida, repita la operacion");
			}

		} while (id_color < 1 || id_color > 8);
		
		return id_color;
	}

	private static int elegir_Talla() {

		int talla = 0;
		do {
			System.out.println("Elije la talla");
			System.out.println("1. S");
			System.out.println("2. M");
			System.out.println("3. L");
			System.out.println("4. XL");
			System.out.println("5. 42");
			System.out.println("6. 39");
			talla = Integer.parseInt(entrada.nextLine());
			if (talla < 1 || talla > 6) {
				System.out.println("Opcion no válida, repita la operacion");
			}

		} while (talla < 1 || talla > 6);

		return talla;
	}

	private static int pedirCategoria() {
		int categoria;

		do {
			System.out.println("Elija la categoria:");
			System.out.println("1. Camisas:");
			System.out.println("2. Pantalones");
			System.out.println("3. Vestidos");
			System.out.println("4. Abrigos");
			System.out.println("5. Faldas");
			System.out.println("6. Calzado");
			categoria = Integer.parseInt(entrada.nextLine());

			if (categoria < 1 || categoria > 6) {
				System.out.println("Opcion no válida, repita la operacion");
			}

		} while (categoria < 1 || categoria > 6);

		return categoria;
	}

	private static void procutosPorTallas() {
		String talla = "";
		System.out.println("Introduce la talla");
		talla = entrada.nextLine().toUpperCase();

		String consulta = "SELECT nombre_Producto, estado, precio, t.talla " + "FROM producto p "
				+ "JOIN talla t ON p.id_Talla = t.id_Talla " + "WHERE t.talla = ?;";

		try {
			PreparedStatement ps = con.prepareStatement(consulta);

			ps.setString(1, talla);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("Talla: " + rs.getString("talla"));
				System.out.println("Nombre: " + rs.getString("nombre_Producto"));
				System.out.println("Estado " + rs.getString("estado"));
				System.out.println("Precio " + rs.getDouble("precio"));
			}

			if (!rs.next()) {
				System.out.println("No existe la talla");
			}

			rs.close();
			ps.close();
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

			if (!rs.next()) {
				System.out.println("No existe la talla");
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
