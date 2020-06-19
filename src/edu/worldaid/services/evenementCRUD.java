/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.services;

import edu.worldaid.entities.Administrateur;
import edu.worldaid.entities.Association;
import edu.worldaid.entities.Benevole;
import edu.worldaid.entities.CasSocial;
import edu.worldaid.entities.Evenement;
import edu.worldaid.entities.User;
import edu.worldaid.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public class evenementCRUD {

    //statement: interface utilisé pour executé les requetes sql
    private Connection con;
    private Statement ste;

    public evenementCRUD() {
        con = MyConnection.getInstance().getCnx();

    }

    public int getConnectedId() {
        UserCrud cc = new UserCrud();
        Association ass = (Association) cc.getConnectedUser();
        int x;
        x = ass.getId();
        return x;
    }

    //CRUD
    public void ajouterEvenement(Evenement e) {
        try {
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date date = new java.sql.Date(utilDate.getTime());
            String requete1 = "INSERT INTO evenement(id_association,nom_event,date_debut_event,date_fin_event,longitude,latitude,description,categorie) VALUES(?,?,?,?,?,?,?,?)";
            System.out.println(requete1);
            PreparedStatement pst = con.prepareStatement(requete1);
            pst.setInt(1, getConnectedId());
            pst.setString(2, e.getNom_event());
            pst.setDate(3, (Date) e.getDate_debut_event());
            pst.setDate(4, (Date) e.getDate_fin_event());
            pst.setDouble(5, e.getLongitude());
            pst.setDouble(6, e.getLatitude());
            pst.setString(7, e.getDescription());
            pst.setString(8, e.getCategorie());

            pst.executeUpdate();
            System.out.println("Evenement ajouté!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

//CRUD
    public boolean supprimerEvenement(int id_event) {
        String requete2 = "delete from evenement where id_event=?";
        try {
            PreparedStatement pst2 = con.prepareStatement(requete2);
            //set the corresponding param
            pst2.setInt(1, id_event);
            //execute la requete
            pst2.executeUpdate();
            System.out.println("Evenement supprimé!");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression de l evenement" + ex.getMessage());
        }
        return false;
    }

//CRUD
    public void modifierEvenement(Evenement e, int idevent) {
        PreparedStatement ps = null;

        try {
            String query = "UPDATE `evenement` SET `nom_event`=?,`date_debut_event`=?,`date_fin_event`=?,`longitude`=?,`latitude`=?,`description`=?,`categorie`=? WHERE id_event=" + idevent;
            ps = con.prepareStatement(query);
            // System.out.println(query);
            ps.setString(1, e.getNom_event());
            ps.setDate(2, (Date) e.getDate_debut_event());
            ps.setDate(3, (Date) e.getDate_fin_event());
            ps.setDouble(4, e.getLongitude());
            ps.setDouble(5, e.getLatitude());
            ps.setString(6, e.getDescription());
            ps.setString(7, e.getCategorie());
            //  System.out.println(ps);
            ps.executeUpdate();
            System.out.println("modification effectué");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
    private ObservableList<Evenement> Listeopp;

    public ObservableList<Evenement> displayAllEvent() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Listeopp = FXCollections.observableArrayList();
        try {
            String query = "select * from evenement";
            ps = con.prepareStatement(query);
            //ps.setString(1, sl_no);
            //System.out.println(ps);
            rs = ps.executeQuery();
            while (rs.next()) {
                Listeopp.add(new Evenement(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getDouble(6), rs.getDouble(7), rs.getString(8), rs.getString(9)));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        Listeopp.forEach(e -> System.out.println(e));
        return Listeopp;

    }

//metier recherche event by id    
    public Evenement RechercheEventById(int id_event) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "select * from evenement where id_event=" + id_event;
            ps = con.prepareStatement(query);
            //ps.setString(1, sl_no);
            // System.out.println(ps);
            rs = ps.executeQuery();
            while (rs.next()) {

                Evenement e = new Evenement(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getDouble(6), rs.getDouble(7), rs.getString(8), rs.getString(9));
                System.out.println(e);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        Evenement e = null;
        return e;
    }

//metier: recherche event by categorie
    public Evenement RechercheEventByCategorie(String mekki) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM `evenement` WHERE `categorie` LIKE'" + mekki + "'";
            ps = con.prepareStatement(query);
            //ps.setString(1, sl_no);
            // System.out.println(ps);
            rs = ps.executeQuery();
            while (rs.next()) {

                Evenement e = new Evenement(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getDouble(6), rs.getDouble(7), rs.getString(8), rs.getString(9));
                System.out.println(e);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        Evenement e = null;
        return e;
    }

//metier: compter nbr evenet     
    public int CompterEvent() {
        int nbr = 0;
        PreparedStatement ps = null;
        try {
            String query = "Select Count(*) from evenement";
            ps = con.prepareStatement(query);
            // System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nbr = rs.getInt(1);
                System.out.println(nbr);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return nbr;

    }

    public int CompterMyEvent() {
        int nbr = 0;
        PreparedStatement ps = null;
        try {
            String query = "Select Count(*) from evenement where id_association=?";
            ps = con.prepareStatement(query);
            // System.out.println(ps);
             ps.setInt(1,getConnectedId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nbr = rs.getInt(1);
                System.out.println(nbr);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return nbr;

    }

    public ObservableList<Evenement> DisplayMy_Events() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Listeopp = FXCollections.observableArrayList();
        try {
            String query = "select * from evenement where id_association=?";
           // System.out.println(query);
            
            ps = con.prepareStatement(query);
            ps.setInt(1,getConnectedId());
            //System.out.println(ps);
             
            rs = ps.executeQuery();
            while (rs.next()) {
                Listeopp.add(new Evenement(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getDouble(6), rs.getDouble(7), rs.getString(8), rs.getString(9)));
                //  System.out.println(Listeopp);
            }
        } catch (Exception e) {
            System.out.println("edfrf");
        }
        Listeopp.forEach(e -> System.out.println(e));
        return Listeopp;
    }

    public User getConnectedUser() {
        String requete = "select * from login ";
        try {
            PreparedStatement ps = con.prepareStatement(requete);

            User u = new User();
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                switch (rs.getInt("type")) {
                    case 1:
                        Benevole b = new Benevole();
                        b.setId(rs.getInt("id"));
                        b.setType(rs.getInt("type"));
                        b.setMdp(rs.getString("Mdp"));
                        b.setUserName(rs.getString("username"));
                        b.setNom(rs.getString("nom"));
                        b.setPrenom(rs.getString("prenom"));
                        b.setPays(rs.getString("pays"));
                        b.setMail(rs.getString("mail"));

                        b.setDateNaissance(rs.getDate("dateNaissance"));
                        return b;

                    case 2:
                        Association a = new Association();
                        a.setId(rs.getInt("id"));
                        a.setType(rs.getInt("type"));
                        a.setMdp(rs.getString("Mdp"));
                        a.setUserName(rs.getString("username"));
                        a.setNomAssociaiton(rs.getString("nomAssociaiton"));
                        a.setRib(rs.getString("rib"));
                        a.setAddresse(rs.getString("addresse"));
                        a.setCategorie(rs.getString("categorie"));
                        a.setMail(rs.getString("mail"));
                        a.setLogo(rs.getString("logo"));
                        a.setNumero(rs.getInt("numero"));
                        a.setValide(rs.getBoolean("valide"));

                        return a;

                    case 3:
                        CasSocial c = new CasSocial();
                        c.setId(rs.getInt("id"));
                        c.setType(rs.getInt("type"));
                        c.setMdp(rs.getString("Mdp"));
                        c.setUserName(rs.getString("username"));
                        c.setDescriptionCasSocial(rs.getString("descriptionCasSocial"));
                        c.setValide(rs.getBoolean("valide"));
                        c.setIdCampement(rs.getInt("idcampement"));

                        return c;

                    case 4: {
                        Administrateur A = new Administrateur();
                        A.setId(rs.getInt("id"));
                        A.setType(rs.getInt("type"));
                        A.setMdp(rs.getString("Mdp"));
                        A.setUserName(rs.getString("username"));
                        return A;

                    }
                }

            }
            return null;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche d'un benevole" + ex.getMessage());
            return null;
        }
    }

}
