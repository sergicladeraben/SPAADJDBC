/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.paucasesnovescifp.spaad.jdbc;

import cat.paucasesnovescifp.spaad.jdbc.db.Database;
import cat.paucasesnovescifp.spaad.jdbc.helpers.JDBCException;
import cat.paucasesnovescifp.spaad.jdbc.info.Autor;
import cat.paucasesnovescifp.spaad.jdbc.info.Nacionalitat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author Alumne
 */
public class Main {

    public static void main(String[] args) throws SQLException, JDBCException {
        //Hem d'assignar els properties de usuari, el pasword i la base de dades per construir la url sencera de la conexió
        //Un try catch que tiri un throw de que la url i les propietats no puguin ser null

        String url = "jdbc:mysql://localhost:3306/";
        if (url == null || "".equals(url)) {
            System.out.println("La url no pot ser null, ni una cadena buida");
        }
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "12345678");
        properties.setProperty("database", "biblioteca");
        Database biblioteca = new Database(url, properties);

        biblioteca.EnllaçDatabase(biblioteca);

        //Exercici 4
        System.out.println("Exercici 4: " + biblioteca.tornaLlengua());

        //Exercici 5
        System.out.println("Exercici 5: " + biblioteca.titolsLlengua("Catalana"));

        //Exercici 6
        System.out.println("Exercici 6: " + biblioteca.titolsLlenguaPS("Catalana"));

        //Exercici 8
        System.out.println("Exercici 8, Nacionalitats: " + biblioteca.getNacionalitats());
        System.out.println("Exercici 8, Autor: " + biblioteca.getAutor("1"));

        Nacionalitat nacionalitat = new Nacionalitat("Bohemia");
        System.out.println("Exercici 8, Autors: " + biblioteca.getAutors(nacionalitat));

        //Exercici 9
        ArrayList<Autor> autors9 = new ArrayList<Autor>();
        Autor sergi = new Autor("1147", "Cladera, Sergi");
        Autor lluis = new Autor("1148", "Noguera, Lluis");
        Autor ivan = new Autor("1149", "Petrus, Ivan");

        autors9.add(sergi);
        autors9.add(lluis);
        autors9.add(ivan);
//        
//        biblioteca.setNacionalitatAutors(nacionalitat, autors9);

        //Exercici 10 Comprovam si els autors creats es troben dins la base de dades correctament
        for (int i = 0; i < autors9.size(); i++) {
            System.out.println("Exercici 10, autor numero " + (i + 1) + ": " + biblioteca.getAutor(autors9.get(i).getID_AUT()));
        }
        
        
        //Exercici 11

    }

}
