package tema4mapeobjetorelacional.ejerciciosJPAalumnos.beans;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class NotaId implements Serializable {

	@ManyToOne
	@JoinColumn(name = "alumno_id", referencedColumnName = "id", nullable = false)
	private AlumnoJPA alumno; // Relación con el alumno

	@ManyToOne
	@JoinColumn(name = "modulo_codigo", referencedColumnName = "codigo", nullable = false)
	private ModuloJPA modulo; // Relación con el módulo

	public NotaId(AlumnoJPA alumno, ModuloJPA modulo) {
		super();
		this.alumno = alumno;
		this.modulo = modulo;
	}

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

	@Override
	public String toString() {
		return "NotaId [alumno=" + alumno + ", modulo=" + modulo + "]";
	}

}
