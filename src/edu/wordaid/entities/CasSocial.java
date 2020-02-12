/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wordaid.entities;

/**
 *
 * @author HP
 */
public class CasSocial extends User{
 private String descriptionCasSocial;
  private boolean valide;

    public String getDescriptionCasSocial() {
        return descriptionCasSocial;
    }

    public void setDescriptionCasSocial(String descriptionCasSocial) {
        this.descriptionCasSocial = descriptionCasSocial;
    }

    public boolean getValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public CasSocial(String descriptionCasSocial, String userName, String mdp) {
        super( userName, mdp, 3);
        this.descriptionCasSocial = descriptionCasSocial;
        this.valide = false;
    }

    public CasSocial() {

    }

    @Override
    public String toString() {
        return super.toString()+"CasSocial{" + "descriptionCasSocial=" + descriptionCasSocial + ", valide=" + valide + '}';
    }





  

 
 
    
}
