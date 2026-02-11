package tema4mapeobjetorelacional.ejerciciosJPAalumnos.beans;

import javax.persistence.*;

@Entity(name = "nota")
public class NotaJPA {

	@EmbeddedId
	private NotaId notaId;

	@Column(name = "nota")
	private double nota;

	public NotaJPA() {
	}

	public NotaJPA(NotaId notaId, double nota) {
		this.notaId = notaId;
		this.nota = nota;
	}

	// Getters y setters
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
