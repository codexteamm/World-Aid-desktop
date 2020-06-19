/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.services;

import edu.worldaid.entities.Administrateur;
import edu.worldaid.entities.Association;
import edu.worldaid.entities.Benevole;
import edu.worldaid.entities.Campement;
import edu.worldaid.entities.CasSocial;
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
            
            System.out.println(c.getNom()+c.getDescription()+c.getPaye()+c.getLongitude()+c.getLatitude());
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

    public void updateCompement( int id , String desc) {
        try {
            PreparedStatement pst;
            String reqUpdate = "UPDATE Campement SET description=?  where id=? ";

            PreparedStatement preparedStatement = cn2.prepareStatement(reqUpdate);
            pst = cn2.prepareStatement(reqUpdate);
            

            pst.setString(1, desc);
          pst.setInt(2, id);
            

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
            public boolean Checkprendreencharge(int idAssoc , int idcampement) {
        Campement c = new Campement();
        String requete = "SELECT *  FROM prendreencharge WHERE 	idcampement=? AND idassociation=? ";

        try {
            PreparedStatement ps = cn2.prepareStatement(requete);
            ps.setInt(1, idcampement);
            ps.setInt(2, idAssoc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;

            }
            

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du Campement " + ex.getMessage());
            
        }
        return false;
    }
        public boolean CheckCampementName(String name) {
        Campement c = new Campement();
        String requete = "SELECT *  FROM campement WHERE nom=? ";

        try {
            PreparedStatement ps = cn2.prepareStatement(requete);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;

            }
            

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du Campement " + ex.getMessage());
            
        }
        return false;
    }
public User getConnectedUser()
    {
        String requete = "select * from login ";
        try {
            PreparedStatement ps = cn2.prepareStatement(requete);
            
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
