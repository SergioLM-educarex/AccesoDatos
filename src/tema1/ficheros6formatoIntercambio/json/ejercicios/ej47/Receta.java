package tema1.ficheros6formatoIntercambio.json.ejercicios.ej47;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Receta {

	@JsonProperty("nombre")
	private String nombreRec;
	@JsonProperty("tipo")
	private String tipoReceta;
	@JsonProperty("origen")
	private Origen origen;
	@JsonProperty("ingredientes")
	private ArrayList <Ingrediente> ingredientes;
	
	
	public Receta(String nombreRec, String tipoReceta, Origen origen, ArrayList<Ingrediente> ingredientes) {
		super();
		this.nombreRec = nombreRec;
		this.tipoReceta = tipoReceta;
		this.origen = origen;
		this.ingredientes = ingredientes;
	}
	public Receta() {
		super();
	}
	public String getNombreRec() {
		return nombreRec;
	}
	public void setNombreRec(String nombreRec) {
		this.nombreRec = nombreRec;
	}
	public String getTipoReceta() {
		return tipoReceta;
	}
	public void setTipoReceta(String tipoReceta) {
		this.tipoReceta = tipoReceta;
	}
	public Origen getOrigen() {
		return origen;
	}
	public void setOrigen(Origen origen) {
		this.origen = origen;
	}
	public ArrayList<Ingrediente> getIngredientes() {
		return ingredientes;
	}
	public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}
	@Override
	public String toString() {
		return "Receta [nombreRec=" + nombreRec + ", tipoReceta=" + tipoReceta + ", origen=" + origen
				+ ", ingredientes=" + ingredientes + "]";
	}
	
	
	
}
