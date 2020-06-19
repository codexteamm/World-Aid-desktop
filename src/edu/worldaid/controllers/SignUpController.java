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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class SignUpController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private Button inscriAssocia;
    @FXML
    private Button inscrioVolunteer;
    @FXML
    private HBox inscriNeedy;
    @FXML
    private Button inscritneedy;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void inscriAssocia(ActionEvent event) {
        try {
            Stage stage = (Stage) inscriAssocia.getScene().getWindow();
            System.out.println("testsetseteststetest");
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/InscAsso.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            
            stage = new Stage();
            // stage.initStyle(StageStyle.TRANSPARENT); //Pour faire disparaitre la barre de fermeture basique de Windows
            
            stage.setScene(new Scene(root1));
            stage.getScene().setFill(Color.TRANSPARENT);
            stage.getScene().getStylesheets().setAll(edu.worldaid.gui.WorldAid.class.getResource("/edu/worldaid/gui/styles.css").toString());
            //Thread.sleep(5000); //Pour simuler un chargement pendant 'x' seconde(s)
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void inscrioVolunteer(ActionEvent event) {
                try {
            Stage stage = (Stage) inscriAssocia.getScene().getWindow();
            System.out.println("testsetseteststetest");
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/InscBen.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            
            stage = new Stage();
            // stage.initStyle(StageStyle.TRANSPARENT); //Pour faire disparaitre la barre de fermeture basique de Windows
            
            stage.setScene(new Scene(root1));
            stage.getScene().setFill(Color.TRANSPARENT);
            stage.getScene().getStylesheets().setAll(edu.worldaid.gui.WorldAid.class.getResource("/edu/worldaid/gui/styles.css").toString());
            //Thread.sleep(5000); //Pour simuler un chargement pendant 'x' seconde(s)
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void inscritneedy(ActionEvent event) {
                                try {
            Stage stage = (Stage) inscriAssocia.getScene().getWindow();
            System.out.println("testsetseteststetest");
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/InscCas.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            
            stage = new Stage();
            // stage.initStyle(StageStyle.TRANSPARENT); //Pour faire disparaitre la barre de fermeture basique de Windows
            
            stage.setScene(new Scene(root1));
            stage.getScene().setFill(Color.TRANSPARENT);
            stage.getScene().getStylesheets().setAll(edu.worldaid.gui.WorldAid.class.getResource("/edu/worldaid/gui/styles.css").toString());
            //Thread.sleep(5000); //Pour simuler un chargement pendant 'x' seconde(s)
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void inscriNeedy(MouseEvent event) {
    }
    
}
