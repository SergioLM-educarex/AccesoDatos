package tema1.ficheros5accesoaleatorio.ejercicios.ej33;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio33 {

	private static final int TAMANIO_STRING = 20;
	private static final int SALIR = 5;
	private static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		RandomAccessFile archivo = new RandomAccessFile("productos33.txt", "rw");

		int opcion = 0;

		do {
			mostrarMenu();
			opcion = Integer.parseInt(entrada.nextLine());

			operar_Menu(opcion, archivo);
		} while (opcion != SALIR);

	}

	private static void operar_Menu(int opcion, RandomAccessFile archivo) {
		switch (opcion) {
		case 1:
			registrar_Producto(archivo);
			break;

		case 2:
			mostrar_ListaProductos(archivo);
			break;

		case 3:
			modificar_Stock(archivo);
			break;

		case 4:
			// borrar_Producto();
			break;

		case 5:
			System.out.println("Saliendo....");
			break;

		default:
			System.out.println("Repita la operación. OPCION NO VALIDA");
			break;
		}

	}

	private static void modificar_Stock(RandomAccessFile archivo) {
		
		int id_pedido= pedir_Codigo();
		int codigo, stock;
		boolean encontrado=false;
		
		try {
			archivo.seek(0);
			
			
			while (true) {
				codigo = archivo.readInt();
				
				if (codigo!=id_pedido) {
					//Saltar 32 bytes por el orden que tiene el fichero ya que no existe
					
					archivo.skipBytes(32);
					
					
				}else {
					//Saltar 28
					archivo.skipBytes(28);
					
					System.out.println("Stock actual: "+archivo.readInt());
					
					System.out.println("Inserte el nuevo stock: ");
					stock = Integer.parseInt(entrada.nextLine());
					
					archivo.seek(archivo.getFilePointer()-4);
					archivo.writeInt(stock);
					System.out.println("Stock actualizado correctamente");
					encontrado=true;
					
				}
				
			}
			
			
			
		} catch (EOFException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (!encontrado) {
			System.out.println("Codigo no encontrado");
		}
		
	}

	private static void mostrar_ListaProductos(RandomAccessFile archivo) {

		try {
			archivo.seek(0);
			int codigo =0;
			byte[]nombreByte= new byte[TAMANIO_STRING];
			String nombre= "";

			double precio= 0;
			int cantidad=0;
			
			while (true) {

				codigo= archivo.readInt();
				archivo.read(nombreByte);
				
				nombre= new String(nombreByte);
				nombre=nombre.trim();
				
				precio=archivo.readDouble();
				cantidad= archivo.readInt();
				
				// Mostramos los datos leídos
				System.out.println("---------");
				System.out.println("Codigo: " + codigo);
				System.out.println("Nombre: " + nombre);
				System.out.println("Precio: " + precio);
				System.out.println("Cantidad en stock: " + cantidad);
				
				
			}

		} catch (EOFException eof) {
			System.out.println("+++++++++++++++++");
			System.out.println("Fichero terminado");
			System.out.println("+++++++++++++++++");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void registrar_Producto(RandomAccessFile archivo) {

		String nombre, tipo;
		int codigo = 0, cantidad = 0;
		double precio = 0;

		System.out.println("Introduce el nombre");
		nombre = entrada.nextLine().trim();

		try {

			codigo = pedir_Codigo();

			System.out.println("Introduce el precio del producto: ");
			precio = Double.parseDouble(entrada.nextLine().trim());

			System.out.println("Introce la cantidad:");
			cantidad = Integer.parseInt(entrada.nextLine().trim());

		} catch (NumberFormatException e) {
			System.out.println("Escriba un numero. Repita la operación");
		}

		escribir_Producto(archivo, nombre, cantidad, precio, codigo);

	}

	private static int pedir_Codigo() {
		int codigo;
		System.out.println("Introduce el codigo del producto: ");
		codigo = Integer.parseInt(entrada.nextLine().trim());
		return codigo;
	}

	private static void escribir_Producto(RandomAccessFile archivo, String nombre, int cantidad, double precio,
			int codigo) {

		if (nombre.length() < TAMANIO_STRING) {
			while (nombre.length() != TAMANIO_STRING) {
				nombre += " ";
			}
		}

		try {
			archivo.writeInt(codigo);
			archivo.writeBytes(nombre);
			archivo.writeDouble(precio);
			archivo.writeInt(cantidad);
		} catch (EOFException eof) {
			// EOFException se lanzaría si intentamos leer/escribir más allá del final del
			// archivo.

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Producto registrado correctamente...!!!");

	}

	private static void mostrarMenu() {
		System.out.println("========== MENU ==========");
		System.out.println("--------------------------");
		System.out.println("1. 1. Dar de alta un producto\r\n" + "2. Mostrar un listar de todos los productos\r\n"
				+ "3. Modificar el stock de un producto\r\n" + "4. Borrar producto");

	}

}
