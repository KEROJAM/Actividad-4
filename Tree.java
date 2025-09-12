import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Clase Tree que implementa un Árbol Binario de Búsqueda (BST) para gestionar empleados.
 * Permite insertar, buscar, eliminar y visualizar empleados de manera eficiente.
 */
public class Tree {
    Node root; // Nodo raíz del árbol
    private PrintWriter logWriter; // Writer para registrar operaciones en archivo de log

    /**
     * Constructor que inicializa el árbol vacío y configura el archivo de log.
     * @param logFile ruta del archivo donde se registrarán las operaciones
     * @throws IOException si hay problemas al crear/abrir el archivo de log
     */
    public Tree (String logFile) throws IOException {
        root = null;
        logWriter = new PrintWriter(new FileWriter(logFile, true));
    }
    /**
     * Método privado para registrar operaciones en el archivo de log con timestamp.
     * @param message mensaje a registrar en el log
     */
    private void log(String message) {
        logWriter.println(message + " " + LocalDate.now() + " " + LocalTime.now());
        logWriter.flush();
    }

    /**
     * Método público para insertar un empleado en el árbol.
     * Utiliza el método recursivo insertRec y registra la operación en el log.
     * @param Data empleado a insertar en el árbol
     */
    public void insert(Empleado Data) {
        root = insertRec(root, Data);
        log("Insertando valor: " + Data);
    }
    /**
     * Método recursivo privado para insertar un empleado en el árbol.
     * Mantiene las propiedades del BST: menores a la izquierda, mayores a la derecha.
     * @param root nodo actual en la recursión
     * @param Data empleado a insertar
     * @return nodo raíz del subárbol después de la inserción
     */
    private Node insertRec(Node root, Empleado Data){
        if (root == null){
            root = new Node(Data);
            return root;
        }

        if (Data.ID < root.Data.ID){
            root.Left = insertRec(root.Left, Data);
        } else if (Data.ID > root.Data.ID){
            root.Right = insertRec(root.Right, Data);
        }
        return root;
    }
     /**
      * Método público para realizar un recorrido inorder del árbol.
      * Muestra los empleados ordenados por ID de menor a mayor.
      */
     public void inorder(){
        inorderRec(root);
     }

     /**
      * Método recursivo privado para el recorrido inorder.
      * Visita: subárbol izquierdo -> nodo actual -> subárbol derecho
      * @param root nodo actual en la recursión
      */
     private void inorderRec(Node root){
        if (root != null ){
            inorderRec(root.Left);
            System.out.print("[" + root.Data + " " + "]");
            System.out.println("|");
            inorderRec(root.Right);
        }
     }

     /**
      * Método público para buscar un empleado en el árbol.
      * @param data empleado a buscar
      * @return true si el empleado existe, false en caso contrario
      */
     public boolean search(Empleado data){
        boolean found = searchRec(root, data);
         log("Búsqueda de " + data + ": " + (found ? "ENCONTRADO" : "NO ENCONTRADO"));
         return found;
     }

    /**
     * Método recursivo privado para buscar un empleado por ID.
     * Utiliza las propiedades del BST para búsqueda eficiente O(log n).
     * @param root nodo actual en la recursión
     * @param data empleado a buscar
     * @return true si encuentra el empleado, false en caso contrario
     */
    private boolean searchRec(Node root, Empleado data){
        if (root == null){
            return false;
        }

        if (root.Data.ID == data.ID){
            return true;
        }

        if (data.ID < root.Data.ID){
            return searchRec(root.Left, data);
        } else {
            return searchRec(root.Right, data);
        }
    }

    /**
     * Método público para encontrar el empleado con el ID mínimo en el árbol.
     * @return empleado con el menor ID
     * @throws IllegalStateException si el árbol está vacío
     */
    public Empleado findMin(){
        return findMinRec(root);
    }

    /**
     * Método recursivo privado para encontrar el empleado con ID mínimo.
     * En un BST, el mínimo siempre está en el nodo más a la izquierda.
     * @param root nodo actual en la recursión
     * @return empleado con el menor ID
     * @throws IllegalStateException si el árbol está vacío
     */
    private Empleado findMinRec(Node root) {
        if (root == null){
            throw new IllegalStateException("Tree is empty");
        }

        if (root.Left == null){
            return root.Data;
        }

        return findMinRec(root.Left);
    }

    /**
     * Método público para encontrar el empleado con el ID máximo en el árbol.
     * @return empleado con el mayor ID
     * @throws IllegalStateException si el árbol está vacío
     */
    public Empleado findMax(){
        return findMaxRec(root);
    }

