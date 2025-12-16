package tema2ManejoConectores.examen.ex2;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name="aves")
public class Lista_Aves implements Serializable {
	 @JsonProperty("aves")     // Jackson usará esto para mapear "aves"
    private ArrayList<Ave> aves = new ArrayList<>();

    public Lista_Aves() {}

    public Lista_Aves(ArrayList<Ave> aves) {
        this.aves = aves;
    }

    @XmlElement(name="ave")   // JAXB usará esto para mapear cada <ave>
   
    public ArrayList<Ave> getAves() {
        return aves;
    }

    public void setAves(ArrayList<Ave> aves) {
        this.aves = aves;
    }

    public void mostrar_Aves() {
        for (Ave ave : aves) {
            System.out.println(ave);
        }
    }

    public void aniadir_Ave(Ave ave) {
        aves.add(ave);
    }

    @Override
    public String toString() {
        return "Lista_Aves [aves=" + aves + "]";
    }
}
