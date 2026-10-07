# 📋 Guion Detallado de Explicación del Proyecto (DOM y JAXB en Java)

---

## 🧭 Visión Global del Proyecto

### ¿Cuál es el problema que resolvemos?
Partimos de un archivo inicial llamado `DepartamentosEmpleados.xml` que contiene datos relacionales o "planos" (etiquetas `<DEP_ROW>` para departamentos y `<EMP_ROW>` para empleados)[cite: 2, 3].
- **Ejercicio 1 (`Ejercicio1DOM.java`)**: Procesa el archivo con el parser **DOM** y muestra los departamentos y sus respectivos trabajadores por consola[cite: 2].
- **Ejercicio 2 (`Ejercicio2JAXB.java`)**: Lee esos mismos datos mediante **DOM**, los transforma a un modelo de objetos Java (`Empresa`, `Departamento`, `Empleado`) y utiliza **JAXB** para exportarlos a un nuevo archivo XML (`DatosEmpresa.xml`) con una estructura jerárquica limpia y formateada[cite: 3].

---

## 1. `Empleado.java` (Modelo de Datos)

### ¿Qué representa?
Representa la entidad individual de un trabajador y se corresponde con la etiqueta XML `<EMPLEADO>`[cite: 4].

### Desglose línea por línea / conceptos clave:
1. **Anotaciones a nivel de clase**:
   - `@XmlAccessorType(XmlAccessType.FIELD)`: Indica a JAXB que debe leer las anotaciones directamente sobre los atributos privados, sin necesidad de colocarlas en los métodos *getters/setters*[cite: 4].
   - `@XmlType(propOrder = { "apellido", "oficio", "dir", "fechaAlt", "salario", "comision" })`: Fija el orden estricto en el que se generarán las etiquetas hijas dentro del XML final[cite: 4].
2. **Atributo vs Elemento**:
   - `@XmlAttribute(name = "numero")`: El identificador del empleado (`numero`) no se genera como una etiqueta hija, sino como un atributo del nodo: `<EMPLEADO numero="...">`[cite: 4].
   - `@XmlElement(name = "...")`: Cada campo restante se mapea a un elemento hijo (`<APELLIDO>`, `<OFICIO>`, `<DIR>`, `<FECHA_ALT>`, `<SALARIO>`, `<COMISION>`)[cite: 4].
3. **Constructores**:
   - `public Empleado() {}`: Constructor sin argumentos, **imprescindible** para que JAXB pueda instanciar la clase por introspección[cite: 4].
   - `public Empleado(...)`: Constructor sobrecargado con todos los parámetros para facilitar la asignación directa de valores desde el código[cite: 4].
4. **Métodos accesores**:
   - Incluye los *getters* y *setters* estándar para mantener el encapsulamiento de datos[cite: 4].

---

## 2. `Departamento.java` (Modelo de Datos)

### ¿Qué representa?
Representa un departamento de la empresa y se corresponde con la etiqueta `<DEPARTAMENTO>`[cite: 1]. Maneja una relación **1 a N** con los empleados[cite: 1].

### Desglose línea por línea / conceptos clave:
1. **Anotaciones a nivel de clase**:
   - `@XmlAccessorType(XmlAccessType.FIELD)`: Al igual que en `Empleado`, inspecciona los campos privados[cite: 1].
   - `@XmlType(propOrder = { "loc", "dnombre", "empleados" })`: Establece que primero aparecerá la localidad, luego el nombre del departamento y al final el bloque de empleados[cite: 1].
2. **Atributo del departamento**:
   - `@XmlAttribute(name = "id")`: El código del departamento (`id`) se serializa como un atributo: `<DEPARTAMENTO id="...">`[cite: 1].
3. **Elementos simples**:
   - `@XmlElement(name = "LOC")` y `@XmlElement(name = "DNOMBRE")`: Elementos hijos directos con la ubicación y el nombre[cite: 1].
