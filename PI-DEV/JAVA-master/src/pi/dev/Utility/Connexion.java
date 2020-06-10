/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dev.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ANONYMOUS
 */
public class Connexion {
    String login ="root";
     String url = "jdbc:mysql://localhost:3306/symfony";
     String pwd="";
     public Connection con;
     
     static Connexion instance;
     private Connexion() {
         try {
             con=DriverManager.getConnection(url, login, pwd);
             System.out.println("connexion etablie");
         } catch (SQLException ex) {
             System.out.println(ex);
         }
    }
     public Connection  getConnection()
    {
    return con;
    }   
      public static Connexion getInstance()
    {if(instance==null)
        instance=new Connexion();
    return instance;
    }      
    
    
}
