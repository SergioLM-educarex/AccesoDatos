package tema1.prueba3;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

//raiz y permitir cambiar el tipo de dato Elemento
@XmlRootElement(name="dispositivos")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaDispositivos implements Serializable {

	@JsonProperty("dispositivos")
	
	//Nombre del elemento
	@XmlElement(name = "dispositivo")
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
