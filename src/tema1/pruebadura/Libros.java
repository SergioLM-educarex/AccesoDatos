package tema1.pruebadura;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Libros implements Serializable {

	private List<Libro> listaLibros = new ArrayList<Libro>();

	public Libros(List<Libro> piezas) {
		super();
		this.listaLibros = piezas;
	}

	public Libros() {
		super();
	}

	public List<Libro> getLibros() {
		return listaLibros;
	}

	public void setLibros(List<Libro> piezas) {
		this.listaLibros = piezas;
	}

	@Override
	public String toString() {
		return "Libros [libros=" + listaLibros + "]";
	}
	
	
	public void aniadirLibro (Libro libro) {
		listaLibros.add(libro);
	}
	
	public void mostrarLibros() {
		for (Libro libro : listaLibros) {
			System.out.println(libro);
		}
	}
	
	public int obtenerSiguienteId() {
	    return listaLibros.size() + 1;
	}
	
}
