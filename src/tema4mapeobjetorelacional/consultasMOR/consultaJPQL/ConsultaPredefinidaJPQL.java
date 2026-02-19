package tema4mapeobjetorelacional.consultasMOR.consultaJPQL;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;



import tema4mapeobjetorelacional.ejemplos.beans.Alumno;

public class ConsultaPredefinidaJPQL {

	// Este ejercicio referencia al ejemplo.beans.Alumno

	public static void main(String[] args) {

		// Crear la factoria de EntityManager
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UPPersonas");
		System.out.println("Factoria creada");

		EntityManager em = factory.createEntityManager();
		System.out.println("EntityManager creado");

		

		try {
			// Iniciar la transacción
			EntityTransaction tx = em.getTransaction();
			tx.begin(); // <<--- MUY IMPORTANTE

			//Esta de a continuacion está mal
			//javax.persistence.Query consulta = em.createNamedQuery("Alumno.verAlumnos");
			
			// ---->> Esta es la correcta
			javax.persistence.Query consulta = em.createNamedQuery("verAlumnos");



			consulta.setParameter("edad", 4);
			List<Alumno> resultados = consulta.getResultList();

			for (Alumno alumno : resultados) {
				System.out.println("Nombre " + alumno.getNombre() + ", Edad: " + alumno.getEdad());
			}

			// Confirmar los cambios en la base de datos
			tx.commit();
			System.out.println("FIN");

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
			
		}
	}

}
