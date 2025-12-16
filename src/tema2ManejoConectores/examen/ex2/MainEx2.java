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

public class MainEx2 {

	private static final int OPCION_SALIR = 7;
	// Rutas de los archivos
	private static final String AVES_JSON = "aves.json";
	private static final File AVES_XML = new File("aves.xml");
	public static final String TABLA_AVE = "Tabla_Ave.sql";
	public static Scanner entrada = new Scanner(System.in);

	// Conexión a BD
	private static Connection conn = ConexionAve.conectar();

	// Listas
	public static Lista_Aves listaAvesJson = new Lista_Aves();
	public static Lista_Aves listaAvesXml = new Lista_Aves();
	public static Lista_Aves listaAves = new Lista_Aves(); // lista final SIN duplicados

	public static void main(String[] args) {

		int opcion = 0;

		crear_Tabla_Ave();
		leerDatosJson();
		leer_Datos_Xml();

		unirListas(); // aquí se eliminan duplicados
		System.out.println("---------");

		listaAves.mostrar_Aves();

		insertar_Base_Datos();

		do {
			mostrar_Menu();
			opcion = Integer.parseInt(entrada.nextLine());
			operar_Menu(opcion);
		} while (opcion != OPCION_SALIR);

	}

	private static void operar_Menu(int opcion) {

		switch (opcion) {
		case 1:

			break;
		case 2:

			break;

		case 3:

			break;

		case 4:

			break;

		case 5:

			break;

		case 6:
			
			break;

		case 7:
			System.out.println("Saliendo....");
			break;

		default:
			System.out.println("Opcion no válida");
			break;
		}

	}

	private static void mostrar_Menu() {
		System.out.println("-------------");
		System.out.println("====== Menu operaciones ======");
		System.out.println("1.Mostrar todas las aves\r\n" + "\r\n" + "2.Mostrar aves por hábitat\r\n" + "\r\n"
				+ "3.Mostrar aves con una envergadura mayor a un valor indicado\r\n" + "\r\n"
				+ "4.Mostrar el ave con mayor envergadura\r\n" + "\r\n"
				+ "5.Mostrar la envergadura media por estado de conservación\r\n" + "\r\n" + "6.Exportar datos\r\n"
				+ "\r\n" + "7.Salir");
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

	/**
	 * Crea la tabla leyendo el script SQL desde fichero
	 */
	private static void crear_Tabla_Ave() {

		try {
			String sql = Files.readString(Paths.get(TABLA_AVE));

			Statement st = conn.createStatement();
			st.execute(sql);

			System.out.println("Tabla creada o ya existente");

		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
}
