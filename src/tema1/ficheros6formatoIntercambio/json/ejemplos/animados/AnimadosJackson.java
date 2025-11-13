package tema1.ficheros6formatoIntercambio.json.ejemplos.animados;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class AnimadosJackson {
    public static void main(String[] args) {
        // Se crea un objeto ObjectMapper, el cual es el componente principal
        // de Jackson que permite convertir entre objetos Java y JSON.
        ObjectMapper mapper = new ObjectMapper();

        // Cadena JSON de ejemplo para leer y convertir a un objeto Java.
        String jsonInput =
        "{\"id\":1,\"nombre\":\"Robin\",\"apellido\":\"Wilson\"}";

        // Se declara un objeto Animados vacío (debe existir una clase Animados)
        Animado a = new Animado();

        try {
            // Convierte (parsea) la cadena JSON en un objeto Animados
            // readValue toma el texto JSON y la clase destino.
            a = mapper.readValue(jsonInput, Animado.class);
        } catch (JsonProcessingException e) {
            // Si ocurre un error al procesar el JSON, se imprime la traza del error
            e.printStackTrace();
        }

        // Imprime el objeto 'a' leído desde JSON (usa el método toString() de la clase Animados)
        System.out.println("Leer y parsed a animados desde JSON: " + a);

        // Se crea un nuevo objeto Animados con valores propios
        Animado b = new Animado(2, "Roger", "Rabbit");

        // Se imprime el objeto antes de convertirlo a JSON
        System.out.print("Animado object " + b + " as JSON = ");

        try {
            // Convierte el objeto 'b' a una cadena JSON y la escribe en la salida estándar (consola)
            mapper.writeValue(System.out, b);
        } catch (IOException e) {
            // Captura cualquier error de entrada/salida
            e.printStackTrace();
        }
    }
}
