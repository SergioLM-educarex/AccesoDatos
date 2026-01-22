package tema3PostgreSQL.ejercicios.ej13;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import tema3PostgreSQL.ConexionMascotasDB;

public class Ejercicio13 {

    private static final int MENU_ALTA_VETERINARIO = 1;
    private static final int MENU_ALTA_MASCOTA = 2;
    private static final int MENU_ALTA_PROPIETARIO = 3;

    private static final int CASE_ALTA = 1;
    private static final int CASE_MODIFICAR = 2;
    private static final int OPCION_SALIR = 5;

    private static final Connection conn = ConexionMascotasDB.getConexion();
    private static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion;

        do {
            mostrar_Menu();
            opcion = Integer.parseInt(entrada.nextLine());
            operarMenu(opcion);
        } while (opcion != OPCION_SALIR);
    }

    private static void operarMenu(int op) {

        switch (op) {

        case CASE_ALTA:
            int opcionAlta;
            do {
                menu_alta();
                opcionAlta = Integer.parseInt(entrada.nextLine());
                operar_alta(opcionAlta);
            } while (opcionAlta < 1 || opcionAlta > 3);
            break;

        case CASE_MODIFICAR:
            modificar_propietario();
            break;

        case OPCION_SALIR:
            System.out.println("Saliendo del programa");
            break;

        default:
            System.out.println("Opción no válida");
            break;
        }
    }

    /* ===================== ALTA ===================== */

    private static void operar_alta(int opcion) {

        switch (opcion) {

        case MENU_ALTA_VETERINARIO:
            System.out.println("Alta Veterinario (pendiente de BD)");
            break;

        case MENU_ALTA_MASCOTA:
            System.out.println("Alta Mascota (pendiente de BD)");
            break;

        case MENU_ALTA_PROPIETARIO:
            System.out.println("Alta Propietario (pendiente de BD)");
            break;

        default:
            System.out.println("Opción no disponible");
            break;
        }
    }

    private static void menu_alta() {
        System.out.println("Seleccione un alta");
        System.out.println("1. Alta de Veterinario");
        System.out.println("2. Alta de Mascota");
        System.out.println("3. Alta de Propietario");
    }

    /* ===================== MODIFICAR PROPIETARIO ===================== */

    private static void modificar_propietario() {

        String sqlBuscar = "SELECT dni FROM propietario WHERE dni = ?";

        System.out.println("Introduce el DNI del propietario:");
        int dni = Integer.parseInt(entrada.nextLine());

        try {
            PreparedStatement ps = conn.prepareStatement(sqlBuscar);
            ps.setInt(1, dni);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("No existe un propietario con ese DNI");
                return;
            }

            int opcion;
            do {
                System.out.println("¿Qué desea modificar?");
                System.out.println("1. Nombre");
                System.out.println("2. Apellidos");
                System.out.println("3. Dirección");
                System.out.println("4. Salir");

                opcion = Integer.parseInt(entrada.nextLine());

                switch (opcion) {
                case 1:
                    modificar_campo_propietario("nombre", dni);
                    break;
                case 2:
                    modificar_campo_propietario("apellidos", dni);
                    break;
                case 3:
                    modificar_campo_propietario("direccion", dni);
                    break;
                case 4:
                	mostrar_Menu();
                    break;
                default:
                    System.out.println("Opción no válida");
                }

            } while (opcion != 4);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void modificar_campo_propietario(String campo, int dni) {

        String sql = "UPDATE propietario SET " + campo + " = ? WHERE dni = ?";

        System.out.println("Introduce el nuevo valor:");
        String nuevoValor = entrada.nextLine();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nuevoValor);
            ps.setInt(2, dni);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Dato actualizado correctamente");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* ===================== MENÚ ===================== */

    private static void mostrar_Menu() {
        System.out.println("-----------------------------------");
        System.out.println("------  APLICACION MASCOTAS   -----");
        System.out.println("-----------------------------------");
        System.out.println("\t 1. Alta");
        System.out.println("\t 2. Modificar");
        System.out.println("\t 3. Baja");
        System.out.println("\t 4. Aplicar vacuna");
        System.out.println("\t 5. Salir");
        System.out.println("Elige una opcion:");
    }
}
