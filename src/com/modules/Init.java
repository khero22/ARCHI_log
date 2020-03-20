/*
class qui va gerer tous les chargement et ecriture du fichier json
 */
package com.modules;

import com.json.driver.JsonDriver;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * 19/01/2019
 * @author KHERO
 */
public class Init {
    private final ObservableList<Projet> mesProjets = FXCollections.observableArrayList();
    private final ObservableList<Client> mesClients = FXCollections.observableArrayList();
    private final ObservableList<Categorie> mesCategories = FXCollections.observableArrayList();
    private final ObservableList<String> mesCategoriesString = FXCollections.observableArrayList();
    private final ObservableList<Annee> mesAnnees = FXCollections.observableArrayList();
    
    //methode pour charger la liste des projets du fichier json -------------------------------------------------
    public ObservableList<Projet> chargerListeProjets() throws ParseException {
        mesProjets.clear();
        JSONObject tempoj = JsonDriver.LectureTousFichierJson(NomChamps.FICHIER_JSON_PROJET);
        for (Object cle : tempoj.keySet()) {
            JSONObject keyvalue = (JSONObject) tempoj.get(cle);
            Object annee = keyvalue.get("Annee");
            Object categorie = keyvalue.get("Categorie");
            Object client = keyvalue.get("Client");
            Object numero = keyvalue.get("Numero");
            Object projet = keyvalue.get("Projet");
            mesProjets.add(new Projet(Integer.parseInt(cle.toString()),
                                       Integer.parseInt(annee.toString()),
                                       Integer.parseInt(categorie.toString()),
                                       Integer.parseInt(client.toString()),
                                       Integer.parseInt(numero.toString()),
                                        projet.toString()));
        }
        return mesProjets;
    }//----------------------------------------------------------------------------------------------------------
    
    //methode pour charger la liste des clients du fichier json -------------------------------------------------
    public ObservableList<Client> chargerListeClients() throws ParseException {
        mesClients.clear();
        JSONObject tempoj = JsonDriver.LectureTousFichierJson(NomChamps.FICHIER_JSON_CLIENT);
        for (Object cle : tempoj.keySet()) {
            JSONObject keyvalue = (JSONObject) tempoj.get(cle);
            Object client = keyvalue.get("Client");
            mesClients.add(new Client(Integer.parseInt(cle.toString()),client.toString()));
        }
        return mesClients;
    }//-----------------------------------------------------------------------------------------------------------
    //methode pour charger la liste des categorie du fichier json -------------------------------------------------
    public ObservableList<Categorie> chargerListeCategorie() throws ParseException {
        mesCategories.clear();
        JSONObject tempoj = JsonDriver.LectureTousFichierJson(NomChamps.FICHIER_JSON_CATEGORIE);
        for (Object cle : tempoj.keySet()) {
            JSONObject keyvalue = (JSONObject) tempoj.get(cle);
            Object categorie = keyvalue.get("Categorie");
            mesCategories.add(new Categorie(Integer.parseInt(cle.toString()),categorie.toString()));
        }
        return mesCategories;
    }//-----------------------------------------------------------------------------------------------------------
    //methode pour charger la liste des categorie du fichier json dans observable String--------------------------
    public ObservableList<String> chargerListeCategorieString() throws ParseException {
        mesCategoriesString.clear();
        JSONObject tempoj = JsonDriver.LectureTousFichierJson(NomChamps.FICHIER_JSON_CATEGORIE);
        for (Object cle : tempoj.keySet()) {
            JSONObject keyvalue = (JSONObject) tempoj.get(cle);
            Object categorie = keyvalue.get("Categorie");
            Object numero = keyvalue.get("numero");
            mesCategoriesString.add(categorie.toString());
        }
        return mesCategoriesString;
    }//-----------------------------------------------------------------------------------------------------------
    
