package tema4mapeobjetorelacional.zejerciciofinal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAFinal {

	private static final String PERSISTENCE_UNIT_NAME = "UPLibreria";
    private static EntityManagerFactory factory;
    
    public static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null || !factory.isOpen()) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return factory;
    }
    
    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }
    
    public static void close() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
	
}
