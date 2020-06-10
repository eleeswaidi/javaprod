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
import java.util.Date;
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
import pi.dev.Entity.Produit;
import pi.dev.Entity.Promotion;
import pi.dev.Service.PromotionService;


public class AfficherPromotionController implements Initializable {

    @FXML
    private TableView<Promotion> table;
    @FXML
    private TableColumn<Integer, Promotion> id;
    @FXML
    private TableColumn<Date, Promotion> dated;
    @FXML
    private TableColumn<Date, Promotion> datef;
    @FXML
    private TableColumn<Integer, Promotion> valeur;
    @FXML
    private TableColumn<Integer, Promotion> Produit;

    private ObservableList<Promotion> PromotionData = FXCollections.observableArrayList();
    PromotionService prs = new PromotionService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            List<Promotion> ListProduit = new ArrayList<Promotion>();
            ListProduit = prs.readAll();
            PromotionData.clear();
            PromotionData.addAll(ListProduit);
            System.out.println("pi.dev.AfficherProduitController.initialize()" + ListProduit);

            table.setItems(PromotionData);

            id.setCellValueFactory(
                    new PropertyValueFactory<>("id")
            );

            dated.setCellValueFactory(
                    new PropertyValueFactory<>("dateDebut")
            );

            datef.setCellValueFactory(
                    new PropertyValueFactory<>("dateFin")
            );

            valeur.setCellValueFactory(
                    new PropertyValueFactory<>("valeur")
            );

            Produit.setCellValueFactory(
                    new PropertyValueFactory<>("produit")
            );

            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AfficherPromotionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Modifier(ActionEvent event) {
        if (table.getSelectionModel().getSelectedItem() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Vous devez séléctionner une promotion", ButtonType.OK);
            a.show();
            return;
        } else {
            ModifierPromotionController.idpromo = table.getSelectionModel().getSelectedItem().getId();
            System.out.println("cxxxxxxxxxxxxxxxxxxxxxxxxx" + ModifierPromotionController.idpromo);

            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("ModifierPromotion.fxml"));
                Stage myWindow = (Stage) table.getScene().getWindow();
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
    private void Supprimer(ActionEvent event) throws SQLException {
      if(table.getSelectionModel().getSelectedItem()==null){
            Alert a=new Alert(Alert.AlertType.ERROR,"Vous devez séléctionner une promotion",ButtonType.OK);
            a.show();
            return ;
        }else{
        Promotion PromotionSelect = (Promotion) table.getSelectionModel().getSelectedItem();
        prs.supprimer(PromotionSelect.getId());
        resetTableData();
      }
    }

    public void resetTableData() throws SQLDataException, SQLException {
        List<Promotion> listPromotion = new ArrayList<>();
        listPromotion = prs.readAll();
        ObservableList<Promotion> data = FXCollections.observableArrayList(listPromotion);
        table.setItems(data);
    }

    @FXML
    private void Produit(ActionEvent event) {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("AfficherProduit.fxml"));
            Stage myWindow = (Stage) table.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();

        } catch (IOException ex) {
            // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

}
