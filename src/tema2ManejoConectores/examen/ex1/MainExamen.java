// MainExamen.java
package tema2ManejoConectores.examen.ex1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MainExamen {

	private static final String SEPARADOR = ";";
	private static final String BASE_DATOS_ZAPATON_TXT = "BaseDatosZapaton.txt";
	private static final int OPCION_SALIR = 6;
	private static final String ZAPATOS_JSON = "zapatos.json";
	private static final String TABLA_ZAPATOS_SQL = "scriptZapat.sql";

	private static Scanner entrada = new Scanner(System.in);
	private static Connection conn = ConexionExamen.conectar();
	private static ListaZapatos lista;

	public static void main(String[] args) {

		int opcion = 0;
		crear_Tabla_zapato();

		leer_Json();
		System.out.println("");

		// insertarDatos();
		do {
			mostrar_Menu();
			opcion = Integer.parseInt(entrada.nextLine());
			operar_Menu(opcion);
		} while (opcion != OPCION_SALIR);
		
		
		generar_Fichero_BaseDatos();

	}

	private static void generar_Fichero_BaseDatos() {
		
		String sql = "Select * from zapato";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(BASE_DATOS_ZAPATON_TXT)));
			
			String linea ;
			
			while (rs.next()) {
				
				int id = rs.getInt("id");
				String marca = rs.getString("marca");
				String modelo= rs.getString("modelo");
				String tamano= rs.getString("tamano");
				String color = rs.getString("color");
				int stock = rs.getInt("stock");
				double precio = rs.getDouble("precio");
				
				String idS = String.valueOf(id);
				String stockS= String.valueOf(stock);
				String precioS = String.valueOf(precio);
				
				linea = idS+SEPARADOR+marca+SEPARADOR+modelo+SEPARADOR+tamano+SEPARADOR
						+color+SEPARADOR+stockS+SEPARADOR+precio+SEPARADOR;
				
				bw.write(linea);
				bw.newLine();
				
			}
			
			bw.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	private static void operar_Menu(int opcion) {

		switch (opcion) {
		case 1:
			mostrar_Stock_Sub5();
			break;

		case 2:
			aumentar_Precio_Nike();
			break;
		case 3:
			aniadir_Campo_Descripcion();
			break;
		case 4:
			mostrar_Zapatos_Rojos();
			break;
		case 5:
			mostrar_Total_Zapatos();
			break;

		case 6:
			System.out.println("Saliendo");
			break;

		default:
			System.out.println("Opcion no valida");
			break;
		}

	}

	private static void mostrar_Total_Zapatos() {
		try {
			CallableStatement stmt = conn.prepareCall("{?=CALL total_Zapatos()}");
			
			stmt.registerOutParameter(1, java.sql.Types.INTEGER);
			
			stmt.execute();
			
			int cantidad = stmt.getInt(1);
			
			System.out.println("El total de los zapatos es de "+ cantidad);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void mostrar_Zapatos_Rojos() {
		// Consulta preparada
		String sql = "SELECT * FROM zapato where color = ? and stock <=?;";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);

			// Asignamos los valores a los parámetros
			pstmt.setString(1, "rojo"); // primer ?
			pstmt.setDouble(2, 20); // segundo ?

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println("ID: " + rs.getInt("id") + " modelo: " + rs.getString("modelo") + ", Color: "
						+ rs.getString("color") + ", Precio: " + rs.getDouble("precio"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void aniadir_Campo_Descripcion() {

		String sql = "ALTER TABLE zapato ADD descripcion VARCHAR(100);";
		try {
			Statement st = conn.createStatement();
			int registros = st.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void aumentar_Precio_Nike() {

		String sql = "UPDATE zapato SET precio = precio + 2 WHERE marca = 'NIKE'";

		try {
			Statement st = conn.createStatement();
			int filas = st.executeUpdate(sql);

			System.out.println("Precios actualizados en " + filas + " filas");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void mostrar_Stock_Sub5() {

		String sql = "select * from zapato where stock <= 5;";

		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				System.out.println("Marca: " + rs.getString("marca") + ", Modelo: " + rs.getString("modelo")
						+ ", Tamaño: " + rs.getString("tamano") + ", Color: " + rs.getString("color") + ", Stock: "
						+ rs.getInt("stock") + ", Precio: " + rs.getDouble("precio") + "€");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void mostrar_Menu() {
		System.out.println("/*/*/* MENU /*/*/*/*");
		System.out.println("1. Mostrar todos los zapatos cuyo stock es menor que 5");
		System.out.println("2. Aumentar el precio en 2 euros de todas las zapatillas Nike");
		System.out.println("3. Añade a la tabla el campo descripción");
		System.out.println("4. Mostrar los zapatos de color rojo y menores de 20");
		System.out.println("5. Mostrar total de zapatos");
		System.out.println("6. Salir");
	}

	private static void crear_Tabla_zapato() {

		try {

			// Leer todo el contenido del archivo SQL
			String sql = Files.readString(Paths.get(TABLA_ZAPATOS_SQL));

			Statement st = conn.createStatement();
			boolean insertado = true;
			insertado = st.execute(sql);

			if (insertado) {
				System.out.println("Tabla creada");
			} else {
				System.out.println("Tabla no creada");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void insertarDatos() {
		int contador = 0;

		try {
			String sql = "INSERT INTO zapato (marca, modelo, tamano, color, stock, precio)"
					+ "VALUES (?, ?, ?, ?, ?, ?);";
			PreparedStatement ps = conn.prepareStatement(sql);

			for (Zapato z : lista.getListaZapatos()) {
				ps.setString(1, z.getMarca());
				ps.setString(2, z.getModelo());
				ps.setString(3, z.getTamano());
				ps.setString(4, z.getColor());
				ps.setInt(5, z.getStock());
				ps.setDouble(6, z.getPrecio());
				int filas = ps.executeUpdate();

				if (filas > 0) {
					contador++;
				}

			}
			System.out.println("Insertadas " + contador + " filas");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void leer_Json() {
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			// Usar File para leer el archivo correctamente
			lista = objectMapper.readValue(new File(ZAPATOS_JSON), ListaZapatos.class);
			System.out.println("JSON zapatos leído correctamente");

			// Mostrar los zapatos leídos
			lista.obtener_Lista_Zapatos();
			System.out.println("");

		} catch (IOException e) {
			System.err.println("Error al leer el archivo JSON: " + e.getMessage());
			e.printStackTrace();
		}
	}
}