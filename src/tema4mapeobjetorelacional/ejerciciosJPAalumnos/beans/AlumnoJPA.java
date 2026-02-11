package tema4mapeobjetorelacional.ejerciciosJPAalumnos.beans;

import javax.persistence.*;
import java.util.List;

@Entity(name = "alumno")
public class AlumnoJPA {

    @Id
    @Column(name = "dni")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "curso")
    private String curso;

    @Column(name = "telefono")
    private int telefono;

    @OneToMany(mappedBy = "notaId.alumno", cascade = CascadeType.ALL)
    private List<NotaJPA> notas;

    @ManyToOne
    @JoinColumn(name = "direccion_id", referencedColumnName = "id")
    private DireccionJPA direccion;

    public AlumnoJPA() {
    }

    public AlumnoJPA(int id, String nombre, String curso, int telefono) {
        this.id = id;
        this.nombre = nombre;
        this.curso = curso;
        this.telefono = telefono;
    }

    // Getters y setters
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

    public List<NotaJPA> getNotas() {
        return notas;
    }

    public void setNotas(List<NotaJPA> notas) {
        this.notas = notas;
    }

    public DireccionJPA getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionJPA direccion) {
        this.direccion = direccion;
    }
}
