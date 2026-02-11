package tema4mapeobjetorelacional.ejerciciosJPAalumnos.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import tema1.ficheros6formatoIntercambio.jaxb.ejercicios.ejercicio44.beans.Direccion;

@Entity(name = "alumno")
public class AlumnoJPA implements Serializable {

    @Id
    @Column(name = "dni")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "curso")
    private String curso;

    @Column(name = "telefono")
    private int telefono;
    
    // Relación uno a muchos con la entidad NotaJPA
    @OneToMany(mappedBy = "notaId.alumno", cascade = CascadeType.ALL)
    private List<NotaJPA> notas;

    @ManyToOne
    @JoinColumn(name = "direccion_id") // Relación con la tabla direccion
    private DireccionJPA direccion;

    public AlumnoJPA() {
    }

    public AlumnoJPA(int id, String nombre, String curso, int telefono) {
        this.id = id;
        this.nombre = nombre;
        this.curso = curso;
        this.telefono = telefono;
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
