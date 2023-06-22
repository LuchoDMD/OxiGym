package clss.UI;

public class AdminUI extends GeneralUI implements java.io.Serializable {

    private String usuario;

    public AdminUI() {
        this.usuario="admin";
    }

    public AdminUI(String usuario) {
        this.usuario=usuario;
    }

}
