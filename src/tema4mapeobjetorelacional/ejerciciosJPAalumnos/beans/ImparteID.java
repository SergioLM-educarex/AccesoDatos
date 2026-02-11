package tema4mapeobjetorelacional.ejerciciosJPAalumnos.beans;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ImparteID implements Serializable {

	private ProfesorJPA profesor;
	
	private ModuloJPA modulo;

	public ImparteID(ProfesorJPA profesor, ModuloJPA modulo) {
		super();
		this.profesor = profesor;
		this.modulo = modulo;
	}

	public ImparteID() {
		super();
	}

	public ProfesorJPA getProfesor() {
		return profesor;
	}

	public void setProfesor(ProfesorJPA profesor) {
		this.profesor = profesor;
	}

	public ModuloJPA getModulo() {
		return modulo;
	}

	public void setModulo(ModuloJPA modulo) {
		this.modulo = modulo;
	}

	@Override
	public String toString() {
		return "ImparteID [profesor=" + profesor + ", modulo=" + modulo + "]";
	}
	
	
	
}
