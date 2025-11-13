package tema1.ficheros6formatoIntercambio.dom.ejercicios.ejercicio37;

public class Alumno implements Comparable<Alumno> {

	private int numExpediente;
	private String nombre;
	private double nota;
	
	
	public Alumno(int numExpediente, String nombre, double nota) {
		super();
		this.numExpediente = numExpediente;
		this.nombre = nombre;
		this.nota = nota;
	}


	public int getNumExpediente() {
		return numExpediente;
	}


	public void setNumExpediente(int numExpediente) {
		this.numExpediente = numExpediente;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public double getNota() {
		return nota;
	}


	public void setNota(double nota) {
		this.nota = nota;
	}


	@Override
	public String toString() {
		return "Alumno [numExpediente=" + numExpediente + ", nombre=" + nombre + ", nota=" + nota + "]";
	}


	@Override
	public int compareTo(Alumno o) {
		
		return Integer.compare(numExpediente, o.numExpediente);
	}

	

}
