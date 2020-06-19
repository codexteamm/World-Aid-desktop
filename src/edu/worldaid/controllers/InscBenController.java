/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.controllers;

import edu.worldaid.entities.Benevole;
import edu.worldaid.services.UserCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class InscBenController implements Initializable {

    @FXML
    private Button ret;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField mail;
    @FXML
    private TextField pays;
    @FXML
    private DatePicker date;

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
        if(!sp.checkUserName(username.getText())){
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
        if (nom.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Vous devez selectionnez un nom");
            alert.showAndWait();
            return;
        }
        if (prenom.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Vous devez selectionnez un prenom");
            alert.showAndWait();
            return;
        }
        if (mail.getText().isEmpty() 
                        || !mail.getText().contains("@") 
                        || !mail.getText().contains(".") 
                       //|| email.getText().indexOf("@", 0) > email.getText().indexOf(".", 0) 
                        || mail.getText().indexOf("#", 0) >= 0
                        || mail.getText().indexOf("&", 0) >= 0
                        || mail.getText().indexOf("(", 0) >= 0
                        //| email.getText().length() - email.getText().replace("@", "").length() > 1
                        //|| email.getText().length() - email.getText().replace(".", "").length() > 1
                        || mail.getText().indexOf("Â§", 0) >= 0
                        || mail.getText().indexOf("!", 0) >= 0
                        || mail.getText().indexOf("Ã§", 0) >= 0
                        || mail.getText().indexOf("Ã ", 0) >= 0
                        || mail.getText().indexOf("Ã©", 0) >= 0
                        || mail.getText().indexOf(")", 0) >= 0
                        || mail.getText().indexOf("{", 0) >= 0
                        || mail.getText().indexOf("}", 0) >= 0
                        || mail.getText().indexOf("|", 0) >= 0
                        || mail.getText().indexOf("$", 0) >= 0
                        || mail.getText().indexOf("*", 0) >= 0
                        || mail.getText().indexOf("â‚¬", 0) >= 0
                        || mail.getText().indexOf("`", 0) >= 0
                        || mail.getText().indexOf("\'", 0) >= 0
                        || mail.getText().indexOf("\"", 0) >= 0
                        || mail.getText().indexOf("%", 0) >= 0
                        || mail.getText().indexOf("+", 0) >= 0
                        || mail.getText().indexOf("=", 0) >= 0
                        || mail.getText().indexOf("/", 0) >= 0
                        || mail.getText().indexOf("\\", 0) >= 0
                        || mail.getText().indexOf(":", 0) >= 0
                        || mail.getText().indexOf(",", 0) >= 0
                        || mail.getText().indexOf("?", 0) >= 0
                        || mail.getText().indexOf(";", 0) >= 0
                        || mail.getText().indexOf("Â°", 0) >= 0
                        || mail.getText().indexOf("<", 0) >= 0
                        || mail.getText().indexOf(">", 0) >= 0) 
                {
                    
                     Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Erreur");
                alert.setContentText("Vous devez saisir un mail valid");
                alert.showAndWait();
                return;
                }
        if (pays.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Vous devez selectionnez un pays");
            alert.showAndWait();
            return;
        }
                String usernameP = username.getText();
        String passwordP = password.getText();
        String nomP = nom.getText();
        String prenomP = prenom.getText();
        String mailP = mail.getText();
        String paysP = pays.getText();
        Date dateBirth = java.sql.Date.valueOf(date.getValue());


        
        Benevole b = new Benevole(usernameP, nomP, prenomP, paysP, passwordP, mailP,dateBirth);
        sp.inscriptionUser(b);

    }
}
