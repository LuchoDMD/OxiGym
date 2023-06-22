package clss.people;

import java.util.List;

import clss.UI.GeneralUI;
import clss.use.Membresias;

public class Cliente extends Persona
{
    private Membresias memb;
    private int couta;

    //private String[] disciplinas;

    /** BUILDERS **/
    public Cliente()
    {

    }

    public Cliente(String lname, String fname, int dni, int telefono) {
        super(lname, fname, dni, telefono);
    }

    public Cliente(String lname, String fname, int dni, int telefono, Membresias memb, int couta)
    {
        super(lname, fname, dni, telefono);
        this.memb = memb;
        this.couta = couta;
    }

    /** GETTERS & SETTERS **/
    public Membresias getMemb()
    {
        return memb;
    }
    public void setMemb(Membresias memb) {
        this.memb = memb;
    }
    public int getCouta()
    {
        return couta;
    }
    public void setCouta(int couta) ///La cuota no se setea ver abajo
    {
        this.couta = couta;
    }

    public Object[] rowForTable() {
        Object[] row = {this.getNombre(), this.getApellido(), this.getDni(), this.getMemb()};
        return row;
    }

    public static Cliente getClientePorDni(int dni) {
        List<Cliente> clientes = (List<Cliente>) GeneralUI.readListJson("clientes.json");
        for (Cliente c: clientes) {
            if (c.getDni() == dni) {
                return c;
            }
        }
        return null;
    }

    public static boolean eliminarClientePorDni(int dni) {
        List<Cliente> clientes = (List<Cliente>) GeneralUI.readListJson("clientes.json");
        for (Cliente c: clientes) {
            if (c.getDni() == dni) {
                clientes.remove(c);
                GeneralUI.listToJson(clientes, "clientes.json");
                return true;
            }
        }
        return false;
    }

    /** OTHERS **/
    @Override
    public String toString() {
        return  super.toString()+
                ">Membresia: "+ memb.toString()+
                ">Cuota....: "+ couta;
    }
}

/* Se calcula a travez de la membresia que recibe y las disciplinas que este mismo realice. Podria crearse una
   interface descuento que amortize el pago.(Aun no se como) */