    /**
     * Método recursivo privado para encontrar el empleado con ID máximo.
     * En un BST, el máximo siempre está en el nodo más a la derecha.
     * @param root nodo actual en la recursión
     * @return empleado con el mayor ID
     * @throws IllegalStateException si el árbol está vacío
     */
    private Empleado findMaxRec(Node root){
        if (root == null)
            throw new IllegalStateException("Tree is empty");

        if (root.Right == null)
            return root.Data;

        return findMaxRec(root.Right);
    }

    /**
     * Método público para buscar un empleado específicamente por su ID.
     * Más eficiente que buscar por objeto completo.
     * @param id ID del empleado a buscar
     * @return empleado encontrado o null si no existe
     */
    public Empleado searchByID(int id) {
        Empleado result = searchByIDRec(root, id);
        log("Búsqueda por ID " + id + ": " + (result != null ? "ENCONTRADO - " + result : "NO ENCONTRADO"));
        return result;
    }
    
    /**
     * Método recursivo privado para buscar empleado por ID.
     * Utiliza las propiedades del BST para búsqueda eficiente O(log n).
     * @param root nodo actual en la recursión
     * @param id ID del empleado a buscar
     * @return empleado encontrado o null si no existe
     */
    private Empleado searchByIDRec(Node root, int id) {
        if (root == null) {
            return null;
        }
        
        if (root.Data.ID == id) {
            return root.Data;
        }
        
        if (id < root.Data.ID) {
            return searchByIDRec(root.Left, id);
        } else {
            return searchByIDRec(root.Right, id);
        }
    }
    
    /**
     * Método público para buscar un empleado por su nombre.
     * Nota: Esta búsqueda es O(n) ya que el nombre no mantiene el orden del BST.
     * @param nombre nombre del empleado a buscar (case-insensitive)
     * @return empleado encontrado o null si no existe
     */
    public Empleado searchByName(String nombre) {
        Empleado result = searchByNameRec(root, nombre);
        log("Búsqueda por nombre '" + nombre + "': " + (result != null ? "ENCONTRADO - " + result : "NO ENCONTRADO"));
        return result;
    }
    
    /**
     * Método recursivo privado para buscar empleado por nombre.
     * Debe recorrer todo el árbol ya que los nombres no siguen el orden del BST.
     * @param root nodo actual en la recursión
     * @param nombre nombre del empleado a buscar
     * @return empleado encontrado o null si no existe
     */
    private Empleado searchByNameRec(Node root, String nombre) {
        if (root == null) {
            return null;
        }
        
        if (root.Data.Nombre.equalsIgnoreCase(nombre)) {
            return root.Data;
        }
        
        // Buscar en ambos subárboles ya que el nombre no sigue el orden del BST
        Empleado leftResult = searchByNameRec(root.Left, nombre);
        if (leftResult != null) {
            return leftResult;
        }
        
        return searchByNameRec(root.Right, nombre);
    }
    
    /**
     * Método público para eliminar un empleado del árbol por su ID.
     * Mantiene las propiedades del BST después de la eliminación.
     * @param id ID del empleado a eliminar
     * @return true si se eliminó exitosamente, false si no se encontró
     */
    public boolean delete(int id) {
        int initialSize = countNodes();
        root = deleteRec(root, id);
        int finalSize = countNodes();
        boolean deleted = finalSize < initialSize;
        log("Eliminación de empleado ID " + id + ": " + (deleted ? "EXITOSA" : "NO ENCONTRADO"));
        return deleted;
    }
    
    /**
     * Método recursivo privado para eliminar un nodo del árbol.
     * Maneja tres casos: nodo hoja, nodo con un hijo, nodo con dos hijos.
     * @param root nodo actual en la recursión
     * @param id ID del empleado a eliminar
     * @return nodo raíz del subárbol después de la eliminación
     */
    private Node deleteRec(Node root, int id) {
        if (root == null) {
            return root;
        }
        
        if (id < root.Data.ID) {
            root.Left = deleteRec(root.Left, id);
        } else if (id > root.Data.ID) {
            root.Right = deleteRec(root.Right, id);
        } else {
            // Nodo encontrado, proceder con eliminación
            if (root.Left == null) {
                return root.Right;
            } else if (root.Right == null) {
                return root.Left;
            }
            
            // Nodo con dos hijos: obtener el sucesor inorder
            root.Data = findMinRec(root.Right);
            root.Right = deleteRec(root.Right, root.Data.ID);
        }
        
        return root;
    }
    
