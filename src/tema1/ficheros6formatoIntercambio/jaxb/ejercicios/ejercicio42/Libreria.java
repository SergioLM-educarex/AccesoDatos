package tema1.ficheros6formatoIntercambio.jaxb.ejercicios.ejercicio42;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

//El XmlRootElement lo que hace es decir que es el Elemento principal

@XmlRootElement
public class Libreria {

	private String nombre;
	private String lugar;
	private int cod_Postal;
	private ArrayList <Libro> listaLibros;
	
	
	public Libreria(String nombre, String lugar, int cod_Postal, ArrayList<Libro> listaLibros) {
		super();
		this.nombre = nombre;
		this.lugar = lugar;
		this.cod_Postal = cod_Postal;
		this.listaLibros = listaLibros;
	}


	public Libreria() {
		super();
	}

	//El XmlAtrribute es un atributo del RootElemento
	
	@XmlAttribute
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@XmlAttribute
	public String getLugar() {
		return lugar;
	}


	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	@XmlAttribute
	public int getCod_Postal() {
		return cod_Postal;
	}


	public void setCod_Postal(int cod_Postal) {
		this.cod_Postal = cod_Postal;
	}


	@XmlElementWrapper(name="listaLibros")
	@XmlElement(name="Libro")
	public ArrayList<Libro> getListaLibros() {
		return listaLibros;
	}


	public void setListaLibros(ArrayList<Libro> listaLibros) {
		this.listaLibros = listaLibros;
	}


	
	public void aniadirLibro(Libro libro) {
		this.listaLibros.add(libro);
	}
	
	
	@Override
	public String toString() {
		return "Libreria [nombre=" + nombre + ", lugar=" + lugar + ", cod_Postal=" + cod_Postal + ", listaLibros="
				+ listaLibros + "]";
	}
	
	public void mostrarDatosLibreria() {
		
		System.out.println("Libreria: "+nombre);
		System.out.println("Poblacion: "+lugar);
		System.out.println("Codigo postal: "+cod_Postal);
		
		//Printear la lista de libros
		for (Libro libro : listaLibros) {
			System.out.println(libro);
		}
		
	}
	
}
