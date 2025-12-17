package tema2ManejoConectores.examen.ex2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.databind.ObjectMapper;

public class InsertarTabla {

	// Rutas de los archivos
	private static final String AVES_JSON = "aves.json";
	private static final File AVES_XML = new File("aves.xml");

	// Conexión a BD
	private static Connection conn = ConexionAve.conectar();

	// Listas
	public static Lista_Aves listaAvesJson = new Lista_Aves();
	public static Lista_Aves listaAvesXml = new Lista_Aves();
	public static Lista_Aves listaAves = new Lista_Aves(); // lista final SIN duplicados

	public static void main(String[] args) {

		leerDatosJson();
		leer_Datos_Xml();

		unirListas(); // aquí se eliminan duplicados
		System.out.println("---------");

		listaAves.mostrar_Aves();

		insertar_Base_Datos();

	}

	private static void mostrar_Total_Aves() {

		String sql = "SELECT * FROM ";
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
	 * Comprueba si un ave ya existe en la lista final
	 */
	private static boolean existeAveEnListaFinal(Ave aveNueva) {

		for (Ave a : listaAves.getAves()) {
			if (a.getNombre_cientifico().equalsIgnoreCase(aveNueva.getNombre_cientifico())) {
				return true;
			}
		}
		return false;
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
