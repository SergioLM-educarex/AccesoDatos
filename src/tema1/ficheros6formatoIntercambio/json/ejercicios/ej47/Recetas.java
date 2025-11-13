package tema1.ficheros6formatoIntercambio.json.ejercicios.ej47;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Recetas {

	private ArrayList <Receta> recetas;

	public Recetas(ArrayList<Receta> recetas) {
		super();
		this.recetas = recetas;
	}

	public Recetas() {
		super();
	}

	public ArrayList<Receta> getRecetas() {
		return recetas;
	}

	public void setRecetas(ArrayList<Receta> recetas) {
		this.recetas = recetas;
	}

	@Override
	public String toString() {
		return "Recetas [recetas=" + recetas + "]";
	}
	
	
}
