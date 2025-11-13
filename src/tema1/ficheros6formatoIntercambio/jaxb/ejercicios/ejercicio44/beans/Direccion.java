package tema1.ficheros6formatoIntercambio.jaxb.ejercicios.ejercicio44.beans;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "nombreVia", "numVia", "poblacion", "codPostal" })

public class Direccion {

	private String nombreVia;
	private int numVia;
	private String poblacion;
	private int codPostal;

	public Direccion(String nombreVia, int numVia, String poblacion, int codPostal) {
		super();
		this.nombreVia = nombreVia;
		this.numVia = numVia;
		this.poblacion = poblacion;
		this.codPostal = codPostal;
	}

	public Direccion() {
		super();
	}

	public String getNombreVia() {
		return nombreVia;
	}

	public void setNombreVia(String nombreVia) {
		this.nombreVia = nombreVia;
	}

	public int getNumVia() {
		return numVia;
	}

	public void setNumVia(int numVia) {
		this.numVia = numVia;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public int getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(int codPostal) {
		this.codPostal = codPostal;
	}

	@Override
	public String toString() {
		return "Direccion [nombreVia=" + nombreVia + ", numVia=" + numVia + ", poblacion=" + poblacion + ", codPostal="
				+ codPostal + "]";
	}

}
