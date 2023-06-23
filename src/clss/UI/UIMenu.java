package clss.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIMenu extends JFrame {
    private JButton opcion1;
    private JButton opcion2;
    private JButton opcion3;
    private JButton opcion4;
    private JButton opcion5;

    public UIMenu(){
        setTitle("OXiGym");
        setSize(300,150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel jPanel= new JPanel();
        opcion1 = new JButton("Clientes");
        opcion2 = new JButton("Empleados");
        opcion3 = new JButton("Turnos");

        opcion1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIClientes clientes= new UIClientes();
                clientes.setVisible(true);
            }
        });
        opcion2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsuariosUI usuariosUI = new UsuariosUI();
                usuariosUI.setVisible(true);
            }
        });
        opcion3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        CalendarioGUI gui = new CalendarioGUI();
                        gui.setVisible(true);
                    }
                });

            }
        });

        jPanel.add(opcion1);
        jPanel.add(opcion2);
        jPanel.add(opcion3);
        add(jPanel);
    }
}
