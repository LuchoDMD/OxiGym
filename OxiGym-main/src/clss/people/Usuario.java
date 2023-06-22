package clss.people;

public class Usuario extends Empleado{
    private String user;
    private String pass;
    private boolean admin;

    public Usuario(String apellido, String nombre, int dni, String telefono, int sueldoMin, String user, String pass){
        super(apellido,nombre,dni,telefono,sueldoMin);
        this.user= user;
        this.pass= pass;
    }
    public boolean getAdmin(){return admin;}
    public String getPass() {return pass;}
    public String getUser() {return user;}
    public void setPass(String pass) {this.pass = pass;}
    public void setUser(String user) {this.user = user;}
    public void setAdmin(boolean admin) {this.admin = admin;}
}
