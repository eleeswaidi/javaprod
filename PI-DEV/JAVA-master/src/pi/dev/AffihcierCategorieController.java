/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dev;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import pi.dev.Entity.Categorie;
import pi.dev.Service.CategorieService;


public class AffihcierCategorieController implements Initializable {

    @FXML
    private TableColumn<Integer, Categorie> id;
    @FXML
    private TableColumn<Integer, Categorie> nom;
    @FXML
    private TableView<Categorie> table;
    private ObservableList<Categorie> CatData = FXCollections.observableArrayList();
    
    CategorieService cs = new CategorieService();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Categorie> listCategories= new ArrayList<Categorie>();
            listCategories = cs.readAll();
            CatData.clear();
            CatData.addAll(listCategories);
            table.setItems(CatData);
        id.setCellValueFactory
        (
            new PropertyValueFactory<>("id")
        );
        
        nom.setCellValueFactory
        (
            new PropertyValueFactory<>("nom")
        );
        
                    
                    // TODO
                    } catch (SQLException ex) {
            Logger.getLogger(AffihcierCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Add(ActionEvent event) {
                Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("AjouterCategorie.fxml"));
            Stage myWindow =  (Stage) table.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @FXML
    private void Delete(ActionEvent event) throws SQLException {
        
        if(table.getSelectionModel().getSelectedItem()==null){
            Alert a=new Alert(Alert.AlertType.ERROR,"Vous devez séléctionner une catégorie",ButtonType.OK);
            a.show();
            return ;
        }else{
               Categorie catSelc = (Categorie) table.getSelectionModel().getSelectedItem();
               cs.supprimer(catSelc.getId());
               resetTableData();
        }
    }
    
    public void resetTableData() throws SQLDataException, SQLException
    {
        List<Categorie> listCategorie = new ArrayList<>();
        listCategorie = cs.readAll();
        ObservableList<Categorie> data = FXCollections.observableArrayList(listCategorie);
        table.setItems(data);
    }

    @FXML
    private void Produit(ActionEvent event) {
        
                        
        Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("AfficherProduit.fxml"));
            Stage myWindow =  (Stage) table.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
        
    }
    
}
