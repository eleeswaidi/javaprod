/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dev;

import java.io.IOException;
import java.security.MessageDigest;
import sun.misc.BASE64Encoder;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pi.dev.Service.ServiceUtilisateur;


public class AuthentifactionController implements Initializable {

    //@FXML
    //private Text TitreAuthentification;
    @FXML
    private AnchorPane BackTextGroupBox;

    @FXML
    private AnchorPane GroupBox;

    @FXML
    private TextField UserName;

    @FXML
    private PasswordField Mdp;

    @FXML
    private Text erreurText;

    ServiceUtilisateur userDao = new ServiceUtilisateur();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void HandleConnexionAction(ActionEvent event) throws IOException {
        ServiceUtilisateur.SessionUser connexion = userDao.Connecter(Mdp.getText(), UserName.getText());
        if (connexion != null) {

            if (connexion.getTypeFos().equals("admin")) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
                Parent root = loader.load();
                FXMLDocumentController personController = loader.getController();
                personController.home.setBackground(Background.EMPTY);
                //  personController.navbarbg.setVisible(false);
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                // stage.show();
                Stage ThisStage = (Stage) erreurText.getScene().getWindow();
                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                stage.show();

                ThisStage.close();
            }

            if (connexion.getTypeFos().equals("client")) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("Front.fxml"));
                Parent root = loader.load();

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                // stage.show();
                Stage ThisStage = (Stage) erreurText.getScene().getWindow();

                stage.show();

                ThisStage.close();
            }

        } else {
            erreurText.setVisible(true);
            Mdp.setText("");
        }
    }

    private void HandleInscrireConnexion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Inscrire.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        ((Stage) erreurText.getScene().getWindow()).close();

        // stage.show();
    }

}
