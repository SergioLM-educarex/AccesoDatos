package tema1.ficheros6formatoIntercambio.jaxb.ejercicios.ejercicio44.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import tema1.ficheros6formatoIntercambio.jaxb.ejercicios.ejercicio44.beans.Direccion;
import tema1.ficheros6formatoIntercambio.jaxb.ejercicios.ejercicio44.beans.EmpresaRoot;
import tema1.ficheros6formatoIntercambio.jaxb.ejercicios.ejercicio44.beans.Trabajador;

public class Apk_Empresa {

	private static final int SALIR = 6;
	private static Scanner entrada = new Scanner(System.in);
	private static EmpresaRoot laEmpresa;


	public static void main(String[] args) {
		int op = 0;
		laEmpresa = introducir_Datos_Empresa();
		do {

			mostrar_Menu();
			op = Integer.parseInt(entrada.nextLine());

			operar_Menu(op);

		} while (op != SALIR);

	}

	//Metodo para la recogida de datos de la empresa que se realizará una vez, no entra en bucle
	private static EmpresaRoot introducir_Datos_Empresa() {

		String nombreEmpresa, nie, calle, ubicacion;
		int cdPostal, numeroCalle;

		
		// Genericos de la empresa
		System.out.println("Nombre de la empresa");
		nombreEmpresa = entrada.nextLine();
		System.out.println("NIE de la empresa");
		nie = entrada.nextLine();

		// Datos de objeto Direccion
		System.out.println("Nombre de la calle de la empresa: ");
		calle = entrada.nextLine();

		System.out.println("Introduce el numero:");
		numeroCalle = Integer.parseInt(entrada.nextLine());

		System.out.println("Lugar de la empresa: ");
		ubicacion= entrada.nextLine();
		
		System.out.println("Codigo Postal de la empresa: ");
		cdPostal=Integer.parseInt(entrada.nextLine());
		
		
		//Genero la direccion de la empresa
		Direccion direccion = new Direccion(calle, numeroCalle, ubicacion, cdPostal);
		
		//Creo el mapa
		HashMap <String,Trabajador> map_Trabajadores = new HashMap<String, Trabajador>();
		
		//Creo y devuelvo la empresa
		EmpresaRoot empresa = new EmpresaRoot(nie, nombreEmpresa, direccion,map_Trabajadores);
		
		return empresa;
		
		

	}

	private static void operar_Menu(int op) {
		switch (op) {
		case 1:
			ver_Datos_Empresa();
			break;

		case 2:
			// ver_Trabajadores();
			break;
		case 3:
			// aniadir_Trabajador();
			break;
		case 4:

			// modificar_Trabajador();
			break;
		case 5:
			// borrar_Trabajador();
			break;
		case 6:
			// guardar_Y_salir();
			break;

		default:
			System.out.println("Opcion no disponible, repita la operación");
			break;
		}

	}

	private static void ver_Datos_Empresa() {
		
		System.out.println("DATOS DE LA EMPRESA");
		laEmpresa.mostrar_Datos_Empresa();

	}

	private static void mostrar_Menu() {
		System.out.println("=======****=****=======");
		System.out.println("--------- Menu principal de empresa ------------");
		System.out.println("1. Ver datos de la empresa");
		System.out.println("2. Ver trabajadores");
		System.out.println("3. Añadir trabajador (sin nif duplicados)");
		System.out.println("4. Modificar trabajador, por nif");
		System.out.println("5. Borrar trabajador, por nif");
		System.out.println("6. Generar xml y Salir");

	}
}
