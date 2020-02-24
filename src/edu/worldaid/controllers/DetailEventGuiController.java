/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.controllers;

import edu.worldaid.entities.Evenement;
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
 * @author user
 */
public class DetailEventGuiController implements Initializable {

    private Evenement e;
    @FXML
    private Label type;
    @FXML
    private Button CancelButton;
    @FXML
    private Label colevent_name;
    @FXML
    private Label colexpary_date;
    @FXML
    private Label colstart_date;
    @FXML
    private Label colevent_category;

    /**
     * Initializes the controller class.
     *
     * * @param Ev
     */
    public void AfficherDetails(Evenement ev) {
        e = ev;

      //  System.out.println(e.getNom_event() + "bij");
        //System.out.println(e.getNom_event());
        colevent_name.setText(e.getNom_event());
        colstart_date.setText(e.getDate_debut_event().toString());
        colexpary_date.setText(e.getDate_fin_event().toString());
        colevent_category.setText(e.getCategorie());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void annuler(ActionEvent event) {
        Stage stage = (Stage) CancelButton.getScene().getWindow();
        stage.close();
    }

}
