/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dev.Entity;

/**
 *
 * @author admin
 */
public class CategorieReclamation {
    
    
 
    private int idCr;
    private String nomCr;

    @Override
    public String toString() {
        return "CategorieReclamation{" + "idCr=" + idCr + ", nomCr=" + nomCr + '}';
    }

    public int getIdCr() {
        return idCr;
    }

    public void setIdCr(int idCr) {
        this.idCr = idCr;
    }

    public String getNomCr() {
        return nomCr;
    }

    public void setNomCr(String nomCr) {
        this.nomCr = nomCr;
    }

    public CategorieReclamation(String nomCr) {
        this.nomCr = nomCr;
    }

    public CategorieReclamation(int idCr, String nomCr) {
        this.idCr = idCr;
        this.nomCr = nomCr;
    }
}
    
