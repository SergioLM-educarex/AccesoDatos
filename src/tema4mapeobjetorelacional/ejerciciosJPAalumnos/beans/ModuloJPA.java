package tema4mapeobjetorelacional.ejerciciosJPAalumnos.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "modulo")
public class ModuloJPA {

	@Id()
	@Column(name = "codigo")
	private String codigo;
	
	@Column(name = "nombre_modulo")
	private String nombreModulo;

	public ModuloJPA(String codigo, String nombreModulo) {
		super();
		this.codigo = codigo;
		this.nombreModulo = nombreModulo;
	}
	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombreModulo() {
		return nombreModulo;
	}

	public void setNombreModulo(String nombreModulo) {
		this.nombreModulo = nombreModulo;
	}

	@Override
	public String toString() {
		return "ModuloJPA [codigo=" + codigo + ", nombreModulo=" + nombreModulo + "]";
	}
	
	
	
	
}
