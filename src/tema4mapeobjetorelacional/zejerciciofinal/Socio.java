package tema4mapeobjetorelacional.zejerciciofinal;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * RELACIÓN CON PRESTAMOS:
 * Un socio puede tener MUCHOS préstamos, pero cada préstamo
 * pertenece a UN solo socio.
 * Esto es una relación UNO A MUCHOS (1:N)
 *
 * SOCIO (1) ────────── (N) PRESTAMOS
 *
 * En la BD esto se traduce en que la tabla "prestamos" tiene
 * una columna "socioDni" que apunta a la columna "dni" 
 * de la tabla "socio", que es la clave primaria (@Id).
 * 
 * En Java apunta al atributo "dni" de la clase Socio,
 * que está mapeado con @ Column(name = "dni").
 * 
 * La clave foránea (FK) siempre está en el lado de MUCHOS.
 */

@Entity(name = "socio")
@Table(name = "socio")
public class Socio implements Serializable {
	
	

	@Id
	@Column(name = "dni")
	private String dni;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "numTelefono")
	private String numTelefono;

	/**
     * RELACIÓN @OneToMany — Un socio tiene muchos préstamos
     *
     * @OneToMany significa: desde el punto de vista de Socio,
     * UN socio puede tener MUCHOS préstamos.
     *
     * mappedBy = "socio" es MUY IMPORTANTE:
     * - Le dice a JPA que la relación ya está definida en la clase Prestamo
     * - Concretamente en el atributo que se llama "socio" dentro de Prestamo
     * - El "dueño" de la relación es Prestamo (porque tiene la FK en su tabla)
     * - Socio es el lado "inverso" de la relación
     *
     * Sin mappedBy, JPA crearía una tabla extra socio_prestamos
     * que no necesitamos porque la FK ya está en prestamos.
     */
	
	
	@OneToMany(mappedBy = "socio")
	private List<Prestamo> prestamos;

	public Socio() {
		super();
	}

	public Socio(String dni, String nombre, String numTelefono) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.numTelefono = numTelefono;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(String numTelefono) {
		this.numTelefono = numTelefono;
	}

	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	@Override
	public String toString() {
		return "Socio [dni=" + dni + ", nombre=" + nombre + ", numTelefono=" + numTelefono + "]";
	}
}