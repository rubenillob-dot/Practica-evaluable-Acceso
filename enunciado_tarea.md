# Leer un fichero XML con DOM y crear otro con JAXB

Crea un proyecto Java con dos clases, cada una de ellas con su propio método `main` que resuelva cada uno de los ejercicios siguientes.

**Requisitos del entorno:**
* Utiliza **JDK 25**.
* **No uses Maven ni ninguna otra herramienta de gestión de proyectos** (las librerías/JARs necesarias deben gestionarse manualmente).

---

## Ejercicios

### a) Lectura y listado con DOM
Se trata de listar los datos contenidos en un fichero XML con información de empleados y departamentos:
* Utiliza la API **DOM**.
* Lista los números, nombres y localidad de los departamentos y, a continuación, apellido, oficio, salario y comisión de los empleados pertenecientes a dicho departamento.

---

### b) Transformación y generación con JAXB
Se trata de crear un fichero XML con información de empleados y departamentos, partiendo de los datos que se encuentran en el fichero `DepartamentosEmpleados.xml`.

El proceso es el siguiente:
1. Acceder con **DOM** al fichero para leer los datos.
2. Escribir con **JAXB** un nuevo fichero llamado `DatosEmpresa.xml` con los mismos datos de empleados y departamentos.

Este fichero debe seguir el siguiente esquema/estructura:

```xml
<EMPRESA>
  <TITULO>DATOS DE LAS TABLA DEPART y EMPLE </TITULO>
  <DEPARTAMENTO id="10">
    <LOC>SEVILLA</LOC>
    <DNOMBRE>CONTABILIDAD</DNOMBRE>
    
    <EMPLEADOS>
      <EMPLEADO numero="XX">
        <APELLIDO>MUÑOZ</APELLIDO>
        <OFICIO>EMPLEADO</OFICIO>
        <DIR>7782</DIR>
        <FECHA_ALT>1992-01-23</FECHA_ALT>
        <SALARIO>1690</SALARIO>
        <COMISION>0</COMISION>
      </EMPLEADO>
      ...
    </EMPLEADOS>
  </DEPARTAMENTO>
  ...
</EMPRESA>
```

---

## Criterios de puntuación
* **Total:** 10 puntos.

---

## Indicaciones de entrega

1. **Estructura del archivo:**
   * Comprime el proyecto que resuelve la tarea, incluidas todas las clases que se piden.
   * Las librerías importadas deberán estar en una carpeta dentro del propio proyecto.
   * Todos los ficheros utilizados en el proyecto deben estar dentro del mismo, empleando **rutas relativas**.

2. **Formato y nomenclatura:**
   * El envío se realizará a través de la plataforma en la forma establecida.
   * El archivo `.zip` se nombrará siguiendo la siguiente pauta:
     ```text
     apellido1_apellido2_nombre_2I_Tarea.zip
     ```