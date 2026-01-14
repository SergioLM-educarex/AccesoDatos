package tema3PostgreSQL.ejercicios.ej9;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import tema3PostgreSQL.ConexionMascotasDB;

public class Ejercicio9_10_propietario {

    private static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {

        Connection conn = ConexionMascotasDB.getConexion();

        // SQL con array de apellidos y tipo compuesto direccion
        String sql = "INSERT INTO propietario (dni, nombre, apellidos, direccion)\r\n"
        		+ "VALUES (?, ?, ?, ROW(?, ?, ?, ?))";

        int dni, numero, cp;
        String nombre, calle, ciudad;
        String[] apellidos = new String[2];

        System.out.println("INSERTE UN PROPIETARIO");

        System.out.print("Ingrese DNI: ");
        dni = Integer.parseInt(entrada.nextLine());

        System.out.print("Ingrese nombre: ");
        nombre = entrada.nextLine();

        System.out.print("Ingrese primer apellido: ");
        apellidos[0] = entrada.nextLine();

        System.out.print("Ingrese segundo apellido: ");
        apellidos[1] = entrada.nextLine();

        System.out.print("Ingrese calle: ");
        calle = entrada.nextLine();

        System.out.print("Ingrese número: ");
        numero = Integer.parseInt(entrada.nextLine());

        System.out.print("Ingrese ciudad: ");
        ciudad = entrada.nextLine();

        System.out.print("Ingrese código postal: ");
        cp = Integer.parseInt(entrada.nextLine());

        try (PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, dni);
            st.setString(2, nombre);
            st.setArray(3, conn.createArrayOf("text", apellidos));
            st.setString(4, calle);
            st.setInt(5, numero);
            st.setString(6, ciudad);
            st.setInt(7, cp);

            int filasInsertadas = st.executeUpdate();

            if (filasInsertadas > 0) {
                System.out.println("Propietario insertado correctamente");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
