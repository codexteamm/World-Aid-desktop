/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.controllers;

import edu.worldaid.entities.CasSocial;
import edu.worldaid.services.UserCrud;
import edu.worldaid.services.demande_aideCRUD;
import edu.worldaid.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author HP
 */
public class Home_cas_social2Controller implements Initializable {

    @FXML
    private Label type;
    @FXML
    private Button DeleteOpp1;
    @FXML
    private ImageView Profile_pic;
    @FXML
    private Label username;
    @FXML
    private ImageView pic1;
    @FXML
    private Hyperlink help;
    @FXML
    private Hyperlink stat;
    @FXML
    private ListView<?> List;
    @FXML
    private ImageView muteicon;
    @FXML
    private Button mute;
    private Button btnExit;

    Connection cn2;
    Statement st;
    @FXML
    private Button Request;
    @FXML
    private Button Exit;
    @FXML
    private Button Feedback;
    @FXML
    private Button logoutbutton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        demande_aideCRUD dcr2=new demande_aideCRUD();
        username.setText(dcr2.getConnectedUser().getUserName());
            cn2 = MyConnection.getInstance().getCnx();
    }    

    @FXML
    private void showtab(ActionEvent event) {
    }






    @FXML
    private void gotohelp(ActionEvent event) {
    }

    @FXML
    private void DisplayStatistics(ActionEvent event) {
    }

    @FXML
    private void mute(ActionEvent event) {
    }

    @FXML
    private void Exit(ActionEvent event) {
        Stage st= (Stage) Exit.getScene().getWindow();
        st.close();
    }

    @FXML
    private void ToRequest(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/Requests_cas_social2.fxml"));
            Parent root = loader.load();
            Requests_cas_social2Controller dpc = loader.getController();
            Exit.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Requests_cas_social2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void ToAssociations(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/Associations_cas_social2.fxml"));
            Parent root = loader.load();
            Associations_cas_social2Controller dpc = loader.getController();
            Exit.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Associations_cas_social2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void toFeedback(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/Feedback_cas_social.fxml"));
            Parent root = loader.load();
            Feedbacks_cas_socialController dpc = loader.getController();
            Exit.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void logout(ActionEvent event) {
          try {
            UserCrud uc = new UserCrud();
            uc.logout();

            Parent intDM = FXMLLoader.load(getClass().getResource("/edu/worldaid/gui/FXMLFirst.fxml"));
            Scene intDmScene = new Scene(intDM);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(intDmScene);
            window.show();

        } catch (IOException ex) {

            System.out.println("erreur logout"+ex.getMessage());

        }
    }
    
    
}
