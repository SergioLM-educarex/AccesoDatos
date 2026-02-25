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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Empleado")
@Table(name = "empleado")
public class Empleado implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nif", unique = true)
	private String nif;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "salario")
	private Double salario;
	
	@Column(name = "fechaIngreso")
	private LocalDate fechaIngreso;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "direccion_id", referencedColumnName = "id")
	private Direccion direccion;
	
	@OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Asignacion> listaAsignaciones;

	public Empleado( String nif, String nombre, String email, Double salario, LocalDate fechaIngreso,
			List<Asignacion> listaAsignaciones) {
		super();
		
		this.nif = nif;
		this.nombre = nombre;
		this.email = email;
		this.salario = salario;
		this.fechaIngreso = fechaIngreso;
		this.listaAsignaciones = listaAsignaciones;
	}

	public Empleado() {
		super();
	}

	public Empleado(String nif, String nombre, String email, Double salario, LocalDate fechaIngreso) {
		super();
		this.nif = nif;
		this.nombre = nombre;
		this.email = email;
		this.salario = salario;
		this.fechaIngreso = fechaIngreso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public List<Asignacion> getListaAsignaciones() {
		return listaAsignaciones;
	}

	public void setListaAsignaciones(List<Asignacion> listaAsignaciones) {
		this.listaAsignaciones = listaAsignaciones;
	}
	
	

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nif=" + nif + ", nombre=" + nombre + ", email=" + email + ", salario="
				+ salario + ", fechaIngreso=" + fechaIngreso+"]";
	}
	
	

}
