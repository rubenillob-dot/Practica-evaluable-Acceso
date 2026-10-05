# Práctica Evaluable: DOM y JAXB en Java

Este proyecto resuelve los dos ejercicios de manejo de ficheros XML en Java utilizando JDK 25 y librerías gestionadas manualmente (sin Maven).

## Estructura del proyecto

```text
PracticaEvaluableAD/
├── DepartamentosEmpleados.xml   # Fichero XML original con departamentos y empleados
├── DatosEmpresa.xml             # Fichero XML generado con JAXB
├── lib/                         # Librerías JAR necesarias para JAXB
│   ├── jaxb-api.jar
│   ├── jaxb-runtime.jar
│   └── javax.activation.jar
├── src/                         # Código fuente Java
│   ├── Ejercicio1DOM.java       # Ejercicio a: Lectura y listado de departamentos y empleados con DOM
│   ├── Ejercicio2JAXB.java      # Ejercicio b: Lectura con DOM y generación de XML con JAXB
│   ├── Empresa.java             # Clase modelo para la etiqueta <EMPRESA>
│   ├── Departamento.java        # Clase modelo para la etiqueta <DEPARTAMENTO>
│   └── Empleado.java            # Clase modelo para la etiqueta <EMPLEADO>
├── bin/                         # Clases compiladas (.class)
└── .vscode/                     # Configuración de classpath y librerías para VS Code
```

---

## Cómo compilar y ejecutar

### Opción 1: Desde la terminal

1. **Compilar todas las clases:**
   ```bash
   javac -cp "lib/*" -d bin src/*.java
   ```

2. **Ejecutar el Ejercicio 1 (Lectura con DOM):**
   ```bash
   java -cp "bin:lib/*" Ejercicio1DOM
   ```

3. **Ejecutar el Ejercicio 2 (Generación con JAXB):**
   ```bash
   java -cp "bin:lib/*" Ejercicio2JAXB
   ```

*(Nota: En Java moderno también puedes ejecutar directamente con `java -cp "src:lib/*" src/Ejercicio1DOM.java` y `java -cp "src:lib/*" src/Ejercicio2JAXB.java`)*

### Opción 2: Desde VS Code

Al abrir la carpeta en VS Code, la configuración de `.vscode/settings.json` ya incluye la carpeta `lib/`. Basta con abrir `Ejercicio1DOM.java` o `Ejercicio2JAXB.java` y pulsar en **Run** (o `F5`).
