package tema1.ficheros4flujosostreams.ejemplos.leerBinario;


import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class EscribirBinario {

	public static void main(String[] args) throws IOException {
		
		String nombreString = "datos.bin";
		
		FileInputStream fis = new FileInputStream(nombreString);
		DataInputStream dis = new DataInputStream(fis);
		
		//Leer los datos en el mismo orden que los escribimos
		int numero = dis.readInt();
		String Utf = dis.readUTF();
		double decimal = dis.readDouble();
		boolean bool = dis.readBoolean();
		String texto = dis.readUTF();
		
		
		/*
		 * das.writeInt(123);
		das.writeUTF("---------");
		das.writeDouble(45.26);
		das.writeBoolean(true);
		das.writeUTF("--- HOLA MUNDO --");
		 */
		
		System.out.println("Numero : "+ numero );
		System.out.println("Decimal : "+ decimal);
		System.out.println("Booleano: "+ bool);
		System.out.println("Texto:"+texto);
		
		
		
		

	}

}
