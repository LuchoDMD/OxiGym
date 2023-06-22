package clss.use;

import java.util.Locale;

public enum Disciplinas
{
    MAQUINA("Maquina"),
    NATACIÒN("Natacion"),
    FUTBOL("Futbol5"),
    VOLEY("Voley"),
    BASQUET("Basquet"),
    TAE_KWON_DO("Tae-Kwon-Do"),
    JUDO("Judo"),
    GIMNASIA("Gimnasia"),
    DANZA("Danza");

    public String abrev;

    Disciplinas(String abrev)
    {
        this.abrev=abrev.toUpperCase(Locale.ROOT).substring(0);
    }
    public static Disciplinas stringtoEnum(String res){
        return Disciplinas.valueOf(res);
    }
    public String getAbrev()
    {
        return abrev;
    }
}
