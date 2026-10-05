import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Ejercicio2JAXB {

    public static void main(String[] args) {
        try {
            // 1. Buscamos el fichero origen con ruta relativa
            File ficheroEntrada = obtenerFicheroXml("DepartamentosEmpleados.xml");
            if (!ficheroEntrada.exists()) {
                System.out.println("No se ha encontrado el fichero origen: " + ficheroEntrada.getPath());
                return;
            }

            System.out.println("1. Leyendo datos con DOM desde: " + ficheroEntrada.getName());

            // Leemos el fichero XML mediante DOM
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(ficheroEntrada);
            doc.getDocumentElement().normalize();

            // Creamos el objeto raíz para JAXB
            Empresa empresa = new Empresa();

            // Obtenemos las listas de departamentos y empleados del DOM
            NodeList listaDepartamentos = doc.getElementsByTagName("DEP_ROW");
            NodeList listaEmpleados = doc.getElementsByTagName("EMP_ROW");

            // Recorremos los departamentos
            for (int i = 0; i < listaDepartamentos.getLength(); i++) {
                Element depElem = (Element) listaDepartamentos.item(i);

                String idDept = obtenerTexto(depElem, "DEPT_NO");
                String nombreDept = obtenerTexto(depElem, "DNOMBRE");
                String localidad = obtenerTexto(depElem, "LOC");

                // Creamos el objeto Departamento
                Departamento depto = new Departamento(idDept, nombreDept, localidad);

                // Buscamos los empleados que pertenecen a este departamento
                for (int j = 0; j < listaEmpleados.getLength(); j++) {
                    Element empElem = (Element) listaEmpleados.item(j);
                    String deptEmpleado = obtenerTexto(empElem, "DEPT_NO");

                    if (idDept.equals(deptEmpleado)) {
                        String numEmp = obtenerTexto(empElem, "EMP_NO");
                        String apellido = obtenerTexto(empElem, "APELLIDO");
                        String oficio = obtenerTexto(empElem, "OFICIO");
                        String dir = obtenerTexto(empElem, "DIR");
                        String fechaAlt = obtenerTexto(empElem, "FECHA_ALT");
                        String salario = obtenerTexto(empElem, "SALARIO");
                        String comision = obtenerTexto(empElem, "COMISION");

                        Empleado emp = new Empleado(numEmp, apellido, oficio, dir, fechaAlt, salario, comision);
                        depto.getEmpleados().add(emp);
                    }
                }

                // Añadimos el departamento a la empresa
                empresa.getDepartamentos().add(depto);
            }

            // 2. Definimos la ruta del fichero de salida DatosEmpresa.xml
            File ficheroSalida = new File(ficheroEntrada.getParent() != null ? ficheroEntrada.getParent() : ".", "DatosEmpresa.xml");
            System.out.println("2. Generando fichero XML con JAXB en: " + ficheroSalida.getPath());

            // Configuramos el contexto de JAXB con la clase Empresa
            JAXBContext context = JAXBContext.newInstance(Empresa.class);
            Marshaller marshaller = context.createMarshaller();

            // Opciones de formato para que el XML quede ordenado y legible
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            // Escribimos el XML en el fichero de salida
            marshaller.marshal(empresa, ficheroSalida);

            System.out.println("¡Listo! Se ha creado correctamente el fichero DatosEmpresa.xml.");

        } catch (Exception e) {
            System.out.println("Error durante la generación con JAXB: " + e.getMessage());
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
