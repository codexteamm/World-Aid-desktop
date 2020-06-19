/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.controllers;

import com.lowagie.text.DocumentException;
import edu.worldaid.entities.CasSocial;
import edu.worldaid.services.UserCrud;
import edu.worldaid.services.demande_aideCRUD;
import edu.worldaid.entities.demande_aide;
import edu.worldaid.utils.MyConnection;
import edu.worldaid.utils.PDFutil;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
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
import javafx.print.PrinterJob;
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
public class Requests_cas_social2Controller implements Initializable {

    @FXML
    private Label lbTitre;
    @FXML
    private ComboBox<String> txtTitre;
    @FXML
    private TextArea txtDescription;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;

    Connection cn2;
    Statement st;
    private ResultSet rs = null;

    /**
     *
     */
    public ObservableList<demande_aide> data = FXCollections.observableArrayList();

    @FXML
    private TableView<demande_aide> TableDemandes;
    @FXML
    private TableColumn<demande_aide, Integer> ColumnID;
    @FXML
    private TableColumn<demande_aide, String> ColumnTitre;
    @FXML
    private TableColumn<demande_aide, String> ColumnDescription;
    @FXML
    private TableColumn<demande_aide, Integer> ColumnEtat;
    @FXML
    private TextField txtSearch;
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
    @FXML
    private Button Exit;
    @FXML
    private Button Home2;
    @FXML
    private Button btnPrint;
    demande_aideCRUD dcr;
    @FXML
    private Button feedback2;
    @FXML
    private Button logoutbutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         dcr = new demande_aideCRUD();
        username.setText(dcr.getConnectedUser().getUserName());
        cn2 = MyConnection.getInstance().getCnx();
        setCellTable();
        loadDataFromDatabase(dcr.getConnectedUser().getId());
        searchDemande();
        txtTitre.getItems().removeAll(txtTitre.getItems());
        txtTitre.getItems().addAll("Food","Money","Accomodation","Clothes");
    }

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {

        if(txtTitre.getSelectionModel().getSelectedItem()=="" || txtDescription.getText().equals("")){
            alert2("Fill in the fields please!");
        }
        else{
            demande_aideCRUD dcr = new demande_aideCRUD();
        CasSocial cs = (CasSocial) dcr.getConnectedUser();
        cn2 = MyConnection.getInstance().getCnx();
        String requete2 = "INSERT INTO demande_aide (titre,description,id_cassocial) VALUES (?,?,?)";
        PreparedStatement pst = cn2.prepareStatement(requete2);
        pst.setString(1, txtTitre.getSelectionModel().getSelectedItem());
        pst.setString(2, txtDescription.getText());
        pst.setInt(3, cs.getId());
        pst.executeUpdate();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Request adding");
        alert.setHeaderText(null);
        alert.setContentText("Request added");
        txtTitre.setValue("");
        txtDescription.setText("");
        alert.showAndWait();
        data.clear();
        loadDataFromDatabase(dcr.getConnectedUser().getId());
        }

    }

    @FXML
    public void Supprimer(ActionEvent event) throws SQLException {

        if (TableDemandes.getSelectionModel().getSelectedItem() == null) {
            alert2("PLEASE SELECT THE REQUEST THAT YOU WANT TO DELETE");
            //
        } else {
            if(TableDemandes.getSelectionModel().getSelectedItem().getEtat()==1){
                alert2("You request is already checked by administrators!");
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
                    String requete2 = "DELETE FROM demande_aide WHERE id_demande=? ";
                    PreparedStatement pst2 = cn2.prepareStatement(requete2);

                    pst2.setInt(1, TableDemandes.getSelectionModel().getSelectedItem().getId_demande());
                    pst2.executeUpdate();
                    //txtTitre.clear();
                    txtDescription.clear();

                    Alert alertSuppr = new Alert(Alert.AlertType.INFORMATION);
                    alertSuppr.setTitle("Request deletion");
                    alertSuppr.setHeaderText(null);
                    alertSuppr.setContentText("Request deleted!");
                    alertSuppr.showAndWait();
                    data.clear();
                    loadDataFromDatabase(dcr.getConnectedUser().getId());
                } else if (action.get() == ButtonType.CANCEL) {
                    alert.close();

                }
            } catch (SQLException ex) {
                System.out.println("Erreur lors de la mise à jour de la demande " + ex.getMessage());
            }
            data.clear();
            loadDataFromDatabase(dcr.getConnectedUser().getId());
            }
        }
    }

    private void setCellTable() {
        //demande_aideCRUD dcr = demande_aideCRUD();
        ColumnID.setCellValueFactory(new PropertyValueFactory<>("id_demande"));
        ColumnTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        ColumnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        ColumnEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));

    }

    private void loadDataFromDatabase(int idcassocial) {
        try {
            cn2 = MyConnection.getInstance().getCnx();
            String requete2 = "SELECT * FROM demande_aide WHERE id_cassocial= ?";
            PreparedStatement pst2 = cn2.prepareStatement(requete2);
            pst2.setInt(1, idcassocial);
            rs = pst2.executeQuery();
            while (rs.next()) {
                data.add(new demande_aide(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Requests_cas_social2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        TableDemandes.setItems(data);
    }

    public void searchDemande() {
        txtSearch.setOnKeyReleased(e -> {
            if (txtSearch.getText().equals("")) {
                data.clear();
                loadDataFromDatabase(dcr.getConnectedUser().getId());
            } else {
                data.clear();
                String sql = "SELECT * FROM demande_aide WHERE id_demande LIKE '%" + txtSearch.getText() + "%'"
                        + "UNION SELECT * FROM demande_aide WHERE titre LIKE '%" + txtSearch.getText() + "%'"
                        + "UNION SELECT * FROM demande_aide WHERE etat LIKE '%" + txtSearch.getText() + "%'"
                        + "UNION SELECT * FROM demande_aide WHERE description LIKE '%" + txtSearch.getText() + "%'";

                try {
                    PreparedStatement pst = cn2.prepareStatement(sql);
                    //pst.setString(1, txtSearch.getText());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        //System.out.println("" + rs.getString(2));
                        data.add(new demande_aide(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));

                    }
                    TableDemandes.setItems(data);

                } catch (SQLException ex) {
                    Logger.getLogger(Requests_cas_social2Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
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

    private void alert1(String Message) {
        Alert a1 = new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Alert");
        a1.setHeaderText("Select the row");
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
    public void update(ActionEvent event) {
        if (TableDemandes.getSelectionModel().getSelectedItem() == null) {
            alert2("PLEASE SELECT THE REQUEST THAT YOU WANT TO UPDATE");
            //
        } else {
            if(txtTitre.getSelectionModel().getSelectedItem()=="" || txtDescription.getText().equals("")){
                alert2("Fill in the fields please!");
            }
            else{
                if(TableDemandes.getSelectionModel().getSelectedItem().getEtat()==0){
                try {
                cn2 = MyConnection.getInstance().getCnx();
                String requete = "UPDATE demande_aide SET titre=?,description=? WHERE id_demande=?";
                PreparedStatement pst2 = cn2.prepareStatement(requete);

                pst2.setString(1, txtTitre.getSelectionModel().getSelectedItem());
                pst2.setString(2, txtDescription.getText());
                pst2.setInt(3, TableDemandes.getSelectionModel().getSelectedItem().getId_demande());
                pst2.executeUpdate();
                //System.out.println("Mise à jour de la demande d'aide effectuée avec succès");
                alert1("Request updated");
            } catch (SQLException ex) {
                System.out.println("Erreur lors de la mise à jour de la demande " + ex.getMessage());
            }
            //txtTitre.setText(TableDemandes.getSelectionModel().getSelectedItem().getTitre());
            //txtDescription.setText(TableDemandes.getSelectionModel().getSelectedItem().getDescription());
            data.clear();
            loadDataFromDatabase(dcr.getConnectedUser().getId());
            }
            else {
                alert1("ERROR!Your request is already viewed by administrators and is in treatment phase!");
            }
            }
        }
    }

    

    @FXML
    public void GenerateDemandePdf() throws SQLException, IOException, FileNotFoundException {
        PDFutil pdf = new PDFutil();

        try {
            pdf.listDemande();
        } catch (DocumentException ex) {
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
System.out.println(ex.getMessage());        }
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
