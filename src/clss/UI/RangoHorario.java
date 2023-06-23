package clss.UI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public enum RangoHorario {
    H7_00("07:00", "08:30"),
    H8_30("08:30", "10:00"),
    H10_00("10:00", "11:30"),
    H11_30("11:30", "13:00"),
    H13_00("13:00", "14:30"),
    H14_30("14:30", "16:00"),
    H16_00("16:00", "17:30"),
    H17_30("17:30", "19:00"),
    H19_00("19:00", "20:30"),
    H20_30("20:30", "22:00"),
    H22_00("22:00", "23:30");

    private final String horaInicio;
    private final String horaFin;

    RangoHorario(String horaInicio, String horaFin) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }
    @Override
    public String toString() {
        return horaInicio + " - " + horaFin;
    }
    public static String seleccionarRangoHorario() {
        JComboBox<RangoHorario> comboBox = new JComboBox<>(RangoHorario.values());
        comboBox.setBounds(50, 50, 200, 30);

        JOptionPane.showMessageDialog(null, comboBox, "Seleccionar rango horario", JOptionPane.QUESTION_MESSAGE);

        RangoHorario opcionSeleccionada = (RangoHorario) comboBox.getSelectedItem();
        if (opcionSeleccionada != null) {
            return opcionSeleccionada.toString();
        } else {
            return null;
        }
    }
    public static String seleccionarRangoHorarioDepurado(List<String> a) {
        JComboBox<String> comboBox = new JComboBox<>(armaListaNoSeleccionados(a).toArray(new String[0]));
        comboBox.setBounds(50, 50, 200, 30);

        JOptionPane.showMessageDialog(null, comboBox, "Seleccionar rango horario", JOptionPane.QUESTION_MESSAGE);

        String opcionSeleccionada = (String) comboBox.getSelectedItem();
        if (opcionSeleccionada != null) {
            return opcionSeleccionada.toString();
        } else {
            return null;
        }
    }
    public static List<String> armaListaNoSeleccionados(List<String> a){
        List<String> listaHorarios=new ArrayList<>() ;
        for(RangoHorario elemnto : RangoHorario.values()){
            listaHorarios.add(elemnto.toString());
        }
        List<String> horariosIterable = new ArrayList<>(listaHorarios);
        for(String elemnto: horariosIterable){
            for (String horario : a){
                if(elemnto.equals(horario)){
                   listaHorarios.remove(horario);
                }
            }
        }
        return listaHorarios;
    }
}

