/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.paucasesnovescifp.spaad.jdbc.db;

import cat.paucasesnovescifp.spaad.jdbc.helpers.JDBCException;
import cat.paucasesnovescifp.spaad.jdbc.info.Autor;
import cat.paucasesnovescifp.spaad.jdbc.info.Nacionalitat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Alumne
 */
public class Database {

    private String url;
    Properties propietats = new Properties();
    ArrayList<String> idiomes = new ArrayList<>();
    ArrayList<String> titolsLlengua = new ArrayList<>();
    ArrayList<Nacionalitat> nacionalitats = new ArrayList<>();
    ArrayList<Autor> autors = new ArrayList<Autor>();

    public Database(String url, Properties propietats) throws SQLException {

        this.url = url;
        this.propietats = propietats;

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Properties getPropietats() {
        return propietats;
    }

    public void setPropietats(Properties propietats) {
        this.propietats = propietats;
    }

    public List<String> getIdiomes() {
        return idiomes;
    }

    public void setIdiomes(ArrayList<String> idiomes) {
        this.idiomes = idiomes;
    }

    public void EnllaçDatabase(Database database) throws SQLException, JDBCException {

        Properties prop = database.getPropietats();
        if ((prop.getProperty("database") == null) || ("".equals(prop.getProperty("database")))) {
            throw new JDBCException("Bases de dades introduida amb valor null o cadena buida");
        }

        if ((prop.getProperty("user") == null) || ("".equals(prop.getProperty("database")))) {
            throw new JDBCException("User null o cadena buida");
        }

        if ((prop.getProperty("password") == null) || ("".equals(prop.getProperty("database")))) {
            throw new JDBCException("Password intrioduit null o cadena buida");
        }

        this.url = url + prop.getProperty("database") + "?" + "user=" + prop.getProperty("user") + "&" + "password=" + prop.getProperty("password");

    }

    public ArrayList<String> tornaLlengua() throws SQLException {

        String query = "SELECT * FROM biblioteca.llengues";
        Connection con = DriverManager.getConnection(url);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            String llengua = rs.getString(1);
            idiomes.add(llengua);
        }
        rs.close();

        st.close();
        con.close();

        return idiomes;
    }

    public ArrayList<String> titolsLlengua(String llengua) throws SQLException {

        String query = "SELECT titol FROM biblioteca.llibres where FK_LLENGUA = '" + llengua + "'";
        Connection con = DriverManager.getConnection(url);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            String titol = rs.getString(1);
            titolsLlengua.add(titol);
        }
        rs.close();

        st.close();
        con.close();

        return titolsLlengua;
    }

    public ArrayList<String> titolsLlenguaPS(String llengua) throws SQLException {

        String query = "SELECT titol FROM biblioteca.llibres where FK_LLENGUA = ? ";
        Connection con = DriverManager.getConnection(url);
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, llengua);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            String titol = rs.getString(1);
            titolsLlengua.add(titol);
        }
        rs.close();

        st.close();
        con.close();

        return titolsLlengua;
    }

    public ArrayList<Nacionalitat> getNacionalitats() throws SQLException, JDBCException {
        String query = "SELECT * FROM biblioteca.nacionalitats";
        Connection con = DriverManager.getConnection(url);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Nacionalitat nacionalitat = new Nacionalitat((String) rs.getObject(1));

            nacionalitats.add(nacionalitat);

        }
        rs.close();

        st.close();
        con.close();

        return nacionalitats;

    }

    public Autor getAutor(String ID_AUT) throws SQLException, JDBCException {
        String NOM_AUTOR = null;
        String DNAIX_AUT = null;
        String FK_NACIONALITAT = null;
        String IMG_AUT = null;
        String query = "SELECT * FROM biblioteca.autors  where ID_AUT = '" + ID_AUT + "'";
        Connection con = DriverManager.getConnection(url);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            NOM_AUTOR = rs.getString(2);
            DNAIX_AUT = rs.getString(3);
            FK_NACIONALITAT = rs.getString(4);
            IMG_AUT = rs.getString(5);
        }
        Autor autor = new Autor(ID_AUT, NOM_AUTOR, DNAIX_AUT, FK_NACIONALITAT, IMG_AUT);
        rs.close();

        st.close();
        con.close();

        return autor;

    }

    public ArrayList<Autor> getAutors(Nacionalitat nacionalitat) throws SQLException, JDBCException {
        String ID_AUT = null;
        String NOM_AUT = null;
        String DNAIX_AUT = null;
        String FK_NACIONALITAT = null;
        String IMG_AUT = null;

        String query = ("SELECT * FROM AUTORS WHERE FK_NACIONALITAT = '" + nacionalitat.getNom() + "'");

        Connection con = DriverManager.getConnection(url);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            ID_AUT = rs.getString(1);
            NOM_AUT = rs.getString(2);
            DNAIX_AUT = rs.getString(3);
            FK_NACIONALITAT = rs.getString(4);
            IMG_AUT = rs.getString(5);

            Autor autor = new Autor(ID_AUT, NOM_AUT, DNAIX_AUT, FK_NACIONALITAT, IMG_AUT);
            autors.add(autor);
        }

        rs.close();

        st.close();

        con.close();

        return autors;

    }

    public void setNacionalitatAutors(Nacionalitat nacionalitat, ArrayList<Autor> autors9) throws SQLException, JDBCException {
        String query = "INSERT INTO biblioteca.autors VALUES (?,?,?,?,?)";

        Connection con = DriverManager.getConnection(url);
        for (int i = 0; i < autors9.size(); i++) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, Integer.parseInt(autors9.get(i).getID_AUT()));
            stmt.setString(2, autors9.get(i).getNOM_AUT());
            stmt.setString(3, null);
            stmt.setString(4, nacionalitat.getNom());
            stmt.setString(5, null);
            stmt.executeUpdate();

            stmt.close();

        }
        con.close();

    }

}
