package tema1.ficheros6formatoIntercambio.dom.ejemplos;

// Importaci�n de clases necesarias para manejo de archivos y XML
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LeerPersonasXML_Completo {

    public static void main(String[] args) {

        // 1. Crear una f�brica para construir parsers DOM
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            // 2. Crear el parser DOM a partir de la f�brica
            DocumentBuilder builder = factory.newDocumentBuilder();

            // 3. Crear un objeto File que representa el archivo XML a leer
            File archivoXML = new File("Personas.xml");

            // 4. Parsear el archivo XML y obtener el objeto Document (�rbol DOM)
            Document documento = builder.parse(archivoXML);

            // 5. Normalizar el documento para limpiar espacios y organizar nodos
            documento.getDocumentElement().normalize();

            // 6. Obtener el elemento ra�z del documento XML
            Element raiz = documento.getDocumentElement();

            // 7. Recorrer y mostrar todos los nodos del �rbol XML de forma recursiva
            mostrarNodos(raiz, 0);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            // 8. Capturar y mostrar cualquier error de configuraci�n, formato o lectura
            e.printStackTrace();
        }

    }

    /**
     * Funci�n recursiva para mostrar la estructura de los nodos XML.
     * @param elemento Nodo actual a mostrar
     * @param nivel Profundidad en el �rbol (para tabulaci�n visual)
     */
    public static void mostrarNodos(Node elemento, int nivel) {
        // Imprimir tabulaciones seg�n el nivel de profundidad
        for (int k = 0; k < nivel; k++) {
            System.out.print("\t");
        }
        // Imprimir el nombre del nodo actual
        System.out.print("Nodo: " + elemento.getNodeName());

        // Si el nodo tiene atributos, mostrarlos
        if (elemento.hasAttributes()) {
            NamedNodeMap atri = elemento.getAttributes();
            for (int k = 0; k < atri.getLength(); k++) {
                System.out.print(" Atributo:" + atri.item(k).getNodeName() + ":" + atri.item(k).getNodeValue());
            }
        }
        // Salto de l�nea tras mostrar el nodo y sus atributos
        System.out.println("");

        // Obtener la lista de nodos hijos del nodo actual
        NodeList hijos = elemento.getChildNodes();

        // Recorrer todos los hijos del nodo
        for (int i = 0; i < hijos.getLength(); i++) {
            Node nodo = hijos.item(i);

            // Si el hijo es un nodo de texto y no est� vac�o, mostrar su contenido
            if (nodo.getNodeType() == Node.TEXT_NODE && !nodo.getNodeValue().trim().isEmpty()) {
                // Tabulaci�n adicional para el contenido
                for (int k = 0; k < nivel + 1; k++) {
                    System.out.print("\t");
                }
                System.out.println("Contenido: " + nodo.getNodeValue().trim());
            }

            // Si el hijo es un nodo de tipo elemento, llamar recursivamente a la funci�n
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                mostrarNodos(nodo, nivel + 1);
            }
        }
    }
}
