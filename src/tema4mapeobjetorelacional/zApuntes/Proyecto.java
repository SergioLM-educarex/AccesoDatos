package tema4mapeobjetorelacional.zApuntes;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "Proyecto")
@Table(name = "proyecto")
@NamedNativeQuery(name = "Proyecto.listarProyectos",query = "select * from proyecto", resultClass = Proyecto.class)
public class Proyecto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProyecto;

	@Column(name = "codigo", unique = true)
	private String codigo;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "presupuesto")
	private Double presupuesto;

	@Column(name = "fechaInicio")
	private LocalDate fechaInicio;

	@Column(name = "fechaFin", nullable = true)
	private LocalDate fechaFin;

	@OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Asignacion> listaAsignaciones;

	public Proyecto(String codigo, String nombre, Double presupuesto, LocalDate fechaInicio) {
		super();
		
		this.codigo = codigo;
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.fechaInicio = fechaInicio;
	
	}

	public Proyecto() {
		super();
	}

	

	public Long getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(Double presupuesto) {
		this.presupuesto = presupuesto;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public List<Asignacion> getListaAsignaciones() {
		return listaAsignaciones;
	}

	public void setListaAsignaciones(List<Asignacion> listaAsignaciones) {
		this.listaAsignaciones = listaAsignaciones;
	}

	@Override
	public String toString() {
		return "Proyecto [idProyecto=" + idProyecto + ", codigo=" + codigo + ", nombre=" + nombre + ", presupuesto="
				+ presupuesto + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", listaAsignaciones="
				 + "]";
	}

}
