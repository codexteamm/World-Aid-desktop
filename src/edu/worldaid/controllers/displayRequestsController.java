/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.controllers;

import edu.worldaid.entities.demande_aide;
import edu.worldaid.services.UserCrud;
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
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class displayRequestsController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private WebView webView;
    @FXML
    private VBox menu;
    @FXML
    private ImageView Profile_pic;
    @FXML
    private Label username;
    @FXML
    private Button Home;
    @FXML
    private Button add_and_event;
    @FXML
    private Button show_event;
    @FXML
    private Button showAllEvents;
    @FXML
    private Button Add_Event2;
    @FXML
    private ImageView pic12;
    @FXML
    private Button supportedCamps;
    @FXML
    private ImageView pic121;
    @FXML
    private Button Add_Camps;
    @FXML
    private ImageView pic1;
    @FXML
    private Button settings;
    @FXML
    private Hyperlink help;
    @FXML
    private TableView<demande_aide> TablesDemandes;
    @FXML
    private TableColumn<demande_aide, Integer> ColumnID;
    @FXML
    private TableColumn<demande_aide, String> ColumnTitle;
    @FXML
    private TableColumn<demande_aide, String> ColumnDescription;
    @FXML
    private TableColumn<demande_aide, Integer> ColumnState;

    /**
     * Initializes the controller class.
     */
    Connection cn2;
    Statement st;
    private ResultSet rs = null;
    demande_aideCRUD dcr;

    public ObservableList<demande_aide> data = FXCollections.observableArrayList();
    @FXML
    private Button btnTakeCharge;
    @FXML
    private Button Requests;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dcr = new demande_aideCRUD();
        cn2 = MyConnection.getInstance().getCnx();
        username.setText(dcr.getConnectedUser().getUserName());
        setCellTable();
        loadDataFromDatabase();
    }

    private void setCellTable() {
        //demande_aideCRUD dcr = demande_aideCRUD();
        ColumnID.setCellValueFactory(new PropertyValueFactory<>("id_demande"));
        ColumnTitle.setCellValueFactory(new PropertyValueFactory<>("titre"));
        ColumnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        ColumnState.setCellValueFactory(new PropertyValueFactory<>("etat"));

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

        TablesDemandes.setItems(data);
    }

    @FXML
    private void Show_Home(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/AcceuilAssociation.fxml"));
            Parent root = loader.load();
            AcceuilAssociationController dpc = loader.getController();

            Home.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(DisplaycampsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void add_an_event(ActionEvent event) {
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
    private void ShowEvents(ActionEvent event) {
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
    private void Add_Camp(ActionEvent event) {
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
    private void showq(ActionEvent event) {
    }

    @FXML
    private void logoutbutton(ActionEvent event) {
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

    @FXML
    private void gotohelp(ActionEvent event) {
         try {
                Parent tableViewOpportunity = FXMLLoader.load(getClass().getResource("/edu/worldaid/gui/helpCenter.fxml"));
                Scene tableViewOpportunityScene = new Scene(tableViewOpportunity);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(tableViewOpportunityScene);
            } catch (IOException ex) {
                Logger.getLogger(AcceuilAssociationController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }



    private void alert(String Message) {
        Alert a1 = new Alert(Alert.AlertType.INFORMATION);
        a1.setTitle("Alert");
        a1.setHeaderText("Done");
        a1.setContentText(Message);
        a1.showAndWait();

    }

    private void alert2(String Message) {
        Alert a1 = new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Alert");
        a1.setHeaderText("ERROR");
        a1.setContentText(Message);
        a1.showAndWait();

    }

    @FXML
    private void takeCharge(ActionEvent event) {
        if (TablesDemandes.getSelectionModel().getSelectedItem() == null) {
            alert2("PLEASE SELECT THE REQUEST THAT YOU WANT TO TAKE IN CHARGE");
            //
        } else {

            if (TablesDemandes.getSelectionModel().getSelectedItem().getEtat() == 0) {
                try {
                    cn2 = MyConnection.getInstance().getCnx();
                    String requete = "UPDATE demande_aide SET etat=1 WHERE id_demande=?";
                    PreparedStatement pst2 = cn2.prepareStatement(requete);

                    pst2.setInt(1, TablesDemandes.getSelectionModel().getSelectedItem().getId_demande());
                    pst2.executeUpdate();
                    //System.out.println("Mise à jour de la demande d'aide effectuée avec succès");
                    alert("Request taken in charge");
                } catch (SQLException ex) {
                    System.out.println("Erreur lors de la mise à jour de la demande " + ex.getMessage());
                }
                //txtTitre.setText(TableDemandes.getSelectionModel().getSelectedItem().getTitre());
                //txtDescription.setText(TableDemandes.getSelectionModel().getSelectedItem().getDescription());
                data.clear();
                loadDataFromDatabase();
            } 
            else{
                alert2("This request is already taken in charge");
            }

        }
    }

    @FXML
    private void Requests(ActionEvent event) {
    }

}
