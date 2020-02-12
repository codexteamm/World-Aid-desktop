/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worldaid.DonsService;

import com.worldaid.Entites.DonMateriel;
import com.worldaid.IService.IService;
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
public class donMaterielService implements IService<DonMateriel> {
 private Connection con;
 private Statement ste;
    private String type_materiel;

    public donMaterielService() {
        con = DataBase.getInstance().getConnection();

    }
    
    
    @Override
    public void ajouter(DonMateriel t) throws SQLException {
         ste = con.createStatement();
         String requeteInsert = "INSERT INTO `projet`.`don_materiel` (`reference`, `date_don`, `type_materiel`, `id_benevole`, `id_association`, `id_evenement`) VALUES (NULL, '" + t.getDate() + "', '" +t.type_Materiel+ "', '" + t.getId_benevole() + "', '" + t.getId_association()+"', '" + t.getId_evenement() + "');";
         ste.executeUpdate(requeteInsert);
    }

    @Override
    public void delete(DonMateriel t) throws SQLException {
            String requete2 = "delete from don_materiel where reference=27";
        try {
            ste = con.createStatement();
            ste.executeUpdate(requete2);
            System.out.println("Don supprime!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void update(DonMateriel t) throws SQLException {
        String requete1 = "update don_materiel set type_materiel='MaterielTechnologiques' where reference='"+t.getReference()+"'";
        try {
            ste = con.createStatement();
            ste.executeUpdate(requete1);
            System.out.println("Votre don a ete modifie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public List<DonMateriel> readAll() throws SQLException {
        List<DonMateriel> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from don_materiel");
     while (rs.next()) {                
               int reference=rs.getInt(1);
               Date date_don = rs.getDate(2);
               String type = rs.getString(3);
               int id_benevole = rs.getInt(4);
               int id_association = rs.getInt(5);
               int id_evenement = rs.getInt(6);
               DonMateriel dm = new DonMateriel(reference,date_don,type,id_benevole,id_association,id_evenement);
     arr.add(dm);
     
     }
    return arr;
    }

    @Override
    public void count(DonMateriel t) throws SQLException {
         Statement s = con.createStatement();
         ResultSet r = s.executeQuery("SELECT COUNT(*) AS rowcount FROM don_materiel where type_materiel='Vetements'");
         r.next();
         int count = r.getInt("rowcount") ;
         r.close() ;
          System.out.println("J'ai  " + count + " dons materiels de vetements.");

    }

    
}

    

 
