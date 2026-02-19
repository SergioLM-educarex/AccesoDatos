package tema4mapeobjetorelacional.consultasMOR.consultasnativasSQL;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import tema4mapeobjetorelacional.ejemplos.beans.Alumno;

public class ConsultaDinamicaSQL {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UPPersonas");
		System.out.println("Factoria creada");

		EntityManager em = factory.createEntityManager();
		System.out.println("EntityManager creado");

		String sql = "SELECT * FROM Alumno where edad > ?";

		Query query = em.createNativeQuery(sql, Alumno.class);
		
		//OJO AL UTILIZAR ESTO
	
		query.setParameter(1, 5);
		
		List<Alumno> resutltado = query.getResultList();
		
		
		
		for (Alumno alumno : resutltado) {

			System.out.println("Nombre: " + alumno.getNombre() + ",\t Edad: " + alumno.getEdad());

		}
		System.out.println("FIN");

	}
}
