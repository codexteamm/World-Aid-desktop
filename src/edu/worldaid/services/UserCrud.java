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
import edu.worldaid.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author reznov
 */
public class UserCrud {

    private Connection connection;

    public UserCrud() {
        connection = MyConnection.getInstance().getCnx();
    }

    //hethi timchi 3ala ay user
    public void inscriptionUser(User u) {
        try {
            PreparedStatement pst;
            String requete2;

            switch (u.getType()) {

                case 1:
                    Benevole b = (Benevole) u;
                    requete2 = "INSERT INTO user (nom ,prenom,pays ,mail,mdp,dateNaissance,userName,type)VALUES (?,?,?,?,?,?,?,?)";
                    pst = connection.prepareStatement(requete2);

                    pst.setString(1, b.getNom());
                    pst.setString(2, b.getPrenom());
                    pst.setString(3, b.getPays());
                    pst.setString(4, b.getMdp());
                    pst.setString(5, b.getMail());
                    pst.setTimestamp(6, Timestamp.valueOf(b.getDateNaissance()));
                    pst.setString(7, b.getUserName());
                    pst.setInt(8, b.getType());
                    pst.executeUpdate();
                    break;

                case 2:
                    Association a = (Association) u;

                    requete2 = "INSERT INTO user (userName ,mdp,type,nomAssociaiton,rib,categorie,mail,logo,numero,valide ,addresse)VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                    pst = connection.prepareStatement(requete2);
                    pst.setString(1, a.getUserName());
                    pst.setString(2, a.getMdp());
                    pst.setInt(3, a.getType());
                    pst.setString(4, a.getNomAssociaiton());
                    pst.setString(5, a.getRib());
                    pst.setString(6, a.getCategorie());
                    pst.setString(7, a.getMail());
                    pst.setString(8, a.getLogo());

                    pst.setInt(9, a.getNumero());
                    pst.setBoolean(10, a.getValide());
                    pst.setString(11, a.getAddresse());
                    pst.executeUpdate();

                    break;
                case 3:
                    CasSocial c = (CasSocial) u;
                    requete2 = "INSERT INTO user (userName ,mdp,type,descriptionCasSocial,valide,idcampement )VALUES (?,?,?,?,?,?)";
                    pst = connection.prepareStatement(requete2);
                    pst.setString(1, c.getUserName());
                    pst.setString(2, c.getMdp());
                    pst.setInt(3, c.getType());
                    
                    pst.setString(4, c.getDescriptionCasSocial());
                    pst.setBoolean(5, c.getValide());
                    pst.setInt(6, c.getIdCampement());
                    pst.executeUpdate();

                    break;
                case 4:
                    Administrateur A = (Administrateur) u;
                    requete2 = "INSERT INTO user (mdp,username,type)VALUES (?,?,?)";
                    pst = connection.prepareStatement(requete2);
                    pst.setString(1, A.getMdp());
                    pst.setString(2, A.getUserName());
                    pst.setInt(3, A.getType());
                    pst.executeUpdate();
                    break;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void updateBenevole(Benevole b, int idBenevole) {
        try {
            String reqUpdate = "UPDATE user SET nom=? , prenom=? , pays=? , mail=? mdp=? , dateNaissance=?,  userName=? , type=? where idBenevole=? ";
            PreparedStatement pst = connection.prepareStatement(reqUpdate);

            pst.setString(1, b.getNom());
            pst.setString(2, b.getPrenom());
            pst.setString(3, b.getPays());
            pst.setString(4, b.getMdp());
            pst.setString(5, b.getMail());
            pst.setTimestamp(6, Timestamp.valueOf(b.getDateNaissance()));
            pst.setString(7, b.getUserName());
            pst.setInt(8, b.getType());
            pst.setInt(9, idBenevole);

            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    public void updateAssociation(Association a, int idAssociation) {
        try {
            String reqUpdate = "UPDATE user SET userName=? ,mdp=?,type=? ,nomAssociaiton=? , rib=? , categorie=? ,mail=? ,logo=? , numero=? ,valide=?  where idBenevole=? ";
            PreparedStatement pst = connection.prepareStatement(reqUpdate);

            pst.setString(1, a.getUserName());
            pst.setString(2, a.getMdp());
            pst.setInt(3, a.getType());
            pst.setString(4, a.getNomAssociaiton());
            pst.setString(5, a.getRib());
            pst.setString(6, a.getCategorie());
            pst.setString(7, a.getMail());
            pst.setString(8, a.getLogo());
            pst.setInt(9, a.getNumero());
            pst.setBoolean(10, a.getValide());
            pst.setInt(11, idAssociation);

            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    public void updateCasSocial(CasSocial c, int idCasSocial) {
        try {
            String reqUpdate = "UPDATE user SET nom=? , prenom=? ,pays=? ,mail=? , mdp=? ,dateNaissance=? ,userName=? ,type=? ,idcampement=?  where idBenevole=? ";
            PreparedStatement pst = connection.prepareStatement(reqUpdate);

            pst.setString(1, c.getUserName());
            pst.setString(2, c.getMdp());
            pst.setInt(3, c.getType());
            pst.setString(4, c.getDescriptionCasSocial());
            pst.setBoolean(5, c.getValide());
            pst.setInt(6, c.getIdCampement());
            pst.setInt(7, idCasSocial);

            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    public User getUserbyid(int id) {
        String requete = "select * from user where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
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

                        b.setDateNaissance(rs.getTimestamp("dateNaissance").toLocalDateTime());
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

    boolean checkUserName(String userName) {
        try {
            String requete3 = "SELECT * FROM user WHERE userName=? ";
            PreparedStatement pst2 = connection.prepareStatement(requete3);
            pst2.setString(1, userName);
            ResultSet rs = pst2.executeQuery();

            if (rs.next()) {
                System.out.println("username already used");
                   return false;
            }
        } catch (SQLException ex) {
            System.out.println("probleme checkuserName"+ex.getMessage());
        }
         return true;
    }

}
