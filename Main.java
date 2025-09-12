import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal que contiene el sistema de gestión de empleados.
 * Proporciona una interfaz de menú para interactuar con el árbol binario de búsqueda.
 */
public class Main {
    /**
     * Método estático para leer empleados desde un archivo CSV.
     * @param archivo ruta del archivo CSV a leer
     * @param logFile archivo donde registrar las operaciones de carga
     * @return lista de empleados leídos del archivo
     */
    private static List<Empleado> leerEmpleadosDesdeCSV(String archivo, String logFile) {
        List<Empleado> empleados = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo));
             PrintWriter logWriter = new PrintWriter(new FileWriter(logFile, true))) {
            
            // Saltar la línea de encabezado
            String line = br.readLine();
            
            // Log del inicio de carga de empleados
            logWriter.println("Iniciando carga de empleados desde " + archivo + " " + LocalDate.now() + " " + LocalTime.now());
            logWriter.flush();

            while ((line = br.readLine()) != null) {
                String[] datos = line.split(",");
                if (datos.length >= 2) {
                    int id = Integer.parseInt(datos[0].trim());
                    String nombre = datos[1].trim();
                    empleados.add(new Empleado(nombre, id));
                    
                    // Log de cada empleado cargado
                    logWriter.println("Empleado cargado: " + nombre + " (ID: " + id + ") " + LocalDate.now() + " " + LocalTime.now());
                    logWriter.flush();
                }
            }
            
            // Log del final de carga
            logWriter.println("Carga completada. Total empleados: " + empleados.size() + " " + LocalDate.now() + " " + LocalTime.now());
            logWriter.flush();
            
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return empleados;
    }

    private static Tree tree;
    private static List<Empleado> empleadosList;
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Método principal que inicializa el sistema y muestra el menú de opciones.
     * Carga los empleados desde CSV, crea el árbol binario y gestiona la interfaz de usuario.
     * @param args argumentos de línea de comandos (no utilizados)
     * @throws IOException si hay problemas al leer archivos o crear logs
     */
    public static void main(String[] args) throws IOException {
        // Inicializar el sistema
        System.out.println("=== SISTEMA DE GESTIÓN DE EMPLEADOS ===");
        System.out.println("Cargando empleados desde CSV...");
        
        empleadosList = leerEmpleadosDesdeCSV("list.csv", "logs.log");
        tree = new Tree("logs.log");
        
        // Cargar empleados en el árbol
        for (Empleado emp : empleadosList) {
            tree.insert(emp);
        }
        
        System.out.println("✓ Sistema inicializado con " + empleadosList.size() + " empleados");
        System.out.println("✓ Árbol binario de búsqueda creado");
        
        // Mostrar menú principal
        mostrarMenuPrincipal();
        
        // Cerrar recursos
        tree.closeLog();
        scanner.close();
    }
    
    /**
     * Muestra el menú principal del sistema y gestiona la navegación entre opciones.
     * Controla el flujo principal de la aplicación hasta que el usuario decide salir.
     */
    private static void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("         SISTEMA DE GESTIÓN DE EMPLEADOS");
            System.out.println("=".repeat(50));
            System.out.println("1.  Mostrar todos los empleados");
            System.out.println("2.  Buscar empleado por ID");
            System.out.println("3.  Buscar empleado por nombre");
            System.out.println("4.  Agregar nuevo empleado");
            System.out.println("5.  Eliminar empleado");
            System.out.println("6.  Mostrar estadísticas del árbol");
            System.out.println("7.  Comparar eficiencia: Árbol vs Búsqueda Secuencial");
            System.out.println("8.  Mostrar empleados ordenados (Inorder)");
            System.out.println("9.  Visualizar estructura del árbol");
            System.out.println("10. Mostrar árbol por niveles (BFS)");
            System.out.println("11. Mostrar árbol por niveles (Recursivo)");
            System.out.println("0.  Salir");
            System.out.println("=".repeat(50));
            System.out.print("Seleccione una opción: ");
            
            opcion = leerEntero();
            
            switch (opcion) {
                case 1:
                    mostrarTodosLosEmpleados();
                    break;
                case 2:
                    buscarPorID();
                    break;
                case 3:
                    buscarPorNombre();
                    break;
                case 4:
                    agregarEmpleado();
                    break;
                case 5:
                    eliminarEmpleado();
                    break;
                case 6:
                    mostrarEstadisticas();
                    break;
                case 7:
                    compararEficiencia();
                    break;
                case 8:
                    mostrarEmpleadosOrdenados();
                    break;
                case 9:
                    visualizarEstructuraArbol();
                    break;
                case 10:
                    mostrarArbolPorNiveles();
                    break;
                case 11:
                    mostrarArbolPorNivelesRecursivo();
                    break;
                case 0:
                    System.out.println("¡Gracias por usar el sistema!");
                    break;
                default:
                    System.out.println("❌ Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 0);
    }
    
    /**
     * Muestra la lista completa de empleados registrados en el sistema.
     * Presenta los empleados en el orden en que fueron cargados desde el CSV.
     */
    private static void mostrarTodosLosEmpleados() {
        System.out.println("\n LISTA COMPLETA DE EMPLEADOS");
        System.out.println("-".repeat(40));
        if (empleadosList.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        
        for (int i = 0; i < empleadosList.size(); i++) {
            System.out.println((i + 1) + ". " + empleadosList.get(i));
        }
        System.out.println("-".repeat(40));
        System.out.println("Total: " + empleadosList.size() + " empleados");
    }
    
    /**
     * Permite al usuario buscar un empleado específico por su ID.
     * Utiliza el árbol binario para búsqueda eficiente y mide el tiempo de ejecución.
     */
    private static void buscarPorID() {
        System.out.print("\n🔍 Ingrese el ID del empleado a buscar: ");
        int id = leerEntero();
        
        long startTime = System.nanoTime();
        Empleado empleado = tree.searchByID(id);
        long endTime = System.nanoTime();
        
        if (empleado != null) {
            System.out.println(" Empleado encontrado: " + empleado);
        } else {
            System.out.println(" No se encontró empleado con ID: " + id);
        }
        
        System.out.println("⏱️ Tiempo de búsqueda: " + (endTime - startTime) + " nanosegundos");
    }
    
    /**
     * Permite al usuario buscar un empleado por su nombre.
     * La búsqueda es case-insensitive y mide el tiempo de ejecución.
     */
    private static void buscarPorNombre() {
        System.out.print("\n Ingrese el nombre del empleado a buscar: ");
        scanner.nextLine(); // Limpiar buffer
        String nombre = scanner.nextLine();
        
        long startTime = System.nanoTime();
        Empleado empleado = tree.searchByName(nombre);
        long endTime = System.nanoTime();
        
        if (empleado != null) {
            System.out.println(" Empleado encontrado: " + empleado);
        } else {
            System.out.println(" No se encontró empleado con nombre: " + nombre);
        }
        
        System.out.println(" Tiempo de búsqueda: " + (endTime - startTime) + " nanosegundos");
    }
    
    /**
     * Permite agregar un nuevo empleado al sistema.
     * Genera automáticamente el siguiente ID disponible y actualiza tanto el árbol como la lista.
     */
    private static void agregarEmpleado() {
        System.out.println("\n AGREGAR NUEVO EMPLEADO");
        
        // Generar automáticamente el siguiente ID disponible
        int nuevoID = generarSiguienteID();
        
        System.out.print("Ingrese el nombre del empleado: ");
        scanner.nextLine(); // Limpiar buffer
        String nombre = scanner.nextLine().trim();
        
        if (nombre.isEmpty()) {
            System.out.println("❌ El nombre no puede estar vacío");
            return;
        }
        
        Empleado nuevoEmpleado = new Empleado(nombre, nuevoID);
        tree.insert(nuevoEmpleado);
        empleadosList.add(nuevoEmpleado);
        
        System.out.println(" Empleado agregado exitosamente:");
        System.out.println(" ID asignado automáticamente: " + nuevoID);
        System.out.println(" Empleado: " + nuevoEmpleado);
    }
    
    /**
     * Genera automáticamente el siguiente ID disponible para un nuevo empleado.
     * Encuentra el ID más alto existente y le suma 1.
     * @return el siguiente ID disponible
     */
    private static int generarSiguienteID() {
        if (empleadosList.isEmpty()) {
            return 1;
        }
        
        // Encontrar el ID más alto y sumar 1
        int maxID = empleadosList.stream()
                                 .mapToInt(Empleado::getID)
                                 .max()
                                 .orElse(0);
        
        return maxID + 1;
    }
    
    /**
     * Permite eliminar un empleado del sistema por su ID.
     * Solicita confirmación antes de proceder con la eliminación.
     */
    private static void eliminarEmpleado() {
        System.out.print("\n Ingrese el ID del empleado a eliminar: ");
        int id = leerEntero();
        
        Empleado empleado = tree.searchByID(id);
        if (empleado == null) {
            System.out.println(" No se encontró empleado con ID: " + id);
            return;
        }
        
        System.out.println("Empleado a eliminar: " + empleado);
        System.out.print("¿Está seguro? (s/n): ");
        scanner.nextLine(); // Limpiar buffer
        String confirmacion = scanner.nextLine();
        
        if (confirmacion.toLowerCase().startsWith("s")) {
            boolean eliminado = tree.delete(id);
            if (eliminado) {
                empleadosList.removeIf(emp -> emp.getID() == id);
                System.out.println(" Empleado eliminado exitosamente");
            } else {
                System.out.println(" Error al eliminar el empleado");
            }
        } else {
            System.out.println("Operación cancelada");
        }
    }
    
    /**
     * Muestra estadísticas del sistema incluyendo total de empleados,
     * empleado con menor y mayor ID, y altura estimada del árbol.
     */
    private static void mostrarEstadisticas() {
        System.out.println("\n📊 ESTADÍSTICAS DEL SISTEMA");
        System.out.println("-".repeat(40));
        System.out.println("Total de empleados: " + tree.countNodes());
        
        try {
            Empleado min = tree.findMin();
            Empleado max = tree.findMax();
            System.out.println("Empleado con menor ID: " + min);
            System.out.println("Empleado con mayor ID: " + max);
        } catch (IllegalStateException e) {
            System.out.println("El árbol está vacío");
        }
        
        System.out.println("Altura estimada del árbol: ~" + Math.ceil(Math.log(tree.countNodes()) / Math.log(2)));
    }
    
    /**
     * Compara la eficiencia entre búsqueda con árbol binario vs búsqueda secuencial.
     * Realiza ambas búsquedas con el mismo empleado y muestra métricas de rendimiento.
     */
    private static void compararEficiencia() {
        System.out.println("\n⚡ COMPARACIÓN DE EFICIENCIA");
        System.out.println("=".repeat(50));
        
        if (empleadosList.isEmpty()) {
            System.out.println("No hay empleados para comparar");
            return;
        }
        
        // Seleccionar un empleado aleatorio para buscar
        int randomIndex = (int) (Math.random() * empleadosList.size());
        Empleado empleadoABuscar = empleadosList.get(randomIndex);
        int idABuscar = empleadoABuscar.getID();
        
        System.out.println("Buscando empleado: " + empleadoABuscar);
        System.out.println("En una base de datos de " + empleadosList.size() + " empleados");
        System.out.println("-".repeat(50));
        
        // Búsqueda con Árbol Binario
        System.out.println(" BÚSQUEDA CON ÁRBOL BINARIO:");
        long startTime = System.nanoTime();
        Empleado resultadoArbol = tree.searchByID(idABuscar);
        long tiempoArbol = System.nanoTime() - startTime;
        System.out.println("   Resultado: " + (resultadoArbol != null ? "ENCONTRADO" : "NO ENCONTRADO"));
        System.out.println("   Tiempo: " + tiempoArbol + " nanosegundos");
        System.out.println("   Complejidad: O(log n) - " + Math.ceil(Math.log(empleadosList.size()) / Math.log(2)) + " comparaciones máximas");
        
        // Búsqueda Secuencial
        System.out.println("\n BÚSQUEDA SECUENCIAL:");
        startTime = System.nanoTime();
        Empleado resultadoSecuencial = busquedaSecuencial(empleadosList, idABuscar);
        long tiempoSecuencial = System.nanoTime() - startTime;
        System.out.println("   Resultado: " + (resultadoSecuencial != null ? "ENCONTRADO" : "NO ENCONTRADO"));
        System.out.println("   Tiempo: " + tiempoSecuencial + " nanosegundos");
        System.out.println("   Complejidad: O(n) - hasta " + empleadosList.size() + " comparaciones");
        
        // Análisis de eficiencia
        System.out.println("\n ANÁLISIS DE EFICIENCIA:");
        System.out.println("-".repeat(50));
        double mejora = (double) tiempoSecuencial / tiempoArbol;
        System.out.printf("   El árbol binario es %.2fx más rápido\n", mejora);
        System.out.println("   Diferencia de tiempo: " + (tiempoSecuencial - tiempoArbol) + " nanosegundos");
        
        if (empleadosList.size() > 100) {
            System.out.println("\n VENTAJAS DEL ÁRBOL BINARIO:");
            System.out.println("   • Búsqueda más eficiente: O(log n) vs O(n)");
            System.out.println("   • Escalabilidad: La diferencia aumenta con más datos");
            System.out.println("   • Inserción y eliminación eficientes");
            System.out.println("   • Datos automáticamente ordenados");
        }
    }
    
    /**
     * Implementa búsqueda secuencial en la lista de empleados.
     * Utilizada para comparar eficiencia con el árbol binario.
     * @param lista lista de empleados donde buscar
     * @param id ID del empleado a buscar
     * @return empleado encontrado o null si no existe
     */
    private static Empleado busquedaSecuencial(List<Empleado> lista, int id) {
        for (Empleado emp : lista) {
            if (emp.getID() == id) {
                return emp;
            }
        }
        return null;
    }
    
    /**
     * Muestra los empleados ordenados por ID utilizando recorrido inorder del árbol.
     * Demuestra cómo el BST mantiene automáticamente el orden de los datos.
     */
    private static void mostrarEmpleadosOrdenados() {
        System.out.println("\n EMPLEADOS ORDENADOS POR ID (Recorrido Inorder)");
        System.out.println("-".repeat(50));
        if (tree.countNodes() == 0) {
            System.out.println("No hay empleados en el árbol");
            return;
        }
        
        tree.inorder();
        System.out.println("\n-".repeat(50));
        System.out.println("Los empleados se muestran automáticamente ordenados por ID");
        System.out.println("gracias a las propiedades del Árbol Binario de Búsqueda");
    }
    
    /**
     * Muestra la estructura visual del árbol binario de búsqueda.
     * Presenta el árbol en formato gráfico para entender su organización.
     */
    private static void visualizarEstructuraArbol() {
        System.out.println("\n ESTRUCTURA VISUAL DEL ÁRBOL BINARIO");
        System.out.println("=".repeat(50));
        if (tree.countNodes() == 0) {
            System.out.println("No hay empleados en el árbol");
            return;
        }
        
        tree.mostrarArbolVisual();
        System.out.println("\nExplicación:");
        System.out.println("• Los números más pequeños están a la IZQUIERDA");
        System.out.println("• Los números más grandes están a la DERECHA");
        System.out.println("• Esta estructura permite búsquedas eficientes O(log n)");
    }
    
    /**
     * Muestra el árbol organizado por niveles de profundidad usando BFS.
     * Útil para entender la altura y distribución de los nodos.
     */
    private static void mostrarArbolPorNiveles() {
        System.out.println("\n🌳 ÁRBOL POR NIVELES (Breadth-First Search)");
        System.out.println("=".repeat(50));
        if (tree.countNodes() == 0) {
            System.out.println("No hay empleados en el árbol");
            return;
        }
        
        tree.mostrarArbolPorNiveles();
        System.out.println("\nInformación:");
        System.out.println("• Nivel 0: Raíz del árbol");
        System.out.println("• Cada nivel hacia abajo duplica el número máximo de nodos");
        System.out.println("• La altura del árbol determina la eficiencia de búsqueda");
        System.out.println("• Este método usa BFS para mostrar niveles completos");
    }
    
    /**
     * Muestra el árbol organizado por niveles usando método recursivo.
     * Alternativa al método BFS para comparar implementaciones.
     */
    private static void mostrarArbolPorNivelesRecursivo() {
        System.out.println("\n🌳 ÁRBOL POR NIVELES (Método Recursivo)");
        System.out.println("=".repeat(50));
        if (tree.countNodes() == 0) {
            System.out.println("No hay empleados en el árbol");
            return;
        }
        
        tree.mostrarArbolPorNivelesRecursivo();
        System.out.println("\nInformación:");
        System.out.println("• Este método usa recursión para mostrar cada nivel");
        System.out.println("• Útil para entender la estructura recursiva del árbol");
        System.out.println("• Compara con el método BFS (opción 10) para ver diferencias");
    }
    
    /**
     * Método utilitario para leer un entero del usuario con validación.
     * Maneja errores de entrada y solicita reingresar datos inválidos.
     * @return entero válido ingresado por el usuario
     */
    private static int leerEntero() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.print("❌ Ingrese un número válido: ");
                scanner.nextLine(); // Limpiar buffer
            }
        }
    }

}
