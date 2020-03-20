/*
model categorie
 */
package com.modules;

/**
 * 19/01/2019
 * @author KHERO
 */
public class Categorie {
    private int ID;

    private String Categorie;

    public Categorie(int ID,String Categorie) {
        this.ID =ID;
        this.Categorie = Categorie;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String Categorie) {
        this.Categorie = Categorie;
    }


    @Override
    public String toString() {
        return Categorie; 
    }
    
}
