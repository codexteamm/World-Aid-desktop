/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.controllers;

import edu.worldaid.services.CompementCrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class campalreadysupported implements Initializable {

    @FXML
    private Label type;
    @FXML
    private Button CancelButton;
    @FXML
    private Button CancelButton1;
    int idass;
    int idcamp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void annuler(ActionEvent event) {
            Stage stage = (Stage) CancelButton.getScene().getWindow();
    // do what you have to do
    stage.close();
    }

    @FXML
    private void unsupport(ActionEvent event) {
        CompementCrud cc=new CompementCrud();
        cc.deletePrendreEnCharge(idcamp, idass);
        System.out.println("unspport from comp " + idass+" ,"+ idcamp);
        
        
        
    }

    void prendre_charge(int asss, int camp) {
        this.idass=asss;
        this.idcamp=camp;
        
        
    }
    
}
