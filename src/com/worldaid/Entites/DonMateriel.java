/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worldaid.Entites;

import com.worldaid.Entites.DonMateriel.type_materiel;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class DonMateriel {

    
    int reference;
    int id_association;
    int id_evenement;
    int id_benevole;
    Date date_don;
    public String type_Materiel;
    

    public DonMateriel(java.sql.Date date, String type_Materiel, int id_benevole, int id_association, int id_evenement) {
        this.date_don=date;
        this.type_Materiel=type_Materiel;
        this.id_benevole=id_benevole;
        this.id_association=id_association;
        this.id_evenement=id_evenement;
    }

    public DonMateriel() {
    }

     


    
   public enum type_materiel{
        Vetements,
        Alimentations,
        Medicaments,
        Hebergements,
        MaterielTechnlogiques;
        
       
        
        @Override
        public String toString() {
            return "type_materiel{" + '}';
        }

        public  type_materiel getVetements() {
            return Vetements;
        }

        public  type_materiel getAlimentations() {
            return Alimentations;
        }

        public  type_materiel getMedicaments() {
            return Medicaments;
        }

        public  type_materiel getHebergement() {
            return Hebergements;
        }

        public  type_materiel getMaterielTechnlogique() {
            return MaterielTechnlogiques;
        }
    
    }
    
    /**
     *
     * @param reference
     * @param id_association
     * @param id_evenement
     * @param id_benevole
     * @param date_don
     * @param type_Materiel
     */
    

        public DonMateriel(int reference,java.sql.Date date_don,String type_Materiel, int id_benevole, int id_association, int id_evenement) {
        this.reference = reference;
        this.date_don = date_don;
        this.type_Materiel=type_Materiel;
        this.id_benevole = id_benevole;
        this.id_association = id_association;
        this.id_evenement = id_evenement;
        
    }
    
        public void setTypeMateriel(type_materiel type){
            type_Materiel = type.toString();
        }
     public int getReference() {
        return reference;
    }

    public int getId_association() {
        return id_association;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public int getId_benevole() {
        return id_benevole;
    }

    public Date getDate() {
        return date_don;
    }
 
     public void setReference(int reference) {
        this.reference = reference;
    }

    public void setId_association(int id_association) {
        this.id_association = id_association;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public void setId_benevole(int id_benevole) {
        this.id_benevole = id_benevole;
    }

    public void setDate(Date date_don) {
        this.date_don = date_don;
    }
    
    @Override
    public String toString(){
        return"DonMateriel : Reference : "+reference+"  DateduDon : "+date_don+"  TypeMateriel : "+type_Materiel+"  Id_Benevole : "+id_benevole+"  Id_Association : "+id_association+"  Id_Evenement : "+id_evenement+"";
    }
    
}
