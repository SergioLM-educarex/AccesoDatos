package tema4mapeobjetorelacional.zejerciciofinal;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;

import javax.persistence.TypedQuery;

public class MainEjercicioFinal {

	private static final String RESPUESTA_NO = "N";
	private static final String RESPUESTA_SI = "S";
	private static final int OPCION_SALIR = 0;
	private static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {

		// Creamos el EntityManager que nos permite operar con la BD
		EntityManager em = JPAPrestamos.getEntityManager();

		int op;

		try {
			// Al iniciar la transacción y hacer commit Hibernate
			// crea las tablas
			System.out.println("Conectando a la base de datos...");
			em.getTransaction().begin();
			em.getTransaction().commit();
			System.out.println("¡Tablas creadas correctamente!");

			do {
				mostrarMenu();
				op = Integer.parseInt(entrada.nextLine());
				operarMenu(op, em);

			} while (op != OPCION_SALIR);

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			em.getTransaction().rollback();
		} finally {
			em.close();
			JPAPrestamos.close();
			entrada.close();
		}
	}

	private static void operarMenu(int op, EntityManager em) {
		switch (op) {
		case 1:
			crearDatos(em);
			break;
		case 2:
			actualizar_Telefono(em);
			break;
		case 3:
			socios_Por_Libro(em);
			break;
		case 4:
			total_Prestamos(em);
			break;
		case 0:
			System.out.println("Saliendo...");
			break;
		default:
			System.out.println("Opción no válida");
			break;
		}
	}

	/**
	 * ── OPERACIÓN 4 ── Total préstamos (consulta ESTÁTICA)
	 *  createNamedQuery() → usa la @NamedQuery definida en Prestamos.java 
	 *  Se carga al arrancar el programa, más eficiente que la dinámica getSingleResult() 
	 *  porque COUNT() siempre devuelve un solo valor
	 * 
	 * @param em
	 */
	private static void total_Prestamos(EntityManager em) {

		try {
			TypedQuery<Long> consulta = em.createNamedQuery("Prestamos.countAll", Long.class);
			Long total = consulta.getSingleResult();
			System.out.println("Total de préstamos realizados: " + total);

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

	/**
	 * OPERACIÓN 3 Socios por libro (consulta DINÁMICA) createQuery() → se compila
	 * cada vez que se ejecuta TypedQuery<Socio> → garantiza que la lista devuelve
	 * objetos Socio :isbn → parámetro por nombre, se rellena con setParameter()
	 * 
	 * @param em
	 */
	private static void socios_Por_Libro(EntityManager em) {
		try {
			System.out.print("Introduce el ISBN del libro: ");
			String isbn = entrada.nextLine();

			// Consulta dinámica JPQL con TypedQuery
			String jpql = "SELECT p.socio FROM prestamos p WHERE p.libro.isbn = :isbn";
			TypedQuery<Socio> consulta = em.createQuery(jpql, Socio.class);
			consulta.setParameter("isbn", isbn);

			List<Socio> socios = consulta.getResultList();

			if (socios.isEmpty()) {
				System.out.println("Ningún socio ha sacado ese libro.");
			} else {
				System.out.println("Socios que han sacado el libro:");
				for (Socio s : socios) {
					System.out.println(s.toString());
				}
			}

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	/**
	 * OPERACIÓN 2 Actualizar teléfono find() busca por Clave primaria sin usar JPQL
	 * merge() sincroniza los cambios con la BD
	 */
	private static void actualizar_Telefono(EntityManager em) {
		try {
			System.out.println("Introduce el DNI del socio: ");
			String dni = entrada.nextLine();

			// Buscar el socio por clave primaria (sin consulta)
			Socio socio = em.find(Socio.class, dni);

			if (socio != null) {
				System.out.println("Introduce el nuevo teléfono: ");
				String nuevoTelefono = entrada.nextLine();

				em.getTransaction().begin();
				socio.setNumTelefono(nuevoTelefono);
				em.merge(socio); // Actualiza en BD
				em.getTransaction().commit();

				System.out.println("Teléfono actualizado correctamente!");
			} else {
				System.out.println("No se encontró ningún socio con DNI: " + dni);
			}

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			em.getTransaction().rollback();
		}

	}

	/*
	 * ─OPERACIÓN 1
	 *  Crear datos (SIN consulta) --> 
	 *  Orden importante: primero
	 * Libro y Socio, luego Prestamos porque Prestamos tiene FK que apuntan a ambos,
	 * LIBROS Y SOCIOS DEBEN CREARSE Y PERSISTIRSE ANTES PORQUE LOS
	 * NECESITAMOS EN PRESTAMOS
	 */
	private static void crearDatos(EntityManager em) {

		// BEGIN
		em.getTransaction().begin();

		// ── 1 LIBROS ──
		System.out.println("\n--- INTRODUCE LOS DATOS DE LOS LIBROS ---");
		Libro[] libros = new Libro[3];
		for (int i = 0; i < 3; i++) {
			System.out.println("\nLibro " + (i + 1) + ":");
			System.out.print("ISBN: ");
			String isbn = entrada.nextLine();
			System.out.print("Título: ");
			String titulo = entrada.nextLine();
			System.out.print("Autor: ");
			String autor = entrada.nextLine();
			System.out.print("Nº ejemplares: ");
			int numEjemplar = Integer.parseInt(entrada.nextLine());

			libros[i] = new Libro(isbn, numEjemplar, titulo, autor);
			em.persist(libros[i]);
		}

		// ── 2 SOCIOS ──
		System.out.println("\n--- INTRODUCE LOS DATOS DE LOS SOCIOS ---");
		Socio[] socios = new Socio[3];
		for (int i = 0; i < 3; i++) {
			System.out.println("\nSocio " + (i + 1) + ":");
			System.out.print("DNI: ");
			String dni = entrada.nextLine();
			System.out.print("Nombre: ");
			String nombre = entrada.nextLine();
			System.out.print("Teléfono: ");
			String telefono = entrada.nextLine();

			socios[i] = new Socio(dni, nombre, telefono);
			em.persist(socios[i]);
		}

		// ─ 3 PRÉSTAMOS
		System.out.println("\n--- CREANDO LOS 3 PRÉSTAMOS ---");
		for (int i = 0; i < 3; i++) {
			System.out.println("\nPréstamo " + (i + 1) + ":");

			String respuesta;
			do {
				System.out.print("¿Está prestado? (S/N): ");
				respuesta = entrada.nextLine().toUpperCase();
				if (!respuesta.equals(RESPUESTA_SI) && !respuesta.equals(RESPUESTA_NO)) {
					System.out.println("Opción no válida, introduce S o N");
				}
			} while (!respuesta.equals(RESPUESTA_SI) && !respuesta.equals(RESPUESTA_NO));

			boolean prestado = respuesta.equals(RESPUESTA_SI);

			Prestamo p = new Prestamo(prestado, socios[i], libros[i]);
			em.persist(p);
		}

		// COMMIT
		em.getTransaction().commit(); // ejecuta todos los INSERT a la vez
		System.out.println("Insertados Correctamente");

	}

	private static void mostrarMenu() {
		System.out.println("\n=== MENÚ PRÉSTAMOS ===");
		System.out.println("1. Crear libros, socios y préstamos");
		System.out.println("2. Actualizar teléfono de un socio");
		System.out.println("3. Socios que han sacado un libro");
		System.out.println("4. Total de préstamos realizados");
		System.out.println("0. Salir");
		System.out.print("Selecciona una opción: ");
	}
}