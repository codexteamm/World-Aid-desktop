/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wordaid.tests;

import edu.wordaid.entities.Administrateur;
import edu.wordaid.entities.Association;
import edu.wordaid.entities.Benevole;
import edu.wordaid.entities.Campement;
import edu.wordaid.entities.CasSocial;
import edu.wordaid.services.AdminCrud;
import edu.wordaid.services.CompementCrud;
import edu.wordaid.services.UserCrud;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainClass {

    public MainClass() {

    }

    public static void main(String[] args) {
        //try {
        UserCrud uCrud = new UserCrud();
        AdminCrud admincrud = new AdminCrud();

        /*  Benevole b=new Benevole( "sofientr", "sofien", "triki", "tunisie ", "sofien@gmail ", "test ", LocalDateTime.now());
           Administrateur admin =new Administrateur( "root", "root");*/
 /*CasSocial c=new CasSocial("rgdsgdg dg dfg fd", "ahmed", "test",5);
        /*  
            uCrud.inscriptionUser(admin);
            uCrud.inscriptionUser(ass);
            
            uCrud.inscriptionUser(b);
         */
        Association ass = new Association("croissant2 ", "4654564654", "tunis", "help", "croi@gezazm", "gfhoeifh", 1231321, "asso ", "test ");

        uCrud.inscriptionUser(ass);
        CompementCrud cc = new CompementCrud();
        Campement c0 = new Campement("sofien", 12.5, 31.2, "dffdf", "tunis");
        Campement c1 = new Campement("ahtes", 12.5, 31.2, "dffdf", "tunis");
        Campement dd = cc.getCampementByid(2);

        cc.addPrendreEnCharge(5, 76);

        /*System.out.println(a);
            System.out.println(cas);
            System.out.println(l);*/
        //admincrud.validateAssoCasSobyId(6,true);
        /*      
        } catch (SQLException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
        //cc.updateCompement(c1, 2);


        // System.out.println(dd);
        //List<Campement> list =cc.displayAllCampement();
        //System.out.println(list);
        // cc.addPrendreEnCharge(30, 50);
        // cc.deletePrendreEnCharge(20, 50);
        //cc.deleteCampement(1);
        System.out.println(cc.displayAssociationbyidcam(5));

    }
}
