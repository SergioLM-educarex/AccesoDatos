package tema1.ficheros6formatoIntercambio.json.ejemplos.animados;

public class Animado {

	private int id;
	private String nombre;
	private String apellido;

	public Animado(int id, String nombre, String apellido) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public Animado() {
		super();
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public String toString() {
		return "Animado [nombre=" + nombre + ", apellido=" + apellido + "]";
	}

}
