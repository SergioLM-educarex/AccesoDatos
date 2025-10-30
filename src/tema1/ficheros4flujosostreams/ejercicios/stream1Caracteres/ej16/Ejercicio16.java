package tema1.ficheros4flujosostreams.ejercicios.stream1Caracteres.ej16;

import java.io.*;
import java.util.Scanner;

public class Ejercicio16 {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        String nombreFichero;

        System.out.print("Indique cu�l es el fichero: ");
        nombreFichero = entrada.nextLine();

        File fichero = new File(nombreFichero);

        if (fichero.isFile()) {
            int totalLineas = mostrarFichero(fichero);

            int numModificar = 0;
            do {
                System.out.print("Introduce el n�mero de l�nea que deseas borrar (1 - " + totalLineas + "): ");
                try {
                    numModificar = Integer.parseInt(entrada.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, introduce un n�mero v�lido.");
                }
            } while (numModificar < 1 || numModificar > totalLineas);

            borrarFichero(fichero, numModificar);

            System.out.println("\nFichero modificado:");
            mostrarFichero(fichero);
        } else {
            System.out.println("No es un fichero.");
        }

        entrada.close();
    }

    private static void borrarFichero(File fichero, int numModificar) {
        int lineaActual = 1;
        String linea;
        File temporal = new File("temporal.txt");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fichero));
             BufferedWriter bfTemporal = new BufferedWriter(new FileWriter(temporal))) {

            while ((linea = bufferedReader.readLine()) != null) {
              
            	if (lineaActual != numModificar) {
                    bfTemporal.write(linea);
                    bfTemporal.newLine();
                }
                lineaActual++;
            }
            bufferedReader.close();
            bfTemporal.close();

        } catch (IOException e) {
            e.printStackTrace();
            
        }

        // Eliminar el fichero original y renombrar el temporal
        if (fichero.delete()) {
            if (!temporal.renameTo(fichero)) {
                System.out.println("Error al renombrar el fichero temporal.");
            }
        } else {
            System.out.println("Error al eliminar el fichero original.");
        }
    }

    private static int mostrarFichero(File fichero) {
        String linea;
        int numLinea = 1;

        try (BufferedReader breader = new BufferedReader(new FileReader(fichero))) {
            while ((linea = breader.readLine()) != null) {
                System.out.println(numLinea + ": " + linea);
                numLinea++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return numLinea - 1;
    }
}
