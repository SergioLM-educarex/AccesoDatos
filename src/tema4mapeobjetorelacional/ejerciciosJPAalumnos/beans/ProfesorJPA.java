package tema4mapeobjetorelacional.ejerciciosJPAalumnos.beans;

import javax.persistence.*;
import java.util.List;

@Entity(name = "profesor")
public class ProfesorJPA {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nombre")
	private String nombre;

	@OneToMany(mappedBy = "imparteId.profesor", cascade = CascadeType.ALL)
	private List<ImparteJPA> impartidos;

	public ProfesorJPA() {
	}

	public ProfesorJPA(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<ImparteJPA> getImpartidos() {
		return impartidos;
	}

	public void setImpartidos(List<ImparteJPA> impartidos) {
		this.impartidos = impartidos;
	}
}
