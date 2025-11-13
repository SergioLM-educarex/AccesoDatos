package tema1.ficheros6formatoIntercambio.jaxb.ejemplos;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Personas {
	ArrayList<Persona> listaPersonas = new ArrayList<Persona>();

	public Personas() {
	}

	@XmlElementWrapper(name = "listaPersonas")
	@XmlElement(name = "Persona")
	public ArrayList<Persona> getPersonas() {
		return listaPersonas;
	}

	public void setPersonas(ArrayList<Persona> personas) {
		listaPersonas = personas;
	}

	public void mostrarPersonas() {
		for (Persona p : listaPersonas)
			System.out.println(p.toString());
	}

	public void aniadirPersona(Persona p) {
		this.listaPersonas.add(p);
	}
}