4. **Relación 1 a N (`@XmlElementWrapper`)**:
   - `@XmlElementWrapper(name = "EMPLEADOS")`: Genera una etiqueta contenedora envoltorio `<EMPLEADOS>`[cite: 1].
   - `@XmlElement(name = "EMPLEADO")`: Define que cada elemento individual de la lista `List<Empleado>` será una etiqueta `<EMPLEADO>` dentro de dicho contenedor[cite: 1].
   - **Estructura resultante**:
     ```xml
     <DEPARTAMENTO id="10">
         <LOC>SEVILLA</LOC>
         <DNOMBRE>CONTABILIDAD</DNOMBRE>
         <EMPLEADOS>
             <EMPLEADO numero="7369">...</EMPLEADO>
         </EMPLEADOS>
     </DEPARTAMENTO>
     ```
5. **Constructores y colecciones**:
   - La lista `empleados` se inicializa directamente con `new ArrayList<>()` para prevenir excepciones de tipo `NullPointerException` al agregar trabajadores[cite: 1].
   - Cuenta con constructor vacío para JAXB y constructor parametrizado para facilitar su creación[cite: 1].

---

## 3. `Empresa.java` (Elemento Raíz de JAXB)

### ¿Qué representa?
Es la clase raíz del documento de salida; agrupa el título general y todos los departamentos que componen la organización[cite: 5].

### Desglose línea por línea / conceptos clave:
1. **Elemento Raíz**:
   - `@XmlRootElement(name = "EMPRESA")`: Es la anotación fundamental que define a esta clase como el nodo raíz del XML generado (`<EMPRESA>...</EMPRESA>`)[cite: 5].
2. **Estructura y orden**:
   - `@XmlType(propOrder = { "titulo", "departamentos" })`: Garantiza que la cabecera/título aparezca antes del listado de departamentos[cite: 5].
3. **Campos**:
   - `titulo`: Inicializado con `"DATOS DE LAS TABLA DEPART y EMPLE "` y anotado con `@XmlElement(name = "TITULO")`[cite: 5].
   - `departamentos`: Colección `List<Departamento>` anotada con `@XmlElement(name = "DEPARTAMENTO")`, permitiendo repetir la etiqueta `<DEPARTAMENTO>` por cada elemento de la lista sin un contenedor intermedio[cite: 5].
4. **Constructores**:
   - Constructor por defecto requerido por JAXB y un constructor alternativo para cambiar el título si fuera necesario[cite: 5].

---

## 4. `Ejercicio1DOM.java` (Lectura y Consulta con DOM)

### ¿Qué hace este programa?
Lee el fichero XML original utilizando el analizador en memoria **DOM (Document Object Model)**, cruza en memoria los departamentos con sus empleados y muestra los resultados con formato en la consola[cite: 2].

### Pasos explicados en detalle:
1. **Localización del fichero (`obtenerFicheroXml`)**:
   - Comprueba si el fichero `DepartamentosEmpleados.xml` se encuentra en el directorio actual o en el directorio superior (`../`), facilitando la ejecución tanto desde la raíz del proyecto como desde subcarpetas de un IDE[cite: 2].
2. **Inicialización del parser DOM**:
   - Se obtiene una factoría con `DocumentBuilderFactory.newInstance()`[cite: 2].
   - Se crea el analizador con `factory.newDocumentBuilder()`[cite: 2].
   - `builder.parse(fichero)` lee el archivo y genera el árbol DOM en memoria[cite: 2].
   - `doc.getDocumentElement().normalize()`: Elimina nodos de texto redundantes y normaliza saltos de línea y espacios en blanco[cite: 2].
3. **Recuperación de nodos**:
   - `doc.getElementsByTagName("DEP_ROW")`: Extrae la lista de todos los nodos de departamento[cite: 2].
   - `doc.getElementsByTagName("EMP_ROW")`: Extrae la lista de todos los nodos de empleados[cite: 2].
