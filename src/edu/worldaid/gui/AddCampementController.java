/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.gui;
import edu.worldaid.entities.Campement;
import edu.worldaid.services.CompementCrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.w3c.dom.Element;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AddCampementController implements Initializable {

    @FXML
    private WebView webView;
    @FXML
    private Button addc;
    @FXML
    private TextField nomc;
    @FXML
    private TextArea descriptionc;
    WebEngine webEngine;
    @FXML
    private TextField paysc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        webEngine = webView.getEngine();

        URL url1 = this.getClass().getResource("/edu/worldaid/gui/addmaps.html");
        webEngine.load(url1.toString());
        webEngine.setJavaScriptEnabled(true);
        

    }

    @FXML
    private void addc(ActionEvent event) {
/*webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
    @Override
    public void changed(ObservableValue<? extends Worker.State> ov, Worker.State t, Worker.State t1) {
        if (t1 == Worker.State.SUCCEEDED) {*/
            try {
                System.out.println("trying to execute script");

                // fixed - innerHtml is a property, not a function
                String latitude = (String)webEngine.executeScript("document.getElementById('latitude').value");
                double lat = Double.parseDouble(latitude);
                
                String longitude = (String)webEngine.executeScript("document.getElementById('longitude').value");
                double lon = Double.parseDouble(longitude);
                
                Campement camp=new Campement(nomc.getText(), lat, lon,descriptionc.getText(),paysc.getText());
                
                CompementCrud cc=new CompementCrud();
                
                cc.addCompement(camp);
                
               
                System.out.println("script successful");
            } catch (Exception e) {

                // you can also print the exception to diagnose the error
                e.printStackTrace();
                System.out.println("script failed");
            }
    //    }
    //}
//});
        
    }

}
