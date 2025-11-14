package tema1.prueba2;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;



public class MapaProductos implements Serializable {

	HashMap<Integer, Producto> mapaProductos = new HashMap<Integer,Producto>();

	public MapaProductos(HashMap<Integer, Producto> mapaProductos) {
		super();
		this.mapaProductos = mapaProductos;
	}
	
	

	public MapaProductos() {
		
		
	}



	public HashMap<Integer, Producto> getMapaProductos() {
		return mapaProductos;
	}

	public void setMapaProductos(HashMap<Integer, Producto> mapaProductos) {
		this.mapaProductos = mapaProductos;
	}
	
	//Recorrer
	public void mostrarMapa(HashMap<Integer, Producto> mapaProductos) {
		for (Map.Entry<Integer, Producto> entry : mapaProductos.entrySet()) {
		    System.out.println("ID: " + entry.getKey());
		    
		    System.out.println("Producto: " + entry.getValue());
		}
	}

	@Override
	public String toString() {
		return "ListaProductos [mapaProductos=" + mapaProductos + "]";
	}
	
	
	
}
