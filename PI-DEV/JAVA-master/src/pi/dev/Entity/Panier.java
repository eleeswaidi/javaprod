package pi.dev.Entity;

public class Panier {

    private int id;
    private float somme;
    private int quantité;

    public Panier(int id, float somme, int quantité) {
        this.id = id;
        this.somme = somme;
        this.quantité = quantité;

    }

    public Panier(float somme, int quantité) {

        this.somme = somme;
        this.quantité = quantité;

    }

    public Panier() {
    }

    public int getId() {
        return id;
    }

    public float getSomme() {
        return somme;
    }

    public int getQuantité() {
        return quantité;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSomme(float somme) {
        this.somme = somme;
    }

    public void setQuantité(int quantité) {
        this.quantité = quantité;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Float.floatToIntBits(this.somme);
        hash = 53 * hash + this.quantité;
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
        final Panier other = (Panier) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Float.floatToIntBits(this.somme) != Float.floatToIntBits(other.somme)) {
            return false;
        }
        if (this.quantité != other.quantité) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", somme=" + somme + ", quantit\u00e9=" + quantité + '}';
    }

}
