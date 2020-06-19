/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.controllers;

import edu.worldaid.entities.CasSocial;
import edu.worldaid.entities.feedback;
import edu.worldaid.services.UserCrud;
import edu.worldaid.services.feedbackCRUD;
import edu.worldaid.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ButtonType;
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
public class Feedbacks_cas_socialController implements Initializable {

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

    Connection cn2;
    Statement st;
    private ResultSet rs = null;

    /**
     *
     */
    public ObservableList<feedback> data = FXCollections.observableArrayList();

    @FXML
    private TableView<feedback> TableFeedbacks;
    @FXML
    private TableColumn<feedback, Integer> ColumnID;
    @FXML
    private TableColumn<feedback, String> ColumnTitre;
    @FXML
    private TableColumn<feedback, String> ColumnMessage;
    @FXML
    private TableColumn<feedback, Integer> ColumnEtat;
    @FXML
    private Label lbTitre;
    @FXML
    private ComboBox<String> txtTitre;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private TextField txtSearch;
    private TextArea txtDescription;
    @FXML
    private Label lbMessage;
    @FXML
    private TextArea txtMessage;
    @FXML
    private Button Exit;
    feedbackCRUD fcr;
    @FXML
    private Button Request2;
    @FXML
    private Button Home5;
    @FXML
    private Button logoutbutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fcr = new feedbackCRUD();
        username.setText(fcr.getConnectedUser().getUserName());
        //username.setText(fcr.getConnectedUser().getUserName());
        cn2 = MyConnection.getInstance().getCnx();
        setCellTable();
        loadDataFromDatabase(fcr.getConnectedUser().getId());
        searchDemande();
        txtTitre.getItems().removeAll(txtTitre.getItems());
        txtTitre.getItems().addAll("Admins", "Members", "Bugs", "Application operation");
    }

   @FXML
   private void Ajouter(ActionEvent event) throws SQLException {

        if (txtTitre.getSelectionModel().getSelectedItem() == "" || txtMessage.getText().equals("")) {
            alert1("Fill in all the fields please!");
        } else {
            feedbackCRUD fcr = new feedbackCRUD();
            CasSocial cs = (CasSocial) fcr.getConnectedUser();
            cn2 = MyConnection.getInstance().getCnx();
            String requete2 = "INSERT INTO feedback (titre,message,id_cassocial) VALUES (?,?,?)";
            PreparedStatement pst = cn2.prepareStatement(requete2);

            pst.setString(1, txtTitre.getSelectionModel().getSelectedItem());
            pst.setString(2, txtMessage.getText());
            pst.setInt(3, cs.getId());
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add feedback");
            alert.setHeaderText(null);
            alert.setContentText("Feedback added");
            alert.showAndWait();
            txtTitre.setValue("");
            txtMessage.setText("");
            data.clear();
            loadDataFromDatabase(fcr.getConnectedUser().getId());
        }
    }

   @FXML
    public void Supprimer(ActionEvent event) throws SQLException {

        if (TableFeedbacks.getSelectionModel().getSelectedItem() == null) {
            alert1("PLEASE SELECT THE FEEDBACK THAT YOU WANT TO DELETE");
            //
        } else {
            if(TableFeedbacks.getSelectionModel().getSelectedItem().getEtat()==1){
                alert1("Your feedback is already checked by administrators!");
            }
            else{
                try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("test");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure to delete ?");

                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    cn2 = MyConnection.getInstance().getCnx();
                    String requete2 = "DELETE FROM feedback WHERE id_feedback=? ";
                    PreparedStatement pst2 = cn2.prepareStatement(requete2);

                    pst2.setInt(1, TableFeedbacks.getSelectionModel().getSelectedItem().getId_feedback());
                    pst2.executeUpdate();
                    // txtTitre.getSelectionModel().getSelectedItem("");
                    txtMessage.clear();

                    Alert alertSuppr = new Alert(Alert.AlertType.INFORMATION);
                    alertSuppr.setTitle("Feedback deletion");
                    alertSuppr.setHeaderText(null);
                    alertSuppr.setContentText("Feedback deleted!");
                    alertSuppr.showAndWait();
                    data.clear();
                    loadDataFromDatabase(fcr.getConnectedUser().getId());
                } else if (action.get() == ButtonType.CANCEL) {
                    alert.close();

                }
            } catch (SQLException ex) {
                System.out.println("Erreur lors de la mise à jour de la demande " + ex.getMessage());
            }
            data.clear();
            loadDataFromDatabase(fcr.getConnectedUser().getId());
            }
        }

    }

    private void setCellTable() {

        ColumnID.setCellValueFactory(new PropertyValueFactory<>("id_feedback"));
        ColumnTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        ColumnMessage.setCellValueFactory(new PropertyValueFactory<>("message"));
        ColumnEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));

    }

    private void loadDataFromDatabase(int id_cassocial) {
        try {
            cn2 = MyConnection.getInstance().getCnx();
            String requete2 = "SELECT id_feedback,titre,message,etat FROM feedback WHERE id_cassocial=?";
            PreparedStatement pst2 = cn2.prepareStatement(requete2);
            pst2.setInt(1, id_cassocial);
            rs = pst2.executeQuery();
            while (rs.next()) {
                data.add(new feedback(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
        } catch (SQLException ex) {
System.out.println(ex.getMessage());        }

        TableFeedbacks.setItems(data);
    }

    public void searchDemande() {
        txtSearch.setOnKeyReleased(e -> {
            if (txtSearch.getText().equals("")) {
                data.clear();
                loadDataFromDatabase(fcr.getConnectedUser().getId());
            } else {
                data.clear();
                String sql = "SELECT * FROM feedback WHERE id_feedback LIKE '%" + txtSearch.getText() + "%'"
                        + "UNION SELECT * FROM feedback WHERE titre LIKE '%" + txtSearch.getText() + "%'"
                        + "UNION SELECT * FROM feedback WHERE message LIKE '%" + txtSearch.getText() + "%'";

                try {
                    PreparedStatement pst = cn2.prepareStatement(sql);

                    rs = pst.executeQuery();
                    while (rs.next()) {

                        data.add(new feedback(rs.getInt(1), rs.getString(2), rs.getString(3)));

                    }
                    TableFeedbacks.setItems(data);

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        });
    }


    private void alert1(String Message) {
        Alert a1 = new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Alert");
        a1.setHeaderText("Select the row");
        a1.setContentText(Message);
        a1.showAndWait();

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
    private void update(ActionEvent event) {
        if (TableFeedbacks.getSelectionModel().getSelectedItem() == null) {
            alert1("PLEASE SELECT THE FEEDBACK THAT YOU WANT TO UPDATE");
            //
        } else {
            if (txtTitre.getSelectionModel().getSelectedItem() == "" || txtMessage.getText().equals("")) {
                alert1("Fill in all the fields please!");
            } else {
                if (TableFeedbacks.getSelectionModel().getSelectedItem().getEtat() == 0) {
                    try {
                        cn2 = MyConnection.getInstance().getCnx();
                        String requete = "UPDATE feedback SET titre=?,message=? WHERE id_feedback=?";
                        PreparedStatement pst2 = cn2.prepareStatement(requete);

                        pst2.setString(1, txtTitre.getSelectionModel().getSelectedItem());
                        pst2.setString(2, txtMessage.getText());
                        pst2.setInt(3, TableFeedbacks.getSelectionModel().getSelectedItem().getId_feedback());
                        pst2.executeUpdate();

                        //System.out.println("Mise à jour du feedback effectuée avec succès");
                        alert("Feedback updated");
                    } catch (SQLException ex) {
                        System.out.println("Erreur lors de la mise à jour de la demande " + ex.getMessage());
                    }

                    data.clear();
                    loadDataFromDatabase(fcr.getConnectedUser().getId());
                } else {
                    alert1("ERROR! Your feedback is already viewed by admins!");
                }
            }
        }
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
