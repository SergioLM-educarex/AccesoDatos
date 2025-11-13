package tema1.ficheros6formatoIntercambio.json.ejercicios.ej46;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainRecetas {

	public static void main(String[] args) {

		ObjectMapper mapper = new ObjectMapper();
		List<Receta> lista = new ArrayList<Receta>();

		try {
			Recetas recetas = mapper.readValue(new File("ej46recetas.json"), Recetas.class);

			lista = recetas.getRecetas();

			System.out.println("Json leido");

			for (Receta receta : lista) {
				mapper.writeValue(System.out, receta);
			}

		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
