
/**
 * Clase Empleado que representa un empleado con ID y nombre.
 * Utilizada como dato en el árbol binario de búsqueda.
 */
public class Empleado {
    String Nombre; // Nombre del empleado
    int ID; // Identificador único del empleado
    
    /**
     * Constructor para crear un nuevo empleado.
     * @param valName nombre del empleado
     * @param valID identificador único del empleado
     */
    public Empleado(String valName, int valID) {
        this.Nombre = valName;
        this.ID = valID;
    }

    /**
     * Establece el ID del empleado.
     * @param ID nuevo identificador para el empleado
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Establece el nombre del empleado.
     * @param nombre nuevo nombre para el empleado
     */
    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    /**
     * Obtiene el ID del empleado.
     * @return identificador del empleado
     */
    public int getID() {
        return ID;
    }

    /**
     * Obtiene el nombre del empleado.
     * @return nombre del empleado
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * Representación en cadena del empleado.
     * @return cadena con formato "ID: [id], Nombre: [nombre]"
     */
    @Override
    public String toString() {
        return "ID: " + ID + ", Nombre: " + Nombre;
    }

}
