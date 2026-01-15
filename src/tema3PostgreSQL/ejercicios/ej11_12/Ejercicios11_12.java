package tema3PostgreSQL.ejercicios.ej11_12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import tema3PostgreSQL.ConexionMascotasDB;

public class Ejercicios11_12 {

	private static final int OPCION_SI = 1;

	public static void main(String[] args) throws SQLException {

		Connection conn = ConexionMascotasDB.getConexion();

		String sql = "INSERT INTO public.mascota(id, nombre, especie, raza, cartillavacunacion) "
				+ "VALUES (?, ?, ?, ?, ARRAY [ROW(?,?,?)::cartillavacunacion]);";

		Scanner entrada = new Scanner(System.in);
		int opcion = 0;

		
		 // 1️⃣ Obtener próximo ID libre
        PreparedStatement psId = conn.prepareStatement("SELECT COALESCE(MAX(id),0) + 1 AS next_id FROM mascota");
        ResultSet rs = psId.executeQuery();
        rs.next();
        int id = rs.getInt("next_id");
        System.out.println("Id generado = " + id);
		

		System.out.println("Inserte el nombre");
		String nombre = entrada.nextLine();

		System.out.println("Inserte la especie");
		String especie = entrada.nextLine();

		System.out.println("Inserte la raza");
		String raza = entrada.nextLine();

		// ARRAY
		// Preguntar si quiere añadir otra vacunacion
	
		
		System.out.println("Inserte nombre vacuna ");
		String nombreVacuna = entrada.nextLine();

		System.out.println("Inserte el numero de colegiado");
		int colegiado = Integer.parseInt(entrada.nextLine());

		// Fecha de vacunacion
		System.out.println("Indique año vacunacion");
		String anyo = entrada.nextLine();
		System.out.println("Indique mes de vacunacion");
		String mes = entrada.nextLine();
		System.out.println("Indique dia");
		String dia = entrada.nextLine();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fecha = LocalDate.parse(anyo + "-" + mes + "-" + dia, formatter);

		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, id);
			ps.setString(2, nombre);
			ps.setString(3, especie);
			ps.setString(4, raza);

			ps.setString(5, nombreVacuna);
			ps.setInt(6, colegiado);
			ps.setDate(7, java.sql.Date.valueOf(fecha));


			int filasInsertadas = ps.executeUpdate();

			if (filasInsertadas > 0) {
				System.out.println("Mascota insertada correctamente");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
