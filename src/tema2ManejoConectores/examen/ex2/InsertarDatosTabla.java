package tema2ManejoConectores.examen.ex2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.databind.ObjectMapper;

public class InsertarDatosTabla {

	private static final String SEPARADOR = ",";
	// Rutas de los archivos

	private static final File AVES_TXT = new File("aves.txt");
	private static final String AVES_JSON = "aves.json";
	private static final File AVES_XML = new File("aves.xml");

	// Conexión a BD
	private static Connection conn = ConexionAve.conectar();

	// Listas
	public static Lista_Aves listaAvesJson = new Lista_Aves();
	public static Lista_Aves listaAvesXml = new Lista_Aves();
	public static Lista_Aves listaAves = new Lista_Aves(); // lista final SIN duplicados

	public static void main(String[] args) {
		System.out.println("DATOS JSON");
		leerDatosJson();

		System.out.println("DATOS XML");
		leer_Datos_Xml();

		unirListas(); // aquí se eliminan duplicados

		System.out.println("Datos TXT");
		leer_Datos_Txt();
		System.out.println("---------");

		listaAves.mostrar_Aves();

		insertar_Base_Datos();

	}

	private static void leer_Datos_Txt() {

		String linea, nombre, cientifico, habitat, estado;
		int envergadura = 0;

		String[] datosTroc;

		try {
			BufferedReader br = new BufferedReader(new FileReader(AVES_TXT));

			br.readLine(); // Leer cabecera
			while ((linea = br.readLine()) != null) {

				datosTroc = linea.split(SEPARADOR);
				nombre = datosTroc[0];
				cientifico = datosTroc[1];
				habitat = datosTroc[2];
				envergadura = Integer.parseInt(datosTroc[3]);
				estado = datosTroc[4];

				Ave ave = new Ave(nombre, cientifico, habitat, envergadura, estado);

				if (!existeAveEnListaFinal(ave)) {

					listaAves.aniadir_Ave(ave);
				}

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Inserta las aves en la base de datos. La lista ya está limpia de duplicados.
	 */
	private static void insertar_Base_Datos() {

		int contador = 0;

		String sql = "INSERT INTO ave (nombre_comun, nombre_cientifico, habitat, envergadura_cm, estado_conservacion) "
				+ "VALUES (?,?,?,?,?)";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			for (Ave a : listaAves.getAves()) {

				ps.setString(1, a.getNombre_comun());
				ps.setString(2, a.getNombre_cientifico());
				ps.setString(3, a.getHabitat());
				ps.setInt(4, a.getEnvergadura_cm());
				ps.setString(5, a.getEstado_conservacion());

				ps.executeUpdate();
				contador++;
			}

			System.out.println("Se han insertado " + contador + " filas");

		} catch (SQLException e) {
			// Si hay duplicados, la BD los rechaza
			System.out.println("Error al insertar datos en la base de datos");
			e.printStackTrace();
		}
	}

	/**
	 * Une las listas de JSON y XML evitando duplicados. Un ave es única por nombre
	 * común + nombre científico.
	 */
	private static void unirListas() {

		// Aves del JSON
		for (Ave a : listaAvesJson.getAves()) {
			if (!existeAveEnListaFinal(a)) {
				listaAves.aniadir_Ave(a);
			}
		}

		// Aves del XML
		for (Ave a : listaAvesXml.getAves()) {
			if (!existeAveEnListaFinal(a)) {
				listaAves.aniadir_Ave(a);
			}
		}
	}

	/**
	 * Comprueba si un ave ya existe en la lista final Normaliza los nombres
	 * científicos para evitar duplicados por mayúsculas, minúsculas o espacios.
	 */
	private static boolean existeAveEnListaFinal(Ave aveNueva) {
		// Normalizamos el nombre científico del ave nueva
		String nuevoNombre = aveNueva.getNombre_cientifico().trim().toLowerCase();

		for (Ave a : listaAves.getAves()) {
			// Normalizamos el nombre científico del ave existente
			String nombreExistente = a.getNombre_cientifico().trim().toLowerCase();
			if (nombreExistente.equals(nuevoNombre)) {
				System.out.println("Ave " + nuevoNombre + "ya existe en la lista");
				return true; // ya existe en la lista
			}
		}
		return false; // no existe, se puede añadir
	}

	/**
	 * Lectura de datos desde XML usando JAXB
	 */
	private static void leer_Datos_Xml() {

		try {
			JAXBContext contexto = JAXBContext.newInstance(Lista_Aves.class);
			Unmarshaller um = contexto.createUnmarshaller();

			listaAvesXml = (Lista_Aves) um.unmarshal(AVES_XML);

			System.out.println("XML leído correctamente");
			listaAvesXml.mostrar_Aves();

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lectura de datos desde JSON usando Jackson
	 */
	private static void leerDatosJson() {

		ObjectMapper mapper = new ObjectMapper();

		try {
			listaAvesJson = mapper.readValue(new File(AVES_JSON), Lista_Aves.class);

			System.out.println("JSON leído correctamente");
			listaAvesJson.mostrar_Aves();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
