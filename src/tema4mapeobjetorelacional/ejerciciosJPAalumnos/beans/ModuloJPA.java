package tema4mapeobjetorelacional.ejerciciosJPAalumnos.beans;

import javax.persistence.*;
import java.util.List;

@Entity(name = "modulo")
public class ModuloJPA {

    @Id
    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nombre_modulo")
    private String nombreModulo;

    @OneToMany(mappedBy = "imparteId.modulo", cascade = CascadeType.ALL)
    private List<ImparteJPA> impartidos;

    public ModuloJPA() {
    }

    public ModuloJPA(String codigo, String nombreModulo) {
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

    public List<ImparteJPA> getImpartidos() {
        return impartidos;
    }

    public void setImpartidos(List<ImparteJPA> impartidos) {
        this.impartidos = impartidos;
    }
}
