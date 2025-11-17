package tema1.prueba.ej2;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"personas"})
@XmlRootElement(name="personas")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaPersonasG implements Serializable{

	@XmlElement(name = "persona")
	ArrayList<PersonaG> personas = new ArrayList<PersonaG>();

	public ListaPersonasG(ArrayList<PersonaG> personas) {
		super();
		this.personas = personas;
	}

	public ListaPersonasG() {
		super();
	}

	
	public ArrayList<PersonaG> getPersonas() {
		return personas;
	}

	public void setPersonas(ArrayList<PersonaG> personas) {
		this.personas = personas;
	}

	@Override
	public String toString() {
		return "ListaPersonasG [personas=" + personas + "]";
	}
	
	public void mostrarPersonas() {
		for (PersonaG personaG : personas) {
			System.out.println(personaG);
		}
	}
	
	public void aniadirPersona(PersonaG persona) {
		personas.add(persona);
	}
	
	
	
}
