/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dev;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pi.dev.Entity.Categorie;
import pi.dev.Entity.Produit;
import pi.dev.Service.CategorieService;
import pi.dev.Service.produitSevice;
import pi.dev.Utility.ConnectDB;


public class ModifierProduitController implements Initializable {

    @FXML
    private TextField Nom;
    @FXML
    private TextField description;
    @FXML
    private TextField prix;
    @FXML
    private TextField quantite;
    private ImageView imgprod;
    @FXML
    private ChoiceBox<String> cat;
    Connection conn;
    Statement ste;
    public static int idmof;

    produitSevice ps = new produitSevice();
    CategorieService p = new CategorieService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Produit p = ps.getProduit(idmof);
        Nom.setText(p.getNom());
        description.setText(p.getDescription());
        prix.setText(String.valueOf(p.getPrix()));
        quantite.setText(String.valueOf(p.getQuantite()));
        conn = ConnectDB.getInstance().getConnection();

        try {
            ObservableList<String> lst = FXCollections.observableArrayList();
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
            CategorieService cdao=new CategorieService();
            Categorie c=  cdao.getCategorieById(p.getCategorie());
            cat.getSelectionModel().select(c.getNom());
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);

        }        // TODO
    }

    @FXML
    private void Update(ActionEvent event) throws SQLException {
        String name = Nom.getText();
        String des = description.getText();
        int p = Integer.parseInt(prix.getText());
        int q = Integer.parseInt(quantite.getText());
         CategorieService cdao=new CategorieService();
        Categorie idCat = cdao.getCategorieByNom(cat.getValue());
        Produit produit = new Produit(idmof, name, p, des, q);
        produit.setCategorie(idCat.getId());

        ps.modifier(produit);
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("AfficherProduit.fxml"));
            Stage myWindow = (Stage) prix.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();

        } catch (IOException ex) {
            // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

}
