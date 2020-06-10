/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dev;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import pi.dev.Entity.Categorie;
import pi.dev.Entity.Produit;
import pi.dev.Service.CategorieService;
import pi.dev.Service.produitSevice;
import pi.dev.Utility.ConnectDB;


public class AjouterProduitController implements Initializable {

    @FXML
    private TextField Nom;
    @FXML
    private TextField description;
    @FXML
    private TextField prix;
    @FXML
    private TextField quantite;
    @FXML
    private ImageView imgprod;
    @FXML
    private ChoiceBox<String> cat;
    private ResultSet rs;
    Connection conn;
    Statement ste;

    int c;
    int file;
    File pDir;
    File pfile;
    String lien;
    @FXML
    private Button btnAjout;
    @FXML
    private Label error;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        error.setVisible(false);
        btnAjout.setDisable(true);

        cat.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                if ( Nom.getText().toString().equals("") || description.getText().toString().equals("") || prix.getText().toString().equals("") || quantite.getText().toString().equals("")) {
                   btnAjout.setDisable(true);
                    error.setVisible(true);
                } else {
                   btnAjout.setDisable(false);
                    error.setVisible(false);
                }

               
            }
        });

        conn = ConnectDB.getInstance().getConnection();

        try {
            ObservableList<String> lst = FXCollections.observableArrayList();
            CategorieService p = new CategorieService();
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery("select * from categorie");
            try {
                while (rs.next()) {
                    lst.add(rs.getString(2));
                }
            } catch (SQLException ex) {
                Logger.getLogger(AjouterCategorieController.class.getName()).log(Level.SEVERE, null, ex);
            }
            cat.setItems(lst);

            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);

        }
        c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
        pDir = new File("C:/wamp64/www/Projet/Projet/web/Upload/Profile" + c + ".jpg");
        lien = "Profile" + c + ".jpg";
    }

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
        
        CategorieService cs = new CategorieService();
        produitSevice ps = new produitSevice();

        copier(pfile, pDir);
        String nom = Nom.getText();
        String Description = description.getText();
        int pri = Integer.parseInt(prix.getText());
        int qua = Integer.parseInt(quantite.getText());
        Categorie idCat = cs.getCategorieByNom(cat.getValue());
        Produit p = new Produit(nom, Description, pri, qua, idCat.getId(), lien);
        ps.insererProduit(p);
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("AfficherProduit.fxml"));
            Stage myWindow = (Stage) Nom.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();

        } catch (IOException ex) {
            // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }

    }

    @FXML
    private void uploadFile(ActionEvent event) throws MalformedURLException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image: ");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );
        Window stage = null;
        pfile = fileChooser.showOpenDialog(stage);

        /* - draw image */
        if (pfile != null) {
            file = 1;
            Image image = new Image(pfile.toURI().toURL().toExternalForm());
            imgprod.setImage(image);
        }
    }

    public static boolean copier(File source, File dest) {
        try (InputStream sourceFile = new java.io.FileInputStream(source);
                OutputStream destinationFile = new FileOutputStream(dest)) {
            // Lecture par segment de 0.5Mo  
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false; // Erreur 
        }
        return true; // Résultat OK   
    }

    @FXML
    private void VerifHandleAction(KeyEvent event) {
        if (verif() == true) {
            btnAjout.setDisable(false);
            error.setVisible(false);
        } else {
            btnAjout.setDisable(true);
            error.setVisible(true);
        }
       if( event.getSource()==quantite){
        int a=  quantite.getText().toString().length();
       char caracter= quantite.getText().toString().charAt(a-1);
       if(Character.isDigit( caracter)==false){
           quantite.setText(quantite.getText().toString().substring(0, a-1));
           quantite.positionCaret(quantite.getText().toString().length());
       }
       }
       
       if( event.getSource()==prix){
        int a=  prix.getText().toString().length();
       char caracter= prix.getText().toString().charAt(a-1);
       if(Character.isDigit( caracter)==false){
           prix.setText(prix.getText().toString().substring(0, a-1));
            prix.positionCaret(prix.getText().toString().length());
       }
       }
    }

    public boolean verif() {
        if (cat.getValue() == null || Nom.getText().toString().equals("") || description.getText().toString().equals("") || prix.getText().toString().equals("") || quantite.getText().toString().equals("")) {
            return false;
        } else {
            return true;
        }

    }

    @FXML
    private void VerifCategorieHandler(InputMethodEvent event) {

    }

}
