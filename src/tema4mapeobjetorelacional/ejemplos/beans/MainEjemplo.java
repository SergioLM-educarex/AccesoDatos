package tema4mapeobjetorelacional.ejemplos.beans;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MainEjemplo {

    public static void main(String[] args) {

        // Crear la factoria de EntityManager
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("UPPersonas");
        System.out.println("Factoria creada");

        EntityManager em = factory.createEntityManager();
        System.out.println("EntityManager creado");

        Scanner entrada = new Scanner(System.in);

        try {
            // Iniciar la transacción
            EntityTransaction tx = em.getTransaction();
            tx.begin(); // <<--- MUY IMPORTANTE

            // Crear un objeto Persona
            Persona p = new Persona("123", 32, "Sergio", 666222555);

            // Persistir la persona
            em.persist(p);

            // Confirmar los cambios en la base de datos
            tx.commit();
            System.out.println("Persona guardada con éxito");

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
