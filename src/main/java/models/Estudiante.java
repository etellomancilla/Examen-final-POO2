package models;
public class Estudiante extends Persona {
    private String curso;

    public Estudiante(int id, String nombre, String rut, String correo, String curso) {
        super(id, nombre, rut, correo);
        this.curso = curso;
    }
    
    // IL1. Sobrecarga de constructor
    public Estudiante(String nombre, String rut, String correo, String curso) {
        super(0, nombre, rut, correo);
        this.curso = curso;
    }

    @Override
    public String getDetalle() {
        return "Estudiante: " + nombre + " - Curso: " + curso;
    }

    public String getCurso() { return curso; }
}
