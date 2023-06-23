package clss.people;

import java.util.ArrayList;
import java.util.List;

import clss.UI.GeneralUI;

public class Usuario extends Empleado {
    private String user;
    private String pass;
    private boolean admin;

    public Usuario() { }

    public Usuario(String apellido, String nombre, int dni, String telefono, int sueldoMin, String user, String pass) {
        super(apellido,nombre,dni,telefono,sueldoMin);
        this.user= user;
        this.pass= pass;
    }

    public Usuario(String apellido, String nombre, int dni, String telefono) {
        super(apellido,nombre,dni,telefono, 0);
    } 

    public boolean getAdmin(){return admin;}
    public String getPass() {return pass;}
    public String getUser() {return user;}
    public void setPass(String pass) {this.pass = pass;}
    public void setUser(String user) {this.user = user;}
    public void setAdmin(boolean admin) {this.admin = admin;}
    

    public static void addUsuario(Usuario usuario) {
        List<Usuario> usuarios = (List<Usuario>) GeneralUI.readListJson("usuarios.json");

        if (usuarios == null) {
            usuarios = new ArrayList<Usuario>();
        }
        usuarios.add(usuario);
        GeneralUI.listToJson(usuarios, "usuarios.json");
    }

    public static boolean eliminarUsuarioPorDni(int dni) {
        List<Usuario> usuarios = (List<Usuario>) GeneralUI.readListJson("usuarios.json");
        for (Usuario u: usuarios) {
            if (u.getDni() == dni) {
                usuarios.remove(u);
                GeneralUI.listToJson(usuarios, "usuarios.json");
                return true;
            }
        }
        return false;
    }

    public static Usuario getUsuarioPorDni(int dni) {
        List<Usuario> usuarios = (List<Usuario>) GeneralUI.readListJson("usuarios.json");
        for (Usuario u: usuarios) {
            if (u.getDni() == dni) {
                return u;
            }
        }
        return null;
    }

    public Object[] rowForTable() {
        Object[] row = {this.getNombre(), this.getApellido(), this.getDni(), this.getTelefono(), this.getSueldo(), this.getUser(), this.getPass()};
        return row;
    }

}
