package tema1.ficheros6formatoIntercambio.json.ejercicios.ej47;

public class Origen {

	private String pais;
	private String region;
	public Origen(String pais, String region) {
		super();
		this.pais = pais;
		this.region = region;
	}
	public Origen() {
		super();
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	@Override
	public String toString() {
		return "Origen [pais=" + pais + ", region=" + region + "]";
	}
	
	
}
