/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
//  <HyperlinkLabel layoutX="138.0" layoutY="93.0" />

/**
 * FXML Controller class
 *
 * @author user
 */
public class HelpCenterController implements Initializable {

    @FXML
    private Label type;
    @FXML
    private Label type1;
    @FXML
    private Label type11;
    @FXML
    private Label type13;
    @FXML
    private Label type112;
    @FXML
    private Label type12;
    @FXML
    private Label type111;
    @FXML
    private Label type14;
    @FXML
    private Label type113;
    @FXML
    private Label type15;
    @FXML
    private Label type114;
    @FXML
    private Label type1511;
    @FXML
    private Label type11411;
    @FXML
    private Label type151;
    @FXML
    private Label type1141;
    @FXML
    private Label type1512;
    @FXML
    private Label type11412;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Backtohome(ActionEvent event) {
        try {
            Parent tableViewOpportunity=FXMLLoader.load(getClass().getResource("/edu/worldaid/gui/AcceuilAssociation.fxml"));
            Scene tableViewOpportunityScene=new Scene (tableViewOpportunity);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewOpportunityScene);
        } catch (IOException ex) {
            Logger.getLogger(HelpCenterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
