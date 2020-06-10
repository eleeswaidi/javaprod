/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dev;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pi.dev.Entity.Promotion;
import pi.dev.Service.PromotionService;


public class AjouterPromotionController implements Initializable {

    @FXML
    private DatePicker dated;
    @FXML
    private DatePicker datef;
    @FXML
    private TextField valeur;

    public static int idp;

    PromotionService prs = new PromotionService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Ajouter(ActionEvent event) {

        java.sql.Date dd = new java.sql.Date(new Date(dated.getEditor().getText()).getTime());
        java.sql.Date df = new java.sql.Date(new Date(datef.getEditor().getText()).getTime());
        int v = Integer.parseInt(valeur.getText());

        Promotion p = new Promotion(dd, df, v);
        prs.insererProduit(p, idp);

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("AfficherPromotion.fxml"));
            Stage myWindow = (Stage) valeur.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();

        } catch (IOException ex) {
            // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }

    }

    @FXML
    private void VerifHandleDateAction(ActionEvent event) {
        if (dated.getValue() != null) {
            java.sql.Date dateAuj = new java.sql.Date(Calendar.getInstance().getTime().getTime());

            Timestamp deb = new Timestamp(dated.getValue().getYear() - 1900, dated.getValue().getMonthValue() - 1, dated.getValue().getDayOfMonth(), 23, 59, 59, 5);
            if (dateAuj.getTime() >= deb.getTime()) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Date de debut ne doit pas inferieur à la date d'aujourd'hui", ButtonType.OK);
                a.show();
                dated.setValue(null);
            }
        }

        if (dated.getValue() != null && datef.getValue() != null) {
            java.sql.Date dateDeb = new java.sql.Date(dated.getValue().getYear() - 1900, dated.getValue().getMonthValue() - 1, dated.getValue().getDayOfMonth());
            java.sql.Date datefin = new java.sql.Date(datef.getValue().getYear() - 1900, datef.getValue().getMonthValue() - 1, datef.getValue().getDayOfMonth());
            if(datefin.getTime()<dateDeb.getTime())
            {
                Alert a = new Alert(Alert.AlertType.ERROR, "Date de fin ne doit pas dépasser la date de début", ButtonType.OK);
                a.show();
                datef.setValue(null);
            }
        }
    }

}