4. **Cruce de datos (Bucle anidado)**:
   - Itera sobre cada departamento, extrayendo `DEPT_NO`, `DNOMBRE` y `LOC`[cite: 2].
   - Para cada departamento, recorre la lista completa de empleados comparando si el `DEPT_NO` del empleado coincide con el del departamento[cite: 2].
   - Si coincide, imprime en consola los datos del trabajador (apellido, oficio, salario y comisión)[cite: 2].
   - Si un departamento no tiene ningún empleado asociado (usando la bandera booleana `tieneEmpleados`), muestra explícitamente el aviso `(Este departamento no tiene empleados)`[cite: 2].
5. **Método auxiliar `obtenerTexto(Element elemento, String etiqueta)`**:
   - Busca la etiqueta solicitada dentro de un elemento, verifica que exista y tenga contenido, y devuelve su valor con `.getTextContent().trim()`. Si no existe, devuelve una cadena vacía para evitar fallos[cite: 2].

---

## 5. `Ejercicio2JAXB.java` (Lectura DOM + Generación con JAXB)

### ¿Qué hace este programa?
Combina la lectura de datos mediante **DOM** con la generación y serialización de un nuevo XML mediante **JAXB (Marshalling)**[cite: 3].

### Pasos explicados en detalle:
1. **Fase 1: Lectura con DOM**:
   - Localiza y parsea `DepartamentosEmpleados.xml` exactamente igual que en el Ejercicio 1[cite: 3].
2. **Fase 2: Construcción del árbol de objetos Java**:
   - Se instancia el objeto principal: `Empresa empresa = new Empresa()`[cite: 3].
   - Al recorrer cada `<DEP_ROW>`, se crea un objeto `Departamento` con su identificador, nombre y localidad[cite: 3].
   - Mediante el bucle interior, para cada empleado que pertenece al departamento se crea una instancia de `Empleado` con todos sus datos (`EMP_NO`, `APELLIDO`, `OFICIO`, `DIR`, `FECHA_ALT`, `SALARIO`, `COMISION`) y se añade a la lista interna del departamento: `depto.getEmpleados().add(emp)`[cite: 3].
   - Se añade el departamento completo a la empresa: `empresa.getDepartamentos().add(depto)`[cite: 3].
3. **Fase 3: Proceso de Marshalling con JAXB**:
   - **Ruta de salida**: Se define `DatosEmpresa.xml` en la misma ubicación del archivo de entrada[cite: 3].
   - **Contexto**: Se crea el contexto con `JAXBContext.newInstance(Empresa.class)`, indicándole a JAXB cuál es la clase raíz[cite: 3].
   - **Configuración del Marshaller**:
     - `marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE)`: Aplica indentación y saltos de línea para que el XML resultante sea legible[cite: 3].
     - `marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8")`: Asegura la correcta codificación de caracteres especiales y tildes[cite: 3].
   - **Escritura**: `marshaller.marshal(empresa, ficheroSalida)` convierte todo el grafo de objetos Java a formato XML físico en el disco[cite: 3].

---

## 📊 Cuadro Comparativo: DOM vs JAXB en esta Práctica

| Aspecto | DOM (`Ejercicio1DOM`) | JAXB (`Ejercicio2JAXB`) |
| :--- | :--- | :--- |
| **Enfoque** | Manejo de nodos genéricos en memoria (`Element`, `NodeList`)[cite: 2]. | Mapeo objeto-relacional XML (Objetos POJO con anotaciones)[cite: 1, 3, 4, 5]. |
| **Uso en el proyecto** | Lectura y filtrado directo para visualización por consola[cite: 2]. | Serialización (*marshalling*) para crear un nuevo XML ordenado[cite: 3]. |
| **Control de estructura** | Manual (navegando por etiquetas y extrayendo texto)[cite: 2]. | Automático (definido mediante `@XmlElement`, `@XmlAttribute` y `@XmlElementWrapper`)[cite: 1, 4]. |