/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.gui;

import edu.worldaid.entities.Campement;
import edu.worldaid.entities.CasSocial;
import edu.worldaid.services.AdminCrud;
import edu.worldaid.services.CompementCrud;
import edu.worldaid.services.UserCrud;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class WorldAid extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("mapsC.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("addCampement.fxml"));
        
        Scene scene = new Scene(root);
        
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       /* UserCrud uc=new UserCrud();
        CompementCrud cc =new CompementCrud();
        Campement camp =new Campement("tunis camp", 37, 10, "this is a description for the campement", "tunisie");
        cc.addCompement(camp);
        CasSocial cas1 =new CasSocial("this is is a description", "saber", "test", 1);
        uc.inscriptionUser(cas1);
        */
        
        launch(args);
    }
    
}
