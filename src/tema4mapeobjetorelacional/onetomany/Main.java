package tema4mapeobjetorelacional.onetomany;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        // 1️⃣ Crear EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UPProductosG");
        EntityManager em = emf.createEntityManager();

        try {
            // 2️⃣ Iniciar transacción
            em.getTransaction().begin();

            // 3️⃣ Crear y persistir categoría
            Categoria categoria = new Categoria();
            categoria.setNombre("Electrónica");
            categoria.setProductos(new ArrayList<>()); // Inicializar lista
            em.persist(categoria);

            // 4️⃣ Crear producto y asignarle la categoría
            ProductoJPA producto = new ProductoJPA();
            producto.setNombre("TV");
            producto.setPrecio(500);
            producto.setCategoria(categoria);

            // 5️⃣ Añadir el producto a la lista de la categoría
            categoria.getProductos().add(producto);

            // 6️⃣ Persistir producto
            em.persist(producto);

            // 7️⃣ Confirmar transacción
            em.getTransaction().commit();

            // 8️⃣ Consultar la categoría y sus productos
            Categoria catDB = em.find(Categoria.class, categoria.getId());
            System.out.println("Categoría: " + catDB.getNombre());
            catDB.getProductos().forEach(p -> System.out.println("  Producto: " + p.getNombre() + " - Precio: " + p.getPrecio()));

        } finally {
            // 9️⃣ Cerrar EntityManager
            em.close();
            emf.close();
        }
    }
}
