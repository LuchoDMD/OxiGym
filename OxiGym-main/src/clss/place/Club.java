package clss.place;

import clss.UI.AdminUI;
import clss.UI.GeneralUI;
import clss.people.Cliente;
import clss.people.Empleado;
import clss.people.Persona;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Club extends AdminUI implements Serializable{
    ColeccionGenerica<Persona> club= new ColeccionGenerica<>();
    List<Sala> salas= new ArrayList<>();
    /*CLASE CALENDARIO*/

    public int contarEmpleados(){
        int count=0;
        for(Persona p : club){
            if(p instanceof Empleado)
                count++;
        }
        return count;
    }
    public int contarClientes(){
        int count=0;
        for(Persona p : club){
            if(p instanceof Cliente)
                count++;
        }
        return count;
    }
    public void agregarCliente(){
        club.agregar(getCliente());
    }
    public void agregarEmpleado(){
        club.agregar(getEmpleado());
    }
    public void agregarSala(){
        salas.add(getSala());
    }
    //CALCULAR GANANCIAS//
    public int calcularGanancias(){
        int count=0;
        for(Persona p : club){
            if(p instanceof Cliente){
                Cliente tempCliente= (Cliente) p;
                count+=tempCliente.getCouta();
            }
        }
        return count;
    }
    public void showAll(){
        club.listar();
    }
    public void showEmpleados(){
        for(Persona p : club){
            if(p instanceof Empleado)
                System.out.println(p.toString());
        }
    }
    public void showClientes(){
        for(Persona p : club){
            if(p instanceof Cliente)
                System.out.println(p.toString());
        }
    }
}