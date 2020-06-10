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
import java.util.ArrayList;
import java.util.List;
import pi.dev.Entity.Reclamation;
import pi.dev.Utility.ConnectDB;

/**
 *
 * @author admin
 */
public class ReclamationServices {
      Connection cnx2;
   public ReclamationServices(){
       cnx2=ConnectDB.getInstance().getConnection();
   }
//                public void ajouterReclamation(){
//                    try {
//                    String requete="INSERT INTO Reclamation (designation,description,etat,date) VALUES('malek','blaaa',0,'2020-11-16')";
//
//                 Statement st= cnx2.createStatement();
//                 st.executeUpdate(requete);
//                 System.out.println("Reclamation");
//             }
//             catch (SQLException ex) {
//             System.out.println(ex.getMessage());
//             }
//                }
   
       public void ajouterReclamation2(Reclamation r){
           
          
       try{
       String requete2="INSERT INTO  Reclamation (designation,description,etat,date) VALUES (?,?,?,?)";
       PreparedStatement pst = cnx2.prepareStatement(requete2);
       pst.setString(1, r.getDesignation());
       pst.setString(2, r.getDescription());
   
       pst.setBoolean(3, r.getEtat());
       pst.setDate(4, (Date) r.getDate());
       pst.executeUpdate();
       System.out.println("reclamation added");
       } catch (SQLException ex) {
           System.out.println(ex.getMessage());
       }
   }
       
          public List <Reclamation> afficher(){
       
    List<Reclamation> myList= new ArrayList<>();
       try {
      
       String requete ="SELECT * FROM reclamation";
       PreparedStatement pst=cnx2.prepareStatement(requete);
     ResultSet rs=  pst.executeQuery();
       while (rs.next()){
          Reclamation r=new Reclamation();
           r.setIdR(rs.getInt("id"));
           
           r.setDesignation(rs.getString("Designation"));
           r.setDescription(rs.getString("Description"));
           r.setEtat(rs.getBoolean("Etat"));
            r.setDate(rs.getDate("date"));
           myList.add(r);
       }
           
       }
       catch (SQLException ex) {
               System.out.println(ex.getMessage());
               }
       return myList;
   }
          
    public boolean SupprimerReclamation() throws SQLException {
		String requete = "DELETE FROM Reclamation WHERE id=13";

                   PreparedStatement pst = cnx2.prepareStatement(requete);
  
                   int rowsDeleted = pst.executeUpdate();
                   if (rowsDeleted > 0) {
	         System.out.println("A user was deleted successfully!");
}
          return false;
	
       
       
       
}
   
    	public  void ModificationReclamation(Reclamation r ) throws SQLException {
        
    String requete = "UPDATE Reclamation SET  designation=?, description=?,etat=?,date=? WHERE id=6";
    
    PreparedStatement pst = cnx2.prepareStatement(requete);
 
       
         pst.setString(1, "123456789");
         pst.setString(2, "William Henry Bill Gates");
         pst.setBoolean(3, true);
         pst.setDate(4, (Date) r.getDate());
 
        int rowsUpdated = pst.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing user was updated successfully!");
        }
        
        }
	
		
	}
