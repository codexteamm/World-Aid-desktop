/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wordaid.entities;

import java.time.LocalDateTime;

import java.util.Objects;

/**
 *
 * @author HP
 */
public class Benevole {
   private int idBenevole;
   private String nom;
   private String  prenom;
   private String  pays;
   private String  mail;
   private String  mdp;
   private LocalDateTime   dateNaissance;

    public Benevole(int idBenevole, String nom, String prenom, String pays, String mail, String mdp, LocalDateTime  dateNaissance) {
        this.idBenevole = idBenevole;
        this.nom = nom;
        this.prenom = prenom;
        this.pays = pays;
        this.mail = mail;
        this.mdp = mdp;
        this.dateNaissance = dateNaissance;
    }
    public Benevole() {
   
    }

    public int getIdBenevole() {
        return idBenevole;
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

    public String getMdp() {
        return mdp;
    }

    public LocalDateTime  getDateNaissance() {
        return dateNaissance;
    }

    public void setIdBenevole(int idBenevole) {
        this.idBenevole = idBenevole;
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

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setDateNaissance(LocalDateTime  dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.idBenevole;
        hash = 83 * hash + Objects.hashCode(this.nom);
        hash = 83 * hash + Objects.hashCode(this.prenom);
        hash = 83 * hash + Objects.hashCode(this.pays);
        hash = 83 * hash + Objects.hashCode(this.mail);
        hash = 83 * hash + Objects.hashCode(this.mdp);
        hash = 83 * hash + Objects.hashCode(this.dateNaissance);
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
        if (this.idBenevole != other.idBenevole) {
            return false;
        }
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
        if (!Objects.equals(this.mdp, other.mdp)) {
            return false;
        }
        if (!Objects.equals(this.dateNaissance, other.dateNaissance)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Benevole{" + "idBenevole=" + idBenevole + ", nom=" + nom + ", prenom=" + prenom + ", pays=" + pays + ", mail=" + mail + ", mdp=" + mdp + ", dateNaissance=" + dateNaissance + '}';
    }
   

   
   
    
}
