package tema1.prueba2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) 
@XmlType(propOrder = { "nombre", "talla", "color", "precio", "estado" })
public class Producto implements Serializable {

	private int id;
	@JsonIgnore 
	private String nombre;
	@JsonIgnore 
	private String talla;
	@JsonIgnore 
	private String color;

	@JsonIgnore 
	private int stock;
	private double precio;
	@JsonIgnore 
	private int coste;
	@JsonIgnore 
	private String estado;
	@JsonIgnore 
	private int descuento;

	public Producto(int id, String nombre, String talla, String color, int stock, double precio, int coste,
			String estado, int descuento) {
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

	@XmlTransient
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

	@XmlTransient
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

	@XmlTransient
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

	@XmlTransient
	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", talla=" + talla + ", color=" + color + ", stock="
				+ stock + ", precio=" + precio + ", coste=" + coste + ", estado=" + estado + ", descuento=" + descuento
				+ "]";
	}
}
