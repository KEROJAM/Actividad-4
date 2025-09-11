# Sistema de Gestión de Empleados con Árbol Binario de Búsqueda

## Descripción

Este proyecto implementa un sistema completo de gestión de empleados utilizando una estructura de datos de **Árbol Binario de Búsqueda (BST)** para optimizar las operaciones de búsqueda, inserción y eliminación. El sistema demuestra las ventajas de eficiencia de los árboles binarios comparado con búsquedas secuenciales tradicionales.

## Características Principales

### Funcionalidades del Sistema
- **Carga automática de empleados** desde archivo CSV
- **Gestión completa CRUD** (Crear, Leer, Actualizar, Eliminar)
- **Búsqueda eficiente por ID** con complejidad O(log n)
- **Búsqueda por nombre** de empleado
- **Generación automática de IDs** para nuevos empleados
- **Visualización de la estructura del árbol** en formato jerárquico
- **Comparación de rendimiento** entre árbol binario y búsqueda secuencial
- **Sistema de logging** completo con timestamps
- **Interfaz de usuario interactiva** por consola

### Ventajas del Árbol Binario de Búsqueda
- **Eficiencia de búsqueda**: O(log n) vs O(n) de búsqueda lineal
- **Inserción y eliminación eficientes**: Mantiene el orden automáticamente
- **Datos ordenados**: Recorrido inorder proporciona datos ordenados por ID
- **Escalabilidad**: La diferencia de rendimiento aumenta significativamente con más datos

## Estructura del Proyecto

```
Actividad-4/
├── src/
│   ├── Main.java          # Clase principal con menú interactivo
│   ├── Tree.java          # Implementación del Árbol Binario de Búsqueda
│   ├── Node.java          # Nodo del árbol
│   └── Empleado.java      # Clase modelo para empleados
├── list.csv               # Archivo de datos de empleados
├── logs.log              # Archivo de registro de operaciones
└── README.md             # Este archivo
```

## Requisitos del Sistema

- **Java Development Kit (JDK)** 8 o superior
- **Sistema operativo**: Windows, macOS, o Linux
- **Terminal/Línea de comandos** para compilación y ejecución

## Instalación y Compilación

### 1. Clonar o descargar el proyecto
```bash
git clone https://github.com/KEROJAM/Actividad-4.git
cd Actividad-4
```

### 2. Compilar el proyecto
```bash
# Compilar todos los archivos Java
javac *.java

# O compilar individualmente si es necesario
javac Empleado.java
javac Node.java
javac Tree.java
javac Main.java
```

### 3. Verificar la compilación
Después de la compilación exitosa, deberías ver archivos `.class` generados:
```
Empleado.class
Main.class
Node.class
Tree.class
```

## Ejecución del Programa

### Ejecutar el sistema
```bash
java Main
```

### Salida esperada al iniciar
```
=== SISTEMA DE GESTIÓN DE EMPLEADOS ===
Cargando empleados desde CSV...
✓ Sistema inicializado con X empleados
✓ Árbol binario de búsqueda creado
```

## Uso del Sistema

### Menú Principal
Al ejecutar el programa, verás el siguiente menú interactivo:

```
==================================================
         SISTEMA DE GESTIÓN DE EMPLEADOS
==================================================
1.  Mostrar todos los empleados
2.  Buscar empleado por ID
3.  Buscar empleado por nombre
4.  Agregar nuevo empleado
5.  Eliminar empleado
6.  Mostrar estadísticas del árbol
7.  Comparar eficiencia: Árbol vs Búsqueda Secuencial
8.  Mostrar empleados ordenados (Inorder)
9.  Visualizar estructura del árbol
10. Mostrar árbol por niveles
0.  Salir
==================================================
```

### Descripción de Opciones

