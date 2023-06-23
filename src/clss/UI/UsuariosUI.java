package clss.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import clss.people.Usuario;
import clss.use.Disciplinas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class UsuariosUI extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnAgregar;
    private JButton btnEliminar;


    public UsuariosUI() {
        // Configurar ventana principal
        setTitle("Usuarios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Configurar tabla
        tableModel = new DefaultTableModel(new Object[]{"Apellido", "Nombre" , "DNI" , "Teléfono"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Configurar panel de ingreso de Usuario
        JPanel panelIngreso = new JPanel(new FlowLayout());
        btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarUsuario();
            }
        });
        panelIngreso.add(btnAgregar);


        // Configurar panel de eliminación de Usuario
        JPanel panelEliminacion = new JPanel(new FlowLayout());
        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
            }
        });
        panelEliminacion.add(btnEliminar);

        // Agregar componentes a la ventana
        add(scrollPane, BorderLayout.CENTER);
        add(panelIngreso, BorderLayout.NORTH);
        add(panelEliminacion, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);

        generarTabla((List<Usuario>) GeneralUI.readListJson("usuarios.json"));
    }

    private void agregarUsuario() {
        JDialog dialog = new JDialog(this, "Agregar Usuario", true);
        dialog.setLayout(new FlowLayout());

        JTextField nombre = new JTextField(10);
        JTextField apellido = new JTextField(10);
        JTextField dni = new JTextField(10);
        JTextField telefono = new JTextField(10);
        JTextField sueldo = new JTextField(10);
        JTextField user = new JTextField(10);
        JTextField pass = new JTextField(10);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreTexto = nombre.getText();
                String apellidoTexto = apellido.getText();
                String dniTexto = dni.getText();
                String telefonoTexto = telefono.getText();
                String sueldoTexto = sueldo.getText();
                String userTexto = user.getText();
                String passTexto = pass.getText();


                if (nombreTexto.isEmpty() || apellidoTexto.isEmpty() || dniTexto.isEmpty() || telefonoTexto.isEmpty() 
                    || sueldoTexto.isEmpty() || userTexto.isEmpty() || passTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Por favor, complete todos los campos.");
                    return;
                }

                int dniInt = Integer.parseInt(dniTexto);
                int sueldoInt = Integer.parseInt(sueldoTexto);
                Usuario usuario = new Usuario(nombreTexto, apellidoTexto, dniInt, telefonoTexto, sueldoInt, userTexto, passTexto);
                Usuario.addUsuario(usuario);
                mostrarListaUsuariosTabla((List<Usuario>) GeneralUI.readListJson("usuarios.json"));

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
        dialog.add(new JLabel("Sueldo Minimo"));
        dialog.add(sueldo);
        dialog.add(new JLabel("Usuario"));
        dialog.add(user);
        dialog.add(new JLabel("Contraseña"));
        dialog.add(pass);

        dialog.add(btnAceptar);
        
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void generarTabla(List<Usuario> usuarios) {
        tableModel.setRowCount(0);
        if (usuarios == null)
            return;
        for (Usuario u: usuarios) {
            tableModel.addRow(u.rowForTable());
        }
    }

    private void eliminarUsuario() {
        JDialog dialog = new JDialog(this, "Eliminar usuario", true);
        dialog.setLayout(new FlowLayout());

        JTextField dni = new JTextField(10);
        
        JButton btnAceptar = new JButton("Aceptar");
        
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // elimina el Usuario
                String dniTexto = dni.getText();
                int dniInt = Integer.parseInt(dniTexto);
                if (!Usuario.eliminarUsuarioPorDni(dniInt) && !dniTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "No se encontró el usuario.");
                    return;
                }

                // actualiza la tabla
                tableModel.setRowCount(0);
                generarTabla((List<Usuario>) GeneralUI.readListJson("usuarios.json"));
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

    private void mostrarListaUsuariosTabla(List<Usuario> list){
        tableModel.setRowCount(0);
        for(Usuario e: list) {
            tableModel.addRow(e.rowForTable());
        }
    }
    private void mostrarEnTablaUsuarios(Usuario Usuario){
        tableModel.addRow(new Object[]{Usuario.getApellido(), Usuario.getNombre(),Usuario.getDni()});
    }
}

