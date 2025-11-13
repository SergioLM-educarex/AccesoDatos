package tema1.ficheros6formatoIntercambio.dom.ejercicios.ejercicio37;

import java.io.File;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Ejercicio37 {


	private static final int SI = 1;
	private static Scanner entrada = new Scanner(System.in);
	private static Set<Alumno> conjuntoAlumnos = new TreeSet<Alumno>();

	public static void main(String[] args) {

		// ------------------------------
		// 1. Bucle de entrada de datos
		// ------------------------------
		boolean continuar = true;
		int num = 1;

		while (num == SI) {
			System.out.println("----- PROGRAMA PARA A�ADIR NOTAS EXPEDIENTE ----");
			try {
				// Pedimos numero de expediente
				System.out.println("Inserte el numero de Expediente");
				int numExpe = Integer.parseInt(entrada.nextLine());

				// Pedimos nombre del alumno
				System.out.println("Inserte el nombre");
				String nombre = entrada.nextLine();

				// Pedimos la nota
				System.out.println("Inserte la nota");
				double nota = Double.parseDouble(entrada.nextLine());

				// Preguntamos si quiere continuar
				System.out.println("¿Quieres continuar? 1-SI / 2-NO");
				num = Integer.parseInt(entrada.nextLine());

				// Crear objeto Alumno y anadir al TreeSet
				Alumno alumno = new Alumno(numExpe, nombre, nota);
				conjuntoAlumnos.add(alumno);

			} catch (NumberFormatException e) {
				System.out.println("Error!! Introduce un numero válido");
			}
		} // fin del while

		
		// ------------------------------
		// 2. Crear estructura del DOM
		// ------------------------------
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = factoria.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		// Creamos un DOM vacio
		Document documento = db.newDocument();
		// Establecemos la version XML
		documento.setXmlVersion("1.0");

		// Creamos la raiz y aniadimos al DOM
		Element elementoRaiz = documento.createElement("Alumnos");

		// ------------------------------
		// 3. Recorrer TreeSet y crear elementos XML
		// ------------------------------
		for (Alumno alumno : conjuntoAlumnos) {

			// Creo el elemento Alumno para anadirle toda la informacion
			Element el_alumno = documento.createElement("Alumno");

			// Creo el Nodo num_expediente con el fin de anadirle un nodo de tipo Texto
			// Este nodo tipo texto se hace desde el Document, no se crea otro Element
			Element num_Expediente = documento.createElement("Expediente");
			Text t_numexp = documento.createTextNode(String.valueOf(alumno.getNumExpediente()));
			num_Expediente.appendChild(t_numexp);
			el_alumno.appendChild(num_Expediente);

			// ----------------
			// Nombre
			// Este paso es directo, aniade desde el append child,
			// sin necesidad de crear un objeto Text
			Element nombreAlumno = documento.createElement("Nombre");
			nombreAlumno.appendChild(documento.createTextNode(alumno.getNombre()));
			el_alumno.appendChild(nombreAlumno);

			// ------------------
			// Nota
			Element notaAlumno = documento.createElement("Nota");
			notaAlumno.appendChild(documento.createTextNode(String.valueOf(alumno.getNota())));
			el_alumno.appendChild(notaAlumno);

			// Aniadir <Alumno> a la raiz
			elementoRaiz.appendChild(el_alumno);
		}

		// ------------------------------
		// 4. Aniadir raiz al documento
		// ------------------------------
		documento.appendChild(elementoRaiz);

		// ------------------------------
		// 5. Transformar y guardar XML
		// ------------------------------
		try {
			// 1. Crear una fuente/origen con el arbol DOM
			DOMSource fuente = new DOMSource(documento);
			// 2. Crear el destino de la transformacion (archivo)
			StreamResult ficheroXML = new StreamResult(new File("notasALumnado.xml"));
			// 2.1. Si queremos mostrar por consola
			StreamResult consola = new StreamResult(System.out);

			// 3. Crear un transformador
			Transformer t = TransformerFactory.newInstance().newTransformer();
			// 4. Mejoramos el formato de salida
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			// 5. Transformar la fuente en el resultado
			t.transform(fuente, ficheroXML);
			// 5.1. Mostrar por consola
			t.transform(fuente, consola);

		} catch (TransformerException e) {
			e.printStackTrace();
		}

		// ------------------------------
		// 6. Cerrar Scanner
		// ------------------------------
		entrada.close();
	}
}
