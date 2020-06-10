/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dev;

import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;


public class FXMLDocumentController implements Initializable {

    public AnchorPane rootAcc;
    @FXML
    public AnchorPane employeebg;
    //public AnchorPane navbarbg;
    private ImageView statIcon1;
    private ImageView exitIcon;
    private ImageView statIcon;
    private ImageView planningIcon1;
    private ImageView planningIcon;
    @FXML
    public AnchorPane home;
    @FXML
    private ImageView deconnecter;
    private ImageView exitIconLiv;
    @FXML
    private Polygon d1;
    private Polygon r1;
    @FXML
    private Label d2;
      private Label r2;
    private Polygon e2;
    private Label e1;
    private Polygon s2;
    private Label s1;

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        d2.setVisible(false);
       d1.setVisible(false);
        home.setStyle("-fx-background-color:transparent");
       
    }

    private void handleButtonAction(MouseEvent event) throws IOException {
        if (event.getTarget() == planningIcon) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherPlanningJourLivreur.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.getScene().setFill(Color.TRANSPARENT);
            stage.show();

        }
    }

    @FXML
    private void unlight(MouseEvent event) {
        
        d1.setVisible(false);
       
        d2.setVisible(false);
       
    }

    @FXML
    private void light(MouseEvent event) {
        if (event.getTarget() == planningIcon) {
            planningIcon.setOpacity(0);
            planningIcon1.setVisible(true);
            r1.setVisible(false);
            d1.setVisible(false);
            s1.setVisible(false);
            e1.setVisible(true);
            r2.setVisible(false);
            d2.setVisible(false);
            s2.setVisible(false);
            e2.setVisible(true);

        } else if (event.getTarget() == statIcon) {

            statIcon.setOpacity(0);
            statIcon1.setVisible(true);
            r1.setVisible(false);
            d1.setVisible(false);
            s1.setVisible(true);
            e1.setVisible(false);
            r2.setVisible(false);
            d2.setVisible(false);
            s2.setVisible(true);
            e2.setVisible(false);
        } else if (event.getTarget() == exitIcon) {

            statIcon.setOpacity(0);
            statIcon1.setVisible(true);
            r1.setVisible(true);
            d1.setVisible(false);
            s1.setVisible(false);
            e1.setVisible(false);
            r2.setVisible(true);
            d2.setVisible(false);
            s2.setVisible(false);
            e2.setVisible(false);
        } else if (event.getTarget() == deconnecter) {

           
           
            d1.setVisible(true);
            
            d2.setVisible(true);

        }
    }

    @FXML
    private void handeDeconnectionAction(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Authentifaction.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        ((Stage) home.getScene().getWindow()).close();
    }

    @FXML
    private void handleButtonCategories(ActionEvent event)throws IOException{
    FXMLLoader loader = new FXMLLoader(getClass().getResource("AffihcierCategorie.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        
        stage.show();
    }

    @FXML
    private void handleButtonProduit(ActionEvent event)throws IOException{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduit.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        
        stage.show();
    }

    @FXML
    private void handleButtonStatistiqueLivraisonAdminAction(ActionEvent event)throws IOException{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StatProd.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        
        stage.show();
    }

    @FXML
    private void handleButonpromotionAction(ActionEvent event)throws IOException{
           FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherPromotion.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        
        stage.show();
    } 

}