1. **Mostrar todos los empleados**: Lista completa de empleados con numeración
2. **Buscar empleado por ID**: Búsqueda rápida O(log n) con medición de tiempo
3. **Buscar empleado por nombre**: Búsqueda por nombre completo
4. **Agregar nuevo empleado**: ID asignado automáticamente, solo requiere nombre
5. **Eliminar empleado**: Eliminación con confirmación de seguridad
6. **Mostrar estadísticas**: Información del árbol (total, min, max, altura)
7. **Comparar eficiencia**: Demostración práctica de ventajas del BST
8. **Empleados ordenados**: Recorrido inorder (automáticamente ordenado)
9. **Visualizar estructura**: Representación jerárquica del árbol
10. **Árbol por niveles**: Visualización por niveles de profundidad

## Formato del Archivo CSV

El archivo `list.csv` debe tener el siguiente formato:

```csv
id_empleado,nombre
1,María González López
2,Juan Rodríguez Martínez
3,Ana Fernández García
...
```

**Importante**: 
- La primera línea debe ser el encabezado
- Los IDs deben ser números enteros únicos
- Los nombres pueden contener espacios y caracteres especiales

## Archivos Generados

### logs.log
El sistema genera automáticamente un archivo de log que registra:
- Carga inicial de empleados desde CSV
- Todas las operaciones de búsqueda con resultados
- Inserciones de nuevos empleados
- Eliminaciones de empleados
- Timestamps de todas las operaciones

Ejemplo de entrada en el log:
```
Iniciando carga de empleados desde list.csv 2024-01-15 10:30:45
Empleado cargado: María González López (ID: 1) 2024-01-15 10:30:45
Búsqueda por ID 5: ENCONTRADO - ID: 5, Nombre: Isabel Martín Ruiz 2024-01-15 10:31:20
```

## Análisis de Complejidad

### Operaciones del Árbol Binario de Búsqueda
- **Búsqueda**: O(log n) en promedio, O(n) en el peor caso
- **Inserción**: O(log n) en promedio, O(n) en el peor caso
- **Eliminación**: O(log n) en promedio, O(n) en el peor caso
- **Recorrido inorder**: O(n)

### Comparación con Búsqueda Secuencial
- **Búsqueda secuencial**: Siempre O(n)
- **Ventaja del BST**: Especialmente notable con grandes volúmenes de datos

## Ejemplos de Uso

### Ejemplo 1: Agregar un empleado
```
Seleccione una opción: 4

AGREGAR NUEVO EMPLEADO
Ingrese el nombre del empleado: Carlos Nuevo Empleado

Empleado agregado exitosamente:
ID asignado automáticamente: 26
Empleado: ID: 26, Nombre: Carlos Nuevo Empleado
```

### Ejemplo 2: Visualizar estructura del árbol
```
Seleccione una opción: 9

ESTRUCTURA VISUAL DEL ÁRBOL BINARIO
==================================================
Estructura del Árbol Binario:
(Formato: ID - Nombre)

└── 15 - Juan Pérez
    ├── 25 - Ana García
    │   └── 30 - Carlos López
    └── 8 - María Rodríguez
        ├── 12 - Pedro Sánchez
        └── 3 - Laura Torres
```

### Ejemplo 3: Comparación de eficiencia
```
Seleccione una opción: 7

COMPARACIÓN DE EFICIENCIA
==================================================
Buscando empleado: ID: 15, Nombre: Juan Pérez
En una base de datos de 25 empleados

BÚSQUEDA CON ÁRBOL BINARIO:
   Resultado: ENCONTRADO
   Tiempo: 12500 nanosegundos
   Complejidad: O(log n) - 5 comparaciones máximas

BÚSQUEDA SECUENCIAL:
   Resultado: ENCONTRADO
   Tiempo: 45000 nanosegundos
   Complejidad: O(n) - hasta 25 comparaciones

ANÁLISIS DE EFICIENCIA:
--------------------------------------------------
   El árbol binario es 3.60x más rápido
   Diferencia de tiempo: 32500 nanosegundos
```


