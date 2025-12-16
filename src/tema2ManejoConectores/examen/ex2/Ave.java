package tema2ManejoConectores.examen.ex2;


import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



@XmlType(propOrder = {"nombre_comun", "nombre_cientifico", "habitat", "envergadura_cm", "estado_conservacion"})

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "nombre_comun", "nombre_cientifico", "habitat", "envergadura_cm", "estado_conservacion" })
public class Ave {

	private String nombre_comun;
	private String nombre_cientifico;
	private String habitat;
	private int envergadura_cm;
	private String estado_conservacion;

	public Ave(String nombre_comun, String nombre_cientifico, String habitat, int envergadura_cm,
			String estado_conservacion) {
		super();
		this.nombre_comun = nombre_comun;
		this.nombre_cientifico = nombre_cientifico;
		this.habitat = habitat;
		this.envergadura_cm = envergadura_cm;
		this.estado_conservacion = estado_conservacion;
	}

	public Ave() {
		super();
	}

	public String getNombre_comun() {
		return nombre_comun;
	}

	public void setNombre_comun(String nombre_comun) {
		this.nombre_comun = nombre_comun;
	}

	public String getNombre_cientifico() {
		return nombre_cientifico;
	}

	public void setNombre_cientifico(String nombre_cientifico) {
		this.nombre_cientifico = nombre_cientifico;
	}

	public String getHabitat() {
		return habitat;
	}

	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}

	public int getEnvergadura_cm() {
		return envergadura_cm;
	}

	public void setEnvergadura_cm(int envergadura_cm) {
		this.envergadura_cm = envergadura_cm;
	}

	public String getEstado_conservacion() {
		return estado_conservacion;
	}

	public void setEstado_conservacion(String estado_conservacion) {
		this.estado_conservacion = estado_conservacion;
	}

	@Override
	public String toString() {
		return "nombre_comun=" + nombre_comun + ", nombre_cientifico=" + nombre_cientifico
				+ ", habitat=" + habitat + ", envergadura_cm=" + envergadura_cm + ", estado_conservacion="
				+ estado_conservacion + "]";
	}

}
