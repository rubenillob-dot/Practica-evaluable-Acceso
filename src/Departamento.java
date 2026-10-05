import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

// Clase que representa la etiqueta <DEPARTAMENTO>
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "loc", "dnombre", "empleados" })
public class Departamento {

    // El número de departamento va en el atributo 'id'
    @XmlAttribute(name = "id")
    private String id;

    @XmlElement(name = "LOC")
    private String loc;

    @XmlElement(name = "DNOMBRE")
    private String dnombre;

    // Etiqueta contenedora <EMPLEADOS> con la lista de <EMPLEADO>
    @XmlElementWrapper(name = "EMPLEADOS")
    @XmlElement(name = "EMPLEADO")
    private List<Empleado> empleados = new ArrayList<>();

    // Constructor vacío necesario para JAXB
    public Departamento() {
    }

    // Constructor con los datos básicos
    public Departamento(String id, String dnombre, String loc) {
        this.id = id;
        this.dnombre = dnombre;
        this.loc = loc;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getDnombre() {
        return dnombre;
    }

    public void setDnombre(String dnombre) {
        this.dnombre = dnombre;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
}
