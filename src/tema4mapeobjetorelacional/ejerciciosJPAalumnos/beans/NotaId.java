package tema4mapeobjetorelacional.ejerciciosJPAalumnos.beans;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class NotaId implements Serializable {

	@ManyToOne
	@JoinColumn(name = "alumno", referencedColumnName = "dni")
	private AlumnoJPA alumno;

	@ManyToOne
	@JoinColumn(name = "modulo", referencedColumnName = "codigo")
	private ModuloJPA modulo;

	public NotaId() {
	}

	public NotaId(AlumnoJPA alumno, ModuloJPA modulo) {
		this.alumno = alumno;
		this.modulo = modulo;
	}

	// Getters y setters
	public AlumnoJPA getAlumno() {
		return alumno;
	}

	public void setAlumno(AlumnoJPA alumno) {
		this.alumno = alumno;
	}

	public ModuloJPA getModulo() {
		return modulo;
	}

	public void setModulo(ModuloJPA modulo) {
		this.modulo = modulo;
	}
}
