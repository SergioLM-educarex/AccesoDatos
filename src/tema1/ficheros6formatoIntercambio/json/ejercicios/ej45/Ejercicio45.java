package tema1.ficheros6formatoIntercambio.json.ejercicios.ej45;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Ejercicio45 {

    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        List<Persona> personas = new ArrayList<>();

        boolean continuar = true;

        do {
            System.out.println("--------********----------");

            System.out.print("Ingrese el nombre: ");
            String nombre = entrada.nextLine().trim();

            System.out.print("Ingrese el dni: ");
            String dni = entrada.nextLine().trim();

            int edad = 1;
            boolean edadValida = false;
            do {
                System.out.print("Ingrese la edad: ");
                String inputEdad = entrada.nextLine().trim();
                try {
                    edad = Integer.parseInt(inputEdad);
                    edadValida = true;
                } catch (NumberFormatException e) {
                    System.out.println("Error: introduzca un nÃºmero entero valido para la edad.");
                }
            } while (!edadValida);

            String jsonInput = "{\"dni\":\"" + dni + "\",\"nombre\":\"" + nombre + "\",\"edad\":" + edad + "}";

            Persona persona = null;
            try {
                persona = mapper.readValue(jsonInput, Persona.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            System.out.println("Persona generada desde JSON: " + persona);
            personas.add(persona);

            // AquÃ­ estÃ¡ la parte corregida
            String respuesta = "";
            do {
                System.out.print("¿Desea continuar? (S/N): ");
                respuesta = entrada.nextLine().trim();
            } while (respuesta.isEmpty());

            if (!respuesta.equalsIgnoreCase("S")) {
                continuar = false;
            }

        } while (continuar);

        try {
            System.out.println("\nListado de personas en formato JSON:");
            
            for (Persona persona : personas) {
            	 mapper.writeValue(System.out, personas);
            	    System.out.println(); // salto de lÃ­nea final
			}
            
            mapper.writeValue(new File("Json_Ejercicio45.txt"), personas);
            System.out.println("\nArchivo 'Json_Ejercicio45.txt' guardado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        entrada.close();
    }
}
