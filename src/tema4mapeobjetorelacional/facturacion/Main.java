package tema4mapeobjetorelacional.facturacion;

import tema4mapeobjetorelacional.facturacion.dao.ClienteDAO;
import tema4mapeobjetorelacional.facturacion.dao.FacturaDAO;
import tema4mapeobjetorelacional.facturacion.modelo.Cliente;
import tema4mapeobjetorelacional.facturacion.modelo.Factura;
import tema4mapeobjetorelacional.facturacion.util.JPAUtil;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ClienteDAO clienteDAO = new ClienteDAO();
        FacturaDAO facturaDAO = new FacturaDAO();

        try {
            // 1. CREAR CLIENTES
            System.out.println("=== CREAR CLIENTES ===");
            Cliente cliente1 = new Cliente("Juan", "Pérez García", "Calle Mayor 123");
            Cliente cliente2 = new Cliente("María", "López Ruiz", "Avenida Principal 456");

            clienteDAO.crear(cliente1);
            clienteDAO.crear(cliente2);

            // 2. CREAR FACTURAS PARA LOS CLIENTES
            System.out.println("\n=== CREAR FACTURAS ===");
            Factura factura1 = new Factura(LocalDate.now(), 150.50);
            factura1.setCliente(cliente1);

            Factura factura2 = new Factura(LocalDate.now().minusDays(5), 320.75);
            factura2.setCliente(cliente1);

            Factura factura3 = new Factura(LocalDate.now().minusMonths(3), 99.99);
            factura3.setCliente(cliente2);

            facturaDAO.crear(factura1);
            facturaDAO.crear(factura2);
            facturaDAO.crear(factura3);

            // 3. LISTAR TODOS LOS CLIENTES
            System.out.println("\n=== LISTAR TODOS LOS CLIENTES ===");
            List<Cliente> clientes = clienteDAO.listarTodos();
            for (Cliente c : clientes) {
                System.out.println(c);
            }

            // 4. BUSCAR CLIENTE POR ID
            System.out.println("\n=== BUSCAR CLIENTE POR ID ===");
            Cliente clienteBuscado = clienteDAO.buscarPorId(cliente1.getIdCliente());
            if (clienteBuscado != null) {
                System.out.println("Cliente encontrado: " + clienteBuscado);
            }

            // 5. LISTAR TODAS LAS FACTURAS
            System.out.println("\n=== LISTAR TODAS LAS FACTURAS ===");
            List<Factura> todasFacturas = facturaDAO.listarTodas();
            for (Factura f : todasFacturas) {
                System.out.println(f);
            }

            // 6. LISTAR FACTURAS POR CLIENTE
            System.out.println("\n=== FACTURAS DEL CLIENTE 1 ===");
            List<Factura> facturasCliente1 = facturaDAO.listarPorCliente(cliente1.getIdCliente());
            for (Factura f : facturasCliente1) {
                System.out.println(f);
            }

            // 7. ACTUALIZAR CLIENTE
            System.out.println("\n=== ACTUALIZAR CLIENTE ===");
            cliente1.setDireccion("Nueva Dirección 789");
            clienteDAO.actualizar(cliente1);
            Cliente clienteActualizado = clienteDAO.buscarPorId(cliente1.getIdCliente());
            System.out.println("Cliente actualizado: " + clienteActualizado);

            // 8. ACTUALIZAR FACTURA
            System.out.println("\n=== ACTUALIZAR FACTURA ===");
            factura1.setTotal(200.00);
            facturaDAO.actualizar(factura1);
            Factura facturaActualizada = facturaDAO.buscarPorId(factura1.getIdFactura());
            System.out.println("Factura actualizada: " + facturaActualizada);

            // 9. ELIMINAR UNA FACTURA
            System.out.println("\n=== ELIMINAR FACTURA ===");
            facturaDAO.eliminar(factura3.getIdFactura());
            System.out.println("Facturas restantes:");
            List<Factura> facturasRestantes = facturaDAO.listarTodas();
            for (Factura f : facturasRestantes) {
                System.out.println(f);
            }

            // 10. ELIMINAR UN CLIENTE (esto eliminará también sus facturas por cascade)
            System.out.println("\n=== ELIMINAR CLIENTE ===");
            clienteDAO.eliminar(cliente2.getIdCliente());
            System.out.println("Clientes restantes:");
            List<Cliente> clientesRestantes = clienteDAO.listarTodos();
            for (Cliente c : clientesRestantes) {
                System.out.println(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar EntityManagerFactory
            JPAUtil.close();
            System.out.println("\n=== Aplicación finalizada ===");
        }
    }
}