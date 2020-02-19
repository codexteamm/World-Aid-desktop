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

/**
 * FXML Controller class
 *
 * @author HP
 */
public class SowSupportedController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private WebView webView;
    @FXML
    private ImageView Profile_pic;
    @FXML
    private Label username;
    @FXML
    private Button Home;
    @FXML
    private Button Show_My_Draft1;
    @FXML
    private Button Show_My_Draft;
    @FXML
    private ImageView pic12;
    @FXML
    private Button supportedCamps;
    @FXML
    private ImageView pic121;
    @FXML
    private Button Add_Camps;
    @FXML
    private ImageView pic1;
    @FXML
    private Button reloadB;
    @FXML
    private TextField searchfField;
    @FXML
    private Button search;
    @FXML
    private ListView<Campement> listView;
    @FXML
    private Button showcamp;
    @FXML
    private Button Unsupport;
    Campement selected;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CompementCrud cc = new CompementCrud();
        Association ass = (Association) cc.getConnectedUser();
        username.setText(ass.getNomAssociaiton());
        Unsupport.setDisable(true);

        WebEngine webEngine = webView.getEngine();

        URL url1 = this.getClass().getResource("/edu/worldaid/controllers/webmaps.html");
        webEngine.load(url1.toString());
        webEngine.setJavaScriptEnabled(true);

        List<Campement> list = cc.displayCampementbyIdAss(ass.getId());
        listView.getItems().addAll(list);
        listView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Campement> ov, Campement old_val, final Campement new_val) -> {
                    Unsupport.setDisable(false);

                    selected = new_val;
                    try {
                        System.out.println("Selected item: " + new_val.getDescription());
                        System.out.println("addpopup(" + new_val.getLatitude() + "," + new_val.getLongitude() + ",'" + new_val.getDescription() + "')");
                        webEngine.executeScript("addpopup(" + new_val.getLatitude() + "," + new_val.getLongitude() + ",'" + new_val.getDescription() + "')");
                    } catch (Exception e) {
                        System.out.println("problem with script" + e.getMessage());
                    }

                });
        // TODO
    }

    @FXML
    private void Show_Home(ActionEvent event) {
    }

    @FXML
    private void Show_Draft(ActionEvent event) {
    }

    @FXML
    private void showSupportedCamps(ActionEvent event) {
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
    private void showq(ActionEvent event) {
    }

    @FXML
    private void logoutbutton(ActionEvent event) {
    }

    @FXML
    private void reloadbutton(ActionEvent event) {
        CompementCrud cc = new CompementCrud();
        Association ass = (Association) cc.getConnectedUser();
        System.out.println("reloadbutton cliked");

        listView.getItems().clear();
        listView.getItems().addAll(cc.displayCampementbyIdAss(ass.getId()));
    }

    @FXML
    private void enter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            CompementCrud cc = new CompementCrud();
            Association ass = (Association) cc.getConnectedUser();

            String searchS = searchfField.getText();
            List<Campement> list = cc.displayCampementbyIdAss(ass.getId());

            List<Campement> result = list.stream()
                    .filter(item -> item.getNom().contains(searchS))
                    .collect(Collectors.toList());
            if (searchfField.getText() == null || searchfField.getText().trim().isEmpty()) {
                listView.getItems().clear();
                listView.getItems().addAll(cc.displayCampementbyIdAss(ass.getId()));

            } else {

                listView.getItems().clear();
                listView.getItems().addAll(result);
            }
        }
    }

    @FXML
    private void searchbclick(ActionEvent event) {
        CompementCrud cc = new CompementCrud();
        Association ass = (Association) cc.getConnectedUser();

        String searchS = searchfField.getText();
        List<Campement> list = cc.displayCampementbyIdAss(ass.getId());

        List<Campement> result = list.stream()
                .filter(item -> item.getNom().contains(searchS))
                .collect(Collectors.toList());
        if (searchfField.getText() == null || searchfField.getText().trim().isEmpty()) {
            listView.getItems().clear();
            listView.getItems().addAll(cc.displayCampementbyIdAss(ass.getId()));

        } else {

            listView.getItems().clear();
            listView.getItems().addAll(result);
        }
        searchfField.setText("");
    }

    @FXML
    private void showcamp(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/displayCamps.fxml"));
            Parent root = loader.load();
            DisplaycampsController dpc = loader.getController();

            Home.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void unspport(ActionEvent event) {
        try {
            CompementCrud cc = new CompementCrud();
            Association asss = (Association) cc.getConnectedUser();

            /* FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/campalreadysupported.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();*/
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/campalreadysupported.fxml"));
            Parent root = loader.load();
            campalreadysupported dpc = loader.getController();
            dpc.idass = asss.getId();
            dpc.idcamp = selected.getId();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SowSupportedController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
