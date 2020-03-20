/*
model projet
 */
package com.modules;

/**
 * 19/01/2019
 * @author KHERO
 */
public class Projet {
    private int  ID;
    private int  Annee;
    private int  Categorie;
    private int Client;
    private int  numero;
    private String  Projet;

    public Projet(int ID, int Annee, int Categorie, int Client, int numero, String Projet) {
        this.ID = ID;
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

    public int getCategorie() {
        return Categorie;
    }

    public void setCategorie(int Categorie) {
        this.Categorie = Categorie;
    }

    public int getClient() {
        return Client;
    }

    public void setClient(int Client) {
        this.Client = Client;
    }

 

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