     //methode pour charger la liste des annee du fichier json -------------------------------------------------
    public ObservableList<Annee> chargerListeAnnee() throws ParseException {
        mesAnnees.clear();
        JSONObject tempoj = JsonDriver.LectureTousFichierJson(NomChamps.FICHIER_JSON_ANNEE);
        for (Object cle : tempoj.keySet()) {
            JSONObject keyvalue = (JSONObject) tempoj.get(cle);
            Object annee = keyvalue.get("Annee");
            mesAnnees.add(new Annee(Integer.parseInt(cle.toString()),Integer.parseInt(annee.toString())));
        }
        return mesAnnees;
    }//-----------------------------------------------------------------------------------------------------------
    
      //methode pour ajouter un nouveau client a un fichier json -----------------------------------------------------------
     public Client NouveauClient(int ID,String Client) throws IOException {
        try {

            Client tempClient = new Client(ID, Client);
            JSONObject temp = new JSONObject();
            temp.put("Client", tempClient.getClient());
            JsonDriver.AjouterJSONObjectJson(NomChamps.FICHIER_JSON_CLIENT, String.valueOf(ID), temp);
            return tempClient;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }//---------------------------------------------------------------------------------------------------------------------
     //methode pour ajouter un nouvelle categorie a un fichier json -----------------------------------------------------------
     public Categorie NouvelleCategorie(int ID,String Categorie) throws IOException {
        try {

            Categorie tempCategorie = new Categorie(ID, Categorie);
            JSONObject temp = new JSONObject();
            temp.put("Categorie", tempCategorie.getCategorie());
            JsonDriver.AjouterJSONObjectJson(NomChamps.FICHIER_JSON_CATEGORIE, String.valueOf(ID), temp);
            return tempCategorie;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }//---------------------------------------------------------------------------------------------------------------------

      //methode pour ajouter un nouvelle annee a un fichier json -----------------------------------------------------------
     public Annee NouvelleAnnee(int ID,int Annee) throws IOException {
        try {

            Annee tempAnnee = new Annee(ID, Annee);
            JSONObject temp = new JSONObject();
            temp.put("Annee", tempAnnee.getAnnee());
            JsonDriver.AjouterJSONObjectJson(NomChamps.FICHIER_JSON_ANNEE, String.valueOf(ID), temp);
            return tempAnnee;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }//---------------------------------------------------------------------------------------------------------------------

      //methode pour ajouter un nouveau projet a un fichier json -----------------------------------------------------------
     public Projet NouveauProjet(int ID,int Annee,int Categorie,int Client, int numero,String projet) throws IOException {
        try {

            Projet tempProjet = new Projet(ID, Annee, Categorie, Client, numero, projet);
            JSONObject temp = new JSONObject();
            temp.put("Annee", tempProjet.getAnnee());
            temp.put("Categorie", tempProjet.getCategorie());
            temp.put("Client", tempProjet.getClient());
            temp.put("Numero", tempProjet.getNumero());
            temp.put("Projet", tempProjet.getProjet());

            JsonDriver.AjouterJSONObjectJson(NomChamps.FICHIER_JSON_PROJET, String.valueOf(ID), temp);
            return tempProjet;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }//---------------------------------------------------------------------------------------------------------------------
     
     //methode pour modifier une clef du fichier json avec retour true si ok -----------------------------------------------
    public  Boolean ModifierJSONObjetJson(String FileJson,String clePrimaire, String cle,int nouvelleValeur) {
        JSONParser lecture = new JSONParser();
        try {
            Object obj = lecture.parse(new FileReader(FileJson));
            JSONObject maConfiguration = (JSONObject) obj;
            JSONObject objet2 = (JSONObject) maConfiguration.get(clePrimaire);
            objet2.replace(cle, nouvelleValeur);
            FileWriter save = new FileWriter(FileJson);
            save.write(maConfiguration.toJSONString());
            save.flush();
        } catch (IOException | org.json.simple.parser.ParseException ex) {
            Logger.getLogger(JsonDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
       return true;
    } //---------------------------------------------------------------------------------------------------------------------
}
