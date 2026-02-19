package tema4mapeobjetorelacional.ejemplos.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity(name="alumno")
@Table(name="alumno")
//Consulta JPQL predefinida
@NamedQuery(name = "verAlumnos", query = "SELECT a FROM alumno a where a.edad > :edad")

//Consulta SQL predefinida
@NamedNativeQuery(name = "alumno.veralumnos", query = "SELECT * FROM alumno", resultClass = Alumno.class)

public class Alumno implements Serializable {

	@Id()
	@Column(name = "dni")
	private String dni;
	
	@Column(name = "edad")
	private int edad;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "telefono")
	private int telefono;

	public Alumno(String dni, int edad, String nombre, int telefono) {
		super();
		this.dni = dni;
		this.edad = edad;
		this.nombre = nombre;
		this.telefono = telefono;
	}

	

	public Alumno() {
		super();
	}



	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", edad=" + edad + ", nombre=" + nombre + ", telefono=" + telefono + "]";
	}

}
