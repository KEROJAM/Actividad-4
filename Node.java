public class Node <E> {
    public int Data;
    public String Name;
    protected Node head;
    protected Node next;
    protected Node last;
    protected Node tail;

    public Node(int valData) {
        this.Data = valData;
        //this.Name = valName;
        this.head = null;
        this.next = null;
        this.last = null;
        this.tail = null;
    }

    /**
     * Establece/actualiza el dato almacenado en el nodo.
     * @param valData nuevo dato
     */
    public void setData(int valData) {
        Data = valData;
    }

    /**
     * Define la referencia a la cabeza de la lista.
     * @param head nodo cabeza
     */
    public void setHead(Node head) {
        this.head = head;
    }

    /**
     * Define el enlace al siguiente nodo.
     * @param next siguiente nodo
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * Define el enlace al nodo anterior.
     * @param last nodo anterior
     */
    public void setLast(Node last) {
        this.last = last;
    }

    /**
     * Define la referencia a la cola de la lista.
     * @param tail nodo cola
     */
    public void setTail(Node tail) {
        this.tail = tail;
    }

    /**
     * Obtiene el dato almacenado en el nodo.
     * @return dato del nodo
     */
    public int getData() {
            return Data;
    }
}
