package tema4mapeobjetorelacional.ejerciciosJPAalumnos.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name = "alumno")
public class AlumnoJPA implements Serializable {
	
	@Id()
	@Column(name = "dni")
	private int id;
	
	@Column(name ="nombre")
	private String nombre;
	
	@Column(name ="curso")
	private String curso;
	@Column(name ="telefono")
	private int telefono;
	public AlumnoJPA(int id, String nombre, String curso, int telefono) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.curso = curso;
		this.telefono = telefono;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	
	

}
