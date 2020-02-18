/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.controllers;

import edu.worldaid.entities.Association;
import edu.worldaid.entities.Campement;
import edu.worldaid.services.CompementCrud;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jdk.nashorn.internal.parser.TokenType;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DisplaycampsController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private ImageView Profile_pic;
    @FXML
    private Label username;
    @FXML
    private Button Home;
    @FXML
    private ImageView pic1;
    @FXML
    private Button Show_My_Draft;
    @FXML
    private TextField searchfField;
    @FXML
    private Button search;
    @FXML
    private ListView<Campement> listView;
    @FXML
    private WebView webView;
    @FXML
    private Button predreChargeE;
    private Campement selected;
    @FXML
    private Button Show_My_Draft1;
    @FXML
    private Button Add_Event2;
    @FXML
    private ImageView pic12;
    @FXML
    private Button Add_Camps;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CompementCrud cc = new CompementCrud();
        Association ass = (Association) cc.getConnectedUser();
        username.setText(ass.getNomAssociaiton());

        predreChargeE.setDisable(true);
        WebEngine webEngine = webView.getEngine();

        URL url1 = this.getClass().getResource("/edu/worldaid/controllers/webmaps.html");

        webEngine.load(url1.toString());
        webEngine.setJavaScriptEnabled(true);

        List<Campement> list = cc.displayAllCampement();
        listView.getItems().addAll(list);

        listView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Campement> ov, Campement old_val, final Campement new_val) -> {
                    predreChargeE.setDisable(false);
                    selected = new_val;
                    try {
                        System.out.println("Selected item: " + new_val.getDescription());
                        System.out.println("addpopup(" + new_val.getLatitude() + "," + new_val.getLongitude() + ",'" + new_val.getDescription() + "')");
                        webEngine.executeScript("addpopup(" + new_val.getLatitude() + "," + new_val.getLongitude() + ",'" + new_val.getDescription() + "')");
                    } catch (Exception e) {
                        System.out.println("problem with script"+ e.getMessage());
                    }

                });

    }


    @FXML
    private void Show_Home(ActionEvent event) {
    }

    @FXML
    private void Add_an_opportunity(ActionEvent event) {
    }

    @FXML
    private void Show_Draft(ActionEvent event) {
    }

    @FXML
    private void showq(ActionEvent event) {
    }

    @FXML
    private void logoutbutton(ActionEvent event) {
    }

    @FXML
    private void enter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String text = searchfField.getText();
            CompementCrud cc = new CompementCrud();

            String searchS = searchfField.getText();
            List<Campement> list = cc.displayAllCampement();

            List<Campement> result = list.stream()
                    .filter(item -> item.getNom().contains(searchS))
                    .collect(Collectors.toList());
            if (searchfField.getText() == null || searchfField.getText().trim().isEmpty()) {
                listView.getItems().clear();
                listView.getItems().addAll(cc.displayAllCampement());

            } else {

                listView.getItems().clear();
                listView.getItems().addAll(result);
            }
            searchfField.setText("");
        }
    }

    @FXML
    private void predreChargeE(ActionEvent event) throws IOException {
        System.out.println("prendre en charge: " + selected.getNom() + selected.getId());

        CompementCrud cc = new CompementCrud();
        Association asss = (Association) cc.getConnectedUser();
        
       if (cc.Checkprendreencharge(asss.getId(), selected.getId()))
       {
           
               /* FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/campalreadysupported.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();*/
      
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/campalreadysupported.fxml"));
            Parent root= loader.load();
            campalreadysupported dpc = loader.getController();
            dpc.idass=asss.getId();
            dpc.idcamp=selected.getId();
           
              Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("ABC");
        stage.setScene(new Scene(root));
        stage.show();
           
           
           
       }else{
                   cc.addPrendreEnCharge(selected.getId(), asss.getId());

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/campSupported.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("ABC");
        stage.setScene(new Scene(root1));
        stage.show();
           
       }
          

    }

    @FXML
    private void Add_Camp(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/Add_Campement.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DisplaycampsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void searchbclick(ActionEvent event) {
        CompementCrud cc = new CompementCrud();

        String searchS = searchfField.getText();
        List<Campement> list = cc.displayAllCampement();

        List<Campement> result = list.stream()
                .filter(item -> item.getNom().contains(searchS))
                .collect(Collectors.toList());
        if (searchfField.getText() == null || searchfField.getText().trim().isEmpty()) {
            listView.getItems().clear();
            listView.getItems().addAll(cc.displayAllCampement());

        } else {

            listView.getItems().clear();
            listView.getItems().addAll(result);
        }
        searchfField.setText("");

    }

}
