/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.entities;

import java.time.LocalDateTime;

import java.util.Objects;

/**
 *
 * @author HP
 */
public class Benevole extends User {

   private String nom;
   private String  prenom;
   private String  pays;
   private String  mail;
   private LocalDateTime   dateNaissance;

    public Benevole(String userName, String nom, String prenom, String pays, String mail, String mdp, LocalDateTime  dateNaissance) {
        super(userName,mdp,1 );
       
        this.nom = nom;
        this.prenom = prenom;
        this.pays = pays;
        this.mail = mail;
        this.dateNaissance = dateNaissance;
    }
    public Benevole() {
   
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Benevole other = (Benevole) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.pays, other.pays)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        if (!Objects.equals(this.dateNaissance, other.dateNaissance)) {
            return false;
        }
        return true;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setDateNaissance(LocalDateTime dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPays() {
        return pays;
    }

    public String getMail() {
        return mail;
    }

    public LocalDateTime getDateNaissance() {
        return dateNaissance;
    }

    @Override
    public String toString() {
        return super.toString()+ "nom=" + nom + ", prenom=" + prenom + ", pays=" + pays + ", mail=" + mail + ", dateNaissance=" + dateNaissance ;
    }


   
   
    
}
