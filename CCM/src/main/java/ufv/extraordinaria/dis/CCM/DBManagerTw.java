package ufv.extraordinaria.dis.CCM;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;

public class DBManagerTw {
    private final String db_path = "resources/twitter.json"; //Path de la base de datos
    private static final Gson gsonDate = new GsonBuilder()
            .setDateFormat("yyyy/MM/dd").create(); // le paso el formato de la fecha, para que lo lea bien y saque los datos

    public DBTwitter readDB() { // clase para leer la base de datos, que viene dada por un json
        try { // abrimos el archivo
            JsonReader reader = new JsonReader(new FileReader(db_path)); // creamos un lector de json
            return gsonDate.fromJson(reader, DBTwitter.class);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage()); // si no encuentra el archivo, imprime el error
        } catch (IOException ex) {
            System.out.println(ex.getMessage()); // si hay algun error de entrada/salida, imprime el error
        }
        return null;
    }

    public void saveDB(DBTwitter db2save) { //clase para guardar la base de datos, ya que vamos a ir actualizando datos, poder guardarlos
        try (Writer writer = new FileWriter(db_path)) { // abrimos el archivo
            Gson gson2 = new GsonBuilder().setDateFormat("yyyy/MM/dd").create(); // creamos un gson
            gson2.toJson(db2save, writer); // le pasamos la base de datos y el writer
        } catch (IOException e) {
            e.printStackTrace(); // si hay algun error de entrada/salida, imprime el error
        }
    }
}


}


