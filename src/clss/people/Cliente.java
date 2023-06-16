package clss.people;

import clss.use.Membresias;
import clss.use.Telefono;

public class Cliente extends Persona
{
    private Membresias memb;
    private int couta;

    //private String[] disciplinas;

    /** BUILDERS **/
    public Cliente()
    {

    }

    public Cliente(String lname, String fname, int dni, Telefono contacto, Membresias memb, int couta)
    {
        super(lname, fname, dni, contacto);
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