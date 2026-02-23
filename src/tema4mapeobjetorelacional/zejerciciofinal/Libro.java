package tema4mapeobjetorelacional.zejerciciofinal;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



//@Entity le dice a JPA que esta clase es una tabla de la BD
//name = "libro" es el nombre que usaremos en las consultas JPQL

@Entity(name = "libro")

//@Table define el nombre real de la tabla en la BD MySQL
@Table(name = "libro")
public class Libro implements Serializable {

    @Id
    @Column(name = "isbn")
    private String isbn;

    @Column(name = "numEjemplar")
    private int numEjemplar;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "autor")
    private String autor;

    public Libro() {
        super();
    }

    public Libro(String isbn, int numEjemplar, String titulo, String autor) {
        super();
        this.isbn = isbn;
        this.numEjemplar = numEjemplar;
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getNumEjemplar() { return numEjemplar; }
    public void setNumEjemplar(int numEjemplar) { this.numEjemplar = numEjemplar; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    @Override
    public String toString() {
        return "Libro [isbn=" + isbn + ", numEjemplar=" + numEjemplar 
                + ", titulo=" + titulo + ", autor=" + autor + "]";
    }
}