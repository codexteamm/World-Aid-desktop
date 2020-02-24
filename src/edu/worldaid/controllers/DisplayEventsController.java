/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.controllers;

//import static edu.worldaid.controllers.DisplayEventsController.PlayNotification;
import edu.worldaid.entities.Evenement;
import edu.worldaid.services.evenementCRUD;
import edu.worldaid.utils.MyConnection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DisplayEventsController implements Initializable {

    private ObservableList<Evenement> data;
    @FXML
    private AnchorPane parent;
    private Label Nombre_Opp;
    @FXML
    private TextField txtField;

    @FXML
    private TableView<Evenement> table = new TableView<>();

    @FXML
    private Button displayopp;
    @FXML
    private TableColumn<Evenement, Integer> colid_event;
    @FXML
    private TableColumn<Evenement, Integer> colid_association;
    @FXML
    private TableColumn<Evenement, String> colevent_name;
    @FXML
    private TableColumn<Evenement, Date> colstart_date;
    @FXML
    private TableColumn<Evenement, Date> colexpary_date;
    @FXML
    private TableColumn<Evenement, Double> collongitude;
    @FXML
    private TableColumn<Evenement, Double> collatitude;
    @FXML
    private TableColumn<Evenement, String> colevent_description;
    @FXML
    private TableColumn<Evenement, String> colevent_category;
    @FXML
    private Button DeleteEvent;
    @FXML
    private Button update;
    @FXML
    private Label Nombre_event;
    int x;
    evenementCRUD S = new evenementCRUD();
    @FXML
    private Button cancel;
    @FXML
    private Button reload1;
    private MyConnection con;
    evenementCRUD US;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = MyConnection.getInstance();

        reload();
        initFilter();
        data = S.DisplayMy_Events();
        x = S.CompterMyEvent();
        Nombre_event.setText("" + x);
    }

    private void initFilter() {

        txtField.setPromptText("Filter");

        txtField.textProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable o) {

                if (txtField.textProperty().get().isEmpty()) {

                    table.setItems(data);

                    return;

                }

                ObservableList<Evenement> tableItems = FXCollections.observableArrayList();

                ObservableList<TableColumn<Evenement, ?>> cols = table.getColumns();

                for (int i = 0; i < data.size(); i++) {

                    for (int j = 0; j < cols.size(); j++) {

                        TableColumn col = cols.get(j);

                        String cellValue = col.getCellData(data.get(i)).toString();

                        cellValue = cellValue.toLowerCase();

                        if (cellValue.contains(txtField.textProperty().get().toLowerCase())) {

                            tableItems.add(data.get(i));

                            break;

                        }

                    }

                }

                table.setItems(tableItems);

            }

        });

    }

    private void reload() {
        evenementCRUD ec = new evenementCRUD();

        data = ec.DisplayMy_Events();

        colid_event.setCellValueFactory(new PropertyValueFactory<>("id_event"));
        colid_association.setCellValueFactory(new PropertyValueFactory<>("id_association"));
        colevent_name.setCellValueFactory(new PropertyValueFactory<>("nom_event"));
        colstart_date.setCellValueFactory(new PropertyValueFactory<>("date_debut_event"));
        colexpary_date.setCellValueFactory(new PropertyValueFactory<>("date_fin_event"));
        collongitude.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        collatitude.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        colevent_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        colevent_category.setCellValueFactory(new PropertyValueFactory<>("categorie"));

        colid_event.setSortType(TableColumn.SortType.DESCENDING);
        colid_association.setSortType(TableColumn.SortType.DESCENDING);
        colevent_name.setSortType(TableColumn.SortType.DESCENDING);
        colstart_date.setSortType(TableColumn.SortType.DESCENDING);
        colexpary_date.setSortType(TableColumn.SortType.DESCENDING);
        collongitude.setSortType(TableColumn.SortType.DESCENDING);
        collatitude.setSortType(TableColumn.SortType.DESCENDING);
        colevent_description.setSortType(TableColumn.SortType.DESCENDING);
        colevent_category.setSortType(TableColumn.SortType.DESCENDING);
        System.out.println(data);
        table.setItems(data);

    }

    private void Show_Home(ActionEvent event) {
        try {
            Parent tableViewOpportunity = FXMLLoader.load(getClass().getResource("/edu/worldaid/gui/AcceuilAssociation.fxml"));
            Scene tableViewOpportunityScene = new Scene(tableViewOpportunity);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewOpportunityScene);
            window.setOnCloseRequest(e -> Platform.exit());

          //  PlayNotification("home.mp3");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void Add_an_opportunity(ActionEvent event) {
        try {
            FXMLLoader detail = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/Add_Events.fxml"));
            Parent root2 = (Parent) detail.load();
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root2));
            stage1.setOnCloseRequest(e -> Platform.exit());

            stage1.show();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void displayDetails(ActionEvent event) {
        if (table.getSelectionModel().getSelectedItem() == null) {
            alert1("PLEASE SELECT THE EVENT THAT YOU WANT TO SEE IN DETAILS");
            //
        } else {

            try {
                FXMLLoader detail = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/DetailEventGui.fxml"));
                Parent root1 = (Parent) detail.load();
                Stage stage = new Stage();
                DetailEventGuiController p = detail.getController();

                System.out.println(table.getSelectionModel().getSelectedItem());
                stage.setScene(new Scene(root1));
                stage.show();
                p.AfficherDetails(table.getSelectionModel().getSelectedItem());
            } catch (IOException ex) {
                Logger.getLogger(DisplayEventsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void RemoveEvent(ActionEvent event) {
        if (table.getSelectionModel().getSelectedItem() == null) {
            alert1("PLEASE SELECT THE EVENT THAT YOU WANT TO DELETE");
            //
        } else {
            if (alert1Confirmation() == true) {
                int id_opp = 0;
                ObservableList<Evenement> AllOp, SingleOp;
                AllOp = table.getItems();
                SingleOp = table.getSelectionModel().getSelectedItems();
                evenementCRUD s = new evenementCRUD();

                table.getSelectionModel().getSelectedItem();
                System.out.println("Value is in this row which" + table.getSelectionModel().getSelectedItem().getId_event());

                s.supprimerEvenement(table.getSelectionModel().getSelectedItem().getId_event());
                SingleOp.forEach(AllOp::remove);
//                int i = s.CompterEvent();
//                Nombre_Opp.setText("" + i + "");
                alert2("EVENT DELETED");
               // PlayNotification("deleted.mp3");

            }
        }
    }

    private void alert1(String Message) {
        Alert a1 = new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Alert");
        a1.setHeaderText("Select the row");
        a1.setContentText(Message);
        a1.showAndWait();

    }
/*
    public static void PlayNotification(String fileNotif) {
        String musicFile = "\\c:\\sounds\\" + fileNotif;     // For example

        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

    }
*/
    private boolean alert1Confirmation() {
        Alert a1 = new Alert(Alert.AlertType.CONFIRMATION);
        a1.setTitle("CONFIRMATION DIALOG");
        a1.setHeaderText("SUPPRESSION CONFIRMATION");
        a1.setContentText("ARE YOU SURE THAT YOU WANT TO DELETE THIS EVENT ?");

       // PlayNotification("sure.mp3");

        Optional<ButtonType> result = a1.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }

    }

    private void alert2(String Message) {
        Alert a1 = new Alert(Alert.AlertType.INFORMATION);
        a1.setTitle("INFORMATION");
        a1.setHeaderText("");
        a1.setContentText(Message);
        a1.showAndWait();

    }

    @FXML
    private void update(ActionEvent event) {
        if (table.getSelectionModel().getSelectedItem() == null) {
            alert1("PLEASE SELECT THE EVENT THAT YOU WANT TO UPDATE");
            //
        } else {

            try {
                FXMLLoader detail = new FXMLLoader(getClass().getResource("/edu/worldaid/gui/UpdateEvent.fxml"));
                Parent root1 = (Parent) detail.load();
                Stage stage = new Stage();
                UpdateEventController p = detail.getController();

                System.out.println(table.getSelectionModel().getSelectedItem());
                stage.setScene(new Scene(root1));
                stage.show();
                p.AfficherDetailsUpdate(table.getSelectionModel().getSelectedItem());
            } catch (IOException ex) {
                Logger.getLogger(DisplayEventsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void annuler(ActionEvent event) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
        Parent root2;

        Stage stage1 = new Stage();
//        PlayNotification("home.mp3");

    }

    @FXML
    private void reload1(ActionEvent event) {
        evenementCRUD ec = new evenementCRUD();

        data = ec.DisplayMy_Events();

        colid_event.setCellValueFactory(new PropertyValueFactory<>("id_event"));
        colid_association.setCellValueFactory(new PropertyValueFactory<>("id_association"));
        colevent_name.setCellValueFactory(new PropertyValueFactory<>("nom_event"));
        colstart_date.setCellValueFactory(new PropertyValueFactory<>("date_debut_event"));
        colexpary_date.setCellValueFactory(new PropertyValueFactory<>("date_fin_event"));
        collongitude.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        collatitude.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        colevent_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        colevent_category.setCellValueFactory(new PropertyValueFactory<>("categorie"));

        colid_event.setSortType(TableColumn.SortType.DESCENDING);
        colid_association.setSortType(TableColumn.SortType.DESCENDING);
        colevent_name.setSortType(TableColumn.SortType.DESCENDING);
        colstart_date.setSortType(TableColumn.SortType.DESCENDING);
        colexpary_date.setSortType(TableColumn.SortType.DESCENDING);
        collongitude.setSortType(TableColumn.SortType.DESCENDING);
        collatitude.setSortType(TableColumn.SortType.DESCENDING);
        colevent_description.setSortType(TableColumn.SortType.DESCENDING);
        colevent_category.setSortType(TableColumn.SortType.DESCENDING);
        System.out.println(data);
        table.setItems(data);
        int i = ec.CompterMyEvent();
        Nombre_event.setText("" + i + "");

    }
}
