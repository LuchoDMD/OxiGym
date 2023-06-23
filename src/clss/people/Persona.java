package clss.people;

import java.util.Objects;

public class Persona
{
    protected String apellido, nombre;
    protected int dni;
    protected String telefono;

    /** BUILDERS **/
    public Persona()
    {

    }
    public Persona(String apellido, String nombre, int dni, String telefono)
    {
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
    }

    /** GETTERS & SETTERS **/
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String fname) {
        this.nombre = fname;
    }
    public int getDni() {
        return dni;
    }
    public void setDni(int dni) {
        this.dni = dni;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setContacto(String telefono) {
        this.telefono = telefono;
    }

    /** OTHERS **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return dni == persona.dni && Objects.equals(apellido, persona.apellido) && Objects.equals(nombre, persona.nombre);
    }
    @Override
    public int hashCode() {
        return Objects.hash(apellido, nombre, dni);
    }
    @Override
    public String toString() {
        return  "\n>Apellido/Nombre.: "+nombre+" "+apellido+
                "\n>D.N.I...........: "+dni+
                "\n>Telefono"+telefono;
    }
}
