package tema1.prueba.ej2;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@JsonIgnoreProperties(ignoreUnknown = true)

@JsonPropertyOrder({"dni", "nombre", "edad"})

@XmlType(propOrder = { "dni", "telefono", "email"})
public class PersonaG implements Serializable{

	private String dni;

	private String nombre;
	
	private int edad;

	@JsonIgnore
	private int telefono;
	@JsonIgnore
	private String email;

	public PersonaG(String dni, String nombre, int edad, int telefono, String email) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.edad = edad;
		this.telefono = telefono;
		this.email = email;
	}

	public PersonaG() {
		super();
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@XmlTransient
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@XmlTransient
	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
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
		return "PersonaG [dni=" + dni + ", nombre=" + nombre + ", edad=" + edad + ", telefono=" + telefono + ", email="
				+ email + "]";
	}

}
