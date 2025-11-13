package tema1.ficheros6formatoIntercambio.jaxb.ejercicios.ejercicio44.beans;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"dni", "nombre","cargo"})
public class Trabajador {

	private String dni;
	private String nombre;
	private String cargo;
	
	public Trabajador(String dni, String nombre, String cargo) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.cargo = cargo;
	}

	public Trabajador() {
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

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	@Override
	public String toString() {
		return "Trabajador [dni=" + dni + ", nombre=" + nombre + ", cargo=" + cargo + "]";
	}
	
	
	
}
