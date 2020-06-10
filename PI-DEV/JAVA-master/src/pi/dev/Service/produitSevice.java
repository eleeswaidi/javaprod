package pi.dev.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pi.dev.Entity.Produit;
import pi.dev.Utility.ConnectDB;

public class produitSevice {

    private Statement ste;

    public void insererProduit(Produit p) {

        Connection conn = ConnectDB.getInstance().getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("insert into produit (nom,description,prix,quantite,id,nomfile) values (?,?,?,?,?,?)");
            ps.setString(1, p.getNom());
            ps.setString(2, p.getDescription());
            ps.setInt(3, p.getPrix());
            ps.setInt(4, p.getQuantite());
            ps.setInt(5, p.getCategorie());
            ps.setString(6, p.getImage());

            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println("done");
    }

    public Produit getProduit(int id) {

        Produit p = new Produit();
        Connection conn = ConnectDB.getInstance().getConnection();
        try {
            //*********
            PreparedStatement ps = conn.prepareStatement("select * from produit where idp =" + id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                p.setIdP(rs.getInt("idp"));
                //p.setCategorie(pservice. s.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrix(rs.getInt("prix"));
                p.setDescription(rs.getString("description"));
                p.setQuantite(rs.getInt("quantite"));
                p.setCategorie(rs.getInt("id"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }

    public List<Produit> readAll() {

        List<Produit> arr = new ArrayList();
        Connection conn = ConnectDB.getInstance().getConnection();
        try {
            //*********
            PreparedStatement ps = conn.prepareStatement("select * from produit order by nom");
            CategorieService pservice = new CategorieService();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Produit p = new Produit();
                p.setIdP(rs.getInt("idp"));
                //p.setCategorie(pservice. s.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrix(rs.getInt("prix"));
                p.setImage(rs.getString("nomfile"));
                p.setDescription(rs.getString("description"));
                p.setQuantite(rs.getInt("quantite"));
                p.setCategorie(rs.getInt("id"));
                arr.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return arr;
    }

    public boolean supprimer(int id) {
        try {
            Connection conn = ConnectDB.getInstance().getConnection();
            String requeteInsert = "DELETE FROM produit WHERE idp=" + id;
            PreparedStatement pre = conn.prepareStatement(requeteInsert);

            pre.executeUpdate();
            return true;

        } catch (SQLException ex) {

            System.out.println("erruer delete");
            return false;
        }
    }

    public boolean modifier(Produit p) {
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String requeteInsert = "UPDATE produit set nom=?, description=?,quantite=?,prix=?,id=?  where idp=?";
            PreparedStatement pre = con.prepareStatement(requeteInsert);

            pre.setString(1, p.getNom());
            pre.setString(2, p.getDescription());
            pre.setInt(3, p.getQuantite());
            pre.setInt(4, p.getPrix());
            pre.setInt(6, p.getIdP());
            pre.setInt(5, p.getCategorie());

            pre.executeUpdate();
            return true;

        } catch (SQLException ex) {

            System.out.println("erruer update");
            return false;
        }

    }

    public Produit RechercheProduit(String nom) {

        Produit p = new Produit();
        Connection conn = ConnectDB.getInstance().getConnection();
        try {
            //*********
            PreparedStatement ps = conn.prepareStatement("select * from produit where nom='" + nom + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                p.setIdP(rs.getInt("idp"));
                p.setNom(rs.getString("nom"));
                p.setDescription(rs.getString("description"));
                p.setQuantite(rs.getInt("quantite"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }

    public int numberevent() throws SQLException {
        int y = 0;
        Connection con = ConnectDB.getInstance().getConnection();

        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("SELECT COUNT(*) as total FROM produit ");
        while (rs.next()) {
            y = rs.getInt("total");

        }
        System.out.println("total number : " + y);
        return y;

    }

    public List<Stat> afficheproduitParCateg() {

        List<Stat> arr = new ArrayList();
        Connection conn = ConnectDB.getInstance().getConnection();
        try {
            //*********
            PreparedStatement ps = conn.prepareStatement("select c.nom,count(distinct p.idp) from produit p inner join categorie c on c.id=p.id group by c.nom  ");
            CategorieService pservice = new CategorieService();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String grp = rs.getString(1);
                int number = rs.getInt(2);
                Stat l = new Stat(grp, number);
                arr.add(l);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return arr;
    }

    public class Stat {

        private String group;
        private int nombre;

        public Stat(String group, int nombre) {
            this.group = group;
            this.nombre = nombre;
        }

        public Stat() {

        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public int getNombre() {
            return nombre;
        }

        public void setNombre(int nombre) {
            this.nombre = nombre;
        }

    }

}
