package tema1.prueba2;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



public class Main {

	private static MapaProductos mapaProductos = new MapaProductos();

	public static void main(String[] args) {

		ejercicio1();

		ejercicio2();

	}

	private static void ejercicio2() {

		File ropaDat = new File("ropa.dat");

	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ropaDat))) {

	        MapaProductos mp = (MapaProductos) ois.readObject();

	        System.out.println("Mapa leído correctamente:");
	        System.out.println(mp);
			

		} catch (EOFException e) {
			e.printStackTrace();

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

	}

	private static void ejercicio1() {

		File archivo = new File("ropa.csv");
		File archivoropa = new File("ropa.dat");

		try {
			BufferedReader reader = new BufferedReader(new FileReader(archivo));
			String linea = "";
			String[] datostroc;

			String nombre, talla, color, estado;
			int stock;
			Integer id, precio, coste, descuento;

			while ((linea = reader.readLine()) != null) {

				datostroc = linea.split(";");

				id = Integer.parseInt(datostroc[0]);
				nombre = datostroc[1];
				talla = datostroc[3];
				color = datostroc[4];
				stock = Integer.parseInt(datostroc[6]);
				precio = Integer.parseInt(datostroc[7]);
				coste = Integer.parseInt(datostroc[8]);
				estado = datostroc[9];
				descuento = Integer.parseInt(datostroc[10]);

				Producto p = new Producto(id, nombre, talla, color, stock, precio, coste, estado, descuento);

				mapaProductos.getMapaProductos().put(id, p);

			}

			ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(archivoropa));
			ois.writeObject(mapaProductos);

			System.out.println("Csv leido con exito");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
