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
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DelateCamp implements Initializable {

    @FXML
    private Label type;
    @FXML
    private Button CancelButton;
    @FXML
    private Button CancelButton1;
    int idcamp=0;
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
          stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));

    
    stage.close();
        
    }

    @FXML
    private void unsupport(ActionEvent event) {
        CompementCrud cc =new CompementCrud();
        cc.deleteCampement(this.idcamp);
                           Stage stage = (Stage) CancelButton.getScene().getWindow();
          stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));

    
    stage.close();
    }
    
}
