package clss.UI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import clss.place.Sala;
import clss.place.TipoSalas;

public class AdminUI implements java.io.Serializable {

    private String usuario;

    public AdminUI() {
        this.usuario="admin";
    }

    public AdminUI(String usuario) {
        this.usuario=usuario;
    }

    // devuelve un objeto Sala con los datos ingresados por el usuario
    private Sala getSala() {
        // inicializa los atributos de la sala
        TipoSalas tipo;

        System.out.println("Seleccione el tipo de sala: ");

        // tipos de salas disponibles en el archivo enum TipoSalas
        System.out.println("1. SUM");
        System.out.println("2. GIMNASIO");
        System.out.println("3. PISCINA");
        System.out.println("4. CANCHA");

        // escanea entrada de usuario
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();

        // asigna el tipo de sala segun la opcion elegida con un while para evitar respuestas incorrectas
        while (opcion < 1 || opcion > TipoSalas.values().length)
        {
            System.out.println("Opcion incorrecta, ingrese nuevamente: ");
            opcion = sc.nextInt();
        }
        sc.close();

        switch (opcion)
        {
            case 1:
                tipo = TipoSalas.SUM;
                break;
            case 2:
                tipo = TipoSalas.GIMNASIO;
                break;
            case 3:
                tipo = TipoSalas.PISCINA;
                break;
            case 4:
                tipo = TipoSalas.CANCHA;
                break;
            default:
                tipo = TipoSalas.SUM;
                break;
        }
        
        // genera el id de la sala
        int id = 0;

        // se crea el objeto
        return new Sala(id, tipo);
    }

    // devuelve la cantidad de salas de un tipo especifico en el archivo salas.json
    public int getSalas(List<Sala> salas, TipoSalas tipo)
    {
        int cant = 0;
        for (Sala s: salas) {
            if (s != null) 
            {
                if (s.getTipo() == tipo)
                {
                    cant++;
                }
            }
        }
        return cant;
    }

    // escribe las listas de salas en el archivo salas.json
    private void listToSalasJson(List<Sala> salas) {
        File file = new File("salas.json");
        file.setWritable(true);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // le da una estructura mas legible

        try {
            mapper.writeValue(file, salas);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file.setWritable(false);
        }
    }

    // devuelve una lista de las salas del archivo salas.json, devuelve null si el archivo no existe
    private List<Sala> readSalasJson() {
        File file = new File("salas.json");
        file.setReadable(true);

        if (!file.exists())
            return null;

        ObjectMapper mapper = new ObjectMapper();
        
        List<Sala> salas = null;
        try {
            salas = mapper.readValue(file, new TypeReference<List<Sala>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return salas;
    }

    // agrega salas al archivo salas.json
    public void addSala(Sala s) {
        // lee el archivo salas.json
        List<Sala> salas = readSalasJson();
        // si el archivo no existe, crea una lista vacia
        if (salas == null)
            salas = new ArrayList<Sala>();
        // agrega la sala a la lista y le asigna un id único
        s.setId(salas.size() + 1);
        salas.add(s);
        // escribe la lista en el archivo salas.json
        listToSalasJson(salas);
    }

    
    
}
