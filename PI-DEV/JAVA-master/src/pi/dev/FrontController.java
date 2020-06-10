/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dev;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import pi.dev.Entity.Produit;
import pi.dev.Service.produitSevice;
import pi.dev.Utility.ConnectDB;


public class FrontController implements Initializable {

    @FXML
    private AnchorPane Paneeventsfx;
    @FXML
    private ImageView imageeventspanefx;
    @FXML
    private Label subjecteventspanefx;
    @FXML
    private Label texteventspanefx;
    @FXML
    private Label DateeventsInteface;
    @FXML
    private AnchorPane Paneeventsfx1;
    @FXML
    private ImageView imageeventspanefx1;
    @FXML
    private Label subjecteventspanefx1;
    @FXML
    private Label texteventspanefx1;
    @FXML
    private Label DateeventsInteface1;
    @FXML
    private AnchorPane Paneeventsfx11;
    @FXML
    private ImageView imageeventspanefx11;
    @FXML
    private Label subjecteventspanefx11;
    @FXML
    private Label texteventspanefx11;
    @FXML
    private Label DateeventsInteface11;
    private ObservableList<Produit> data;
    private Connection con = null;
    produitSevice ps = new produitSevice();
    int Current = 0;
    int CurrentEvent = 0;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           data = FXCollections.observableArrayList();
        con = ConnectDB.getInstance().getConnection();
         data.addAll(ps.readAll());
          try {
            getUserData(Current);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // TODO
    }
  
    public ArrayList<ImageView> ListImagese = new ArrayList<>();
    public ArrayList<Label> ListTextflowe = new ArrayList<>();
    public ArrayList<Label> Listlabeltitleevent = new ArrayList<>();
    public ArrayList<Label> Listdateevent = new ArrayList<>();
    public ArrayList<AnchorPane> Listpaneevent = new ArrayList<>();

    public ArrayList<AnchorPane> ListPaneeventsfx = new ArrayList<>();
    
      public void getUserData(int CurrentEvent) throws SQLException {

        Listpaneevent.add(Paneeventsfx);
        Listpaneevent.add(Paneeventsfx1);
        Listpaneevent.add(Paneeventsfx11);

        ListImagese.add(imageeventspanefx);
        ListImagese.add(imageeventspanefx1);
        ListImagese.add(imageeventspanefx11);

        ListTextflowe.add(texteventspanefx);
        ListTextflowe.add(texteventspanefx1);
        ListTextflowe.add(texteventspanefx11);

        Listlabeltitleevent.add(subjecteventspanefx);
        Listlabeltitleevent.add(subjecteventspanefx1);
        Listlabeltitleevent.add(subjecteventspanefx11);

        Listdateevent.add(DateeventsInteface);
        Listdateevent.add(DateeventsInteface1);
        Listdateevent.add(DateeventsInteface11);

        int Nombre = ps.numberevent();
        int i = CurrentEvent;

        for (i = CurrentEvent; i < CurrentEvent + 3; i++) {
        
            System.out.println(data.get(i).getImage());
            Image image = new Image("http://localhost/PI-DEV-PROD/web/photos/" + data.get(i).getImage());
            System.out.println("pi.dev.FrontController.getUserData()"+data.get(i).getImage());
            ListImagese.get(i).setImage(image);
            Listlabeltitleevent.get(i).setText(data.get(i).getNom());
            ListTextflowe.get(i).setText(data.get(i).getDescription());
            Listpaneevent.get(i).setVisible(true);
           // FXMLDocumentController.idproduit=data.get(i).getIdP();

        }
      }

    @FXML
    private void viewmore(ActionEvent event) {
               int Nombre = 0;
        try {
            Nombre = ps.numberevent();
        } catch (SQLException ex) {
            ex.getMessage();
        }
        CurrentEvent = CurrentEvent + 3;

        int diff = Nombre - CurrentEvent;
        if (diff == 2) {
            CurrentEvent--;
        } else if (diff == 1) {
            CurrentEvent = CurrentEvent - 2;
        } else if (diff <= 0) {
            CurrentEvent = 0;
        }

        try {
            getUserData(CurrentEvent);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
