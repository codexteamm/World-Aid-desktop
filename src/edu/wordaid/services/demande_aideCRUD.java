/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.services;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
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

    /*public void supprimerODemandeAide2(demande_aide d) {
        String requete2 = "DELETE FROM demande_aide WHERE id_demande = "+d.getId_demande();
        try {
            PreparedStatement pst2 = cn2.prepareStatement(requete2);

            // set the corresponding param
            //pst2.setInt(1, id);
            // execute the delete statement
            pst2.executeUpdate();
            
                    if (pst2.executeUpdate()==1) {String reponse="Demande id"+d.getId_demande()+"supprimée!";
            System.out.println(reponse);}
            else {System.out.println("Erreur! Vérifier l'ID !");}
        } catch (SQLException ex) {
            System.out.println("Erreur! " + ex.getMessage());
        }
    }*/
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

    public List<demande_aide> afficherDemandeAide() {
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

    public demande_aide chercherDemandeAide(int id_demande) {
        String requete = "select * from demande_aide where id_demande=?";
        demande_aide d = new demande_aide();

        try {
            PreparedStatement ps = cn2.prepareStatement(requete);
            ps.setInt(1, id_demande);
            ResultSet rs = ps.executeQuery();
            //On récupère les MetaData

            System.out.println("\n**********************************");
            if (rs.next() == false) {
                return null;
            } else {
                while (rs.next()) {
                    d.setId_demande(rs.getInt(id_demande));
                    d.setTitre(rs.getString("titre"));
                    d.setDescription(rs.getString("description"));
                    d.setEtat(rs.getInt("etat"));
                }
                return d;
            }

        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche de votre demande" + ex.getMessage());
            return null;
        }
    }
    
    /*public demande_aide chercherDemandeAide(int id_demande) {
        String requete = "select * from demande_aide where id_demande=?";
        demande_aide d = new demande_aide();

        try {
            PreparedStatement ps = cn2.prepareStatement(requete);
            ps.setInt(1, id_demande);
            ResultSet rs = ps.executeQuery();
            //On récupère les MetaData

            //On récupère les MetaData
            ResultSetMetaData resultMeta = rs.getMetaData();
            while (rs.next()) {
                for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
                    return (demande_aide) rs.getObject(i);
                }

            }

            rs.close();
            ps.close();
            return null;
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche de votre demande" + ex.getMessage());
            return null;
        }
    }*/

}
