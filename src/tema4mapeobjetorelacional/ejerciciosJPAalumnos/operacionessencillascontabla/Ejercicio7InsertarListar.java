package tema4mapeobjetorelacional.ejerciciosJPAalumnos.operacionessencillascontabla;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import tema4mapeobjetorelacional.ejerciciosJPAalumnos.beans.ProfesorJPA;

public class Ejercicio7InsertarListar {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UPAlumnos");

		System.out.println("Factoria creada");

		EntityManager em = emf.createEntityManager();

		System.out.println("Entidad Creada");

		Scanner entrada = new Scanner(System.in);

		System.out.println("Inserte el nombre");
		String nombreProfesor = entrada.nextLine();

		try {

			EntityTransaction tx = em.getTransaction();
			tx.begin();

			ProfesorJPA profesorJPA = new ProfesorJPA(nombreProfesor);
			
			// Persistir la persona
			em.persist(profesorJPA);

			tx.commit();
			System.out.println("Profesor " + profesorJPA + " creado");
			
			
			mostrarProfesores(em);
			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Ocurrió un error. Revirtiendo cambios");
		} finally {
			em.close();
			emf.close();
			entrada.close();
		}

		// Revertir la transacción si hubo error
		if (em.getTransaction().isActive()) {
			em.getTransaction().rollback();
		}
	}

	private static void mostrarProfesores(EntityManager em) {
		System.out.println("Lista de profesores:");
		
		List <ProfesorJPA> profesores = em.createQuery("SELECT p FROM profesor p", ProfesorJPA.class).getResultList();
		
		//Muestra las personas consultadas
		for (ProfesorJPA p : profesores) {
			System.out.println(p.toString());
		}
	}

}
