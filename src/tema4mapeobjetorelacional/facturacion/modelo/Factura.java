package tema4mapeobjetorelacional.facturacion.modelo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "facturas")
public class Factura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private Long idFactura;
    
    @Column(nullable = false)
    private LocalDate fecha;
    
    @Column(nullable = false)
    private Double total;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
    
    public Factura() {}
    
    public Factura(LocalDate fecha, Double total) {
        this.fecha = fecha;
        this.total = total;
    }
    
    public Long getIdFactura() {
        return idFactura;
    }
    
    public void setIdFactura(Long idFactura) {
        this.idFactura = idFactura;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }
    
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    public Double getTotal() {
        return total;
    }
    
    public void setTotal(Double total) {
        this.total = total;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    @Override
    public String toString() {
        return "Factura [idFactura=" + idFactura + ", fecha=" + fecha + ", total=" + total + "€]";
    }
}