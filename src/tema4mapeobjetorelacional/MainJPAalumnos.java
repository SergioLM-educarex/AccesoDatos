package tema4mapeobjetorelacional;



import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import tema4mapeobjetorelacional.ejerciciosJPAalumnos.beans.AlumnoJPA;
import tema4mapeobjetorelacional.ejerciciosJPAalumnos.beans.ProfesorJPA;

public class MainJPAalumnos {

	public static void main(String[] args) {
		// Crear la factoria de EntityManager
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UPAlumnos");
		System.out.println("Factoria creada");

		EntityManager em = factory.createEntityManager();
		System.out.println("EntityManager creado");

		Scanner entrada = new Scanner(System.in);

		try {
			// Iniciar la transacción
			EntityTransaction tx = em.getTransaction();
			tx.begin(); // <<--- MUY IMPORTANTE

			// Crear un objeto Persona
			AlumnoJPA alumno = new AlumnoJPA(1, "Sergio", "1DAM", 635);
			ProfesorJPA pro = new ProfesorJPA("Ramon");

			// Persistir la persona
			em.persist(pro);

			// Confirmar los cambios en la base de datos
			tx.commit();
			System.out.println("Profesor guardado con éxito");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Ocurrió un error. Revirtiendo cambios");

			// Revertir la transacción si hubo error
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

		} finally {
			em.close();
			factory.close();
			entrada.close();
		}
	}

}