    /**
     * Método público para contar el total de nodos en el árbol.
     * @return número total de empleados en el árbol
     */
    public int countNodes() {
        return countNodesRec(root);
    }
    
    /**
     * Método recursivo privado para contar nodos.
     * @param root nodo actual en la recursión
     * @return número de nodos en el subárbol
     */
    private int countNodesRec(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodesRec(root.Left) + countNodesRec(root.Right);
    }

    /**
     * Método público para mostrar la estructura visual del árbol.
     * Presenta el árbol en formato de árbol con líneas y conectores.
     */
    public void mostrarArbolVisual() {
        if (root == null) {
            System.out.println("El árbol está vacío");
            return;
        }
        
        System.out.println("Estructura del Árbol Binario:");
        System.out.println("(Formato: ID - Nombre)");
        System.out.println();
        mostrarArbolVisualRec(root, "", true);
    }
    
    /**
     * Método recursivo privado para mostrar el árbol visualmente.
     * Utiliza caracteres ASCII para crear una representación gráfica del árbol.
     * @param nodo nodo actual a mostrar
     * @param prefijo prefijo de líneas para mantener la estructura visual
     * @param esUltimo indica si es el último nodo en su nivel
     */
    private void mostrarArbolVisualRec(Node nodo, String prefijo, boolean esUltimo) {
        if (nodo != null) {
            System.out.println(prefijo + (esUltimo ? "└── " : "├── ") + 
                             nodo.Data.getID() + " - " + nodo.Data.getNombre());
            
            // Determinar si hay hijos
            boolean tieneHijoIzq = nodo.Left != null;
            boolean tieneHijoDer = nodo.Right != null;
            
            // Crear el nuevo prefijo para los hijos
            String nuevoPrefijo = prefijo + (esUltimo ? "    " : "│   ");
            
            // Mostrar hijo izquierdo primero (valores menores)
            if (tieneHijoIzq) {
                mostrarArbolVisualRec(nodo.Left, nuevoPrefijo, !tieneHijoDer);
            }
            
            // Mostrar hijo derecho (valores mayores)
            if (tieneHijoDer) {
                mostrarArbolVisualRec(nodo.Right, nuevoPrefijo, true);
            }
        }
    }
    
    /**
     * Método público para mostrar el árbol organizado por niveles.
     * Muestra cada nivel del árbol en una línea separada usando BFS.
     */
    public void mostrarArbolPorNiveles() {
        if (root == null) {
            System.out.println("El árbol está vacío");
            return;
        }
        
        int altura = calcularAltura(root);
        System.out.println("Árbol por niveles (altura: " + altura + "):");
        System.out.println();
        
        // Usar una cola para recorrido por niveles (BFS)
        java.util.Queue<Node> cola = new java.util.LinkedList<>();
        cola.offer(root);
        int nivelActual = 0;
        
        while (!cola.isEmpty()) {
            int nodosEnNivel = cola.size();
            System.out.print("Nivel " + nivelActual + ": ");
            
            for (int i = 0; i < nodosEnNivel; i++) {
                Node nodo = cola.poll();
                System.out.print("[" + nodo.Data.getID() + "-" + nodo.Data.getNombre() + "] ");
                
                if (nodo.Left != null) {
                    cola.offer(nodo.Left);
                }
                if (nodo.Right != null) {
                    cola.offer(nodo.Right);
                }
            }
            System.out.println();
            nivelActual++;
        }
    }
    
    /**
     * Método alternativo para mostrar el árbol por niveles usando recursión.
     * Útil para entender la estructura recursiva del árbol.
     */
    public void mostrarArbolPorNivelesRecursivo() {
        if (root == null) {
            System.out.println("El árbol está vacío");
            return;
        }
        
        int altura = calcularAltura(root);
        System.out.println("Árbol por niveles (método recursivo - altura: " + altura + "):");
        System.out.println();
        
        for (int i = 0; i < altura; i++) {
            System.out.print("Nivel " + i + ": ");
            mostrarNivel(root, i);
            System.out.println();
        }
    }
    
    /**
     * Método recursivo privado para mostrar todos los nodos de un nivel específico.
     * @param nodo nodo actual en la recursión
     * @param nivel nivel objetivo a mostrar (0 = nivel actual)
     */
    private void mostrarNivel(Node nodo, int nivel) {
        if (nodo == null) {
            return;
        }
        
        if (nivel == 0) {
            System.out.print("[" + nodo.Data.getID() + "-" + nodo.Data.getNombre() + "] ");
        } else {
            mostrarNivel(nodo.Left, nivel - 1);
            mostrarNivel(nodo.Right, nivel - 1);
        }
    }
    
