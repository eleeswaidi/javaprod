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
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pi.dev.Entity.Categorie;
import pi.dev.Entity.Produit;
import pi.dev.Service.produitSevice;

/**
 * FXML Controller class
 *
 * @author Rzouga
 */
public class AfficherProduitController implements Initializable {

    @FXML
    private TableView<Produit> table;
    @FXML
    private TableColumn<Integer, Produit> id;
    @FXML
    private TableColumn<String,Produit> nom;
    @FXML
    private TableColumn<String, Produit> Description;
    @FXML
    private TableColumn<Integer, Produit> Prix;
    @FXML
    private TableColumn<Integer, Produit> Quantite;
    @FXML
    private TableColumn<String, Produit> Categorie;

    private ObservableList<Produit> ProduitData = FXCollections.observableArrayList();
    
    produitSevice ps = new produitSevice();
    @FXML
    private TextField searchTextfield;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            List<Produit> ListProduit= new ArrayList<Produit>();
            ListProduit = ps.readAll();
            ProduitData.clear();
            ProduitData.addAll(ListProduit);
                        System.out.println("pi.dev.AfficherProduitController.initialize()"+ListProduit);

            table.setItems(ProduitData);
            
                id.setCellValueFactory
        (
            new PropertyValueFactory<>("idp")
        );
        
        nom.setCellValueFactory
        (
            new PropertyValueFactory<>("nom")
        );
        
            Description.setCellValueFactory
        (
            new PropertyValueFactory<>("Description")
        );
        
                Prix.setCellValueFactory
        (
            new PropertyValueFactory<>("Prix")
        );
        
                    Quantite.setCellValueFactory
        (
            new PropertyValueFactory<>("quantite")
        );
       
            Categorie.setCellValueFactory
        (
            new PropertyValueFactory<>("categorie")
        );
        
          
            
        // TODO
    }    

    @FXML
    private void Ajouter(ActionEvent event) {   
                
        Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("AjouterProduit.fxml"));
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
    private void Modifier(ActionEvent event) {
        if(table.getSelectionModel().getSelectedItem()==null){
            Alert a=new Alert(Alert.AlertType.ERROR,"Vous devez séléctionner un produit",ButtonType.OK);
            a.show();
            return ;
        }
            ModifierProduitController.idmof =  table.getSelectionModel().getSelectedItem().getIdP();
                   System.out.println("cxxxxxxxxxxxxxxxxxxxxxxxxx"+ModifierProduitController.idmof);

                 
        Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("ModifierProduit.fxml"));
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
    private void Supprimer(ActionEvent event) throws SQLException {
        if(table.getSelectionModel().getSelectedItem()==null){
            Alert a=new Alert(Alert.AlertType.ERROR,"Vous devez séléctionner un produit",ButtonType.OK);
            a.show();
            return ;
        }  
        else{
        Produit ProduitSelec = (Produit) table.getSelectionModel().getSelectedItem();
               ps.supprimer(ProduitSelec.getIdP());
               resetTableData();
        }
    }
    
        public void resetTableData() throws SQLDataException, SQLException
    {
        List<Produit> listProduit = new ArrayList<>();
        listProduit = ps.readAll();
        ObservableList<Produit> data = FXCollections.observableArrayList(listProduit);
        table.setItems(data);
    }

    @FXML
    private void Promotion(ActionEvent event) {
         if(table.getSelectionModel().getSelectedItem()==null){
            Alert a=new Alert(Alert.AlertType.ERROR,"Vous devez séléctionner un produit",ButtonType.OK);
            a.show();
            return ;
        }  
        else{
        AjouterPromotionController.idp =  table.getSelectionModel().getSelectedItem().getIdP();
                   System.out.println("cxxxxxxxxxxxxxxxxxxxxxxxxx"+AjouterPromotionController.idp);

            Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("AjouterPromotion.fxml"));
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

    private void Categorie(ActionEvent event) {
        
            Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("AffihcierCategorie.fxml"));
            Stage myWindow =  (Stage) table.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        
    }

    private void Stqu(ActionEvent event) {
        
            Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("Stat.fxml"));
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
    private void handeSearchAction(KeyEvent event) {
          ObservableList<Produit> listaSearch=FXCollections.observableArrayList();
            if(searchTextfield.getText()!=""){
                    Iterator iterator = ProduitData.iterator(); 
                    while (iterator.hasNext()) {
                        Produit lv=(Produit)iterator.next();
                        if(lv.getNom().toUpperCase().contains(searchTextfield.getText().toUpperCase()) || lv.getDescription().toUpperCase().contains(searchTextfield.getText().toUpperCase()) )
                        {
                            listaSearch.add(lv);
                        }
                    }
             table.setItems(listaSearch);
            }
            else{
                table.setItems(ProduitData);
            }
    }
    
    
}
