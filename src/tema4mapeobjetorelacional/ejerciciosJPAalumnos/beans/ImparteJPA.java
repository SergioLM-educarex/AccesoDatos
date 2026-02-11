package tema4mapeobjetorelacional.ejerciciosJPAalumnos.beans;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "modulo_profesor")
public class ImparteJPA {

	@EmbeddedId
	private ImparteJPA imparte;

	public ImparteJPA() {
		super();
	}

	public ImparteJPA getImparte() {
		return imparte;
	}

	public void setImparte(ImparteJPA imparte) {
		this.imparte = imparte;
	}

	@Override
	public String toString() {
		return "ImparteJPA [imparte=" + imparte + "]";
	}
	
	
}
