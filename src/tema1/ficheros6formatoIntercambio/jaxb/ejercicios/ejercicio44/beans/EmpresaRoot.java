package tema1.ficheros6formatoIntercambio.jaxb.ejercicios.ejercicio44.beans;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EmpresaRoot {

	private String nie;
	private String nom_empresa;
	private Direccion direccion;
	private HashMap<String, Trabajador> mapTrabajadores;

	public EmpresaRoot(String nie, String nom_empresa, Direccion direccion,
			HashMap<String, Trabajador> mapTrabajadores) {
		super();
		this.nie = nie;
		this.nom_empresa = nom_empresa;
		this.direccion = direccion;
		this.mapTrabajadores = mapTrabajadores;

	}

	public EmpresaRoot() {
		super();
	}

	@XmlElement
	public String getNie() {
		return nie;
	}

	public void setNie(String nie) {
		this.nie = nie;
	}

	@XmlElement
	public String getNom_empresa() {
		return nom_empresa;
	}

	public void setNom_empresa(String nom_empresa) {
		this.nom_empresa = nom_empresa;
	}

	@XmlElement
	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	@XmlElementWrapper(name = "mapTrabajadores")
	@XmlElement(name = "Libro")
	public HashMap<String, Trabajador> getMapTrabajadores() {
		return mapTrabajadores;
	}

	public void setMapTrabajadores(HashMap<String, Trabajador> mapTrabajadores) {
		this.mapTrabajadores = mapTrabajadores;

	}

	@Override
	public String toString() {
		return "EmpresaRoot [nie=" + nie + ", nom_empresa=" + nom_empresa + ", direccion=" + direccion
				+ ", mapTrabajadores=" + mapTrabajadores + "]";
	}

	public void aniadirTrabajadores(Trabajador t) {

		mapTrabajadores.put(t.getDni(), t);

	}

	public void mostrar_Datos_Empresa() {
		System.out.println("Nombre de la empresa:  " + nom_empresa);
		System.out.println("Nie:  " + nie);
		System.out.println("Dirección:  " + direccion.toString());

		if (mapTrabajadores.isEmpty()) {
			System.out.println("No hay Trabajadadores añadidos");
		} else {

			// Recorrer el mapa y printearlo
			for (Map.Entry<String, Trabajador> entrada : mapTrabajadores.entrySet()) {
				String clave = entrada.getKey();
				Trabajador trabajador = entrada.getValue();
				System.out.println("DNI Trabajador:  " + trabajador.getDni());
				System.out.println("Nombre Trabajador:  " + trabajador.getNombre());
				System.out.println("Cargo Trabajador:  " + trabajador.getCargo());

			}

		}
		System.out.println("=======****=****=======");
	}

}
