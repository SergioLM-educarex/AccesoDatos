package tema5MongoDB;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {

    private static final String URI = "mongodb+srv://sleonm03_db_user:K6j7xGLafKlDVzNl@cluster0.o5vdrtv.mongodb.net/?appName=Cluster0";
    private static final String DATABASE_NAME = "miBaseDeDatos";

    private MongoClient mongoClient;
    private MongoDatabase database;

    public MongoConnection() {
        try {
            mongoClient = MongoClients.create(URI);
            database = mongoClient.getDatabase(DATABASE_NAME);
            System.out.println("Conexión exitosa a la base de datos: " + database.getName());
        } catch (Exception e) {
            System.out.println("Error al conectarse a MongoDB: " + e.getMessage());
        }
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}