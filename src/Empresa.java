import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

// Elemento raíz <EMPRESA>
@XmlRootElement(name = "EMPRESA")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "titulo", "departamentos" })
public class Empresa {

    // Título que encabeza el XML de salida
    @XmlElement(name = "TITULO")
    private String titulo = "DATOS DE LAS TABLA DEPART y EMPLE ";

    // Lista de etiquetas <DEPARTAMENTO>
    @XmlElement(name = "DEPARTAMENTO")
    private List<Departamento> departamentos = new ArrayList<>();

    // Constructor vacío necesario para JAXB
    public Empresa() {
    }

    // Constructor opcional por si queremos cambiar el título
    public Empresa(String titulo) {
        this.titulo = titulo;
    }

    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }
}
