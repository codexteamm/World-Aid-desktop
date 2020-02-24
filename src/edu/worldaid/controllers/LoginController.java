/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.controllers;

import edu.worldaid.entities.Administrateur;
import edu.worldaid.entities.Association;
import edu.worldaid.entities.Benevole;
import edu.worldaid.entities.CasSocial;
import edu.worldaid.entities.User;
import edu.worldaid.services.UserCrud;
import edu.worldaid.gui.WorldAid;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class LoginController implements Initializable {

    @FXML
    private Button button_login;
    @FXML
    private TextField username;
    @FXML
    private TextField mdp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws InterruptedException {
        try {

            UserCrud uc = new UserCrud();
       
         
            User u= uc.login(username.getText(), mdp.getText());
            System.out.println(u);
            if (u != null) {
                
                if (u instanceof Benevole) {
                    Stage stage = (Stage) button_login.getScene().getWindow();
                    stage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("toadd"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    stage = new Stage();
                    stage.initStyle(StageStyle.TRANSPARENT); //Pour faire disparaitre la barre de fermeture basique de Windows

                    stage.setScene(new Scene(root1));
                    stage.getScene().setFill(Color.TRANSPARENT);
                    stage.getScene().getStylesheets().setAll(edu.worldaid.gui.WorldAid.class.getResource("/edu/worldaid/gui/styles.css").toString());
                    //Thread.sleep(5000); //Pour simuler un chargement pendant 'x' seconde(s)
                    stage.show();

                }
                if (u instanceof Association) {
                    
                    Stage stage = (Stage) button_login.getScene().getWindow();
                    stage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/AcceuilAssociation.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    stage = new Stage();
                   // stage.initStyle(StageStyle.TRANSPARENT); //Pour faire disparaitre la barre de fermeture basique de Windows

                    stage.setScene(new Scene(root1));
                    stage.getScene().setFill(Color.TRANSPARENT);
                    stage.getScene().getStylesheets().setAll(edu.worldaid.gui.WorldAid.class.getResource("/edu/worldaid/gui/styles.css").toString());
                    //Thread.sleep(5000); //Pour simuler un chargement pendant 'x' seconde(s)
                    stage.show();

                }
                if (u instanceof CasSocial) {
                                        Stage stage = (Stage) button_login.getScene().getWindow();
                    stage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("toadd"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    stage = new Stage();
                    stage.initStyle(StageStyle.TRANSPARENT); //Pour faire disparaitre la barre de fermeture basique de Windows

                    stage.setScene(new Scene(root1));
                    stage.getScene().setFill(Color.TRANSPARENT);
                    stage.getScene().getStylesheets().setAll(edu.worldaid.gui.WorldAid.class.getResource("/edu/worldaid/gui/styles.css").toString());
                    //Thread.sleep(5000); //Pour simuler un chargement pendant 'x' seconde(s)
                    stage.show();


                }
                if (u instanceof Administrateur) {

                }

            }

        } catch (IOException ex) {

        }
    }

}
