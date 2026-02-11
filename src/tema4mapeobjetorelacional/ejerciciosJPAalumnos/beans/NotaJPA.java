package tema4mapeobjetorelacional.ejerciciosJPAalumnos.beans;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "nota")
public class NotaJPA {

	@EmbeddedId
	private NotaId notaId;

	private double nota;

	public NotaJPA() {
		super();
	}

	public NotaJPA(NotaId notaId, double nota) {
		super();
		this.notaId = notaId;
		this.nota = nota;
	}

	public NotaId getNotaId() {
		return notaId;
	}

	public void setNotaId(NotaId notaId) {
		this.notaId = notaId;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

}
