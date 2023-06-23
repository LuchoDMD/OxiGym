package clss.UI;
import clss.people.Instructor;
import clss.people.Usuario;
import clss.people.Empleado;
import clss.place.ColeccionGenerica;
import clss.use.Disciplinas;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class UILogin extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton createButton;
    private ColeccionGenerica<Empleado> usuarios;

    public UILogin() {
        usuarios= new ColeccionGenerica<>();
        ArrayList<Usuario> fileUsuarios = (ArrayList<Usuario>) GeneralUI.readListJson("usuarios.json");
        for (Usuario u: fileUsuarios) {
            usuarios.agregar(u);
        }
        
        Disciplinas disc1= Disciplinas.BASQUET;

        Instructor instructor= new Instructor("Instuctor","A",56468,"46549874",25000,disc1);

        usuarios.agregar(instructor);
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel usernameLabel = new JLabel("Usuario");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Contraseña");
        passwordField = new JPasswordField();

        loginButton = new JButton("Iniciar sesion");
        createButton= new JButton("Crear usuario");
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);
        panel.add(new JLabel());
        panel.add(createButton);
        add(panel);


        //click sobre iniciar sesion
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (verificar(username, password)) {
                    JOptionPane.showMessageDialog(UILogin.this, "Inicio de sesion exitoso");
                    dispose();
                    UIMenu menu= new UIMenu();
                    menu.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(UILogin.this, "Usuario o Contraseña erroneas");
                }
            }
        });

        //click sobre crear usuario
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String username= JOptionPane.showInputDialog(UILogin.this,"Ingrese el nombre de usuario");
               String password= JOptionPane.showInputDialog(UILogin.this,"Ingrese la contraseña");
                String nombre= JOptionPane.showInputDialog(UILogin.this,"Ingrese el nombre");
                String apellido= JOptionPane.showInputDialog(UILogin.this,"Ingrese el apellido");
                String telefono= JOptionPane.showInputDialog(UILogin.this,"Ingrese el telefono: ");
                int dni;
                int sueldo;
                try{
                     dni= Integer.parseInt(JOptionPane.showInputDialog(UILogin.this,"Ingrese el dni"));
                     sueldo= Integer.parseInt(JOptionPane.showInputDialog(UILogin.this,"Ingrese el sueldo"));
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(UILogin.this,"Error: ingrese valores validos para DNI o sueldo");
                    return;
                }

               if(username != null && password != null){
                   usuarios.agregar(new Usuario(apellido,nombre,dni,telefono,sueldo,username,password));
                   JOptionPane.showMessageDialog(UILogin.this,"Usuario creado exitosamente");
               }
            }
        });
    }
    public boolean verificar(String username, String password){
        for(Empleado empleado : usuarios){
            if(empleado instanceof Usuario){
                Usuario tempUser= (Usuario) empleado;
                if(tempUser.getUser().equals(username) && tempUser.getPass().equals(password))
                    return true;
            }
        }
        return false;
    }

}
