package tema4mapeobjetorelacional.zApuntes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity(name = "asignacion")
@Table(name = "asignacion")
@NamedQuery(name = "Asignacion.totalHoras", query = "SELECT SUM(a.horasAsignadas) FROM asignacion a")

public class Asignacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long idAsignacion;
	
	@Column(name = "horasAsignadas")
	private Integer horasAsignadas;
	
	@ManyToOne
	@JoinColumn(name = "emplado_id") 
	private Empleado empleado;
	
	@ManyToOne
	@JoinColumn(name = "proyecto_id") 
	private Proyecto proyecto;

	public Asignacion(Integer horasAsignadas, Empleado empleado, Proyecto proyecto) {
		super();
		this.horasAsignadas = horasAsignadas;
		this.empleado = empleado;
		this.proyecto = proyecto;
	}

	public Asignacion() {
		super();
	}

	public Long getIdAsignacion() {
		return idAsignacion;
	}

	public void setIdAsignacion(Long idAsignacion) {
		this.idAsignacion = idAsignacion;
	}

	public Integer getHorasAsignadas() {
		return horasAsignadas;
	}

	public void setHorasAsignadas(Integer horasAsignadas) {
		this.horasAsignadas = horasAsignadas;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	@Override
	public String toString() {
		return "Asignacion [idAsignacion=" + idAsignacion + ", horasAsignadas=" + horasAsignadas + ", empleado="
				+ empleado + ", proyecto=" + proyecto + "]";
	}
	
	
	
	
	
	
	
}
