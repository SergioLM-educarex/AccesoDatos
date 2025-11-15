package tema1.prueba3;

import java.util.ArrayList;

public class ListaDispositivos {

	private ArrayList<Dispositivo> listaDispositivos = new ArrayList<Dispositivo>();

	public ListaDispositivos(ArrayList<Dispositivo> listaDispositivos) {
		super();
		this.listaDispositivos = listaDispositivos;
	}

	public ListaDispositivos() {
		super();
	}

	public ArrayList<Dispositivo> getListaDispositivos() {
		return listaDispositivos;
	}

	public void setListaDispositivos(ArrayList<Dispositivo> listaDispositivos) {
		this.listaDispositivos = listaDispositivos;
	}
	
	public void mostrarListaDispositivos () {
		for (Dispositivo dispositivo : listaDispositivos) {
			System.out.println(dispositivo);
		}
	}

	@Override
	public String toString() {
		return "ListaDispositivos [listaDispositivos=" + listaDispositivos + "]";
	}
	
	

}
