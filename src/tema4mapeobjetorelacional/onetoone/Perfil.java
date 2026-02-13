package tema4mapeobjetorelacional.onetoone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "perfiles")
public class Perfil {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column()
    private String biografia;
    
    @Column()
    private String telefono;
    @Column()
    private String direccion;
    
    // Relación bidireccional (opcional)
    @OneToOne(mappedBy = "perfil")
    private Usuario usuario;
    
    // Constructores
    public Perfil() {}
    
    public Perfil(String biografia, String telefono, String direccion) {
        this.biografia = biografia;
        this.telefono = telefono;
        this.direccion = direccion;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Perfil [id=" + id + ", biografia=" + biografia + ", telefono=" + telefono + ", direccion=" + direccion
				+ ", usuario=" + usuario + "]";
	}
    
    
}
