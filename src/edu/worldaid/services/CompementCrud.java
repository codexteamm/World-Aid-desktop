/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.services;

import edu.worldaid.entities.Association;
import edu.worldaid.entities.Campement;
import edu.worldaid.entities.User;
import edu.worldaid.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * private int id ; private String nom ; private String description ; private
 * double latitude; private double longitude;
 *
 */
public class CompementCrud {

    Connection cn2;
    Statement st;

    public CompementCrud() {
        cn2 = MyConnection.getInstance().getCnx();
    }

    public void addCompement(Campement c) {
        try {
            PreparedStatement pst;
            String requete2;
            requete2 = "INSERT INTO campement (nom, description, paye,longitude,Latitude )VALUES (?,?,?,?,?)";
            pst = cn2.prepareStatement(requete2);

            pst.setString(1, c.getNom());
            pst.setString(2, c.getDescription());
            pst.setString(3, c.getPaye());
            pst.setDouble(4, c.getLongitude());
            pst.setDouble(5, c.getLatitude());

            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger("errreur dans l'ajout compement"+ex.getMessage());
        }

    }

    public void addPrendreEnCharge(int idCompement, int idAssociation) {
        try {
            PreparedStatement pst;
            String requete2;
            requete2 = "INSERT INTO prendreencharge (idcampement,idassociation)VALUES (?,?)";
            pst = cn2.prepareStatement(requete2);

            pst.setInt(1, idCompement);
            pst.setInt(2, idAssociation);

            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("erreur addPrendreEnCharge "+ex.getMessage());;
        }

    }

    public void deletePrendreEnCharge(int idCampement, int idAssociation) {
        try {

            String reqDel = "DELETE FROM prendreencharge WHERE idcampement=? AND idassociation=? ";
            PreparedStatement pst = cn2.prepareStatement(reqDel);
            pst.setInt(1, idCampement);
            pst.setInt(2, idAssociation);
            pst.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }

    }

    public void updateCompement(Campement c, int id) {
        try {
            PreparedStatement pst;
            String reqUpdate = "UPDATE Campement SET nom=? ,description=? ,paye=? ,longitude=? ,latitude=? where id=? ";

            PreparedStatement preparedStatement = cn2.prepareStatement(reqUpdate);
            pst = cn2.prepareStatement(reqUpdate);
            
            pst.setString(1, c.getNom());
            pst.setString(2, c.getDescription());
            pst.setString(3, c.getPaye());
            
            pst.setDouble(4, c.getLongitude());
            pst.setDouble(5, c.getLatitude());
            pst.setInt(6, id);
            pst.executeUpdate();
            
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
    public Campement getCampementByid(int id )
    {
        Campement c = new Campement();
         try {

            String requete3 = "SELECT * FROM campement WHERE id=? ";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            pst2.setInt(1, id);
            ResultSet rs = pst2.executeQuery();
            while (rs.next()) {
                
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setDescription(rs.getString("description"));
                c.setLongitude(rs.getDouble("Longitude"));
                c.setLatitude(rs.getDouble("Latitude"));
                c.setPaye(rs.getString("paye"));
                

            }

        } catch (SQLException ex) {
             System.out.println("erreur getCampementByid "+ex.getMessage());
        }
        
        
        return c ;
    }

    public List<Campement> displayCampementbyIdAss(int idAssociation) //les compements pris en charge par une Associaction
    {
        ArrayList<Campement> list = new ArrayList<>();

        try {

            String requete3 = "SELECT * FROM prendreencharge WHERE idassociation=? ";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            pst2.setInt(1, idAssociation);
            ResultSet rs = pst2.executeQuery();
            while (rs.next()) {
                Campement c = this.getCampementByid(rs.getInt("idcampement"));

                list.add(c);

            }

        } catch (SQLException ex) {
            System.out.println("displayCampementbyIdAss"+ex.getMessage());
        }
        return list;

    }
    
 public List<Association> displayAssociationbyidcam(int idaCampement) //les associaions qui ont pris en charge une compement 
    {
        ArrayList<Association> list = new ArrayList<>();

        try {

            String requete3 = "SELECT * FROM prendreencharge WHERE idcampement=? ";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            pst2.setInt(1, idaCampement);
            ResultSet rs = pst2.executeQuery();
            while (rs.next()) {
                UserCrud uc = new UserCrud();
                
                User u= uc.getUserbyid(rs.getInt("idassociation"));
                if (u instanceof Association )
                {
                Association a =(Association)u; 
                                list.add(a);
                }


            }

        } catch (SQLException ex) {
            System.out.println("erreur displayAssociationbyidcam  "+ex.getMessage());
        }
        return list;

    }
    public void deleteCampement(int idCompemnt) {
        try {

            String reqDel = "DELETE FROM campement WHERE id=? ";
            PreparedStatement pst = cn2.prepareStatement(reqDel);
            pst.setInt(1, idCompemnt);
            pst.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }

    }

    public List<Campement> displayAllCampement() {
        ArrayList<Campement> list = new ArrayList<>();

        try {

            String requete3 = "SELECT * FROM campement ";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();
            while (rs.next()) {
                Campement c = new Campement();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setDescription(rs.getString("description"));
                c.setLongitude(rs.getDouble("Longitude"));
                c.setLatitude(rs.getDouble("Latitude"));
                c.setPaye(rs.getString("paye"));
                list.add(c);

            }

        } catch (SQLException ex) {
            System.out.println("erreur displayAllCampement" +ex.getMessage());
        }
        return list;

    }

    public Campement findByIdCampement(int id) {
        Campement c = new Campement();
        String requete = "SELECT (id,nom,description, paye,longitude,latitude)  FROM campement WHERE id=? ";

        try {
            PreparedStatement ps = cn2.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setDescription(rs.getString("description"));
                c.setLongitude(rs.getDouble("Longitude"));
                c.setLatitude(rs.getDouble("Latitude"));
                c.setPaye(rs.getString("paye"));

            }
            return c;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du Campement " + ex.getMessage());
            return null;
        }
    }



}
