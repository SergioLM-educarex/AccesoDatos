package tema1.pruebadura;

import java.io.Serializable;

public class Libro implements Serializable {

	private int idLibro;
	private String titulo;
	private String autor;
	private int anio;
	private double precioLibro;

	public Libro(int idLibro, String titulo, String autor, int anio, double precioLibro) {
		super();
		this.idLibro = idLibro;
		this.titulo = titulo;
		this.autor = autor;
		this.anio = anio;
		this.precioLibro = precioLibro;
	}

	public Libro() {
		super();
	}

	public int getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public double getPrecioLibro() {
		return precioLibro;
	}

	public void setPrecioLibro(double precioLibro) {
		this.precioLibro = precioLibro;
	}

	@Override
	public String toString() {
		return "Pieza [idLibro=" + idLibro + ", titulo=" + titulo + ", autor=" + autor + ", anio=" + anio
				+ ", precioLibro=" + precioLibro + "]";
	}

}
