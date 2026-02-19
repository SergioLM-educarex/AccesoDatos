package tema4mapeobjetorelacional.consultasMOR.consultasnativasSQL;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import tema4mapeobjetorelacional.ejemplos.beans.Alumno;

public class ConsultaPredefinidaSQL {

	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UPPersonas");
		System.out.println("Factoria creada");

		EntityManager em = factory.createEntityManager();
		System.out.println("EntityManager creado");

		Query query = em.createNamedQuery("alumno.veralumnos");
		
		//query.setParameter("edad", 4);
		
		List<Alumno> resutltado = query.getResultList();

		for (Alumno alumno : resutltado) {
			System.out.println(alumno.toString());
		}

	}

}
