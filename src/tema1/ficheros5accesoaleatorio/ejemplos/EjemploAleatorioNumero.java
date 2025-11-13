package tema1.ficheros5accesoaleatorio.ejemplos;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class EjemploAleatorioNumero {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        try {
            // Crea o abre el archivo para escritura/lectura
            RandomAccessFile archivo = new RandomAccessFile("numeros.txt", "rw");

            // Escribimos varios pares de (int, double)
            archivo.writeInt(1);         // 4 bytes
            archivo.writeDouble(5.5);    // 8 bytes

            archivo.writeInt(9);
            archivo.writeDouble(7.7);

            archivo.writeInt(3);
            archivo.writeDouble(3.6);

            // Pedimos al usuario qué número quiere buscar
            System.out.println("Introduce el orden del número a buscar:");
            int numeroLeer = Integer.parseInt(entrada.nextLine());

            // Volvemos al inicio del archivo para comenzar la lectura
            archivo.seek(0);

            int numPosicion;
            double valor;
            boolean encontrado = false;

            // Leemos mientras haya datos
            while (true) {
                try {
                    numPosicion = archivo.readInt();     // Lee un entero
                    valor = archivo.readDouble();        // Lee el double asociado

                    if (numPosicion == numeroLeer) {
                        System.out.println("Número encontrado: " + numPosicion + ", valor: " + valor);
                        encontrado = true;
                        break; // Terminamos el bucle si encontramos el valor
                    }
                } catch (EOFException e) {
                    // Se alcanza el final del archivo
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("Número no encontrado en el archivo.");
            }

            archivo.close();     // Cerramos el archivo al final del try
            entrada.close();     // Cerramos el scanner
        } catch (IOException e) {
            e.printStackTrace(); // Mostramos cualquier error de E/S
        }
    }
}
