public class Empleado {
    String Nombre;
    String ID;
    public Empleado(String valName, String valID) {
        this.Nombre = valName;
        this.ID = valID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getID() {
        return ID;
    }

    public String getNombre() {
        return Nombre;
    }
}
