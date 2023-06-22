package clss.place;

import java.util.ArrayList;
import java.util.List;

import clss.UI.GeneralUI;

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

    // agrega salas al archivo salas.json
    public static void addSala(Sala s) {
        // lee el archivo salas.json
        List<Sala> salas = (List<Sala>) GeneralUI.readListJson("salas.json");
        // si el archivo no existe, crea una lista vacia
        if (salas == null)
            salas = new ArrayList<Sala>();
        // agrega la sala a la lista y le asigna un id único
        s.setId(salas.size() + 1); // TODO: modificar generador id
        salas.add(s);
        // escribe la lista en el archivo salas.json
        GeneralUI.listToJson(salas, "salas.json");
    }

    // elimina una sala del archivo salas.json
    public static void eliminarSala(Sala s) {
        // lee el archivo salas.json
        List<Sala> salas = (List<Sala>) GeneralUI.readListJson("salas.json");
        // elimina la sala de la lista
        if (salas == null) {
            return;
        }
        if (salas.contains(s)) {
            salas.remove(s);
            // escribe la lista en el archivo salas.json
            GeneralUI.listToJson(salas, "salas.json");
        }
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
