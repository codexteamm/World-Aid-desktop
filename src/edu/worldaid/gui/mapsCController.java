/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.gui;

import edu.worldaid.entities.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import edu.worldaid.services.CompementCrud;
import java.util.List;
/**
 *
 * @author HP
 */
public class mapsCController implements Initializable {

    @FXML
    private WebView webView;
    @FXML
    private ListView<Campement> listView;
    @FXML
    private Button prendreCharge;
    private Campement selected;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prendreCharge.setDisable(true);
        WebEngine webEngine = webView.getEngine();

        URL url1 = this.getClass().getResource("/edu/worldaid/gui/webmaps.html");
         CompementCrud cc =new CompementCrud();
       List<Campement> list =cc.displayAllCampement();
        listView.getItems().addAll(list);

        listView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Campement> ov, Campement old_val, final Campement new_val) -> {
                    prendreCharge.setDisable(false);
                    selected = new_val;
                    System.out.println("Selected item: " + new_val.getLatitude() + new_val.getLongitude());
                    webEngine.executeScript("addpopup("+ new_val.getLatitude() + "," + new_val.getLongitude() + ")");

                });

        webEngine.load(url1.toString());
        webEngine.setJavaScriptEnabled(true);
    }

    @FXML
    private void predreChargeE(ActionEvent event) {

        System.out.println("prendre en charge: " + selected.getNom()+selected.getId());
        

    }

}
