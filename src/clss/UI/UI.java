package clss.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import clss.people.Cliente;
import clss.use.Membresias;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;




public class UI extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnAgregar;
    private JButton btnMostrar;
    private JButton btnEliminar;

    public UI() {
        // Configurar ventana principal
        setTitle("Clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Configurar tabla
        tableModel = new DefaultTableModel(new Object[]{"Fecha", "Nombre" , "DNI" , "Membresía"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Configurar panel de ingreso de turno
        JPanel panelIngreso = new JPanel(new FlowLayout());
        btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCliente();

            }
        });
        panelIngreso.add(btnAgregar);

        // Configurar panel de visualización de turnos
        JPanel panelVisualizacion = new JPanel(new FlowLayout());
        btnMostrar = new JButton("Mostrar Turnos");
        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // mostrarTurnos();
            }
        });
        panelVisualizacion.add(btnMostrar);

        // Configurar panel de eliminación de turno
        JPanel panelEliminacion = new JPanel(new FlowLayout());
        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCliente();
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

        generarTabla((List<Cliente>) GeneralUI.readListJson("clientes.json"));
    }

    private void agregarCliente() {
        JDialog dialog = new JDialog(this, "Agregar cliente", true);
        dialog.setLayout(new FlowLayout());

        JTextField nombre = new JTextField(10);
        JTextField apellido = new JTextField(10);
        JTextField dni = new JTextField(10);
        JTextField membresia = new JTextField(10);
        JTextField telefono = new JTextField(10);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreTexto = nombre.getText();
                String apellidoTexto = apellido.getText();
                String dniTexto = dni.getText();
                String telefonoTexto = telefono.getText();
                String membresiaTexto = membresia.getText();
            // String seniaTexto = txtSenia.getText();

                if (nombreTexto.isEmpty() || apellidoTexto.isEmpty() || dniTexto.isEmpty() || membresiaTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Por favor, complete todos los campos.");
                    return;
                }

                Membresias m = Membresias.valueOf(membresiaTexto);
                int dniInt = Integer.parseInt(dniTexto);
                int telefonoInt = Integer.parseInt(telefonoTexto);
                Cliente cliente = new Cliente(nombreTexto, apellidoTexto, dniInt, telefonoInt, m, 0);
                GeneralUI.addCliente(cliente);

                dialog.dispose();
            }
        });

        dialog.add(new JLabel("Nombre"));
        dialog.add(nombre);
        dialog.add(new JLabel("Apellido"));
        dialog.add(apellido);
        dialog.add(new JLabel("DNI"));
        dialog.add(dni);
        dialog.add(new JLabel("Telefono"));
        dialog.add(telefono);
        dialog.add(new JLabel("Membresia"));
        dialog.add(membresia);

        dialog.add(btnAceptar);
        
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void generarTabla(List<Cliente> clientes) {
        tableModel.setRowCount(0);
        if (clientes == null)
            return;
        for (Cliente c: clientes) {
            tableModel.addRow(c.rowForTable());
        }
    }

    private void eliminarCliente() {
        JDialog dialog = new JDialog(this, "Eliminar cliente", true);
        dialog.setLayout(new FlowLayout());

        JTextField dni = new JTextField(10);
        
        JButton btnAceptar = new JButton("Aceptar");
        
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dniTexto = dni.getText();
                int dniInt = Integer.parseInt(dniTexto);
                Cliente c = Cliente.getClientePorDni(dniInt);
                

                dialog.add(btnAceptar);
                Cliente.eliminarClientePorDni(dniInt);
                
            }
        });
        dialog.add(new JLabel("Cliente"));
        dialog.add(new JTextField());
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    public static boolean mostrarPanelVerificacion(String texto) {
        int opcion = JOptionPane.showOptionDialog(null, texto, "Verificación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        return opcion == JOptionPane.YES_OPTION;
    }
}

