/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dev.Entity;
import java.sql.Date;


/**
 *
 * @author Ach
 */
public class Reclamation {
    
    private int idR;
    private String designation;
    private String description;
    private boolean etat;
    private Date date;



    @Override
    public String toString() {
        return "Reclamation{" + "idR=" + idR + ", designation=" + designation + ", description=" + description + ", etat=" + etat + ", date=" + date + '}';
    }

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Reclamation(int idR, String designation, String description, boolean etat, Date date) {
        this.idR = idR;
        this.designation = designation;
        this.description = description;
        this.etat = etat;
        this.date = date;
    }

  

    public Reclamation() {
    }
    
    
    
}
