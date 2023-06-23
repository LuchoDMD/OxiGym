package clss.people;

import clss.use.Disciplinas;
import java.util.LinkedList;
import java.util.List;

public class Instructor extends Empleado
{
    private Disciplinas disciplina;
    private List<Cliente>alumnos; /**Posiblemente puedan usarse una coleccion generica propia**/

    public Instructor(String apellido, String nombre, int dni, String telefono, int sueldoMin, Disciplinas disciplina)
    {
        super(apellido, nombre, dni, telefono, sueldoMin);
        this.disciplina = disciplina;
        this.alumnos= new LinkedList<>();
    }

    public boolean existeAlumno(Cliente alumno)
    {
        for(Cliente a : alumnos)
        {
            if(a.equals(alumno))
            {
                return true;
            }
        }
        return false;
    }
    public void agregarAlumno(Cliente alumno)
    {
        if(existeAlumno(alumno))
        {
            System.out.println("El alumno ya esta listado");
        }else
        {
            alumnos.add(alumno);
        }///Se puede trabajar con excepciones.
    }
    public Cliente devolverAlumno(String nombre, String apellido)
    {
        Cliente aux=null;
        for(Cliente a : alumnos)
        {
            if(a.apellido.equals(apellido)&&a.nombre.equals(nombre))
            {
                aux=a;
            }
        }
        return aux;
    }
    public Cliente removerAlumno(Cliente alumno)
    {
        if(existeAlumno(alumno))
        {
            Cliente aux=alumnos.get(alumnos.indexOf(alumno));
            alumnos.remove(alumno);
        }
        return null;
    }

    public Disciplinas getDisciplina() {
        return disciplina;
    }


}
