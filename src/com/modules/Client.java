/*
model client
 */
package com.modules;

/**
 * 18/01/2019
 * @author KHERO
 */
public class Client {
    private int ID;
    private String Client;

    public Client(int ID,String Client) {
        this.ID=ID;
        this.Client = Client;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getClient() {
        return Client;
    }

    public void setClient(String Client) {
        this.Client = Client;
    }

    @Override
    public String toString() {
        return Client; 
    }
    
}
