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
import edu.worldaid.entities.User;
import edu.worldaid.entities.demande_aide;
import edu.worldaid.entities.feedback;
import edu.worldaid.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class feedbackCRUD {

    Connection cn2;
    Statement st;

    public feedbackCRUD() {
        //cn2 = MyConnection.getInstance().getCnx();
        cn2 = MyConnection.getInstance().getCnx();
    }

    public void ajouterFeedback() {

        try {
            String requete = "INSERT INTO feedback (id_user,message) VALUES (20,'Très bon travail! Bonne continuation.')";
            st = cn2.createStatement();
            st.executeUpdate(requete);
            System.out.println("Feedback ajouté!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ajouterFeedback2(feedback fb) {
        try {
            String requete2 = "INSERT INTO feedback (id_user,message) VALUES (?,?)";
            PreparedStatement pst = cn2.prepareStatement(requete2);
            //pst.setInt(1, da.getId_demande());
            //pst.setInt(1, fb.getId_user());
            pst.setString(2, fb.getMessage());

            pst.executeUpdate();
            System.out.println("Feedback ajouté!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    /*public void supprimerDemandeAide(demande_aide d) {
        try {
            //int idd = d.getId_demande();
            String requete3 = "DELETE FROM demande_aide WHERE id_demande = " + d.getId_demande() + ";";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            pst2.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }*/
    
    public void supprimerFeedback2(int id) {
        String requete2 = "DELETE FROM feedback WHERE id_feedback = ?";
        try {
            PreparedStatement pst2 = cn2.prepareStatement(requete2);

            // set the corresponding param
            pst2.setInt(1, id);
            // execute the delete statement
            pst2.executeUpdate();
            System.out.println("Feedback id '" + id + "' supprimé!");

        } catch (SQLException ex) {
            System.out.println("Erreur! " + ex.getMessage());
        }
    }

    /**
     *
     * @param d
     * @param id
     */
    public void modiferFeedback(feedback f, int id) {
        try {
            String requete = "UPDATE feedback SET message=? WHERE id_feedback=?";
            PreparedStatement pst2 = cn2.prepareStatement(requete);

            //pst2.setInt(1, d.getId_demande());
            pst2.setString(1, f.getMessage());
            pst2.setInt(2, id);

            pst2.executeUpdate();

            System.out.println("Mise à jour du feedback effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    public List<feedback> afficherFeedback() {
        ArrayList<feedback> feed = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM feedback";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();
            while (rs.next()) {
                feedback fb = new feedback();
                fb.setId_feedback(rs.getInt("id_feedback"));
                fb.setTitre(rs.getString("titre"));
                fb.setMessage(rs.getString("message"));
                feed.add(fb);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return feed;
    }
    
    public List<feedback> afficherFeedbackTrie() {
        ArrayList<feedback> feed = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM feedback order by id_feedback";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();
            while (rs.next()) {
                feedback fb = new feedback();
                fb.setId_feedback(rs.getInt("id_feedback"));
                fb.setMessage(rs.getString("message"));
                //fb.setId_user(rs.getInt("id_user"));
                feed.add(fb);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return feed;
    }

    /*public feedback chercherFeedback(int id_feed) {
        String requete = "select * from feedback where id_feedback=?";
        feedback fb = new feedback();

        try {
            PreparedStatement ps = cn2.prepareStatement(requete);
            ps.setInt(1, id_feed);
            ResultSet rs = ps.executeQuery();
            //On récupère les MetaData

            System.out.println("\n**********************************");
            if (rs.next() == false) {
                return null;
            } else {
                while (rs.next()) {
                    fb.setId_feedback(rs.getInt("id_feedback"));
                    fb.setId_user(rs.getInt("id_user"));
                    fb.setMessage(rs.getString("message"));
                }
                return fb;
            }

        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche de votre feedback" + ex.getMessage());
            return null;
        }
    }*/
    
   public feedback chercherFeedback5(int id_feedback) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String requete = "select * from feedback where id_feedback="+id_feedback;
            ps = cn2.prepareStatement(requete);
            rs = ps.executeQuery();
            //On récupère les MetaData

                while (rs.next()) {
                    feedback f = new feedback((rs.getInt(1)),rs.getString(2),rs.getString(3));
                    System.out.println(f);   
                }

        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche de votre feedback" + ex.getMessage());
        }
        feedback f=null;
        return f;
    }
   
   public int nbrFeedback (){

        int k=0;
        try {
            String requete = "select count(*) from feedback" ;
            PreparedStatement ps = cn2.prepareStatement(requete);
                        k++;

            ps.executeQuery();

        } catch (SQLException ex) {
            System.out.println("Erreur lors du calcul" + ex.getMessage());
        }
                   return k;

   }
   
   public User getConnectedUser() {
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
