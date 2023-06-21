package clss.use;

import java.util.Locale;

public enum Membresias
{
    NORMAL("N"),
    GOLD("G"),
    PREMIUM("P");

    public String abrev;

    Membresias(String abrev){
        this.abrev=abrev.toUpperCase(Locale.ROOT).substring(0);
    }

    public String getAbrev()
    {
        return abrev;
    }
}
