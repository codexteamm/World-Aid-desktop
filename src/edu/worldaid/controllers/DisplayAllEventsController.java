/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.controllers;

import edu.worldaid.entities.Evenement;
import edu.worldaid.services.evenementCRUD;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DisplayAllEventsController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private Label Nombre_Opp;
    @FXML
    private TextField txtField;
    @FXML
    private TableView<Evenement> table = new TableView<>();
    @FXML
    private TableColumn<?, ?> colid_event;
    @FXML
    private TableColumn<?, ?> colid_association;
    @FXML
    private TableColumn<?, ?> colnom_event;
    @FXML
    private TableColumn<?, ?> colstart_day;
    @FXML
    private TableColumn<?, ?> colexpiry_date;
    @FXML
    private TableColumn<?, ?> colLongitude;
    @FXML
    private TableColumn<?, ?> colLatitude;
    @FXML
    private TableColumn<?, ?> ColEvent_Desription;
    @FXML
    private TableColumn<?, ?> ColEvent_category;
    @FXML
    private Button myapplications;
    @FXML
    private Button displayopp;
    @FXML
    private Button reload1;
    int x;
    evenementCRUD S = new evenementCRUD();
    private ObservableList<Evenement> data;
    @FXML
    private Button cancel;
    @FXML
    private Button reload;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reload();
        initFilter();
        data = S.displayAllEvent();
        x = S.CompterEvent();
        Nombre_Opp.setText("" + x);

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

    @FXML
    private void myApplications(ActionEvent event) {

        try {
            Parent tableViewOpportunity = FXMLLoader.load(getClass().getResource("/edu/worldaid/gui/displayEvents.fxml"));
            Scene tableViewOpportunityScene = new Scene(tableViewOpportunity);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewOpportunityScene);
            window.setOnCloseRequest(e -> Platform.exit());

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void reload(ActionEvent event) {
        evenementCRUD ec = new evenementCRUD();

        data = ec.displayAllEvent();

        colid_event.setCellValueFactory(new PropertyValueFactory<>("id_event"));
        colid_association.setCellValueFactory(new PropertyValueFactory<>("id_association"));
        colnom_event.setCellValueFactory(new PropertyValueFactory<>("nom_event"));
        colstart_day.setCellValueFactory(new PropertyValueFactory<>("date_debut_event"));
        colexpiry_date.setCellValueFactory(new PropertyValueFactory<>("date_fin_event"));
        colLongitude.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        colLatitude.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        ColEvent_Desription.setCellValueFactory(new PropertyValueFactory<>("description"));
        ColEvent_category.setCellValueFactory(new PropertyValueFactory<>("categorie"));

        colid_event.setSortType(TableColumn.SortType.DESCENDING);
        colid_association.setSortType(TableColumn.SortType.DESCENDING);
        colnom_event.setSortType(TableColumn.SortType.DESCENDING);
        colstart_day.setSortType(TableColumn.SortType.DESCENDING);
        colexpiry_date.setSortType(TableColumn.SortType.DESCENDING);
        colLongitude.setSortType(TableColumn.SortType.DESCENDING);
        colLatitude.setSortType(TableColumn.SortType.DESCENDING);
        ColEvent_Desription.setSortType(TableColumn.SortType.DESCENDING);
        ColEvent_category.setSortType(TableColumn.SortType.DESCENDING);
        System.out.println(data);
        table.setItems(data);

    }

    private void reload() {
        evenementCRUD ec = new evenementCRUD();

        data = ec.displayAllEvent();

        colid_event.setCellValueFactory(new PropertyValueFactory<>("id_event"));
        colid_association.setCellValueFactory(new PropertyValueFactory<>("id_association"));
        colnom_event.setCellValueFactory(new PropertyValueFactory<>("nom_event"));
        colstart_day.setCellValueFactory(new PropertyValueFactory<>("date_debut_event"));
        colexpiry_date.setCellValueFactory(new PropertyValueFactory<>("date_fin_event"));
        colLongitude.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        colLatitude.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        ColEvent_Desription.setCellValueFactory(new PropertyValueFactory<>("description"));
        ColEvent_category.setCellValueFactory(new PropertyValueFactory<>("categorie"));

        colid_event.setSortType(TableColumn.SortType.DESCENDING);
        colid_association.setSortType(TableColumn.SortType.DESCENDING);
        colnom_event.setSortType(TableColumn.SortType.DESCENDING);
        colstart_day.setSortType(TableColumn.SortType.DESCENDING);
        colexpiry_date.setSortType(TableColumn.SortType.DESCENDING);
        colLongitude.setSortType(TableColumn.SortType.DESCENDING);
        colLatitude.setSortType(TableColumn.SortType.DESCENDING);
        ColEvent_Desription.setSortType(TableColumn.SortType.DESCENDING);
        ColEvent_category.setSortType(TableColumn.SortType.DESCENDING);
        System.out.println(data);
        table.setItems(data);
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
        String musicFile = "C:\\Users\\HP\\Documents\\NetBeansProjects\\WorldAid\\src\\sounds" + fileNotif;     // For example

        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
*/

    @FXML
    private void annuler(ActionEvent event) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
        Parent root2;

        Stage stage1 = new Stage();
//ayNotification("home.mp3");
    }

    @FXML
    private void ExportToExcel() throws FileNotFoundException, IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("sample");

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumns().get(j).getText());
        }

        for (int i = 0; i < table.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getColumns().get(j).getCellData(i) != null) {
                    row.createCell(j).setCellValue(table.getColumns().get(j).getCellData(i).toString());
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }

        FileOutputStream fileOut = new FileOutputStream("worldaid.xls");
        workbook.write(fileOut);
        fileOut.close();
        System.out.println("Data is wrtten Successfully");
        alert2("Table Export To Excell, check worldaid.xls");
    }

    private void alert2(String Message) {
        Alert a1 = new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Alert");
        a1.setHeaderText("Table Exported To Excel");
        a1.setContentText(Message);
        a1.showAndWait();

    }
    
    @FXML
    private void displayDetails(ActionEvent event) {
          System.out.println("dcdcdcdcd");
        if (table.getSelectionModel().getSelectedItem() == null) {
            alert1("PLEASE SELECT THE EVENT THAT YOU WANT TO SEE IN DETAILS");

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

}
