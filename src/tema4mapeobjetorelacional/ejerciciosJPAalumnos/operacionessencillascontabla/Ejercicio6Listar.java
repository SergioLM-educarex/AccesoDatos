package tema4mapeobjetorelacional.ejerciciosJPAalumnos.operacionessencillascontabla;





import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import tema4mapeobjetorelacional.ejerciciosJPAalumnos.beans.ProfesorJPA;

public class Ejercicio6Listar {

	private static final String UPA_ALUMNOS = "UPAlumnos";

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(UPA_ALUMNOS);
		System.out.println("factoria creada");
		
		EntityManager em = emf.createEntityManager();
		System.out.println("Entidad creada");
		
		//======================
		//Ejercicio 6 Muestra el contenido de la tabla Profesor
		//====
		
		try {
			
			//Esta es la consulta
		List <ProfesorJPA> profesores = em.createQuery("SELECT p FROM profesor p", ProfesorJPA.class).getResultList();
		
		//Muestra las personas consultadas
		for (ProfesorJPA profesorJPA : profesores) {
			System.out.println(profesorJPA.toString());
		}
		
			
			
			
		} catch (Exception e) {
			System.out.println("Estas en la excepcion");
					e.printStackTrace();
		}finally {
			em.close();
			emf.close();
		}
		
		

	}

}
