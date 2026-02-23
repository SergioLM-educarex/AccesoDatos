package tema4mapeobjetorelacional.zejerciciofinal;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * ENTIDAD - Prestamo
 *
 * Representa la tabla "prestamo" en la BD.
 * Es la clase más importante del proyecto porque es la que
 * contiene las CLAVES FORÁNEAS y define las relaciones con
 * Socio y Libro.
 *
 * RELACIONES:
 *
 * 1. Con Socio → MUCHOS préstamos pertenecen a UN socio (ManyToOne)
 *    PRESTAMOS (N) ────────── (1) SOCIO
 *
 * 2. Con Libro → UN préstamo es de UN libro (OneToOne)
 *    PRESTAMOS (1) ────────── (1) LIBRO
 *
 * En la BD la tabla "prestamo" tiene dos columnas FK:
 *    - socioDni → apunta al dni de la tabla socio
 *    - libroIsbn → apunta al isbn de la tabla libro
 *
 * CONSULTA ESTÁTICA (@NamedQuery):
 * Se define aquí encima de la clase y se carga cuando arranca
 * el programa. 
 * El nombre debe ser único en toda la unidad de persistencia.
 */


@Entity(name = "prestamos")
@Table(name = "prestamos")
@NamedQuery(name = "Prestamos.countAll", // nombre único para llamarla desde el código
				query = "SELECT COUNT(p) FROM prestamos p" // JPQL, no SQL
				)
public class Prestamo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPrestamo")
	private int idPrestamo;

	@Column(name = "prestado")
	private boolean prestado;

	/**
     * RELACIÓN @ManyToOne con Socio
     *
     * @ManyToOne significa: desde el punto de vista de Prestamo,
     * MUCHOS préstamos pueden pertenecer a UN mismo socio.
     *
     * Este es el lado "dueño" de la relación porque aquí
     * es donde se crea la clave foránea en la tabla.
     *
     * @JoinColumn define cómo se crea esa clave foránea:
     * - name = "socioDni" → así se llama la columna FK en la tabla prestamos
     * - referencedColumnName = "dni" → apunta a la columna "dni" de la tabla socio
     *
     * En Socio tenemos @OneToMany(mappedBy = "socio") que es
     * el lado inverso de esta misma relación. mappedBy = "socio"
     * hace referencia al nombre de ESTE atributo (private Socio socio)
     */
	@ManyToOne
	@JoinColumn(name = "socioDni", referencedColumnName = "dni") //Nombre que tenga en el Column
	private Socio socio;

	/**
     * RELACIÓN @OneToOne con Libro
     *
     * Igual que con Socio, @JoinColumn define la FK:
     * - name = "libroIsbn" → nombre de la columna FK en tabla prestamos
     * - referencedColumnName = "isbn" → apunta al nombre de Column de la tabla Libro
     *
     */
	
	@OneToOne
	@JoinColumn(name = "libroIsbn", referencedColumnName = "isbn") //Nombre que tenga en el Column
	private Libro libro;

	public Prestamo() {

	}

	public Prestamo(boolean prestado, Socio socio, Libro libro) {
		super();
		this.prestado = prestado;
		this.socio = socio;
		this.libro = libro;
	}

	public int getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public boolean isPrestado() {
		return prestado;
	}

	public void setPrestado(boolean prestado) {
		this.prestado = prestado;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	@Override
	public String toString() {
		return "Prestamos [idPrestamo=" + idPrestamo + ", prestado=" + prestado + ", socioDni="
				+ (socio != null ? socio.getDni() : "null") + ", libroIsbn="
				+ (libro != null ? libro.getIsbn() : "null") + "]";
	}
}