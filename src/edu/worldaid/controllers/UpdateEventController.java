/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.controllers;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import static edu.worldaid.controllers.Add_EventsController.LONGITUDE_PATTERN;
//import static edu.worldaid.controllers.DisplayEventsController.PlayNotification;
import edu.worldaid.entities.Evenement;
import edu.worldaid.services.evenementCRUD;
import edu.worldaid.utils.OpenStreetMapUtils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;

/**
 * FXML Controller class
 *
 * @author user
 */
public class UpdateEventController implements Initializable, MapComponentInitializedListener {

    private Evenement e;

    @FXML
    private Label type;
    @FXML
    private Button CancelButton;
    @FXML
    private Button update;
    @FXML
    private TextField longitude;
    @FXML
    private TextField latitude;
    @FXML
    private DatePicker fin;
    @FXML
    private TextField description;
    private TextField category;
    @FXML
    private DatePicker start;
    @FXML
    private TextField nom;
    @FXML
    private TextField id;

    private TableView<Evenement> table = new TableView<>();
    @FXML
    private Label UpStartLab;
    @FXML
    private Label UpFinLab;

    protected static final String LATITUDE_PATTERN = "^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?))$";
    protected static final String LONGITUDE_PATTERN = "^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?))$";
    @FXML
    private GoogleMapView mapView;
    @FXML
    private ComboBox<String> comboBoxRegions;

    private GoogleMap map;
    @FXML
    private ComboBox<String> event_category;

    /**
     * Initializes the controller class.
     */
    public void AfficherDetailsUpdate(Evenement Ev) {
        Evenement e = Ev;

        System.out.println(e.getNom_event() + "bij");
        System.out.println(e.getNom_event());
        System.out.println(e.getId_event());
        id.setText("" + e.getId_event());
        id.setVisible(false);
        nom.setText(e.getNom_event());
        UpStartLab.setText(e.getDate_debut_event().toString());
        UpFinLab.setText(e.getDate_fin_event().toString());
        longitude.setText("" + e.getLongitude());
        latitude.setText("" + e.getLatitude());
        description.setText(e.getDescription());
        // category.setText(event_category.getSelectionModel().getSelectedItem().toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
        comboBoxRegions.setItems(FXCollections.observableArrayList("Tunis", "Ben Arous", "Ariana",
                "Sousse", "Djerba", "Kef", "Jendouba", "Bizerte", "Gafsa", "Sfax", "Tataouin"));
         event_category.setItems(FXCollections.observableArrayList("enfants","sdf","refugies","pauvres","femmes","persones agées"));
    }

    @FXML
    private void annuler() {
        Stage stage = (Stage) CancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void Update(ActionEvent event) {

        if (nom.getText().isEmpty()) {
            alert1("Event Name Is Empty !!");

            return;
        }
        if (start.getValue().isBefore(LocalDate.now())) {
            alert1("La date de début ne peut pas etre dans le passé !!");
            return;
        }
        if (fin.getValue().isBefore(LocalDate.now())) {
            alert1("La date de fin ne peut pas etre dans le passé !!");
            return;
        }
        if (description.getText().isEmpty()) {
            alert1("Event Description Is Empty !!");
            return;
        }

//        if (!longitude.getText().trim().matches(LONGITUDE_PATTERN)) {
//            alert1("Must be Flooat !!");
//
//            return;
//        }
//        if (!latitude.getText().trim().matches(LATITUDE_PATTERN)) {
//            alert1("Must be Flooat !!");
//
//            return;
//        }
        if (longitude.getText().isEmpty()) {
            alert1("Please Select A New Place !!");
            return;
        }
        if (latitude.getText().isEmpty()) {
            alert1("latitude Is Empty !!");
            return;
        }

        if (event_category.getSelectionModel().getSelectedItem().toString().isEmpty()) {
            alert1("Event Category Is Empty !!");
            return;
        }

        Date date = java.sql.Date.valueOf(start.getValue());
        Date date1 = java.sql.Date.valueOf(fin.getValue());
        if (alert1Confirmation() == true) {

            Evenement O1 = new Evenement(nom.getText(), date, date1, Double.parseDouble(longitude.getText()), Double.parseDouble(latitude.getText()), description.getText(),event_category.getSelectionModel().getSelectedItem().toString());
            //System.out.println(O1);
            //  System.out.println("bij2");

            evenementCRUD S = new evenementCRUD();

            // System.out.println("bij");
            S.modifierEvenement(O1, Integer.valueOf(id.getText()));
            alert2("EVENT UPDATED");

           //PlayNotification("updated.mp3");

        }
        annuler();
    }

    private void alert1(String Message) {
        Alert a1 = new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Alert");
        a1.setHeaderText("Champ Vide");
        a1.setContentText(Message);
        a1.showAndWait();

    }

    private boolean alert1Confirmation() {
        Alert a1 = new Alert(Alert.AlertType.CONFIRMATION);
        a1.setTitle("CONFIRMATION DIALOG");
        a1.setHeaderText("UPDATE CONFIRMATION");
        a1.setContentText("ARE YOU SURE THAT YOU WANT TO UPDATE THIS EVENT ?");

     //  PlayNotification("sure.mp3");

        Optional<ButtonType> result = a1.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }

    }
/*
    public static void PlayNotification(String fileNotif) {
        String musicFile = "\\c:\\sounds\\" + fileNotif;     // For example

        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

    }
*/

