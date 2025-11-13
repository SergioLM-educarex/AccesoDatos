package tema1.ficheros6formatoIntercambio.json.ejemplos.personas;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class PersonaMain {

	public static void main(String[] args) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		
		
		try {
			ListaPersonas lista = mapper.readValue(new File("personas.json"), ListaPersonas.class);
			
			lista.mostrarPersonas();
			
		
			
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
