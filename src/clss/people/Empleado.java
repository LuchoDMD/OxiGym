package clss.people;

public abstract class Empleado extends Persona
{
    private int sueldoMin;
    String lname;
    String fname;
    
    /** BUILDERS **/
    public Empleado()
    {

    }
    public Empleado(String lname, String fname, int dni, int telefono, int sueldoMin)
    {
        super(lname, fname, dni, telefono);
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
                ", contacto=" + telefono +
                '}';
    }
}
