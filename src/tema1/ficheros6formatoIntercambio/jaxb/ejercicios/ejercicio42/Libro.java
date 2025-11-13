package tema1.ficheros6formatoIntercambio.jaxb.ejercicios.ejercicio42;

import javax.xml.bind.annotation.XmlType;


//De estos te generará un titulo, un autor una editoria y un isbn de tipo Elemento

@XmlType(propOrder={"titulo", "autor","editorial", "isbn"})
public class Libro {

	private String titulo;
	private String autor;
	private String editorial;
	private String isbn;
	
	public Libro(String titulo, String autor, String editorial, String isbn) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.isbn = isbn;
	}

	public Libro() {
		super();
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

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	
	

	@Override
	public String toString() {
		return "Libro [titulo=" + titulo + ", autor=" + autor + ", editorial=" + editorial + ", isbn=" + isbn + "]";
	}
	
	
	
}
