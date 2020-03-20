/*
 driver pour manipulation du json
 */
package com.json.driver;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * le: 27/01/2017
 * @author KHERO
 */
public class JsonDriver {

    
      //methode d'ecriture
    public static void EcritureMapJson(Map config, String FileJson) {
        FileWriter file = null;
        try {
            JSONObject CONFIG = new JSONObject();
            CONFIG.putAll(config);
            file = new FileWriter(FileJson);
            file.write(CONFIG.toJSONString());
            file.flush();

        } catch (IOException ex) {
            Logger.getLogger(JsonDriver.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                file.close();
            } catch (IOException ex) {
                Logger.getLogger(JsonDriver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //methode de lecture d'une seule clef avec retour de la valeur
    public static String LectureClefJson(String FileJson, String clef){
        JSONParser lecture = new JSONParser();
        String clefs="";
        try {
            Object obj = lecture.parse(new FileReader(FileJson));
            JSONObject maConfiguration = (JSONObject) obj;
            clefs = (String) maConfiguration.get(clef);

        } catch (IOException | ParseException ex) {
            Logger.getLogger(JsonDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
      return clefs;
    }
    
     //methode de lecture d'une seule clef avec retour de la valeur en objet json
    public static JSONObject LectureClefJsonObject(String FileJson, String clef){
        JSONParser lecture = new JSONParser();
        JSONObject clefs=null;
        try {
            Object obj = lecture.parse(new FileReader(FileJson));
            JSONObject maConfiguration = (JSONObject) obj;
            clefs =  (JSONObject)maConfiguration.get(clef);

        } catch (IOException | ParseException ex) {
            Logger.getLogger(JsonDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
      return clefs;
    }
        //methode de lecture de tous le fichier  avec retour objet json
    public static JSONObject LectureTousFichierJson(String FileJson){
        JSONParser lecture = new JSONParser();
        JSONObject maConfiguration = new JSONObject();
        try {
            Object obj = lecture.parse(new FileReader(FileJson));
            maConfiguration = (JSONObject) obj;
          
        } catch (IOException | ParseException ex) {
            Logger.getLogger(JsonDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
       return maConfiguration; 
    }
         //methode de lecture de tous le fichier  avec retour objet json en utilisant le file comme entree pour verifier sont existance
    public static JSONObject LectureTousFichierJsonFile(File FileJson){
        JSONParser lecture = new JSONParser();
        JSONObject maConfiguration = new JSONObject();
        try {
            Object obj = lecture.parse(new FileReader(FileJson));
            maConfiguration = (JSONObject) obj;
          
        } catch (IOException | ParseException ex) {
            Logger.getLogger(JsonDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
       return maConfiguration; 
    }  
    //methode pour ajouter une clef a un fichier json
    public static void AjouterClefJson(String FileJson, String cle,String valeur) {
        JSONParser lecture = new JSONParser();
        try {
            Object obj = lecture.parse(new FileReader(FileJson));
            JSONObject maConfiguration = (JSONObject) obj;
            maConfiguration.put(cle, valeur);
            FileWriter save = new FileWriter(FileJson);
            save.write(maConfiguration.toJSONString());
            save.flush();
        } catch (IOException | ParseException ex) {
            Logger.getLogger(JsonDriver.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    //methode pour ajouter un jsonObject a un fichier json
    public static void AjouterJSONObjectJson(String FileJson, String cle,JSONObject valeur) {
        JSONParser lecture = new JSONParser();
        try {
            Object obj = lecture.parse(new FileReader(FileJson));
            JSONObject maConfiguration = (JSONObject) obj;
            maConfiguration.put(cle, valeur);
            FileWriter save = new FileWriter(FileJson);
            save.write(maConfiguration.toJSONString());
            save.flush();
        } catch (IOException | ParseException ex) {
            Logger.getLogger(JsonDriver.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    //methode pour supprimer une clef du fichier json avec retour true si ok
    public static Boolean SupprimerClefJson(String FileJson, String cle) {
        JSONParser lecture = new JSONParser();
        try {
            Object obj = lecture.parse(new FileReader(FileJson));
            JSONObject maConfiguration = (JSONObject) obj;
            maConfiguration.remove(cle);
            FileWriter save = new FileWriter(FileJson);
            save.write(maConfiguration.toJSONString());
            save.flush();
        } catch (IOException | ParseException ex) {
            Logger.getLogger(JsonDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
       return true;
    }
    
    //methode pour modifier une clef du fichier json avec retour true si ok
    public static Boolean ModifierClefJson(String FileJson, String cle,String nouvelleValeur) {
        JSONParser lecture = new JSONParser();
        try {
            Object obj = lecture.parse(new FileReader(FileJson));
            JSONObject maConfiguration = (JSONObject) obj;
            maConfiguration.replace(cle, nouvelleValeur);
            FileWriter save = new FileWriter(FileJson);
            save.write(maConfiguration.toJSONString());
            save.flush();
        } catch (IOException | ParseException ex) {
            Logger.getLogger(JsonDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
       return true;
    }
        //methode pour modifier une clef du fichier json avec retour true si ok
    public static Boolean ModifierJSONObjetJson(String FileJson,String clePrimaire, String cle,String nouvelleValeur) {
        JSONParser lecture = new JSONParser();
        try {
            Object obj = lecture.parse(new FileReader(FileJson));
            JSONObject maConfiguration = (JSONObject) obj;
            JSONObject objet2 = (JSONObject) maConfiguration.get(clePrimaire);
            objet2.replace(cle, nouvelleValeur);
            FileWriter save = new FileWriter(FileJson);
            save.write(maConfiguration.toJSONString());
            save.flush();
        } catch (IOException | ParseException ex) {
            Logger.getLogger(JsonDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
       return true;
    }
    //methode pour ajouter une valeur a un JsonArray 
    public static void AjouterValueJsonArray(String FileJson, String cle,String valeur) {
        JSONParser lecture = new JSONParser();
        try {
            Object obj = lecture.parse(new FileReader(FileJson));
            JSONObject maConfiguration = (JSONObject) obj;
            JSONArray temp= (JSONArray) maConfiguration.get(cle);
            temp.add(valeur);
            maConfiguration.put(cle, temp);
            FileWriter save = new FileWriter(FileJson);
            save.write(maConfiguration.toJSONString());
            save.flush();
        } catch (IOException | ParseException ex) {
            Logger.getLogger(JsonDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     //methode pour supprimer une valeur du array
    public static Boolean SupprimerClefJsonArray(String FileJson,String cle, String valeur) {
        JSONParser lecture = new JSONParser();
        try {
            Object obj = lecture.parse(new FileReader(FileJson));
            JSONObject maConfiguration = (JSONObject) obj;
            JSONArray temp= (JSONArray) maConfiguration.get(cle);
            temp.remove(valeur);
            maConfiguration.put(cle, temp);
            FileWriter save = new FileWriter(FileJson);
            save.write(maConfiguration.toJSONString());
            save.flush();
        } catch (IOException | ParseException ex) {
            Logger.getLogger(JsonDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
       return true;
    }
}

