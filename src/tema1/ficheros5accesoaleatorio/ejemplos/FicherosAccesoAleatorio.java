package tema1.ficheros5accesoaleatorio.ejemplos;

import java.io.IOException;
import java.io.RandomAccessFile;

public class FicherosAccesoAleatorio {

    public static void main(String[] args) {

        try {
            // Abrimos (o creamos si no existe) un archivo en modo lectura/escritura ("rw")
            RandomAccessFile archivo = new RandomAccessFile("l�neas.txt", "rw");

           
            // Escribimos varias l�neas en el archivo, una por una
            archivo.writeBytes("Primera l�nea de ejemplo.\n");  // L�nea 1
            archivo.writeBytes("Segunda l�nea de ejemplo.\n");  // L�nea 2
            archivo.writeBytes("Tercera l�nea de ejemplo.\n");  // L�nea 3
            archivo.writeBytes("Cuarta l�nea de ejemplo.\n");   // L�nea 4

          
            // Movemos el puntero de archivo al inicio para comenzar a leer desde el principio
            archivo.seek(0);

            String linea;                    // Almacena temporalmente cada l�nea le�da
            long posicion = 0;              // Guarda la posici�n actual del puntero en el archivo
            int numeroDeLinea = 3;          // L�nea que queremos encontrar (la tercera)
            int contadorDeLineas = 1;       // Contador que incrementa por cada l�nea le�da

            String terceraLinea = null;     // Aqu� se guardar� el contenido de la tercera l�nea
            long posicionTerceraLinea = -1; // Aqu� se guardar� la posici�n en bytes de la tercera l�nea

           
            // Leemos el archivo l�nea por l�nea hasta que no haya m�s l�neas (readLine devuelve null)
            while ((linea = archivo.readLine()) != null) {

              
            	// Si estamos en la tercera l�nea (la que queremos)
                if (contadorDeLineas == numeroDeLinea) {
                    terceraLinea = linea;              // Guardamos el contenido
                    posicionTerceraLinea = posicion;   // Guardamos la posici�n del inicio de esa l�nea
                }

                // Actualizamos la posici�n despu�s de leer la l�nea actual
                // getFilePointer devuelve d�nde est� ahora el puntero de lectura
                posicion = archivo.getFilePointer();

                // Avanzamos al siguiente n�mero de l�nea
                contadorDeLineas++;
            }

            // Si encontramos la tercera l�nea, la mostramos
            if (terceraLinea != null) {
                System.out.println("Posici�n de la 3� l�nea: " + posicionTerceraLinea);
                System.out.println("Contenido de la 3� l�nea: " + terceraLinea);
            } else {
                // Si no la encontramos (menos de 3 l�neas en el archivo)
                System.out.println("No se encontr� la tercera l�nea.");
            }

            // Cerramos el archivo para liberar recursos
            archivo.close();

        } catch (IOException e) {
            // Capturamos cualquier error de entrada/salida y lo mostramos
            e.printStackTrace();
        }
    }
}
