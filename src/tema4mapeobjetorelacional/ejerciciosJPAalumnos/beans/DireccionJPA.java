package tema4mapeobjetorelacional.ejerciciosJPAalumnos.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DireccionJPA {

	@Id
	@Column(name= "alumno")
	private int alumno;
	
	@Column(name="calle")
	private String calle;
	
	@Column(name="poblacion")
	private String poblacion;
	@Column(name="cp")
	private int cp;

	public DireccionJPA(int alumno, String calle, String poblacion, int cp) {
		super();
		this.alumno = alumno;
		this.calle = calle;
		this.poblacion = poblacion;
		this.cp = cp;
	}

	public int getAlumno() {
		return alumno;
	}

	public void setAlumno(int alumno) {
		this.alumno = alumno;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	@Override
	public String toString() {
		return "DireccionJPA [alumno=" + alumno + ", calle=" + calle + ", poblacion=" + poblacion + ", cp=" + cp + "]";
	}

}
