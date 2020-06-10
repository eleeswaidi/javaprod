/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dev;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import pi.dev.Entity.Categorie;
import pi.dev.Service.CategorieService;


public class AjouterCategorieController implements Initializable {

    @FXML
    private TextField CategorieField;
    @FXML
    private Label error;
    @FXML
    private Button btnCat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        error.setVisible(false);
        error.setText("Cette catégorie existe déja");
        btnCat.setDisable(true);
    }

    @FXML
    private void AjouterCatégorie(ActionEvent event) throws SQLException {
        if (!error.visibleProperty().getValue() && !btnCat.disableProperty().getValue()) {
            Categorie cat = new Categorie();
            cat.setNom(CategorieField.getText());
            CategorieService cs = new CategorieService();
            cs.ajouter(cat);

            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("AffihcierCategorie.fxml"));
                Stage myWindow = (Stage) CategorieField.getScene().getWindow();
                Scene sc = new Scene(root);
                myWindow.setScene(sc);
                myWindow.setTitle("");
                myWindow.show();

            } catch (IOException ex) {
                // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
    }

    @FXML
    private void VerifCategorieHandlerAction(KeyEvent event) throws SQLException {
        if (CategorieField.getText() != null && CategorieField.getText().toString().equals("")) {
            btnCat.setDisable(true);
        } else {
            CategorieService catDao = new CategorieService();
            if (catDao.getCategorieByNom(CategorieField.getText()) == null) {
                error.setVisible(false);
                btnCat.setDisable(false);
            } else {
                error.setVisible(true);
                btnCat.setDisable(true);
            }
        }
    }

}
