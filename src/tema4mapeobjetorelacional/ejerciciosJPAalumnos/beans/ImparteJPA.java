package tema4mapeobjetorelacional.ejerciciosJPAalumnos.beans;

import javax.persistence.*;

@Entity(name = "imparte")
public class ImparteJPA {

	@EmbeddedId
	private ImparteID imparteId;

	// Si necesitas más información sobre la relación, puedes agregar más atributos
	// aquí,
	// por ejemplo, si un profesor tiene más detalles sobre cómo imparte el módulo.

	public ImparteJPA() {
	}

	public ImparteJPA(ImparteID imparteId) {
		this.imparteId = imparteId;
	}

	public ImparteID getImparteId() {
		return imparteId;
	}

	public void setImparteId(ImparteID imparteId) {
		this.imparteId = imparteId;
	}

	// Otros métodos que quieras agregar

}
