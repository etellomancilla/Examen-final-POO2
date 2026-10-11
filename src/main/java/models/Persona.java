package models;
// IL2. Clase abstracta
public abstract class Persona {
    protected int id;
    protected String nombre;
    protected String rut;
    protected String correo;

    public Persona(int id, String nombre, String rut, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.rut = rut;
        this.correo = correo;
    }

    public Persona() {}

    // IL1. Método a sobrescribir
    public abstract String getDetalle();
    
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getRut() { return rut; }
    public String getCorreo() { return correo; }
}
