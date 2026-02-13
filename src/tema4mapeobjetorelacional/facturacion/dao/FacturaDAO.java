package tema4mapeobjetorelacional.facturacion.dao;

import tema4mapeobjetorelacional.facturacion.modelo.Factura;
import tema4mapeobjetorelacional.facturacion.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class FacturaDAO {

	// Crear factura
	public void crear(Factura factura) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(factura);
			em.getTransaction().commit();
			System.out.println("Factura creada con ID: " + factura.getIdFactura());
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	// Buscar factura por ID
	public Factura buscarPorId(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Factura factura = null;
		try {
			factura = em.find(Factura.class, id);
		} finally {
			em.close();
		}
		return factura;
	}

	// Listar todas las facturas
	public List<Factura> listarTodas() {
		EntityManager em = JPAUtil.getEntityManager();
		List<Factura> facturas = null;
		try {
			TypedQuery<Factura> query = em.createQuery("SELECT f FROM Factura f", Factura.class);
			facturas = query.getResultList();
		} finally {
			em.close();
		}
		return facturas;
	}

	// Listar facturas por cliente
	public List<Factura> listarPorCliente(Long idCliente) {
		EntityManager em = JPAUtil.getEntityManager();
		List<Factura> facturas = null;
		try {
			TypedQuery<Factura> query = em.createQuery("SELECT f FROM Factura f WHERE f.cliente.idCliente = :idCliente",
					Factura.class);
			query.setParameter("idCliente", idCliente);
			facturas = query.getResultList();
		} finally {
			em.close();
		}
		return facturas;
	}

	// Actualizar factura
	public void actualizar(Factura factura) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(factura);
			em.getTransaction().commit();
			System.out.println("Factura actualizada");
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	// Eliminar factura
	public void eliminar(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			Factura factura = em.find(Factura.class, id);
			if (factura != null) {
				em.remove(factura);
				System.out.println("Factura eliminada");
			} else {
				System.out.println("Factura no encontrada");
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