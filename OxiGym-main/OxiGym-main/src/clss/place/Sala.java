package clss.place;

import java.util.ArrayList;
import java.util.List;

// TODO: agregar disponibilidad de horarios | lista de turnos
public class Sala implements java.io.Serializable
{
    private int id; // numero de sala
    private TipoSalas tipo;
    private List<String> disciplinas;


    public Sala() {
        this.disciplinas = new ArrayList<>();
    }

    public Sala(int id, TipoSalas tipo) {
        this.id = id;
        this.tipo = tipo;
        this.disciplinas = new ArrayList<>();
    }

    public void addDisciplina(String disciplina) {
        disciplinas.add(disciplina);
    }

    public void removeDisciplina(String disciplina) {
        disciplinas.remove(disciplina);
    }

    public TipoSalas getTipo() {
        return tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void getTipo(TipoSalas tipo) {
        this.tipo = tipo;
    }

    public void setTipo(TipoSalas tipo) {
        this.tipo = tipo;
    }

    public void setId(TipoSalas tipo) {
        this.tipo = tipo;
    }

    public List<String> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<String> disciplinas) {
        this.disciplinas = disciplinas;
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
