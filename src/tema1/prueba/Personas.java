package tema1.prueba;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"personas"})

//anotaciones xml
@XmlRootElement(name="personas")
@XmlAccessorType(XmlAccessType.FIELD) // Usa FIELD para JAXB
public class Personas implements Serializable {

	
	@XmlElement(name="persona")
    private ArrayList<Persona> listaPersonas;

    public Personas(ArrayList<Persona> listaPersonas) {
        super();
        this.listaPersonas = listaPersonas;
    }

    public Personas() {
        super();
        this.listaPersonas = new ArrayList<>();
    }

    
    @JsonProperty("personas")
    public ArrayList<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(ArrayList<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }
    
    public void aniadirPersona(Persona persona) {
    	listaPersonas.add(persona);
    }

    @Override
    public String toString() {
        return "Personas [listaPersonas=" + listaPersonas + "]";
    }
}
