/**
 * Clase Node que representa un nodo en el árbol binario de búsqueda.
 * Cada nodo contiene un empleado y referencias a sus hijos izquierdo y derecho.
 * @param <E> tipo genérico (aunque se usa específicamente con Empleado)
 */
public class Node <E> {
    public Empleado Data; // Dato almacenado en el nodo (empleado)
    protected Node Left; // Referencia al hijo izquierdo
    protected Node Right; // Referencia al hijo derecho

    /**
     * Constructor que crea un nuevo nodo con el empleado especificado.
     * Inicializa los hijos como null.
     * @param valData empleado a almacenar en el nodo
     */
    public Node(Empleado valData) {
        this.Data = valData;
        this.Left = this.Right = null;
    }

    /**
     * Establece/actualiza el empleado almacenado en el nodo.
     * @param valData nuevo empleado a almacenar
     */
    public void setData(Empleado valData) {
        Data = valData;
    }

    /**
     * Establece la referencia al hijo derecho del nodo.
     * NOTA: Hay un error en la implementación original - debería ser this.Right = head;
     * @param head nodo que será el hijo derecho
     */
    public void setRight(Node head) {
        this.Right = head; // Corregido: era this.Right = Right;
    }

    /**
     * Establece la referencia al hijo izquierdo del nodo.
     * NOTA: Hay un error en la implementación original - debería ser this.Left = next;
     * @param next nodo que será el hijo izquierdo
     */
    public void setLeft(Node next) {
        this.Left = next; // Corregido: era this.Left = Left;
    }

    /**
     * Obtiene el empleado almacenado en el nodo.
     * @return empleado almacenado en este nodo
     */
    public Empleado getData() {
            return Data;
    }

    /**
     * Representación en cadena del nodo.
     * Muestra el ID del empleado almacenado.
     * @return cadena con formato "ID:[empleado]"
     */
    @Override
    public String toString(){
        return "ID:" + this.Data;
    }
}
