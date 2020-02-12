/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worldaid.DonsService;



import com.worldaid.Entites.DonFinancier;
import com.worldaid.IService.IService1;
import com.worldaid.Utils.DataBase;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class donFinancierService implements IService1<DonFinancier> {
   private  Connection con;
   private Statement ste;
  
    public donFinancierService() {
        con = DataBase.getInstance().getConnection();
    }
    
   @Override
    public void ajouter(DonFinancier df) throws SQLException {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `projet`.`don_financier` ( `reference`, `id_association`, `id_evenement`, `id_benevole`, `date_don`, `montant`) VALUES ( ?, ?, ?, ?, ?, ?);");
    pre.setInt(1, df.getReference());
    pre.setInt(2, df.getId_association());
    pre.setInt(3, df.getId_evenement());
    pre.setInt(4, df.getId_benevole());
    pre.setDate(5, df.getDate_don());
    pre.setDouble(6, df.getMontant());
    pre.executeUpdate();
    }

   @Override
    public void delete(DonFinancier t) throws SQLException {
         String requete2 = "delete from don_financier where reference=1";
        try {
            ste = con.createStatement();
            ste.executeUpdate(requete2);
            System.out.println("Don supprime!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

   @Override
    public void update(DonFinancier t) throws SQLException {
        String requete1 = "update don_financier set montant=12 where reference=1";
        try {
            ste = con.createStatement();
            ste.executeUpdate(requete1);
            System.out.println("Votre don a ete modifie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   @Override
    public List<DonFinancier> readAll() throws SQLException {
      List<DonFinancier> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from don_financier");
     while (rs.next()) {                
               int reference=rs.getInt(1);
               int id_association = rs.getInt(2);
               int id_evenement = rs.getInt(3);
               int id_benevole = rs.getInt(4);
               Date date_don = rs.getDate(5);
               double montant = rs.getDouble(6);
               DonFinancier dm = new DonFinancier(reference,id_association,id_evenement,id_benevole,date_don,montant);
     arr.add(dm);
     
     }
    return arr;
    }

    @Override
    public DonFinancier searchById(int reference) {
        String requete = "select * from don_financier where reference=?";
         DonFinancier df  = new DonFinancier();

        try {
            PreparedStatement ps = con.prepareStatement(requete);
            ps.setInt(1, reference);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 df.setReference(rs.getInt("reference"));
                df.setId_association(rs.getInt("id_association"));
                df.setId_evenement(rs.getInt("id_evenement"));
                df.setId_benevole(rs.getInt("id_benevole"));
                df.setDate_don(rs.getDate("date_don"));
                df.setMontant(rs.getDouble("montant"));
            }
            return df;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche d'benevole" + ex.getMessage());
            return null;
        }
    }
        
        
    }