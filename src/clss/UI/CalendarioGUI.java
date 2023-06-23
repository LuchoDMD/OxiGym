package clss.UI;

import clss.people.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.List;

public class CalendarioGUI extends JFrame {
    private Map<LocalDate, List<Turno>> calendario;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnAgregar;
    private JButton btnMostrar;
    private JButton btnEliminar;

    public CalendarioGUI(/*List<T> clientes*/) {
        calendario = new TreeMap<>();
        leerEnArchivoTremap();
        // Configurar ventana principal
        setTitle("Calendario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Configurar tabla
        tableModel = new DefaultTableModel(new Object[]{"Fecha", "Nombre" , "Para" , "Horario"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Configurar panel de ingreso de turno
        JPanel panelIngreso = new JPanel(new FlowLayout());
        btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarTurno();

            }
        });
        panelIngreso.add(btnAgregar);

        // Configurar panel de visualización de turnos
        JPanel panelVisualizacion = new JPanel(new FlowLayout());
        btnMostrar = new JButton("Mostrar Turnos");
        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTurnos();

            }
        });
        panelVisualizacion.add(btnMostrar);

        // Configurar panel de eliminación de turno
        JPanel panelEliminacion = new JPanel(new FlowLayout());
        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarTurno();

            }
        });
        panelEliminacion.add(btnEliminar);

        // Agregar componentes a la ventana
        add(scrollPane, BorderLayout.CENTER);
        add(panelIngreso, BorderLayout.NORTH);
        add(panelVisualizacion, BorderLayout.SOUTH);
        add(panelEliminacion, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
        mostrarTreeMapEnTabla();
    }

    private void agregarTurno() {
        JDialog dialog = new JDialog(this, "Agregar clss.UI.Turno", true);
        dialog.setLayout(new FlowLayout());

        JTextField txtFecha = new JTextField(10);
        JTextField txtPara = new JTextField(10);
        //JTextField txtSenia = new JTextField(5);
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fechaTexto = txtFecha.getText();
                //String quienTexto = txtQuien.getText();
                //List<Cliente> clientes = (List<Cliente>) GeneralUI.readListJson("clientes.json");
                String quienTexto = StacticasG.seleccionarBoxListaStringTexto(StacticasG.listaAListaString(new ArrayList<>((List<Cliente>) GeneralUI.readListJson("clientes.json"))),"Selector por dni");
                String paraTexto = txtPara.getText();
               // String seniaTexto = txtSenia.getText();

                if (fechaTexto.isEmpty() || quienTexto.isEmpty() || paraTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Por favor, complete todos los campos.");
                    return;
                }

                try {
                    LocalDate fecha = LocalDate.parse(fechaTexto, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    String horarioTexto;
                    if (!calendario.containsKey(fecha)) {
                        calendario.put(fecha, new ArrayList<>());
                        horarioTexto = RangoHorario.seleccionarRangoHorario();
                    }else{
                        horarioTexto = RangoHorario.seleccionarRangoHorarioDepurado(convertirAlistaHorariosTurnos(calendario.get(fecha)));
                    }
                    if(StacticasG.mostrarPanelVerificacion("¿Agendar turno?")){
                        Turno turno = new Turno(quienTexto,paraTexto, fecha,horarioTexto);
                        calendario.get(fecha).add(turno);
                        mostrarTreeMapEnTabla();
                        escribiEnArchivoTremap();
                        JOptionPane.showMessageDialog(dialog, "clss.UI.Turno guardar exitosamente.");
                    }

                    dialog.dispose();
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(dialog, "Fecha inválida. Por favor, ingrese la fecha en formato dd/MM/yyyy.");
                } /*catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Hora inválida. Por favor, ingrese un valor numérico para la hora.");
                }*/

            }
        });

        dialog.add(new JLabel("Fecha (dd/MM/yyyy):"));
        dialog.add(txtFecha);
        dialog.add(new JLabel("Para:"));
        dialog.add(txtPara);
        /*dialog.add(new JLabel("Señia"));
        dialog.add(txtSenia);*/
        dialog.add(btnAceptar);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    private void mostrarTreeMapEnTabla(){
        tableModel.setRowCount(0);
        Iterator<LocalDate> iterator = calendario.keySet().iterator();
        while (iterator.hasNext()) {
            LocalDate key = iterator.next();
            mostrarListaTurnoTabla(calendario.get(key));
        }
    }
    private void mostrarListaTurnoTabla(List<Turno> list){
        for(Turno e: list){
            mostrarEnTablaTurno(e);
        }
    }
    private void mostrarEnTablaTurno(Turno turno){
        tableModel.addRow(new Object[]{turno.getDia(), turno.getQuien(),turno.getPara(),turno.getHorario(),turno.getSenia() });
    }
    private void mostrarTurnos() {
        JDialog dialog = new JDialog(this, "Mostrar Turnos", true);
        dialog.setLayout(new FlowLayout());

        JTextField txtFecha = new JTextField(10);
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fechaTexto = txtFecha.getText();

                try {
                    LocalDate fecha = LocalDate.parse(fechaTexto, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    if (calendario.containsKey(fecha)) {
                        List<Turno> turnos = calendario.get(fecha);
                        tableModel.setRowCount(0);
                        mostrarListaTurnoTabla(turnos);

                    } else {
                        JOptionPane.showMessageDialog(dialog, "No hay turnos programados para la fecha " + fechaTexto);
                    }

                    dialog.dispose();
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(dialog, "Fecha inválida. Por favor, ingrese la fecha en formato dd/MM/yyyy.");
                }

            }
        });

        dialog.add(new JLabel("Fecha (dd/MM/yyyy):"));
        dialog.add(txtFecha);
        dialog.add(btnAceptar);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void eliminarTurno() {
        JDialog dialog = new JDialog(this, "Eliminar clss.UI.Turno", true);
        dialog.setLayout(new FlowLayout());

        JTextField txtFecha = new JTextField(10);
        JTextField txtQuien = new JTextField(10);
        JTextField txtPara = new JTextField(10);
        //JTextField txtSenia = new JTextField(5);
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fechaTexto = txtFecha.getText();
                String quienTexto = txtQuien.getText();
                String paraTexto = txtPara.getText();
                String horarioTexto= RangoHorario.seleccionarRangoHorario();
                //int senia  = Integer.parseInt(txtSenia.getText());

                try {
                    LocalDate fecha = LocalDate.parse(fechaTexto, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    if (calendario.containsKey(fecha)) {
                        List<Turno> turnos = calendario.get(fecha);
                        Turno turnoBuscado = new Turno(quienTexto,paraTexto, fecha,horarioTexto);
                        if (turnos.contains(turnoBuscado)) {
                            if(StacticasG.mostrarPanelVerificacion("¿Eliminar turno?")){
                                turnos.remove(turnoBuscado);
                                int fila = buscarFilaTabla(fecha);
                                if(fila!=-1) {
                                    tableModel.removeRow(fila);
                                }
                                escribiEnArchivoTremap();
                                JOptionPane.showMessageDialog(dialog, "clss.UI.Turno eliminado exitosamente.");
                            }

                        } else {
                            JOptionPane.showMessageDialog(dialog, "No se encontró un turno con los datos especificados.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(dialog, "No hay turnos programados para la fecha " + fechaTexto);
                    }

                    dialog.dispose();
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(dialog, "Fecha inválida. Por favor, ingrese la fecha en formato dd/MM/yyyy.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Hora inválida. Por favor, ingrese un valor numérico para la hora.");
                }

            }
        });

        dialog.add(new JLabel("Fecha (dd/MM/yyyy):"));
        dialog.add(txtFecha);
        dialog.add(new JLabel("Quien:"));
        dialog.add(txtQuien);
        dialog.add(new JLabel("Para:"));
        dialog.add(txtPara);
        /*dialog.add(new JLabel("Señia"));
        dialog.add(txtSenia);*/
        dialog.add(btnAceptar);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private int buscarFilaTabla(LocalDate fecha) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).equals(fecha)) {
                return i;
            }
        }
        return -1;
    }

    private List<String> convertirAlistaHorariosTurnos(List<Turno> a){
        List<String> horarios = new ArrayList();
        for(Turno e : a){
            horarios.add(e.getHorario());
        }
        return horarios;
    }
    private void escribiEnArchivoTremap(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\EmA_9\\Desktop\\Programacion\\OxiGym-main (1)\\OxiGym-main\\src\\calendario.txt"))) {
            for (Map.Entry<LocalDate, List<Turno>> entry : calendario.entrySet()) {
                LocalDate fecha = entry.getKey();
                List<Turno> turnos = entry.getValue();
                writer.write(fecha.toString());
                writer.newLine();
                for (Turno turno : turnos) {
                    writer.write(turno.toString());
                    writer.newLine();
                }
            }
            System.out.println("El TreeMap se ha guardado en el archivo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void leerEnArchivoTremap() {
        File esta = new File("C:\\Users\\54223\\Desktop\\OxiGym-main\\calendario.txt");
        if (esta.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\54223\\Desktop\\OxiGym-main\\calendario.txt"))) {
                String line;
                LocalDate fecha = null;
                List<Turno> turnos = null;

                while ((line = reader.readLine()) != null) {
                    if (!line.isEmpty()) {
                        if (fecha == null) {
                            fecha = LocalDate.parse(line);
                            turnos = new ArrayList<>();
                        } else {
                            Turno turno = Turno.fromString(line);
                            turnos.add(turno);
                        }
                    } else {
                        if (fecha != null && turnos != null) {
                            calendario.put(fecha, turnos);
                            fecha = null;
                            turnos = null;
                        }
                    }
                }
                if (fecha != null && turnos != null) {
                    calendario.put(fecha, turnos);
                }

                System.out.println("El TreeMap se ha cargado desde el archivo.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}

