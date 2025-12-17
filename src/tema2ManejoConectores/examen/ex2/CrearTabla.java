package tema2ManejoConectores.examen.ex2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearTabla {

	private static Connection conn = ConexionAve.conectar();
	public static final String TABLA_AVE = "Tabla_Ave.sql";

	public static void main(String[] args) {
		crear_Tabla_Ave();

	}

	/**
	 * Crea la tabla leyendo el script SQL desde fichero
	 */
	private static void crear_Tabla_Ave() {
		int filas = 0;
		try {
			String sql = Files.readString(Paths.get(TABLA_AVE));

			Statement st = conn.createStatement();
			filas = st.executeUpdate(sql);

			if (filas == 0) {
				
				System.out.println(filas);
				System.out.println("Tabla creada");
			} else {
				System.out.println("Tabla no creada");
			}

		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
}
