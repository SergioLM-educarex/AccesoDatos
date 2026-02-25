package tema5MongoDB.insertar.insertarMASregistros;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertManyResult;

import tema5MongoDB.MongoConnection;

public class InsertarMasRegistros {

	private static final String MI_BASE_DE_DATOS = "MiBaseDeDatos";

	public static void main(String[] args) {
		MongoConnection mg = new MongoConnection();

		MongoDatabase database = mg.getDatabase();

		MongoCollection<Document> collection = database.getCollection(MI_BASE_DE_DATOS);

		List<Document> documents = new ArrayList<Document>();

		documents.add(new Document("nombre", "Raul").append("correo", "raul@gmail.com")
				.append("telefono", new ArrayList<Long>() {
					{
						add(927536653L);
						add(666000555L);
					}
				}).append("direccion", new Document("calle", "la fuente").append("población", "Casatejada")
						.append("códigoPostal", 10520)));
		documents.add(new Document("nombre", "Ana").append("correo", "ana@gmail.com")
				.append("telefono", new ArrayList<Long>() {
					{
						add(123456789L);
						add(987654321L);
					}
				}).append("direccion",
						new Document("calle", "el río").append("población", "Trujillo").append("códigoPostal", 10200)));
		documents.add(new Document("nombre", "Carlos").append("correo", "carlos@gmail.com")
				.append("telefono", new ArrayList<Long>() {
					{
						add(555555555L);
						add(666666666L);
					}
				}).append("direccion", new Document("calle", "la montaña").append("población", "Plasencia")
						.append("códigoPostal", 10600)));
		// Insertar los documentos en la colección
		InsertManyResult resultado = collection.insertMany(documents);
		// Comprobar si la inserción fue exitosa
		System.out.println("Se insertaron " + resultado.getInsertedIds().size() + "documentos.");
		// Obtener el número de documentos en la colección
		long numDocumentos = collection.countDocuments();
		// Mostrar el número de documentos en la colección
		System.out.println("Documentos en la colección 'persona': " + numDocumentos);
		// Cerrar la conexión
		mg.close();
	}
}
