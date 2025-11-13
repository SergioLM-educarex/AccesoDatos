package tema1.ficheros5accesoaleatorio.ejemplos;

import java.io.IOException;
import java.io.RandomAccessFile;

public class FicherosAccesoAleatorio {

    public static void main(String[] args) {

        try {
            // Abrimos (o creamos si no existe) un archivo en modo lectura/escritura ("rw")
            RandomAccessFile archivo = new RandomAccessFile("líneas.txt", "rw");

           
            // Escribimos varias líneas en el archivo, una por una
            archivo.writeBytes("Primera línea de ejemplo.\n");  // Línea 1
            archivo.writeBytes("Segunda línea de ejemplo.\n");  // Línea 2
            archivo.writeBytes("Tercera línea de ejemplo.\n");  // Línea 3
            archivo.writeBytes("Cuarta línea de ejemplo.\n");   // Línea 4

          
            // Movemos el puntero ARCHIVO.SEEK de archivo al inicio para comenzar a leer desde el principio
            archivo.seek(0);

            String linea;                    // Almacena temporalmente cada línea leída
            long posicion = 0;              // Guarda la posición actual del puntero en el archivo
            int numeroDeLinea = 3;          // Línea que queremos encontrar (la tercera)
            int contadorDeLineas = 1;       // Contador que incrementa por cada línea leída

            String terceraLinea = null;     // Aquí se guardará el contenido de la tercera línea
            long posicionTerceraLinea = -1; // Aquí se guardará la posición en bytes de la tercera línea

           
            // Leemos el archivo línea por línea hasta que no haya más líneas (readLine devuelve null)
            while ((linea = archivo.readLine()) != null) {

              
            	// Si estamos en la tercera línea (la que queremos)
                if (contadorDeLineas == numeroDeLinea) {
                    terceraLinea = linea;              // Guardamos el contenido
                    posicionTerceraLinea = posicion;   // Guardamos la posición del inicio de esa línea
                }

                // Actualizamos la posición después de leer la línea actual
                // getFilePointer devuelve dónde está ahora el puntero de lectura
                posicion = archivo.getFilePointer();

                // Avanzamos al siguiente número de línea
                contadorDeLineas++;
            }

            // Si encontramos la tercera línea, la mostramos
            if (terceraLinea != null) {
                System.out.println("Posición de la 3º línea: " + posicionTerceraLinea);
                System.out.println("Contenido de la 3º línea: " + terceraLinea);
            } else {
                // Si no la encontramos (menos de 3 líneas en el archivo)
                System.out.println("No se encontró la tercera línea.");
            }

            // Cerramos el archivo para liberar recursos
            archivo.close();

        } catch (IOException e) {
            // Capturamos cualquier error de entrada/salida y lo mostramos
            e.printStackTrace();
        }
    }
}
