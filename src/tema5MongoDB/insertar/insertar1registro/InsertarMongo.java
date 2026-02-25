package tema5MongoDB.insertar.insertar1registro;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;

import tema5MongoDB.MongoConnection;

public class InsertarMongo {

    public static void main(String[] args) {

        MongoConnection mg = new MongoConnection();

        try {
            // Obtener la base de datos
            MongoDatabase baseDeDatos = mg.getDatabase();

            // Obtener la colección 'persona'
            MongoCollection<Document> coleccion = baseDeDatos.getCollection("MiBaseDeDatos");

            // Crear el ArrayList de teléfonos
            ArrayList<Long> telefonos = new ArrayList<>();
            telefonos.add(927530053L);
            telefonos.add(666000333L);

            // Crear el documento
            Document documento = new Document("nombre", "Juan")
                    .append("correo", "juan@gmail.com")
                    .append("telefono", telefonos)
                    .append("direccion", new Document("calle", "la fuente")
                            .append("poblacion", "Casatejada")
                            .append("codigoPostal", 10520));

            // Insertar documento
            InsertOneResult resultado = coleccion.insertOne(documento);

            // Verificar inserción
            if (resultado.getInsertedId() != null) {
                System.out.println("Insertado con el ID: " + resultado.getInsertedId());
            } else {
                System.out.println("La inserción falló.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            mg.close();
        }
    }
}