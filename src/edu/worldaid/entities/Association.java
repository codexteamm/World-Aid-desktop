/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.entities;

/**
 *
 * @author HP
 */
public class Association extends User{
    private String nomAssociaiton ;
    private String rib;
    private String addresse;
    private String categorie;
    private String mail;
    private String logo;
    private int numero;
     private boolean valide;

    public Association(String nomAssociaiton, String rib, String addresse, String categorie, String mail, String logo, int numero, String userName, String mdp) {
        super( userName, mdp,2 );
        this.nomAssociaiton = nomAssociaiton;
        this.rib = rib;
        this.addresse = addresse;
        this.categorie = categorie;
        this.mail = mail;
        this.logo = logo;
        this.numero = numero;
        this.valide = false;
    }

    public Association() {

    }

    public String getNomAssociaiton() {
        return nomAssociaiton;
    }

    public String getRib() {
        return rib;
    }

    public String getAddresse() {
        return addresse;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getMail() {
        return mail;
    }

    public String getLogo() {
        return logo;
    }

    public int getNumero() {
        return numero;
    }

    public boolean getValide() {
        return valide;
    }

    public void setNomAssociaiton(String nomAssociaiton) {
        this.nomAssociaiton = nomAssociaiton;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    @Override
    public String toString() {
        return "Association{"+super.toString() + "nomAssociaiton=" + nomAssociaiton + ", rib=" + rib + ", addresse=" + addresse + ", categorie=" + categorie + ", mail=" + mail + ", logo=" + logo + ", numero=" + numero + ", valide=" + valide + '}';
    }


  
    
    

    
}
