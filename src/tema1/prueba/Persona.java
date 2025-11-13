package tema1.prueba;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"dni","nombre","edad"})


public class Persona implements Serializable {

	private String dni;
	private String nombre;
	private Integer edad;
	@JsonIgnore
	private int telefono;
	@JsonIgnore
	private String email;
	
	
	public Persona(String dni, String nombre, Integer  edad, int telefono, String email) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.edad = edad;
		this.telefono = telefono;
		this.email = email;
	}


	public Persona() {
		super();
	}

	
	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	
	public int getTelefono() {
		return telefono;
	}


	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", nombre=" + nombre + ", edad=" + edad + ", telefono=" + telefono + ", email="
				+ email + "]";
	}
	
	

}
