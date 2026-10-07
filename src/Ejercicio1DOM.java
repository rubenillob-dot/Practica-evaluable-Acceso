import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Ejercicio1DOM {

    public static void main(String[] args) {
        try {
            // Buscamos el fichero XML usando ruta relativa
            File fichero = obtenerFicheroXml("DepartamentosEmpleados.xml");
            if (!fichero.exists()) {
                System.out.println("No se ha encontrado el fichero: " + fichero.getPath());
                return;
            }

            // Creamos el analizador DOM y cargamos el documento
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fichero);
            doc.getDocumentElement().normalize();

            // Obtenemos todos los departamentos y empleados del fichero
            NodeList listaDepartamentos = doc.getElementsByTagName("DEP_ROW");
            NodeList listaEmpleados = doc.getElementsByTagName("EMP_ROW");

            
            System.out.println("LISTADO DE DEPARTAMENTOS Y EMPLEADOS CON DOM");
            

            // Recorremos cada departamento
            for (int i = 0; i < listaDepartamentos.getLength(); i++) {
                Element dep = (Element) listaDepartamentos.item(i);

                String numDept = obtenerTexto(dep, "DEPT_NO");
                String nombreDept = obtenerTexto(dep, "DNOMBRE");
                String localidad = obtenerTexto(dep, "LOC");

            
                System.out.println("DEPARTAMENTO " + numDept + ": " + nombreDept + " (" + localidad + ")");
                

                // Buscamos los empleados que pertenecen a este departamento
                boolean tieneEmpleados = false;
                for (int j = 0; j < listaEmpleados.getLength(); j++) {
                    Element emp = (Element) listaEmpleados.item(j);
                    String deptEmpleado = obtenerTexto(emp, "DEPT_NO");

                    if (numDept.equals(deptEmpleado)) {
                        tieneEmpleados = true;
                        String apellido = obtenerTexto(emp, "APELLIDO");
                        String oficio = obtenerTexto(emp, "OFICIO");
                        String salario = obtenerTexto(emp, "SALARIO");
                        String comision = obtenerTexto(emp, "COMISION");

                        System.out.println("  * " + apellido + " | " + oficio +
                                           " | Salario: " + salario + " €" +
                                           " | Comisión: " + comision + " €");
                    }
                }

                // Si un departamento no tiene empleados (como Producción)
                if (!tieneEmpleados) {
                    System.out.println("  (Este departamento no tiene empleados)");
                }
            }

           
            System.out.println("Proceso finalizado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al procesar el XML con DOM: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método auxiliar para leer el texto de una etiqueta dentro de un elemento
    private static String obtenerTexto(Element elemento, String etiqueta) {
        NodeList lista = elemento.getElementsByTagName(etiqueta);
        if (lista != null && lista.getLength() > 0) {
            return lista.item(0).getTextContent().trim();
        }
        return "";
    }

    // Localiza el fichero tanto si se ejecuta desde la raíz como desde otra carpeta
    private static File obtenerFicheroXml(String nombreFichero) {
        File f = new File(nombreFichero);
        if (!f.exists()) {
            f = new File("../" + nombreFichero);
        }
        return f;
    }
}
