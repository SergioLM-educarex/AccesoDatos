package tema3PostgreSQL.ejercicios;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

import java.util.Scanner;

import tema3PostgreSQL.ConexionMascotasDB;

public class Ejercicio3_Insert {

	private static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {
		Connection conn = ConexionMascotasDB.getConexion();

		String sql = "INSERT INTO mascota (id, nombre, especie, raza) VALUES (?, ?, ?, ?)";

		int id;
		String nombre, especie, raza;

		System.out.println("INSERTE UNA MASCOTA");

		System.out.println("Ingrese un id");
		id = Integer.parseInt(entrada.nextLine());

		System.out.println("Ingrese un nombre");
		nombre = entrada.nextLine();

		System.out.println("Ingrese una especie");
		especie = entrada.nextLine();

		System.out.println("Ingrese una raza");
		raza = entrada.nextLine();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			// Asignar parámetros
			st.setInt(1, id);
			st.setString(2, nombre);
			st.setString(3, especie);
			st.setString(4, raza);

			// Ejecutar INSERT
			int filasInsertadas = st.executeUpdate();

			if (filasInsertadas > 0) {
				System.out.println("Mascota insertada correctamente");
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
