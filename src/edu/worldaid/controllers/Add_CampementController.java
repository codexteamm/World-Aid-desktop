/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.controllers;

import edu.worldaid.entities.Association;
import edu.worldaid.entities.Campement;
import edu.worldaid.services.CompementCrud;
import edu.worldaid.services.UserCrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Add_CampementController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private Label type;
    @FXML
    private TextField nomc;
    @FXML
    private TextField paysc;
    @FXML
    private TextArea descriptionc;
    @FXML
    private WebView webView;
    WebEngine webEngine;
    @FXML
    private Button exit;
    @FXML
    private Button addc;
    @FXML
    private Label error;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        webEngine = webView.getEngine();

        URL url1 = this.getClass().getResource("/edu/worldaid/controllers/addmaps.html");
        webEngine.load(url1.toString());
        webEngine.setJavaScriptEnabled(true);

        // TODO
    }

    @FXML
    private void exit(ActionEvent event) {
        
            Stage stage = (Stage) exit.getScene().getWindow();
          stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));

    
    stage.close();
    }

    @FXML
    private void addc(ActionEvent event) {
        /*webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
    @Override
    public void changed(ObservableValue<? extends Worker.State> ov, Worker.State t, Worker.State t1) {
        if (t1 == Worker.State.SUCCEEDED) {*/
        CompementCrud cc = new CompementCrud();
        boolean er = false;
        String error = "";
        System.out.println(descriptionc.getText() + nomc.getText() + paysc.getText());
        if (nomc.getText() == null || nomc.getText().trim().isEmpty()) {
            error = error + "empty name field \n ";
            er = true;
        }
        
        if (paysc.getText() == null || paysc.getText().trim().isEmpty()) {
            error = error + "empty country field \n";
            er = true;

        }
        if (descriptionc.getText() == null || descriptionc.getText().trim().isEmpty()) {
            error = error + "empty description field \n";
            er = true;
        }

                if (cc.CheckCampementName(nomc.getText())) {
            error = error + "name already used \n";
            er = true;

        }
        this.error.setText(error);

        if (er == false) {

            try {
                System.out.println("trying to execute script");

                // fixed - innerHtml is a property, not a function
                String latitude = (String) webEngine.executeScript("document.getElementById('latitude').value");
                double lat = Double.parseDouble(latitude);

                String longitude = (String) webEngine.executeScript("document.getElementById('longitude').value");
                double lon = Double.parseDouble(longitude);

                Campement camp = new Campement(nomc.getText(), lat, lon, descriptionc.getText(), paysc.getText());
                   System.out.println(camp);
                

                 cc.addCompement(camp);
                System.out.println("script successful");
            } catch (Exception e) {

                // you can also print the exception to diagnose the error
                e.printStackTrace();
                System.out.println("script failed");
            }
        }
        //    }
        //}
//});

    }

}
