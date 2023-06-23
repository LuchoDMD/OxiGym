package clss.people;

public abstract class Empleado extends Persona
{
    int sueldo;
    String lname;
    String fname;
    
    /** BUILDERS **/
    public Empleado()
    {

    }
    public Empleado(String lname, String fname, int dni, String telefono, int sueldoMin)
    {
        super(lname, fname, dni, telefono);
        this.sueldo= sueldoMin;
    }
    /** GETTERS & SETTERS **/
    public int getSueldo() {
        return sueldo;
    }
    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }
    public String getLname() {
        return lname;
    }
    public String getFname() {
        return fname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    
    /** OTHERS **/

    @Override
    public String toString() {
        return "Empleado{" +
                "sueldoMin=" + sueldo +
                ", lname='" + lname + '\'' +
                ", fname='" + fname + '\'' +
                ", dni=" + dni +
                ", contacto=" + telefono +
                '}';
    }
}
