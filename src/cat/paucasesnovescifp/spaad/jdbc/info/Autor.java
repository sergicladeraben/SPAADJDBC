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
public class Autor {

    private String ID_AUT;
    private String NOM_AUT;
    private String FK_NACIONALITAT = null;
    private String DNAIX_AUT = null;
    private String IMG_AUT = null;

    public Autor(String ID_AUT, String NOM_AUT) throws JDBCException {
        if (ID_AUT == null || "".equals(ID_AUT)) {
            throw new JDBCException("L'identificador de l'autor no pot ser null ni una cadena buida");
        }
        if (NOM_AUT == null || "".equals(NOM_AUT)) {
            throw new JDBCException("El nom de l'autor no pot ser null ni una cadena buida");
        }
        this.ID_AUT = ID_AUT;
        this.NOM_AUT = NOM_AUT;
    }

    public Autor(String ID_AUT, String NOM_AUT, String FK_NACIONALITAT) throws JDBCException {
        if (ID_AUT == null || "".equals(ID_AUT)) {
            throw new JDBCException("L'identificador de l'autor no pot ser null ni una cadena buida");
        }
        if (NOM_AUT == null || "".equals(NOM_AUT)) {
            throw new JDBCException("El nom de l'autor no pot ser null ni una cadena buida");
        }
        this.ID_AUT = ID_AUT;
        this.NOM_AUT = NOM_AUT;
        this.FK_NACIONALITAT = FK_NACIONALITAT;
    }

    public Autor(String ID_AUT, String NOM_AUT, String DNAIX_AUT, String FK_NACIONALITAT, String IMG_AUT) throws JDBCException {
        if (ID_AUT == null || "".equals(ID_AUT)) {
            throw new JDBCException("L'identificador de l'autor no pot ser null ni una cadena buida");
        }
        if (NOM_AUT == null || "".equals(NOM_AUT)) {
            throw new JDBCException("El nom de l'autor no pot ser null ni una cadena buida");
        }
        this.ID_AUT = ID_AUT;
        this.NOM_AUT = NOM_AUT;
        this.DNAIX_AUT = DNAIX_AUT;
        this.FK_NACIONALITAT = FK_NACIONALITAT;
        this.IMG_AUT = IMG_AUT;
    }

    public String getID_AUT() {
        return ID_AUT;
    }

    public void setID_AUT(String ID_AUT) {
        this.ID_AUT = ID_AUT;
    }

    public String getNOM_AUT() {
        return NOM_AUT;
    }

    public void setNOM_AUT(String NOM_AUT) {
        this.NOM_AUT = NOM_AUT;
    }

    public String getFK_NACIONALITAT() {
        return FK_NACIONALITAT;
    }

    public void setFK_NACIONALITAT(String FK_NACIONALITAT) {
        this.FK_NACIONALITAT = FK_NACIONALITAT;
    }

    public String getDNAIX_AUT() {
        return DNAIX_AUT;
    }

    public void setDNAIX_AUT(String DNAIX_AUT) {
        this.DNAIX_AUT = DNAIX_AUT;
    }

    public String getIMG_AUT() {
        return IMG_AUT;
    }

    public void setIMG_AUT(String IMG_AUT) {
        this.IMG_AUT = IMG_AUT;
    }

    @Override
    public String toString() {
        return "[ ID_AUT= " + ID_AUT + "| NOM_AUT= " + NOM_AUT + "| FK_NACIONALITAT= " + FK_NACIONALITAT + "| DNAIX_AUT= " + DNAIX_AUT + "| IMG_AUT= " + IMG_AUT + " ]";
    }

}
