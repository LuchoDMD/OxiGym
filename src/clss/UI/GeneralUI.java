package clss.UI;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import clss.people.Cliente;
import clss.people.Empleado;
import clss.people.Usuario;
import clss.place.Sala;

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
            } else if (filename.equals("usuarios.json")) {
                list = mapper.readValue(file, new TypeReference<List<Usuario>>() {});
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
    
}
