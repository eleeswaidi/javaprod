/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dev;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import pi.dev.Utility.ConnectDB;


public class StatController implements Initializable {

  
    @FXML
    private BarChart<String, Integer> barCh;
    @FXML
    private JFXButton close;
    PreparedStatement pst;
    ResultSet res;
    private ConnectDB conn;
    @FXML
    private JFXButton load;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        conn = ConnectDB.getInstance();
        try {
            loaddata();
        } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }    

    @FXML
    private void close(ActionEvent event) {
   Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("AfficherProduit.fxml"));
            Stage myWindow =  (Stage) close.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @FXML
    private void loaddata() throws SQLException{
        conn = ConnectDB.getInstance();    
        String query="SELECT nom,quantite FROM Produit";
        XYChart.Series<String,Integer> series=new XYChart.Series<>();
        ResultSet rs=conn.getConnection().createStatement().executeQuery(query);
        try {
            while (rs.next())
            {
                series.getData().add(new XYChart.Data<>(rs.getString(1),rs.getInt(2)));
                barCh.getData().add(series);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
    }
    
    

