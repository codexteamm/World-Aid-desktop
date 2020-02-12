/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wordaid.tests;



import edu.wordaid.entities.Administrateur;
import edu.wordaid.entities.Association;
import edu.wordaid.entities.Benevole;
import edu.wordaid.entities.CasSocial;
import edu.wordaid.services.AdminCrud;
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
            UserCrud uCrud=new UserCrud();
            AdminCrud admincrud=new AdminCrud();
            
            Benevole b=new Benevole( "sofientr", "sofien", "triki", "tunisie ", "sofien@gmail ", "test ", LocalDateTime.now());
            Administrateur admin =new Administrateur( "root", "root");
            Association ass =new  Association("croissant2 ", "4654564654", "tunis", "help", "croi@gezazm", "gfhoeifh", 1231321, "asso ","test ");
            CasSocial c=new CasSocial("rgdsgdg dg dfg fd", "ahmed", "test");
          
            uCrud.inscriptionUser(admin);
            uCrud.inscriptionUser(ass);
            uCrud.inscriptionUser(c);
            uCrud.inscriptionUser(b);
            
            
            
            List<Association> a= admincrud.displayAllAssociation();
            List<CasSocial> cas =admincrud.displayAllCasSocial();
            List<Benevole> l=admincrud.displayAllBenevole();
            boolean br;
            
        
            System.out.println(a);
            System.out.println(cas);
            System.out.println(l);
            
            
            //admincrud.validateAssoCasSobyId(6,true);
       
            
            
             
      /*      
        } catch (SQLException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
     
    }
}
