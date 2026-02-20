package tema5MongoDB;

// Importamos la clase Document para manejar documentos BSON
import org.bson.Document;

// Importamos clases para trabajar con MongoDB
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

// Importamos filtros estáticos para consultas (como eq)
import static com.mongodb.client.model.Filters.eq;

public class QuickStartClase {
    public static void main(String[] args) {
        // Cadena de conexión a MongoDB Atlas con usuario y contraseña
        // Recuerda reemplazar <user> y <db_password> con tus credenciales
        String uri = "mongodb+srv://sleonm03_db_user:K6j7xGLafKlDVzNl@cluster0.o5vdrtv.mongodb.net/?appName=Cluster0";

        // Creamos un cliente MongoDB usando try-with-resources para cerrar la conexión automáticamente
        try (MongoClient mongoClient = MongoClients.create(uri)) {

            // Seleccionamos la base de datos "sample_mflix"
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");

            // Seleccionamos la colección "movies" dentro de la base de datos
            MongoCollection<Document> collection = database.getCollection("movies");

            // Buscamos el primer documento cuyo campo "title" sea igual a "Back to the Future"
            Document doc = collection.find(eq("title", "Back to the Future")).first();

            // Verificamos si se encontró un documento
            if (doc != null) {
                // Mostramos campos específicos del documento encontrado
                System.out.println("Título: " + doc.getString("title")); // Imprime el título
                System.out.println("Año: " + doc.getInteger("year"));    // Imprime el año
                System.out.println("Géneros: " + doc.get("genres"));     // Imprime los géneros
            } else {
                // Si no se encontró ningún documento coincidente
                System.out.println("No se encontraron documentos que coincidan.");
            }
        }
    }
}