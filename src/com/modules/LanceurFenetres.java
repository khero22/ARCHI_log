/*
 * le:23/01/2017

  class pour creer des instance de fenetres type javaFX
 */
package com.modules;

/**
 *
 * @author AS khero
 */
public class LanceurFenetres  {
    
    public static int NOMBRESFENETRES=0;
    private String FenetreRessource ="";
    private String FenetreNom =NomChamps.NOM_APP;
    private String FenetreCss =NomChamps.CSS;
    private final String FenetreIcon=NomChamps.ICON;
    //constructeur avec trois parametres
    public LanceurFenetres(String FRes,String FNom,String Css) {
        FenetreRessource=FRes;
        FenetreNom=FNom;
        FenetreCss=Css;
        NOMBRESFENETRES ++;
    }
    //constructeur avec un seule parametre ressources de la fenetre
    public LanceurFenetres(String FRes) {
        FenetreRessource=FRes;
        NOMBRESFENETRES ++;
    }

    public String getCss(){
        return FenetreCss; 
    }
    public String getRessources(){
        return FenetreRessource; 
    }
    public String getFenetreNom(){
        return FenetreNom; 
    }
    //pour retourner le nombresdes fenetres 
    public int getNombresDeFenetres(){
        return NOMBRESFENETRES; 
    } 
    public String getFenetreIcon(){
        return FenetreIcon;
    } 
            
}
