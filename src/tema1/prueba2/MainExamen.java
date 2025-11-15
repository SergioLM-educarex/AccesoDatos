package tema1.prueba2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MainExamen {

	private static final String SEPARADOR = ";";

	// Mapa que almacenara todos los productos leidos del CSV
	private static ListaProductos listaProductos = new ListaProductos();

	// Archivo donde se guarda el mapa serializado
	private static final File ARCHIVO_ROPA = new File("ropa.dat");
	private static final File ARCHIVO_PRECIO = new File("precio.dat");
	private static final File XML_PRODUCTO = new File("producto.xml");
	private static final File JSON_PRODUCTO = new File("productosJson.json");
	private static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {

		try {
			int opcion = 0;
			do {
				System.out.println("--- Programa simulacro de examen ----");
				mostrarmenu();

				opcion = Integer.parseInt(entrada.nextLine());
				operarMenu(opcion);

			} while (opcion != 4);


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void ejercicio4() {
		ListaProductos lista = null;
		try {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_ROPA))) {
			 
		        
		            lista = (ListaProductos) ois.readObject();
		            
		        } catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			JAXBContext context = JAXBContext.newInstance(ListaProductos.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			
			marshaller.marshal(lista, XML_PRODUCTO);

			System.out.println("Archivo generado correctamente");

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void ejercicio3() {
		int idProducto = 0;
		boolean encontrado = false;

		System.out.println("*-*-*-*-*-*-*-*-");
		System.out.println("Inserte el id del Producto para calcular su beneficio");
		idProducto = Integer.parseInt(entrada.nextLine());

		try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(ARCHIVO_PRECIO))) {

			while (true) {
				int id = dataInputStream.readInt();
				double precio = dataInputStream.readDouble();
				int coste = dataInputStream.readInt();
				int descuento = dataInputStream.readInt();

				if (idProducto == id) {
					encontrado = true;
					double beneficio = precio - coste - (precio * descuento / 100);
					System.out.println("El beneficio con ID " + id + " es " + beneficio);

				}
			}
		} catch (EOFException e) {
			// Llegamos al final del archivo, lectura finalizada correctamente

		} catch (NumberFormatException e) {
			System.out.println("Ha introducido un caracter no numerico");
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (!encontrado) {
			System.out.println("id " + idProducto + " no encontrado");
		}
	}

	private static void ejercicio2() throws FileNotFoundException, IOException, ClassNotFoundException {

		File ropaDat = new File("ropa.dat");

		// Abrimos un flujo para leer el objeto serializado
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_ROPA))) {

			// Leemos el objeto completo ListaProductos del archivo
			ListaProductos lista = (ListaProductos) ois.readObject();

			System.out.println("Lista leida correctamente:");
			System.out.println(lista);

			// Creamos un archivo para escritura mediante RandomAccessFile
			try (RandomAccessFile raf = new RandomAccessFile(ARCHIVO_PRECIO, "rw")) {

				// Recorremos todos los productos de la lista
				for (Producto p : lista.getProductos()) {

					// Escribimos cada campo del producto de forma individual
					// Este formato permite acceso directo por posiciones
					raf.writeInt(p.getId()); // 4 bytes
					raf.writeDouble(p.getPrecio()); // 8 bytes
					raf.writeInt(p.getCoste()); // 4 bytes
					raf.writeInt(p.getDescuento()); // 4 byte
				}

				System.out.println("Archivo RandonmAccessF creado correctamente.");
			}

		} catch (EOFException eof) {
			// Se captura si se llega al final del archivo
			eof.printStackTrace();
		}

	}

	private static void ejercicio1() {

		File archivo = new File("ropa.csv");

		try {
			// Lector de texto para leer el CSV linea a linea
			BufferedReader reader = new BufferedReader(new FileReader(archivo));
			String linea = "";
			String[] datostroc;

			// Variables donde guardaremos los datos leidos del CSV
			String nombre, talla, color, estado;
			int stock;
			Integer id, coste, descuento;
			double precio;

			// Se lee el archivo completo linea a linea
			while ((linea = reader.readLine()) != null) {

				// Separamos la linea por el caracter ';'
				datostroc = linea.split(SEPARADOR);

				// Convertimos cada columna a su tipo correspondiente
				id = Integer.parseInt(datostroc[0]);
				nombre = datostroc[1];
				talla = datostroc[3];
				color = datostroc[4];
				stock = Integer.parseInt(datostroc[6]);
				precio = Double.parseDouble(datostroc[7]);
				coste = Integer.parseInt(datostroc[8]);
				estado = datostroc[9];
				descuento = Integer.parseInt(datostroc[10]);

				// Creamos un objeto Producto con los datos del CSV
				Producto p = new Producto(id, nombre, talla, color, stock, precio, coste, estado, descuento);

				// Lo guardamos dentro del mapa usando el ID como clave
				listaProductos.getProductos().add(p);
			}

			// Guardamos el mapa completo en un archivo binario serializado
			ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(ARCHIVO_ROPA));
			ois.writeObject(listaProductos);

			System.out.println("CSV leido correctamente");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private static void operarMenu(int opcion) throws FileNotFoundException, Exception {

		switch (opcion) {
		case 1:
			ejercicio1();
			break;

		case 2:
			ejercicio2();
			break;

		case 3:
			ejercicio3();
			break;

		case 4:
			ejercicio4();
			break;
		case 5:
			ejercicio5();
			break;

		default:
			System.out.println("opción no válida");
			break;
		}

	}
	

	private static void ejercicio5() {
		
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_ROPA))) {
			
			ListaProductos lista = (ListaProductos) ois.readObject();
			
			ObjectMapper mapper = new ObjectMapper();
			
			mapper.writerWithDefaultPrettyPrinter().writeValue(JSON_PRODUCTO, lista);
			
			System.out.println("Json creado correctamente");
		} catch (Exception e) {
			// TODO: handle exception
		}  

		
				
	}

	private static void mostrarmenu() {

		System.out.println("Ejercicio 1 - Leer CSV a Binario");
		System.out.println("Ejercicio 2 - RandomAccessFile a Binario");
		System.out.println("Ejercicio 3");
		System.out.println("Ejercicio 4");
		System.out.println("Ejercicio 5");
		
		System.out.println("\tElige opcion");
	}

}
