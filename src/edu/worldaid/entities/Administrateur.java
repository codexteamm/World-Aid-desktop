/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.entities;



/**
 *
 * @author reznov
 */
public class Administrateur extends User{
    

    public Administrateur() {
    }


    
    public Administrateur( String userName, String mdp) {
        super(userName,mdp,4 );
        
    }
    
}
