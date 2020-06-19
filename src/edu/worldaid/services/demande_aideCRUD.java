/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.services;

//import edu.worldaid.entities.cas_social;
import edu.worldaid.entities.Administrateur;
import edu.worldaid.entities.Association;
import edu.worldaid.entities.Benevole;
import edu.worldaid.entities.CasSocial;
import edu.worldaid.entities.User;
import edu.worldaid.entities.demande_aide;
import edu.worldaid.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class demande_aideCRUD {

    Connection cn2;
    Statement st;

    public demande_aideCRUD() {
        //cn2 = MyConnection.getInstance().getCnx();
        cn2 = MyConnection.getInstance().getCnx();
    }

    public void ajouterDemandeAide() {

        try {
            String requete = "INSERT INTO demande_aide (id_demande,titre,description,etat) VALUES (20,'Besoin d'argent,'Bonjour tout le monde',0)";
            st = cn2.createStatement();
            st.executeUpdate(requete);
            System.out.println("Demande aide ajoutée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ajouterDemandeAide2(demande_aide da) {
        try {
            String requete2 = "INSERT INTO demande_aide (titre,description,etat) VALUES (?,?,?)";
            PreparedStatement pst = cn2.prepareStatement(requete2);
            //pst.setInt(1, da.getId_demande());
            pst.setString(1, da.getTitre());
            pst.setString(2, da.getDescription());  
            pst.setInt(3, da.getEtat());
            pst.executeUpdate();
            System.out.println("Demande d'aide ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    
    
    public void supprimerDemandeAide2(int id) {
        String requete2 = "DELETE FROM demande_aide WHERE id_demande = ?";
        try {
            PreparedStatement pst2 = cn2.prepareStatement(requete2);

            // set the corresponding param
            pst2.setInt(1, id);
            // execute the delete statement
            pst2.executeUpdate();
            System.out.println("Demande id '" + id + "' supprimée!");
            //if (pst2.executeUpdate()==1) {System.out.println("Demande supprimée!");}
            //else {System.out.println("Erreur! Vérifier l'ID !");}
        } catch (SQLException ex) {
            System.out.println("Erreur! " + ex.getMessage());
        }
    }

    
    
    /**
     *
     * @param d
     * @param id
     */
    public void modiferDemandeAide(demande_aide d, int id) {
        try {
            String requete = "UPDATE demande_aide SET titre=?,description=? ,etat=? WHERE id_demande=?";
            PreparedStatement pst2 = cn2.prepareStatement(requete);

            //pst2.setInt(1, d.getId_demande());
            pst2.setString(1, d.getTitre());
            pst2.setString(2, d.getDescription());
            pst2.setInt(3, d.getEtat());
            pst2.setInt(4, id);
            pst2.executeUpdate();

            System.out.println("Mise à jour de la demande d'aide effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la mise à jour de la demande " + ex.getMessage());
        }
    }

    public ArrayList<demande_aide> afficherDemandeAide() {
        ArrayList<demande_aide> per = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM demande_aide";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();
            while (rs.next()) {
                demande_aide d = new demande_aide();
                d.setId_demande(rs.getInt("id_demande"));
                d.setTitre(rs.getString(2));
                d.setDescription(rs.getString("description"));
                d.setEtat(rs.getInt("etat"));
                per.add(d);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return per;
    }

    public List<demande_aide> afficherDemandeAideTrie() {
        ArrayList<demande_aide> per = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM demande_aide  ORDER BY id_demande";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();
            while (rs.next()) {
                demande_aide d = new demande_aide();
                d.setId_demande(rs.getInt("id_demande"));
                d.setTitre(rs.getString(2));
                d.setDescription(rs.getString("description"));
                d.setEtat(rs.getInt("etat"));
                per.add(d);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return per;
    }

    
    public demande_aide chercherDemandeAide5(int id_demande) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String requete = "select * from demande_aide where id_demande=" + id_demande;
            ps = cn2.prepareStatement(requete);
            rs = ps.executeQuery();
            //On récupère les MetaData

            while (rs.next()) {
                demande_aide d = new demande_aide((rs.getInt(1)), rs.getString(2), rs.getString(3), rs.getInt(4));
                System.out.println(d);
            }

        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche de votre demande" + ex.getMessage());
        }
        demande_aide d = null;
        return d;
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
