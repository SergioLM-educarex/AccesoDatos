package tema4mapeobjetorelacional.aemet2013;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import tema4mapeobjetorelacional.aemet2013.model.DatoClima;
import tema4mapeobjetorelacional.aemet2013.util.JPAUtilAemet;

public class AEMETMain {

	private static final int OPCION_SALIR = 0;
	private static final String AEMET_JSON = "AEMET.json";

	public static void main(String[] args) throws Exception {
		Scanner entrada = new Scanner(System.in);
		int op = 0;
		// 1. LeerJson

		List<DatoClima> datos_clima = leerJson();

		// 2. Guardar Datos en la Base de Datos

		guardar_Datos(datos_clima);

		do {
			mostrar_Menu_Consultas();
			op = entrada.nextInt();
			operar_Menu_Consultas(op);

		} while (op != OPCION_SALIR);

	}

	private static void operar_Menu_Consultas(int op) throws Exception {

		switch (op) {
		case 1:
			mostrarMes("precipitacionMensual", true, "Mes más lluvioso");
			break;
		case 2:
			mostrarMes("precipitacionMensual", false, "Mes menos lluvioso");

			break;
		case 3:
			mostrarMes("temperaturaMediaMaximas", true, "Mes más caluroso");
			break;
		case 4:
			mostrarMes("temperaturaMediaMinimas", false, "Mes más frío");
			break;
		case 5:
			mostrarMes("temperatureMediaMensual", true, "Mes con mayor temperatura media");
			break;
		case 6:
			mostrarMes("temperatureMediaMensual", false, "Mes con mayor temperatura media");
			break;

		case 0:
			System.out.println("Saliendo del programa.... ");
			break;

		default:
			throw new Exception("Error en el argumento");
		}

	}

	private static void mostrarMes(String campo, boolean desc, String mensaje) {
		EntityManager em = JPAUtilAemet.getEntityManager();

		try {
			String orden = desc ? "DESC" : "ASC";

			DatoClima resultado = em.createQuery(
					"SELECT d FROM DatoClima d WHERE d.fecha <> '2013-13' ORDER BY d." + campo + " " + orden,
					DatoClima.class).setMaxResults(1).getSingleResult();

			System.out.println(mensaje + ": " + nombreMes(resultado.getFecha()));
			System.out.println("  -> " + resultado);

		} catch (Exception e) {
			System.out.println("Error al ejecutar la consulta: " + e.getMessage());
		} finally {
			em.close();
		}

	}

	private static void mostrar_Menu_Consultas() {
		System.out.println("\n=== MENÚ CLIMÁTICO 2013 ===");
		System.out.println("1. Mes más lluvioso");
		System.out.println("2. Mes menos lluvioso");
		System.out.println("3. Mes más caluroso");
		System.out.println("4. Mes más frío");
		System.out.println("5. Mes con mayor temperatura media");
		System.out.println("6. Mes con menor temperatura media");
		System.out.println("0. Salir");
		System.out.print("Opción: ");
	}

	private static String nombreMes(String fecha) {
		int mes = Integer.parseInt(fecha.split("-")[1]);
		switch (mes) {
		case 1:
			return "enero";
		case 2:
			return "febrero";
		case 3:
			return "marzo";
		case 4:
			return "abril";
		case 5:
			return "mayo";
		case 6:
			return "junio";
		case 7:
			return "julio";
		case 8:
			return "agosto";
		case 9:
			return "septiembre";
		case 10:
			return "octubre";
		case 11:
			return "noviembre";
		case 12:
			return "diciembre";
		default:
			return "mes desconocido";
		}
	}

	private static void guardar_Datos(List<DatoClima> datos_clima) {

		EntityManager em = JPAUtilAemet.getEntityManager();

		try {

			em.getTransaction().begin();

			for (DatoClima datoClima : datos_clima) {
				em.persist(datoClima);
			}

			em.getTransaction().commit();
			System.out.println(" Guardados " + datos_clima.size() + " registros en la BD");

		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}

	}

	private static List<DatoClima> leerJson() {
		// Se crea un objeto ObjectMapper, componente principal
		// de Jackson que permite convertir entre objetos Java y JSON
		ObjectMapper mapper = new ObjectMapper();

		List<DatoClima> datos = null;

		try {
			datos = mapper.readValue(new File(AEMET_JSON), new TypeReference<List<DatoClima>>() {
			});

			// Cantidad de datos leidos
			System.out.println("Leídos " + datos.size() + " registros desde JSON");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datos;
	}
}
