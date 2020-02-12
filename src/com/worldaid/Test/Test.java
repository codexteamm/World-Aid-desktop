/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worldaid.Test;

import com.worldaid.DonsService.donFinancierService;
import com.worldaid.DonsService.donMaterielService;
import com.worldaid.Entites.DonFinancier;
import com.worldaid.Entites.DonMateriel;
import com.worldaid.Entites.DonMateriel.type_materiel;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class Test {
    
    
    public static void main(String[] args){
     
    
    donMaterielService ser = new donMaterielService();    // Création d'une instance donMaterielService
    donFinancierService ser1 = new donFinancierService(); // Création d'une instance donFinancierService 
        
    try{
        java.util.Date d = new java.util.Date(); //Importer date courante d
        java.sql.Date date = new java.sql.Date(d.getTime());  
        DonMateriel dm = new DonMateriel(date,"Hebergements",5,2,3); //Création d'un nouveau objet DonMateriel
        DonFinancier df = new DonFinancier(1,2,3,date,12.47); //Création d'un nouveau objet DonFinancier                                                      //Création d'un nouveau objet Don
       
         // Test du crud Don Materiel : 
            System.out.println("Crud Don Materiel : ");
            System.out.println("Ajout Effectué avec succes");
            ser.ajouter(dm);
            ser.ajouter(dm);
            //Suppression : 
            ser.delete(dm);
            //Mise a jour : 
            ser.update(dm);
            System.out.println("Affichage : ");
            List<DonMateriel> listDonMateriel = ser.readAll();
            System.out.println(listDonMateriel);
            ser.count(dm);
            System.out.println("\n");
           
            
            System.out.println("***********************************************  \n");
            
            
           // Test du crud Don Financier :  
            System.out.println("Crud Don Financier :  ");
            System.out.println("Ajout Effectué avec succes");
            ser1.ajouter(df);
            System.out.println("Suppression  :");
            ser1.delete(df);
            System.out.println("Mise a jour : ");
            ser1.update(df);
            System.out.println("Affichage : ");
            List<DonFinancier> listDonFinancier = ser1.readAll();
            System.out.println(listDonFinancier);
            System.out.println("Recherche by id : ");
            ser1.searchById(1);
            
       
        
     }catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
        
    }
}
