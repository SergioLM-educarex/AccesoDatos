package tema1.ficheros4flujosostreams.ejercicios.stream3almacenaObjetosenBinario.ej26;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import tema1.ficheros4flujosostreams.ejercicios.stream3almacenaObjetosenBinario.ej27.Empleado;

public class Ejercicio26 {

	public static void main(String[] args) {
		
		/*
		 * Implementa la clase Empleado con los atributos dni, nombre y sueldo. Como m�todos
tendra los constructores, getters y setters y el toString. La clase debe implementar la
interface Serializable. Ahora, crea un programa que cree un empleado pas�ndole
directamente los valores por par�metros, se guarde en el fichero empleado.bin Despu�s
crea otro programa que lea y muestre por pantalla el objeto del fichero empleado.bin
		 */
		
		
		Empleado empleado = new Empleado("12345678A", "Paco", 3000);
		ObjectOutputStream oss=null;
		
		
		
		try {
			oss = new ObjectOutputStream(new FileOutputStream("empleado.bin"));
			oss.writeObject(empleado);
			
			
			System.out.println("Empleado guardado correctamente");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al guardar el empleado: "+e.getMessage());
			
			
		} catch (IOException e) {
			
			
			e.printStackTrace();
		}finally {
			if (oss!=null) {
				
				try {
					oss.close();
				} catch (Exception e2) {
					
					System.out.println("Error al cerrar el ObjectOutputStream "+e2.getMessage());
				}
				
			}
		}
		
		
		
		
	}
}
