package clss.people;

import java.util.ArrayList;
import java.util.List;

import clss.UI.GeneralUI;

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

    // agrega un empleado al archivo de empleados
    public static void addEmpleado(Empleado e) {
        List<Empleado> empleados = (List<Empleado>) GeneralUI.readListJson("empleados.json");
        if (empleados == null)
            empleados = new ArrayList<Empleado>();
        empleados.add(e);
        GeneralUI.listToJson(empleados, "empleados.json");
    } 

    // elimina un empleado de la lista de empleados y devuelve true si lo logra
    public static boolean eliminarEmpleado(Empleado e) {
        List<Empleado> empleados = (List<Empleado>) GeneralUI.readListJson("empleados.json");
        if (empleados == null)
            return false;
        if (empleados.remove(e)) {
            GeneralUI.listToJson(empleados, "empleados.json");
            return true;
        }
        return false;
    }

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
