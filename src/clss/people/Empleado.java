package clss.people;

import clss.use.Telefono;

public abstract class Empleado extends Persona
{
    private int sueldoMin;

    /** BUILDERS **/
    public Empleado()
    {

    }
    public Empleado(String lname, String fname, int dni, Telefono contacto, int sueldoMin)
    {
        super(lname, fname, dni, contacto);
        this.sueldoMin = sueldoMin;
    }
    /** GETTERS & SETTERS **/
    public int getSueldoMin() {
        return sueldoMin;
    }
    public abstract int salarioMensual();
    /** OTHERS **/
    @Override
    public String toString() {
        return "Empleado{" +
                "sueldoMin=" + sueldoMin +
                ", lname='" + lname + '\'' +
                ", fname='" + fname + '\'' +
                ", dni=" + dni +
                ", contacto=" + contacto +
                '}';
    }
}
