package tema4mapeobjetorelacional.ejerciciosJPAalumnos.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "alumno")
public class AlumnoJPA implements Serializable {

<<<<<<< HEAD
	@Id()
	@Column(name = "dni")
	private int id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "curso")
	private String curso;
	@Column(name = "telefono")
	private int telefono;
	
	
	@OneToMany(mappedBy = "")
	private List <NotaJPA> notas;

	public AlumnoJPA(int id, String nombre, String curso, int telefono) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.curso = curso;
		this.telefono = telefono;
	}
=======
    @Id
    @Column(name = "dni")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "curso")
    private String curso;

    @Column(name = "telefono")
    private int telefono;

    public AlumnoJPA() {
    }

    public AlumnoJPA(int id, String nombre, String curso, int telefono) {
        this.id = id;
        this.nombre = nombre;
        this.curso = curso;
        this.telefono = telefono;
    }
>>>>>>> branch 'master' of https://github.com/SergioLM-educarex/AccesoDatos.git

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

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "AlumnoJPA [id=" + id + ", nombre=" + nombre + ", curso=" + curso + ", telefono=" + telefono + "]";
	}

   
}
