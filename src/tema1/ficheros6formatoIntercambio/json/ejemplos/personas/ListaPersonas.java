package tema1.ficheros6formatoIntercambio.json.ejemplos.personas;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ListaPersonas {

	@JsonProperty("personas")
	ArrayList <Persona> listaPersonas;

	public ListaPersonas(ArrayList<Persona> listaPersonas) {
		super();
		this.listaPersonas = listaPersonas;
	}

	public ListaPersonas() {
		super();
	}

	public ArrayList<Persona> getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(ArrayList<Persona> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}

	@Override
	public String toString() {
		return "ListaPersonas [listaPersonas=" + listaPersonas + "]";
	}
	
	public void mostrarPersonas() {
		for (Persona persona : listaPersonas) {
			System.out.println(persona.toString()+"\n");
		}
	}
	
	public void aniadirPersonas(Persona p) {
		this.listaPersonas.add(p);
	}
}
