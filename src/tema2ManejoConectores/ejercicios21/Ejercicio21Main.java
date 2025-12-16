package tema2ManejoConectores.ejercicios21;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Ejercicio21Main {

    private static final int OPCION_SALIR = 4;
    private static Connection con = ConexionEjercicio21.conectar();
    private static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion = 0;
        String nombre = null, curso = null;
        int idAlumno = 0;  // Definir el ID del alumno

        do {
            mostrar_Menu();
            opcion = Integer.parseInt(entrada.nextLine());
            operarMenu(opcion);

        } while (opcion != OPCION_SALIR);
    }

    private static void operarMenu(int opcion) {

    	int idAlumno;
    	String nombre, curso;
    	
        switch (opcion) {
            case 1:
                // Opción 1: Chequear si el alumno existe
                System.out.println("Ingrese el ID del alumno a verificar:");
                idAlumno = Integer.parseInt(entrada.nextLine());
                boolean existe = chequearAlumno(idAlumno);
                if (existe) {
                    System.out.println("El alumno con ID " + idAlumno + " existe.");
                } else {
                    System.out.println("El alumno con ID " + idAlumno + " no existe.");
                }
                break;

            case 2:
                // Opción 2: Agregar nuevo alumno
                System.out.println("Ingrese el ID del nuevo alumno:");
                idAlumno = Integer.parseInt(entrada.nextLine());

                System.out.println("Inserte nombre:");
                nombre = entrada.nextLine();

                System.out.println("Inserte curso:");
                curso = entrada.nextLine();

                agregar_Alumno(idAlumno, nombre, curso);
                break;

            case 3:
                // Opción 3: Contar alumnos
                int totalAlumnos = contarAlumnos();
                System.out.println("Número total de alumnos: " + totalAlumnos);
                break;

            case 4:
                // Opción 4: Salir
                System.out.println("Saliendo del programa...");
                break;

            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    private static void mostrar_Menu() {
        System.out.println("*/*/*/*/*/*/*/*/*/*/*/*/");
        System.out.println("1. Chequear alumno");
        System.out.println("2. Añadir nuevo alumno");
        System.out.println("3. Contar alumnos");
        System.out.println("4. Salir");
        System.out.println("Elige la opción:");
    }

    // Método para agregar un alumno
    private static void agregar_Alumno(int idAlumno, String nombre, String curso) {
        String query = "{CALL agregarAlumno(?, ?, ?)}"; // Procedimiento almacenado para agregar alumno
        try (CallableStatement stmt = con.prepareCall(query)) {
            stmt.setInt(1, idAlumno);  // Aquí el ID debe ser proporcionado
            stmt.setString(2, nombre);
            stmt.setString(3, curso);
            stmt.executeUpdate();
            System.out.println("Alumno agregado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para chequear si un alumno existe
    private static boolean chequearAlumno(int idAlumno) {
        String query = "{? = CALL chequearAlumno(?)}"; // Función almacenada para chequear si un alumno existe
        try (CallableStatement stmt = con.prepareCall(query)) {
            stmt.registerOutParameter(1, java.sql.Types.BOOLEAN);
            stmt.setInt(2, idAlumno);
            stmt.execute();
            return stmt.getBoolean(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para contar el número total de alumnos
    private static int contarAlumnos() {
        String query = "{? = CALL contarAlumnos()}"; // Función almacenada para contar los alumnos
        try (CallableStatement stmt = con.prepareCall(query)) {
            stmt.registerOutParameter(1, java.sql.Types.INTEGER);
            stmt.execute();
            return stmt.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
