import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

// Clase que representa la etiqueta <EMPLEADO>
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "apellido", "oficio", "dir", "fechaAlt", "salario", "comision" })
public class Empleado {

    // El número de empleado va como atributo 'numero'
    @XmlAttribute(name = "numero")
    private String numero;

    @XmlElement(name = "APELLIDO")
    private String apellido;

    @XmlElement(name = "OFICIO")
    private String oficio;

    @XmlElement(name = "DIR")
    private String dir;

    @XmlElement(name = "FECHA_ALT")
    private String fechaAlt;

    @XmlElement(name = "SALARIO")
    private String salario;

    @XmlElement(name = "COMISION")
    private String comision;

    // Constructor vacío que necesita JAXB
    public Empleado() {
    }

    // Constructor con datos para rellenarlo fácil
    public Empleado(String numero, String apellido, String oficio, String dir, String fechaAlt, String salario, String comision) {
        this.numero = numero;
        this.apellido = apellido;
        this.oficio = oficio;
        this.dir = dir;
        this.fechaAlt = fechaAlt;
        this.salario = salario;
        this.comision = comision;
    }

    // Getters y Setters
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getFechaAlt() {
        return fechaAlt;
    }

    public void setFechaAlt(String fechaAlt) {
        this.fechaAlt = fechaAlt;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getComision() {
        return comision;
    }

    public void setComision(String comision) {
        this.comision = comision;
    }
}
