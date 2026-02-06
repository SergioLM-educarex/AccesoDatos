package tema4mapeobjetorelacional.ejercicioCRUD;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CrudProducto {

	private static final int OP_SALIR = 6;

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("UPProductos");

	private static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {

		int op = 0;

		do {
			mostrar_menu();

			try {
				op = Integer.parseInt(entrada.nextLine());
				operarMenu(op);

			} catch (NumberFormatException e) {
				System.out.println("Ingrese un valor numericoo");
			}

		} while (op != OP_SALIR);

		// Cierre global de recursos
		entrada.close();
		JPAUtil.close();

		System.out.println("Aplicación finalizada correctamente");

	}

	private static void operarMenu(int op) {
		switch (op) {
		case 1:
			insertar_Producto();
			break;
		case 2:
			listar_Productos();
			break;

		case 3:
			buscar_Producto();
			break;

		case 4:
			actualizar_Producto();
			break;

		case 5:
			eliminar_Producto();
			break;
		case 6:
			System.out.println("Saliendo....");
			break;

		default:
			System.out.println("Opcion no válida");
			break;
		}

	}

	private static void eliminar_Producto() {
		EntityManager em = emf.createEntityManager();

		int id = 0;
		System.out.println("Inserte el id:");
		id = Integer.parseInt(entrada.nextLine());

		Producto p = em.find(Producto.class, id);

		if (p != null) {
			em.getTransaction().begin();
			em.remove(p); // Elimina el producto de la base de datos
			em.getTransaction().commit();
		}

	}

	private static void actualizar_Producto() {
		EntityManager em = emf.createEntityManager();

		try {
			int id = 0;
			System.out.println("Inserte el id:");
			id = Integer.parseInt(entrada.nextLine());

			Producto p = em.find(Producto.class, id);

			if (p != null) {

				int opcion;
				do {
					System.out.println("¿Que quieres cambiar?");
					System.out.println("1. Nombre");
					System.out.println("2. Precio");
					System.out.println("3. Los 2");
					opcion = Integer.parseInt(entrada.nextLine());

					switch (opcion) {
					case 1:
						pedir_Nuevo_Nombre(em, p);
						System.out.println("Campos precio modificado para" + p.getId() + " correctamente");
						mostrar_menu();
						break;

					case 2:
						pedir_Nuevo_Precio(em, p);
						System.out.println("Campos precio modificado para" + p.getId() + " correctamente");
						mostrar_menu();

						break;

					case 3:
						pedir_Nuevo_Nombre(em, p);
						pedir_Nuevo_Precio(em, p);
						System.out.println("Campos modificados correctamente");
						mostrar_menu();
						break;

					default:
						System.out.println("Opcion no válida");
						break;
					}

				} while (opcion != 1 || opcion != 2 || opcion != 3);

			} else {
				System.out.println("No se encontró producto con id " + id);
			}
		} catch (Exception e) {
			System.out.println("Estas en la excepcion");
			e.printStackTrace();
		} finally {
			em.close();
		}

	}

	private static void pedir_Nuevo_Nombre(EntityManager em, Producto p) {
		String nombreNuevo;

		System.out.println("Ingrese nombre nuevo: ");
		nombreNuevo = entrada.nextLine();

		em.getTransaction().begin();
		p.setNombre(nombreNuevo);
		em.merge(p); // Actualiza el producto en la base de datos
		em.getTransaction().commit();
	}

	private static void pedir_Nuevo_Precio(EntityManager em, Producto p) {
		double precioNuevo;
		System.out.println("Ingrese el nuevo precio: ");
		precioNuevo = Double.parseDouble(entrada.nextLine());

		em.getTransaction().begin();
		p.setPrecio(precioNuevo);
		em.merge(p); // Actualiza el producto en la base de datos
		em.getTransaction().commit();
	}

	private static void buscar_Producto() {
		EntityManager em = emf.createEntityManager();

		int id = 0;
		System.out.println("Inserte el id:");
		id = Integer.parseInt(entrada.nextLine());

		Producto p = em.find(Producto.class, id);

		if (p != null) {
			System.out.println("Producto encontrado: " + p.toString());
		} else {
			System.out.println("No se encontró producto con id " + id);
		}

		em.close();
	}

	private static void listar_Productos() {

		EntityManager em = emf.createEntityManager();

		try {
			// Consultar todas las personas
			List<Producto> productos = em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
			// Mostrar las personas consultadas
			for (Producto p : productos) {
				System.out.println(p.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}

	}

	private static void insertar_Producto() {

		EntityManager em = JPAUtil.getEntityManager();
		System.out.println("✔ EntityManager creado correctamente");

		EntityTransaction tx = em.getTransaction();

		try {
			System.out.print("Inserte el nombre del producto: ");
			String nombre = entrada.nextLine();

			System.out.print("Inserte el precio: ");
			double precioProducto = Double.parseDouble(entrada.nextLine());

			Producto p = new Producto(nombre, precioProducto);

			em.getTransaction().begin();

			em.persist(p);

			em.getTransaction().commit();

			System.out.println("✔ " + p + " ingresado correctamente");

		} catch (Exception e) {

			System.err.println("Error al insertar el producto");
			e.printStackTrace();
		} finally {
			em.close();
			System.out.println("EntityManager cerrado");
		}
	}

	private static void mostrar_menu() {
		System.out.println("==========================");
		System.out.println("||    CRUD de PRODUCTOS ||");
		System.out.println("==========================");

		System.out.println("1. Insertar producto\r\n" + "2. Listar productos\r\n" + "3. Buscar producto\r\n"
				+ "4. Actualizar producto\r\n" + "5. Eliminar producto\r\n" + "6. Salir");
	}
}