    /**
     * Método recursivo privado para calcular la altura del árbol.
     * La altura es el número máximo de niveles desde la raíz hasta una hoja.
     * @param nodo nodo actual en la recursión
     * @return altura del subárbol
     */
    private int calcularAltura(Node nodo) {
        if (nodo == null) {
            return 0;
        }
        
        int alturaIzq = calcularAltura(nodo.Left);
        int alturaDer = calcularAltura(nodo.Right);
        
        return Math.max(alturaIzq, alturaDer) + 1;
    }

    /**
     * Método para crear un árbol balanceado a partir de una lista ordenada de empleados.
     * Utiliza el algoritmo de construcción balanceada dividiendo recursivamente la lista.
     * @param empleados lista ordenada de empleados
     */
    public void crearArbolBalanceado(java.util.List<Empleado> empleados) {
        // Limpiar el árbol actual
        root = null;
        
        // Convertir lista a array para acceso por índice eficiente
        Empleado[] array = empleados.toArray(new Empleado[0]);
        
        // Construir árbol balanceado recursivamente
        root = construirBalanceadoRec(array, 0, array.length - 1);
        
        log("Árbol balanceado creado con " + empleados.size() + " empleados");
    }
    
    /**
     * Método recursivo para construir un árbol balanceado.
     * Toma el elemento del medio como raíz y construye subárboles recursivamente.
     * @param array array ordenado de empleados
     * @param inicio índice de inicio del subarray
     * @param fin índice de fin del subarray
     * @return nodo raíz del subárbol balanceado
     */
    private Node construirBalanceadoRec(Empleado[] array, int inicio, int fin) {
        if (inicio > fin) {
            return null;
        }
        
        // Encontrar el punto medio
        int medio = inicio + (fin - inicio) / 2;
        
        // Crear nodo con el elemento del medio
        Node nodo = new Node(array[medio]);
        
        // Construir subárboles recursivamente
        nodo.Left = construirBalanceadoRec(array, inicio, medio - 1);
        nodo.Right = construirBalanceadoRec(array, medio + 1, fin);
        
        return nodo;
    }
    
    /**
     * Método para verificar si el árbol está balanceado.
     * Un árbol está balanceado si la diferencia de altura entre subárboles no excede 1.
     * @return true si el árbol está balanceado, false en caso contrario
     */
    public boolean estaBalanceado() {
        return verificarBalance(root) != -1;
    }
    
    /**
     * Método recursivo para verificar el balance del árbol.
     * @param nodo nodo actual
     * @return altura del subárbol si está balanceado, -1 si no está balanceado
     */
    private int verificarBalance(Node nodo) {
        if (nodo == null) {
            return 0;
        }
        
        int alturaIzq = verificarBalance(nodo.Left);
        if (alturaIzq == -1) return -1;
        
        int alturaDer = verificarBalance(nodo.Right);
        if (alturaDer == -1) return -1;
        
        if (Math.abs(alturaIzq - alturaDer) > 1) {
            return -1;
        }
        
        return Math.max(alturaIzq, alturaDer) + 1;
    }
    
    /**
     * Método para obtener información detallada sobre la estructura del árbol.
     */
    public void mostrarInformacionArbol() {
        if (root == null) {
            System.out.println("El árbol está vacío");
            return;
        }
        
        int altura = calcularAltura(root);
        int nodos = countNodes();
        boolean balanceado = estaBalanceado();
        
        System.out.println("📊 INFORMACIÓN DEL ÁRBOL:");
        System.out.println("═".repeat(40));
        System.out.println("• Total de nodos: " + nodos);
        System.out.println("• Altura actual: " + altura);
        System.out.println("• Altura óptima: " + (int)Math.ceil(Math.log(nodos) / Math.log(2)));
        System.out.println("• ¿Está balanceado?: " + (balanceado ? "✅ SÍ" : "❌ NO"));
        
        if (!balanceado) {
            System.out.println("• Tipo de estructura: Árbol degenerado (como lista enlazada)");
            System.out.println("• Complejidad de búsqueda: O(n) en lugar de O(log n)");
        } else {
            System.out.println("• Tipo de estructura: Árbol balanceado");
            System.out.println("• Complejidad de búsqueda: O(log n)");
        }
    }

    /**
     * Método para cerrar el archivo de log y liberar recursos.
     * Debe llamarse al finalizar el uso del árbol para evitar memory leaks.
     */
    public void closeLog(){
        if (logWriter != null){
            logWriter.close();
        }
    }
}
