/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worldaid.Entites;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class DonFinancier {

     
    int reference;
    int id_association;
    int id_evenement;
    int id_benevole;
    Date date_don;
    double montant;
    
    public DonFinancier() {
        reference=0;
        id_association=0;
        id_evenement=0;
        id_benevole=0;
        montant=0;
    }
    public DonFinancier(int reference, int id_association, int id_evenement, int id_benevole, Date date_don, double montant) {
        this.reference = reference;
        this.id_association = id_association;
        this.id_evenement = id_evenement;
        this.id_benevole = id_benevole;
        this.date_don = date_don;
        this.montant = montant;
    }

     public DonFinancier(int id_association, int id_evenement, int id_benevole, Date date_don, double montant) {
        this.id_association = id_association;
        this.id_evenement = id_evenement;
        this.id_benevole = id_benevole;
        this.date_don = date_don;
        this.montant = montant;
    }

    
     
     public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public int getId_association() {
        return id_association;
    }

    public void setId_association(int id_association) {
        this.id_association = id_association;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public int getId_benevole() {
        return id_benevole;
    }

    public void setId_benevole(int id_benevole) {
        this.id_benevole = id_benevole;
    }

    public Date getDate_don() {
        return date_don;
    }

    public void setDate_don(Date date_don) {
        this.date_don = date_don;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
     
      @Override
    public String toString() {
        return "DonFinancier{" + "reference=" + reference + ", id_association=" + id_association + ", id_evenement=" + id_evenement + ", id_benevole=" + id_benevole + ", date_don=" + date_don + ", montant=" + montant + '}';
    }
     
}
