package clss.use;

import java.util.Locale;

public enum Dias
{
    LUNES("LUN"),
    MARTES("MAR"),
    MIERCOLES("MIE"),
    JUEVES("JUE"),
    VIERNES("VIE"),
    SABADO("SAB"),
    DOMINGO("DOM");

    public String abrev;
    Dias(String abrev)
    {
        this.abrev=abrev.toUpperCase(Locale.ROOT).substring(0,2);
    }
}
