public class Node <E> {
    public Empleado Data;
    protected Node Left;
    protected Node Right;

    public Node(Empleado valData) {
        this.Data = valData;
        this.Left = this.Right = null;
    }

    /**
     * Establece/actualiza el dato almacenado en el nodo.
     * @param valData nuevo dato
     */
    public void setData(Empleado valData) {
        Data = valData;
    }

    /**
     * Define la referencia a la cabeza de la lista.
     * @param head nodo cabeza
     */
    public void setRight(Node head) {
        this.Right = Right;
    }

    /**
     * Define el enlace al siguiente nodo.
     * @param next siguiente nodo
     */
    public void setLeft(Node next) {
        this.Left = Left;
    }

    /**
     * Obtiene el dato almacenado en el nodo.
     * @return dato del nodo
     */
    public Empleado getData() {
            return Data;
    }

    @Override
    public String toString(){
        return "ID:" + this.Data;
    }
}
