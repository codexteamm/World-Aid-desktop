/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wordaid.tests;

import edu.wordaid.entities.Benevole;
import edu.wordaid.utils.BenevoleCRUD;
import java.time.LocalDateTime;



public class MainClass {


    public MainClass() {


    }

    public static void main(String[] args) {
     /*   MyConnection mc =MyConnection.getInstance();
        MyConnection mc1 =MyConnection.getInstance();
        System.out.println(mc.hashCode()+"-"+mc1.hashCode());*/
     BenevoleCRUD bcr= new BenevoleCRUD();
     
      Benevole b1;
        b1 = new Benevole(1  , "sofien", "triki", "tunisie", "sofien.triki@esprit.tn", "aaaa", LocalDateTime.now());
     Benevole b2 = new Benevole(2 , "sofien", "triki", "france", "sofien.triki@esprit.tn", "aaaa", LocalDateTime.now());
     bcr.deleteBenevole(1);
     
    }
}
