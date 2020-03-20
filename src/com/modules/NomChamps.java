/*
   tous les nom de champs dans la table pour ne pas faire d'erreur d'ecriture
 */
package com.modules;

/**
 * le:23/01/2017
 *
 * @author khero
 */
public class NomChamps {

    public static final String NOM_APP = "Logiciel ARCHI_log Gestion de Projet pour Bureau d'etude [ version: " + Variables.VERSION + " ]";
    public static final String ICON = "/ARCHI_log/data/icons/logoAcad.jpg"; // a modifier par logo ACAD
    public static final String ICON_BASE = "/ARCHI_log/data/icons/.Ndoc.png";
    public static final String ICON_CLIENT = "/ARCHI_log/data/icons/.Nclient.png";
    public static final String ICON_PROJET = "/ARCHI_log/data/icons/.Nprojet.png";
    public static String CSS = "ARCHI_log/data/icons/style.css";
    public static final String FICHIER_JSON_PROJET = System.getProperty("user.dir") + "/data/DB/PROJETS.json";
    public static final String FICHIER_JSON_CLIENT = System.getProperty("user.dir") + "/data/DB/CLIENTS.json";
    public static final String FICHIER_JSON_ANNEE = System.getProperty("user.dir") + "/data/DB/ANNEES.json";
    public static final String FICHIER_JSON_CATEGORIE = System.getProperty("user.dir") + "/data/DB/CATEGORIES.json";
}
