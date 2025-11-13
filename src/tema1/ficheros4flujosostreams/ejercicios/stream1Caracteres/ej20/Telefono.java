package tema1.ficheros4flujosostreams.ejercicios.stream1Caracteres.ej20;

public class Telefono {

	private String nombrepersona;
	private String numTelefono;
	
	
	
	public Telefono(String nombrepersona, String numTelefono) {
		super();
		this.nombrepersona = nombrepersona;
		this.numTelefono = numTelefono;
	}
	public String getNombrepersona() {
		return nombrepersona;
	}
	public void setNombrepersona(String nombrepersona) {
		this.nombrepersona = nombrepersona;
	}
	public String getNumTelefono() {
		return numTelefono;
	}
	public void setNumTelefono(String numTelefono) {
		this.numTelefono = numTelefono;
	}
	@Override
	public String toString() {
		return "Telefono [nombrepersona=" + nombrepersona + ", numTelefono=" + numTelefono + "]";
	}
	
	
}
