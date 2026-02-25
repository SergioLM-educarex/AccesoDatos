package tema4mapeobjetorelacional.zApuntes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAProyectos {

	private static final String PERSISTENCE_UNIT_NAME = "UPProyectosExamen";
    private static EntityManagerFactory factory;
    
    public static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null || !factory.isOpen()) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return factory;
    }
    
    
    /**
     * Crea y devuelve un EntityManager nuevo.
     *
     * El EntityManager es el objeto con el que interactuamos
     * directamente para hacer operaciones CRUD:
     * - em.persist()  → INSERT
     * - em.find()     → SELECT por clave primaria
     * - em.merge()    → UPDATE
     * - em.remove()   → DELETE
     * - em.createQuery() → SELECT con JPQL
     *
     * Cada EntityManager gestiona su propio contexto de persistencia,
     * que es la memoria donde Hibernate guarda los objetos que
     * está gestionando antes de sincronizarlos con la BD.
     */
    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }
    
    public static void close() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
	
}
