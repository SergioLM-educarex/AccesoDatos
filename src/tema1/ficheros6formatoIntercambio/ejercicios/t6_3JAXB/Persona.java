package tema1.ficheros6formatoIntercambio.ejercicios.t6_3JAXB;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder= {"dni","nombre","edad"})

public class Persona {

	private String dni, nombre;
	private int edad;
	
	
	
	public Persona() {
		super();
	}

	public Persona(String dni, String nombre, int edad) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.edad = edad;
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
	
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	
	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", nombre=" + nombre + ", edad=" + edad + "]";
	}
	
	
	
}
