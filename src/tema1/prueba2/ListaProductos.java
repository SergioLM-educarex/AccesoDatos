package tema1.prueba2;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
public class ListaProductos implements Serializable {

	@JsonProperty("personas")
	ArrayList<Producto> productos = new ArrayList<Producto>();

	public ListaProductos(ArrayList<Producto> productos) {
		super();
		this.productos = productos;
	}

	public ListaProductos() {
		super();
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}

	@Override
	public String toString() {
		return "ListaProductos [productos=" + productos + "]";
	}

	

}
