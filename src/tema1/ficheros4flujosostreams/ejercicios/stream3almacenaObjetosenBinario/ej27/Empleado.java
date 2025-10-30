package tema1.ficheros4flujosostreams.ejercicios.stream3almacenaObjetosenBinario.ej27;

import java.io.Serializable;

public class Empleado implements Serializable{

	private String dni;
	private String nombre;
	private double sueldo;
	
	
	public Empleado(String dni, String nombre, double sueldo) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.sueldo = sueldo;
	}


	public Empleado() {
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


	public double getSueldo() {
		return sueldo;
	}


	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}


	@Override
	public String toString() {
		return "Empleado [dni=" + dni + ", nombre=" + nombre + ", sueldo=" + sueldo + "]";
	}
	
	
	
	
}
