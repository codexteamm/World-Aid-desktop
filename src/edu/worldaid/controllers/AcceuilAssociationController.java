/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.controllers;

import edu.worldaid.entities.Association;
import edu.worldaid.entities.Evenement;
import edu.worldaid.services.UserCrud;
import edu.worldaid.services.evenementCRUD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AcceuilAssociationController implements Initializable {

    private ObservableList<Evenement> data;
    @FXML
    private Label type;
    @FXML
    private Button Add_Event2;
    @FXML
    private Button supportedCamps;
    @FXML
    private Button Add_Camps;
    @FXML
    private Button setting;

    public String getType() {
        return type.getText();
    }

    public void setType(String type) {
        this.type.setText(type);
    }
    @FXML
    private Label username;

    public String getUsername() {
        return username.getText();
    }

    public void setUsername(String username) {
        this.username.setText(username);
    }
    @FXML
    private ImageView pic1;
    @FXML
    private Button show_event;
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
    @FXML
    private Button add_and_event;
    @FXML
    private Label eventnbr;
    evenementCRUD S = new evenementCRUD();
    int f;
    UserCrud uc = new UserCrud();

    @FXML
    private Button showAllEvents;
    @FXML
    private Label allEventnbr;
    int g;
    @FXML
    private ImageView Profile_pic;

    //  int mute = 0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getConnectedUserName();
        // int mute = 1;
        //PlayNotification("hello.mp3");

        data = S.DisplayMy_Events();

        f = S.CompterMyEvent();
        eventnbr.setText("" + f);

        g = S.CompterEvent();
        allEventnbr.setText("" + g);

    }

    public void getConnectedUserName() {
        UserCrud cc = new UserCrud();
        Association ass = (Association) cc.getConnectedUser();
        setUsername(ass.getNomAssociaiton());
    }

    @FXML
    private void Show_Home(ActionEvent event) {
    }

    @FXML
    private void Add_an_event(ActionEvent event) {
        try {
            FXMLLoader detail = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/Add_Events.fxml"));
            // System.out.println("err1");
            Parent root2 = (Parent) detail.load();
            //System.out.println("err2");
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root2));
            //System.out.println("err3");
            stage1.setOnCloseRequest(e -> Platform.exit());

            stage1.show();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void ShowEvents(ActionEvent event) throws IOException {
        try {
            FXMLLoader detail = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/displayEvents.fxml"));
            Parent root2 = (Parent) detail.load();
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root2));
            stage1.setOnCloseRequest(e -> Platform.exit());
            stage1.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    @FXML
    private void logoutbutton(ActionEvent event) {
    }

    @FXML
    private void gotohelp(ActionEvent event) {
        {
            try {
                Parent tableViewOpportunity = FXMLLoader.load(getClass().getResource("/edu/worldaid/gui/helpCenter.fxml"));
                Scene tableViewOpportunityScene = new Scene(tableViewOpportunity);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(tableViewOpportunityScene);
            } catch (IOException ex) {
                Logger.getLogger(AcceuilAssociationController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void DisplayStatistics(ActionEvent event) {
    }

    @FXML
    private void mute(ActionEvent event) {

    }
/*
    public static void PlayNotification(String fileNotif) {
        String musicFile = "\\c:\\sounds\\" + fileNotif;     // For example

        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

    }
*/
    @FXML
    private void showAllEvents(ActionEvent event) {
        try {

            FXMLLoader detail = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/displayAllEvents.fxml"));

            Parent root2 = (Parent) detail.load();
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root2));
            stage1.setOnCloseRequest(e -> Platform.exit());
            stage1.show();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    private void Add_an_opportunity(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/displayCamps.fxml"));
            Parent root = loader.load();
            DisplaycampsController dpc = loader.getController();

            Add_Event2.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    @FXML
    private void showSupportedCamps(ActionEvent event) {
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/showSupportedcamps.fxml"));
            Parent root = loader.load();
            SowSupportedController dpc = loader.getController();

            Add_Event2.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    @FXML
    private void Add_Camps(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/Add_Campement.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DisplaycampsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void setting(ActionEvent event) {
    }

}