    @Override
    public void mapInitialized() {
        BasicConfigurator.configure();
        Map<String, Double> TunisiaCoords;
        TunisiaCoords = OpenStreetMapUtils.getInstance().getCoordinates("Tunisia");
        System.out.println("Latitude de la localisation Tunisia: " + TunisiaCoords.get("lat"));
        System.out.println("Longitude de la localisation Tunisia: " + TunisiaCoords.get("lon"));
        //Get Tunisia coords and center it on the map

//            LatLong joeSmithLocation = new LatLong(TunisiaCoords.get("lat"), TunisiaCoords.get("lon"));
//            LatLong joshAndersonLocation = new LatLong(47.6297, -122.3431);
//            LatLong bobUnderwoodLocation = new LatLong(47.6397, -122.3031);
//            LatLong tomChoiceLocation = new LatLong(47.6497, -122.3325);
//            LatLong fredWilkieLocation = new LatLong(47.6597, -122.3357);
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(TunisiaCoords.get("lat"), TunisiaCoords.get("lon")))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(true)
                .zoomControl(false)
                .zoom(8);

        map = mapView.createMap(mapOptions);
   
    }

    @FXML
    private void choisirLocalisation(ActionEvent event) throws IOException {

        BasicConfigurator.configure();
        Map<String, Double> TunisiaCoords;
        TunisiaCoords = OpenStreetMapUtils.getInstance().getCoordinates("Tunisia");
        System.out.println("Latitude de la localisation Tunisia: " + TunisiaCoords.get("lat"));
        System.out.println("Longitude de la localisation Tunisia: " + TunisiaCoords.get("lon"));
        //Get Tunisia coords and center it on the map

//            LatLong joeSmithLocation = new LatLong(TunisiaCoords.get("lat"), TunisiaCoords.get("lon"));
//            LatLong joshAndersonLocation = new LatLong(47.6297, -122.3431);
//            LatLong bobUnderwoodLocation = new LatLong(47.6397, -122.3031);
//            LatLong tomChoiceLocation = new LatLong(47.6497, -122.3325);
//            LatLong fredWilkieLocation = new LatLong(47.6597, -122.3357);
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(TunisiaCoords.get("lat"), TunisiaCoords.get("lon")))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(true)
                .zoomControl(false)
                .zoom(8);

        map = mapView.createMap(mapOptions);
       

        //Placement du marqueur
        Map<String, Double> EventCoords;
        EventCoords = OpenStreetMapUtils.getInstance().getCoordinates(comboBoxRegions.getValue());
        LatLong LatLongEvent = new LatLong(EventCoords.get("lat"), EventCoords.get("lon"));

        //System.out.println("Latitude de la localisation "+ disp.getLocalisation()+": " + ExpertCoords.get("lat"));
        //System.out.println("Longitude de la localisation: " + disp.getLocalisation()+": " +ExpertCoords.get("lon"));
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(LatLongEvent);
        Marker LatExpertMarker = new Marker(markerOptions);
        map.addMarker(LatExpertMarker);

        longitude.setText(EventCoords.get("lon").toString());
        latitude.setText(EventCoords.get("lat").toString());

    }

    private void alert2(String Message) {
        Alert a1 = new Alert(Alert.AlertType.INFORMATION);
        a1.setTitle("INFORMATION");
        a1.setHeaderText("");
        a1.setContentText(Message);
        a1.showAndWait();

    }
}
