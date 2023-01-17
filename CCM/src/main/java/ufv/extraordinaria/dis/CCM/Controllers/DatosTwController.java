package ufv.extraordinaria.dis.CCM.Controllers;

import org.springframework.web.bind.annotation.*;
import ufv.extraordinaria.dis.CCM.Clases.DatosTweets;
import ufv.extraordinaria.dis.CCM.DBManagerTw;
import ufv.extraordinaria.dis.CCM.DBTwitter;

import java.util.ArrayList;

@RestController
public class DatosTwController {
    private final String path = "/twitter"; // ruta para acceder a los datos de las zonas básicas de salud mayores de 60 años

    private DBTwitter db = new DBTwitter(); // creo un objeto de la clase DB60

    // método para leer los datos de las zonas básicas de salud mayores de 60 años de la base de datos y devolverlos al Frontend
    @GetMapping(path)
    // método para leer los datos de las zonas básicas de salud mayores de 60 años de la base de datos y devolverlos al Frontend
    public ArrayList<DatosTweets> readDatos() { // función que devuelve los datos de las zonas básicas de salud mayores de 60 años
        DBManagerTw dbMgr = new DBManagerTw(); // creo un objeto de la clase DBManager60
        db = dbMgr.readDB(); // leo la base de datos
        return db.getDatos(); // devuelvo los datos
    }

    @PostMapping(path)
    public String crearZona(@RequestBody DatosTweets dz) {
        DBManagerTw dbmgr = new DBManagerTw(); //  creo un objeto de la clase DBManger
        db = dbmgr.readDB(); // leo la base de datos
        try {
            for (int i = 0; i < db.getDatos().size(); i++) { // recorro la lista de tweets
                if (db.getDatos().get(i).getId().equals(dz.getId())) { // si el id ya existe en la base de datos
                    return "Error! \n El id ya exisite.";
                }
            }
        } catch (Exception exception) {
        }

        String codigo = db.getDatos().get(db.getDatos().size() - 1).getId(); // obtengo el código de geometría del último elemento de la lista
        int idInt = Integer.parseInt(codigo); // lo convierto a entero
        idInt++; // le sumo 1
        codigo = Integer.toString(idInt); // lo convierto a String
        int count = countDigits(idInt); // cuento los dígitos del código de geometría
        while (count < 1) { // mientras el número de dígitos sea menor que 3
            codigo = "0" + codigo; // le añado un 0 delante
            count++; // incremento el contador
        }
        dz.setId(codigo); // le asigno el nuevo id al tweet

        db.getDatos().add(dz); // añado el objeto dz a la lista de datos de los tweets
        dbmgr.saveDB(db); // guardo la base de datos
        return "Tweet con id " + dz.getId() + " añadido a la base de datos";
    }

    public static int countDigits(int number) {
        int count = 0; // creo un contador
        while (number != 0) { // mientras el número sea distinto de 0
            number /= 10; // divido el número entre 10
            count++; // incremento el contador
        }
        return count; // devuelvo el contador
    }

    @DeleteMapping(path)
    public String eliminar(@RequestBody String id) {
        DBManagerTw dbmgr = new DBManagerTw(); // creo un objeto de la clase DBManager
        db = dbmgr.readDB(); // leo la base de datos
        for (int i = 0; i < db.getDatos().size(); i++) {
            //Escribe por consola que no encuentra el id
            System.out.println("El id que recibe es: " + id);
            System.out.println("El id de la base de datos es: " + db.getDatos().get(i).getId());
            // if que compare el id que recibe con el id de la base de datos
            if (db.getDatos().get(i).getId().equals(id)) { //comparo los datos
                // Imprime el dato que se va a eliminar
                System.out.println("El dato que se va a eliminar es: " + db.getDatos().get(i).getFecha());
                db.getDatos().remove(i); // elimino el dato con ese id
                dbmgr.saveDB(db); // guardo la base de datos
                break;
            }
        }
        return "Error! \n Zona básica con código geometría " + id + " no existe.";
    }
}