package tema4mapeobjetorelacional.facturacion.dao;

import tema4mapeobjetorelacional.facturacion.modelo.Cliente;
import tema4mapeobjetorelacional.facturacion.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClienteDAO {
    
    // Crear cliente
    public void crear(Cliente cliente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
            System.out.println("Cliente creado con ID: " + cliente.getIdCliente());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    // Buscar cliente por ID
    public Cliente buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        Cliente cliente = null;
        try {
            cliente = em.find(Cliente.class, id);
        } finally {
            em.close();
        }
        return cliente;
    }
    
    
    // Listar todos los clientes
    public List<Cliente> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Cliente> clientes = null;
        try {
            TypedQuery<Cliente> query = em.createQuery(
                "SELECT c FROM Cliente c", Cliente.class
            );
            clientes = query.getResultList();
        } finally {
            em.close();
        }
        return clientes;
    }
    
    // Actualizar cliente
    public void actualizar(Cliente cliente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
            System.out.println("Cliente actualizado");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    // Eliminar cliente
    public void eliminar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, id);
            if (cliente != null) {
                em.remove(cliente);
                System.out.println("Cliente eliminado");
            } else {
                System.out.println("Cliente no encontrado");
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    
   
}