/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.entities;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author user
 */
public class Evenement {

    private int id_event;
    private int id_association;
    private String nom_event;
    private Date date_debut_event;
    private Date date_fin_event;
    private Double longitude;
    private Double latitude;
    private String description;
    private String categorie;

    public Evenement() {
    }

    public Evenement(int id_event, int id_association, String nom_event, Date date_debut_event, Date date_fin_event, double longitude, double latitude, String description, String categorie) {
        this.id_event = id_event;
        this.id_association = id_association;
        this.nom_event = nom_event;
        this.date_debut_event = date_debut_event;
        this.date_fin_event = date_fin_event;
        this.longitude = longitude;
        this.latitude = latitude;
        this.description = description;
        this.categorie = categorie;
    }

    public Evenement(int id_association, String nom_event, Date date_debut_event, Date date_fin_event, double longitude, double latitude, String description, String categorie) {
        //this.id_event = id_event;
        this.id_association = id_association;
        this.nom_event = nom_event;
        this.date_debut_event = date_debut_event;
        this.date_fin_event = date_fin_event;
        this.longitude = longitude;
        this.latitude = latitude;
        this.description = description;
        this.categorie = categorie;
    }

    public Evenement(String nom_event, Date date_debut_event, Date date_fin_event, Double longitude, Double latitude, String description, String categorie) {
        this.nom_event = nom_event;
        this.date_debut_event = date_debut_event;
        this.date_fin_event = date_fin_event;
        this.longitude = longitude;
        this.latitude = latitude;
        this.description = description;
        this.categorie = categorie;
    }
    

    public int getId_event() {
        return id_event;
    }

    public int getId_association() {
        return id_association;
    }

    public String getNom_event() {
        return nom_event;
    }

    public Date getDate_debut_event() {
        return date_debut_event;
    }

    public Date getDate_fin_event() {
        return date_fin_event;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public String getDescription() {
        return description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public void setId_association(int id_association) {
        this.id_association = id_association;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public void setDate_debut_event(Date date_debut_event) {
        this.date_debut_event = date_debut_event;
    }

    public void setDate_fin_event(Date date_fin_event) {
        this.date_fin_event = date_fin_event;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.id_association;
        hash = 61 * hash + Objects.hashCode(this.nom_event);
        hash = 61 * hash + Objects.hashCode(this.date_debut_event);
        hash = 61 * hash + Objects.hashCode(this.date_fin_event);
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
        hash = 61 * hash + Objects.hashCode(this.description);
        hash = 61 * hash + Objects.hashCode(this.categorie);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evenement other = (Evenement) obj;
        if (this.id_event != other.id_event) {
            return false;
        }
        if (this.id_association != other.id_association) {
            return false;
        }
        if (!Objects.equals(this.nom_event, other.nom_event)) {
            return false;
        }
        if (!Objects.equals(this.date_debut_event, other.date_debut_event)) {
            return false;
        }
        if (!Objects.equals(this.date_fin_event, other.date_fin_event)) {
            return false;
        }
        if (Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(other.longitude)) {
            return false;
        }
        if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.categorie, other.categorie)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id_event=" + id_event + ", id_association=" + id_association + ", nom_event=" + nom_event + ", date_debut_event=" + date_debut_event + ", date_fin_event=" + date_fin_event + ", longitude=" + longitude + ", latitude=" + latitude + ", description=" + description + ", categorie=" + categorie + '}';
    }

}
