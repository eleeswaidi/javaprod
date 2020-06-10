/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dev;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static pi.dev.AjouterPromotionController.idp;
import pi.dev.Entity.Promotion;
import pi.dev.Service.PromotionService;
import pi.dev.Service.produitSevice;


public class ModifierPromotionController implements Initializable {

    @FXML
    private DatePicker dated;
    @FXML
    private DatePicker datef;
    @FXML
    private TextField valeur;

    public static int idpromo;

    PromotionService prs = new PromotionService();
    produitSevice ps = new produitSevice();
    @FXML
    private Label prod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            Promotion p = new Promotion();
            p = prs.RecherchePromotion(idpromo);
            System.out.println("pi.dev.ModifierPromotionController.initialize()" + p);
            valeur.setText(String.valueOf(p.getValeur()));
            prod.setText(ps.getProduit(p.getProduit()).getNom());
            dated.setValue(p.getDateDebut().toLocalDate());
            datef.setValue(p.getDateFin().toLocalDate());
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(ModifierPromotionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Modifier(ActionEvent event) throws SQLException {

        java.sql.Date dd = new java.sql.Date(new Date(dated.getEditor().getText()).getTime());
        java.sql.Date df = new java.sql.Date(new Date(datef.getEditor().getText()).getTime());
        double v = Double.parseDouble(valeur.getText());

        Promotion p = new Promotion(idpromo, dd, df, v);
        Promotion pro = prs.RecherchePromotion(idpromo);

        prs.modifier(p, ps.getProduit(pro.getProduit()).getIdP());
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

}
