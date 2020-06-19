/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.controllers;

import edu.worldaid.entities.Association;
import edu.worldaid.entities.User;
import edu.worldaid.entities.demande_aide;
import edu.worldaid.entities.feedback;
import edu.worldaid.services.demande_aideCRUD;
import edu.worldaid.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Associations_cas_social2Controller implements Initializable {

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
    private Label applicationnbr;
    @FXML
    private Button Drafts;
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
    private Button Exit;
    @FXML
    private Button Home2;
    @FXML
    private Button Requests;

    /**
     * Initializes the controller class.
     */
    
    Connection cn2;
    Statement st;
    private ResultSet rs = null;
    demande_aideCRUD dcr;
    
    public ObservableList<demande_aide> data = FXCollections.observableArrayList();
    @FXML
    private TableView<demande_aide> TableAssociations;
    @FXML
    private TableColumn<demande_aide, Integer> ColumnID;
    @FXML
    private TableColumn<demande_aide, String> ColumnTitle;
    @FXML
    private TableColumn<demande_aide, String> ColumnDescription;
    @FXML
    private TableColumn<demande_aide, Integer> ColumState;
    @FXML
    private Button feedback2;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dcr =new demande_aideCRUD();
        cn2 = MyConnection.getInstance().getCnx();
        username.setText(dcr.getConnectedUser().getUserName());
        setCellTable();
        loadDataFromDatabase();
    }    

    @FXML
    private void Home(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/Home_cas_social2.fxml"));
            Parent root = loader.load();
            Home_cas_social2Controller dpc = loader.getController();
            Exit.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Home_cas_social2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ToRequests(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/Requests_cas_social2.fxml"));
            Parent root = loader.load();
            Requests_cas_social2Controller dpc = loader.getController();
            Exit.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Requests_cas_social2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    private void setCellTable() {
        //demande_aideCRUD dcr = demande_aideCRUD();
        ColumnID.setCellValueFactory(new PropertyValueFactory<>("id_demande"));
        ColumnTitle.setCellValueFactory(new PropertyValueFactory<>("titre"));
        ColumnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        ColumState.setCellValueFactory(new PropertyValueFactory<>("etat"));

    }

    private void loadDataFromDatabase() {
        try {
            cn2 = MyConnection.getInstance().getCnx();
            String requete2 = "SELECT * FROM demande_aide";
            PreparedStatement pst2 = cn2.prepareStatement(requete2);
            rs = pst2.executeQuery();
            while (rs.next()) {
                data.add(new demande_aide(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Associations_cas_social2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        TableAssociations.setItems(data);
    }

    @FXML
    private void toFeedback(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/Feedbacks_cas_social2.fxml"));
            Parent root = loader.load();
            Feedbacks_cas_socialController dpc = loader.getController();
            Exit.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
