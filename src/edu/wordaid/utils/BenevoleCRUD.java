
package edu.wordaid.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import edu.wordaid.entities.Benevole;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class BenevoleCRUD {

    Connection cn2;
    Statement st;

    public BenevoleCRUD() {
        cn2 = MyConnection.getInstance().getCnx();
    }

    public void addBenevole(Benevole b) {
        try {
            String requete2 = "INSERT INTO Benevole (nom ,prenom,pays ,mail,mdp,dateDenaissance)VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cn2.prepareStatement(requete2);
            
            pst.setString(1, b.getNom());
            pst.setString(2, b.getPrenom());
            pst.setString(3, b.getPays());
            pst.setString(4, b.getMdp());
            pst.setString(5,b.getMail() );
            pst.setTimestamp(6, Timestamp.valueOf(b.getDateNaissance()));

            pst.executeUpdate();
            System.out.println("Benevole ajouteé!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Benevole> displayAllBenevole() {
        ArrayList<Benevole> ben = new ArrayList<>();
        try {

            String requete3 = "SELECT * FROM Benevole ";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();
            while ( rs.next())
            {
                Benevole b=new Benevole();
                b.setIdBenevole(rs.getInt("idBenevole"));
                b.setNom(rs.getString("nom"));
                b.setPrenom(rs.getString("prenom"));
                b.setPays(rs.getString("pays"));
                b.setMail(rs.getString("mail"));
                b.setMdp(rs.getString("Mdp"));
                b.setDateNaissance(rs.getTimestamp("dateNaissance").toLocalDateTime());
                
                ben.add(b);
                
                
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ben;
    }


    public void deleteBenevole( int idBenevole){
        String requete2 = "DELETE FROM Benevole WHERE idBenevole = ?";
        try {
             PreparedStatement pst2 = cn2.prepareStatement(requete2);
            
            // set the corresponding param
            pst2.setInt(1, idBenevole);
            // execute the delete statement
            pst2.executeUpdate();
            System.out.println("Benevole supprime!");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }
        public void updateBenevole(Benevole b, int idBenevole) {
         try {
            String reqUpdate="UPDATE Benevole SET nom=? ,prenom=?,pays=? ,mail=?,mdp=?,dateDenaissance=? where idBenevole=? ";
            PreparedStatement pst2 = cn2.prepareStatement(reqUpdate);
         
            
            pst2.setString(1, b.getNom());
            pst2.setString(2, b.getPrenom()); 
            pst2.setString(3, b.getPays()); 
            pst2.setString(4, b.getMail()); 
            pst2.setString(5, b.getMdp()); 
            pst2.setTimestamp(6, Timestamp.valueOf(b.getDateNaissance()));
            pst2.setInt(7, idBenevole); 
            pst2.executeUpdate();
            
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
            public Benevole findBenevoleById(int idBenevole) {
          String requete = "select * from Benevole where idBenevole=?";
         Benevole b  = new Benevole();
      
        try {
            PreparedStatement ps = cn2.prepareStatement(requete);
            ps.setInt(1, idBenevole);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 b.setIdBenevole(rs.getInt("idBenevole"));
                b.setNom(rs.getString("nom"));
                b.setPrenom(rs.getString("prenom"));
                b.setPays(rs.getString("pays"));
                b.setMail(rs.getString("mail"));
                b.setMdp(rs.getString("Mdp"));
                b.setDateNaissance(rs.getTimestamp("dateNaissance").toLocalDateTime());
            }
            return b;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche d'benevole" + ex.getMessage());
            return null;
        }
    }

}
