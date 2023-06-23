package clss.use;

import clss.UI.RangoHorario;

import javax.swing.*;
import java.util.Locale;

public enum Disciplinas
{
    MAQUINA("Maquina"),
    NATACIÒN("Natacion"),
    FUTBOL("Futbol5"),
    VOLEY("Voley"),
    BASQUET("Basquet"),
    TAE_KWON_DO("Tae-Kwon-Do"),
    JUDO("Judo"),
    GIMNASIA("Gimnasia"),
    DANZA("Danza");

    public String abrev;

    Disciplinas(String abrev)
    {
        this.abrev=abrev.toUpperCase(Locale.ROOT).substring(0);
    }
    public String getAbrev()
    {
        return abrev;
    }

    public static Disciplinas seleccionarDisciplina() {
        JComboBox<Disciplinas> comboBox = new JComboBox<>(Disciplinas.values());
        comboBox.setBounds(50, 50, 200, 30);

        JOptionPane.showMessageDialog(null, comboBox, "SELECCIONAR DISCIPLINA", JOptionPane.QUESTION_MESSAGE);

        Disciplinas opcionSeleccionada = (Disciplinas) comboBox.getSelectedItem();
        if (opcionSeleccionada != null) {
            return opcionSeleccionada;
        } else {
            return null;
        }
    }
}
