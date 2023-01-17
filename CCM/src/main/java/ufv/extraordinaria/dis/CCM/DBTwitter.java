package ufv.extraordinaria.dis.CCM;

import ufv.extraordinaria.dis.CCM.Clases.DatosTweets;

import java.util.ArrayList;

public class DBTwitter {
        private ArrayList<DatosTweets> data; // Lista de datos de las zonas básicas de salud

        public DBTwitter(ArrayList<DatosTweets> datos) {
            this.data = datos;
        } // Constructor de la clase DB

        public DBTwitter() { // constructor
        }


        // Getter & Setter
        public ArrayList<DatosTweets> getDatos() {
            return data;
        }

        public void setDatos(ArrayList<DatosTweets> datos) {
            this.data = datos;
        }

    }

}
