import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;

public class Tree {
    Node root;
    private PrintWriter logWriter;

    public Tree (String logFile) throws IOException {
        root = null;
        logWriter = new PrintWriter(new FileWriter(logFile, true));
    }
    private void log(String message) {
        logWriter.println(message + " " + LocalDate.now() + " " + LocalTime.now());
        logWriter.flush();
    }

    public void insert(Empleado Data) {
        root = insertRec(root, Data);
        log("Insertando valor: " + Data);
    }
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
     public void inorder(){
        inorderRec(root);
     }

     private void inorderRec(Node root){
        if (root != null ){
            inorderRec(root.Left);
            System.out.print("[" + root.Data + " " + "]");
            inorderRec(root.Right);
        }
     }

     public boolean search(Empleado data){
        boolean found = searchRec(root, data);
         log("Búsqueda de " + data + ": " + (found ? "ENCONTRADO" : "NO ENCONTRADO"));
         return found;
     }

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

    public Empleado findMin(){
        return findMinRec(root);
    }

    private Empleado findMinRec(Node root) {
        if (root == null){
            throw new IllegalStateException("Tree is empty");
        }

        if (root.Left == null){
            return root.Data;
        }

        return findMinRec(root.Left);
    }

    public Empleado findMax(){
        return findMaxRec(root);
    }

    private Empleado findMaxRec(Node root){
        if (root == null)
            throw new IllegalStateException("Tree is empty");

        if (root.Right == null)
            return root.Data;

        return findMaxRec(root.Right);
    }

    // Búsqueda por ID
    public Empleado searchByID(int id) {
        Empleado result = searchByIDRec(root, id);
        log("Búsqueda por ID " + id + ": " + (result != null ? "ENCONTRADO - " + result : "NO ENCONTRADO"));
        return result;
    }
    
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
    
    // Búsqueda por nombre
    public Empleado searchByName(String nombre) {
        Empleado result = searchByNameRec(root, nombre);
        log("Búsqueda por nombre '" + nombre + "': " + (result != null ? "ENCONTRADO - " + result : "NO ENCONTRADO"));
        return result;
    }
    
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
    
    // Eliminar empleado por ID
    public boolean delete(int id) {
        int initialSize = countNodes();
        root = deleteRec(root, id);
        int finalSize = countNodes();
        boolean deleted = finalSize < initialSize;
        log("Eliminación de empleado ID " + id + ": " + (deleted ? "EXITOSA" : "NO ENCONTRADO"));
        return deleted;
    }
    
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
    
    // Contar nodos en el árbol
    public int countNodes() {
        return countNodesRec(root);
    }
    
    private int countNodesRec(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodesRec(root.Left) + countNodesRec(root.Right);
    }

    // Mostrar árbol de forma visual
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
    
    private void mostrarArbolVisualRec(Node nodo, String prefijo, boolean esUltimo) {
        if (nodo != null) {
            System.out.println(prefijo + (esUltimo ? "└── " : "├── ") + 
                             nodo.Data.getID() + " - " + nodo.Data.getNombre());
            
            // Determinar si hay hijos
            boolean tieneHijoIzq = nodo.Left != null;
            boolean tieneHijoDer = nodo.Right != null;
            
            // Mostrar hijo derecho primero (para mantener orden visual correcto)
            if (tieneHijoDer) {
                mostrarArbolVisualRec(nodo.Right, 
                                    prefijo + (esUltimo ? "    " : "│   "), 
                                    !tieneHijoIzq);
            }
            
            // Mostrar hijo izquierdo
            if (tieneHijoIzq) {
                mostrarArbolVisualRec(nodo.Left, 
                                    prefijo + (esUltimo ? "    " : "│   "), 
                                    true);
            }
        }
    }
    
    // Mostrar árbol con niveles (alternativa más compacta)
    public void mostrarArbolPorNiveles() {
        if (root == null) {
            System.out.println("El árbol está vacío");
            return;
        }
        
        int altura = calcularAltura(root);
        System.out.println("Árbol por niveles (altura: " + altura + "):");
        System.out.println();
        
        for (int i = 0; i < altura; i++) {
            System.out.print("Nivel " + i + ": ");
            mostrarNivel(root, i);
            System.out.println();
        }
    }
    
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
    
    private int calcularAltura(Node nodo) {
        if (nodo == null) {
            return 0;
        }
        
        int alturaIzq = calcularAltura(nodo.Left);
        int alturaDer = calcularAltura(nodo.Right);
        
        return Math.max(alturaIzq, alturaDer) + 1;
    }

    // Cierra el Log
    public void closeLog(){
        if (logWriter != null){
            logWriter.close();
        }
    }
}
