package clss.place;

import java.util.ArrayList;
import java.util.List;

public class Sala
{
    private int id;
    private TipoSalas tipo;
    private List<String> disciplinas;

    public Sala(int id, TipoSalas tipo)
    {
        this.id=id;
        this.tipo=tipo;
        this.disciplinas= new ArrayList<>();
    }

    public void addDisciplina(String disciplina)
    {
        disciplinas.add(disciplina);
    }

    public void removeDisciplina(String disciplina)
    {
        disciplinas.remove(disciplina);
    }

    public int getId()
    {
        return id;
    }
    public TipoSalas getTipo()
    {
        return tipo;
    }
    public List<String> getDisciplinas()
    {
        return disciplinas;
    }

    @Override
    public String toString()
    {
        return "Sala{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", disciplinas=" + disciplinas +
                '}';
    }
}
