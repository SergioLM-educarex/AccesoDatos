package tema4mapeobjetorelacional.zApuntes;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class MainEmpleadosEx {

	private static EntityManager em = JPAProyectos.getEntityManager();
	private static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {
		int op = 0;
		crearBaseDatos();

		do {

			try {
				mostrar_Menu();
				op = Integer.parseInt(entrada.nextLine());
				operar_Menu(op);
			} catch (NumberFormatException e) {
				System.out.println("Entra un numero anda");
			}

		} while (op != 0);

	}

	private static void operar_Menu(int op) {

		switch (op) {
		case 1:
			crearDatosIniciales();
			break;
		case 2:
			buscar_por_Nombre();
			break;
		case 3:
			empleadosPorProyecto();
			break;
		case 4:
			totalHorasAsignadas();
			break;
		case 5:
			empleadosSalarioMayor();
			break;

		case 7:
			listarEmpleadosNative();
			break;
		case 8:
			listarProyectos();
			break;
		case 9:
			listarAsignaciones();
			break;
		case 10:
			aniadirDireccion();
			break;
		case 0:
			System.out.println("¡Hasta luego!");
			JPAProyectos.close();
			break;
		default:
			System.out.println("Opción no válida");
		}
	}

	private static void aniadirDireccion() { //UPDATE
		 EntityManager em = JPAProyectos.getEntityManager();
		    EntityTransaction tx = em.getTransaction();
		    
		    try {
		        System.out.print("ID del empleado: ");
		        Long id = Long.parseLong(entrada.nextLine());
		        
		        Empleado emp = em.find(Empleado.class, id);
		        if (emp == null) {
		            System.out.println("Empleado no encontrado");
		            return;
		        }
		        
		        System.out.print("Calle: ");
		        String calle = entrada.nextLine();
		        System.out.print("Ciudad: ");
		        String ciudad = entrada.nextLine();
		        System.out.print("Código Postal: ");
		        String cp = entrada.nextLine();
		        
		        Direccion dir = new Direccion(calle, ciudad, cp);
		        
		        tx.begin();
		        emp.setDireccion(dir);
		        em.merge(emp);  // Con cascade, guarda también la dirección
		        tx.commit();
		        
		        System.out.println("Dirección añadida correctamente");
		        
		    } catch (Exception e) {
		        if (tx.isActive()) tx.rollback();
		        System.out.println("Error: " + e.getMessage());
		    } finally {
		        em.close();
		    }
		}

		
		


	private static void listarAsignaciones() {
		EntityManager em = JPAProyectos.getEntityManager();

		try {
			// Consulta JPQL simple
			String jpql = "SELECT a FROM asignacion a";

			TypedQuery<Asignacion> consulta = em.createQuery(jpql, Asignacion.class);
			List<Asignacion> asignaciones = consulta.getResultList();

			System.out.println("\n LISTA DE ASIGNACIONES:");
			System.out.println("========================");

			if (asignaciones.isEmpty()) {
				System.out.println("No hay asignaciones.");
			} else {
				for (Asignacion a : asignaciones) {
					System.out.println(a.toString());
				}
				System.out.println("\nTotal: " + asignaciones.size() + " asignaciones");
			}

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			em.close();
		}
	}

	private static void listarProyectos() {
		EntityManager em = JPAProyectos.getEntityManager();

		try {

			TypedQuery<Proyecto> consulta = em.createNamedQuery("Proyecto.listarProyectos", Proyecto.class);

			List<Proyecto> proyectos = consulta.getResultList();

			System.out.println("LISTA DE PROYECTOS (NamedNativeQuery):");
			if (proyectos.isEmpty()) {
				System.out.println("No hay proyectos.");
			} else {
				for (Proyecto p : proyectos) {
					System.out.println(p.toString());
				}
			}

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			em.close();
		}

	}

	private static void listarEmpleadosNative() {
		EntityManager em = JPAProyectos.getEntityManager();

		try {
			String sql = "SELECT * FROM Empleado";
			Query consulta = em.createNativeQuery(sql, Empleado.class);
			List<Empleado> empleados = consulta.getResultList();

			System.out.println("LISTA DE EMPLEADOS:");
			if (empleados.isEmpty()) {
				System.out.println("No hay empleados.");
			} else {
				for (Empleado e : empleados) {
					System.out.println(e.toString());
				}
			}

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			em.close();
		}
	}

	private static void empleadosSalarioMayor() {
		System.out.print("Introduce el salario mínimo: ");

		double salarioMin;
		try {
			salarioMin = Double.parseDouble(entrada.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("❌ Cantidad no válida");
			return;
		}

		EntityManager em = JPAProyectos.getEntityManager();

		try {
			// CONSULTA CON PARÁMETRO
			List<Empleado> empleados = em
					.createQuery("SELECT e FROM Empleado e WHERE e.salario > :salario", Empleado.class)
					.setParameter("salario", salarioMin).getResultList();

			System.out.println("\n📋 EMPLEADOS CON SALARIO > " + salarioMin + "€");
			System.out.println("==========================================");

			if (empleados.isEmpty()) {
				System.out.println("❌ No hay empleados con salario superior a " + salarioMin + "€");
			} else {
				for (Empleado e : empleados) {
					System.out.println(e.toString());
				}
				System.out.println("\n✅ Total: " + empleados.size() + " empleados");
			}

		} catch (Exception e) {
			System.out.println("Error en consulta: " + e.getMessage());
		} finally {
			em.close();
		}

	}

	private static void totalHorasAsignadas() {
		EntityManager em = JPAProyectos.getEntityManager();

		try {
			// CONSULTA ESTÁTICA - Definida en Asignacion.java con @NamedQuery
			TypedQuery<Long> query = em.createNamedQuery("Asignacion.totalHoras", Long.class);
			Long totalHoras = query.getSingleResult();

			System.out.println("\nTOTAL DE HORAS ASIGNADAS A PROYECTOS");
			System.out.println("========================================");

			if (totalHoras == null || totalHoras == 0) {
				System.out.println("No hay horas asignadas todavía");
			} else {
				System.out.println("🕒" + totalHoras + " horas en total");

				// Extra: calcular días laborables (8h/día)
				int diasLaborables = (int) (totalHoras / 8);
				System.out.println("   📅 Equivale a " + diasLaborables + " días de trabajo (8h/día)");
			}

		} catch (Exception e) {
			System.out.println("Error en consulta: " + e.getMessage());
		} finally {
			em.close();
		}
	}

	private static void empleadosPorProyecto() {
		System.out.print("Código del proyecto: ");
		String codigo = entrada.nextLine();

		EntityManager em = JPAProyectos.getEntityManager();

		try {
			List<Empleado> empleados = em.createQuery(
					"SELECT e FROM Empleado e JOIN e.listaAsignaciones a WHERE a.proyecto.codigo = :codigo",
					Empleado.class).setParameter("codigo", codigo).getResultList();

			System.out.println("\nEmpleados en proyecto " + codigo + ":");
			if (empleados.isEmpty()) {
				System.out.println("   No hay empleados");
			} else {
				for (Empleado e : empleados) {
					System.out.println("   - " + e.getNombre());
				}
			}

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			em.close();
		}
	}

	private static void buscar_por_Nombre() {
		
		EntityManager em = JPAProyectos.getEntityManager();
		
		
		System.out.print("Nombre del empleado: ");
		String nombre = entrada.nextLine();

		List<Empleado> lista = em.createQuery("SELECT e FROM Empleado e WHERE e.nombre LIKE :nombre", Empleado.class)
				.setParameter("nombre", "%" + nombre + "%").getResultList();

		for (Empleado empleado : lista) {
			System.out.println(empleado.toString());
		}
	}

	private static void crearDatosIniciales() {

		/**
		 * Crea 3 empleados, 2 proyectos y 3 asignaciones
		 * 
		 * SIN CONSULTAS - Operación directa con persist()
		 * 
		 * PRUEBA: Ejecutar este método y luego comprobar con opciones 7,8,9
		 */

		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			Empleado[] e = new Empleado[3];
			Proyecto[] proyectos = new Proyecto[2];
			for (int i = 0; i < 3; i++) {

				System.out.println("-- CREAR EMPLEADO --");

				System.out.println("Nombre");
				String nombre = entrada.nextLine();

				System.out.println("DNI");
				String dni = entrada.nextLine();

				System.out.println("Email");
				String email = entrada.nextLine();

				System.out.println("Sueldo");
				double sueldo = Double.parseDouble(entrada.nextLine());

				System.out.println("Escribe la fecha en este orden Dia-mes-año");
				String fecha = entrada.nextLine();

				String fechatroc[] = fecha.split("-");

				e[i] = new Empleado(dni, nombre, email, sueldo, LocalDate.of(Integer.parseInt(fechatroc[2]), // año
						Integer.parseInt(fechatroc[1]), // mes
						Integer.parseInt(fechatroc[0])));

				em.persist(e[i]);

			}

			System.out.println("\n--- CREAR 2 PROYECTOS ---");

			for (int i = 0; i < 2; i++) {
				System.out.println("\nProyecto " + (i + 1));

				System.out.print("Código (ej: PROY-001): ");
				String codigo = entrada.nextLine();

				System.out.print("Nombre: ");
				String nombreP = entrada.nextLine();

				System.out.print("Presupuesto: ");
				double presupuesto = Double.parseDouble(entrada.nextLine());

				System.out.print("Fecha inicio (dia-mes-año): ");
				String fechaIni = entrada.nextLine();
				String[] ini = fechaIni.split("-");
				LocalDate fechaInicio = LocalDate.of(Integer.parseInt(ini[2]), Integer.parseInt(ini[1]),
						Integer.parseInt(ini[0]));

				proyectos[i] = new Proyecto(codigo, nombreP, presupuesto, fechaInicio);
				em.persist(proyectos[i]);
				System.out.println("Proyecto guardado: " + nombreP);
			}

			System.out.println("Empleado Guardado");

			// ===========================================
			// 3. CREAR 3 ASIGNACIONES
			// ===========================================
			System.out.println("\n=== CREAR 3 ASIGNACIONES ===");

			// Mostrar empleados
			System.out.println("\n--- EMPLEADOS DISPONIBLES ---");
			for (int i = 0; i < e.length; i++) {
				System.out.println((i + 1) + ". " + e[i].getNombre() + " (" + e[i].getNif() + ")");
			}

			// Mostrar proyectos
			System.out.println("\n--- PROYECTOS DISPONIBLES ---");
			for (int i = 0; i < proyectos.length; i++) {
				System.out.println((i + 1) + ". " + proyectos[i].getNombre() + " (" + proyectos[i].getCodigo() + ")");
			}

			for (int i = 0; i < 3; i++) {
				System.out.println("\n--- Asignación " + (i + 1) + " ---");

				int numEmp = -1;
				while (numEmp < 0 || numEmp >= e.length) {
					System.out.print("Número de empleado (1-" + e.length + "): ");
					numEmp = Integer.parseInt(entrada.nextLine()) - 1;
				}

				int numProy = -1;
				while (numProy < 0 || numProy >= proyectos.length) {
					System.out.print("Número de proyecto (1-" + proyectos.length + "): ");
					numProy = Integer.parseInt(entrada.nextLine()) - 1;
				}

				System.out.print("Horas asignadas: ");
				int horas = Integer.parseInt(entrada.nextLine());

				Asignacion asignacion = new Asignacion(horas, e[numEmp], proyectos[numProy]);

				em.persist(asignacion);
				System.out.println("✅ Asignación creada: " + e[numEmp].getNombre() + " → "
						+ proyectos[numProy].getNombre() + " (" + horas + "h)");
			}

			// =================
			// CONFIRMAR
			// ==================
			tx.commit();
			System.out.println("\n🎉 ¡TODOS LOS DATOS GUARDADOS CORRECTAMENTE!");

		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			System.out.println("ERROR: " + e.getMessage());
			e.printStackTrace();
		} finally {
			em.close();
		}

	}

	private static void mostrar_Menu() {
		System.out.println("\n=== GESTIÓN DE EMPLEADOS Y PROYECTOS ===");
		System.out.println("1. Crear datos iniciales (3 empleados, 2 proyectos, 3 asignaciones)");
		System.out.println("2. Buscar por nombre");
		System.out.println("3. Mostrar empleados por proyecto (consulta dinámica)");
		System.out.println("4. Total horas asignadas (consulta estática - NamedQuery)");
		System.out.println("5. Empleados con salario mayor a X cantidad");
		System.out.println("7. Listar todos los empleados");
		System.out.println("8. Listar todos los proyectos");
		System.out.println("9. Listar todas las asignaciones");
		System.out.println("10. Añadir dirección a empleado");
		System.out.println("0. Salir");
		System.out.print("Seleccione una opción: ");
	}

	private static void crearBaseDatos() {

		System.out.println("Conectando a BBDD");
		em.getTransaction().begin();
		em.getTransaction().commit();

		System.out.println("Tablas Creadas");

	}
}
