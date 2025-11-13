package tema1.ficheros6formatoIntercambio.json.ejercicios.ej47;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainRecetas {

	private static final int OP_SALIR = 4;
	private static Scanner entrada = new Scanner(System.in);
	private static ObjectMapper mapper = new ObjectMapper();
	private static List<Receta> lista = new ArrayList<Receta>();

	public static void main(String[] args) {
		int opcion = 0;

		try {
			Recetas recetas = mapper.readValue(new File("ej46recetas.json"), Recetas.class);

			lista = recetas.getRecetas();

			System.out.println("Json cargado correctamente");

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

		do {

			mostrarMenu();
			opcion = Integer.parseInt(entrada.nextLine());

			operarMenu(opcion);

		} while (opcion != OP_SALIR);

	}

	private static void operarMenu(int opcion) {

		switch (opcion) {
		case 1:
			agregar_Receta();
			break;

		case 2:
			mostrar_Recetas();
			break;
		case 3:
			borrar_Receta();
			break;
		case 4:
			System.out.println("Saliendo....");
			break;
		default:
			System.out.println("Opcion no valida");
			break;
		}

	}

	private static void agregar_Receta() {

		ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		Origen origen = new Origen();
		String nombreReceta, tipo, pais, region, cantIngrediente, nombreIngrediente;
		int continuar;

		System.out.println("Nombre de la receta");
		nombreReceta = entrada.nextLine();

		System.out.println("Tipo de receta");
		tipo = entrada.nextLine();

		System.out.println("Origen: Pais de la receta");
		pais = entrada.nextLine();

		System.out.println("Origen: Region de la receta");
		region = entrada.nextLine();

		// Rellenar origen
		origen.setPais(pais);
		origen.setRegion(region);

		do {

			System.out.println("INGREDIENTES:");
			System.out.println("\n Nombre del ingrediente");
			nombreIngrediente = entrada.nextLine();

			System.out.println("\n Cantidad del ingrediente");
			cantIngrediente = entrada.nextLine();
			
			Ingrediente ingrediente = new Ingrediente(nombreIngrediente, cantIngrediente);
			ingredientes.add(ingrediente);

			System.out.println("Continuar 1-SI 2-NO");
			continuar = Integer.parseInt(entrada.nextLine());

		} while (continuar != 2);

		Receta receta = new Receta(nombreReceta, tipo, origen, ingredientes);
		
		lista.add(receta);
	}

	private static void borrar_Receta() {
		String recetaaBorrar;
		System.out.println("�Que receta quieres borrar?");
		recetaaBorrar = entrada.nextLine();

		Iterator<Receta> it = lista.iterator();
		boolean encontrada = false;

		while (it.hasNext()) {
			Receta receta = (Receta) it.next();

			if (receta.getNombreRec().equalsIgnoreCase(recetaaBorrar)) {
				it.remove();
				encontrada = true;
			}

		}
		if (encontrada) {
			System.out.println("Borrada correctamente");
		} else {
			System.out.println("Receta no encontrada");
		}

	
	}

	private static void mostrar_Recetas() {

		Recetas todasRecetas = new Recetas(new ArrayList<>(lista));
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(System.out, todasRecetas);
			System.out.println();

		} catch (StreamWriteException e) {
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

	private static void mostrarMenu() {
		System.out.println("-------- GESTION DE TAREAS ------------");
		System.out.println("1. Nueva Receta");
		System.out.println("2. Mostrar Recetas");
		System.out.println("3. Borrar Recetas");
		System.out.println("4. Salir");

	}

}
