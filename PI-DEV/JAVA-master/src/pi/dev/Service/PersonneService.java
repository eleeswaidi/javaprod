
package pi.dev.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pi.dev.Entity.Personne;
import pi.dev.Utility.ConnectDB;

/**
 *
 * @author admin
 */
public class PersonneService {
   
   
   Connection cnx2;
   public PersonneService(){
       cnx2=ConnectDB.getInstance().getConnection();
   }
   public void ajouterPersonne(){
       try {
       String requete="INSERT INTO personne (nom,prenom) VALUES('abidi','Nada')";
   
    Statement st= cnx2.createStatement();
    st.executeUpdate(requete);
    System.out.println("Personne");
}
catch (SQLException ex) {
System.out.println(ex.getMessage());
}
   }
   
   public void ajouterPersonne2(Personne p){
       try{
       String requete2="INSERT INTO  personne (nom,prenom) VALUES (?,?)";
       PreparedStatement pst = cnx2.prepareStatement(requete2);
       pst.setString(1, p.getNom());
       pst.setString(2, p.getPrenom());
       pst.executeUpdate();
       System.out.println("Personne added");
       } catch (SQLException ex) {
           System.out.println(ex.getMessage());
       }
   }
   
   public List <Personne> afficher(){
       
    List<Personne> myList= new ArrayList<>();
       try {
      
       String requete ="SELECT *FROM personne";
       PreparedStatement pst=cnx2.prepareStatement(requete);
     ResultSet rs=  pst.executeQuery();
       while (rs.next()){
           Personne p=new Personne();
           p.setId(rs.getInt("id"));
           p.setNom(rs.getString(2));
           p.setPrenom(rs.getString("prenom"));
           myList.add(p);
       }
           
       }
       catch (SQLException ex) {
               System.out.println(ex.getMessage());
               }
       return myList;
   }
}