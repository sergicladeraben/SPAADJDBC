/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.paucasesnovescifp.spaad.jdbc.info;

import cat.paucasesnovescifp.spaad.jdbc.helpers.JDBCException;

/**
 *
 * @author Alumne
 */
public class Nacionalitat {

    String nom;

    public Nacionalitat(String nom) throws JDBCException {
     if(nom == null || "".equals(nom))throw new JDBCException("El nom de la naciolitat no pot ser null o una cadena buida");
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom;
    }
    

}
