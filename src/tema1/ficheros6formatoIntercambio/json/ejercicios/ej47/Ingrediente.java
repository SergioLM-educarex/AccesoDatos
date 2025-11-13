package tema1.ficheros6formatoIntercambio.json.ejercicios.ej47;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ingrediente {
	@JsonProperty("nombre")
	private String nombreIngre;
	@JsonProperty("cantidad")
	private String cantidad;
	
	public Ingrediente(String nombreIngre, String cantidad) {
		super();
		this.nombreIngre = nombreIngre;
		this.cantidad = cantidad;
	}
	public Ingrediente() {
		super();
	}
	public String getNombreIngre() {
		return nombreIngre;
	}
	public void setNombreIngre(String nombreIngre) {
		this.nombreIngre = nombreIngre;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	@Override
	public String toString() {
		return "Ingrediente [nombreIngre=" + nombreIngre + ", cantidad=" + cantidad + "]";
	}
	
	
	
}
