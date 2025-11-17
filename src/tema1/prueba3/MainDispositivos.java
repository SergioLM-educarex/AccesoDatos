package tema1.prueba3;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;




public class MainDispositivos {

	private static final File FILE_BINARIO_INVENTARIO = new File("inventario.dat");
	private static final File FILE_CATALOGO = new File("catalogo.xml");
	private static final double PORCENTAJE_REAC = 0.85;
	private static final String SEPARADOR = ",";
	private static final File ARCHIVO_DISPOSITIVO = new File("Dispositivos.csv");
	private static final File JSON_DISPOSITIVO = new File("dispositivos.json");
	private static final File ARCHIVO_PRECIO = new File("precios_dispositivo.dat");
	private static Scanner entrada = new Scanner(System.in);

	private static ListaDispositivos listaDispositivos = new ListaDispositivos();

	public static void main(String[] args) {

		int opcion = 0;
		do {
			System.out.println("--- Programa simulacro de examen ----");
			mostrarmenu();

			opcion = Integer.parseInt(entrada.nextLine());
			operarMenu(opcion);

		} while (opcion != 4);

	}

	

	private static void ejercicio5() {
		
		
		try (RandomAccessFile raf = new RandomAccessFile(FILE_BINARIO_INVENTARIO, "rw")) {

	        // Limpiamos el archivo por si ya existía
	        raf.setLength(0);

	        for (Dispositivo d : listaDispositivos.getListaDispositivos()) {

	            raf.writeInt(d.getId());      // 4 bytes
	            raf.writeInt(d.getStock());   // 4 bytes
	        }

	        System.out.println("inventario.dat generado correctamente.");

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
		
	

	private static void ejercicio4() {

		// LEER JSON
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			ListaDispositivos lista = objectMapper.readValue(JSON_DISPOSITIVO, ListaDispositivos.class);
			System.out.println("JSON leido correctamente");
		
			// Crear contexto JAXB
						JAXBContext jaxbContext = JAXBContext.newInstance(ListaDispositivos.class);
						Marshaller marshaller = jaxbContext.createMarshaller();
						// Configuracion opcional para formato legible
						marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
						// Convertir objeto a XML y mostrar en consola
						
						marshaller.marshal(lista,FILE_CATALOGO);
						
						System.out.println("Xml escrito correctamente");
			
		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void ejercicio3() {
		System.out.println("Introduce el Id del producto:");
		int idBuscado = Integer.parseInt(entrada.nextLine());

		// Booleano para mandar mensaje de Encontrado o No Encontrado
		boolean encontrado = false;

		// 2 Try
		try (RandomAccessFile raf = new RandomAccessFile(ARCHIVO_PRECIO, "r")) {

			try {
				while (true) {
					int idLeido = raf.readInt();

					if (idLeido == idBuscado) {
						double precioOri = raf.readDouble();
						double precioReac = raf.readDouble();
						int garantia = raf.readInt();

						double ahorro = precioOri - precioReac;

						System.out.println("Dispositivo encontrado:");
						System.out.println("ID: " + idLeido);
						System.out.println("Precio original: " + precioOri);
						System.out.println("Precio reacondicionado: " + precioReac);
						System.out.println("Garantía: " + garantia + " meses");
						System.out.println("Ahorro del cliente: " + ahorro + " €");

						encontrado = true;
						System.out.println();

					} else {
						// Saltamos el resto del registro
						raf.skipBytes(20); // 2 doubles + 1 int
					}
				}
			} catch (EOFException eof) {
				// EOFException marca el fin del archivo
				// el control sale automáticamente del while
			}

			if (!encontrado) {
				System.out.println("No se encontró el dispositivo con ID " + idBuscado);
				System.out.println();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void ejercicio2() {

		try {

			// LEER JSON
			ObjectMapper objectMapper = new ObjectMapper();
			ListaDispositivos lista = objectMapper.readValue(JSON_DISPOSITIVO, ListaDispositivos.class);
			System.out.println("JSON leido correctamente");

			// ACCESO RANDOM
			RandomAccessFile raf = new RandomAccessFile(ARCHIVO_PRECIO, "rw");

			for (Dispositivo dispositivo : lista.getListaDispositivos()) {

				int id = dispositivo.getId();
				double precioOriginal = dispositivo.getPrecioReacondicionado();
				double precioReacon = precioOriginal * PORCENTAJE_REAC;
				int garantiaMeses = dispositivo.getGarantiaMeses();

				raf.writeInt(id);
				raf.writeDouble(precioOriginal);
				raf.writeDouble(precioReacon);
				raf.writeInt(garantiaMeses);
			}

			raf.close();
			System.out.println("Archivo precio.dat generado correctamente");

		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void ejercicio1() {

		try {
			// Crear el bufferedReader para leer el archivo
			BufferedReader bufferedReader = new BufferedReader(new FileReader(ARCHIVO_DISPOSITIVO));
			String linea, marca = null, modelo = null, tipo = null, alcacena = null, ram = null, color = null;
			int id = 0, stock = 0, garanMes = 0;
			double precio = 0;
			String[] datostroc;

			// Obvia la primera linea
			String lineaDescartada = bufferedReader.readLine();

			while ((linea = bufferedReader.readLine()) != null) {

				datostroc = linea.split(SEPARADOR);

				id = Integer.parseInt(datostroc[0]);
				marca = datostroc[1];
				modelo = datostroc[2];
				tipo = datostroc[3];
				alcacena = datostroc[4];
				ram = datostroc[5];
				color = datostroc[6];
				stock = Integer.parseInt(datostroc[7]);
				precio = Double.parseDouble(datostroc[8]);
				garanMes = Integer.parseInt(datostroc[9]);

				Dispositivo d = new Dispositivo(id, marca, modelo, tipo, alcacena, ram, color, stock, precio, garanMes);

				listaDispositivos.getListaDispositivos().add(d);

			}
			System.out.println("Archivo Buffer leido correctamente");

			listaDispositivos.mostrarListaDispositivos();

			generarJson();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void generarJson() {

		ObjectMapper mapper = new ObjectMapper();

		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(JSON_DISPOSITIVO, listaDispositivos);

			// TENER CUIDADO SI SE IGNORA ALGUN CAMPO - SI SE IGNORA HAY QUE PONER LAS
			// ANOTACIONES

			System.out.println("Json generado correctamente");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	private static void operarMenu(int opcion) {

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
	

	private static void mostrarmenu() {

		System.out.println("Ejercicio 1 - Generar un fichero JSON a partir de un CSV");
		System.out.println("Ejercicio 2 - Leer Json y crear binario con datos para AccessRandomFile");
		System.out.println("Ejercicio 3 - Buscar precio por ID en un RandomAccesFile");
		System.out.println("Ejercicio 4 - JSON a XML");
		System.out.println("Ejercicio 5 - De JSON a binario (RandomAccesFile)");

		System.out.println("\tElige opcion");
	}

}
