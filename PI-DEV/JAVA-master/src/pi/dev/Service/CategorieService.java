/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pi.dev.Entity.Categorie;
import pi.dev.IService.ICategorie;
import pi.dev.Utility.ConnectDB;

/**
 *
 * @author elee
 */
public class CategorieService implements ICategorie<Categorie> {
    

    private Connection con;
    private Statement ste;

    public CategorieService() {
        con = ConnectDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Categorie t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `pi-dev`.`categorie` (`id`, `nom`) VALUES (NULL, '"
                + t.getNom() + "');";
        ste.executeUpdate(requeteInsert);
    }

    public void ajouter1(Categorie categoriel) throws SQLException {
        
        PreparedStatement pre = con.prepareStatement("INSERT INTO categorie (nom) VALUES (  ?);");
        
        pre.setString(1, categoriel.getNom());
       
        pre.executeUpdate();
    }

    @Override
    
    public void supprimer(int id)
    {
    
      try {
          PreparedStatement pt= con.prepareStatement("DELETE FROM categorie WHERE categorie.`id` =" +id );
          
          pt.execute();
      }catch (SQLException ex) {
                Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE,null,ex);

      }
    
    }
    

    
           @Override
           public void update(String cc){}
           
    public void update(String Oldnomm, String NvNom ) {
        try{
        PreparedStatement pt=con.prepareStatement("UPDATE categorie SET nom = ?  where nom = ?");
      
        pt.setString(1,NvNom);
                pt.setString(2,Oldnomm);
        pt.executeUpdate();
            System.out.println("update categorie établi");
        }catch (SQLException ex)
        {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE,null,ex);
        }
      
              
          }

   

    @Override
    public List<Categorie> readAll() throws SQLException {
        List<Categorie> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from categorie");
        while (rs.next()) {
            int id = rs.getInt(1);
            String nom = rs.getString("nom");
            
            
            Categorie l = new Categorie(id,nom);
            arr.add(l);
        }
        return arr;
    }


    
    public Categorie getCategorieByNom(String noom) throws SQLException {
      Categorie l;
        // List<Categorie> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from categorie where nom='"+noom+"'");
        while (rs.next()) {
            int id = rs.getInt(1);
            String nom = rs.getString("nom");
            
            
             l = new Categorie(id,nom);
            //arr.add(l);
            return l;
        }
        return null;
    }
    
      public Categorie getCategorieById(int ide) throws SQLException {
      Categorie l;
        // List<Categorie> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from categorie where id="+ide);
        while (rs.next()) {
            int id = rs.getInt(1);
            String nom = rs.getString("nom");
            
            
             l = new Categorie(id,nom);
            //arr.add(l);
            return l;
        }
        return null;
    }

   
    
}
