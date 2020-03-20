/*
ce model d'objet est concu pour faire le lien en tre les objets projets et la tableView
pour gerer l'affichage.
 */
package com.modules;

/**
 * 19/01/2019
 * @author KHERO
 */
public class ProjetParser {
   private int  Annee;
    private String  Categorie;
    private String Client;
    private int  numero;
    private String  Projet;
//    Annee A;
//    Categorie C;
//    Client CL;
//    Projet P;

    public ProjetParser(int Annee, String Categorie, String Client, int numero, String Projet) {
        this.Annee = Annee;
        this.Categorie = Categorie;
        this.Client = Client;
        this.numero = numero;
        this.Projet = Projet;
    }

    public int getAnnee() {
        return Annee;
    }

    public void setAnnee(int Annee) {
        this.Annee = Annee;
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String Categorie) {
        this.Categorie = Categorie;
    }

    public String getClient() {
        return Client;
    }

    public void setClient(String Client) {
        this.Client = Client;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getProjet() {
        return Projet;
    }

    public void setProjet(String Projet) {
        this.Projet = Projet;
    }

    

   
}
