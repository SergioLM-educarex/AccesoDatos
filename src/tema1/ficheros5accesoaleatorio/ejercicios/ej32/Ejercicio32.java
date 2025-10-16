package tema1.ficheros5accesoaleatorio.ejercicios.ej32;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Ejercicio32 {

	public static void main(String[] args) {

		try {
			// Creamos un objeto RandomAccessFile que abrir� (o crear�) el archivo
			// "produtos.dat"
			// en modo lectura y escritura ("rw").
			RandomAccessFile accessFile = new RandomAccessFile("produtos.dat", "rw");

			// Escribimos tres productos en el archivo, cada uno con sus datos.
			escribirProducto(accessFile, 1, "Leche", 1.50, 100);
			escribirProducto(accessFile, 2, "Pan", 1.75, 200);
			escribirProducto(accessFile, 3, "Galletas", 5.30, 50);

			// Leemos el producto con �ndice 1 (en realidad el segundo, porque el �ndice se
			// multiplica por 36 m�s adelante)
			leerProducto(accessFile, 0);

		} catch (FileNotFoundException e) {
			// Si el archivo no existe o no se puede crear, se lanza esta excepci�n.
			e.printStackTrace();
		}

	}

	private static void leerProducto(RandomAccessFile archivo, int indice) {

		try {
			// Nos movemos en el archivo a la posici�n correspondiente al registro.
			// Cada producto ocupa 36 bytes, as� que multiplicamos el �ndice por 36.
			archivo.seek(indice * 36);

			// Leemos el c�digo del producto (4 bytes, ya que es un int)
			int codigo = archivo.readInt();

			// Leemos el nombre (20 bytes)
			byte[] nombreBytes = new byte[20];
			archivo.read(nombreBytes);

			// Convertimos los bytes a un String
			String nombre = new String(nombreBytes);
			// Eliminamos espacios en blanco sobrantes
			nombre = nombre.trim();

			// Leemos el precio (8 bytes, porque double ocupa 8)
			double precio = archivo.readDouble();

			// Leemos la cantidad en stock (4 bytes)
			int cantidad = archivo.readInt();

			// Mostramos los datos le�dos
			System.out.println("Codigo: " + codigo);
			System.out.println("Nombre: " + nombre);
			System.out.println("Precio: " + precio);
			System.out.println("Cantidad en stock: " + cantidad);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void escribirProducto(RandomAccessFile accessFile, int i, String string, double d, int j) {

		// El nombre del producto debe ocupar exactamente 20 caracteres.
		// Si es m�s corto, a�adimos espacios al final.
		if (string.length() < 20) {
			while (string.length() < 20) {
				string += " "; // Rellenamos con espacios.
			}
		}

		try {
			// Escribimos cada campo en el archivo en orden:
			// 1. c�digo (4 bytes)
			// 2. nombre (20 bytes)
			// 3. precio (8 bytes)
			// 4. cantidad (4 bytes)
			// En total: 36 bytes por registro.
			accessFile.writeInt(i);
			accessFile.writeBytes(string);
			accessFile.writeDouble(d);
			accessFile.writeInt(j);

		} catch (EOFException eof) {
			// EOFException se lanzar�a si intentamos leer/escribir m�s all� del final del
			// archivo.
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
