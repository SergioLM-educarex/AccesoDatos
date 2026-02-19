package tema4mapeobjetorelacional.consultasMOR.consultaJPQL;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import tema4mapeobjetorelacional.ejemplos.beans.Alumno;

public class ConsultaDinamicaJPQL {

	public static void main(String[] args) {

		// Crear la factoria de EntityManager
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UPPersonas");
		System.out.println("Factoria creada");

		EntityManager em = factory.createEntityManager();
		System.out.println("EntityManager creado");

		String jpql = "SELECT a FROM alumno a";
		Query query = em.createQuery(jpql);
		
		List<Alumno> resultados = query.getResultList();
		
		for (Alumno alumno : resultados) {
			
			System.out.println("Nombre: "+ alumno.getNombre()+ ",\t Edad: "+alumno.getEdad());
			
		}
		System.out.println("FIN");
		

	}

}
