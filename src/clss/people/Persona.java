package clss.people;

import clss.use.Telefono;

public class Persona /**FALTA LA FORMA DE VOLVERLA ABSTRACTA*/
{
    protected String lname, fname;
    protected int dni;
    protected Telefono contacto;

    /** BUILDERS **/
    public Persona()
    {

    }
    public Persona(String lname, String fname, int dni, Telefono contacto)
    {
        this.lname = lname;
        this.fname = fname;
        this.dni = dni;
        this.contacto = contacto;
    }

    /** GETTERS & SETTERS **/
    public String getLname() {
        return lname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }
    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public int getDni() {
        return dni;
    }
    public void setDni(int dni) {
        this.dni = dni;
    }
    public Telefono getContacto() {
        return contacto;
    }
    public void setContacto(Telefono contacto) {
        this.contacto = contacto;
    }

    /** OTHERS **/
    @Override
    public String toString() {
        return  "\n>Apellido/Nombre.: "+lname+" "+fname+
                "\n>D.N.I...........: "+dni+
                "\n>Contacto"+getContacto().toString();
    }
}
