package tema4mapeobjetorelacional.facturacion.modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String apellidos;
    
    @Column()
    private String direccion;
    
    // LAZY por defecto - NO incluir facturas en toString
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Factura> facturas = new ArrayList<>();
    
    public Cliente() {}
    
    public Cliente(String nombre, String apellidos, String direccion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
    }
    
    public void addFactura(Factura factura) {
        facturas.add(factura);
        factura.setCliente(this);
    }
    
    public void removeFactura(Factura factura) {
        facturas.remove(factura);
        factura.setCliente(null);
    }
    
    public Long getIdCliente() {
        return idCliente;
    }
    
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellidos() {
        return apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public List<Factura> getFacturas() {
        return facturas;
    }
    
    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }
    
    // SIN FACTURAS EN EL toString - esto evita el LazyInitializationException
    @Override
    public String toString() {
        return "Cliente [idCliente=" + idCliente + ", nombre=" + nombre + 
               ", apellidos=" + apellidos + ", direccion=" + direccion + "]";
    }
}