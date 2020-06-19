/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.controllers;

import edu.worldaid.entities.CasSocial;
import edu.worldaid.services.UserCrud;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class InscCasController implements Initializable {

    @FXML
    private Button ret;
    @FXML
    private TextField username;
    @FXML
    private TextField idC;
    @FXML
    private TextField password;
    @FXML
    private TextField description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void retour(ActionEvent event) {
                        try {
            Stage stage = (Stage) ret.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/FXMLFirst.fxml"));
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
    private void ajouter(ActionEvent event) {
        UserCrud sp = new UserCrud();

        if (!sp.checkUserName(username.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("change your username");
            alert.showAndWait();
            return;

        }
        if (username.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Vous devez selectionnez un username");
            alert.showAndWait();
            return;
        }
        if (password.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Vous devez selectionnez un password");
            alert.showAndWait();
            return;
        }
        if (description.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Vous devez selectionnez un description");
            alert.showAndWait();
            return;
        }

        String descriptionP = description.getText();
        String usernameP = username.getText();
        String passwordP = password.getText();
        int idcP = Integer.parseInt(idC.getText());

        CasSocial c = new CasSocial(descriptionP, usernameP, passwordP, idcP);
        sp.inscriptionUser(c);

    }
}
