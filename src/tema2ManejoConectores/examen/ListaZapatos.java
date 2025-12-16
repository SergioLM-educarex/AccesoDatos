// ListaZapatos.java
package tema2ManejoConectores.examen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListaZapatos implements Serializable {
	
	@JsonProperty("zapatos")
	private ArrayList<Zapato> listaZapatos = new ArrayList<>();

	// Constructor sin parámetros necesario para Jackson
	public ListaZapatos() {
		super();
	}

	public ListaZapatos(ArrayList<Zapato> listaZapatos) {
		super();
		this.listaZapatos = listaZapatos;
	}

	public List<Zapato> getListaZapatos() {
		return listaZapatos;
	}

	public void setListaZapatos(ArrayList<Zapato> listaZapatos) {
		this.listaZapatos = listaZapatos;
	}

	public void aniadirZapato(Zapato z) {
		listaZapatos.add(z);
	}
	
	public void obtener_Lista_Zapatos() {
		System.out.println("\n=== LISTA DE ZAPATOS ===");
		
		for (Zapato z: listaZapatos) {
			System.out.println(z);
		}
		System.out.println("Total zapatos: " + listaZapatos.size());
	}
	
	@Override
	public String toString() {
		return "ListaZapatos [listaZapatos=" + listaZapatos + "]";
	}
}