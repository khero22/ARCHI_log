/*
model annee
 */
package com.modules;

/**
 * 18/01/2019
 * @author KHERO
 */
public class Annee {
    private int ID;
    private int Annee;

    public Annee( int ID,int Annee) {
        this.ID=ID;
        this.Annee=Annee;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAnnee() {
        return Annee;
    }

    public void setAnnee(int Annee) {
        this.Annee = Annee;
    }

    @Override
    public String toString() {
        return String.valueOf(Annee); 
    }


    
}
