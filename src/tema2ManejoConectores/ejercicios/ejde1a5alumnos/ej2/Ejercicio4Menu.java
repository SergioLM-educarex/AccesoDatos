package tema2ManejoConectores.ejercicios.ejde1a5alumnos.ej2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ejercicio4Menu {
	private static final int SALIR = 4;

	private static Connection con = ConexionEJ2.conectar();

	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);
		int op;

		do {
			System.out.println("Dime que tabla quieres mostrar: ");
			System.out.println("1. Alumeno");
			System.out.println("2. Nota");
			System.out.println("3. Modulo");
			System.out.println("4. Salir");
			op = Integer.parseInt(entrada.nextLine());
			operarMenu(op);
		} while (op != SALIR);

		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void operarMenu(int op) {

		switch (op) {
		case 1:
			mostrarAlumnos();
			break;

		case 2:
			mostrarNotas();
			break;

		case 3:
			mostrarModulos();
			break;
		case 4:
			System.out.println("Saliendo....");
			break;

		default:

			break;
		}

	}

	private static void mostrarModulos() {
		Statement sentencia;
		try {
			sentencia = con.createStatement();
			ResultSet resultado = sentencia.executeQuery("select * from modulo");
			System.out.println("Codigo \t \tNomvre");

			while (resultado.next()) {
				System.out
						.println(resultado.getString(1) + "\t\t" + resultado.getString(2));
			}
			resultado.close();
			sentencia.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	private static void mostrarNotas() {
		Statement sentencia;
		try {
			sentencia = con.createStatement();
			ResultSet resultado = sentencia.executeQuery("select * from nota");
			System.out.println("Alumno \t \tModulo \tNota");

			while (resultado.next()) {
				System.out
						.println(resultado.getInt(1) + "\t\t" + resultado.getString(2) + "\t\t" + resultado.getInt(3));
			}
			resultado.close();
			sentencia.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void mostrarAlumnos() {

		Statement sentencia;
		try {
			sentencia = con.createStatement();
			ResultSet resultado = sentencia.executeQuery("select * from alumno");
			System.out.println("id \t \t Nombre \tCurso");

			while (resultado.next()) {
				System.out.println(
						resultado.getInt(1) + "\t\t" + resultado.getString(2) + "\t\t" + resultado.getString(3));
			}
			resultado.close();
			sentencia.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
