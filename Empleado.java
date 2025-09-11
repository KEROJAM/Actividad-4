
public class Empleado {
    String Nombre;
    int ID;
    public Empleado(String valName, int valID) {
        this.Nombre = valName;
        this.ID = valID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getID() {
        return ID;
    }

    public String getNombre() {
        return Nombre;
    }

    @Override
    public String toString() {
        return "ID: " + ID + ", Nombre: " + Nombre;
    }

}
