package clss.UI;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

class Turno<T> {
    private T para;
    private T quien;
    //@JsonFormat(pattern = "dd-MM-yyyy",shape = JsonFormat.Shape.STRING)
    private LocalDate dia;
    private String horario;
    private int senia;

    public Turno(){};
    public Turno(T quien, T para, LocalDate dia,String horario) {
        this.para = para;
        this.quien = quien;
        this.dia = dia;
        this.horario=horario;
        this.senia=0;
    }
    public void setPara(T para) {
        this.para = para;
    }
    public void setQuien(T quien) {
        this.quien = quien;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setSenia(int senia) {
        this.senia = senia;
    }
    public int getSenia() {
        return senia;
    }

    public T getQuien() {
        return quien;
    }
    public T getPara() {
        return para;
    }

    public LocalDate getDia() {
        return dia;
    }

    @Override
    public String toString() {
        return quien + "," + para + "," + dia.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "," + horario + "," + senia;
    }

    public static Turno fromString(String turnoString) {
        String[] partes = turnoString.split(",");
        String quien = partes[0];
        String para = partes[1];
        String fecha = partes[2];
        System.out.println(fecha);
        LocalDate dia = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String horario = (partes[3]);

        return new Turno(quien, para, dia,horario);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turno<?> turno = (Turno<?>) o;
        return senia == turno.senia && Objects.equals(para, turno.para) && Objects.equals(quien, turno.quien) && Objects.equals(dia, turno.dia) && Objects.equals(horario, turno.horario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(para, quien, dia, horario, senia);
    }
}