package tema1.ficheros4flujosostreams.ejercicios.stream3almacenaObjetosenBinario.ej26;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Ejercicio26v2 {

	public static void main(String[] args) throws IOException {
		
		/*
		 * Implementa la clase Empleado con los atributos dni, nombre y sueldo. Como m�todos
tendr� los constructores, getters y setters y el toString. La clase debe implementar la
interface Serializable. Ahora, crea un programa que cree un empleado pas�ndole
directamente los valores por par�metros, se guarde en el fichero empleado.bin Despu�s
crea otro programa que lea y muestre por pantalla el objeto del fichero empleado.bin
		 */
		
		
			Empleado empleado = null;
			ObjectInputStream ois = null;
			
			try {
				ois = new ObjectInputStream(new FileInputStream("empleado.bin"));
				empleado = (Empleado) ois.readObject();
				
				System.out.println(empleado.toString());
				
			} catch (IOException | ClassNotFoundException e) {
				System.out.println("Error");
				// TODO: handle exception
			}finally {
				if (ois!=null) {
					ois.close();
					
				}
				
			}
		
			
			
			
			
			
		
		
		
	}
}
