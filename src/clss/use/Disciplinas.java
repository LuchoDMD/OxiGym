package clss.use;

import java.util.Locale;

public enum Disciplinas
{
    MAQUINA("M"),
    NATACIÒN("N"),
    FUTBOL("F"),
    VOLEY("V"),
    BASQUET("B"),
    TAE_KWON_DO("T"),
    JUDO("J"),
    GIMNASIA("G"),
    DANZA("D");

    public String abrev;

    Disciplinas(String abrev)
    {
        this.abrev=abrev.toUpperCase(Locale.ROOT).substring(0);
    }
    public String getAbrev()
    {
        return abrev;
    }
}
