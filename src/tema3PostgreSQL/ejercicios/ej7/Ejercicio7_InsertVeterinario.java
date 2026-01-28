package tema3PostgreSQL.ejercicios.ej7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import tema3PostgreSQL.ConexionMascotasDB;

public class Ejercicio7_InsertVeterinario {

	private static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		
		Connection conn = ConexionMascotasDB.getConexion();

		String sql = "INSERT INTO veterinario (dni, nombre, apellidos, numcolegiado) VALUES (?, ?, ?, ?)";

		int numColegiado, dni;
		String nombre, apellido1 = null,apellido2 = null;

		//INSERTAR ARRAY POSTGRE
		String[] apellidos = new String[2];
	

		System.out.println("INSERTE UN VETERINARIO");

		System.out.println("Ingrese numero de colegiado");
		numColegiado = Integer.parseInt(entrada.nextLine());

		System.out.println("Ingrese un nombre");
		nombre = entrada.nextLine();

		System.out.println("Ingrese el primer apellido");
		apellidos[0] = entrada.nextLine();
		
		System.out.println("Ingrese el segundo apellido");
		apellidos[1] = entrada.nextLine();

		System.out.println("Ingrese el dni");
		dni = Integer.parseInt(entrada.nextLine());
		

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			// Asignar parámetros --Cambiar
			st.setInt(1, dni);
			st.setString(2, nombre);
			st.setArray(3, conn.createArrayOf("text", apellidos)); //ESTO ES LO IMPORTANTE
			st.setInt(4, numColegiado);

			// Ejecutar INSERT
			int filasInsertadas = st.executeUpdate();

			if (filasInsertadas > 0) {
				System.out.println("Veterinario insertado correctamente");
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
