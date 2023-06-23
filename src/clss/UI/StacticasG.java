package clss.UI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;
import java.util.List;

public class  StacticasG{
    public StacticasG(){};
    public static String seleccionarBoxListaStringTexto(List<String> a,String texto) {
        JComboBox<String> comboBox = new JComboBox<>(a.toArray(new String[0]));
        comboBox.setBounds(50, 50, 200, 30);

        JOptionPane.showMessageDialog(null, comboBox, texto, JOptionPane.QUESTION_MESSAGE);

        String opcionSeleccionada = (String) comboBox.getSelectedItem();
        if (opcionSeleccionada != null) {
            return opcionSeleccionada.toString();
        } else {
            return null;
        }
    }

    /*public static String seleccionarBoxListaString(List<String> a, String texto) {
        JTextField textField = new JTextField();
        textField.setBounds(50, 50, 200, 30);

        // Crear un JComboBox para mostrar las opciones
        JComboBox<String> comboBox = new JComboBox<>(a.toArray(new String[0]));
        comboBox.setEditable(true);

        // Agregar un DocumentListener al JTextField para filtrar las opciones mientras se escribe
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateOptions();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateOptions();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateOptions();
            }

            private void updateOptions() {
                String searchText = textField.getText().toLowerCase();
                DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

                for (String opcion : a) {
                    if (opcion.toLowerCase().startsWith(searchText)) {
                        model.addElement(opcion);
                    }
                }

                comboBox.setModel(model);
                comboBox.setSelectedIndex(-1);
            }
        });

        // Mostrar un JOptionPane personalizado con el JComboBox
        JOptionPane.showMessageDialog(null, comboBox, texto, JOptionPane.QUESTION_MESSAGE);

        Object opcionSeleccionada = comboBox.getSelectedItem();
        if (opcionSeleccionada != null) {
            return opcionSeleccionada.toString();
        } else {
            return null;
        }
    }*/

    public static List<String> listaAListaString(List<?> a){
        List<String> listCliente=new ArrayList<>() ;
        for(Object e : a){
            listCliente.add((e).toString());
        }
        return listCliente;
    }
    public static boolean mostrarPanelVerificacion(String texto) {
        int opcion = JOptionPane.showOptionDialog(null, texto, "Verificación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        return opcion == JOptionPane.YES_OPTION;
    }
}
