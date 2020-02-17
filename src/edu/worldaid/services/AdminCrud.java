/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.services;

import edu.worldaid.entities.Association;
import edu.worldaid.entities.Benevole;
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
 * @author HP
 */
public class AdminCrud {

    Connection cn2;
    Statement st;

    public AdminCrud() {
        cn2 = MyConnection.getInstance().getCnx();
    }

    public void validateAssoCasSobyId(User u, Boolean valide) {
        if (u instanceof CasSocial || u instanceof Association) {

            try {
                String reqUpdate = "UPDATE user SET valide=?  where id=? ";
                PreparedStatement pst2 = cn2.prepareStatement(reqUpdate);
                pst2.setBoolean(1, valide);
                pst2.setInt(2, u.getId());
                pst2.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(AdminCrud.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void validateAssoCasSobyId(int id, Boolean valide) {

        try {
            String reqUpdate = "UPDATE user SET valide=?  where id=? ";
            PreparedStatement pst2 = cn2.prepareStatement(reqUpdate);
            pst2.setBoolean(1, valide);
            pst2.setInt(2, id);
            pst2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void validateAssoCasSoByUserName(String userName, Boolean valide) {

        try {
            String reqUpdate = "UPDATE user SET valide=?  where userName=? ";
            PreparedStatement pst2 = cn2.prepareStatement(reqUpdate);
            pst2.setBoolean(1, valide);
            pst2.setString(2, userName);

            pst2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void validateDemande(int id, Boolean valide) {

        try {
            String reqUpdate = "UPDATE demandeAid SET valide=?  where id=? ";
            PreparedStatement pst2 = cn2.prepareStatement(reqUpdate);
            pst2.setBoolean(1, valide);
            pst2.setInt(2, id);
            pst2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteUser(int idBenevole) {
        String requete2 = "DELETE FROM user WHERE id = ?";
        try {
            PreparedStatement pst2 = cn2.prepareStatement(requete2);

            // set the corresponding param
            pst2.setInt(1, idBenevole);
            // execute the delete statement
            pst2.executeUpdate();
            System.out.println("user supprime!");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    public void deleteDemande(int idDemande) {
        String requete2 = "DELETE FROM demandeAid WHERE id = ?";
        try {
            PreparedStatement pst2 = cn2.prepareStatement(requete2);

            // set the corresponding param
            pst2.setInt(1, idDemande);
            // execute the delete statement
            pst2.executeUpdate();
            System.out.println("demande supprime!");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    public List<Benevole> displayAllBenevole() {
        ArrayList<Benevole> ben = new ArrayList<>();
        try {

            String requete3 = "SELECT * FROM user WHERE type=1 ";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();
            while (rs.next()) {
                Benevole b = new Benevole();

                b.setId(rs.getInt("id"));
                b.setType(rs.getInt("type"));
                b.setMdp(rs.getString("Mdp"));
                b.setUserName(rs.getString("username"));
                b.setNom(rs.getString("nom"));
                b.setPrenom(rs.getString("prenom"));
                b.setPays(rs.getString("pays"));
                b.setMail(rs.getString("mail"));
                ben.add(b);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ben;
    }

    public List<Association> displayAllAssociation() {
        ArrayList<Association> ass = new ArrayList<>();
        try {

            String requete3 = "SELECT * FROM user WHERE type=2 ";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {
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
                ass.add(a);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ass;
    }

    public List<CasSocial> displayAllCasSocial() {
        ArrayList<CasSocial> cas = new ArrayList<>();
        try {

            String requete3 = "SELECT * FROM user WHERE type=3 ";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {
                CasSocial c = new CasSocial();
                c.setId(rs.getInt("id"));
                c.setType(rs.getInt("type"));
                c.setMdp(rs.getString("Mdp"));
                c.setUserName(rs.getString("username"));
                c.setDescriptionCasSocial(rs.getString("descriptionCasSocial"));
                c.setValide(rs.getBoolean("valide"));
                cas.add(c);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cas;
    }

}
