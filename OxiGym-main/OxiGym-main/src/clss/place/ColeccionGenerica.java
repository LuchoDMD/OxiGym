package clss.place;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ColeccionGenerica<T> implements Serializable,Iterable<T> {
    private List<T> elementos;

    public ColeccionGenerica(){
        this.elementos= new ArrayList<>();
    }

    public void agregar(T elemento){
        elementos.add(elemento);
    }
    public void eliminar(T elemento){
        elementos.remove(elemento);
    }
    public void listar(){
        for(T elemento : elementos){
            System.out.println(elemento.toString());
        }
    }
    @Override
    public Iterator<T> iterator() {
        return elementos.iterator();
    }
}
