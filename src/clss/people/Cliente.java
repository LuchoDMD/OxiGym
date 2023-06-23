package clss.people;

import java.util.ArrayList;
import java.util.List;

import clss.UI.GeneralUI;
import clss.use.Disciplinas;
import clss.use.Membresias;

public class Cliente extends Persona
{
    public static final int cuota= 6000;
    private int couta;
    private Disciplinas disciplina;

    /** BUILDERS **/
    public Cliente()
    {

    }

    public Cliente(String lname, String fname, int dni, String telefono) {
        super(lname, fname, dni, telefono);
    }

    public Cliente(String lname, String fname, int dni, String telefono, Disciplinas disciplina)
    {
        super(lname, fname, dni, telefono);
        this.disciplina= disciplina;
        this.couta = cuota;
    }

    /** GETTERS & SETTERS **/
    public Disciplinas getDisciplina() {return disciplina;}

    public void setDisciplina(Disciplinas disciplina) {this.disciplina = disciplina;}

    public int getCouta()
    {
        return couta;
    }
    public void setCouta(int couta) ///La cuota no se setea ver abajo
    {
        this.couta = couta;
    }

    public Object[] rowForTable() {
        Object[] row = {this.getNombre(), this.getApellido(), this.getDni(), this.getDisciplina()};
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
    // agrega un cliente al archivo de clientes
    public static void addCliente(Cliente c) {
        List<Cliente> clientes = (List<Cliente>) GeneralUI.readListJson("clientes.json");
        // si el archivo no existe crea una lista vacia
        if (clientes == null) 
            clientes = new ArrayList<Cliente>();
        clientes.add(c);
        GeneralUI.listToJson(clientes, "clientes.json");
    }

    /** OTHERS **/
    @Override
    public String toString() {
        return String.valueOf(dni);
    }
}

/* Se calcula a travez de la membresia que recibe y las disciplinas que este mismo realice. Podria crearse una
   interface descuento que amortize el pago.(Aun no se como) */