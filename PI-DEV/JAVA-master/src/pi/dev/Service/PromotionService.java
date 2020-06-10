/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dev.Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pi.dev.Entity.*;
import pi.dev.Utility.ConnectDB;

public class PromotionService {
   public void insererProduit(Promotion p, int idproduit ){
        
        Connection conn = ConnectDB.getInstance().getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("insert into promotion (idp,dateDebut,dateFin,Valeur)  values (?,?,?,?)");
            ps.setInt(1, idproduit);
            ps.setDate(2, (Date) p.getDateDebut());
            ps.setDate(3, (Date) p.getDateFin());
            ps.setDouble(4, p.getValeur());
            
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        System.out.println("done");
    }
   
   public boolean supprimer(int id)   {
       try {   
                Connection conn = ConnectDB.getInstance().getConnection();
        String requeteInsert = "DELETE FROM promotion WHERE id =?";
        PreparedStatement pre=conn.prepareStatement(requeteInsert);
     
         pre.setInt(1, id);
        
        pre.executeUpdate();
        return true;
    
     } catch (SQLException ex) {
         
         System.out.println("erruer delete");
         return false;
     }
    }
   
   
        public Promotion RecherchePromotion(int idpp)  throws SQLException {
            Promotion pr ;
       Connection conn = ConnectDB.getInstance().getConnection();
        Statement ste=conn.createStatement();
        ResultSet rs = ste.executeQuery("select * from promotion where id="+idpp);
        while (rs.next()) {
            int id = rs.getInt(1);
            int idp=rs.getInt(2);
            Date dateDebut=rs.getDate("dateDebut");
            Date dateFin=rs.getDate("dateFin");
            double valeur=rs.getDouble("valeur");
            
            return pr =new Promotion(id, idp, dateDebut, dateFin, valeur);
            
        }
        return null;
    }

    public List<Promotion> readAll() throws SQLException {
        List<Promotion> arr = new ArrayList<>();
       Connection conn = ConnectDB.getInstance().getConnection();
        Statement ste=conn.createStatement();
        ResultSet rs = ste.executeQuery("select id,idp,dateDebut,dateFin,valeur from promotion");
        while (rs.next()) {
            int id = rs.getInt(1);
            int idp=rs.getInt(2);
            Date dateDebut=rs.getDate("dateDebut");
            Date dateFin=rs.getDate("dateFin");
            double valeur=rs.getInt("valeur");
           
            
            Promotion l =new Promotion(id,idp, dateDebut, dateFin, valeur);
            arr.add(l);
        }
        return arr;
    }

   
   public boolean modifier(Promotion p, int id)   {
        try {   
         Connection con = ConnectDB.getInstance().getConnection();
        String requeteInsert = "UPDATE promotion set idp=?,dateDebut=?,dateFin=?,valeur=? where id=?";
        PreparedStatement ps=con.prepareStatement(requeteInsert);
     
         
     ps.setInt(1, id);
            ps.setDate(2, (Date) p.getDateDebut());
            ps.setDate(3, (Date) p.getDateFin());
            ps.setDouble(4, p.getValeur());
            ps.setInt(5, p.getId());
        
        
        ps.executeUpdate();
        return true;
    
     } catch (SQLException ex) {
         
         System.out.println("erruer update");
         return false;
     }
   }
}
