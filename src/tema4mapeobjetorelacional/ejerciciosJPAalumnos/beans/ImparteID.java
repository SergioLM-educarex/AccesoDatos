package tema4mapeobjetorelacional.ejerciciosJPAalumnos.beans;

import javax.persistence.Embeddable;

@Embeddable
public class ImparteID {
    private ProfesorJPA profesor;
    private ModuloJPA modulo;

    public ImparteID() {
        super();
    }

    public ImparteID(ProfesorJPA profesor, ModuloJPA modulo) {
        super();
        this.profesor = profesor;
        this.modulo = modulo;
    }

    public ProfesorJPA getProfesor() {
        return profesor;
    }

    public void setProfesor(ProfesorJPA profesor) {
        this.profesor = profesor;
    }

    public ModuloJPA getModulo() {
        return modulo;
    }

    public void setModulo(ModuloJPA modulo) {
        this.modulo = modulo;
    }

    @Override
    public String toString() {
        return "ImparteId [profesor=" + profesor + ", modulo=" + modulo + "]";
    }
}
