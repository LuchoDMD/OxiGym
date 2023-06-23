package clss.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import clss.people.Cliente;
import clss.use.Disciplinas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class UIClientes extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnAgregar;
    private JButton btnMostrar;
    private JButton btnEliminar;
    private JButton btnDisciplinas;
    private JComboBox<Disciplinas> opcionesDisciplinas;
    public UIClientes() {
        // Configurar ventana principal
        setTitle("Clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        btnDisciplinas= new JButton("Disciplinas");
        opcionesDisciplinas= new JComboBox<>(Disciplinas.values());

        // Configurar tabla
        tableModel = new DefaultTableModel(new Object[]{"Apellido", "Nombre" , "DNI" , "Disciplina"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Configurar panel de ingreso de Cliente
        JPanel panelIngreso = new JPanel(new FlowLayout());
        btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCliente();

            }
        });
        panelIngreso.add(btnAgregar);


        // Configurar panel de eliminación de Cliente
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
        add(panelEliminacion, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);

        generarTabla((List<Cliente>) GeneralUI.readListJson("clientes.json"));
    }

    private void agregarCliente() {
        JDialog dialog = new JDialog(this, "Agregar cliente", true);
        dialog.setLayout(new FlowLayout());
        Disciplinas disciplinas;

        JTextField nombre = new JTextField(10);
        JTextField apellido = new JTextField(10);
        JTextField dni = new JTextField(10);
        JTextField telefono = new JTextField(10);
        JTextField disciplina = new JTextField(10);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreTexto = nombre.getText();
                String apellidoTexto = apellido.getText();
                String dniTexto = dni.getText();
                String telefonoTexto = telefono.getText();
                Disciplinas disciplina = Disciplinas.seleccionarDisciplina();
            // String seniaTexto = txtSenia.getText();

                if (nombreTexto.isEmpty() || apellidoTexto.isEmpty() || dniTexto.isEmpty() || disciplina == null) {
                    JOptionPane.showMessageDialog(dialog, "Por favor, complete todos los campos.");
                    return;
                }

                int dniInt = Integer.parseInt(dniTexto);
                Cliente cliente = new Cliente(nombreTexto, apellidoTexto, dniInt, telefonoTexto, disciplina);
                Cliente.addCliente(cliente);
                mostrarListaClientesTabla((List<Cliente>) GeneralUI.readListJson("clientes.json"));

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

                // elimina el cliente
                String dniTexto = dni.getText();
                int dniInt = Integer.parseInt(dniTexto);
                if (!Cliente.eliminarClientePorDni(dniInt) && !dniTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "No se encontró el cliente.");
                    return;
                }

                // actualiza la tabla
                tableModel.setRowCount(0);
                generarTabla((List<Cliente>) GeneralUI.readListJson("clientes.json"));
                dialog.dispose();
            }
        });
        
        dialog.add(new JLabel("DNI"));
        dialog.add(dni);
        dialog.add(btnAceptar);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    public static boolean mostrarPanelVerificacion(String texto) {
        int opcion = JOptionPane.showOptionDialog(null, texto, "Verificación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        return opcion == JOptionPane.YES_OPTION;
    }

    private void mostrarListaClientesTabla(List<Cliente> list){
        tableModel.setRowCount(0);
        for(Cliente e: list){
            mostrarEnTablaClientes(e);
        }
    }
    private void mostrarEnTablaClientes(Cliente cliente){
        tableModel.addRow(new Object[]{cliente.getApellido(), cliente.getNombre(),cliente.getDni(),cliente.getDisciplina()});
    }
}