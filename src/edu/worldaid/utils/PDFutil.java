/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.utils;

import edu.worldaid.utils.MyConnection;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.html.WebColors;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import edu.worldaid.entities.demande_aide;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author AMINE
 */
public class PDFutil {
    
    Connection cn2;
    Statement ste;

    public PDFutil() {
        cn2 = MyConnection.getInstance().getCnx();
    }
    
    
    
    Document doc = new Document();
        
    public void listDemande() throws SQLException,FileNotFoundException,DocumentException,IOException 
    {  
        ste=cn2.createStatement();
        ResultSet rs=ste.executeQuery("SELECT * from demande_aide");
        PdfWriter.getInstance(doc, new FileOutputStream("./Demandes.pdf"));
        
        doc.open();
        
        //Image image = Image.getInstance ("C:\\Users\\HP\\Desktop\\PI\\logo.png"); 
        //document.add(new Paragraph("test\n  test")); 
        //doc.add(image);  
        doc.add(new Paragraph("   "));
        doc.add(new Paragraph("  Requests list:  "));
        doc.add(new Paragraph("   "));
        
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        PdfPCell cell;
        
        cell = new PdfPCell(new Phrase("REQUEST ID",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(WebColors.getRGBColor("#f6beb9"));
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("TITLE",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(WebColors.getRGBColor("#f6beb9"));
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("DESCRIPTION",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(WebColors.getRGBColor("#f6beb9"));
        table.addCell(cell);
        
        
        cell = new PdfPCell(new Phrase("STATE",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(WebColors.getRGBColor("#f6beb9"));
        table.addCell(cell);
        
        
        
        while (rs.next()) {                
            
                demande_aide da = new demande_aide();
                da.setId_demande(rs.getInt(1));
                da.setTitre(rs.getString(2));
                da.setDescription(rs.getString(3));
                da.setEtat(rs.getInt(4));
               
               /*DateFormat df = new SimpleDateFormat("hh:mm:ss");
               String rec = df.format(a.getId());
                String rank = Integer.toString(a.getId());*/
         
               
               
               
               
               cell = new PdfPCell(new Phrase(String.valueOf(da.getId_demande()),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(WebColors.getRGBColor("#b9cfed"));
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase(da.getTitre(),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(WebColors.getRGBColor("#b9cfed"));
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase(da.getDescription(),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(WebColors.getRGBColor("#b9cfed"));
               table.addCell(cell);
        
        
               cell = new PdfPCell(new Phrase(String.valueOf(da.getEtat()),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(WebColors.getRGBColor("#b9cfed"));
               table.addCell(cell);
               
        }
        
            doc.add(table);
            doc.close();
            Desktop.getDesktop().open(new File ("./Demandes.pdf"));
            }
}
