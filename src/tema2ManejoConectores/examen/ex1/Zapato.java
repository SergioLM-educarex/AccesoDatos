package tema2ManejoConectores.examen.ex1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Zapato {

	private String marca;
	private String modelo;
	
	private String tamano;    // LOS CAMPOS debe llamarse igual
	private String color;
	private int stock;
	private double precio;

	public Zapato() {
	}

	public Zapato(String marca, String modelo, String tamano, String color, int stock, double precio) {
		this.marca = marca;
		this.modelo = modelo;
		this.tamano = tamano;
		this.color = color;
		this.stock = stock;
		this.precio = precio;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	// IMPORTANTE: Debe ser getTamano() para que coincida con el campo
	public String getTamano() {
		return tamano;
	}

	public void setTamano(String tamano) {
		this.tamano = tamano;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Zapato [marca=" + marca + ", modelo=" + modelo + ", tamano=" + tamano + 
		       ", color=" + color + ", stock=" + stock + ", precio=" + precio + "€]";
	}
}