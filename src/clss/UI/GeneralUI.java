package clss.UI;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import clss.people.Cliente;
import clss.place.Sala;
import clss.use.Membresias;

public class GeneralUI {
    
    public static List<?> readListJson(String filename) {
        File file = new File(filename);
        file.setReadable(true);

        // devuelve null si el archivo no existe
        if (!file.exists())
            return null;

        ObjectMapper mapper = new ObjectMapper();

        List<?> list = null;

        try {
            if (filename.equals("clientes.json")) {
                list = mapper.readValue(file, new TypeReference<List<Cliente>>() {});
            } else if (filename.equals("salas.json")) {
                list = mapper.readValue(file, new TypeReference<List<Sala>>() {});
            } else {
                System.out.println("Error: filename no valido");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static void listToJson(List<?> list, String filename) {
        File file = new File(filename);
        file.setWritable(true);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // le da una estructura mas legible al archivo

        try {
            mapper.writeValue(file, list);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file.setWritable(false);
        }
    }

    public static void addCliente(Cliente c) {
        List<Cliente> clientes = (List<Cliente>) readListJson("clientes.json");
        // si el archivo no existe crea una lista vacia
        if (clientes == null) 
            clientes = new ArrayList<Cliente>();
        clientes.add(c);
        listToJson(clientes, "clientes.json");
    }
    
}
