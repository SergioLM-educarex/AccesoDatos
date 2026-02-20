package tema5MongoDB;

// Importamos la clase Document para crear documentos BSON
import org.bson.Document;

// Importamos clases necesarias para la conexión con MongoDB
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class QuickStart {
    public static void main(String[] args) {
        // Cadena de conexión a MongoDB Atlas con usuario y contraseña
        String connectionString = "mongodb+srv://sleonm03_db_user:K6j7xGLafKlDVzNl@cluster0.o5vdrtv.mongodb.net/?appName=Cluster0";

        // Configuramos la versión de la API del servidor
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1) // Usamos la versión V1 de la API
                .build();

        // Configuración del cliente MongoDB
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString)) // Aplicamos la cadena de conexión
                .serverApi(serverApi) // Aplicamos la versión de la API
                .build();

        // Creamos un cliente MongoDB y nos conectamos al servidor usando try-with-resources
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Obtenemos la base de datos "admin"
                MongoDatabase database = mongoClient.getDatabase("admin");
                
                // Enviamos un ping para confirmar que la conexión fue exitosa
                database.runCommand(new Document("ping", 1));
                
                System.out.println("¡Ping a tu despliegue exitoso! Conectado correctamente a MongoDB.");
            } catch (MongoException e) {
                // Si ocurre un error de MongoDB, lo mostramos
                e.printStackTrace();
            }
        }
    }
}