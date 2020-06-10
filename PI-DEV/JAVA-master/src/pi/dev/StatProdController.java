/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dev;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import pi.dev.Service.produitSevice;


public class StatProdController implements Initializable {

    @FXML
    private Text titre;

    @FXML
    private Pane paneStat;
    public int idLivreur = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }

    public void loadData() {
        paneStat.getChildren().clear();
        ObservableList<PieChart.Data> lista = FXCollections.observableArrayList();
        produitSevice sdao = new produitSevice();
        List<produitSevice.Stat> listStat = sdao.afficheproduitParCateg();
        Iterator iterator = listStat.iterator();
        while (iterator.hasNext()) {
            produitSevice.Stat stat = (produitSevice.Stat) iterator.next();
            lista.add(new PieChart.Data(stat.getGroup(), stat.getNombre()));
        }
        PieChart piechat = new PieChart(lista);
        piechat.setTitle("Produits par catégorie");

        lista.forEach(data
                -> data.nameProperty().bind(
                        Bindings.concat(
                                (data.pieValueProperty().intValue()), " produit(s) ", data.getName()
                        )
                )
        );

        paneStat.getChildren().add(piechat);

    }

}
