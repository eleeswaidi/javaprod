/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dev.Entity;

import java.sql.Date;
import java.util.Objects;

public class CommandeP {

    private int id;
    private float somme;
    private Date date;

    public CommandeP(int id, float somme, Date date) {
        this.id = id;
        this.somme = somme;
        this.date = date;

    }

    public CommandeP(float somme, Date date) {

        this.somme = somme;
        this.date = date;

    }

    public CommandeP() {
    }

    public int getId() {
        return id;
    }

    public float getSomme() {
        return somme;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSomme(float somme) {
        this.somme = somme;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Float.floatToIntBits(this.somme);
        hash = 59 * hash + Objects.hashCode(this.date);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CommandeP other = (CommandeP) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Float.floatToIntBits(this.somme) != Float.floatToIntBits(other.somme)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommandeP{" + "id=" + id + ", somme=" + somme + ", date=" + date + '}';
    }

}