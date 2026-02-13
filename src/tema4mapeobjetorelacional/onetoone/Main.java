package tema4mapeobjetorelacional.onetoone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UPPerfilUsuario");
        EntityManager em = emf.createEntityManager();
        
        try {
            // Insertar datos
            em.getTransaction().begin();
            
            // Crear perfil
            Perfil perfil = new Perfil(
                "Desarrollador Java con 5 años de experiencia",
                "555-1234",
                "Calle Principal 123"
            );
            
            // Crear usuario y asociar perfil
            Usuario usuario = new Usuario("Juan Pérez", "juan@example.com");
            usuario.setPerfil(perfil);
            
            // Guardar (cascade guardará también el perfil)
            em.persist(usuario);
            
            em.getTransaction().commit();
            System.out.println("Usuario guardado con ID: " + usuario.getId());
            
            // Consultar
            em.getTransaction().begin();
            
            Usuario usuarioConsultado = em.find(Usuario.class, usuario.getId());
            System.out.println("\n--- Datos del Usuario ---");
            System.out.println(usuarioConsultado);
            System.out.println("\n--- Perfil Asociado ---");
            System.out.println(usuarioConsultado.getPerfil());
            
            em.getTransaction().commit();
            
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}