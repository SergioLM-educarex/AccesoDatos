package tema1.prueba2;

import java.io.Serializable;

public class Producto implements Serializable {

	private int id;
	private String nombre;
	private String talla;
	private String color;
	private int stock;
	private int precio;
	private int coste;
	private String estado;
	private int descuento;
	
	
	public Producto(int id, String nombre, String talla, String color, int stock,
			int precio, int coste, String estado, int descuento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.talla = talla;
		this.color = color;
		this.stock = stock;
		this.precio = precio;
		this.coste = coste;
		this.estado = estado;
		this.descuento = descuento;
	}
	
	
	public Producto() {
		super();
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public String getTalla() {
		return talla;
	}
	
	public void setTalla(String talla) {
		this.talla = talla;
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
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public int getCoste() {
		return coste;
	}
	public void setCoste(int coste) {
		this.coste = coste;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getDescuento() {
		return descuento;
	}
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", talla=" + talla
				+ ", color=" + color + ", stock=" + stock + ", precio=" + precio + ", coste="
				+ coste + ", estado=" + estado + ", descuento=" + descuento + "]";
	}
	
	
	
}
