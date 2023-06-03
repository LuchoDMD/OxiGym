package clss.use;

public class Telefono
{
    private char tipo; //FIJO(f/F) - CELULAR(c/C)
    private short cod_Pais,cod_Area,num;

    /** BUILDERS **/
    public Telefono()
    {

    }
    public Telefono(char tipo, short cod_Pais, short cod_Area, short num)
    {
        this.tipo = tipo;
        this.cod_Pais = cod_Pais;
        this.cod_Area = cod_Area;
        this.num = num;
    }
    /** GETTERS & SETTERS **/
    public short getCod_Pais() {
        return cod_Pais;
    }
    public void setCod_Pais(short cod_Pais) {
        this.cod_Pais = cod_Pais;
    }
    public short getCod_Area() {
        return cod_Area;
    }
    public void setCod_Area(short cod_Area) {
        this.cod_Area = cod_Area;
    }
    public short getNum() {
        return num;
    }
    public void setNum(short num) {
        this.num = num;
    }

    /** OTHERS **/
    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (!(o instanceof Telefono)) return false;
        Telefono t = (Telefono)o;
        return (t.tipo==tipo)&&(t.cod_Pais == cod_Pais)&&(t.cod_Area==cod_Area)&&(t.num==num);
    }
    @Override
    public int hashCode()
    {
        int r= Character.hashCode(tipo);
        r+= 73*Short.hashCode(cod_Pais);
        r+= 73*Short.hashCode(cod_Area);
        r+= 73*Short.hashCode(num);
        return r;
    }
    @Override
    public String toString()
    {
        return "("+tipo+"): +"+cod_Pais+"-"+cod_Area+"-"+"-"+num;
    }
}
